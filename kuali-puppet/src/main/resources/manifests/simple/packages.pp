package { 
  "curl": 
    ensure => present;
  "man": 
    ensure => present;
  "zip": 
    ensure => present;
  "unzip": 
    ensure => present;
  "wget": 
    ensure => present;
  "rsync": 
    ensure => present;
  "openssh-clients": 
    ensure => present;
}
