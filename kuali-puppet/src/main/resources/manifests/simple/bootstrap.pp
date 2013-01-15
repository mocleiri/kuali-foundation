# http://maven.kuali.org.s3.amazonaws.com/release/org/kuali/rice/rice-core-api/2.2.0/rice-core-api-2.2.0.jar

$s3 = "s3.amazonaws.com"
$bucket = "maven.kuali.org"
$prefix = "release"
$repo = "https://${s3}/${bucket}/${prefix}"
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
  creates => $localdir
}

exec { "fetch ${filename}":
  path    => $paths,
  command => "curl --location -output ${localfile} ${url}",
  require => Exec["create-bootstrap-dir"],
  unless  => "[ -e ${localfile} ] && curl --silent --head ${url} | grep ETag | grep `md5sum ${localfile} | cut --characters=1-32`"
}