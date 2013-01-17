define jdk ($localrepo, $bucket, $prefix, $level, $version) {

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
