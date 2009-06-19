#!/usr/bin/perl
#jbmorris October/November, 2006
#-i could cause 0 byte file if error - shell script that calls this makes backup
#(with CVS, using -i.bak or similar does not leave a backup anywhere that I see, locally or remotely)

#TODO:
#bug: if removed comment has other information on line following end of comment, it will be lost; same if other information on line before beginning of comment
#bug: if XML header <?xml etc.> takes up more than one line, it will be hosed
#bug?: on single-line files that need the first line preserved, no comment is
#      added (but that would just be a header, so who cares?)
#bug?: blank files get no license added (who cares?)

use strict;
use warnings;
#BEGIN
#{
#    open (STDERR,">>$0-err.txt");
#    print STDERR "\n",scalar localtime,"\n";
#}
#chb: prototype declarations
sub get_years();
sub AddNewHeader(@);

my($file,$style,$garbage) = @ARGV;
if ($style eq "") { exit 2; }
if ($garbage ne "") { exit 3; }

open READFILE, $file;# or die "Can't open $file : $!";

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

print "About to call get_years\n";
my $getYearsResult = get_years();
my @svnLogYears = split(/-/,$getYearsResult);
print "this is the result of get_years: ". $svnLogYears[0]  . " and " . $svnLogYears[1] . "\n";

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
my($count, $markupPrologueIx, $commentExists, $oldCommentKuali, $commentOther);
$count = $markupPrologueIx = $commentExists = $oldCommentKuali = $commentOther = 0;
my($commentKuali, $oldEclIx, $kualiCommentYearsIx, $shellOrMarkup);
$commentKuali =  $oldEclIx = $kualiCommentYearsIx = $shellOrMarkup = 0;

while(<READFILE>)
{
    print STDERR "In READFILE while.\n";
    if ($. < $linesParsed)
    {
        print STDERR "In conditional after while.\n";
        my $line = $_;

        if($style eq "SHELL" || ($style eq "HTML" && $line =~ /^<\?xml /)) 
        {  
            print "In shell conditional\n";
            #shell/perl scripts and well-formed XML documents need the first line preserved
            $shellOrMarkup = 1;
            $markupPrologueIx = $. - 1;
            print "This is prologue line number" . $markupPrologueIx . "\n";
        } 
        if ($line =~ /^\s*\Q$comment_start{$style}/ && $commentExists eq 0) 
        {
            print "In exists conditional.\n";
            $commentExists = 1;
        }
        if( ($line =~ /^\s*\Q$comment_per_line{$style}\E\s*Copyright\s*200[0-9]-200[0-9]\s*The\sKuali/ ) && $commentExists)
        {
            print "In kuali comment conditional.\n";
            $commentKuali = 1;
            $kualiCommentYearsIx = $. - 1;
        }
        if($line =~ /^\s*\Q$comment_per_line{$style}\E\s*http:.*\/ecl1.php/ && $commentExists && $commentKuali)
        {
            print "In old kuali comment conditional.\n";
            $oldCommentKuali = 1;
            $oldEclIx = $. - 1;
        }
        if($commentKuali == 0 && $commentExists == 1)
        {
            print "In other comment conditional.\n";
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

print "Readlines is this big: " . $readLines . "\n";

print "This is file open for writing " . $file . "\n";

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
            my @prologue = @readLines[$markupPrologueIx];
            print "In commentExists check\n";
            #insert the output of add new header into the array after the markup prologue
            print "DEBUG: Printing readLines\n";
            print STDERR @readLines;

            print "DEBUG: Printing heredocarr.\n"; 
            print STDERR @heredocArr;
            #@newFile = splice(@readLines,1,0,@heredocArr); 
            @newFile = (@prologue,@heredocArr,@readLines); 
            print "DEBUG: Printing newfile ARRAY.\n"; 
            print STDERR @newFile;
        }
        else
        {
            @newFile = (@heredocArr,@readLines); 
        }
        print WRITEFILE @newFile;
    }
    #if we need to modify an old Kuali comment
    elsif ( $oldCommentKuali  )
    {
        #chb: need to replace year and ecl version, year first...
        my $oldYearLine = @readLines[$kualiCommentYearsIx];
        print "This is oldYearLine : " . $oldYearLine .  "\n"; 
        $oldYearLine =~ s/(^.*)Copyright (200[0-9])-200[0-9](.*)/\1Copyright \2-@svnLogYears[1]\3/;
        print "This is oldYearLine : " . $oldYearLine .  "\n"; 
       
        #chb: now we take and modify the ecl url
        my $oldLicenseLine = @readLines[$oldEclIx];
        print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; 
        $oldLicenseLine =~ s/(^.*)http(.*)ecl1\.php(\s*$)/\1http\2$licensePage\3/;
        print "This is oldLicenseLine : " . $oldLicenseLine .  "\n"; 
        #chb: layout of license means we have 5 arrays to order, years first, ecl version second 
        my @firstPart = @readLines[0 .. $kualiCommentYearsIx-1];
        my @oldYearLine = ($oldYearLine); 
        my @thirdPart = @readLines[$kualiCommentYearsIx+1 .. $oldEclIx-1];
        my @oldLicenseLine = ($oldLicenseLine);
        my @fifthPart = @readLines[$oldEclIx+1 .. $readLines-1];
        @newFile = (@firstPart,@oldYearLine,@thirdPart,@oldLicenseLine,@fifthPart);
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
    print "This is arg 0 in AddNewHeader: " . $yearParms[0] . "\n"; 
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
    $c Copyright $copyrightYears Foo Foundation
    $c 
    $c Licensed under the Educational Community License, Version 1.0 (the "License");
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
    print "In get years\n";
    my(@checkin_dates,@sorted_years,$line,$year,$previous_year,$years);
    my @outlines = `svn log $file`;

    my $size = @outlines;
    print "outlines is this: " . $size . " big\n";
    print "The second element is " . $outlines[1] . "\n...and the penultimate is " . $outlines[$size - 2] . "\n";
    while (@outlines) 
    {
        $line = shift(@outlines);
        $size = @outlines;
        print "outlines is this big " . $size . "\n"; 
        print $line . "\n";

        my $delim = "-{72}";
        if ( $line =~ /^$delim$/)
        {
            print "hello3\n";
            $line = shift(@outlines);
            $size = @outlines;

            while( $line !~ /^$delim$/ && $size > 0) 
            {
                if ($line =~ /^r[0-9]* \| [a-zA-Z]* \| ([0-9]{4})-[0-9]{2}-[0-9]{2}/) 
                { 
                    print "This is the pushed year ".$1 . "\n";
                    push @checkin_dates,$1;  #got $1 via parens in above pattern match
                    $line = shift(@outlines);
                }
                elsif ($line =~ /^automated update of standard license header$/) 
                {
                    shift @checkin_dates;  #we can ignore log comments generated by this program itself
                    $line = shift(@outlines);
                    print "This is line after shifting false date: " . $line . " \n"; 
                }
                elsif ($line =~ /^Creating new kuali repository due to corruption of prior repository that was creating issues with the Anthill continuous integration setup$/) {
                    push @checkin_dates,"2005";  #repository was recreated 1-14-2006, so most things with this message were actually originally checked in during 2005
                    $line = shift(@outlines);
                    print "This is line after pushing date for repo corruption: " . $line . " \n"; 
                } 
                else
                {
                    $line = shift(@outlines);
                    $size = @outlines;
                }
            }
        }
    }
    @sorted_years = sort(@checkin_dates);
    $years = shift(@sorted_years);
    print "This is years after sort: " . $years . "\n";
    $previous_year = $years;

    foreach $year (@sorted_years) {
        if ($year eq $previous_year) { next; }  #don't need duplicates
        if ($year == $previous_year + 1) {
            if (substr($years,-5,1) eq "-") {
                substr($years,-4) = $year;
            } else {
                $years .= "-$year";
            }
        } else {
            $years .= ",$year";
        }
        $previous_year = $year;
    }
    return $years;
}

