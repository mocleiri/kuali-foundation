define jdk ($level, $version) {

  if ($level != '6' and $level != '7') {
    fail("jdk level ${level} is not supported. The only supported jdk levels are 6 and 7")
  }

  if ($::hardwaremodel != 'x86_64') {
    fail("Only x86_64 systems are supported. hardwaremodel ${::hardwaremodel} is not supported")
  }

  if ($::osfamily != 'Linux') {
    fail("Only Linux systems are supported. osfamily ${::osfamily} is not supported")
  }

}
