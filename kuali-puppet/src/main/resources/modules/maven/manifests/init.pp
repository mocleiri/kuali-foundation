class maven {

  notify {'Hello world':}
  
  $bucket = "maven.kuali.org"
  $title = "foo"
  $name = "bar"
  $expires = 30
  
  $url = s3getcurl("maven.kuali.org", "foo", "bar", 30)

  notify {$url:}
}

class {'maven': }