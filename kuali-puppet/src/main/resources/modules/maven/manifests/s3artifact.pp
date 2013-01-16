$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven::s3artifact($bucket, $directory, $group_id, $artifact_id, $version, $packaging => 'jar', $classifier) {

  $gav = "${group_id}:${artifact_id}:${version}:${packaging}:${classifier}"
  
  notify {$gav:}  

}
