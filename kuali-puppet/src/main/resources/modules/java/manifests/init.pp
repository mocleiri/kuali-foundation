class java(
  $distribution = 'jdk',
  $version      = 'present'
) {

  anchor { 'java::begin': }
  anchor { 'java::end': }

  case $::osfamily {
    'Linux': {
      if $::hardwaremodel == 'x86_64' {
        class { 'java::package_redhat':
          version      => $version,
          distribution => $distribution,
          require      => Anchor['java::begin'],
          before       => Anchor['java::end'],
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
