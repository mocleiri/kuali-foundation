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

package { 'rubygem-nokogiri': 
  ensure => absent,
}

package { 'ruby-nokogiri': 
  ensure => absent,
  before => Package['rubygem-nokogiri'],
}

package { 'rubygems': 
  ensure => absent,
  require => Package['fog', 'ruby-nokogiri', 'ruby-devel'], 
}

#    
# rsync and openssh-clients are both required by git
#
#package { 'rsync': 
#  ensure => absent,
#}
#
#package { 'openssh-clients': 
#  ensure => absent,
#}
#
#package { 'perl-Git':
#  ensure => absent,
#  before => Package['rsync','openssh-clients'], 
#}
#
#package { 'git':
#  ensure => absent,
#  require => Package['perl-Git'], 
#}
#
# The Amazon rpm's (version 1.7.4.5-1.21.amzn1) for git and perl-Git are kinda jacked.
#
# They both list the other as a dependency.
#
# If you yum install perl-Git, both are installed
# If you yum install git both are installed
#
# Same thing with removing
# 
