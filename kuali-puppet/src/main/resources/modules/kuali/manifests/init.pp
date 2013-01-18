class kuali {

  class { 'kuali::setup': }

  $local_repo = "/root/.m2/repository"
  $bucket = "maven.kuali.org"
  
#  class java { 'java':
#    jdk6_version => "1.6.0-u38",
#    jdk7_version => "1.7.0-u11",
#    local_repo   => $local_repo,
#    bucket       => $bucket,
#    prefix       => "private",
#    group_id     => "com.oracle",
#    artifact_id  => "jdk",
#    packaging    => "zip",
#    classifier   => "linux-x64",
#  }
  
}

class {'kuali':}