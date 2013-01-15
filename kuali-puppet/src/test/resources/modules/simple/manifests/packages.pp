package { 'all-packages': 
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
  'ruby-devel': 
    ensure => installed,
    require => Package['rubygems'];
}

package { 'fog': 
    ensure   => 'installed',
    provider => 'gem',
    require => Package['rubygems','ruby-devel'];
}