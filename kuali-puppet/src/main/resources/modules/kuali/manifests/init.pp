class kuali {

  notify {"Hello Kuali World":}
  
  class {'java':
    level   => '6',
    version => 'foo',
  }

}

class {'kuali':}