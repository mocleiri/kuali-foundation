#!/bin/bash
M2_REPO=~/.m2/repository
GROUPID=org/kuali/common/util
ARTIFACTID=crlf
CLASSIFIER=jar-with-dependencies
VERSION=${project.version}
JAR=$M2_REPO/$GROUPID/$ARTIFACTID/$VERSION/$ARTIFACTID-$VERSION-$CLASSIFIER.jar

java -jar $JAR ${@}