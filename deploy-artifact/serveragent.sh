#!/bin/sh

mvn process-resources -Pprivate -Ddp.groupId=com.appdynamics -Ddp.version=3.4.2.0 -Ddp.artifactId=server-agent -Ddp.file=/Users/jeffcaddel/Downloads/AppServerAgent.zip
