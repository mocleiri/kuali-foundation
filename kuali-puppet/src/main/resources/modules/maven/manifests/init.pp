$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  # The name of the S3 bucket
  $bucket = "maven.kuali.org"

  # The amount of time in seconds the pre-signed url is valid for
  $expires = 30

  # The key to an S3 object in the bucket
  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"

  # Fully qualified path to download the S3 object to
  # Any non-existing parent directories are automatically created as needed
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"

  # The key to the md5 checksum of the S3 object being downloaded
  $md5key = "${key}.md5"

  # Fully qualified path for the downloaded .md5 file
  $md5file = "${filename}.md5"

  # The md5 checksum maintained by S3 of the .md5 file
  $md5md5 = s3md5($bucket,$md5key)

  # Condition indicating the local .md5 file exactly matches the .md5 file on S3
  #  1 - The .md5 file exists AND
  #  2 - The local md5 checksum of the local .md5 file matches the md5 checksum maintained by S3 
  $md5unless = "[ -e ${md5file} ] && echo \"${md5md5}  ${md5file}\" | md5sum --check --status"

  # Title of the exec resource for the cURL command that downloads the .md5 file associated with the S3 object
  $md5exec = "s3curl(${bucket}, ${md5key}, ${md5file}, ${expires})"

  # Title of the exec resource for the cURL command that downloads the S3 object itself
  $objectexec = "s3curl(${bucket}, ${key}, ${filename}, ${expires})"
  
  # Exec resource to download the .md5 checksum of the S3 object to a local file
  exec { $md5exec:
    # Execute a cURL command to download the .md5 file associated with the S3 object
    command => s3curl($bucket, $md5key, $md5file, $expires),
    # Unless the .md5 already exists locally AND the md5 checksum of the .md5 file matches the md5 checksum maintained by S3
    unless  => $md5unless,
  }
  
  # Exec resource to download the S3 object itself
  exec { $objectexec:
    # Execute a cURL command to download the S3 object
    command     => s3curl($bucket, $key, $filename, $expires),
    # Subscribe to the exec resource that downloads the .md5 file associated with the S3 object
    subscribe   => Exec[$md5exec],
    # Only download the S3 object if the .md5 file changes
    refreshonly => true,
  }

}

class {'maven': }
