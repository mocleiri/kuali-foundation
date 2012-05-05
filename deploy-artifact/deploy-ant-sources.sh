#!/bin/sh

ARGS="$ARGS -Ddp.groupId=org.apache.ant"
ARGS="$ARGS -Ddp.artifactId=ant"
ARGS="$ARGS -Ddp.version=1.8.2"
ARGS="$ARGS -Ddp.classifier=sources"
ARGS="$ARGS -Ddp.file=/root/.ant/apache-ant-1.8.2-sources.jar"
mvn process-resources -Ppublic $ARGS
