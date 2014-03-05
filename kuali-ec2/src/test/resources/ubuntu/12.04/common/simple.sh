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

# generic functions
function check_not_blank {
  if ! [ -n "$2" ]; then 
    echo $1 cannot be blank
    show_usage
  fi
}

# module specific functions
function show_usage {
  echo
  echo requires BASEDIR JDK NEXUS_PASSWORD
  echo usage: install_java.sh basedir jdk6/jdk7 nexus_password [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
  check_not_blank JDK $JDK
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
}

function install_jdk {

  # Extract the jdk level from the JDK variable
  JDK_LEVEL=${JDK:3:1}
  check_not_blank JDK_LEVEL $JDK_LEVEL

  # Extract a specific jdk version based on the jdk level
  JDK_VERSION=$(eval echo \${JDK${JDK_LEVEL}_VERSION})
  check_not_blank JDK_VERSION $JDK_VERSION

  JDK_GROUP_ID=com/oracle
  JDK_ARTIFACT_ID=$JDK
  JDK_CLASSIFIER=linux-x64

  JDK_UNZIP_DIR=$JDK_ARTIFACT_ID-$JDK_VERSION
  JDK_ZIP_FILE=$JDK_ARTIFACT_ID-$JDK_VERSION-$JDK_CLASSIFIER.zip
  JDK_BASEDIR=/usr/java

  NEXUS_URL=http://nexus.kuali.org/content/groups/developer
  NEXUS_JDK_LOCATION=$JDK_GROUP_ID/$JDK_ARTIFACT_ID/$JDK_VERSION
  NEXUS_USER=developer
  NEXUS_JDK_DOWNLOAD_FILE=$DOWNLOADS/$JDK_ZIP_FILE
  
  # Directory for the JDK download
  DOWNLOADS=$BASEDIR/target/downloads/jdk
  echo "clean     -> $DOWNLOADS"
  rm -rf $DOWNLOADS; mkdir -p $DOWNLOADS

  URL=$NEXUS_URL/$NEXUS_JDK_LOCATION/$JDK_ZIP_FILE
  OUTPUT_FILE=$DOWNLOADS/$JDK_ZIP_FILE
  echo "download  -> $URL"
  wget $QUIET --user $NEXUS_USER --password $NEXUS_PASSWORD $URL --output-document $OUTPUT_FILE
  echo "to        -> $OUTPUT_FILE"

  # Make sure the JDK and the symbolic link are both gone
  JDK_TARGET=$JDK_BASEDIR/$JDK_UNZIP_DIR
  JDK_LINK=$JDK_BASEDIR/$JDK_ARTIFACT_ID
  echo "install   -> $JDK_LINK -> $JDK_TARGET"
  rm -rf $JDK_LINK $JDK_TARGET

  # Unpack the JDK into /usr/java
  unzip $QUIET -o $DOWNLOADS/$JDK_ZIP_FILE -d $JDK_BASEDIR

  # Create a symbolic link for /usr/java/jdk7 -> /usr/java/jdk7-1.7.0-u51
  ln -s $JDK_TARGET $JDK_LINK
  
}

# module specific variables
BASEDIR=${1-$BASEDIR}
JDK=${2-$JDK}
NEXUS_PASSWORD=${3-$NEXUS_PASSWORD}
QUIET=${4-$QUIET}

# Make sure we have what we need to continue
check_args

# Change these as new versions become available
JDK6_VERSION=1.6.0-u45
JDK7_VERSION=1.7.0-u51

install_jdk

