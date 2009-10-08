#!/usr/bin/perl
#jbmorris October/November, 2006
#-i could cause 0 byte file if error - shell script that calls this makes backup
#(with CVS, using -i.bak or similar does not leave a backup anywhere that I see, locally or remotely)

#TODO: jbmorris:
#bug: if removed comment has other information on line following end of comment, it will be lost; same if other information on line before beginning of comment
#bug: if XML header <?xml etc.> takes up more than one line, it will be hosed
#bug?: on single-line files that need the first line preserved, no comment is
#      added (but that would just be a header, so who cares?)
#bug?: blank files get no license added (who cares?)

#modified by chb - June, 2009
#bug: sql files are missing end comment

use Tie::File;

use strict;
use warnings;

sub get_years();
sub AddNewHeader(@);

my($file,$style,$debug) = @ARGV;
if ($style eq "") { exit 2; }
$debug = ($debug eq "true") ? 1 : 0; 

if($debug){print "Processing " . $file . " with style of: " . $style . "\n";}

#chb: old costly way
#open READFILE, "$file" or die "Can't open $file : $!";
#chb: not as costly?
my @farray;
tie @farray, 'Tie::File', "$file" or die "Can't open $file : $!";

#chb: std perl idiom for getting the current year
#could use localtime, but CVS stores in GMT, so might as well do that here; it only matters on New Year's Eve, and hopefully people aren't checking in then anyway
my(undef,undef,undef,undef,undef,$current_year,undef,undef) = gmtime;  
$current_year += 1900;

my $licensePage = "ecl2.php";

my %comment_start = (
    C => "/*",
    HTML => "<!--",
    JSP => "<%--",
    VM => "#*",
    PROPS => "#",
    SHELL => "#",
    DOS => "REM",
    SQLLDR => "--",
    SQR => "!",
);

my %comment_end = (
    C => "*/",
    HTML => "-->",
    JSP => "--%>",
    VM => "*#",
    #other types don't have an ending comment character, they're just per line
);

#ones in this list either have no opening/closing comment indicators, but
#rather have a character that means the rest of the line is a comment, or
#we just want to insert some extra characters with the comment for formatting
my %comment_per_line = (
    C => " *",
    VM => " *",
    PROPS => "#",
    SHELL => "#",
    DOS => "REM",
    SQLLDR => "--",
    SQR => "!",
    JSP => "",
    HTML => "",
);

#chb: if we have a style that doesn't have an associated comment start, exit
#seems odd to do this, given that we have written $comment_start....?
if (defined $comment_start{$style} != 1) 
{
    exit 1
}

if($debug) { print "About to call get_years\n"; }
my @svnLogYears = get_years();

if($debug) {print "this is the result of get_years: ". $svnLogYears[0]  . " and " . $svnLogYears[1] . "\n";}

# chb: idea here is to go line by line, testing for various regexes, with the 
# intent of discerning one of three possible forms of header:
#   a) no header--we go straight into code of some type (indicated by $style)
#   b) an old Kuali header--must parse year range and ecl version number and 
#       check latter (see c)
#   c) a current header--some unfortunate individual runs this script on code 
#       that has already been processed (exit)
#   d) a third-party header in a file that did not make it into files.ignore
#      (exit)

# another perl trick--any file passed in on the command line is assumed to
# be the default file handle
my $linesParsed = 20;
my( $markupPrologueIx, $commentExists, $newCommentKuali, $oldCommentKuali, $commentOther, $codeBreak );
$markupPrologueIx = $commentExists = $newCommentKuali = $oldCommentKuali = $commentOther = $codeBreak = 0;
my($commentKuali, $oldEclIx, $oldEclPhraseIx, $newEclPhraseIx, $kualiCommentYearsIx, $shellOrMarkup);
$commentKuali =  $oldEclIx = $oldEclPhraseIx = $newEclPhraseIx = $kualiCommentYearsIx = $shellOrMarkup = 0;

#chb: size used farther down when farray is spliced
my $farraySize = @farray;
my $limit = ($farraySize < $linesParsed ? $farraySize : $linesParsed );
if($debug){print "tied array is this big: " . $farraySize . "\n";}
#if($debug){print "This is limit: ". $limit . "\n";}

my $i;
for($i=0; $i<$limit; $i++)
{
    #if($debug && $i==0) {print STDERR "In farray loop.\n";}
    my $line = $farray[$i];
    #if($debug){print STDERR "Current index: ". $i . "\n";}
    #if($debug){print STDERR "Current line: ". $line . "\n";}
    
    if( defined $line != 1)
    {
        print STDERR "ERROR: line undefined in file " . $file . "\n";
    }
    elsif( defined $style != 1)
    {
        print STDERR "ERROR: style undefined in file " . $file . "\n";
    }
    elsif(defined $comment_per_line{$style} != 1)
    {
        print STDERR "ERROR: comment_per_line undefined for style in file " . $file . "\n";
    }

    if($style eq "C" && $line =~ /^(package|import)\s+[a-zA-Z0-9.]*;.*/ )
    {
        #chb: get out
        #if($debug){print STDERR "In code break conditional\n";}
        $codeBreak = 1; 
        last;
    }
    if($style eq "SHELL" || ($style eq "HTML" && $line =~ /^<\?xml /)) 
    {  
        #if($debug){print "In shell conditional\n";}
        #shell/perl scripts and well-formed XML documents need the first line preserved
        $shellOrMarkup = 1;
        $markupPrologueIx = $i;
        #if($debug){print "This is prologue line number" . $markupPrologueIx . "\n";}
    } 
    if ($line =~ /^\s*\Q$comment_start{$style}/ && $commentExists == 0 ) 
    {
        #if($debug){print "In exists conditional.\n";}
        $commentExists = 1;
    }
    if( ($line =~ /Copyright.*The\sKuali\sFoundation/ ) && $commentExists)
    {
        #if($debug){print "In kuali comment conditional.\n";}
        $commentKuali = 1;
        $kualiCommentYearsIx = $i;
    }
    if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*Licensed under the Educational Community License, Version 1\.0.*/ && $commentExists && $commentKuali)
    {
        #if($debug){print "In old kuali comment ECL phrase conditional.\n";}
        $oldCommentKuali = 1;
        $oldEclPhraseIx = $i;
    }
    if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*Licensed under the Educational Community License, Version 2\.0.*/ && $commentExists && $commentKuali)
    {
        #if($debug){print "In new kuali comment ECL phrase conditional.\n";}
        $newCommentKuali = 1;
        $newEclPhraseIx = $i;
    }
    if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*http:.*\/ecl1.php/ && $commentExists && $commentKuali)
    {
        #if($debug){print "In old kuali comment conditional.\n";}
        $oldCommentKuali = 1;
        $oldEclIx = $i;
    }
    if($commentKuali == 0 && $commentExists == 1)
    {
        if($line =~ /(apache|mozilla|antlr|bea systems|bouncy|cddl|common public)/i || $i == ($linesParsed - 1))
        {
            #if($debug){print "In other comment conditional.\n";}
            $commentOther = 1;
        }
    }
}

#if comment is not third-party 
if( $commentOther == 0 && $newCommentKuali == 0)
{
    #open WRITEFILE, "> $file" or die "Can't open $file : $!";
    my @newFile;

    #if we just need to add a new header
    if( $commentExists == 0 || ( ($style eq "PROPS") && $commentExists == 1) )
    {
        #if($debug){print "comment exists == " . $commentExists . "\n";}
        my @heredocArr = AddNewHeader(@svnLogYears);
        
        if( $shellOrMarkup )
        {
            my $prologue = $farray[$markupPrologueIx];
            #if($debug){print "In commentExists check\n";}
            #insert the output of add new header into the array after the markup prologue
            if($debug)
            {
                #print "DEBUG: Printing heredocarr.\n"; 
                #print @heredocArr;
            } 
            #chb: don't want a duplicate prologue so take off the first line
            my @tail = splice(@farray,$markupPrologueIx+1,$farraySize-1); 
            @farray = ($prologue,@heredocArr,@tail); 
            
            if($debug)
            {
                #print "DEBUG: Printing newfile ARRAY.\n"; 
                #print @newFile;
            }
        }
        else
        {
            if($debug)
            {
                #print "DEBUG: Printing heredocarr.\n"; 
                #print @heredocArr;
            } 
            @farray = (@heredocArr,@farray); 
        }
    }
    elsif ( $oldCommentKuali )    #if we need to modify an old Kuali comment
    {
        #chb: need to replace year and ecl version, year first...
        my $oldYearLine = $farray[$kualiCommentYearsIx];
        if($debug){print "This is oldYearLine : " . $oldYearLine .  "\n"; }
        #need to guard against possibility that old license contains a single year and not range  
        #by comparing the first year in the old header with the years in svn log years
        if ($oldYearLine =~ /(^.*)Copyright (200[0-9])(.*)/ )
        {
            @svnLogYears = ($2,@svnLogYears);
            @svnLogYears = sort(@svnLogYears);
            my $kualiStr = " The Kuali Foundation";
         
#            if($debug)
#            {
#                print "Year handling vars: \n"; 
#                print "svnLogYears:\n"; 
#                print @svnLogYears . "\n";
#                print "This is second match: " . $2 . "\n";
#            } 
            if($2 < $svnLogYears[0] )
            {
                $oldYearLine =~ s/(^.*)Copyright (200[0-9])(.*)/$1Copyright $2-$svnLogYears[1]$kualiStr/;
            }
            elsif ($2 > $svnLogYears[0] ) 
            {      
               if($2 > $svnLogYears[1] || $2 == $svnLogYears[1])
               { 
                   $oldYearLine =~ s/(^.*)Copyright (200[0-9])(.*)/$1Copyright $svnLogYears[0]-$2$kualiStr/;
               }
               elsif($2 < $svnLogYears[1] )
               {
                   $oldYearLine =~ s/(^.*)Copyright (200[0-9])(.*)/$1Copyright $svnLogYears[0]-$svnLogYears[1]$kualiStr/;
               } 
            }
            elsif($2 == $svnLogYears[0] )
            {
                if($svnLogYears[0] == $svnLogYears[1] )
                {
                    $oldYearLine =~ s/(^.*)Copyright (200[0-9])(.*)/$1Copyright $2$kualiStr/;
                }
                else
                {
                    $oldYearLine =~ s/(^.*)Copyright (200[0-9])(.*)/$1Copyright $svnLogYears[0]-$svnLogYears[1]$kualiStr/;
                }
            }
        }
        else
        {
           print STDERR "ERROR: kualiCommentYearsIx returned line with no Copyright date info";
        } 
        
        #if($debug){print "This is oldYearLine : " . $oldYearLine .  "\n"; }
      
        my $oldLicensePhraseLine = $farray[$oldEclPhraseIx];
        #if($debug){print "This is oldLicensePhraseLine : " . $oldLicensePhraseLine .  "\n"; }
        $oldLicensePhraseLine =~ s/(^.*)Version 1\.0(.*$)/$1Version 2.0$2/;
        #if($debug){print "This is oldLicensePhraseLine : " . $oldLicensePhraseLine .  "\n"; }

        #chb: now we take and modify the ecl url
        my $oldLicenseLine = $farray[$oldEclIx];
        #if($debug){print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; }
        $oldLicenseLine =~ s/(^.*)http(.*)ecl1\.php(\s*$)/$1http$2$licensePage$3/;
        #if($debug){print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; }
        #chb: layout of license means we have 5 arrays to order, years first, ecl version second 
        my @firstPart = @farray[0 .. $kualiCommentYearsIx-1];#start of ile to year range
        #my @oldYearLine = ($oldYearLine);#year range 
        my @thirdPart = @farray[$kualiCommentYearsIx+1 .. $oldEclPhraseIx-1];#after year range to before ECL phrase
        #my @oldLicensePhraseLine = ($oldLicensePhraseLine); #ECL phrase
        my @fifthPart = @farray[$oldEclPhraseIx+1 .. $oldEclIx-1]; #after ECL phrase to before ECL URL
        #my @oldLicenseLine = ($oldLicenseLine); #ECL URL
        my @seventhPart = @farray[$oldEclIx+1 .. $farraySize-1];
        @farray = (@firstPart,$oldYearLine,@thirdPart,$oldLicensePhraseLine,@fifthPart,$oldLicenseLine,@seventhPart);
        #print WRITEFILE @newFile;
    }
    #close WRITEFILE;
    untie @farray;
}

exit;

sub AddNewHeader(@)
{
    my @yearParms = @_;

    my $c = $comment_per_line{$style};
    my $comment_start = $comment_start{$style};
    my $comment_end = $comment_end{$style};
    if($debug){print "This is arg 0 in AddNewHeader: " . $yearParms[0] . "\n"; }
    my $copyrightYears; 
    if( $yearParms[0] == $yearParms[1] )
    {
        $copyrightYears = $yearParms[0];
    }
    else
    { 
        $copyrightYears = $_[0] . "-" . $_[1];
    } 
    #if($debug){print "This is comment_end in AddNewHeader: " . $comment_end . "\n"; }
    if ($comment_end eq "") {
        $comment_end = "$c DO NOT add comments before the blank line below, or they will disappear.\n";
    } 
    else 
    {
        if ($c ne "") 
        {
            $comment_end = " $comment_end";  #just taking care of a mild style preference I've seen
        }
    }

    #print<<ENDHEADER;
    my $heredocHeader = <<ENDHEADER; 
$comment_start
$c Copyright $copyrightYears The Kuali Foundation
$c 
$c Licensed under the Educational Community License, Version 2.0 (the "License");
$c you may not use this file except in compliance with the License.
$c You may obtain a copy of the License at
$c 
$c http://www.opensource.org/licenses/$licensePage
$c 
$c Unless required by applicable law or agreed to in writing, software
$c distributed under the License is distributed on an "AS IS" BASIS,
$c WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
$c See the License for the specific language governing permissions and
$c limitations under the License.
$comment_end
ENDHEADER
    my @heredocArr = split('\n', $heredocHeader); 
    return @heredocArr;
}

# chb: returns an array of size 2 with the earliest svn commit date first
# if only one commit date or earliest date == latest, then both elements will
# have the same value
sub get_years()
{
    #if($debug){print "In get years\n";}
    my(@checkin_dates,@sorted_dates,$line);
    my @outlines = `svn log $file`;

    my $size = @outlines;
    if( !$size )
    {
        print "ERROR: size not init'd before while check \nIn file: " . $file . "\n"; 
    }
    
    if($debug)
    {
        #print "outlines is this: " . $size . " big\n";
        #print "The second element is " . $outlines[1] . "\n...and the penultimate is " . $outlines[$size - 2] . "\n";
    }

    while (@outlines) 
    {
        $size = @outlines;
        $line = shift(@outlines);
        #if($debug){print "DEBUG: This is line: " . $line . "\n"; }
        
        if(! $line )
        {
            print "ERROR: line not init'd before while check \nIn file: " . $file . "\n"; 
        }  
        
        my $delim = "-{72}";
        if ( $line =~ /^$delim$/ && $size > 1)
        {
            #if($debug){print "Delim line matched...\n";}

            $line = shift(@outlines);
            #if($debug){print "This is shifted line after successful match for delim: " . $line . "\n";}

            while( $line !~ /^$delim$/ ) 
            {
                #if($debug){print "This is line after no match for delim: " . $line . "\n"; }
                if ($line =~ /^r[0-9]* \| [a-zA-Z0-9]* \| ([0-9]{4})-[0-9]{2}-[0-9]{2}/) 
                { 
                    #if($debug) { print "This is the pushed year after successful match for year ".$1 . "\n";}
                    push @checkin_dates,$1;  #got $1 via parens in above pattern match
                    $line = shift(@outlines);
                }
                elsif ($line =~ /^automated update of standard license header$/) 
                {
                    shift @checkin_dates;  #we can ignore log comments generated by this program itself
                    $line = shift(@outlines);
                    #if($debug) { print "This is line after shifting false date: " . $line . " \n"; }
                }
                elsif ($line =~ /^Creating new kuali repository due to corruption of prior repository that was creating issues with the Anthill continuous integration setup$/) {
                    push @checkin_dates,"2005";  #repository was recreated 1-14-2006, so most things with this message were actually originally checked in during 2005
                    $line = shift(@outlines);
                    #if($debug) { print "This is line after pushing date for repo corruption: " . $line . " \n"; }
                } 
                else
                {
                    $line = shift(@outlines);
                    $size = @outlines;
                }
            }
        }
    }
    my $size_dates = @checkin_dates;
    
    #if($debug){print "This is the size of checkin_dates: " . $size_dates . "\n";}  
    if(!$size_dates || $size_dates == 0) 
    {
        print STDERR "ERROR: no commit date found in svn log";
        exit -1
    }
    @sorted_dates = sort(@checkin_dates);
    #chb: get earliest and latest years
    @sorted_dates[0,1] = @sorted_dates[0, $size_dates-1];
    return @sorted_dates;
   
}


