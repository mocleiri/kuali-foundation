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

function show_usage {
  echo requires GPG_PASSPHRASE JDK 
  echo usage: install_java.sh gpg_passphrase jdk6/jdk7 [runtype<local|anything>] [quiet]
  exit 1
}

function check_args {
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  check_not_blank JDK $JDK
}

function get_jdk_version {
  echo "JDK${JDK_LEVEL}_VERSION"
}

function install_jdk {

  # Extract the jdk level from the JDK variable
  JDK_LEVEL=${JDK:3:1}
  check_not_blank JDK_LEVEL $JDK_LEVEL
  
  JDK_VERSION_VAR=$(get_jdk_version)
  
  # Extract a specific jdk version based on the jdk level
  JDK_VERSION=${!JDK_VERSION_VAR}
  check_not_blank JDK_VERSION $JDK_VERSION

  JDK_GROUP_ID=com/oracle
  JDK_ARTIFACT_ID=$JDK
  JDK_CLASSIFIER=linux-x64

  JDK_UNZIP_DIR=$JDK_ARTIFACT_ID-$JDK_VERSION
  JDK_ZIP_FILE=$JDK_ARTIFACT_ID-$JDK_VERSION-$JDK_CLASSIFIER.zip
  JDK_BASEDIR=/usr/java
  mkdir -p $JDK_BASEDIR

  NEXUS_URL=http://nexus.kuali.org/content/groups/developer
  NEXUS_JDK_LOCATION=$JDK_GROUP_ID/$JDK_ARTIFACT_ID/$JDK_VERSION
  NEXUS_USER=developer
  
  URL=$NEXUS_URL/$NEXUS_JDK_LOCATION/$JDK_ZIP_FILE
  OUTPUT_FILE=$JDK_BASEDIR/$JDK_ZIP_FILE
  echo "download  -> $URL"
  wget $QUIET --user $NEXUS_USER --password $NEXUS_PASSWORD $URL --output-document $OUTPUT_FILE

  # Make sure the JDK and the symbolic link are both gone
  JDK_TARGET=$JDK_BASEDIR/$JDK_UNZIP_DIR
  JDK_LINK=$JDK_BASEDIR/$JDK_ARTIFACT_ID
  echo "install   -> $JDK_LINK - [$JDK_TARGET]"
  rm -rf $JDK_LINK $JDK_TARGET

  # Unpack the JDK into /usr/java
  unzip $QUIET -o $OUTPUT_FILE -d $JDK_BASEDIR

  # Create the symbolic link /usr/java/jdk7 -> /usr/java/jdk7-1.7.0-u51
  ln -s $JDK_TARGET $JDK_LINK
  
  TOOLS_JAR=$JDK_LINK/lib/tools.jar
  TOOLS_JAR_COPY=$JDK_LINK/jre/lib/ext/tools.jar
  echo "copy      -> $TOOLS_JAR_COPY"
  cp $TOOLS_JAR $TOOLS_JAR_COPY
  
}

MY_DIR="$( cd "$( dirname "$0" )" && pwd )"
source $MY_DIR/functions.sh

# module specific variables
GPG_PASSPHRASE=${1-$GPG_PASSPHRASE}
JDK=${2-$JDK}
RUNTYPE=${3-local}
QUIET=${4-$QUIET}

# Make sure we have what we need to continue
check_args

echo "decrypt   -> nexus password"
GPG_FILE=$MY_DIR/nexus.password.gpg
NEXUS_PASSWORD=$(decrypt_password $GPG_FILE)

if [ $RUNTYPE == "local" ]; then 
  # For local mode when running the script directly
  JDK6_VERSION=1.6.0-u45
  JDK7_VERSION=1.7.0-u51
else
  # For when the script is invoked via maven
  JDK6_VERSION=${jdk6.version}
  JDK7_VERSION=${jdk7.version}
fi

check_not_blank JDK6_VERSION $JDK6_VERSION
check_not_blank JDK7_VERSION $JDK7_VERSION

install_jdk
