package {
  'man':             ensure => latest;
  'zip':             ensure => latest;
  'unzip':           ensure => latest;
  'wget':            ensure => latest;
  'rsync':           ensure => latest;
  'openssh-clients': ensure => latest;
  'subversion':      ensure => latest;
  'git':             ensure => latest;
}

package { 'rubygems':
  ensure => '1.8.11-3.1.amzn1',
}

package { 'ruby-nokogiri': 
  ensure  => '1.5.2-1.6.amzn1',
  require => Package['rubygems'],
}

package { 'ruby-devel': 
  ensure => '1.8.7.371-1.20.amzn1',
}

package { 'fog': 
  ensure   => '1.8.0',
  provider => 'gem',
  require  => Package['rubygems','ruby-nokogiri','ruby-devel'],
}
