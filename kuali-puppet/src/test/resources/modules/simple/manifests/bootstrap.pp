$s3 = "s3.amazonaws.com"
$bucket = "maven.kuali.org"
$prefix = "release"
$repo = "https://${s3}/${bucket}/${prefix}"
$group_id = "org/kuali/rice"
$artifact_id = "rice-core-api"
$version = "2.2.0"
$packaging = "jar"
$repopath = "${group_id}/${artifact_id}/${version}"
$filename = "${artifact_id}-${version}.${packaging}"
$userhome = "/root"
$localrepo = "${userhome}/.m2/repository"
$localdir = "${localrepo}/${repopath}"

$localfile = "${localdir}/${filename}"
$localfile_md5 = "${localdir}/${filename}.md5"
$url = "${repo}/${repopath}/${filename}"
$url_md5 = "${repo}/${repopath}/${filename}.md5"
$paths = ["/bin", "/usr/bin", "/sbin", "/usr/sbin", "/usr/local/sbin", "/usr/local/bin"]

$mkdir = "mkdir -p ${localdir}"

$curl = "curl --location --output ${localfile} ${url}"
$curl_unless = "[ -e ${localfile} ] && curl --silent --head ${url} | grep ETag | grep `md5sum ${localfile} | cut --characters=1-32`"

$curl_md5 = "curl --location --output ${localfile_md5} ${url_md5}"
$curl_md5_unless = "[ -e ${localfile_md5} ] && curl --silent --head ${url_md5} | grep ETag | grep `md5sum ${localfile_md5} | cut --characters=1-32`"

#
# Create the directory making parent directories as needed, unless the directory already exists
#
exec { $mkdir:
  path    => $paths,
  command => $mkdir,
  creates => $localdir,
  before  => Exec[$curl, $curl_md5],
}

#
# Invoke cURL to download the artifact UNLESS
#   1 - the artifact already exists in the local Maven repository AND
#   2 - the md5 checksum returned in the Amazon S3 http header matches the md5 checksum returned by the local file system
#
exec { $curl:
  path    => $paths,
  command => $curl,
  unless  => $curl_unless,
}

#
# Invoke cURL to download the artifact UNLESS
#   1 - the artifact already exists in the local Maven repository AND
#   2 - the md5 checksum returned in the Amazon S3 http header equals the md5 checksum returned by the local file system
#
exec { $curl_md5:
  path    => $paths,
  command => $curl_md5,
  unless  => $curl_md5_unless,
}

