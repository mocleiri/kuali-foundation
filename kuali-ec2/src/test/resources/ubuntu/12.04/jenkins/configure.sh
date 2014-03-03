#!/bin/bash

TOMCAT7=tomcat7
TOMCAT6=tomcat6
TOMCAT=$TOMCAT7

JENKINS_VERSION=1.532.2

TOMCAT_DIR=/var/lib/$TOMCAT
TOMCAT_ROOT=$TOMCAT_DIR/webapps/ROOT
TOMCAT_ROOT_WAR=$TOMCAT_DIR/webapps/ROOT.war

service $TOMCAT stop

rm -rf $TOMCAT_ROOT TOMCAT_ROOT_WAR

wget http://maven.kuali.org/external/org/jenkins/jenkins/$JENKINS_VERSION/jenkins-$JENKINS_VERSION.war -O $TOMCAT_ROOT_WAR

service $TOMCAT start
