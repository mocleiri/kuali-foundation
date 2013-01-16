class maven {

  notify {'Hello world':}
  
  $bucket = "maven.kuali.org"
  $key = "foo"
  $filename = "/tmp/tmp.txt"
  $expires = 30
  
  $url = s3getcurl($bucket,$key,$filename,$expires)

  notify {$url:}
}

class {'maven': }