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

TIMESTAMP=$(date +%Y%m%d_%H_%M_%S)
BASEDIR=/mnt/kuali-ec2
DOWNLOADS="$BASEDIR/target/downloads/"
SCRIPTS_DIR=$BASEDIR/src/test/resources/ubuntu/12.04/appserver
TOMCAT_VERSION=7

JDK_GROUP_ID=com/oracle
JDK_ARTIFACT_ID=jdk7
JDK_VERSION=1.7.0-u51
JDK_CLASSIFIER=linux-x64

JDK_UNZIP_DIR=$JDK_ARTIFACT_ID-$JDK_VERSION
JDK_ZIP_FILE=$JDK_ARTIFACT_ID-$JDK_VERSION-$JDK_CLASSIFIER.zip
JDK_BASEDIR=/usr/java

NEXUS_URL="http://nexus.kuali.org/content/groups/developer"
NEXUS_JDK_LOCATION="/$JDK_GROUP_ID/$JDK_ARTIFACT_ID/$JDK_VERSION/"
NEXUS_USER=developer
NEXUS_AUTH_ERROR="This request requires HTTP authentication"
NEXUS_JDK_DOWNLOAD_FILE=$DOWNLOADS/$JDK_ZIP_FILE

TOMCAT=tomcat$TOMCAT_VERSION
TOMCAT_OPT_FILE=/etc/default/$TOMCAT
TOMCAT_OPT_FILE_BAK=$TOMCAT_OPT_FILE.$TIMESTAMP
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
PASSWORD=""
HOSTNAME="NOTDEFINED"

SILENT=${1-NOTDEFINED}

# This dir contains downloaded files to install
rm -rf $DOWNLOADS
mkdir -p $DOWNLOADS

#Update Ubuntu repos and packages
function get_upgrades {
apt-get -y update
apt-get -y upgrade
}


# Enable unattended upgrades
function unattended_upgrades {
echo
echo "Enabling unattended upgrades.  Enter "yes" to enable..."
echo
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
apt-get -q $SILENT install iptables-persistent
iptables-save > /etc/iptables/rules.v4
ip6tables-save > /etc/iptables/rules.v6
unset DEBIAN_FRONTEND
}


# Install Tomcat
function install_tomcat {
echo
echo "Installing $TOMCAT..."
echo
apt-get -y install $TOMCAT libtcnative-1
echo "Stopping Tomcat..."
service $TOMCAT stop

cp $TOMCAT_OPT_FILE $TOMCAT_OPT_FILE_BAK
echo "TOMCAT_USER=$TOMCAT_USER" > $TOMCAT_OPT_FILE
echo "TOMCAT_USER=$TOMCAT_GROUP" >> $TOMCAT_OPT_FILE
echo "JAVA_OPTS=$JAVA_OPTS" >> $TOMCAT_OPT_FILE
echo "JAVA_HOME=$JAVA_HOME" >> $TOMCAT_OPT_FILE

SERVER_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/server.xml
WEB_XML=$BASEDIR/src/main/resources/apache-tomcat/$TOMCAT_VERSION/conf/web.xml

cp $TOMCAT_CONF_FILE_DIR/server.xml $TOMCAT_CONF_FILE_DIR/server.xml.$TIMESTAMP
cp $TOMCAT_CONF_FILE_DIR/web.xml $TOMCAT_CONF_FILE_DIR/web.xml.$TIMESTAMP
cp $WEB_XML $TOMCAT_CONF_FILE_DIR/web.xml
cp $SERVER_XML $TOMCAT_CONF_FILE_DIR/server.xml

rm -rf /var/lib/$TOMCAT/webapps/ROOT

}

function test_nexus_access {
if [[ $SILENT == "-y" ]];
then
TestPass=$(curl -sL -w "%{http_code}" --user $NEXUS_USER:$PASSWORD  "$NEXUS_URL")
echo
if [[ "$TestPass" == *$NEXUS_AUTH_ERROR* ]]; then
  echo "Authentication failed.  Please re-enter password for the $NEXUS_USER account on $NEXUS_URL"
  exit 1
else
  echo "Authenticated successfully"
  COUNT=1
fi

else
COUNT=0
while [[ $COUNT -lt 1 ]];do
read -s -p "Password for $NEXUS_USER account on Nexus:" PASSWORD
TestPass=$(curl -sL -w "%{http_code}" --user $NEXUS_USER:$PASSWORD  "$NEXUS_URL")
echo
if [[ "$TestPass" == *$NEXUS_AUTH_ERROR* ]]; then
  echo "Authentication failed.  Please re-enter password for the $NEXUS_USER account on $NEXUS_URL"
else
  echo "Authenticated succesfully"
  COUNT=1
fi

done
fi
}

# Install JDK
function get_jdk {
if [ $PASSWORD = ""]; then
test_nexus_access
fi

wget -N --user $NEXUS_USER --password $PASSWORD $NEXUS_URL$NEXUS_JDK_LOCATION$JDK_ZIP_FILE -O $DOWNLOADS/$JDK_ZIP_FILE

if [ ! -f $DOWNLOADS/$JDK_ZIP_FILE ]; then
  echo "$DOWNLOADS/$JDK_ZIP_FILE does not exist!"
  echo "Check to see if location is correct for the JDK.  Attempting to get:"
  echo "$NEXUS_URL$NEXUS_JDK_LOCATION$JDK_ZIP_FILE"
  echo "Quitting."
  exit 1
fi

rm -rf $JDK_BASEDIR
mkdir -p $JDK_BASEDIR

unzip -o $DOWNLOADS/$JDK_ZIP_FILE -d $JDK_BASEDIR
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

echo "Adding to /etc/hosts:"
ETC_HOSTS="$MYIP $HOSTNAME.$DOMAIN $HOSTNAME"
echo "$ETC_HOSTS"
echo
echo "Adding to /etc/hostname:"
echo "$HOSTNAME"
echo

cp /etc/hostname /etc/hostname.$TIMESTAMP
cp /etc/hosts /etc/hosts.$TIMESTAMP

echo "$HOSTNAME" > /etc/hostname
eval "sed -i -e '/127.0.0.1/a$ETC_HOSTS' /etc/hosts"
hostname $HOSTNAME
}

if [ $SILENT == "silent" ]; then
SILENT="-y"
PASSWORD=${2-NOTDEFINED}
HOSTNAME=${3-NOTDEFINED}
DOMAIN=${4-NOTDEFINED}

if [[ $PASSWORD == "NOTDEFINED" ]]; then
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

test_nexus_access
get_upgrades
redirect_rules
get_jdk
install_tomcat7
set_hostname
fi

if [ $SILENT == "NOTDEFINED" ]; then
SILENT=""

read -p "Update server (apt-get update & upgrade)?  This updates any installed packages (y/n)  " RUN_UPGRADE
if [[ $RUN_UPGRADE == "y" ]]; then
  get_upgrades
fi

apt-get install unzip ntp expect -y

echo "If allow unattended upgrade, select \"Yes\" on the interactive screen that appears."
read -p "Allow unattended ugrades? (y/n)  " ALLOW_UNATTENDED_UPGRADES
if [[ $ALLOW_UNATTENDED_UPGRADES == "y" ]]; then
  unattended_upgrades
fi
echo "If setup port redirect rules, select \"Yes\" on the interactive screen that appears."
read -p "Setup port redirect rules (8080 -> 80)? (y/n)  " SETUP_PORT_REDIRECT
if [[ $SETUP_PORT_REDIRECT == "y" ]]; then
  redirect_rules
fi
read -p "Install JDK? (y/n)  " RUN_GET_JDK
if [[ $RUN_GET_JDK == "y" ]]; then
  get_jdk
fi
read -p "Install Tomcat7? (y/n)  " RUN_TOMCAT_INSTALL
if [[ $RUN_TOMCAT_INSTALL == "y" ]]; then
  install_tomcat7
fi
read -p "Setup hostname and FQDN? (y/n)  " SET_HOSTNAME
if [[ $SET_HOSTNAME == "y" ]]; then
  set_hostname
fi

fi

echo "Completed"
exit 0
