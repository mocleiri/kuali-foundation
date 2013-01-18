class java ($jdk6_version = undef
  , $jdk7_version = undef
  , $bucket
  , $prefix = undef
  , $group_id = "com.oracle"
  , $artifact_id = "jdk"
  , $packaging = "zip"
  , $classifier
) {

  $jdk6_artifact = "jdk-${jdk6_version}"
  $jdk7_artifact = "jdk-${jdk7_version}"
  
  s3artifact { $jdk6_artifact:
    local_repo  => $local_repo,
    bucket      => $bucket,
    prefix      => $prefix,
    group_id    => $group_id,
    artifact_id => $artifact_id,
    version     => $jdk6_version,
    packaging   => $packaging,
    classifier  => $classifier,
  }

  jdk::install { $jdk6_artifact:
    local_repo   => $local_repo,
    group_id     => $group_id,
    artifact_id  => $artifact_id,
    version      => $jdk6_version,
    packaging    => $packaging,
    classifier   => $classifier,
    level        => $level,
    require      => S3artifact[$artifact],
  }
  
}
