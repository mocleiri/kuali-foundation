s3artifact { "java":
  bucket      => 'maven.kuali.org',
  prefix      => 'private',
  group_id    => 'com.oracle',
  artifact_id => 'jdk7',
  version     => '1.7.0-u07',
  classifier  => 'linux-x64',
}