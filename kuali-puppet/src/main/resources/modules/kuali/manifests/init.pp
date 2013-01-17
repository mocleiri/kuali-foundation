class kuali {

  notify {"Hello Kuali World":}
  
  class {'java':
    zip   => 'foo',
    level => '6',
  }

}

class {'kuali':}