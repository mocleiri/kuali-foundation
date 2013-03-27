#!/usr/bin/perl

$HOME=$ENV{'HOME'};

sub build_ec2_lst
{
 $cmd = "ec2-describe-instances";
 my @projects = ("fn","ks","kr");
 my $pj;  #project index
 #remove some temporary files
 system("rm ./instance.lst;touch ./instance.lst");
 system("rm ./tag.lst;touch ./tag.lst");

 foreach  $pj (@projects)
 { 
  #needed to run the ec2-describe-instance or ensure the environment variables are set
  $pk_key   =  "$HOME/.ssh/$pj-pk*";
  $cert_key = "$HOME/.ssh/$pj-cert*";
  $key = `ls $pk_key`;
  $cert = `ls $cert_key`;
  chomp($key);
  chomp($cert);

  $command_tag = "$cmd -K $key -C $cert | grep \"TAG\" >> tag.lst";
  $command_instance = "$cmd -K $key -C $cert | grep \"INSTANCE\" >> instance.lst";
  `$command_instance`;
  `$command_tag`;
 }

 #make a file with one line for each instance, including the tag name 
 open ( EC2,  "<instance.lst"); (@INSTANCE =<EC2>); close (EC2);
 `rm EC2.lst`;
 open EC2LST, ">>EC2.lst" or die "EC2.lst : $!\n" ;
 foreach $instance ( @INSTANCE)
 {
  my @temp = split(/\t/, $instance);
  #This url is the public name, such as the amazon.com or cloud name
  my $url = $temp[3];
  my $status = $temp[5];
  my $instance_id = $temp[1];
  my @TAGS = `grep $instance_id tag.lst`;
  my @tags = ();
  #there could be more than one tag. So lets combine them with ":" 
  foreach $tagline ( @TAGS)
  { chomp($tagline); @tagname = split(/\t/, $tagline ); 
    push( @tags, $tagname[4]); 
  }

  $tags = join ( ":", @tags);
  #done with tag for this instance
  
   
  if ( $url eq "" ){ $temp[3] = "n/a"; }
  
 #let's create a file combining the instance info and tag info.  
  print EC2LST  $instance_id," ",$url," ", $status," ", $tags,"\n";
 }
 close(EC2LST);
}

################################################
sub project_env_status 
################################################
{

 my $env_no = "";
 my $name_url = "";
 my $server =  "";
 my $status = "";
 my $size = "";
 my $tag = "";

 my $project = @_[0];
 my $skip_ec2_list = @_[1];
 chomp ($project);
 chomp ($skip_ec2_list);
 #lets setup the individual project comma delimited files
 $projectfile = "dns_"."$project".".csv";
 `rm $projectfile`;
 #add the headers, primative, but effective
 `echo \"Index Project Svr,DNS Name,EC2 Name,uptime or status, .. , no users,avg load for 1 , for 5 min,15 min,disk,size,GB,used,%\" > $projectfile`;

 #Ok, let put the file in append mode
  open WIKI,  ">>$projectfile" or die "$projectfile $!\n";

 #using ec2-describe-instances, let build a file with all info about fn, rice and student.  I don't have key for ole.
 if (( $project ne "ole" )  && ($skip_ec2_list eq "" ))
 { build_ec2_lst(); }

 #ok, now we'll do some dnsme and find out what's in our dns tables.
 $sourcefile = "dns.$project".".txt";
 
 if ( $project eq "fn" )
 { $cmd = "mvn dnsme:showrecords | grep \">\" | grep -v ole | grep -v ks | grep -v rice > $sourcefile"; }
 else
 { $cmd = "mvn dnsme:showrecords -Ddnsme.recordNameContains=$project > $sourcefile"; }

 #print "\n", $cmd;
 `$cmd`;
 open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
 foreach $line (@DNS)
 {
   chomp($line);
   if ( $line !~ /\->/ ){ next; }
   $no_ping = "";

   #weed out the known situation that will not need query
   if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }
   if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
   if (( lc($line) =~ "s3") ){ $no_ping = "S3 Data Archiver no check"; }
   if (( lc($line) =~ "ghs") ){ $no_ping = "Google Hosted Services(ghs) no check"; }
   #if (( $line =~ "env7") && ($project eq "ole")){ $no_ping = "time-out"; }
   if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
   if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }


   #so I have information, lets parse and clean it up
   ($toss,$url,$ec2,$CNAME,$ttl) = split(/\s|\->|,/,$line);
   print "\n(toss:$toss,url:$url,ec2:$ec2,cname:$CNAME,ttl:$ttl)";
   $name_url = $url.".kuali.org";

   if ( $no_ping ne "" )
    { print WIKI ",$name_url,$url , $no_ping\n"; next; }
   #get rid of that dot at the end of amazon.com name
   @temp = split(//,$ec2);

   pop(@temp); #there's a period there
   $ec2 = join "", @temp; #put it back together


   #if the amazon.com name match in the EC2 List, lets use that info 
   #query ec2 list for the amazon DNS name 
   $outcome_ec2com = `grep $ec2 EC2.lst`;
   chomp($outcome_ec2com);


   #query ec2 list for the tag name 
   #print "\ngrep $url EC2.lst";
   $results_ec2tag = `grep $url EC2.lst`; 
   chomp($results_ec2tag);
   
   #if the ec2 query has returned outcome lets get that info first
   if ( $outcome_ec2com ne "" )
   {   
      ($instance_id, $server, $status, $tags) = split (/\s/, $outcome_ec2com);
   }
   else #let's use the tag query
   { ($instance_id, $server, $status, $tags) = split (/\s/, $results_ec2tag);}

   #only ping if the server is running, or its ole.  I don't have passkeys to access ole with command line tools
   if (( $status eq "running") || ( $project eq "ole" ))
      {  
        if (( $line =~ "env2") && ($project eq "ole"))
         { @results_ec2tag = dead_or_alive($server);  }
        else
        { @results_ec2tag = dead_or_alive($name_url); }
      chomp(@results_ec2tag);
      $status = $results_ec2tag[0];
      $size = $results_ec2tag[1];
      }
    
    #let look up the index, probably write a routine for this
    $domainservers = "./domainsvr_lookup.txt";
    $env_no = "";
   #print "grep $name_url $domainservers\n";
   ($env_no, $env_name, $projectx) = split(/,/,`grep $name_url $domainservers`);
   #I only are about env_no for this effort

   #take the info and print it to the project file
   print WIKI "$env_no,$name_url, $server, $status, $size,$tag\n"; 
   }
  }

#Checks to see if a dns name/server combination results in a live or dead outcome 
sub dead_or_alive
{
 use warnings;
 my $name = $_[0];
 my $output = "";
 my @out = ();
 my $value = "";

#set an Signal Alarm to timeout unresponsive ip addresses
eval {
    local $SIG{ALRM} = sub {die "alarm\n"};
    alarm 5;
    #I don't want to see the chatter, redirect 
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
    #print "Connection to $name was established\n";
    #Get size information
    $size = `ssh root\@$name df \-h \/ | tail -1`;
    $size =~ s/\t|\s+/,/g;
    $out[0] =$value;
    $out[1] = $size;
}

    return(@out);
}


sub main
{
  $project=$ARGV[0];
  $skip_ec2_list = @_[1];
  chomp($project);
  chomp($skip_ec2_list);
  if ( $project eq "" ){ print "\n\tPlease include a project: ole, rice, ks. Try again.\n\n"; exit;}
  project_env_status(  $project, $skip_ec2_list );
}

&main();
