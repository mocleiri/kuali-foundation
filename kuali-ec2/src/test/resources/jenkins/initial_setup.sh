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

NEXUS_AUTH_ERROR="This request requires HTTP authentication"
INSTALL_DIR="/root/installs/"
NEXUS_TEST_AUTH_URL="http://nexus.kuali.org/content/groups/developer"

JDK_GROUP_ID=com/oracle
JDK_ARTIFACT_ID=jdk7
JDK_VERSION=1.7.0-u51
JDK_CLASSIFIER=linux-x64

JDK_UNZIP_DIR=$JDK_ARTIFACT_ID-$JDK_VERSION
JDK_ZIP_FILE=$JDK_ARTIFACT_ID-$JDK_VERSION-$JDK_CLASSIFIER.zip

NEXUS_JDK_LOCATION="/$JDK_GROUP_ID/$JDK_ARTIFACT_ID/$JDK_VERSION/"
NEXUS_JDK_FILE="$JDK_ZIP_FILE"
NEXUS_USER=developer
LOCAL_JDK_DIR=/usr/java
TOMCAT7_OPT_FILE_DIR=/etc/default
TOMCAT7_OPT_FILE=tomcat7
TOMCAT7_CONF_FILE_DIR=/etc/tomcat7
TOMCAT7_USER=tomcat7
TOMCAT7_GROUP=tomcat7
JAVA_OPTS="\"-Djava.awt.headless=true -Xms512m -Xmx2g -XX:MaxPermSize=256m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -Xloggc:/var/log/tomcat7/heap.log -XX:HeapDumpPath=/var/log/tomcat7/logs -XX:+HeapDumpOnOutOfMemoryError"\"
JAVA_HOME=/usr/java/jdk7


#####################################################################
# Do not modify below
#####################################################################
PASSWORD=""
HOSTNAME="NOTDEFINED"
TIMESTAMP=$(date +%Y%m%d_%H_%M_%S)
PWD=$(pwd)
MYPWD=$PWD
echo $MYPWD

SILENT=${1-NOTDEFINED}


# Create installation directory.  This directory will contain install files.
if [ ! -d $INSTALL_DIR ]; then
echo "Creating directory $INSTALL_DIR"
mkdir $INSTALL_DIR
else
echo "The directory \"$INSTALL_DIR\" already exists. No need to create."
fi
cd $INSTALL_DIR

#Update Ubuntu repos and packages
function get_upgrades {
apt-get $SILENT update
apt-get $SILENT upgrade
}


# Enable unattended upgrades
function unattended_upgrades {
echo
echo "Enabling unattended upgrades.  Enter "yes" to enable..."
echo
#dpkg-reco.nfigure unattended-upgrades
echo "PWD=$MYPWD"
$MYPWD/unattended-upgrades.sh
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


# Install Tomcat7
function install_tomcat7 {
echo
echo "Installing Tomcat7..."
echo
apt-get $SILENT install tomcat7
apt-get $SILENT install libtcnative-1
echo "Stopping Tomcat7..."
service tomcat7 stop

cp $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE.$TIMESTAMP
echo "TOMCAT7_USER=$TOMCAT7_USER" > $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE
echo "TOMCAT7_USER=$TOMCAT7_GROUP" >> $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE
echo "JAVA_OPTS=$JAVA_OPTS" >> $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE
echo "JAVA_HOME=$JAVA_HOME" >>  $TOMCAT7_OPT_FILE_DIR/$TOMCAT7_OPT_FILE

wget -N "https://svn.kuali.org/repos/foundation/trunk/kuali-ec2/src/main/resources/apache-tomcat/7/conf/server.xml"
wget -N "https://svn.kuali.org/repos/foundation/trunk/kuali-ec2/src/main/resources/apache-tomcat/7/conf/web.xml"
cp $TOMCAT7_CONF_FILE_DIR/server.xml $TOMCAT7_CONF_FILE_DIR/server.xml.$TIMESTAMP
cp $TOMCAT7_CONF_FILE_DIR/web.xml $TOMCAT7_CONF_FILE_DIR/web.xml.$TIMESTAMP
cp web.xml $TOMCAT7_CONF_FILE_DIR/web.xml
cp server.xml $TOMCAT7_CONF_FILE_DIR/server.xml

rm -rf /var/lib/tomcat7/webapps/ROOT

}

function test_nexus_access {
if [[ $SILENT == "-y" ]];
then
TestPass=$(curl -sL -w "%{http_code}" --user $NEXUS_USER:$PASSWORD  "$NEXUS_TEST_AUTH_URL")
echo
if [[ "$TestPass" == *$NEXUS_AUTH_ERROR* ]]; then
  echo "Authentication failed.  Please re-enter password for the $NEXUS_USER account on $NEXUS_TEST_AUTH_URL"
  exit 1
else
  echo "Authenticated succesfully"
  COUNT=1
fi

else
COUNT=0
while [[ $COUNT -lt 1 ]];do
read -s -p "Password for $NEXUS_USER account on Nexus:" PASSWORD
TestPass=$(curl -sL -w "%{http_code}" --user $NEXUS_USER:$PASSWORD  "$NEXUS_TEST_AUTH_URL")
echo
if [[ "$TestPass" == *$NEXUS_AUTH_ERROR* ]]; then
  echo "Authentication failed.  Please re-enter password for the $NEXUS_USER account on $NEXUS_TEST_AUTH_URL"
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

wget -N --user $NEXUS_USER --password $PASSWORD $NEXUS_TEST_AUTH_URL$NEXUS_JDK_LOCATION$NEXUS_JDK_FILE

if [ ! -f $INSTALL_DIR/$NEXUS_JDK_FILE ]; then
  echo "$INSTALL_DIR/$NEXUS_JDK_FILE does not exist!"
  echo "Check to see if location is correct for the JDK.  Attempting to get:"
  echo "$NEXUS_TEST_AUTH_URL$NEXUS_JDK_LOCATION$NEXUS_JDK_FILE"
  echo "Quitting."
  exit 1
fi

if [ ! -d $LOCAL_JDK_DIR ]; then
echo "Creating directory $LOCAL_JDK_DIR"
mkdir $LOCAL_JDK_DIR
else
echo "$LOCAL_JDK_DIR already exists."
fi

unzip $NEXUS_JDK_FILE -d $LOCAL_JDK_DIR
ln -s $JDK_DIR $LOCAL_JDK_DIR/jdk7
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
HOSTNAME=${3-NOT_DEFINED}
DOMAIN=${4-NOT_DEFINED}

if [[ $PASSWORD == "NOTDEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

if [[ $HOSTNAME == "NOT_DEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

if [[ $DOMAIN == "NOT_DEFINED" ]]; then
echo "One or more parameters not set.  silent, Nexus password, hostname, and domain must all be defined when running in silent mode:"
echo
echo "  initial_setup silent password hostname domain"
echo
exit 1
fi;

test_nexus_access
get_upgrades
apt-get install unzip ntp expect -y
unattnded_upgrades
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
read -p "Install unzip? (y/n)  " RUN_UNZIP_INSTALL
if [[ $RUN_UNZIP_INSTALL == "y" ]]; then
  install_unzip
fi
read -p "Install Network Time Protocal (NTP)? (y/n)  " RUN_NTP_INSTALL
if [[ $RUN_NTP_INSTALL == "y" ]]; then
  install_ntp
fi
read -p "Install expect? (y/n)  " RUN_EXPECT_INSTALL
if [[ $RUN_EXPECT_INSTALL == "y" ]]; then
  install_expect
fi
echo "If allow unattnded upgrade, select \"Yes\" on the interactive screen that appears."
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

cd -
echo "Completed"
exit 0
