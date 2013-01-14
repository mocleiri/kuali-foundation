$jdkpackage = "jdk"
$installdir = "/usr/java"
$versionsuffix = "-fcs"
$jdk6version = "1.6.0_38"
$jdk7version = "1.7.0_11"

$version = "${jdk6version}-${versionsuffix}"
$removedir = "${installdir}/${jdkpackage}-${jdk7version}"

package { "jdk":
  ensure => $version;
}

file { $removedir
  ensure => absent;
}