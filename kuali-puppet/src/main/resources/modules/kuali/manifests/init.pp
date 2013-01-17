class kuali {

  notify {"Hello Kuali World":}
  
  class { 'jdk':
    level   => '8',
    version => 'foo',
  }

}

class {'kuali':}