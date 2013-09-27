#!/usr/bin/perl
use File::stat;
use POSIX qw/strftime/;
use Time::localtime;
$base="/usr/local/sonatype-work/nexus/storage";

$summary="/home/admin/summary.txt";
$attached="/home/admin/attached.txt";

`rm -f $summary`;
`rm -f $attached`;

open summary, ">>$summary" or die "Couldn't open '$summary': $!";
open attached, ">>$attached" or die "Couldn't open '$attached': $!";

#`cd $base;find central hosted-private kuali-s3-external eclipselink maven-restlet atlassian saucelabs-repository jsdoctk java.net-m2 jboss google-reflections google codehaus spring-milestones glassfish ow2 m2eclipse central-m1 java.net-m1-m2 thirdparty java.net-m1 -name "*.jar" > /home/admin/jar.txt`;
#`cd $base;find central hosted-private kuali-s3-external eclipselink maven-restlet atlassian saucelabs-repository jsdoctk java.net-m2 jboss google-reflections google codehaus spring-milestones glassfish ow2 m2eclipse central-m1 java.net-m1-m2 thirdparty java.net-m1 -name "*.pom" > /home/admin/pom.txt`;
#`cd $base;find central hosted-private kuali-s3-external eclipselink maven-restlet atlassian saucelabs-repository jsdoctk java.net-m2 jboss google-reflections google codehaus spring-milestones glassfish ow2 m2eclipse central-m1 java.net-m1-m2 thirdparty java.net-m1 -name "*.xml" > /home/admin/xml.txt`;

@REPO=( "central","hosted-private", "kuali-s3-external", "eclipselink", "maven-restlet", "atlassian", "saucelabs-repository", "jsdoctk", "java.net-m2", "jboss", "google-reflections", "google", "codehaus", "spring-milestones", "glassfish", "ow2", "m2eclipse", "central-m1", "java.net-m1-m2", "thirdparty", "java.net-m1");

@set = ("sha1:sha1sum" ,"md5:md5sum");

open (J ,"</home/admin/jar.txt"); (@JAR =<J>); close (J); 
open (P ,"</home/admin/pom.txt"); (@POM =<P>); close (P); 
open (X ,"</home/admin/xml.txt"); (@XML =<X>); close (X); 

@COMBINE = @JAR;
push(@COMBINE,@POM);
#push(@COMBINE,@XML);

@ALL = sort(@COMBINE);
$count_all=0;
$count_with_checksums=0;
$count_with_badchecksums=0;
foreach $line (@ALL)
{
   chomp($line);
   $file = "$base/$line";
   $count_all++;
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
     if  (-e $checkfile)
     {
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
               else {  ${$repository[0]}=1; ${$checktype}=1; print attached "\n$repository[0]\n" }
         my $timestamp = ctime(stat($file)->mtime);
         print attached "$line $generated[0] -> ",@SUMFILE,": $timestamp\n";
        }
    }
 }
}

foreach $rep (@REPO)
{
   $rep =~ s/\s|\-/_/g;
   $r5 = $rep."_md5";
   $r1 = $rep."_sha1";
   print summary "$rep: ${$rep} md5:${$r5} sha1:${$r1}\n";
}
print summary "all files examined: $count_all\n";
print summary "files with checksum to examine: $count_with_checksums\n";
print summary "files with bad checksums: $count_with_badchecksums\n";
