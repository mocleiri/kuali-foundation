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


function install_packages {
  echo "install   -> custom packages"
  PACKAGES="subversion git graphviz firefox"
  apt-get install $PACKAGES -y > /dev/null 2>&1
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
  gpg --allow-secret-key-import --import $GPG_KEY > /dev/null 2>&1
  
  # setup maven
  rm -rf /root/.m2; mkdir -p /root/.m2;  mv /root/.ssh/settings.xml /root/.m2
  
}

MAVEN_BASEDIR=/usr/maven
QUIET=${1-""}

configure_java
configure_secrets
install_packages
install_maven 3.0.5 30
install_maven 3.1.0 31
install_maven 3.2.1 32
install_default_maven 32
