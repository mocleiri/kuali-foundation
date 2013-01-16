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
  if ($classifier != undef) {
    $id = "${id_fragment}:${classifier}"
  } else {
    $id = $id_fragment
  }
  
  notify {$id:}
  notify {$bucket:}
  if ($prefix != undef) {
    notify {$prefix:}
  }
}
