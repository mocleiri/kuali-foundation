class java ($jdk6_version = undef
  , $jdk7_version = undef
  , $local_repo
  , $bucket
  , $prefix = undef
  , $group_id
  , $artifact_id
  , $packaging
  , $classifier
) {

  if ($jdk6_version == undef and $jdk7_version == undef) {
    fail("No version provided for either jdk6 or jdk7")
  }

  if ($jdk6_version != undef) {
    java::jdk { 'jdk6':
      level   => '6',
      version => $jdk6_version,
    }
  }

  if ($jdk7_version != undef) {
    java::jdk { 'jdk7':
      level   => '7',
      version => $jdk7_version,
    }
  }

}
