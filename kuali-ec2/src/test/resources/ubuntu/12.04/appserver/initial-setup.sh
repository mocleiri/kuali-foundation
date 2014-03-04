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


# Usage:
# Interactive:
#   ./initial_setup.sh
#
# Silent:
#   ./initial_setup.sh silent <nexus_password> <hostname> <domain>
#
# Example for testserver.kuali.org:
#   ./initial_setup.sh silent MyNexusPassword testserver kuali.org
#

BASEDIR=/mnt/kuali-ec2
DOWNLOADS="$BASEDIR/target/downloads/"
SCRIPTS_DIR=$BASEDIR/src/test/resources/ubuntu/12.04/appserver
QUIET=-qq

JDK6=jdk6
JDK6_VERSION=1.6.0-u45

JDK7=jdk7
JDK7_VERSION=1.7.0-u51

#JDK_LEVEL=6
JDK_LEVEL=7
JDK=$(eval echo \${JDK${JDK_LEVEL}})
JDK_VERSION=$(eval echo \${JDK${JDK_LEVEL}_VERSION})

JDK_GROUP_ID=com/oracle
JDK_ARTIFACT_ID=$JDK
JDK_CLASSIFIER=linux-x64

JDK_UNZIP_DIR=$JDK_ARTIFACT_ID-$JDK_VERSION
JDK_ZIP_FILE=$JDK_ARTIFACT_ID-$JDK_VERSION-$JDK_CLASSIFIER.zip
JDK_BASEDIR=/usr/java

NEXUS_URL="http://nexus.kuali.org/content/groups/developer"
NEXUS_JDK_LOCATION="/$JDK_GROUP_ID/$JDK_ARTIFACT_ID/$JDK_VERSION/"
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
JAVA_HOME=/usr/java/jdk7


#####################################################################
# Do not modify below
#####################################################################
NEXUS_PASSWORD=""
HOSTNAME="NOTDEFINED"

SILENT=${1-NOTDEFINED}

# Directory for the JDK download
rm -rf $DOWNLOADS; mkdir -p $DOWNLOADS

#Update Ubuntu repos and packages
function get_upgrades {
apt-get $QUIET -y update
apt-get $QUIET -y upgrade
}


# Enable unattended upgrades
function unattended_upgrades {
#dpkg-reco.nfigure unattended-upgrades
$SCRIPTS_DIR/unattended-upgrades.sh
}

# Setup port redirect rules
function redirect_rules {
echo
echo "Setting up port re-direct rules."
echo
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
apt-get $QUIET -y --purge remove $TOMCAT6 $TOMCAT7
apt-get $QUIET -y install $TOMCAT libtcnative-1
service $TOMCAT stop

echo "TOMCAT_USER=$TOMCAT_USER" > $TOMCAT_OPT_FILE
echo "TOMCAT_USER=$TOMCAT_GROUP" >> $TOMCAT_OPT_FILE
echo "JAVA_OPTS=$JAVA_OPTS" >> $TOMCAT_OPT_FILE
echo "JAVA_HOME=$JAVA_HOME" >> $TOMCAT_OPT_FILE

SERVER_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/server.xml
WEB_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/web.xml

cp $WEB_XML $TOMCAT_CONF_FILE_DIR/web.xml
cp $SERVER_XML $TOMCAT_CONF_FILE_DIR/server.xml

rm -rf /var/lib/$TOMCAT/webapps/ROOT

}

# Install JDK
function get_jdk {
wget $QUIET --user $NEXUS_USER --password $NEXUS_PASSWORD $NEXUS_URL$NEXUS_JDK_LOCATION$JDK_ZIP_FILE --output-document $DOWNLOADS/$JDK_ZIP_FILE

if [ ! -f $DOWNLOADS/$JDK_ZIP_FILE ]; then
  echo "$DOWNLOADS/$JDK_ZIP_FILE does not exist!"
  echo "Check to see if location is correct for the JDK.  Attempting to get:"
  echo "$NEXUS_URL$NEXUS_JDK_LOCATION$JDK_ZIP_FILE"
  echo "Quitting."
  exit 1
fi

# Make sure the JDK and the symbolic link are both gone
rm -rf $JDK_BASEDIR/$JDK_ARTIFACT_ID $JDK_BASEDIR/$JDK_UNZIP_DIR

# Unpack the JDK into /usr/java
unzip $QUIET -o $DOWNLOADS/$JDK_ZIP_FILE -d $JDK_BASEDIR

# Symbolic link for /usr/java/jdk7 -> /usr/java/jdk7-1.7.0-u51
ln -s $JDK_BASEDIR/$JDK_UNZIP_DIR $JDK_BASEDIR/$JDK_ARTIFACT_ID
}

# Request hostname and FQDN
function get_hostname {
read -p "Enter the hostname of this server(ie: nexus): " HOSTNAME
read -p "Enter the domain name for this server (ie: fn.kuali.org):" DOMAIN
}

# Set hostname and FQDN
function set_hostname {
MYIP=$(ifconfig eth0 |grep inet | awk '{ print $2 }' | awk 'BEGIN { FS=":" } { print $2 }')

if [ $HOSTNAME == "NOTDEFINED" ]; then
get_hostname
fi;

ETC_HOSTS="$MYIP $HOSTNAME.$DOMAIN $HOSTNAME"
echo
echo "Adding to /etc/hosts    -> $ETC_HOSTS"
echo "Adding to /etc/hostname -> $HOSTNAME"
echo

echo "$HOSTNAME" > /etc/hostname
eval "sed -i -e '/127.0.0.1/a$ETC_HOSTS' /etc/hosts"
hostname $HOSTNAME
}

if [ $SILENT == "silent" ]; then
SILENT="-y"
NEXUS_PASSWORD=${2-NOTDEFINED}
HOSTNAME=${3-NOTDEFINED}
DOMAIN=${4-NOTDEFINED}

if [[ $NEXUS_PASSWORD == "NOTDEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

if [[ $HOSTNAME == "NOTDEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

if [[ $DOMAIN == "NOTDEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

get_upgrades
redirect_rules
get_jdk
install_tomcat
set_hostname
fi

if [ $SILENT == "NOTDEFINED" ]; then
SILENT=""

read -p "Update server (apt-get update & upgrade)?  This updates any installed packages (y/n)  " RUN_UPGRADE
if [[ $RUN_UPGRADE == "y" ]]; then
  get_upgrades
fi

apt-get install unzip ntp expect -y $QUIET 

read -p "Allow unattended ugrades? (y/n)  " ALLOW_UNATTENDED_UPGRADES
if [[ $ALLOW_UNATTENDED_UPGRADES == "y" ]]; then
  unattended_upgrades
fi
read -p "Setup port redirect rules (8080 -> 80)? (y/n)  " SETUP_PORT_REDIRECT
if [[ $SETUP_PORT_REDIRECT == "y" ]]; then
  redirect_rules
fi
read -p "Install JDK? (y/n)  " RUN_GET_JDK
if [[ $RUN_GET_JDK == "y" ]]; then
  get_jdk
fi
read -p "Install Tomcat? (y/n)  " RUN_TOMCAT_INSTALL
if [[ $RUN_TOMCAT_INSTALL == "y" ]]; then
  install_tomcat
fi
read -p "Setup hostname and FQDN? (y/n)  " SET_HOSTNAME
if [[ $SET_HOSTNAME == "y" ]]; then
  set_hostname
fi

fi

exit 0
