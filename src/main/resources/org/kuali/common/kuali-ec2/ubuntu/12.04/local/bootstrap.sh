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
  echo
  echo requires SUBDOMAIN
  echo usage: bootstrap.sh subdomain
  echo
  exit 1
}

function check_args {
  check_not_blank SUBDOMAIN $SUBDOMAIN
}

function enable_root_ssh {
  echo "enable    -> root ssh"
  SSH="sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys"
  ssh ubuntu@$FQDN "$SSH"
}


function publish_module { 
  LOCAL="$HOME/.m2/repository/${project.groupId.path}/${project.artifactId}/${project.version}/${project.artifactId}-${project.version}.jar"
  DIR=/mnt/${project.artifactId}
  FILE=/mnt/${project.artifactId}.jar
  REMOTE=root@$FQDN:$FILE
  echo "copy      -> $REMOTE"
  CMD1="rm -rf $DIR"
  CMD2="apt-get install unzip -y -qq"
  CMD3="unzip -qq $FILE -d $DIR"
  CMD4="chmod -R 755 $DIR"
  CMDS="$CMD1; $CMD2; $CMD3; $CMD4"
  scp $LOCAL $REMOTE > /dev/null 2>&1
  ssh root@$FQDN "$CMDS"
}

SUBDOMAIN=${1-$SUBDOMAIN}

check_args

DOMAIN=kuali.org
FQDN=$SUBDOMAIN.$DOMAIN

BASEDIR=/mnt/${project.artifactId}
MODULES=$BASEDIR/${project.groupId.path}/${project.artifactId}/ubuntu/12.04
