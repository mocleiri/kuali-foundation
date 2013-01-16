define s3artifact ($bucket, $prefix = undef) {
  notify {$bucket:}
  if ($prefix != undef) {
    notify {$prefix:}
  }
}
