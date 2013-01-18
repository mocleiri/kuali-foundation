class kuali {

  # File system paths to check for exec calls
  # Do this once so each exec command inherits the paths
  $exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]
  Exec { path => $exec_path }
  
  $local_repo = "/root/.m2/repository"
  $bucket = "maven.kuali.org"
  
  class { 'kuali::packages': }

  class { 'java':
    jdk6_version => "1.6.0-u38",
    jdk7_version => "1.7.0-u11",
    local_repo   => $local_repo,
    bucket       => $bucket,
    prefix       => "private",
    group_id     => "com.oracle",
    artifact_id  => "jdk",
    packaging    => "zip",
    classifier   => "linux-x64",
  }
  
}

class {'kuali':}