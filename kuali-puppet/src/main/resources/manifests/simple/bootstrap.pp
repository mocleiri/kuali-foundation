$groupid = "com/oracle"
$artifactid = "ojdbc14"
$version = "10.2.0.3.0"
$packaging = "pom"
$repo = "https://maven.kuali.org/external"
$repopath = "${groupid}/${artifactid}/${version}/${artifactid}-${version}";
$userhome = "/root"
$filename = "${userhome}/.m2/repository/${repopath}"
$url = "${repository}/${repopath}"


exec { "fetch ${filename}":
  path => ['/bin', '/usr/bin', 'sbin', '/usr/sbin'],
  command => "curl -L -o ${filename} ${url}",
}