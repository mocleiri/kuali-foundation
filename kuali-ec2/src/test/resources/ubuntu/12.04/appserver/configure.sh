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
#   ./configure.sh [nexus password] [hostname]
#

DOMAIN=kuali.org
BASEDIR=/mnt/kuali-ec2
DOWNLOADS=$BASEDIR/target/downloads
SCRIPTS_DIR=$BASEDIR/src/test/resources/ubuntu/12.04/appserver
QUIET=-qq

JDK6=jdk6
JDK6_VERSION=1.6.0-u45

JDK7=jdk7
JDK7_VERSION=1.7.0-u51

JDK_LEVEL=6
#JDK_LEVEL=7
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

#TOMCAT_VERSION=6
TOMCAT_VERSION=7
TOMCAT=tomcat$TOMCAT_VERSION
TOMCAT_OPT_FILE=/etc/default/$TOMCAT
TOMCAT_CONF_FILE_DIR=/etc/$TOMCAT
TOMCAT_USER=$TOMCAT
TOMCAT_GROUP=$TOMCAT
TOMCAT_DIR=/var/lib/$TOMCAT
TOMCAT_LOGS=$TOMCAT_DIR/logs
JAVA_OPTS="\"-Djava.awt.headless=true -Xms512m -Xmx2g -XX:MaxPermSize=256m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -Xloggc:$TOMCAT_LOGS/heap.log -XX:HeapDumpPath=$TOMCAT_LOGS -XX:+HeapDumpOnOutOfMemoryError"\"
JAVA_HOME=/usr/java/$JDK


#####################################################################
# Do not modify below
#####################################################################
NEXUS_PASSWORD="NOTDEFINED"
HOSTNAME="NOTDEFINED"

SILENT=${1-NOTDEFINED}

# Directory for the JDK download
rm -rf $DOWNLOADS; mkdir -p $DOWNLOADS

#Update Ubuntu repos and packages
function get_upgrades {
echo "update    -> package indexes"
apt-get $QUIET -y update
echo "upgrade   -> packages"
apt-get $QUIET -y upgrade
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
echo "remove    -> tomcat6 tomcat7"
apt-get $QUIET -y purge tomcat6 tomcat7 > /dev/null 2>&1
echo "install   -> $TOMCAT"
apt-get $QUIET -y install $TOMCAT libtcnative-1 > /dev/null 2>&1
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

rm -rf $TOMCAT_WEBAPPS/* $TOMCAT_LOGS/*
cp $BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/bin/cleanup.sh $TOMCAT_CLEANUP
chmod 755 $TOMCAT_BIN/cleanup.sh
rm -f $USR_BIN_CLEANUP; ln -s $TOMCAT_CLEANUP $USR_BIN_CLEANUP

cp $JSPS $TOMCAT_LOGS
chown -R $TOMCAT_USER:$TOMCAT_GROUP $TOMCAT_LOGS/*.jsp

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
  echo "Check to see if location is correct for the JDK.  Attempting to get:"
  echo "$URL"
  echo "Quitting."
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
echo "installed -> $JDK_LINK - [$JDK_TARGET]"
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

# Request hostname
function get_hostname {
read -p "Enter the hostname of this server (eg: nexus): " HOSTNAME
}

# Request nexus password
function get_nexus_password {
read -p "Enter the password for the Nexus user 'developer': " NEXUS_PASSWORD
}

NEXUS_PASSWORD=${1-NOTDEFINED}
HOSTNAME=${2-NOTDEFINED}

if [ $NEXUS_PASSWORD == "NOTDEFINED" ]; then
get_nexus_password
fi;

if [ $HOSTNAME == "NOTDEFINED" ]; then
get_hostname
fi;

get_upgrades
unattended_upgrades
install_packages
redirect_rules
get_jdk
install_tomcat
set_hostname
