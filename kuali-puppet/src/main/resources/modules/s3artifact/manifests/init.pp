define s3artifact ($local_repo
  , $bucket
  , $prefix = undef
  , $group_id
  , $artifact_id
  , $version
  , $packaging = 'jar'
  , $classifier = undef
) {

  # The amount of time in seconds the pre-signed url is valid for
  # The http request must be initiated within this time
  # The http request does not have to be completed within this time
  $expires = 30
  
  # The name of the file eg "commons-io-1.3.2.jar"
  if ($classifier == undef) {
    $filename = "${artifact_id}-${version}.${packaging}"
  } else {
    $filename = "${artifact_id}-${version}-${classifier}.${packaging}"
  }
  
  # Convert "org.apache.commons" into "org/apache/commons"
  $group_idpath = getpath($group_id)

  # The relative path to the directory containing the file in a Maven repository
  # This value is identical for both the S3 Maven repository and the Maven repository on the local file system
  # eg "org/apache/commons/commons-io/1.3.2"
  $path = "${group_idpath}/${artifact_id}/${version}"

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
  $file = "${local_repo}/${path}/${filename}"
  
  # The S3 key to the .md5 file holding the checksum for the S3 object being downloaded
  $md5_key = "${key}.md5"

  # Fully qualified filename for the downloaded .md5 file
  $md5_file = "${file}.md5"
  
  # The md5 checksum maintained by S3 of the .md5 file
  $md5_md5 = s3md5($bucket,$md5_key)

  # Condition indicating the local .md5 file exactly matches the .md5 file on S3
  #  1 - The .md5 file exists AND
  #  2 - The locally generated md5 checksum of the local .md5 file matches the md5 checksum maintained by S3 
  $md5_unless = "[ -e ${md5_file} ] && echo \"${md5_md5}  ${md5_file}\" | md5sum --check --status"

  # Title of the exec resource for the cURL command that downloads the .md5 file associated with the S3 object
  $md5_exec = "s3curl(${bucket}, ${md5_key}, ${md5_file}, ${expires})"

  # Title of the exec resource for the cURL command that downloads the S3 object itself
  $ojbect_exec = "s3curl(${bucket}, ${key}, ${file}, ${expires})"
  
  # Exec resource that downloads the .md5 checksum of the S3 object
  exec { $md5_exec:
    # Execute a cURL command to download the .md5 file associated with the S3 object
    command => s3curl($bucket, $md5_key, $md5_file, $expires),
    # Unless the .md5 already exists locally AND the md5 checksum of the .md5 file matches the md5 checksum maintained by S3
    unless  => $md5_unless,
  }
  
  # Exec resource that downloads the S3 object itself
  exec { $ojbect_exec:
    # Execute a cURL command to download the S3 object
    command     => s3curl($bucket, $key, $file, $expires),
    # Subscribe to the exec resource that downloads the .md5 file associated with the S3 object
    subscribe   => Exec[$md5_exec],
    # Never run this exec command on its own
    # Only run it in response to the $md5_exec command running
    # The $md5_exec command will only run if the .md5 file does not exist OR
    # the .md5 file no longer matches what is on S3
    refreshonly => true,
  }
}
