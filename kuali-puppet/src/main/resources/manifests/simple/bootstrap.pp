$repo = "http://maven.kuali.org/external"
$groupid = "com/oracle"
$artifactid = "ojdbc14"
$version = "10.2.0.3.0"
$packaging = "pom"
$repopath = "${groupid}/${artifactid}/${version}/"
$filename = "${artifactid}-${version}.${packaging}"
$userhome = "/root"
$localrepo = "${userhome}/.m2/repository"
$localdir = "${localrepo}/${repopath}"
$localfile = "${localdir}/${filename}"
$url = "${repo}/${repopath}/${filename}"
$paths = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]


exec { "create-bootstrap-dir":
  path    => $paths
  command => "mkdir -p ${localdir}",
}

exec { "fetch ${filename}":
  path    => $paths
  command => "curl -L -o ${localfile} ${url}",
  require => Exec["create-bootstrap-dir"],
}