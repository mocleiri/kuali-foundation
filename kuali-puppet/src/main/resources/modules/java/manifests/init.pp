
$localrepo = "/root/.m2/repository"
$group_id = "com/oracle"
$bucket = "maven.kuali.org"
$prefix = "private"
$packaging = "zip"
$classifier = "linux-x64"

$jdk7version = "1.7.0-u07"

$jdk7artifact = "${group_id}:jdk7:${jdk7version}:${packaging}:${classifier}"

s3artifact { $jdk7artifact:
  localrepo   => '/root/.m2/repository',
  bucket      => 'maven.kuali.org',
  prefix      => 'private',
  group_id    => 'com/oracle',
  artifact_id => 'jdk7',
  packaging   => 'zip',
  version     => '1.7.0-u07',
  classifier  => 'linux-x64',
}