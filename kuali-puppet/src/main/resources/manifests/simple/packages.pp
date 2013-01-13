package { 
  "curl": 
    ensure => present;
  "man": 
    ensure => present;
  "zip": 
    ensure => present;
  "wget": 
    ensure => present;
  "unzip": 
    ensure => present;
  "rsync": 
    ensure => present;
  "java-1.7.0-openjdk": 
    ensure => present;
}


 Of course './jdk-6u21-linux-x64-rpm.bin -x ' will extract the RPM ... 