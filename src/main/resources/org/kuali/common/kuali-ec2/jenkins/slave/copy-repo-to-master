#!/bin/sh

echo $(date)
set -xe

function check_not_blank {
  if [ ! -n "$2" ]; then 
    echo $1 cannot be blank
    exit 1
  fi
}

JENKINS_MASTER=$1
check_not_blank JENKINS_MASTER $JENKINS_MASTER

# The trailing slash is significant here
REPO=/root/.m2/repository
SRC=$REPO/
DST=root@$JENKINS_MASTER:$REPO
EXCLUDEFILE=/usr/share/ec2slave/rsync-excludes

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude-from $EXCLUDEFILE

set +x
echo $(date)
