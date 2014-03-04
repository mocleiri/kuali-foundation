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

#
# Usage:
#   ./configure.sh password hostname [jdk] [tomcat] [max_heap] [max_perm]
#

JDK_LEVEL=${3-6}
TOMCAT_VERSION=${4-7}
MAX_HEAP=${5-5g}
MAX_PERM=${6-512m}
QUIET=${7-""}

JDK6_VERSION=1.6.0-u45
JDK7_VERSION=1.7.0-u51

DOMAIN=kuali.org
BASEDIR=/mnt/kuali-ec2
DOWNLOADS=$BASEDIR/target/downloads
SCRIPTS_DIR=$BASEDIR/src/test/resources/ubuntu/12.04/appserver

JDK6=jdk6
JDK7=jdk7

JDK=$(eval echo \${JDK${JDK_LEVEL}})
JDK_VERSION=$(eval echo \${JDK${JDK_LEVEL}_VERSION})

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

TOMCAT=tomcat$TOMCAT_VERSION
TOMCAT_OPT_FILE=/etc/default/$TOMCAT
TOMCAT_CONF_FILE_DIR=/etc/$TOMCAT
TOMCAT_USER=$TOMCAT
TOMCAT_GROUP=$TOMCAT
TOMCAT_DIR=/var/lib/$TOMCAT
TOMCAT_LOGS=$TOMCAT_DIR/logs
TOMCAT_HOME=/home/$TOMCAT
JAVA_OPTS="\"-Duser.home=$TOMCAT_HOME -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Xms512m -Xmx$MAX_HEAP -XX:MaxPermSize=$MAX_PERM -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -Xloggc:$TOMCAT_LOGS/heap.log -XX:HeapDumpPath=$TOMCAT_LOGS -XX:+HeapDumpOnOutOfMemoryError"\"
JAVA_HOME=/usr/java/$JDK


#####################################################################
# Do not modify below
#####################################################################
NEXUS_PASSWORD="NOTDEFINED"
HOSTNAME="NOTDEFINED"

# Directory for the JDK download
rm -rf $DOWNLOADS; mkdir -p $DOWNLOADS

#Update Ubuntu repos and packages
function get_upgrades {
echo "update    -> package indexes"
apt-get $QUIET -y update
echo "upgrade   -> packages"
apt-get $QUIET -y upgrade > /dev/null 2>&1
}


# Enable unattended upgrades
function unattended_upgrades {
#dpkg-reco.nfigure unattended-upgrades
echo "configure -> unattended upgrades"
$SCRIPTS_DIR/unattended-upgrades.sh > /dev/null 2>&1
}

# install custom packages
function install_packages {
echo "install   -> custom packages"
apt-get install unzip ntp expect -y $QUIET 
}

# Setup port redirect rules
function redirect_rules {
echo "redirect  -> port 80 to 8080"
iptables --table nat --append PREROUTING --protocol tcp --dport 80 --jump REDIRECT --to-port 8080
iptables -t nat -A OUTPUT -p tcp -o lo --dport 80 -j DNAT --to 127.0.0.1:8080
export DEBIAN_FRONTEND=noninteractive
apt-get $QUIET -y install iptables-persistent
iptables-save > /etc/iptables/rules.v4
ip6tables-save > /etc/iptables/rules.v6
unset DEBIAN_FRONTEND
}


# Install Tomcat
function install_tomcat {
TOMCAT_PURGE="libtcnative-1 tomcat6-common tomcat6 tomcat7"
echo "purge     -> $TOMCAT_PURGE"
apt-get $QUIET -y purge $TOMCAT_PURGE > /dev/null 2>&1
apt-get $QUIET -y autoremove > /dev/null 2>&1
rm -rf /var/lib/tomcat6 /var/lib/tomcat7
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

WEB_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/web.xml
SERVER_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/server.xml

cp $WEB_XML $TOMCAT_CONF_FILE_DIR/web.xml
cp $SERVER_XML $TOMCAT_CONF_FILE_DIR/server.xml

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

# Setup /home/tomcat[6/7] as the home dir
rm -rf $TOMCAT_HOME; mkdir -p $TOMCAT_HOME; chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_HOME

# Copy custom jsps into the logs directory
cp $JSPS $TOMCAT_LOGS; chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_LOGS/*.jsp

echo "start     -> $TOMCAT"
$USR_BIN_CLEANUP
service $TOMCAT start > /dev/null 2>&1
}

# Install JDK
function get_jdk {

URL=$NEXUS_URL/$NEXUS_JDK_LOCATION/$JDK_ZIP_FILE
OUTPUT_FILE=$DOWNLOADS/$JDK_ZIP_FILE
echo "download  -> $URL"
wget $QUIET --user $NEXUS_USER --password $NEXUS_PASSWORD $URL --output-document $OUTPUT_FILE
echo "to        -> $OUTPUT_FILE"

if [ ! -f $OUTPUT_FILE ]; then
  echo "$OUTPUT_FILE does not exist!"
  exit 1
fi

# Make sure the JDK and the symbolic link are both gone
JDK_TARGET=$JDK_BASEDIR/$JDK_UNZIP_DIR
rm -rf $JDK_BASEDIR/$JDK_ARTIFACT_ID $JDK_TARGET

# Unpack the JDK into /usr/java
unzip $QUIET -o $DOWNLOADS/$JDK_ZIP_FILE -d $JDK_BASEDIR

# Create a symbolic link for /usr/java/jdk7 -> /usr/java/jdk7-1.7.0-u51
JDK_LINK=$JDK_BASEDIR/$JDK_ARTIFACT_ID
ln -s $JDK_TARGET $JDK_LINK
echo "installed -> $JDK_LINK -> $JDK_TARGET"
}

# Set hostname and FQDN
function set_hostname {
MYIP=$(ifconfig eth0 |grep inet | awk '{ print $2 }' | awk 'BEGIN { FS=":" } { print $2 }')

ETC_HOSTS="$MYIP $HOSTNAME.$DOMAIN $HOSTNAME"
echo "hostname  -> $HOSTNAME"
echo "hosts     -> $ETC_HOSTS"

echo "$HOSTNAME" > /etc/hostname
hostname $HOSTNAME

echo '127.0.0.1 localhost' > /etc/hosts
echo $ETC_HOSTS >> /etc/hosts

# Mike's magic line here always appends to /etc/hosts
# This breaks with the concept of idempotence
# And also if for some reason we gave the machine a new DNS name I think we'd have issues 
#eval "sed -i -e '/127.0.0.1/a$ETC_HOSTS' /etc/hosts"
}

function show_usage {
  echo
  echo usage: configure.sh password hostname [jdk] [tomcat] [max_heap] [max_perm] [quiet]
  echo
  exit 1 
}

NEXUS_PASSWORD=${1-NOTDEFINED}
HOSTNAME=${2-NOTDEFINED}

if [ $NEXUS_PASSWORD == "NOTDEFINED" ]; then
  echo no password!
  show_usage
fi

if [ $HOSTNAME == "NOTDEFINED" ]; then
  echo no hostname!
  show_usage
fi

get_upgrades
unattended_upgrades
install_packages
redirect_rules
get_jdk
install_tomcat
set_hostname
