define java::install ($local_repo
  , $group_id
  , $artifact_id
  , $version
  , $packaging
  , $classifier = undef
  , $level
  , $basedir = '/usr/java'
) {

  # Convert "com.oracle" into "com/oracle"
  $group_id_path = getpath($group_id)
  
  # The name of the file eg "jdk-1.7.0-u11-linux-x64.jar"
  if ($classifier == undef) {
    $filename = "${artifact_id}-${version}.${packaging}"
  } else {
    $filename = "${artifact_id}-${version}-${classifier}.${packaging}"
  }
  
  # The relative path to the directory containing the file in a Maven repository
  # This value is identical for both the S3 Maven repository and the Maven repository on the local file system
  # eg "com/oracle/jdk/1.7.0-u11"
  $repo_path = "${group_id_path}/${artifact_id}/${version}"

  # Fully qualified filename that the S3 object will be downloaded to
  # Any non-existing parent directories are automatically created by cURL as needed
  $file = "${local_repo}/${repo_path}/${filename}"
  
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
