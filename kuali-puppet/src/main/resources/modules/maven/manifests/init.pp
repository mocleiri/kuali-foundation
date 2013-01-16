class maven {

  notify {'Hello world':}
  
  $bucket = "maven.kuali.org"
  $title = "foo"
  $name = "bar"
  $expires = 30
  
  $curl = s3getcurl($bucket, $title, $name, $expires),

  notify {$curl:}
}

class {'maven': }