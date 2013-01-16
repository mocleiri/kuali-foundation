define s3artifact ($localrepo
  , $bucket
  , $prefix = undef
  , $group_id
  , $artifact_id
  , $version
  , $packaging = 'jar'
  , $classifier = undef
  , $ensure = 'present'
) {

  notify {$bucket:}
  if ($prefix != undef) {
    notify {$prefix:}
  }
}
