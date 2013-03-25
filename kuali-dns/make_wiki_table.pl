#!/usr/bin/perl

sub build_fn_lst
{
$cmd = "ec2-describe-instances";
$key = `ls ~/.ssh/fn-pk*`;
$cert = `ls ~/.ssh/fn-cert*`;
chomp($key);
chomp($cert);
#print "\nkey:$key.";
#print "cert:$cert.";
$command_instance = "$cmd -K $key -C $cert | grep \"INSTANCE\" > instance.lst";
$command_tag = "$cmd -K $key -C $cert | grep \"TAG\" > tag.lst";
`$command_instance`;
`$command_tag`;
open ( FN,  "<instance.lst"); (@INSTANCE =<FN>); close (FN);
`rm FN.lst`;
open FNLST, ">>FN.lst" or die "FN.lst : $!\n" ;
foreach $instance ( @INSTANCE)
{
  my @temp = split(/\t/, $instance);
  my $url = $temp[3];
  my $status = $temp[5];
  my $instance_id = $temp[1];
  my @TAGS = `grep $instance_id tag.lst`;
  my @tags = ();
  foreach $tagline ( @TAGS)
  { chomp($tagline); @tagname = split(/\t/, $tagline ); 
    push( @tags, $tagname[4]); }
  $tags = join ( ":", @tags);
  if ( $url eq "" ){ $temp[3] = "n/a"; }
  print FNLST  $instance_id," ",$url," ", $status," ", $tags,"\n";

#  foreach $element (@temp) {
#   print "\n",$i," $element\n";
#    $i++; }
}
}

sub foundation_env_status
{
 $project = @_[0];

 $projectfile = "dns_"."$project".".csv";
 `rm $projectfile`;
 `echo \"DNS Name,EC2 Name,uptime or status, .. , no users,avg load for 1 , for 5 min,15 min,disk,size,GB,used,%\" > $projectfile`;
  open WIKI,  ">>$projectfile" or die "$projectfile $!\n";
  build_fn_lst();
 $sourcefile = "dns.$project".".txt";
 $cmd = "mvn dnsme:showrecords | grep \">\" | grep -v ole | grep -v ks | grep -v rice > $sourcefile";
 `$cmd`;
 open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
 foreach $line (@DNS)
 {
   chomp($line);
  $no_ping = "";
   if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }
   if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
   #print "\n",$line;
   @parts = split(/\s|\->|,/,$line);
    ($toss,$url,$ec2,$CNAME,$ttl) = split(/\s|\->|,/,$line);
   print "\n(toss:$toss,url:$url,ec2:$ec2,cname:$CNAME,ttl:$ttl)";
   #$name = $parts[1].".kuali.org";
   $name = $url.".kuali.org";
   if ($name eq ""){next;}
   #print "\nname: $name";
   #@temp = split(//,$parts[2]);
   @temp = split(//,$ec2);
   pop(@temp); #there's a period there
   $ec2 = join "", @temp;
   #print "\ngrep $name FN.lst";
   #print "\ngrep $ec2 FN.lst" ;
   $result = `grep $name FN.lst`;
   $result_ec2 = `grep $ec2 FN.lst`;
    
   chomp($result);
  # print "\nresult 0:", $result[0];
  # print " result 1:", $result[1];
   chomp($result_ec2);
   if ( $result_ec2 ne "" )
   {   
      ($instance_id, $server, $status, $tags) = split (/\s/, $result_ec2);
       print WIKI "$name, $server, $status,$tags\n";
   }
   else{
     if ( $result ne "" )
     {   
        ($instance_id, $server, $status, $tags) = split (/\s/, $result);
     
        print WIKI "$name, $server, $status,$tags\n";
     }
    if ( $no_ping ne "" )
    { print WIKI "$name,$url , $no_ping\n"; }
  }
  }
}

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


sub project_env_status {
 my $project = @_[0];
 $projectfile = "dns_"."$project".".csv";
 `rm $projectfile`;
 $sourcefile = "dns.$project".".txt";
 $cmd = "mvn dnsme:showrecords -Ddnsme.recordNameContains=$project > $sourcefile";
 `echo \"DNS Name,EC2 Name,uptime or status, .. , no users,avg load for 1 , for 5 min,15 min,disk,size,GB,used,%\" > $projectfile`;
 `$cmd`;
 open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
 open WIKI,  ">>$projectfile" or die "$projectfile $!\n";
 $i =0;
 foreach $line (@DNS) {
 chomp($line);
 print "\n",$line;
 $no_ping = "";
 if (( $line =~ "env2") && ($project eq "ole")){ $no_ping = "time-out"; }
 if (( $line =~ "env7") && ($project eq "ole")){ $no_ping = "time-out"; }
 if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
 if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }
 if (($line =~ $project) ) {
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
} #project_env_status


sub get_status
{
my $project = @_[0];
if (( $project eq "rice") || ( $project eq "ks") ||( $project eq "ole") ){ project_env_status($project)};
if ( $project eq "fn" ){ foundation_env_status($project) ;}
#sleep 60; #let the pings timeout
}

sub main
{
  $project=$ARGV[0];
  chomp($project);
  if ( $project eq "" ){ print "\n\tPlease include a project: ole, rice, ks. Try again.\n\n"; exit;}
  get_status( $project);
}

&main();
