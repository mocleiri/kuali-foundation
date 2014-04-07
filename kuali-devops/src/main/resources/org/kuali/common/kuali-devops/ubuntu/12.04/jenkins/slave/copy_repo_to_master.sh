#!/bin/sh

date
set -xe

REPO=/root/.m2/repository
# The trailing slash is significant here
SRC=$REPO/

# This gets replaced by a real value by the process that creates the AMI
# Cannot rely on an environment variable provided by the master because this script only executes when the instance is shutting down
JENKINS_MASTER=FILL_ME_IN_PLEASE
DST=root@$JENKINS_MASTER:$REPO

# Make the rsync call
# Ignore all SNAPSHOT's and Kuali artifacts
# Kuali artifacts get pre-cached on the build slaves by a different process
rsync --archive --stats --verbose $SRC $DST --exclude='**/org/kuali/**' --exclude='**/SNAPSHOT*.*'

set +x
date
