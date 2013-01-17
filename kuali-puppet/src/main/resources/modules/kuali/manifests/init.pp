class kuali {

  # File system paths to check for cURL and md5sum
  $exec_path = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]

  # Do this once so each exec command can inherit the paths
  Exec { path => $exec_path }
  
  $jdk6version = "1.6.0-u38"
  $jdk7version = "1.7.0-u11"
  $tomcatversion = "6.0.35"
  
  jdk { 'jdk6':
    level      => '6',
    version    => $jdk6version,
  }

  jdk { 'jdk7':
    level      => '7',
    version    => $jdk7version,
  }

}

class {'kuali':}