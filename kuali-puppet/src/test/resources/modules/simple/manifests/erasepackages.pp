package { 
  "man": 
    ensure => absent;
  "zip": 
    ensure => absent;
  "unzip": 
    ensure => absent;
  "wget": 
    ensure => absent;
  "rsync": 
    ensure => absent;
  "openssh-clients": 
    ensure => absent;
  "rubygems": 
    ensure => absent;
  "subversion": 
    ensure => absent;
  "git": 
    ensure => absent,
    before => Package["rsync","openssh-clients"];
}
