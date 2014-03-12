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

function check_exists {
  FILENAME=$1
  if [ ! -n "$FILENAME" ]; then 
    echo FILENAME cannot be blank
    exit 1
  fi
  if [ ! -f $FILENAME ]; then
    echo "file [$FILENAME] does not exist"
    exit 1
  fi
}

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

# module specific functions
function show_usage {
  echo
  echo requires SSL_PASSWORD DOMAIN BASEDIR TOMCAT JDK MAX_HEAP MAX_PERM QUIET
  echo usage: install_tomcat.sh ssl_password domain basedir tomcat6/tomcat7 jdk6/jdk7 max_heap max_perm [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank SSL_PASSWORD $SSL_PASSWORD
  check_not_blank DOMAIN $DOMAIN
  check_not_blank BASEDIR $BASEDIR
  check_not_blank TOMCAT $TOMCAT
  check_not_blank JDK $JDK
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
}

function redirect_ports {

  echo "redirect  -> port 80 to 8080"
  iptables --table nat --append PREROUTING --protocol tcp --dport 80 --jump REDIRECT --to-port 8080
  iptables -t nat -A OUTPUT -p tcp -o lo --dport 80 -j DNAT --to 127.0.0.1:8080
  iptables --table nat --append PREROUTING --protocol tcp --dport 443 --jump REDIRECT --to-port 8443
  iptables -t nat -A OUTPUT -p tcp -o lo --dport 443 -j DNAT --to 127.0.0.1:8443
  export DEBIAN_FRONTEND=noninteractive
  execute_quietly "apt-get $QUIET -y install iptables-persistent"
  iptables-save > /etc/iptables/rules.v4
  ip6tables-save > /etc/iptables/rules.v6
  unset DEBIAN_FRONTEND
  
}

function purge_tomcat {

  TOMCAT_PURGE="libtcnative-1 tomcat6-common tomcat6 tomcat7"
  echo "purge     -> $TOMCAT_PURGE"
  execute_quietly "apt-get $QUIET -y purge $TOMCAT_PURGE"
  execute_quietly "apt-get $QUIET -y autoremove"
  rm -rf /var/lib/tomcat6 /var/lib/tomcat7
  
}

function get_java_opts {

  RETURN_VALUE="-Djava.security.egd=file:/dev/./urandom"
  RETURN_VALUE="$RETURN_VALUE -Djava.awt.headless=true"
  RETURN_VALUE="$RETURN_VALUE -Xms512m"
  RETURN_VALUE="$RETURN_VALUE -Xmx$MAX_HEAP"
  RETURN_VALUE="$RETURN_VALUE -XX:MaxPermSize=$MAX_PERM"
  RETURN_VALUE="$RETURN_VALUE -verbose:gc"
  RETURN_VALUE="$RETURN_VALUE -XX:+PrintGCDetails"
  RETURN_VALUE="$RETURN_VALUE -XX:+PrintGCDateStamps"
  RETURN_VALUE="$RETURN_VALUE -XX:+PrintHeapAtGC"
  RETURN_VALUE="$RETURN_VALUE -XX:+PrintTenuringDistribution"
  RETURN_VALUE="$RETURN_VALUE -Xloggc:$TOMCAT_LOGS/heap.log"
  RETURN_VALUE="$RETURN_VALUE -XX:HeapDumpPath=$TOMCAT_LOGS"
  RETURN_VALUE="$RETURN_VALUE -XX:+HeapDumpOnOutOfMemoryError"
  echo "\"$RETURN_VALUE\""
  
}

function configure_tomcat_ssl {

  $TOMCAT_SSL_DIR=/var/lib/$TOMCAT/conf/ssl
  mkdir -p $TOMCAT_SSL_DIR
  
  ZIP=$BASEDIR/${project.groupId.path}/${project.artifactId}/tomcat/config.zip
  unzip $QUIET -P $ZIP_PASSWORD -o $ZIP -d $TOMCAT_SSL_DIR
  
  SSL_KEYSTORE=$TOMCAT_SSL_DIR/wildcard.$DOMAIN.keystore.pksc12
  check_exists $SSL_KEYSTORE

  openssl pkcs12 -in $SSL_KEYSTORE -out $SSL_KEYSTORE/SSLCertificateFile.pem      -clcerts -nokeys -passin pass:"$SSL_PASSWORD"
  openssl pkcs12 -in $SSL_KEYSTORE -out $SSL_KEYSTORE/SSLCertificateChainFile.pem -clcerts -nokeys -passin pass:"$SSL_PASSWORD"
  openssl pkcs12 -in $SSL_KEYSTORE -out $SSL_KEYSTORE/SSLCertificateKeyFile.pem   -nocerts -nodes  -passin pass:"$SSL_PASSWORD"
  
}

function configure_tomcat {

  TOMCAT_VERSION=${TOMCAT:6:1}
  check_not_blank TOMCAT_VERSION $TOMCAT_VERSION

  TOMCAT_OPT_FILE=/etc/default/$TOMCAT
  TOMCAT_CONF_FILE_DIR=/etc/$TOMCAT
  TOMCAT_USER=$TOMCAT
  TOMCAT_GROUP=$TOMCAT
  TOMCAT_DIR=/var/lib/$TOMCAT
  TOMCAT_LOGS=$TOMCAT_DIR/logs
  TOMCAT_HOME=/home/$TOMCAT
  JAVA_OPTS="$(get_java_opts)"
  JAVA_HOME=/usr/java/$JDK

  echo "configure -> $TOMCAT"
  echo "TOMCAT_USER=$TOMCAT_USER"   >  $TOMCAT_OPT_FILE
  echo "TOMCAT_GROUP=$TOMCAT_GROUP" >> $TOMCAT_OPT_FILE
  echo "JAVA_OPTS=$JAVA_OPTS"       >> $TOMCAT_OPT_FILE
  echo "JAVA_HOME=$JAVA_HOME"       >> $TOMCAT_OPT_FILE

  WEBXML=$BASEDIR/${project.groupId.path}/${project.artifactId}/tomcat/$TOMCAT_VERSION/conf/web.xml
  SERVER=$BASEDIR/${project.groupId.path}/${project.artifactId}/tomcat/$TOMCAT_VERSION/conf/server.xml

  cp $WEBXML $TOMCAT_CONF_FILE_DIR/web.xml
  cp $SERVER $TOMCAT_CONF_FILE_DIR/server.xml

  TOMCAT_LOGS=/var/lib/$TOMCAT/logs
  TOMCAT_WEBAPPS=/var/lib/$TOMCAT/webapps
  TOMCAT_BIN=/usr/share/$TOMCAT/bin
  TOMCAT_CLEANUP=$TOMCAT_BIN/cleanup.sh
  USR_BIN_CLEANUP=/usr/bin/cleanup.sh
  JSPS=$BASEDIR/${project.groupId.path}/${project.artifactId}/tomcat/jsps/*.jsp

  rm -rf $TOMCAT_WEBAPPS/* $TOMCAT_LOGS/* /var/lib/tomcat6/bin/cleanup.sh /var/lib/tomcat7/bin/cleanup.sh
  cp $BASEDIR/${project.groupId.path}/${project.artifactId}/tomcat/$TOMCAT_VERSION/bin/cleanup.sh $TOMCAT_CLEANUP
  chmod 755 $TOMCAT_BIN/cleanup.sh
  rm -f $USR_BIN_CLEANUP
  ln -s $TOMCAT_CLEANUP $USR_BIN_CLEANUP

  # Setup the tomcat user with a normal /home directory and enable su
  echo "configure -> $TOMCAT:user"
  rm -rf $TOMCAT_HOME; mkdir -p $TOMCAT_HOME
  usermod --home $TOMCAT_HOME $TOMCAT
  usermod --shell /bin/bash $TOMCAT
  
  # copy root's .bashrc and setup .bash_aliases with Java and Maven
  cp /root/.bashrc $TOMCAT_HOME/.bash_profile
  TOMCAT_BASH_ALIASES=$TOMCAT_HOME/.bash_aliases
  echo "JAVA_HOME=$JAVA_HOME"                       >  $TOMCAT_BASH_ALIASES
  echo "PATH=\$JAVA_HOME/bin:\$PATH:."              >> $TOMCAT_BASH_ALIASES
  echo "MAVEN_OPTS=\"-Xmx2g -XX:MaxPermSize=256m\"" >> $TOMCAT_BASH_ALIASES
  echo "export JAVA_HOME PATH MAVEN_OPTS"           >> $TOMCAT_BASH_ALIASES
  
  # setup ssl
  configure_tomcat_ssl

  chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_HOME
  
  # Copy custom jsps into the logs directory
  cp $JSPS $TOMCAT_LOGS; chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_LOGS/*.jsp
  
}

function install_tomcat_packages {

  TOMCAT_VERSION=${TOMCAT:6:1}
  check_not_blank TOMCAT_VERSION $TOMCAT_VERSION
  
  if [ $TOMCAT_VERSION == "6" ]; then
    echo "install   -> $TOMCAT-common libtcnative-1 $TOMCAT"
    execute_quietly "apt-get $QUIET -y install $TOMCAT-common libtcnative-1 $TOMCAT"
  else
    echo "install   -> libtcnative-1 $TOMCAT"
    execute_quietly "apt-get $QUIET -y install libtcnative-1 $TOMCAT"
  fi
  
}

# Install Tomcat
function install_tomcat {
  
  TOMCAT_VERSION=${TOMCAT:6:1}
  check_not_blank TOMCAT_VERSION $TOMCAT_VERSION
  
  purge_tomcat
  install_tomcat_packages
  
  echo "stop      -> $TOMCAT"
  service $TOMCAT stop > /dev/null 2>&1
  
  configure_tomcat
  redirect_ports
  cleanup.sh
  
  echo "start     -> $TOMCAT"
  service $TOMCAT start > /dev/null 2>&1
  
}

# Module specific variables
SSL_PASSWORD=${1-$SSL_PASSWORD}
DOMAIN=${2-$BASEDIR}
BASEDIR=${3-$BASEDIR}
TOMCAT=${4-$TOMCAT}
JDK=${5-$JDK}
MAX_HEAP=${6-$MAX_HEAP}
MAX_PERM=${7-$MAX_PERM}
QUIET=${8-$QUIET}

# Make sure we have what we need to continue
check_args

install_tomcat
