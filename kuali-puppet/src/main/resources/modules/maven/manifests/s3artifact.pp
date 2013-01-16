class maven::s3artifact ($bucket, $directory, $group_id, $artifact_id, $version, $packaging = 'jar', $classifier = undef) {

  $gav = "${group_id}:${artifact_id}:${version}:${packaging}:${classifier}"
  
  notify {$gav:}  

}
