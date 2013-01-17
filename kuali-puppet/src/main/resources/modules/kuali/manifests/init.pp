class kuali {

  notify {"Hello Kuali World":}
  
  class { 'jdk':
    level   => '6',
    version => 'foo',
  }

  class { 'jdk':
    level   => '7',
    version => 'foo',
  }

}

class {'kuali':}