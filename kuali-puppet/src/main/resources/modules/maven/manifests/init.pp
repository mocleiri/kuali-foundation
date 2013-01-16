$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $key = "builds/org/kuali/pom/kuali-common/1.1.9-r8385/kuali-common-1.1.9-r8385.pom"
  $filename = "/tmp/kuali-common-1.1.9-r8385.pom"
  $expires = 30
  
  $s3curlcmd = s3curlcmd($bucket, $key, $filename, $expires)

  exec { $s3curlcmd:
    command => $s3curlcmd,
  }
  
}

class {'maven': }