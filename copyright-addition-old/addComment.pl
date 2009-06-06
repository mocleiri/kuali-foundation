#!/usr/bin/perl -i
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

my($logflag);
my($file,$style,$tag,$garbage) = @ARGV;
if ($style eq "") { exit 3; }
if ($garbage ne "") { exit 4; }
if ($tag eq "") {
  $logflag = "-b";  #look at trunk only
} else {
  pop;
  if ($tag eq "-A") {
    $logflag = "-b";  #look at trunk only
  } elsif ($tag =~ /^-r/) {
    $logflag = "-r1.1:" . substr($tag,2);
  } else {
    exit 5;
  }
}
pop; #want only filename left for <> processing later
my(undef,undef,undef,undef,undef,$current_year,undef,undef) = gmtime;  #could use localtime, but CVS stores in GMT, so might as well do that here; it only matters on New Year's Eve, and hopefully people aren't checking in then anyway
$current_year += 1900;

my $HeaderRemoved = "";

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
);

if ($comment_start{$style} eq "") {
  exit 1
}

while(<>) {
  if ($HeaderRemoved || ($. == 1 && ($style eq "SHELL" || ($style eq "HTML" && /^<\?xml /)))) {  #shell/perl scripts and well-formed XML documents need the first line preserved
    print $_;
  } else {
    if ($_ eq "\n") { next; }
    my($line) = $_;
    $HeaderRemoved .= $line;
    &AddNewHeader;
    if ($line =~ /^\s*\Q$comment_start{$style}/) {
      while (!eof() &&
             (($comment_end{$style} ne "" && $line !~ /\Q$comment_end{$style}/) ||
              ($comment_end{$style} eq "" && $line =~ /^\s*\Q$comment_per_line{$style}/))) {
        $line = <>;
        $HeaderRemoved .= $line;
      }
      #if it doesn't look like our comment, put it back in
      unless (grep(/copyright/i,$HeaderRemoved) && grep(/kuali/i,$HeaderRemoved)) {
        print $HeaderRemoved;
      }
    } else {
      print $line;
      # in this case there was no header, at least not at the very top
    }
  }
}

sub AddNewHeader {
  my $c = $comment_per_line{$style};
  my $comment_start = $comment_start{$style};
  my $comment_end = $comment_end{$style};
  my $years;
  if ($comment_end eq "") {
    $comment_end = "$c DO NOT add comments before the blank line below, or they will disappear.\n";
  } else {
    if ($c ne "") {
      $comment_end = " $comment_end";  #just taking care of a mild style preference I've seen
    }
  }
  $years = &get_years;

  print<<ENDHEADER;
$comment_start
$c Copyright $years The Kuali Foundation.
$c 
$c Licensed under the Educational Community License, Version 1.0 (the "License");
$c you may not use this file except in compliance with the License.
$c You may obtain a copy of the License at
$c 
$c http://www.opensource.org/licenses/ecl1.php
$c 
$c Unless required by applicable law or agreed to in writing, software
$c distributed under the License is distributed on an "AS IS" BASIS,
$c WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
$c See the License for the specific language governing permissions and
$c limitations under the License.
$comment_end
ENDHEADER
}

sub get_years {
  my(@checkin_dates,@sorted_years,$year,$previous_year,$years);

  open(CVSLOG,"cvs log -N $logflag $file|");
  while(<CVSLOG>) {
    if (/^----------------------------$/) {  #beginning of revision block
      $_ = <CVSLOG>;  #next line should be revision number
      if (!/^revision ([0-9]+\.)+[0-9]+/) { exit 2; }
      $_ = <CVSLOG>;  #next line should be date, which we are interested in
      if (!/^date: ([0-9]{4}\/[0-9]{2}\/[0-9]{2}) [0-9]{2}:[0-9]{2}:[0-9]{2};  author: .*;  state: /) { exit 2; }
      push @checkin_dates,$1;  #got $1 via parens in above pattern match
      $_ = <CVSLOG>;  #next line should be first of any number of lines in check-in comment; hope check-in comment is never completely blank (as opposed to *** empty log message ***) since that would make it skip next revision
      if (/^branches: /) {
        $_ = <CVSLOG>;  #we're not to the comment line yet, so skip to next line
      }
      if (/^automated update of standard license header$/) {
        pop @checkin_dates;  #we can ignore log comments generated by this program itself
      }
      if (/^Creating new kuali repository due to corruption of prior repository that was creating issues with the Anthill continuous integration setup$/) {
        push @checkin_dates,"2005";  #repository was recreated 1-14-2006, so most things with this message were actually originally checked in during 2005
      }
    }
  }
  @sorted_years = sort(map(substr($_,0,4),@checkin_dates));
  $years = shift(@sorted_years);
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
