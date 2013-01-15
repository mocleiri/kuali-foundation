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
  ensure => '1.8.11',
}

package { 'ruby-devel': 
  ensure => '1.8.7.371',
  require => Package['rubygems'],
}

package { 'ruby-nokogiri': 
  ensure => '1.5.2',
  require => Package['ruby-devel'],
}

package { 'fog': 
  ensure   => installed,
  provider => 'gem',
  require  => Package['ruby-nokogiri'],
}
