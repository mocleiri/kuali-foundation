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
  'subversion': 
    ensure => installed;
  'git':
    ensure => installed;
  'rubygems': 
    ensure => installed;
  'fog': 
    ensure   => 'installed',
    provider => 'gem',
    requires => 'rubygems',
}
