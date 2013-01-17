define s3artifact ($localrepo
  , $bucket
  , $prefix = undef
  , $groupid
  , $artifactid
  , $version
  , $packaging = 'jar'
  , $classifier = undef
) {

  # File system paths to check for cURL and md5sum
  $exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]

  # Do this once so each exec command can inherit the paths
  Exec { path => $exec_path }
  
  # The amount of time in seconds the pre-signed url is valid for
  # The http request must be initiated within 30 seconds
  # The http request does not have to completed within 30 seconds
  $expires = 30
  
  # The name of the file eg "commons-io-1.3.2.jar"
  if ($classifier == undef) {
    $filename = "${artifactid}-${version}.${packaging}"
  } else {
    $filename = "${artifactid}-${version}-${classifier}.${packaging}"
  }
  
  # Convert "org.apache.commons" into "org/apache/commons"
  $groupidpath = getpath($groupid)

  # The relative path to the directory containing the file in the maven repository
  # This value is identical for both the S3 maven repository and the maven repository on the local file system
  # eg "org/apache/commons/commons-io/1.3.2"
  $path = "${groupidpath}/${artifactid}/${version}"

  # The fully qualified key to the correct S3 object in the bucket
  # "org/apache/commons/commons-io/1.3.2/commons-io-1.3.2.jar" or
  # "release/org/apache/commons/commons-io/1.3.2/commons-io-1.3.2.jar"
  if ($prefix == undef) {
    $key = "${path}/${filename}"
  } else {
    $key = "${prefix}/${path}/${filename}"
  }
  
  # Fully qualified filename that the S3 object will be downloaded to
  # Any non-existing parent directories are automatically created by cURL as needed
  $file = "${localrepo}/${path}/${filename}"
  
  # The S3 key to the .md5 file holding the checksum for the S3 object being downloaded
  $md5key = "${key}.md5"

  # Fully qualified filename for the downloaded .md5 file
  $md5file = "${file}.md5"
  
  # The md5 checksum maintained by S3 of the .md5 file
  $md5md5 = s3md5($bucket,$md5key)

  # Condition indicating the local .md5 file exactly matches the .md5 file on S3
  #  1 - The .md5 file exists AND
  #  2 - The locally generated md5 checksum of the local .md5 file matches the md5 checksum maintained by S3 
  $md5unless = "[ -e ${md5file} ] && echo \"${md5md5}  ${md5file}\" | md5sum --check --status"

  # Title of the exec resource for the cURL command that downloads the .md5 file associated with the S3 object
  $md5exec = "s3curl(${bucket}, ${md5key}, ${md5file}, ${expires})"

  # Title of the exec resource for the cURL command that downloads the S3 object itself
  $objectexec = "s3curl(${bucket}, ${key}, ${file}, ${expires})"
  
  # Exec resource that downloads the .md5 checksum of the S3 object
  exec { $md5exec:
    # Execute a cURL command to download the .md5 file associated with the S3 object
    command => s3curl($bucket, $md5key, $md5file, $expires),
    # Unless the .md5 already exists locally AND the md5 checksum of the .md5 file matches the md5 checksum maintained by S3
    unless  => $md5unless,
  }
  
  # Exec resource that downloads the S3 object itself
  exec { $objectexec:
    # Execute a cURL command to download the S3 object
    command     => s3curl($bucket, $key, $file, $expires),
    # Subscribe to the exec resource that downloads the .md5 file associated with the S3 object
    subscribe   => Exec[$md5exec],
    # Only download the S3 object if the .md5 file changes
    refreshonly => true,
  }
}
