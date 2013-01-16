$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $expires = 30

  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"

  $key_md5 = "${key}.md5"
  $filename_md5 = "${filename}.md5"
  
  $s3curlmd5exec = "s3curl(${bucket}, ${key_md5}, ${filename_md5}, ${expires})"
  
  exec { $s3curlmd5exec:
    command => s3curl($bucket, $key_md5, $filename_md5, $expires),
  }
  
  exec { "s3curl(${bucket}, ${key}, ${filename}, ${expires})":
    command => s3curl($bucket, $key, $filename, $expires),
    require => Exec[$s3curlmd5exec],
  }

}

class {'maven': }