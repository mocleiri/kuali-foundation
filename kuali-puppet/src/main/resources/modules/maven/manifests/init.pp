class maven {
  class { 'maven::s3artifact' :
  }
}

class {'maven':}
