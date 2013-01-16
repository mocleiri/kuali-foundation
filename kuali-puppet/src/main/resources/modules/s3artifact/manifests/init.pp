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

  $id_fragment = "${group_id}:${artifact_id}:${version}:${packaging}"
  $filename_fragment = "${artifact_id}-${version}"
  
  if ($classifier == undef) {
    $id = $id_fragment
    $filename = "${filename_fragment}.${packaging}"
  } else {
    $id = "${id_fragment}:${classifier}"
    $filename = "${filename_fragment}-${classifier}.${packaging}"
  }
  
  $repo_path = "${group_id}/${artifact_id}/${version}"

  if ($prefix == undef) {
    $key = "${repo_path}/${filename}"
  } else {
    $key = "${prefix}/${repo_path}/${filename}"
  }
  
  $path = "${localrepo}/${repo_path}/${filename}"
  
  notify {"${bucket} ${key} ${path}:}
}
