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
  echo requires BASEDIR JDK NEXUS_PASSWORD
  echo usage: install_java.sh basedir jdk6/jdk7 nexus_password
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
  check_not_blank JDK $JDK
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank JDK_LEVEL $JDK_LEVEL
}

# module specific variables
BASEDIR=${1-$BASEDIR}
JDK=${2-$JDK}
NEXUS_PASSWORD=${3-$NEXUS_PASSWORD}
JDK_LEVEL=${JDK:3:1}

# Make sure we have what we need to continue
check_args

# Change these as new versions become available
JDK6_VERSION=${4-1.6.0-u45}
JDK7_VERSION=${5-1.7.0-u51}

echo $JDK
echo $JDK_LEVEL

