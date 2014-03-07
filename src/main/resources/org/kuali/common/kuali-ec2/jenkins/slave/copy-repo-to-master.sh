#!/bin/sh

echo $(date)
set -xe

# The trailing slash is significant here
REPO=/root/.m2/repository
SRC=$REPO/
DST=root@${jenkins.master}:$REPO
EXCLUDEFILE=/usr/share/ec2slave/rsync-excludes

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude-from $EXCLUDEFILE

set +x
echo $(date)
