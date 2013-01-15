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
# Leave svn alone so we don't have to keep re-installing    
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
  ensure => absent,
}

package { 'rubygems': 
  ensure => absent,
  require => Package['fog', 'ruby-nokogiri', 'ruby-devel'], 
}

#    
# rsync and openssh-clients are both required by git
#
#  'rsync': 
#    ensure => absent;
#  'openssh-clients': 
#    ensure => absent;
#
# The Amazon git rpm's don't play well with puppet
# Attempting to uninstall git results in an error related
# to not being able to uninstall it since perl-Git requires
# it.  But you can't uninstall perl-Git first, because it
# depends on git.  Seems like a catch-22.
#
#  'git': 
#    ensure => absent,
#    before => Package['rsync','openssh-clients'];
#  'perl-Git': 
#    ensure => absent,
#    before => Package['git'];
