define java::jdk ($local_repo = "/root/.m2/repository"
  , $bucket = "maven.kuali.org"
  , $prefix = "private"
  , $group_id = "com.oracle"
  , $artifact_id = "jdk"
  , $version
  , $packaging = "zip"
  , $classifier = "linux-x64"
  , $level
) {

  $artifact = "jdk-${version}"
  
  s3artifact { $artifact:
    local_repo  => $local_repo,
    bucket      => $bucket,
    prefix      => $prefix,
    group_id    => $group_id,
    artifact_id => $artifact_id,
    version     => $version,
    packaging   => $packaging,
    classifier  => $classifier,
  }

  java::install { $artifact:
    local_repo   => $local_repo,
    group_id     => $group_id,
    artifact_id  => $artifact_id,
    version      => $version,
    packaging    => $packaging,
    classifier   => $classifier,
    level        => $level,
    require      => S3artifact[$artifact],
  }
  
}
