$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/kuali-common-1.1.9-r8385.pom"
  $expires = 30
  
  $s3curlcmd = s3curlcmd($bucket, $key, $filename, $expires)

  exec { $s3curlcmd:
    command => $s3curlcmd,
    logoutput => true,
  }
  
}

class {'maven': }