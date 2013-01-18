define jdk::install ($local_repo
  , $group_id
  , $artifact_id
  , $version
  , $packaging
  , $classifier
  , $level
  , $basedir = '/usr/java'
) {

  # The name of the file eg "commons-io-1.3.2.jar"
  if ($classifier == undef) {
    $filename = "${artifact_id}-${version}.${packaging}"
  } else {
    $filename = "${artifact_id}-${version}-${classifier}.${packaging}"
  }
  
  # Convert "org.apache.commons" into "org/apache/commons"
  $group_id_path = getpath($group_id)
  
  # The relative path to the directory containing the file in a Maven repository
  # This value is identical for both the S3 Maven repository and the Maven repository on the local file system
  # eg "org/apache/commons/commons-io/1.3.2"
  $repopath = "${group_id_path}/${artifact_id}/${version}"

  # Fully qualified filename that the S3 object will be downloaded to
  # Any non-existing parent directories are automatically created by cURL as needed
  $file = "${local_repo}/${repopath}/${filename}"
  
  $jdk_dir = "${basedir}/jdk-${version}"
  $jdk_symlink = "${basedir}/jdk${level}"
  $unzip = "unzip -d ${basedir} ${file}"
  
  exec { $unzip:
    command => $unzip,
    creates => $jdk_dir,
  }
  
  file { $jdk_symlink:
    ensure  => 'link',
    target  => $jdk_dir,
    require => Exec[$unzip],
  }
  
}
