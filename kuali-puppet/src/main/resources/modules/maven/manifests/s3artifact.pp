class maven::s3artifact ($group_id, $artifact_id, $version, $packaging = 'jar', $classifier = undef) {

  $gav = "${group_id}:${artifact_id}:${version}:${packaging}"
  notify {$gav:}
  
}
