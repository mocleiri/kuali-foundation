#!/bin/sh
mvn process-resources -Ppublic -Ddp.groupId=org.apache.tomcat -Ddp.version=6.0.33 -Ddp.artifactId=apache-tomcat -Ddp.file=/home/ubuntu/.tomcat/apache-tomcat-6.0.33.zip
mvn process-resources -Ppublic -Ddp.groupId=org.apache.tomcat -Ddp.version=7.0.22 -Ddp.artifactId=apache-tomcat -Ddp.file=/home/ubuntu/.tomcat/apache-tomcat-7.0.22.zip
