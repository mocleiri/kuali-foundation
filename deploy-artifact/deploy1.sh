#!/bin/sh
mvn process-resources -Pprivate -Ddeployment.groupId=com.oracle -Ddeployment.version=10.2.0.3.0 -Ddeployment.artifactId=ojdbc14 -Ddeployment.file=/home/ubuntu/.oracle/10.2.0.3.0/ojdbc14.jar
