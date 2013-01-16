$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"
  $expires = 30
  
  $curlcmd = s3getcurlcmd($bucket, $key, $filename, $expires)

  exec { $curl:
    command => $curlcmd,
  }
}

class {'maven': }