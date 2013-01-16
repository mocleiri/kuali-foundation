class maven {

  class { 'maven::s3artifact':
    group_id => "com.oracle",
    artifact_id => "ojdbc14",
    version => "11.0.2.0",
  }
  

}

class {'maven': }
