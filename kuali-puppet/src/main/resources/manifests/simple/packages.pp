package { 
  "man": 
    ensure => present;
  "zip": 
    ensure => present;
  "unzip": 
    ensure => present;
  "java-1.7.0-openjdk": 
    ensure => present,
    before => Package["java-1.6.0-openjdk"];
  "java-1.6.0-openjdk": 
    ensure => present;
}
