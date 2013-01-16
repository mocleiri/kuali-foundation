package {
  'man':             ensure => absent;
  'zip':             ensure => absent;
  'unzip':           ensure => absent;
  'wget':            ensure => absent;
  'rsync':           ensure => absent;
  'openssh-clients': ensure => absent;
# 'subversion':      ensure => absent; # leave subversion alone so we don't have to keep re-installing it
  'git':             ensure => purged; # purged instead of absent because it lists rsync and openssh-clients as dependencies
}

package { 'fog':
  ensure => absent,
  provider => 'gem',
}

package { 'ruby-devel': 
  ensure => absent,
}

package { 'ruby-nokogiri': 
  ensure => purged,
}

package { 'rubygems': 
  ensure => purged,
  require => Package['fog'], 
}
