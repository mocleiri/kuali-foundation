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


JENKINS_VERSION=1.532.2
TOMCAT=tomcat7
JENKINS_HOME=/home/$TOMCAT/.jenkins

function install_jenkins {
  
  TOMCAT_DIR=/var/lib/$TOMCAT
  TOMCAT_CLEANUP=/usr/share/$TOMCAT/bin/cleanup.sh
  TOMCAT_ROOT=$TOMCAT_DIR/webapps/ROOT
  TOMCAT_ROOT_WAR=$TOMCAT_DIR/webapps/ROOT.war
  JENKINS_URL=http://maven.kuali.org/external/org/jenkins/jenkins/$JENKINS_VERSION/jenkins-$JENKINS_VERSION.war

  rm -rf $TOMCAT_ROOT $TOMCAT_ROOT_WAR $JENKINS_HOME
  curl $JENKINS_URL --output $TOMCAT_ROOT_WAR
  chown $TOMCAT:$TOMCAT $TOMCAT_ROOT_WAR
  $TOMCAT_CLEANUP
  
}

function install_plugin {

	PLUGIN_DOWNLOADS=https://updates.jenkins-ci.org/download/plugins
	PLUGIN_NAME=$1
	PLUGIN_VERSION=$2
	PLUGIN_URL=$PLUGIN_DOWNLOADS/$PLUGIN_NAME/$PLUGIN_VERSION/$PLUGIN_NAME.hpi
	echo $PLUGIN_URL
	PLUGIN_DIR=$JENKINS_HOME/plugins
	PLUGIN_FILE=$PLUGIN_DIR/$PLUGIN_NAME.jpi
	curl $PLUGIN_URL --location --create-dirs --output $PLUGIN_FILE
	chown -R $TOMCAT:$TOMCAT $PLUGIN_DIR  
  
}

service $TOMCAT stop
install_jenkins
install_plugin ec2 1.21
service $TOMCAT start
