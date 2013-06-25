#!/usr/bin/perl

$HOME=$ENV{'HOME'};

sub build_ec2_lst
{
 $cmd = "ec2-describe-instances";
 $cmd_lb = "elb-describe-lbs";
 $cmd_lb_health = "elb-describe-instance-health";
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
  $lbpjfile = "lb.$pj.lst";
  system("rm ./$lbpjfile;touch ./$lbpjfile");
  $key = `ls $pk_key`;
  $cert = `ls $cert_key`;
  chomp($key);
  chomp($cert);

  $command_tag = "$cmd -K $key -C $cert | grep \"TAG\" >> tag.lst";
  $command_instance = "$cmd -K $key -C $cert | grep \"INSTANCE\" >> instance.lst";
  $command_load_balancer = "$cmd_lb -K $key -C $cert >> $lbpjfile";
  #print "\n", $command_load_balancer;
  `$command_instance`;
  `$command_tag`;
  `$command_load_balancer`;
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
  my @tagsx = ();
  $tags = "";
  #there could be more than one tag. So lets combine them with ":" 
  foreach $tagline ( @TAGS)
  { 
    chomp($tagline); @tagname = split(/\t/, $tagline ); 
    push( @tagsx, $tagname[4]); 
  }

  $tags = join ( ":", @tagsx);
  #done with tag for this instance
  
   
  #if ( $url eq "" ){ $url = "n/a"; }

  print EC2LST  $instance_id,"," ,$url,",", $status,",", $tags,"\n";
  $instance_id = "";
  $url = "";
  $status = "";
 $tags = "";
 }

 foreach  $pj (@projects)
 { 
  $pk_key   =  "$HOME/.ssh/$pj-pk*";
  $cert_key = "$HOME/.ssh/$pj-cert*";
  $lbpjfile = "lb.$pj.lst";
  $key = `ls $pk_key`;
  $cert = `ls $cert_key`;
  chomp($key);
  chomp($cert);
  @LB = ();
  my @LB = split(/\n/,`grep LOAD_BALANCER $lbpjfile`);
  $url = $lbout[3];
  #Let organize the load balancer (lb) info
  #LB has been populated with each domains lb info
  foreach $lbline ( @LB)
  { chomp($lbline); @lbname = split(/\t|\s+/, $lbline );
    print "\nworking with lbline: $lbline";
    $lb_id = $lbname[1];
    #print "\nlb_id:$lb_id";
    $lb_xref = $lbname[2];
    #print "\nlb_xref: $lb_xref";
    #print "\n$cmd_lb_health $lb_id -K $key -C $cert";
    ($toss, $lb_instance_id, $service_status) = split(/\t|\s+/,`$cmd_lb_health $lb_id -K $key -C $cert`);
    #print "\ngrep $lb_instance_id instance.lst";
    $lb_result = `grep $lb_instance_id instance.lst`;
    chomp( $lb_result );
    @lbout = split(/\t/, $lb_result);
    #$instance_id = $lbout[1];
    $instance_id = $lb_id;
    $url = $lbout[3];
    $status = $service_status;
    $tags_lb = $lb_xref;

    #print "\n",$instance_id," ",$url," ", $status," ", $tags;    
    print EC2LST  $instance_id," ",$url," ", $status," ", $tags_lb,"\n";
    $instance_id = "";
    $url = "";
    $status = "";
    $tags_lb = "";
  }

  #print "\nend of creating EC2 List"; 
 }
 close(EC2LST);
}

################################################
sub project_env_status 
################################################
{

 my $env_no = "";
 my $anyname_kuali_org = "";
 my $server =  "";
 my $ec2_status = "";
 my $status = "";
 my $size = "";
 my $tags = "";
 $instance_id = "";
 $server = "";
 $ec2_status = "";
 $tags = "";
 $url = "";

 my $project = $_[0];
 my $skip_ec2_list = $_[1];
 chomp ($project);
 chomp ($skip_ec2_list);
 #lets setup the individual project comma delimited files
 $projectfile = "dns_"."$project".".csv";
 `rm $projectfile`;
 #add the headers, primative, but effective
 `echo \"Index Project Svr,DNS Name,EC2 Name,uptime or status, .. , no users,avg load for 1 , for 5 min,15 min,disk,size,GB,used,%\" > $projectfile`;

 #Ok, let put the files in append mode
  open WIKI,  ">>$projectfile" or die "$projectfile $!\n";

 #using ec2-describe-instances, let build a file with all info about fn, rice and student.  I don't have key for ole.
 if (( $project ne "ole" )  && ($skip_ec2_list eq "" ))
 { build_ec2_lst(); }

 #ok, now we'll do some dnsme and find out what's in our dns tables.
 $pwd = `pwd`;
 chomp($pwd); 
 $sourcefile = "dns.$project".".txt";
 if ( $project eq "fn" )  #any line with greater than symbol is data
 { $cmd = "cd ../../../..;mvn dnsme:showrecords | grep \">\" | grep -v ole | grep -v ks | grep -v rice  > $pwd/$sourcefile"; }
 else
 { $cmd = "cd ../../../..; mvn dnsme:showrecords -Ddnsme.recordNameContains=$project > $pwd/$sourcefile"; }

 #print "\n", $cmd;
 `$cmd`;
 #so lets go through this table
 open( dns,  "<$sourcefile"); (@DNS =<dns>); close (dns);
 foreach $line (@DNS)
 {
   chomp($line);
   print "\nworking on $line";
   if ( $line !~ /\->/ ){ next; }
   #some dns entries are not being interrogated at this time. So for each entry
   #default to no ping, and then see if there are any indicators to skip the ping
   $no_ping = "";

   #weed out the known situation that will not need query
   if (( $line =~ "cloudfront") ){ $no_ping = "cloudfront-no check";  }
   if (( $line =~ "rds") ){ $no_ping = "RDS-no check"; }
   if (( lc($line) =~ "s3") ){ $no_ping = "S3 Data Archiver no check"; }
   if (( lc($line) =~ "ghs") ){ $no_ping = "Google Hosted Services(ghs) no check"; }
   if (( $line =~ "env7") && ($project eq "ole")){ $no_ping = "time-out"; }
   if (( $line =~ "env2") && ($project eq "ole")){ $no_ping = "time-out"; }

   #so I have information, lets parse and clean it up
   ($toss,$url,$ec2,$CNAME,$ttl) = split(/\s|\->|,/,$line);
   #filter out any url that does not END with the project name, fn is exempt 
   #print "\n$no_ping";
   #print "\n$url !~ $project)";
   if (( $url !~ /$project$/) && ($project ne "fn"))
   { print "\n$url !~ $project)"; next; }

   #print "\n(toss:$toss,url:$url,ec2:$ec2,cname:$CNAME,ttl:$ttl)";
   $anyname_kuali_org = $url.".kuali.org";
   if ( $anyname_kuali_org eq "www.kuali.org" ) # the server with a name of www needs special attention 
    { $ec2 = "ec2-174-129-193-43.compute-1.amazonaws.com." } #let's hard code it for now.


   #get rid of that dot at the end of amazon.com name
   @temp = split(//,$ec2);
   pop(@temp); #there's a period there pop it off
   $ec2 = join "", @temp; #put it back together


   #if the amazon.com name match in the EC2 List generated at the start of this script for each project, lets use that info 
   #query ec2 list for the amazon DNS name 
   #print "\ngrep $ec2 EC2.lst";
   $dns_ec2_grep_ec2livelist = `grep $ec2 EC2.lst`;


   #query ec2 list for the tag name 
   #this is a little more complicated, as the tag/name info is not consistent
   if ( $urlsize < 3 ){ $results_ec2tag = ""; } #if the name is too small it tends to match everything.  Skip that value.
   @tagoutput = ();
   (@tagoutput) = find_url_tagname($url, "EC2.lst");
   $results_ec2tag = $tagoutput[0];
   chomp($results_ec2tag);

   if ( $results_ec2tag ne "" ){ print "\nresult_ec2tag: $results_ec2tag = grep $url EC2.lst";}
   if ( $no_ping ne "" )
    { 
      chomp($dns_ec2_grep_ec2livelist);
      ($instance_id, $server, $ec2_status, $tags) = split (/\s+|,/, $dns_ec2_grep_ec2livelist);
      print WIKI ",$anyname_kuali_org,$ec2, $no_ping,$tags\n";
      $anyname_kuali_org = "";
      $ec2= "";
      $no_ping = "";
      $tags = "";
      next; }


   #otherwise $no_ping is not set and will go on
   #if ( $status eq ""){$status = "$anyname_kuali_org page not found";}  
   #if the ec2 query has returned outcome lets get that info first
   if ( $dns_ec2_grep_ec2livelist ne "" )
   {   
      ($instance_id, $server, $ec2_status, $tags) = split (/\s|,/, $dns_ec2_grep_ec2livelist);
       #This is code to looked for server of particular load balancer  configurations.
       #I care about Nexus.  So
       if ($url eq "nexus" )
       #if ($url =~ "nexus" )
       {   #I have this line in dns_ec2_grep_ec2livelist:
            #kuali-nexus-lb ec2-50-19-21-45.compute-1.amazonaws.com InService kuali-nexus-lb-287160402.us-east-1.elb.amazonaws.com
            #Lets save some info 
            $lb_id = $instance_id;
            $ec2_name = $server;
            $lbstatus = $ec2_status;
            $lbcom = $tags;
            $dns_ec2_grep_ec2livelist = `grep $ec2_name EC2.lst | grep -v $lbstatus`;  #I don't want the same line
            #now I should have this: i-bdf85cc0 ec2-50-19-21-45.compute-1.amazonaws.com running kuali-nexus-as:nexus
            ($instance_id, $server, $ec2_status, $tags) = split (/\s|,/, $dns_ec2_grep_ec2livelist);  ## what is this for?
             print WIKI ",$lb_id, $ec2_name, ,$lbstatus,$lbcom\n";
             $lb_id = "";
             $ec2_name = "";
             $lbstatus = "";
             $lbcom = "";
            #and I'll still lookup the instance_id and report on it, moving on
       }
            
      #if this is a lb: lbname, server, Service, lb ec2.com name
   }
   elsif ( $results_ec2tag ne "" ) #let's use the tag query, as ec2com didn't find anything
   {   
       $size = "";
       ($instance_id, $server, $ec2_status, $tags) = split (/\s+|,/, $results_ec2tag);
   }
   else
    {
       $server = $ec2;
       if ( $status eq "" ){ $status = "page not found"};
       $size = "";
       #$tag = "";
    }

   #only ping if the server is running, or its ole.  I don't have passkeys to access ole with command line tools
   $user = "root"; #default
   @results_ec2tag = ();
   @results_ping = ();
   if (( $ec2_status eq "running") || ( $project eq "ole" ))
      {  
        if ((( $line =~ "env2") && ($project eq "ole")) || (($project eq "fn") && ($url eq "nexus")) )
         { @results_ping = dead_or_alive($server,$user);  }
        else
        { 
          if (( $anyname_kuali_org eq "www.kuali.org") || ( $anyname_kuali_org eq "dev-cas.kuali.org") || ( $anyname_kuali_org eq "test-www.kuali.org")){ $user = "ubuntu"; }
          if (( $anyname_kuali_org eq "env16.rice.kuali.org") || ( $anyname_kuali_org eq "env29.rice.kuali.org") ||( $anyname_kuali_org eq "env67.rice.kuali.org")){ $user = "ubuntu"; }
          @results_ping = dead_or_alive($anyname_kuali_org,$user); 
       } 
      print "\n",  @results_ping," dead_or_alive($anyname_kuali_org,$user)"; 
      chomp(@results_ping);
      $status = $results_ping[0];
      $size = $results_ping[1];
      }
    
    #let look up the index, probably write a routine for this
    $domainservers = "./domainsvr_lookup.txt";

   print "\n===>grep $anyname_kuali_org $domainservers\n";
   ($env_no, $env_name, $projectx) = split(/,/,`grep $anyname_kuali_org $domainservers`);
   #I only are about env_no for this effort
   #ws.rice.kuali.org, ec2-204-236-253-122.compute-1.amazonaws.com,  22:46:51 up 56 days, 11:29,  0 users,  load average: 0.00, 0.01, 0.05, /dev/xvde1,252G,183G,57G,77%,/,,ci.ws.server
   #,env16.rice.kuali.org, ec2-75-101-184-187.compute-1.amazonaws.com, Fail 256, ,ci.ws.server

   #if this is a load balancer entry 
   if (( $dns_ec2_grep_ec2livelist  =~ ".elb." ) && ($instance_id ne "nexus"))
   { print WIKI ",$instance_id, $url, ,$ec2_status,$tags\n";}
   else
   {
   #take the info and print it to the project file
   print WIKI "$env_no,$anyname_kuali_org, $server, $status, $size,$tags\n"; }
   $env_no = "";
   $anyname_kuali_org = "";
   $server = "";
   $status = "";
   $size = "";
   $tags = "";
   }
  }

   sub find_url_tagname
   {
     my $url = $_[0];
     my $EC2file = $_[1];
     my @output = ();
     my @ProjectEC2 = ();
     #so let go through each line of the EC2.lst
     open ( EC2X,  "<$EC2file"); (@ProjectEC2 =<EC2X>); close (EC2X);
     foreach $line (@ProjectEC2)
     {
      chomp($line);
      #print "\nEC2: ",$line;
      #So here is an instance, or perhaps a load balancer.  It could have lots of info in the tag section.
      #split on words stopped or running
      my @RAWLINE = ();
      (@RAWLINE) = split(/running\,|stopped\,/, $line);
      #print "\n",@RAWLINE;
      #print "\nRAWLINE[0]: ",$RAWLINE[0];
      #print "\nRAWLINE[1]: ",$RAWLINE[1];
      #print "\nRAWLINE[2]: ",$RAWLINE[2];
      my @RAWTAG = split(/:/,$RAWLINE[1]);
      $rl = @RAWTAG;
      if ( $rl == 0 ){ next; }
      #print "\n ($RAWTAG[0] eq $url ) || ( $RAWTAG[1] eq $url )";
      if ( ($RAWTAG[0] eq $url ) || ( $RAWTAG[1] eq $url ))
      { $output[0] = $line; last; }
      }
      return( @output); 
   } # find_url_tagname

#Checks to see if a dns name/server combination results in a live or dead outcome 
sub dead_or_alive
{
 use warnings;
 my $name = $_[0];
 my $user = $_[1];
 my $output = "";
 my @out = ();
 my $value = "";
 my $size = "";
 print "\ndead_or_alive: $name, $user";

#set an Signal Alarm to timeout unresponsive ip addresses
eval {
    local $SIG{ALRM} = sub {die "alarm\n"};
    alarm 5;
    #I don't want to see the chatter, redirect 
    $value = `ssh $user\@$name uptime 2>chatter.txt`;
    alarm 0;
};

if ($?) {
    #print "testProgram failed $name\n";
    $out[0] =  "Fail ".$?;
    sleep (20);
} elsif ($@) {
    die "dieing, ",$@ unless $@ eq "alarm\n";
    #print "timed out $name \n";
    $out[0] = "Timedout ".$@;
    sleep (20);
} else {
    #print "Connection to $name was established\n";
    #Get size information
    $size = `ssh $user\@$name df \-h \/ | tail -1`;
    $size =~ s/\t|\s+/,/g;
    $out[0] =$value;
    $out[1] = $size;
}

    return(@out);
}


sub main
{
  $project=$ARGV[0];
  $skip_ec2_list = $ARGV[1];
  chomp($project);
  chomp($skip_ec2_list);
  if ( $project eq "" ){ print "\n\tPlease include a project: ole, rice, ks. Try again.\n\n"; exit;}
  project_env_status(  $project, $skip_ec2_list );
}

&main();
