#!/usr/bin/perl

$HOME=$ENV{'HOME'};

sub build_ec2_lst
{
 $cmd_instance = "ec2-describe-instances";
 $cmd_vol = "ec2-describe-volumes";
 $cmd_tag = "ec2-describe-tags";
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
  $key = `ls $pk_key`;
  $cert = `ls $cert_key`;
  chomp($key);
  chomp($cert);

  $command_tag = "$cmd_tag -K $key -C $cert >> tag.lst";
  $command_instance = "$cmd_instance -K $key -C $cert | grep INSTANCE >> instance.lst";
  $command_vol = "$cmd_vol -K $key -C $cert >> volume.lst";
  print "\n$command_vol";
  `$command_instance`;
  `$command_tag`;
  `$command_vol`;
 }
}


sub build_wiki_page
{

  $dnsfile = "dns.csv";
  `cat dns_fn.csv dns_ole.csv dns_rice.csv dns_ks.csv | grep -v elb > dns.csv`;
  $com_field = 3;
  $status_field = 5;
  $key_field = 6;
 system("rm ec2_status_info.cvs; touch ec2_status_info.cvs");
 open ( EC2,  "<instance.lst"); (@INSTANCE =<EC2>); close (EC2);
 open WIKI, ">>ec2_status_info.cvs" or die "ec2_status_info.cvs : $!\n" ;
 print WIKI "key|ec2-com|Name|kuali DNS Name| ec2-status| uptime | disk | size | avail | used| percent|\n";
 foreach $instance ( @INSTANCE)
 {
 @temp = split(/\t/, $instance);
 #This url is the public name, such as the amazon.com or cloud name
 $COM = $temp[$com_field];
 $instance_id = "";
 $Status = "terminated";
 $Key = "";
 $Name = "--";
 @TAG = ();
 @VOL_ATTACH = ();
 @VOL = ();
 $volume_id = '';
 $volume_size = ""; 
 @DNS = ();
 $uptime = ""; 
 $DiskUsage = "";
 if ($COM eq ""){next;}
 $instance_id = $temp[1];
 $Status = $temp[$status_field];
 $Key = $temp[$key_field];
 if ($Key eq "" ){$Key = "--";}
 @TAG = split(/\t+/,`grep $instance_id tag.lst | grep \"\tName\t\"`);
 chomp(@TAG);
 $Name = $TAG[4];
 if ($Name eq "" ){$Name = "--";}
 @VOL_ATTACH = split(/\t+/, `grep $instance_id volume.lst`);
 $volume_id = $VOL_ATTACH[1];
 if ( $volume_id ne "" ){
 @VOL = split(/\t+/, `grep $volume_id volume.lst|grep VOLUME`);
 $volume_size = $VOL[2];
 }
 @DNS_INFO = split(/,/,`grep $COM $dnsfile`);
 chomp(@DNS_INFO);
 $DNS = $DNS_INFO[1]; 
 if ($DNS eq "" ){$DNS = "--";}
 $uptime = $DNS_INFO[3]; 
 if ($uptime eq "" ){$uptime = "--";}
 $DiskUsage = $DNS_INFO[9]."|".$DNS_INFO[10]."|".$DNS_INFO[11]."|".$DNS_INFO[12]."|".$DNS_INFO[13]; 
 #print  "|$Key|$COM|$Name|$DNS|$Status|$uptime|$DiskUsage|\n";
 print  WIKI "|$Key|$COM|$Name|$DNS|$Status|$uptime|$DiskUsage|\n";
 }
}

&build_ec2_lst;  # builds 1 list with all content
&build_wiki_page;
