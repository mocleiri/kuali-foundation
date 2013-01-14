$jdkpackage = "jdk"
$installdir = "/usr/java"
$jdk6version = "1.6.0_38"
$jdk7version = "1.7.0_11"
$versionsuffix = "fcs"

$version = "${jdk6version}-${versionsuffix}"
$removedir = "${installdir}/${jdkpackage}-${jdk7version}"

package { $jdkpackage:
  ensure => $version,
}

file { $removedir:
  ensure => absent,
  after => Package[$jdkpackage],
}