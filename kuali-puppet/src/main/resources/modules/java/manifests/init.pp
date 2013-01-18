class java ($jdk6_version = undef
  , $jdk7_version = undef
  , $local_repo = "/root/.m2/repository"
  , $bucket = "maven.kuali.org"
  , $prefix = "private"
  , $group_id = "com.oracle"
  , $artifact_id = "jdk"
  , $packaging = "zip"
  , $classifier = "linux-x64"
) {

  java::jdk { 'jdk7':
    level   => '7',
    version => $jdk7_version,
  }

  java::jdk { 'jdk6':
    level   => '6',
    version => $jdk6_version,
  }

}
