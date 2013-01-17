class kuali {

  notify {"Hello Kuali World":}
  
  jdk { 'jdk6':
    level   => '6',
    version => 'foo',
  }

  jdk { 'jdk7':
    level   => '7',
    version => 'foo',
  }

}

class {'kuali':}