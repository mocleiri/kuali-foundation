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
  echo requires NEXUS_PASSWORD ZIP_PASSWORD SUBDOMAIN TYPE
  echo usage: jenkins.sh nexus_password zip_password subdomain type [quiet]
  exit 1
}

function check_args {
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank ZIP_PASSWORD $ZIP_PASSWORD
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
  JDK=jdk6; source $MY_DIR/installers.sh; JAVA6=$JAVA
  JDK=jdk7; source $MY_DIR/installers.sh; JAVA7=$JAVA
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

MY_DIR="$( cd "$( dirname "$0" )" && pwd )"
source $MY_DIR/../common/functions.sh

# Module specific variables
GPG_PASSPHRASE=$1
SUBDOMAIN=$2
TYPE=$3
QUIET=${4-""}

# Make sure we have what we need
check_args

# import bootstrap variables/functions
source $MY_DIR/../common/bootstrap.sh $SUBDOMAIN

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
