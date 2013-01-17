
$localrepo = "/root/.m2/repository"
$bucket = "maven.kuali.org"
$prefix = "private"
$groupid = "com.oracle"
$artifactid = "jdk"
$packaging = "zip"
$classifier = "linux-x64"

$jdk6version = "1.6.0-u38"
$jdk7version = "1.7.0-u11"

$jdk6 = "${groupid}:${artifactid}:${jdk6version}:${packaging}:${classifier}"
$jdk7 = "${groupid}:${artifactid}:${jdk7version}:${packaging}:${classifier}"

s3artifact { $jdk6:
  localrepo   => $localrepo,
  bucket      => $bucket,
  prefix      => $prefix,
  groupid     => $groupid,
  artifactid  => $artifactid,
  version     => $jdk6version,
  classifier  => $classifier,
  packaging   => $packaging,
}


s3artifact { $jdk7:
  localrepo   => $localrepo,
  bucket      => $bucket,
  prefix      => $prefix,
  groupid     => $groupid,
  artifactid  => $artifactid,
  version     => $jdk7version,
  classifier  => $classifier,
  packaging   => $packaging,
}
