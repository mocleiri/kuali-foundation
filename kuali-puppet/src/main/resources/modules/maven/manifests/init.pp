$exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
Exec { path => $exec_path }

class maven {
  
  $bucket = "maven.kuali.org"
  $expires = 30

  $key = "private/com/oracle/jdk7/1.7.0-u07/jdk7-1.7.0-u07-linux-x64.zip"
  $filename = "/tmp/jdk7-1.7.0-u07-linux-x64.zip"

  $md5key = "${key}.md5"
  $md5file = "${filename}.md5"
  $md5md5 = s3md5($bucket,$md5key)
  $md5unless = "echo \"${md5md5}  ${md5file}\" | md5sum -c --status"
  $md5exec = "s3curl(${bucket}, ${md5key}, ${md5file}, ${expires})"
  
  $objectexec = "s3curl(${bucket}, ${key}, ${filename}, ${expires})"
  $objectunless = "grep `md5sum ${filename} | cut -c1-32` ${md5file}"
  
  exec { $md5exec:
    command => s3curl($bucket, $md5key, $md5file, $expires),
    unless  => $md5unless,
  }
  
  exec { $objectexec:
    command => s3curl($bucket, $key, $filename, $expires),
    unless => $objectunless,
    require => Exec[$md5exec],
  }

}

class {'maven': }

# grep `md5sum jdk7-1.7.0-u07-linux-x64.zip | cut -c1-32` jdk7-1.7.0-u07-linux-x64.zip.md5  && echo yo