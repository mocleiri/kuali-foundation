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
  echo requires BASEDIR TOMCAT JDK MAX_HEAP MAX_PERM QUIET
  echo usage: install_tomcat.sh basedir tomcat6/tomcat7 jdk6/jdk7 max_heap max_perm [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
  check_not_blank TOMCAT $TOMCAT
  check_not_blank JDK $JDK
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
}

function tomcat_purge {

  TOMCAT_PURGE="libtcnative-1 tomcat6-common tomcat6 tomcat7"
  echo "purge     -> $TOMCAT_PURGE"
  apt-get $QUIET -y purge $TOMCAT_PURGE > /dev/null 2>&1
  apt-get $QUIET -y autoremove > /dev/null 2>&1
  rm -rf /var/lib/tomcat6 /var/lib/tomcat7
  
}

# Install Tomcat
function install_tomcat {
  
  tomcat purge
  
  if [ $TOMCAT_VERSION == "6" ]; then
    echo "install   -> $TOMCAT-common libtcnative-1 $TOMCAT"
    apt-get $QUIET -y install $TOMCAT-common libtcnative-1 $TOMCAT > /dev/null 2>&1
  else
    echo "install   -> libtcnative-1 $TOMCAT"
    apt-get $QUIET -y install libtcnative-1 $TOMCAT > /dev/null 2>&1
  fi
  
  service $TOMCAT stop > /dev/null 2>&1
  echo "configure -> $TOMCAT"
  echo "TOMCAT_USER=$TOMCAT_USER" > $TOMCAT_OPT_FILE
  echo "TOMCAT_GROUP=$TOMCAT_GROUP" >> $TOMCAT_OPT_FILE
  echo "JAVA_OPTS=$JAVA_OPTS" >> $TOMCAT_OPT_FILE
  echo "JAVA_HOME=$JAVA_HOME" >> $TOMCAT_OPT_FILE

  WEBXML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/web.xml
  SERVER=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/server.xml

  cp $WEBXML $TOMCAT_CONF_FILE_DIR/web.xml
  cp $SERVER $TOMCAT_CONF_FILE_DIR/server.xml

  TOMCAT_LOGS=/var/lib/$TOMCAT/logs
  TOMCAT_WEBAPPS=/var/lib/$TOMCAT/webapps
  TOMCAT_BIN=/usr/share/$TOMCAT/bin
  TOMCAT_CLEANUP=$TOMCAT_BIN/cleanup.sh
  USR_BIN_CLEANUP=/usr/bin/cleanup.sh
  JSPS=$BASEDIR/src/main/resources/apache-tomcat/jsps/*.jsp

  rm -rf $TOMCAT_WEBAPPS/* $TOMCAT_LOGS/* /var/lib/tomcat6/bin/cleanup.sh /var/lib/tomcat7/bin/cleanup.sh
  cp $BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/bin/cleanup.sh $TOMCAT_CLEANUP
  chmod 755 $TOMCAT_BIN/cleanup.sh
  rm -f $USR_BIN_CLEANUP
  ln -s $TOMCAT_CLEANUP $USR_BIN_CLEANUP

  # Setup the tomcat6/7 user with a normal /home directory and enable su
  rm -rf $TOMCAT_HOME; mkdir -p $TOMCAT_HOME; chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_HOME
  usermod --home $TOMCAT_HOME $TOMCAT
  usermod --shell /bin/bash $TOMCAT

  # Copy custom jsps into the logs directory
  cp $JSPS $TOMCAT_LOGS; chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_LOGS/*.jsp

  echo "start     -> $TOMCAT"
  $USR_BIN_CLEANUP
  service $TOMCAT start > /dev/null 2>&1
}

# Module specific variables
BASEDIR=${1-$BASEDIR}
TOMCAT=${2-$TOMCAT}
JDK=${3-$JDK}
MAX_HEAP=${4-$MAX_HEAP}
MAX_PERM=${5-$MAX_PERM}
QUIET=${6-$QUIET}

# Makes sure we have what we need to continue
check_args



