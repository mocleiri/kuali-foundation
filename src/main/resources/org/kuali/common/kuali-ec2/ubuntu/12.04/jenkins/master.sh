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

function check_not_blank {
  if [ ! -n "$2" ]; then 
    echo $1 cannot be blank
    show_usage
  fi
}

function show_usage {
  echo
  echo requires BASEDIR
  echo usage: master.sh basedir [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
}

function install_jenkins {
  
  echo "install   -> jenkins:$JENKINS_VERSION"
  TOMCAT_DIR=/var/lib/$TOMCAT
  TOMCAT_CLEANUP=/usr/share/$TOMCAT/bin/cleanup.sh
  TOMCAT_WEBAPPS=$TOMCAT_DIR/webapps
  TOMCAT_ROOT=$TOMCAT_WEBAPPS/ROOT
  TOMCAT_ROOT_WAR=$TOMCAT_WEBAPPS/ROOT.war
  JENKINS_URL=http://maven.kuali.org/external/org/jenkins/jenkins/$JENKINS_VERSION/jenkins-$JENKINS_VERSION.war

  echo "download  -> jenkins:war - [$JENKINS_URL]"
  rm -rf $TOMCAT_WEBAPPS/* $JENKINS_HOME
  curl $JENKINS_URL --silent --output $TOMCAT_ROOT_WAR
  chown $TOMCAT:$TOMCAT $TOMCAT_ROOT_WAR
  $TOMCAT_CLEANUP
  
}

function install_plugin {

  PLUGIN_DOWNLOADS=https://updates.jenkins-ci.org/download/plugins
  PLUGIN_NAME=$1
  PLUGIN_VERSION=$2
  echo "install   -> jenkins:plugin:$PLUGIN_NAME:$PLUGIN_VERSION"
  PLUGIN_URL=$PLUGIN_DOWNLOADS/$PLUGIN_NAME/$PLUGIN_VERSION/$PLUGIN_NAME.hpi
  PLUGIN_DIR=$JENKINS_HOME/plugins
  PLUGIN_FILE=$PLUGIN_DIR/$PLUGIN_NAME.jpi
  curl $PLUGIN_URL --silent --location --create-dirs --output $PLUGIN_FILE
  touch $PLUGIN_FILE.pinned 
  
}

function install_plugins {

  # supported plugins (in alphabetical order)
  install_plugin build-pipeline-plugin 1.4.2
  install_plugin cas-plugin            1.1.1
  install_plugin credentials           1.10
  install_plugin cucumber-reports      0.0.22 
  install_plugin dashboard-view        2.9.2
  install_plugin depgraph-view         0.11
  install_plugin ec2                   1.21
  install_plugin email-ext             2.37.2
  install_plugin git                   2.0.3
  install_plugin git-client            1.6.3
  install_plugin jobConfigHistory      2.5
  install_plugin jquery                1.7.2-1 
  install_plugin jquery-ui             1.0.2
  install_plugin mailer                1.8
  install_plugin mask-passwords        2.7.2
  install_plugin maven-plugin          2.1
  install_plugin nested-view           1.14
  install_plugin next-build-number     1.1
  install_plugin node-iterator-api     1.2
  install_plugin parameterized-trigger 2.22
  install_plugin scm-api               0.2
  install_plugin ssh-credentials       1.6.1
  install_plugin token-macro           1.10

  chown -R $TOMCAT:$TOMCAT $TOMCAT_HOME  
  
}

function configure_tomcat_user {

  echo "configure -> $TOMCAT:user"
  rm -rf $TOMCAT_HOME/.gnupg $TOMCAT_HOME/.ssh $TOMCAT_HOME/.m2 $TOMCAT_HOME/.subversion
  cp -R /root/.gnupg       $TOMCAT_HOME
  cp -R /root/.ssh         $TOMCAT_HOME
  cp -R /root/.m2          $TOMCAT_HOME
  cp -R /root/.subversion  $TOMCAT_HOME
  
  # Sets up Java + Maven + Jenkins Master
  cp /root/.bashrc       $TOMCAT_HOME/.bash_profile
  cp /root/.bash_aliases $TOMCAT_HOME/.bash_aliases
  
  # Make sure everything is owned by tomcat
  chown -R $TOMCAT:$TOMCAT $TOMCAT_HOME
  
}

function configure_jenkins {
  
  echo "configure -> jenkins:master"
  
  MASTER_SRC_DIR=$BASEDIR/${project.groupId.path}/${project.artifactId}/jenkins/master

  # point jenkins at the update center for LTS
  UPDATE_CENTER_SRC=$MASTER_SRC_DIR/hudson.model.UpdateCenter.xml
  cp $UPDATE_CENTER_SRC $JENKINS_HOME
  
}

BASEDIR=${1-$BASEDIR}
QUIET=${2-$QUIET}

JENKINS_VERSION=1.532.2
TOMCAT=tomcat7
TOMCAT_HOME=/home/$TOMCAT
JENKINS_HOME=$TOMCAT_HOME/.jenkins

echo "stop      -> $TOMCAT:service"
service $TOMCAT stop > /dev/null 2>&1
configure_tomcat_user
install_jenkins
install_plugins
configure_jenkins
echo "start     -> $TOMCAT:service"
service $TOMCAT start > /dev/null 2>&1
