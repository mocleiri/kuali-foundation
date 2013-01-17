define tomcat ($localrepo = "/root/.m2/repository"
  , $bucket = "maven.kuali.org"
  , $prefix = "external"
  , $groupid = "org.apache.tomcat"
  , $artifactid = "apache-tomcat"
  , $version
  , $packaging = "zip"
  , $classifier = undef,
) {

  $artifact = "${artifactid}-${version}"
  
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

  tomcat::install { $artifact:
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
