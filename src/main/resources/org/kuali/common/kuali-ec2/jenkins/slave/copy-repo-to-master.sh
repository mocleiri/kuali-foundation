#!/bin/sh

echo $(date)
set -xe

REPO=/root/.m2/repository
# The trailing slash is significant here
SRC=$REPO/
DST=root@${jenkins.master}:$REPO
EXCLUDEFILE=/usr/share/ec2slave/rsync-excludes

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude-from $EXCLUDEFILE

set +x
echo $(date)
