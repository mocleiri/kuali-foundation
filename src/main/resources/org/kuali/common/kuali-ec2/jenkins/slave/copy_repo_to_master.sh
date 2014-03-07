#!/bin/sh

echo $(date)
set -xe

REPO=/root/.m2/repository
# The trailing slash is significant here
SRC=$REPO/
JENKINS_MASTER=${jenkins.master}
DST=root@$JENKINS_MASTER:$REPO
EXCLUDEFILE=/usr/share/ec2slave/rsync_excludes

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude-from $EXCLUDEFILE

set +x
echo $(date)
