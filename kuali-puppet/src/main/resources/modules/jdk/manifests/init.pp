define jdk ($localrepo = "/root/.m2/repository"
  , $bucket = "maven.kuali.org"
  , $prefix = "private"
  , $level
  , $version
) {

  $artifact = "jdk-${version}"
  notify {$artifact:}
  
  jdk::download { $artifact:
    level   => $level,
    version => $version,
  }

  jdk::install { $artifact:
    level   => $level,
    version => $version,
    require => Jdk::Download[$artifact]
  }
  
}
