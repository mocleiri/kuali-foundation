#!/bin/sh

ARGS="$ARGS process-resources"
ARGS="$ARGS -Ps3private"
ARGS="$ARGS -Ddp.groupId=com.oracle"
ARGS="$ARGS -Ddp.version=1.7.0-u07"
ARGS="$ARGS -Ddp.artifactId=jdk7"
ARGS="$ARGS -Ddp.classifier=linux-x64"
ARGS="$ARGS -Ddp.file=/Users/jeffcaddel/Downloads/jdk1.7.0-u07.zip"
mvn process-resources -Ps3private -Ddp.groupId=com.oracle -Ddp.version=1.7.0-u07 -Ddp.artifactId=jdk7 -Ddp.file=/Users/jeffcaddel/Downloads/jdk1.7.0-u07.zip
