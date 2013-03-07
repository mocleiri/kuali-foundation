#!/usr/bin/perl

$project=$ARGV[0];
chomp($project);
if ( $project eq "" ){ print "\n\tPlease include a project: ole, rice, ks. Try again.\n\n"; exit;}
$projectfile = "dns_"."$project".".csv";
`rm $projectfile`;
$sourcefile = "dns.$project".".txt";
$wiki = "/usr/local/tomcat/";
`echo \"DNS Name,EC2 Name\\n\" > $sourcefile`;
`mvn dnsme:showrecords -Ddnsme.recordNameContains=$project >> $sourcefile`;
open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
open WIKI,  ">>$projectfile" or die "$projectfile $!\n";
foreach $line (@DNS)
{
 chomp($line);
 if ($line =~ $project)
 {
   @parts = split(/\s|\->|,/,$line);
   $name = $parts[1].".kuali.org";
   @temp = split(//,$parts[2]);
   pop(@temp);
   $server = join "", @temp;
   print WIKI "\n$name, $server  ";
 }
#$i=0;
#foreach $item (@parts)
#{
#  print "\n";
#  print $i++;
#  print $item;
#}
}
print "\ncp $projectfile $wiki";
`cp $projectfile $wiki`;
