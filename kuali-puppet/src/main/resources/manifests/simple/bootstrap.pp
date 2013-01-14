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


file { $localdir:
  ensure  => directory,
  recurse => true,
  force   => true,
}

exec { "fetch ${filename}":
  path => ["/bin", "/usr/bin", "sbin", "/usr/sbin"],
  command => "curl -L -o ${filename} ${url}",
  require => File[$localdir],
}