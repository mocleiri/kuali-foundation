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
}

package { 'rubygems':
  version => '1.8.11',
  ensure => installed,
}

package { 'ruby-devel': 
  version => '1.8.7.371',
  ensure  => installed,
  require => Package['rubygems'],
}

package { 'ruby-nokogiri': 
  version => '1.5.2',
  ensure  => installed,
  require => Package['ruby-devel'],
}

package { 'fog': 
  ensure   => installed,
  provider => 'gem',
  require  => Package['ruby-nokogiri'],
}
