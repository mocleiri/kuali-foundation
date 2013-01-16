$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"
  $expires = 30
  
  $s3curlcmd = s3curlcmd($bucket, $key, $filename, $expires)
  $s3md5sum = s3md5sum($bucket, $key)

  exec { $curl:
    command => $s3curlcmd,
    logoutput => true,
  }
  
  file { "${filename}.md5":
    
  }
}

class {'maven': }