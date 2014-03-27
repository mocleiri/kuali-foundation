#!/bin/sh

date
set -xe

REPO=/root/.m2/repository
# The trailing slash is significant here
SRC=$REPO/
JENKINS_MASTER=JENKINS_MASTER
DST=root@$JENKINS_MASTER:$REPO
EXCLUDEFILE=/usr/share/ec2slave/rsync.excludes

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude-from $EXCLUDEFILE

set +x
date
