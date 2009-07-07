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

use strict;
use warnings;
#chb: force auto flush of output buffers
$|++;

sub get_years();
sub AddNewHeader(@);

my($file,$style,$debug) = @ARGV;
if ($style eq "") { exit 2; }
$debug = ($debug eq "true") ? 1 : 0; 

open READFILE, "$file" or die "Can't open $file : $!";

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
    SHELL => "#",
    DOS => "REM",
    SQLLDR => "--",
    SQR => "!",
    HTML => "",
);

#chb: if we have a style that doesn't have an associated comment start, exit
#seems odd to do this, given that we have written $comment_start....?
if ($comment_start{$style} eq "") {
    exit 1
}

#TODO: debug
if($debug) { print "About to call get_years\n"; }
my $getYearsResult = get_years();
my @svnLogYears;
if($getYearsResult)
{
    @svnLogYears = split(/-/,$getYearsResult);
}
else
{
    print "ERROR: get_years returned is: " . $getYearsResult . "in file " . $file . "\n";
}

#TODO: debug
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
my $HeaderRemoved = "";
my @firstSixteenLines; 
my $linesParsed = 20;
my($count, $markupPrologueIx, $commentExists, $oldCommentKuali, $commentOther );
$count = $markupPrologueIx = $commentExists = $oldCommentKuali = $commentOther = 0;
my($commentKuali, $oldEclIx, $oldEclPhraseIx, $kualiCommentYearsIx, $shellOrMarkup);
$commentKuali =  $oldEclIx = $oldEclPhraseIx = $kualiCommentYearsIx = $shellOrMarkup = 0;

while(<READFILE>)
{
    if($debug) {print STDERR "In READFILE while.\n";}
    if ($. < $linesParsed)
    {
        if($debug){print STDERR "In conditional after while.\n";}
        my $line = $_;

        if($style eq "SHELL" || ($style eq "HTML" && $line =~ /^<\?xml /)) 
        {  
            if($debug){print "In shell conditional\n";}
            #shell/perl scripts and well-formed XML documents need the first line preserved
            $shellOrMarkup = 1;
            $markupPrologueIx = $. - 1;
            if($debug){print "This is prologue line number" . $markupPrologueIx . "\n";}
        } 
        if ($line =~ /^\s*\Q$comment_start{$style}/ && $commentExists eq 0) 
        {
            if($debug){print "In exists conditional.\n";}
            $commentExists = 1;
        }
        if( ($line =~ /^\s*\Q$comment_per_line{$style}\E\s*Copyright\s*200[0-9]-200[0-9]\s*The\sKuali/ ) && $commentExists)
        {
            if($debug){print "In kuali comment conditional.\n";}
            $commentKuali = 1;
            $kualiCommentYearsIx = $. - 1;
        }
        if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*Licensed under the Educational Community License.*/ && $commentExists && $commentKuali)
        {
            if($debug){print "In old kuali comment ECL phrase conditional.\n";}
            $oldCommentKuali = 1;
            $oldEclPhraseIx = $. - 1;
        }
        if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*http:.*\/ecl1.php/ && $commentExists && $commentKuali)
        {
            if($debug){print "In old kuali comment conditional.\n";}
            $oldCommentKuali = 1;
            $oldEclIx = $. - 1;
        }
        if($commentKuali == 0 && $commentExists == 1)
        {
            if($debug){print "In other comment conditional.\n";}
            if($line =~ /(apache|mozilla|antlr|bea systems|bouncy|cddl|common public)/i || $. == ($linesParsed - 1))
            {
                $commentOther = 1;
            }
        }
    }
}

open READFILE, $file;# or die "Can't open $file : $!";
my @readLines = <READFILE>;
my $readLines = @readLines;
close READFILE;

if($debug){print "Readlines is this big: " . $readLines . "\n";}

if($debug){print "This is file open for writing " . $file . "\n";}

#chb: TODO: this should really make use of the in-place editor
#


#if comment is not third-party 
if( $commentOther == 0)
{
    open WRITEFILE, "> $file" or die "Can't open $file : $!";
    my @newFile;

    #if we just need to add a new header
    if( $commentExists == 0 )
    {
        my @heredocArr = AddNewHeader(@svnLogYears);
        my $heredocArr = @heredocArr;
        
        if( $shellOrMarkup )
        {
            my @prologue = $readLines[$markupPrologueIx];
            if($debug){print "In commentExists check\n";}
            #insert the output of add new header into the array after the markup prologue
            if($debug)
            {
                print "DEBUG: Printing readLines\n";
                print STDERR @readLines;
                print "DEBUG: Printing heredocarr.\n"; 
                print STDERR @heredocArr;
            } 
            #@newFile = splice(@readLines,1,0,@heredocArr); 
            @newFile = (@prologue,@heredocArr,@readLines); 
            
            if($debug)
            {
                print "DEBUG: Printing newfile ARRAY.\n"; 
                print STDERR @newFile;
            }
        }
        else
        {
            @newFile = (@heredocArr,@readLines); 
        }
        if($debug){print WRITEFILE @newFile;}
    }
    #if we need to modify an old Kuali comment
    elsif ( $oldCommentKuali  )
    {
        #chb: need to replace year and ecl version, year first...
        my $oldYearLine = $readLines[$kualiCommentYearsIx];
        if($debug){print "This is oldYearLine : " . $oldYearLine .  "\n"; }
        $oldYearLine =~ s/(^.*)Copyright (200[0-9])-200[0-9](.*)/$1Copyright $2-$svnLogYears[1]$3/;
        if($debug){print "This is oldYearLine : " . $oldYearLine .  "\n"; }
      
        my $oldLicensePhraseLine = $readLines[$oldEclPhraseIx];
        if($debug){print "This is oldLicensePhraseLine : " . $oldLicensePhraseLine .  "\n"; }
        $oldLicensePhraseLine =~ s/(^.*)Version 1\.0(.*$)/$1Version 2.0$2/;
        if($debug){print "This is oldLicensePhraseLine : " . $oldLicensePhraseLine .  "\n"; }

        #chb: now we take and modify the ecl url
        my $oldLicenseLine = $readLines[$oldEclIx];
        if($debug){print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; }
        $oldLicenseLine =~ s/(^.*)http(.*)ecl1\.php(\s*$)/$1http$2$licensePage$3/;
        if($debug){print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; }
        #chb: layout of license means we have 5 arrays to order, years first, ecl version second 
        my @firstPart = @readLines[0 .. $kualiCommentYearsIx-1];#start of ile to year range
        my @oldYearLine = ($oldYearLine);#year range 
        my @thirdPart = @readLines[$kualiCommentYearsIx+1 .. $oldEclPhraseIx-1];#after year range to before ECL phrase
        my @oldLicensePhraseLine = ($oldLicensePhraseLine); #ECL phrase
        my @fifthPart = @readLines[$oldEclPhraseIx+1 .. $oldEclIx-1]; #after ECL phrase to before ECL URL
        my @oldLicenseLine = ($oldLicenseLine); #ECL URL
        my @seventhPart = @readLines[$oldEclIx+1 .. $readLines-1];
        @newFile = (@firstPart,@oldYearLine,@thirdPart,@oldLicensePhraseLine,@fifthPart,@oldLicenseLine,@seventhPart);
        print WRITEFILE @newFile;
    }
    close WRITEFILE;
}

exit;

sub AddNewHeader(@)
{
    my @yearParms = @_;

    my $c = $comment_per_line{$style};
    my $comment_start = $comment_start{$style};
    my $comment_end = $comment_end{$style};
    if($debug){print "This is arg 0 in AddNewHeader: " . $yearParms[0] . "\n"; }
    my $copyrightYears = $_[0] . "-" . $_[1];
    if ($comment_end eq "") {
        $comment_end = "$c DO NOT add comments before the blank line below, or they will disappear.\n";
    } else {
        if ($c ne "") {
            $comment_end = " $comment_end";  #just taking care of a mild style preference I've seen
        }
    }

    #print<<ENDHEADER;
    my @heredocArr = split('/$',<<ENDHEADER); 
    $comment_start
    $c Copyright $copyrightYears FooBar Foundation
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
    return @heredocArr;
}

sub get_years()
{
    if($debug){print "In get years\n";}
    my(@checkin_dates,@sorted_years,$line);
    my @outlines = `svn log $file`;

    my $size = @outlines;
    if( !$size )
    {
        print "ERROR: size not init'd before while check \nIn file: " . $file . "\n"; 
    }
    
    if($debug)
    {
        print "outlines is this: " . $size . " big\n";
        print "The second element is " . $outlines[1] . "\n...and the penultimate is " . $outlines[$size - 2] . "\n";
    }

    while (@outlines) 
    {
        $size = @outlines;
        $line = shift(@outlines);
        if($debug){print "DEBUG: This is line: " . $line . "\n"; }
        
        if(! $line )
        {
            print "ERROR: line not init'd before while check \nIn file: " . $file . "\n"; 
        }  
        
        my $delim = "-{72}";
        if ( $line =~ /^$delim$/ && $size > 1)
        {
            if($debug){print "Delim line matched...\n";}

            $line = shift(@outlines);
            if($debug){print "This is shifted line after successful match for delim: " . $line . "\n";}

            while( $line !~ /^$delim$/ ) 
            {
                if($debug){print "This is line after no match for delim: " . $line . "\n"; }
                if ($line =~ /^r[0-9]* \| [a-zA-Z0-9]* \| ([0-9]{4})-[0-9]{2}-[0-9]{2}/) 
                { 
                    if($debug) { print "This is the pushed year after successful match for year ".$1 . "\n";}
                    push @checkin_dates,$1;  #got $1 via parens in above pattern match
                    $line = shift(@outlines);
                }
                elsif ($line =~ /^automated update of standard license header$/) 
                {
                    shift @checkin_dates;  #we can ignore log comments generated by this program itself
                    $line = shift(@outlines);
                    if($debug) { print "This is line after shifting false date: " . $line . " \n"; }
                }
                elsif ($line =~ /^Creating new kuali repository due to corruption of prior repository that was creating issues with the Anthill continuous integration setup$/) {
                    push @checkin_dates,"2005";  #repository was recreated 1-14-2006, so most things with this message were actually originally checked in during 2005
                    $line = shift(@outlines);
                    if($debug) { print "This is line after pushing date for repo corruption: " . $line . " \n"; }
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
    if($debug){print "This is the size of checkin_dates: " . $size_dates . "\n";}  
    @sorted_years = sort(@checkin_dates);
    #chb: get earliest and latest years
    my $first_year = shift(@sorted_years);
    my $last_year = pop(@sorted_years); 
    if($debug){print "This is the first_year: " . $first_year . "\n";}
    if($debug){print "This is the last_year: " . $last_year . "\n";}
    my $years = $first_year;

    if(!$years || $last_year < $first_year)
    {
        print "ERROR: get_years in " . $file . " produces " . $years . "\n";         
        print "This is the first_year: " . $first_year . "\n";
        print "This is the last_year: " . $last_year . "\n";
    }
    if (!$last_year || $first_year eq $last_year)
    {
        #nopreturn $years;
    }
    elsif ($last_year > $first_year ) 
    {
        $years = $first_year . "-" . $last_year; 
        return $years;
    }

    return $years;
}

