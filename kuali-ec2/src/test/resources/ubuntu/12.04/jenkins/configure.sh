#!/bin/bash

TOMCAT7=tomcat7
TOMCAT6=tomcat6
TOMCAT=$TOMCAT7

TOMCAT_DIR=/var/lib/$TOMCAT
TOMCAT_ROOT=$TOMCAT_DIR/webapps/ROOT

service $TOMCAT stop
