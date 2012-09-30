#!/bin/sh

VERSION=1.6.0-u35
ARGS="$ARGS process-resources"
ARGS="$ARGS -Pprivate"
ARGS="$ARGS -Ddp.groupId=com.oracle"
ARGS="$ARGS -Ddp.version=$VERSION"
ARGS="$ARGS -Ddp.artifactId=jdk6"
ARGS="$ARGS -Ddp.classifier=linux-x64"
ARGS="$ARGS -Ddp.file=/root/jdk6/jdk$VERSION.zip"
mvn $ARGS
