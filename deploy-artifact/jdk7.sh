#!/bin/sh

VERSION=1.7.0-u07
ARGS="$ARGS process-resources"
ARGS="$ARGS -Ps3private"
ARGS="$ARGS -Ddp.groupId=com.oracle"
ARGS="$ARGS -Ddp.version=$VERSION"
ARGS="$ARGS -Ddp.artifactId=jdk7"
ARGS="$ARGS -Ddp.classifier=linux-x64"
ARGS="$ARGS -Ddp.file=/Users/jeffcaddel/Downloads/jdk7/jdk$VERSION.zip"
mvn $ARGS
