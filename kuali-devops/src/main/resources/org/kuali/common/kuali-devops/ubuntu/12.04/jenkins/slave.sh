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


function execute_quietly {
  COMMAND=$1
  if [ "$QUIET" = "-qq" ]; then
    $COMMAND > /dev/null 2>&1
  else
    $COMMAND
  fi
}

function check_not_blank {
  if [ ! -n "$2" ]; then 
    echo $1 cannot be blank
    show_usage
  fi
}

function show_usage {
  echo
  echo requires BASEDIR
  echo usage: slave.sh basedir [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
}

function slave_shutdown_scripting {
  
  echo "configure -> jenkins:slave:shutdown:rsync"
  
  EC2SLAVE_DST_DIR=/usr/share/ec2slave
  rm -rf $EC2SLAVE_DST_DIR; mkdir -p $EC2SLAVE_DST_DIR;
  
  EC2SLAVE_SRC_DIR=$BASEDIR/${project.groupId.path}/${project.artifactId}/ubuntu/12.04/jenkins/slave
  
  EC2SLAVE_SRC=$EC2SLAVE_SRC_DIR/ec2slave
  EC2SLAVE_DST=/etc/init.d/ec2slave
  cp $EC2SLAVE_SRC $EC2SLAVE_DST

  EXCLUDES_SRC=$EC2SLAVE_SRC_DIR/rsync.excludes
  EXCLUDES_DST=$EC2SLAVE_DST_DIR/rsync.excludes
  cp $EXCLUDES_SRC $EXCLUDES_DST

  COPY_REPO_SRC=$EC2SLAVE_SRC_DIR/copy_repo_to_master.sh
  COPY_REPO_DST=$EC2SLAVE_DST_DIR/copy_repo_to_master.sh
  cp $COPY_REPO_SRC $COPY_REPO_DST
  
  chmod 755 $COPY_REPO_DST

  execute_quietly "update-rc.d -f ec2slave remove"
  execute_quietly "update-rc.d ec2slave defaults"

}

function install_mysql_server {

  echo "remove    -> mysql-server"
  # Remove all traces of MySQL
  execute_quietly "apt-get remove mysql-server -y"
  execute_quietly "apt-get purge  mysql-server -y"
  execute_quietly "apt-get autoremove -y"

  echo "install   -> mysql-server"
  # Setup the MySQL install so it won't prompt for a password
  MYSQL_ROOT_PASSWORD=password
  debconf-set-selections <<< "mysql-server mysql-server/root_password password $MYSQL_ROOT_PASSWORD"
  debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $MYSQL_ROOT_PASSWORD"
   
  # Install MySQL with no prompts  
  execute_quietly "apt-get install mysql-server -y"
  
  # Remove the password for the root user
  mysqladmin -u root -p$MYSQL_ROOT_PASSWORD password ""
   
}

function copy_repo_from_master {

  M2_REPO=/root/.m2/repository
  mkdir -p $M2_REPO
  echo "copy      -> maven repo"
  echo "rsync     -> start -> $(date)"
  execute_quietly "rsync -av --stats root@$JENKINS_MASTER:$M2_REPO/ $M2_REPO" 
  echo "rsync     -> stop  -> $(date)"
  
}

function build_maven_project {
  cd /mnt/$1; svn up; mvn -f /mnt/$1/pom.xml clean install -DskipTests -T C1
}

function build_maven_projects {
  build_maven_project kuali-util
  build_maven_project kuali-core
  build_maven_project kuali-httpclient
  build_maven_project kuali-dns
  build_maven_project kuali-aws
  build_maven_project kuali-devops
  build_maven_project wagon-maven-plugin
}

function copy_repo_from_amazon {

  M2_REPO=/root/.m2/repository
  mkdir -p $M2_REPO
  echo "copy      -> amazon repo"
  echo "start     -> $(date)"
  MAVEN_POM="$BASEDIR/META-INF/maven/${project.groupId}/${project.artifactId}/pom.xml"
  MAVEN_ARGS="$MAVEN_ARGS -f $MAVEN_POM"
  MAVEN_ARGS="$MAVEN_ARGS initialize -Pupdate"
  MAVEN_ARGS="$MAVEN_ARGS -Dorg.slf4j.simpleLogger.log.org.kuali.maven.wagon=warn"
  MAVEN="mvn $MAVEN_ARGS"
  echo $MAVEN
  $MAVEN
  echo "stop      -> $(date)"
  
}

BASEDIR=${1-BASEDIR}
QUIET=${2-$QUIET}
JENKINS_MASTER=${jenkins.master}

check_args

slave_shutdown_scripting
install_mysql_server
copy_repo_from_master
build_maven_projects
copy_repo_from_amazon
