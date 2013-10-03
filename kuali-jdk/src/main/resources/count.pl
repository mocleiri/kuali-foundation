#!/usr/bin/perl
use File::stat;
use POSIX qw/strftime/;
use Time::localtime;
$base="/usr/local/sonatype-work/nexus/storage";

$summary="/home/admin/summary.txt";
$attached="/home/admin/attached.txt";
$total ="/home/admin/total.txt";
$no_checksums ="/home/admin/no_checksums.txt";

`rm -f $summary`;
`rm -f $attached`;
`rm -f $total`;
`rm -f $no_checksums`;

open summary, ">>$summary" or die "Couldn't open '$summary': $!";
print summary "repository/artifact,total bad check sums, bad md5sum, bad sha1sums\n";
open attached, ">>$attached" or die "Couldn't open '$attached': $!";
print attached "repository/artifact,generated for compare ,checksum file contains ,timestamp\n";

open no_checksums, ">>$no_checksums" or die "Couldn't open '$no_checksums': $!";
print no_checksums "repository/artifact,generated for compare ,checksum file contains ,timestamp\n";

open total, ">>$total" or die "Couldn't open '$total': $!";
print total "Description,Total\n";

#jar
`cd $base;find  central maven-restlet sonatype-oss-releases saucelabs-repository jsdoctk kuali-s3-private kuali-builds kuali-s3-external kuali-legacy-releases kuali-legacy-snapshots glassfish ow2 spring-milestones atlassian google-reflections hosted-private codehaus eclipselink jboss kuali-private kuali-release kuali-snapshot codehaus-snapshots apache-snapshots java.net-m2 google jasperreports-sourceforge m2eclipse central-m1 java.net-m1-m2 thirdparty releases snapshots java.net-m1 -name "*.jar" > /home/admin/jar.txt`;

#war
`cd $base;find  central maven-restlet sonatype-oss-releases saucelabs-repository jsdoctk kuali-s3-private kuali-builds kuali-s3-external kuali-legacy-releases kuali-legacy-snapshots glassfish ow2 spring-milestones atlassian google-reflections hosted-private codehaus eclipselink jboss kuali-private kuali-release kuali-snapshot codehaus-snapshots apache-snapshots java.net-m2 google jasperreports-sourceforge m2eclipse central-m1 java.net-m1-m2 thirdparty releases snapshots java.net-m1 -name "*.war" > /home/admin/war.txt`;

#pom
`cd $base;find  central maven-restlet sonatype-oss-releases saucelabs-repository jsdoctk kuali-s3-private kuali-builds kuali-s3-external kuali-legacy-releases kuali-legacy-snapshots glassfish ow2 spring-milestones atlassian google-reflections hosted-private codehaus eclipselink jboss kuali-private kuali-release kuali-snapshot codehaus-snapshots apache-snapshots java.net-m2 google jasperreports-sourceforge m2eclipse central-m1 java.net-m1-m2 thirdparty releases snapshots java.net-m1 -name "*.pom" > /home/admin/pom.txt`;

#zip
`cd $base;find  central maven-restlet sonatype-oss-releases saucelabs-repository jsdoctk kuali-s3-private kuali-builds kuali-s3-external kuali-legacy-releases kuali-legacy-snapshots glassfish ow2 spring-milestones atlassian google-reflections hosted-private codehaus eclipselink jboss kuali-private kuali-release kuali-snapshot codehaus-snapshots apache-snapshots java.net-m2 google jasperreports-sourceforge m2eclipse central-m1 java.net-m1-m2 thirdparty releases snapshots java.net-m1 -name "*.zip" > /home/admin/zip.txt`;

@REPO= qw(central maven-restlet sonatype-oss-releases saucelabs-repository jsdoctk kuali-s3-private kuali-builds kuali-s3-external kuali-legacy-releases kuali-legacy-snapshots glassfish ow2 spring-milestones atlassian google-reflections hosted-private codehaus eclipselink jboss kuali-private kuali-release kuali-snapshot codehaus-snapshots apache-snapshots java.net-m2 google jasperreports-sourceforge m2eclipse central-m1 java.net-m1-m2 thirdparty releases snapshots java.net-m1);

@set = ("sha1:sha1sum" ,"md5:md5sum");

open (J ,"</home/admin/jar.txt"); (@JAR =<J>); close (J); 
open (W ,"</home/admin/war.txt"); (@WAR =<W>); close (W); 
open (P ,"</home/admin/pom.txt"); (@POM =<P>); close (P); 
open (Z ,"</home/admin/zip.txt"); (@ZIP =<Z>); close (Z); 
#open (X ,"</home/admin/xml.txt"); (@XML =<X>); close (X); 

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
       if (!($reconstruct =~ $generated[0]))
       {
         $count_with_badchecksums++;
         @repository =  split("/", $line);
         $repository[0] =~ s/\s|\-/_/g;
         $checktype = $repository[0]."_".$type;
         print "\nchecktype: $checktype";
         if (defined( ${$repository[0]}))  { ${$repository[0]}++; ${$checktype}++; print "\n$checktype: ",${$checktype}; } 
               else {  ${$repository[0]}=1; ${$checktype}=1; #print attached "\n$repository[0]\n"
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
