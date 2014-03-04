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
TOMCAT_HOME=/home/$TOMCAT
JENKINS_HOME=$TOMCAT_HOME/.jenkins

function install_jenkins {
  
  TOMCAT_DIR=/var/lib/$TOMCAT
  TOMCAT_CLEANUP=/usr/share/$TOMCAT/bin/cleanup.sh
  TOMCAT_ROOT=$TOMCAT_DIR/webapps/ROOT
  TOMCAT_ROOT_WAR=$TOMCAT_DIR/webapps/ROOT.war
  JENKINS_URL=http://maven.kuali.org/external/org/jenkins/jenkins/$JENKINS_VERSION/jenkins-$JENKINS_VERSION.war

  echo "download -> $JENKINS_URL"
  rm -rf $TOMCAT_ROOT $TOMCAT_ROOT_WAR $JENKINS_HOME
  curl $JENKINS_URL --silent --output $TOMCAT_ROOT_WAR
  chown $TOMCAT:$TOMCAT $TOMCAT_ROOT_WAR
  $TOMCAT_CLEANUP
  
}

function install_plugin {

	PLUGIN_DOWNLOADS=https://updates.jenkins-ci.org/download/plugins
	PLUGIN_NAME=$1
	PLUGIN_VERSION=$2
	PLUGIN_URL=$PLUGIN_DOWNLOADS/$PLUGIN_NAME/$PLUGIN_VERSION/$PLUGIN_NAME.hpi
	PLUGIN_DIR=$JENKINS_HOME/plugins
	PLUGIN_FILE=$PLUGIN_DIR/$PLUGIN_NAME.jpi
    echo "install  -> $PLUGIN_URL"
	curl $PLUGIN_URL --silent --location --create-dirs --output $PLUGIN_FILE
	touch $PLUGIN_FILE.pinned 
	chown -R $TOMCAT:$TOMCAT $TOMCAT_HOME  
  
}

echo "stop     -> $TOMCAT"
service $TOMCAT stop > /dev/null 2>&1
echo "install  -> Jenkins $JENKINS_VERSION"
install_jenkins
install_plugin node-iterator-api 1.2
install_plugin ec2               1.21
install_plugin cas-plugin        1.1.1
install_plugin git               2.0.3
install_plugin git-client        1.6.3
install_plugin credentials       1.10
install_plugin scm-api           0.2
install_plugin ssh-credentials   1.6.1
install_plugin jobConfigHistory  2.5
install_plugin maven-plugin      2.1
install_plugin mailer            1.8
echo "start    -> $TOMCAT"
service $TOMCAT start > /dev/null 2>&1
