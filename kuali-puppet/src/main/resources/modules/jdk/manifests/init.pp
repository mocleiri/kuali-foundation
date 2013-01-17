define jdk ($level, $version) {

  if ($level != '6' and $level != '7') {
    fail("jdk level ${level} is not supported. The only supported jdk levels are 6 and 7")
  }

  if ($::hardwaremodel != 'x86_64') {
    fail("Only 64 bit systesm are supported. hardwaremodel ${::hardwaremodel} is not supported")
  }

}
