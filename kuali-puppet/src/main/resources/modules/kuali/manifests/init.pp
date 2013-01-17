class kuali {

  notify {"Hello World":}
  
  class {'java':
    zip  => 'foo',
    name => 'bar',
  }

}

class {'kuali':}