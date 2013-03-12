#!/usr/bin/perl

sub dead_or_alive
{
use warnings;
my $name = $_[0];
my $default_value = $_[1];
my $output = "";
my @out = ();
my $value = "";

if ( $default_value ne ""){$out[0]=$name; $out[1]=$default_value; return(@out);}
eval {
    local $SIG{ALRM} = sub {die "alarm\n"};
    alarm 5;
    $value = `ssh root\@$name uptime 2>chatter.txt`;
    alarm 0;
};

if ($?) {
    #print "testProgram failed $name\n";
    $out[0] =  "Fail ".$?;
    sleep (20);
} elsif ($@) {
    die unless $@ eq "alarm\n";
    #print "timed out $name \n";
    $out[0] = "Timedout ".$@;
    sleep (20);
} else {
    #print "didn't time out $name\n";
    $size = `ssh root\@$name df \-h \/ | tail -1`;
    $size =~ s/\t|\s+/,/g;
    $out[0] =$value;
    $out[1] = $size;
}

    return(@out);
}


sub main {
$project=$ARGV[0];
chomp($project);
if ( $project eq "" ){ print "\n\tPlease include a project: ole, rice, ks. Try again.\n\n"; exit;}
$projectfile = "dns_"."$project".".csv";
`rm $projectfile`;
$sourcefile = "dns.$project".".txt";
$wiki = "root\@ci.rice.kuali.org:/usr/local/tomcat/";
`echo \"DNS Name,EC2 Name,uptime or status, .. , no users,avg load for 1 , for 5 min,15 min,disk,size,GB,used,%\" > $projectfile`;
`mvn dnsme:showrecords -Ddnsme.recordNameContains=$project > $sourcefile`;
open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
open WIKI,  ">>$projectfile" or die "$projectfile $!\n";
$i =0;
foreach $line (@DNS)
{
 chomp($line);
 print "\n",$line;
 $no_ping = "";
 if (( $line =~ "env2") && ($project eq "ole")){ next; }
 if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
 if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }
 if ($line =~ $project)
 {
   @parts = split(/\s|\->|,/,$line);
   $name = $parts[1].".kuali.org";
   if ($name eq ""){next;}
   @temp = split(//,$parts[2]);
   pop(@temp);
   $server = join "", @temp;
   @result = dead_or_alive($name,$no_ping);
   chomp(@result);
   $status = $result[0];
   $size = $result[1];
   $i++;
   print WIKI "$name, $server, $status, $size\n";
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
`scp $projectfile $wiki`;
exit;
} #main

&main();
sleep 60; #let the pings timeout
