package { 
  'man': 
    ensure => absent;
  'zip': 
    ensure => absent;
  'unzip': 
    ensure => absent;
  'wget': 
    ensure => absent;
  'subversion': 
    ensure => absent;
}

package { 'fog':
  ensure => absent,
  provider => 'gem',
  before => Package['ruby-devel']; 
}

package { 'ruby-devel': 
  ensure => absent,
  before => Package['rubygems']; 
}

package { 'ruby-nokogiri': 
  ensure => absent,
  before => Package['rubygems']; 
}

package { 'rubygems': 
  ensure => absent;
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
