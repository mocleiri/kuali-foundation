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
  #  - Any non-existing parent directories are automatically created as needed
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"

  # The key to the md5 checksum of the S3 object being downloaded
  $md5key = "${key}.md5"
  # Fully qualified path for the downloaded .md5 file
  $md5file = "${filename}.md5"
  # The md5 checksum maintained by S3 of the .md5 file
  $md5md5 = s3md5($bucket,$md5key)
  # Condition indicating the local .md5 file exactly matches what is on S3
  #  1 - The .md5 file exists AND
  #  2 - The local md5 checksum of the .md5 file matches the md5 checksum maintained by S3 
  $md5unless = "[ -e ${md5file} ] && echo \"${md5md5}  ${md5file}\" | md5sum --check --status"
  # cURL command for downloading the .md5 file associated with the S3 object
  $md5exec = "s3curl(${bucket}, ${md5key}, ${md5file}, ${expires})"
  
  # cURL command for downloading the S3 object itself
  $objectexec = "s3curl(${bucket}, ${key}, ${filename}, ${expires})"
  # Condition indicating the local file exactly matches the S3 object
  #  1 - The local file exists AND
  #  2 - The local md5 checksum of the file matches the contents of the .md5 file 
  $objectunless = "[ -e ${filename} ] && grep `md5sum ${filename} | cut -c1-32` ${md5file}"
  
  exec { $md5exec:
    command => s3curl($bucket, $md5key, $md5file, $expires),
    unless  => $md5unless,
  }
  
  exec { $objectexec:
    command     => s3curl($bucket, $key, $filename, $expires),
    subscribe   => Exec[$md5exec],
    refreshonly => true,
  }

}

class {'maven': }
