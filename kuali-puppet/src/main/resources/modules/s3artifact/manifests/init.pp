define s3artifact ($localrepo
  , $bucket
  , $prefix = undef
  , $group_id
  , $artifact_id
  , $version
  , $packaging = 'jar'
  , $classifier = undef
  , $ensure = 'present'
) {

  $exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
  Exec { path => $exec_path }
  
  $expires = 30
  
  $filename_fragment = "${artifact_id}-${version}"
  if ($classifier == undef) {
    $filename = "${filename_fragment}.${packaging}"
  } else {
    $filename = "${filename_fragment}-${classifier}.${packaging}"
  }
  
  $repo_path = "${group_id}/${artifact_id}/${version}"

  if ($prefix == undef) {
    $key = "${repo_path}/${filename}"
  } else {
    $key = "${prefix}/${repo_path}/${filename}"
  }
  
  $file = "${localrepo}/${repo_path}/${filename}"
  
  $md5key = "${key}.md5"
  $md5file = "${file}.md5"
  
  # The md5 checksum maintained by S3 of the .md5 file
  $md5md5 = s3md5($bucket,$md5key)

  # Condition indicating the local .md5 file exactly matches the .md5 file on S3
  #  1 - The .md5 file exists AND
  #  2 - The local md5 checksum of the local .md5 file matches the md5 checksum maintained by S3 
  $md5unless = "[ -e ${md5file} ] && echo \"${md5md5}  ${md5file}\" | md5sum --check --status"

  # Title of the exec resource for the cURL command that downloads the .md5 file associated with the S3 object
  $md5exec = "s3curl(${bucket}, ${md5key}, ${md5file}, ${expires})"

  # Title of the exec resource for the cURL command that downloads the S3 object itself
  $objectexec = "s3curl(${bucket}, ${key}, ${file}, ${expires})"
  
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
    command     => s3curl($bucket, $key, $file, $expires),
    # Subscribe to the exec resource that downloads the .md5 file associated with the S3 object
    subscribe   => Exec[$md5exec],
    # Only download the S3 object if the .md5 file changes
    refreshonly => true,
  }
}
