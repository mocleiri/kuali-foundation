class mvn::s3artifact ($localrepo, $bucket, $prefix = undef, $group_id, $artifact_id, $version, $packaging = 'jar', $classifier = undef) {

  $gav = "${group_id}:${artifact_id}:${version}:${packaging}"
  notify {$gav:}
  
}
