#!/bin/sh
mvn process-resources -Ppublic -Ddb.groupId=org.apache.tomcat -Ddb.version=6.0.33 -Ddb.artifactId=apache-tomcat -Ddb.file=/home/ubuntu/.tomcat/apache-tomcat-6.0.33.zip
mvn process-resources -Ppublic -Ddb.groupId=org.apache.tomcat -Ddb.version=7.0.22 -Ddb.artifactId=apache-tomcat -Ddb.file=/home/ubuntu/.tomcat/apache-tomcat-7.0.22.zip
