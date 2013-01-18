class kuali {

  # File system paths to check for cURL and md5sum
  $exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]

  # Do this once so each exec command can inherit the paths
  Exec { path => $exec_path }
  
  $local_repo = "/root/.m2/repository"
  $bucket = "maven.kuali.org"
  $jdk6_version = "1.6.0-u38"
  $jdk7_version = "1.7.0-u11"
  $tomcat_version = "6.0.35"
  
  class java { 'java':
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
  
  jdk { 'jdk6':
    level      => '6',
    version    => $jdk6_version,
  }

  jdk { 'jdk7':
    level      => '7',
    version    => $jdk7_version,
  }

  tomcat { 'tomcat':
    version    => $tomcat_version,
  }

}

class {'kuali':}