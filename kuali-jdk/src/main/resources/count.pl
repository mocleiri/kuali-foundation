#!/usr/bin/perl
use File::stat;
use POSIX qw/strftime/;
use Time::localtime;
$base="/usr/local/sonatype-work/nexus/storage";

chdir("/tmp");
$pwd = `pwd`;
chomp($pwd);
$summary="$pwd/summary.txt";
$attached="$pwd/attached.txt";
$total ="$pwd/total.txt";
$no_checksums ="$pwd/no_checksums.txt";

`rm -f $summary`;
`rm -f $attached`;
`rm -f $total`;
`rm -f $no_checksums`;

$jar="$pwd/jar.txt";
$war="$pwd/war.txt";
$pom="$pwd/pom.txt";
$zip="$pwd/zip.txt";

open summary, ">>$summary" or die "Couldn't open '$summary': $!";
print summary "repository/artifact,total bad check sums, bad md5sum, bad sha1sums\n";
open attached, ">>$attached" or die "Couldn't open '$attached': $!";
print attached "repository/artifact,generated for compare ,checksum file contains ,timestamp\n";

open no_checksums, ">>$no_checksums" or die "Couldn't open '$no_checksums': $!";
print no_checksums "repository/artifact,generated for compare ,checksum file contains ,timestamp\n";

open total, ">>$total" or die "Couldn't open '$total': $!";
print total "Description,Total\n";

`cd $base;find . -not -path \'*/\.*\' -name "*.jar" > $jar`;
`cd $base;find . -not -path \'*/\.*\' -name "*.war" > $war`;
`cd $base;find . -not -path \'*/\.*\' -name "*.pom" > $pom`;
`cd $base;find . -not -path \'*/\.*\' -name "*.zip" > $zip`;

chdir($base);
@REPO=`ls -c1`;
@set = ("sha1:sha1sum" ,"md5:md5sum");

open (J ,"<$jar"); (@JAR =<J>); close (J); 
open (W ,"<$war"); (@WAR =<W>); close (W); 
open (P ,"<$pom"); (@POM =<P>); close (P); 
open (Z ,"<$zip"); (@ZIP =<Z>); close (Z); 

@COMBINE = @JAR;
push(@COMBINE,@POM);
push(@COMBINE,@WAR);
push(@COMBINE,@ZIP);
#push(@COMBINE,@XML); #it was just snaging metadata files

@ALL = sort(@COMBINE);
$count_all=0;
$count_with_checksums=0;
$count_with_badchecksums=0;
$i=0;
$sum_flag = "0";
foreach $line (@ALL)
{
   chomp($line);
   $file = "$base/$line";
   $count_all++;
   $sum_flag = "0";
   foreach $typeof (@set)
   {
     chomp($typeof);
     ($type,$cmd) = split(/:/,$typeof);
     #$checktype= "check_".$type;
     @CMD = `$cmd $file`;
     chomp(@CMD);
     @generated = split(/\s+/, $CMD[0]);
     chomp(@generated);
     $checkfile = $file."."."$type";
     $i++;
     if  (-e $checkfile)
     {
       $sum_flag = "1";
       $count_with_checksums++;
       @SUMFILE = `cat $checkfile`;
       chomp(@SUMFILE);
       @checksumfile = split(/\s+/,$SUMFILE[0]);
       chomp(@checksumfile);
       #if ($generated[0] eq $checksumfile[0])
       $reconstruct = join " ", @checksumfile;
         @repository =  split("/", $line);
         $repository[1] =~ s/\s|\-/_/g;
       if (!($reconstruct =~ $generated[0]))
       {
         $count_with_badchecksums++;
         @repository =  split("/", $line);
         $repo = $repository[1];
         $repo =~ s/\s|\-/_/g;
         $checktype = $repo."_".$type;
         if (defined( ${$repo}))  { ${$repo}++; ${$checktype}++; print "\n1. $checktype: ",${$checktype}; } 
               else {  ${$repo}=1; ${$checktype}=1; #print attached "\n$repo\n"
             }
         my $timestamp = ctime(stat($file)->mtime);
         $sumfile_one_row = join " ", @SUMFILE;
         $sumfile_short=  substr $sumfile_one_row, 0, 50; 
         print attached "$line,$generated[0],$sumfile_short,$timestamp\n";
        }
    }
 }

   if ($sum_flag eq "0" )
   {
         my $timestamp = ctime(stat($file)->mtime);
         #print attached "$i $line $generated[0] -> no_mdf_no_sha1: $timestamp\n";
         print attached "$line, $generated[0],  no_mdf_no_sha1, $timestamp\n";
         print no_checksums "$line, $generated[0],  no_mdf_no_sha1, $timestamp\n";
  }
}

foreach $rep (@REPO)
{
   $rep =~ s/\s|\-/_/g;
   $r5 = $rep."_md5";
   $r1 = $rep."_sha1";
   print summary "$rep, ${$rep}, ${$r5}, ${$r1}\n";
}

print total "all files examined, $count_all\n";
print total "number of checksum files examined, $count_with_checksums\n";
print total "files with bad checksums: $count_with_badchecksums\n";
