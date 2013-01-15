package { 
  'man': 
    ensure => latest;
  'zip': 
    ensure => latest;
  'unzip': 
    ensure => latest;
  'wget': 
    ensure => latest;
  'rsync': 
    ensure => latest;
  'openssh-clients': 
    ensure => latest;
  'rubygems': 
    ensure => installed;
  'subversion': 
    ensure => installed;
  'git':
    ensure => installed;
  'fog': 
    ensure   => 'installed',
    provider => 'gem',
}
