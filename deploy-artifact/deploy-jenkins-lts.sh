#!/bin/sh

#
# This script uploads the latest LTS Jenkins war file to S3
#
# Browse for it here -> http://shrub.appspot.com/maven.kuali.org/external/org/jenkins/jenkins/
#
#
# Change the version # to match whatever the latest LTS version is
# http://mirrors.jenkins-ci.org/war-stable/
#
VERSION=1.532.2

GROUP_ID=org.jenkins
ARTIFACT_ID=jenkins
FILE=/Users/jcaddel/Downloads/jenkins-$VERSION.war

ARGS="$ARGS -Ddp.groupId=$GROUP_ID"
ARGS="$ARGS -Ddp.version=$VERSION"
ARGS="$ARGS -Ddp.artifactId=$ARTIFACT_ID"
ARGS="$ARGS -Ddp.file=$FILE"

mvn process-resources -Ppublic $ARGS
