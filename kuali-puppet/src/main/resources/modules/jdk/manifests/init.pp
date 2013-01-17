define jdk ($level, $version) {
  
) {

  $artifact = "jdk-${version}"
  
  jdk::download {$artifact:
  }

  jdk::install {$artifact:
    require => Jdk::Download[$artifact]
  }
}
