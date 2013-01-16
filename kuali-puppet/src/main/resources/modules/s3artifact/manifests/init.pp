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
  
  notify {"${bucket} ${key} ${file}":}
}
