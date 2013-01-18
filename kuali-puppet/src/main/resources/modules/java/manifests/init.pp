class java ($jdk6_version = undef
  , $jdk7_version = undef
  , $bucket
  , $prefix = undef
  , $group_id = "com.oracle"
  , $artifact_id = "jdk"
  , $packaging = "zip"
  , $classifier
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
