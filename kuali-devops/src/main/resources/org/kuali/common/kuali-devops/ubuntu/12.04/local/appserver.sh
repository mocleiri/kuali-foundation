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
  echo requires NEXUS_PASSWORD SUBDOMAIN JDK TOMCAT MAX_HEAP MAX_PERM
  echo usage: appserver.sh password subdomain jdk7/jdk6 tomcat7/tomcat6 max_heap max_perm [quiet]
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
  echo "configure -> $FQDN :: appserver"
  source installers.sh
  TOMCAT="$MODULES/common/install_tomcat.sh $BASEDIR $TOMCAT $JDK $MAX_HEAP $MAX_PERM $QUIET"
  SSH="$BASICS; $JAVA; $DNS; $TOMCAT"
  ssh root@$FQDN "$SSH"
}

echo $(date)

# import generic functions
source preconditions.sh

# Module specific variables
NEXUS_PASSWORD=$1
SUBDOMAIN=$2
JDK=$3
TOMCAT=$4
MAX_HEAP=$5
MAX_PERM=$6
QUIET=${7-""}

# Make sure we have what we need
check_args

# import bootstrap variables/functions
source bootstrap.sh $SUBDOMAIN

echo "configure -> $FQDN"
enable_root_ssh
publish_module
configure_application_server

echo $(date)
