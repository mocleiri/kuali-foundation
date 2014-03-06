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

# generic functions
function check_not_blank {
  if ! [ -n "$2" ]; then 
    echo $1 cannot be blank
    show_usage
  fi
}

function check_args {
  check_not_blank SUBDOMAIN $SUBDOMAIN
}

function enable_root_ssh {
  echo "enable    -> root ssh"
  SSH="sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys"
  ssh ubuntu@$FQDN "$SSH"
}

function checkout_module {
  SVN_REPO=https://svn.kuali.org/repos/foundation
  SVN_PATH=trunk/kuali-ec2
  SVN_URL=$SVN_REPO/$SVN_PATH
  echo "checkout  -> $SVN_URL"

  SVN1="apt-get install subversion -y $QUIET"
  SVN2="rm -rf $BASEDIR"
  SVN3="svn $QUIET checkout $SVN_URL $BASEDIR"
  SSH="$SVN1; $SVN2; $SVN3"
  ssh root@$FQDN "$SSH"
}

# Make sure we have what we need to continue
check_args

LOCAL=/Users
DOMAIN=kuali.org
BASEDIR=/mnt/kuali-ec2
MODULES=$BASEDIR/src/test/resources/ubuntu/12.04
FQDN=$SUBDOMAIN.$DOMAIN

echo "configure -> $FQDN"
enable_root_ssh
checkout_module
