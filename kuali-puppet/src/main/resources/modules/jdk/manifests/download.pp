define jdk::download ($localrepo, $bucket, $prefix, $level, $version) {
  notify {"Download jdk-${version}":}
}
