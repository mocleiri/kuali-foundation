package { 'man': 
  ensure => absent,
}
package { 'zip': 
  ensure => absent,
}
package { 'unzip': 
  ensure => absent,
}
package { 'wget': 
  ensure => absent,
}

# Leave svn alone so we don't have to keep re-installing it
#package { 'subversion': 
#  ensure => absent,
#}

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

package { 'git':
  ensure => purged,
}
