#!/bin/sh

date
set -xe

REPO=/root/.m2/repository
# The trailing slash is significant here
SRC=$REPO/
DST=root@$JENKINS_MASTER:$REPO

# Make the rsync call
rsync --archive --stats -v $SRC $DST --exclude='**/org/kuali/**' --exclude='**/SNAPSHOT*.*'

set +x
date
