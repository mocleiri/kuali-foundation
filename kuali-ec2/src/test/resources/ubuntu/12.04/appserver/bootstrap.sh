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

echo $(date)

# Subversion
SVN_REPO=https://svn.kuali.org/repos/foundation
SVN_PATH=trunk/kuali-ec2
SVN_URL=$SVN_REPO/$SVN_PATH
SVN_DIR=/mnt/kuali-ec2
SCRIPTS_DIR=$SVN_DIR/src/test/resources/ubuntu/12.04/appserver

# DNS
DOMAIN=kuali.org

# Nexus
NEXUS_PASSWORD=${1-NOTDEFINED}
if [[ $NEXUS_PASSWORD == "NOTDEFINED" ]]; then
echo "nexus password is required"
exit 1
fi

HOSTNAME=${2-NOTDEFINED}
if [[ $HOSTNAME == "NOTDEFINED" ]]; then
echo "hostname is required"
exit 1
fi

FQDN=$HOSTNAME.$DOMAIN

JDK=${3-7}
TOMCAT=${4-7}
MAX_HEAP=${5-5g}
MAX_PERM=${6-512m}
QUIET=${7-""}

CONFIGURE=$SCRIPTS_DIR/configure.sh
SVN1="apt-get install subversion -y $QUIET"

# Enable root ssh
echo "enable    -> root ssh"
ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

# Checkout kuali-ec2 on the remote server and run configure.sh
echo "configure -> application server"
ssh root@$FQDN "$SVN1; rm -rf $SVN_DIR; svn $QUIET checkout '$SVN_URL' '$SVN_DIR'; '$CONFIGURE' '$NEXUS_PASSWORD' '$HOSTNAME' '$JDK' '$TOMCAT' '$MAX_HEAP' '$MAX_PERM' '$QUIET'"

echo $(date)
