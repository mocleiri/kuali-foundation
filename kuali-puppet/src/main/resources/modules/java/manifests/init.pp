
$localrepo = "/root/.m2/repository"
$groupid = "com/oracle"
$bucket = "maven.kuali.org"
$prefix = "private"
$packaging = "zip"
$classifier = "linux-x64"
$jdk7artifactid = "jdk7"
$jdk6artifactid = "jdk6"

$jdk6version = "1.6.0-u34"
$jdk7version = "1.7.0-u07"

$jdk6packaging = "bin"

$jdk6 = "${groupid}:${jdk6artifactid}:${jdk6version}:${jdk6packaging}:${classifier}"
$jdk7 = "${groupid}:${jdk7artifactid}:${jdk7version}:${packaging}:${classifier}"

s3artifact { $jdk6:
  localrepo   => $localrepo,
  bucket      => $bucket,
  prefix      => $prefix,
  groupid     => $groupid,
  artifactid  => $jdk6artifactid,
  version     => $jdk6version,
  classifier  => $classifier,
  packaging   => $jdk6packaging,
}


s3artifact { $jdk7:
  localrepo   => $localrepo,
  bucket      => $bucket,
  prefix      => $prefix,
  groupid     => $groupid,
  artifactid  => $jdk7artifactid,
  version     => $jdk7version,
  classifier  => $classifier,
  packaging   => $packaging,
}
