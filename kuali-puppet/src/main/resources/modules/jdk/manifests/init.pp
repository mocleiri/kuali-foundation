class jdk ($level
  , $version
) {

  if ($level != '6' and $level != '7') {
    fail("jdk level ${level} is not supported. The only supported jdk levels are 6 and 7")
  }

  case $::osfamily {
    'Linux': {
      if $::hardwaremodel == 'x86_64' {
        class { 'jdk::package_64bit':
          level   => $level,
          version => $version,
        }
      } else {
        fail("osfamily ${::osfamily} with hardwaremodel ${::hardwaremodel} is not supported")
      }
    }
    default: {
      fail("osfamily ${::osfamily} is not supported")
    }
  }
}
