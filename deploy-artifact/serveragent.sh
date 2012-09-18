#!/bin/sh

mvn process-resources -Pprivate -Ddp.groupId=com.appdynamics -Ddp.version=3.4.0.2 -Ddp.artifactId=server-agent -Ddp.file=/Users/jeffcaddel/Downloads/AppServerAgent.zip
