class kuali {

  notify {"Hello Kuali World":}
  
  class { 'jdk':
    level   => '6',
    version => 'foo',
  }

}

class {'kuali':}