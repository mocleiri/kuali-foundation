$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"
  $expires = 30
  
  $s3curlmd5 = s3curlcmd($bucket, "${key}.md5", "${filename}.md5", $expires)
  $s3curlcmd = s3curlcmd($bucket, $key, $filename, $expires)
  
  exec { $s3curlmd5:
    command => $s3curlmd5,
  }
  
  exec { $s3curlcmd:
    command => $s3curlcmd,
    require => Exec[$s3curlmd5],
  }

}

class {'maven': }