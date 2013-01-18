class java ($jdk6_version = undef
  , $jdk7_version = undef
  , $bucket
  , $prefix = undef
  , $group_id = "com.oracle"
  , $artifact_id = "jdk"
  , $version
  , $packaging = "zip"
  , $classifier
) {

  $artifact = "jdk-${version}"
  
  s3artifact { $artifact:
    localrepo  => $localrepo,
    bucket     => $bucket,
    prefix     => $prefix,
    groupid    => $groupid,
    artifactid => $artifactid,
    version    => $version,
    packaging  => $packaging,
    classifier => $classifier,
  }

  jdk::install { $artifact:
    localrepo   => $localrepo,
    groupid     => $groupid,
    artifactid  => $artifactid,
    version     => $version,
    packaging   => $packaging,
    classifier  => $classifier,
    level       => $level,
    require     => S3artifact[$artifact],
  }
  
}
