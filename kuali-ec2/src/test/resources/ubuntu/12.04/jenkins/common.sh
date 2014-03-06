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
  echo requires SVN_PASSWORD  QUIET
  echo usage: common.sh svn_password [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank SVN_PASSWORD $SVN_PASSWORD
}

function execute_quietly {
  COMMAND=$1
  if [ "$QUIET" = "-qq" ]; then
    $COMMAND > /dev/null 2>&1
  else
    $COMMAND
  fi
}

function install_packages {
  echo "install   -> custom packages"
  PACKAGES="subversion git graphviz firefox"
  APT_GET="apt-get install $PACKAGES -y"
  execute_quietly "$APT_GET"
}

function install_maven {
  MAVEN_VERSION=$1
  MAVEN_ABBR=$2

  echo "install   -> maven $MAVEN_VERSION"

  MAVEN_ARTIFACT_ID=apache-maven
  MAVEN_ZIP=$MAVEN_ARTIFACT_ID-$MAVEN_VERSION-bin.zip
  MAVEN_URL=http://search.maven.org/remotecontent?filepath=org/apache/maven/apache-maven/$MAVEN_VERSION/$MAVEN_ZIP
  MAVEN_DIR=$MAVEN_BASEDIR/$MAVEN_ARTIFACT_ID-$MAVEN_VERSION
  MAVEN_FILE=$MAVEN_BASEDIR/$MAVEN_ZIP
  curl $MAVEN_URL --silent --location --create-dirs --output $MAVEN_FILE
  MAVEN_TARGET=$MAVEN_DIR
  MAVEN_LINK=$MAVEN_BASEDIR/mvn$MAVEN_ABBR
  MAVEN_USR_BIN=/usr/bin/mvn$MAVEN_ABBR

  rm -rf $MAVEN_DIR $MAVEN_LINK $MAVEN_USR_BIN
  unzip -qq $MAVEN_FILE -d $MAVEN_BASEDIR
  chmod -R 755 $MAVEN_DIR
  ln -s $MAVEN_TARGET $MAVEN_LINK
  ln -s $MAVEN_BASEDIR/mvn$MAVEN_ABBR/bin/mvn $MAVEN_USR_BIN
}

function install_default_maven {
  MAVEN_ABBR=$1

  echo "install   -> default maven mvn$MAVEN_ABBR"

  MAVEN_TARGET=$MAVEN_BASEDIR/mvn$MAVEN_ABBR/bin/mvn
  MAVEN_USR_BIN=/usr/bin/mvn

  rm -rf $MAVEN_USR_BIN
  ln -s $MAVEN_TARGET $MAVEN_USR_BIN
}

# Make sure /usr/bin/java points to JDK7  
function configure_java {

  echo "configure -> default java"
  
  # the default /root/.bashrc that ships with 12.04 automatically imports /root/.bash_aliases
  ROOT_ALIASES=/root/.bash_aliases
  echo "JAVA_HOME=/usr/java/jdk7"                   >  $ROOT_ALIASES
  echo "PATH=\$JAVA_HOME/bin:$PATH:."               >> $ROOT_ALIASES
  echo "MAVEN_OPTS=\"-Xmx2g -XX:MaxPermSize=256m\"" >> $ROOT_ALIASES
  echo "export JAVA_HOME PATH MAVEN_OPTS"           >> $ROOT_ALIASES

  # remove whatever's at /usr/bin/java and replace it with a symbolic link to jdk7
  rm -rf /usr/bin/java
  ln -s /usr/java/jdk7/bin/java /usr/bin/java
  
}

function configure_secrets {

  # Extract the EC2 private keys and the GPG key into the .ssh directory
  echo "configure -> secrets"
  ZIP=/root/.ssh/secrets.zip
  unzip $QUIET -o $ZIP -d /root

  # Setup GPG
  GPG_KEY=/root/.ssh/private.key.gpg
  rm -rf /root/.gnupg
  GPG="gpg --allow-secret-key-import --import $GPG_KEY"
  execute_quietly "$GPG"
  
  # setup maven
  rm -rf /root/.m2; mkdir -p /root/.m2;  mv /root/.ssh/settings.xml /root/.m2
  
}

function configure_subversion {

  echo "configure -> subversion"
  SVN_BASEDIR=/root/.subversion
  SVN_SERVERS=$SVN_BASEDIR/servers
  
  rm -rf $SVN_BASEDIR; mkdir -p $SVN_BASEDIR
  echo "[global]"                      >  $SVN_SERVERS
  echo "store-plaintext-passwords=yes" >> $SVN_SERVERS
  
  SVN_MESSAGE="Automated auth check"
  SVN_TOUCH_URL="https://svn.kuali.org/repos/foundation/sandbox/kuali-devops/temp/nightly/touch"
  SVN_AUTH="--username=$SVN_USERNAME --password=$SVN_PASSWORD"
  SVN_CREATE="svn mkdir --parents $SVN_AUTH -m '$SVN_MESSAGE' $SVN_TOUCH_URL"
  echo $SVN_CREATE
  SVN_DELETE="svn rm -m '$SVN_MESSAGE' $SVN_TOUCH_URL"
  
  $SVN_CREATE
  $SVN_DELETE
  
}

MAVEN_BASEDIR=/usr/maven
SVN_PASSWORD=$1
QUIET=${2-""}

check_args

SVN_USERNAME=jcaddel

configure_java
configure_secrets
install_packages
install_maven 3.0.5 30
install_maven 3.1.0 31
install_maven 3.2.1 32
install_default_maven 32
configure_subversion
