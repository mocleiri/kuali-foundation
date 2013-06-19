define tomcat::install ($localrepo
  , $groupid
  , $artifactid
  , $version
  , $packaging
  , $basedir = '/usr/local'
) {

  $filename = "${artifactid}-${version}.${packaging}"
  $groupidpath = getpath($groupid)
  $repopath = "${groupidpath}/${artifactid}/${version}"
  $file = "${localrepo}/${repopath}/${filename}"
  
  $tomcatdir = "${basedir}/${artifactid}-${version}"
  $tomcatsymlink = "${basedir}/tomcat"
  $unzip = "unzip -d ${basedir} ${file}"
  
  exec { $unzip:
    command => $unzip,
    creates => $jdkdir,
  }
  
  file { $tomcatsymlink:
    ensure  => 'link',
    target  => $tomcatdir,
    require => Exec[$unzip],
  }
  
}
