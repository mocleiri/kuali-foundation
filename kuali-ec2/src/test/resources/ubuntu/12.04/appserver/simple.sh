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

# module specific functions
function show_usage {
  echo
  echo requires SUBDOMAIN NEXUS_PASSWORD
  echo usage: bootstrap.sh subdomain password [jdk6/jdk6] [tomcat6/tomcat7] [max_heap] [max_perm] [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank JDK $JDK
  check_not_blank TOMCAT $TOMCAT
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
}

echo $(date)

# Module specific variables
SUBDOMAIN=$1
NEXUS_PASSWORD=$2
JDK=${3-jdk7}
TOMCAT=${4-tomcat7}
MAX_HEAP=${5-5g}
MAX_PERM=${6-512m}
QUIET=$7

# Make sure we have what we need to continue
check_args

DOMAIN=kuali.org
BASEDIR=/mnt/kuali-ec2
MODULES=$BASEDIR/src/test/resources/ubuntu/12.04
FQDN=$SUBDOMAIN.$DOMAIN

# Subversion
SVN_REPO=https://svn.kuali.org/repos/foundation
SVN_PATH=trunk/kuali-ec2
SVN_URL=$SVN_REPO/$SVN_PATH

SVN1="apt-get install subversion -y $QUIET"
SVN2="rm -rf $BASEDIR"
SVN3="svn $QUIET checkout $SVN_URL $BASEDIR"
SVN="$SVN1; $SVN2; $SVN3"

echo $(date)


