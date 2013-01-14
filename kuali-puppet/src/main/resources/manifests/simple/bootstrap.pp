include s3file::curl
 
s3file { "/tmp/whatever.pom":
  s3_domain = "maven.kuali.org",
  source => "external/com/oracle/ojdbc14/10.2.0.3.0/ojdbc14-10.2.0.3.0.pom",
  ensure => "present",
}
