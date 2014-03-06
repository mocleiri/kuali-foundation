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
  echo requires SUBDOMAIN SVN_PASSWORD TYPE
  echo usage: jenkins.sh subdomain svn_password type [quiet]
  exit 1
}

function check_args {
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank SVN_PASSWORD $SVN_PASSWORD
  check_not_blank TYPE $TYPE
}

function transfer_secrets {
  SECRETS=$HOME/.ssh/secrets.zip
  check_exists $SECRETS
  DEST=root@$FQDN:/root/.ssh/secrets.zip
  echo "create    -> $DEST"
  SCP="scp $SECRETS $DEST"
  if [ "$QUIET" = "-qq" ]; then
    $SCP > /dev/null 2>&1
  else
    $SCP
  fi
}

function configure_common {
  echo "configure -> jenkins:common"
  COMMON="$MODULES/jenkins/common.sh $SVN_PASSWORD $QUIET"
  SSH="$COMMON"
  ssh root@$FQDN "$SSH"
}

function configure_master {
  echo "configure -> jenkins:master"
  SERVER="$MODULES/jenkins/master.sh"
  SSH="$SERVER"
  ssh root@$FQDN "$SSH"
}

echo $(date)

# import generic functions
source preconditions.sh

# Module specific variables
SUBDOMAIN=$1
SVN_PASSWORD=$2
TYPE=$3
QUIET=${4-""}


# Make sure we have what we need
check_args

# import bootstrap variables/functions
source bootstrap.sh $SUBDOMAIN

checkout_module
transfer_secrets
configure_common

if [ "$TYPE" = "master" ]; then
  configure_master
fi


echo $(date)
