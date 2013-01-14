$repo = "http://maven.kuali.org/release"
$groupid = "org/kuali/rice"
$artifactid = "rice-core-api"
$version = "2.2.0"
$packaging = "jar"
$repopath = "${groupid}/${artifactid}/${version}"
$filename = "${artifactid}-${version}.${packaging}"
$userhome = "/root"
$localrepo = "${userhome}/.m2/repository"
$localdir = "${localrepo}/${repopath}"
$localfile = "${localdir}/${filename}"
$url = "${repo}/${repopath}/${filename}"
$paths = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]

exec { "create-bootstrap-dir":
  path    => $paths,
  command => "mkdir -p ${localdir}",
  unless  => "[ -e ${localdir} ]"
}

exec { "fetch ${filename}":
  path    => $paths,
  command => "curl --location -output ${localfile} ${url}",
  require => Exec["create-bootstrap-dir"],
  unless  => "[ -e ${filename} ] && curl --head ${url} | grep ETag | grep `md5sum ${filename} | cut -c1-32`"
}