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

# module specific functions
function show_usage {
  echo
  echo requires NEXUS_PASSWORD SUBDOMAIN JDK TOMCAT MAX_HEAP MAX_PERM
  echo usage: bootstrap.sh password subdomain jdk7/jdk6 tomcat7/tomcat6 max_heap max_perm quiet
  echo
  exit 1
}

function check_args {
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank JDK $JDK
  check_not_blank TOMCAT $TOMCAT
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
}

function configure_application_server {
  echo "configure -> $FQDN as [application server]"
  BASICS="$MODULES/common/install_basics.sh $BASEDIR $QUIET"
  JAVA="$MODULES/common/install_java.sh $BASEDIR $JDK $NEXUS_PASSWORD $QUIET"
  DNS="$MODULES/common/update_hostname.sh $SUBDOMAIN $DOMAIN"
  TOMCAT="$MODULES/appserver/install_tomcat.sh $BASEDIR $TOMCAT $JDK $MAX_HEAP $MAX_PERM $QUIET"
  SSH="$BASICS; $JAVA; $DNS; $TOMCAT"
  ssh root@$FQDN "$SSH"
}

echo $(date)

# source generic functions
source preconditions.sh

# Module specific variables
NEXUS_PASSWORD=$1
SUBDOMAIN=$2
JDK=$3
TOMCAT=$4
MAX_HEAP=$5
MAX_PERM=$6
QUIET=${7-""}

# Make sure we have what we need to continue
check_args

# bootstrap the server we are working on (enables root ssh, checks out kuali-ec2 to /mnt)
source bootstrap.sh $SUBDOMAIN

echo "configure -> $FQDN"
enable_root_ssh
checkout_module
configure_application_server

echo $(date)
