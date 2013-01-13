host {'self':
  ensure       => present,
  name         => $fqdn,
  host_aliases => ['puppet', $hostname],
  ip           => $ipaddress,
}
    
file {'/tmp/info.txt':
  ensure  => file,
  path    => '/tmp/info.txt',
  mode    => 0644,
  content => "AMI: $ec2_ami_id\nType:$ec2_instance_type\nPublic Hostname:$ec2_public_hostname",
}