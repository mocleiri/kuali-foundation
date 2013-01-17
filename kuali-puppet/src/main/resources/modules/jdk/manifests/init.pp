define jdk ($localrepo = "/root/.m2/repository"
  , $bucket = "maven.kuali.org"
  , $prefix = "private"
  , $groupid = "com.oracle"
  , $artifactid = "jdk"
  , $version
  , $packaging = "zip"
  , $classifier = "linux-x64"
  , $level
) {

  $artifact = "jdk-${version}"
  
  notify {$artifact:}
  
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
    localrepo => $localrepo,
    level     => $level,
    version   => $version,
    require   => S3artifact[$artifact]
  }
  
}
