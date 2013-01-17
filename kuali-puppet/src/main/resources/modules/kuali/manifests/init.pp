class kuali {

  notify {"Hello World":}
  
  class {'java':
    zip   => 'foo',
    level => '6',
  }

}

class {'kuali':}