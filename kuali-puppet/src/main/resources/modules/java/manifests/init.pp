class java ($zip
  , $level
) {

  case $::osfamily {
    'Linux': {
      if $::hardwaremodel == 'x86_64' {
        class { 'java::package_64bit':
          zip   => $zip,
          level => $level,
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
