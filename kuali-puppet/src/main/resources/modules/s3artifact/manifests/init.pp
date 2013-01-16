define s3artifact ($bucket, $prefix = undef, $group_id, $artifact_id, $version, $packaging = 'jar', $classifier = undef) {
  notify {$bucket:}
  if ($prefix != undef) {
    notify {$prefix:}
  }
}
