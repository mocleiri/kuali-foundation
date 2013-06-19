class kuali::packages {

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

  package { 'ruby-devel': 
    ensure => '1.8.7.371-1.20.amzn1',
  }

  package { 'fog': 
    ensure   => '1.8.0',
    provider => 'gem',
  }
  
}
