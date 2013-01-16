class mvn {

  class {'mvn::helloworld':}

#  class { 'mvn::s3artifact' :
#    bucket      => 'maven.kuali.org,
#    prefix      => 'private',
#    group_id    => 'com.oracle',
#    artifact_id => 'ojdbc14',
#    version     => '11.0.2.0',
#  }
}

class {'mvn':}
