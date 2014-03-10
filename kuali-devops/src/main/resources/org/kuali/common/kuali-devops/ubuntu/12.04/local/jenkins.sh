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
  echo requires NEXUS_PASSWORD SVN_PASSWORD SUBDOMAIN TYPE
  echo usage: jenkins.sh nexus_password svn_password subdomain type [quiet]
  exit 1
}

function check_args {
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank SVN_PASSWORD $SVN_PASSWORD
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank TYPE $TYPE
}

function configure_common {
  echo "configure -> jenkins:common"
  COMMON="$MODULES/jenkins/common.sh $BASEDIR $SVN_PASSWORD $ZIP_PASSWORD $QUIET"
  SSH="$COMMON"
  ssh root@$FQDN "$SSH"
}

function configure_master {
  echo "configure -> jenkins:master"
  JDK=jdk6
  source installers.sh
  MASTER="$MODULES/jenkins/master.sh $ZIP_PASSWORD $BASEDIR $QUIET"
  SSH="$JAVA; $MASTER"
  ssh root@$FQDN "$SSH"
}

function configure_slave_before {
  enable_root_ssh
  publish_module
  echo "configure -> $FQDN :: ec2slave"
  JDK=jdk6; source installers.sh; JAVA6=$JAVA
  JDK=jdk7; source installers.sh; JAVA7=$JAVA
  SSH="$BASICS; $JAVA6; $JAVA7"
  ssh root@$FQDN "$SSH"
}

function configure_slave_after {
  echo "configure -> jenkins:slave"
  SLAVE="$MODULES/jenkins/slave.sh $BASEDIR $QUIET"
  SSH="$SLAVE"
  ssh root@$FQDN "$SSH"
}

echo $(date)

# import generic functions
source preconditions.sh

# Module specific variables
NEXUS_PASSWORD=$1
SVN_PASSWORD=$2
# TODO subdomain is not actually used when configuring a build slave (no DNS configuration takes place)
SUBDOMAIN=$3
TYPE=$4
QUIET=${5-""}

# Same password for both
ZIP_PASSWORD=$SVN_PASSWORD

# Make sure we have what we need
check_args

# import bootstrap variables/functions
source bootstrap.sh $SUBDOMAIN

if [ "$TYPE" = "slave" ]; then
  configure_slave_before
fi

publish_module
configure_common

if [ "$TYPE" = "master" ]; then
  configure_master
fi

if [ "$TYPE" = "slave" ]; then
  configure_slave_after
fi


echo $(date)
