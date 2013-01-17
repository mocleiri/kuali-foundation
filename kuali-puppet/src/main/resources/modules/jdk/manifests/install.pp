define jdk::install ($localrepo
  , $groupid
  , $artifactid
  , $version
  , $packaging
  , $classifier
  , $level
  , $basedir = '/usr/java'
) {

    # The name of the file eg "commons-io-1.3.2.jar"
  if ($classifier == undef) {
    $filename = "${artifactid}-${version}.${packaging}"
  } else {
    $filename = "${artifactid}-${version}-${classifier}.${packaging}"
  }
  
  # Convert "org.apache.commons" into "org/apache/commons"
  $groupidpath = getpath($groupid)
  
  # The relative path to the directory containing the file in a Maven repository
  # This value is identical for both the S3 Maven repository and the Maven repository on the local file system
  # eg "org/apache/commons/commons-io/1.3.2"
  $path = "${groupidpath}/${artifactid}/${version}"

  # Fully qualified filename that the S3 object will be downloaded to
  # Any non-existing parent directories are automatically created by cURL as needed
  $file = "${localrepo}/${path}/${filename}"
  
  $jdkdir = "${basedir}/jdk-${version}"
  $jdksymlink = "${basedir}/jdk${level}"
  $unzip = "unzip -d ${basedir} ${file}"
  
  file { $jdksymlink:
    ensure => absent,
  }
  
  file { $jdkdir:
    ensure  => absent,
    recurse => true,
  }
  
  exec { $unzip:
    command => $unzip,
    require => File[$jdksymlink,$jdkdir],
  }
  
	
}
