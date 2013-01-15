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

$mkdircmd = "mkdir -p ${localdir}"
$curlcmd = "curl --location --output ${localfile} ${url}"
$curlunless = "[ -e ${localfile} ] && curl --silent --head ${url} | grep ETag | grep `md5sum ${localfile} | cut --characters=1-32`"

#
# Create the directory making parent directories as needed, unless the directory already exists
#
exec { $mkdircmd:
  path    => $paths,
  command => $mkdircmd,
  creates => $localdir,
  before  => Exec[$curlcmd],
}

#
# Invoke cURL to download the artifact UNLESS
#   1 - the artifact already exists in the local Maven repository AND
#   2 - the md5 checksum returned in the Amazon S3 http header equals the md5 checksum returned by the local file system
#
exec { $curlcmd:
  path    => $paths,
  command => $curlcmd,
  unless  => $curlunless,
}

