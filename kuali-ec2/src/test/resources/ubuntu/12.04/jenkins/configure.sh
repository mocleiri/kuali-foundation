#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


TOMCAT7=tomcat7
TOMCAT6=tomcat6
TOMCAT=$TOMCAT7

JENKINS_VERSION=1.532.2

TOMCAT_DIR=/var/lib/$TOMCAT
TOMCAT_CLEANUP=/var/lib/$TOMCAT/bin/cleanup.sh
TOMCAT_ROOT=$TOMCAT_DIR/webapps/ROOT
TOMCAT_ROOT_WAR=$TOMCAT_DIR/webapps/ROOT.war

service $TOMCAT stop
rm -rf $TOMCAT_ROOT $TOMCAT_ROOT_WAR
wget http://maven.kuali.org/external/org/jenkins/jenkins/$JENKINS_VERSION/jenkins-$JENKINS_VERSION.war --output-document $TOMCAT_ROOT_WAR
chown $TOMCAT:$TOMCAT $TOMCAT_ROOT_WAR
$TOMCAT_CLEANUP
service $TOMCAT start
