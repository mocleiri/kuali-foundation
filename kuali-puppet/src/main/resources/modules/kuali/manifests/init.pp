class kuali {

  notify {"Hello Kuali World":}
  
  $jdk6version = "1.6.0-u38"
  $jdk7version = "1.7.0-u11"
  
  jdk { 'jdk6':
    level   => '6',
    version => $jdk6version,
  }

  jdk { 'jdk7':
    level   => '7',
    version => $jdk7version,
  }

}

class {'kuali':}