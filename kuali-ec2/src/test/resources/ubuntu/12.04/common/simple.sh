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
  echo requires BASEDIR TOMCAT MAX_HEAP MAX_PERM QUIET
  echo usage: install_tomcat.sh basedir tomcat6/tomcat7 max_heap max_perm [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
  check_not_blank TOMCAT_VERSION $TOMCAT_VERSION
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
}

# Module specific variables
BASEDIR=${1-$BASEDIR}
TOMCAT=${2-$TOMCAT}
MAX_HEAP=${3-$MAX_HEAP}
MAX_PERM=${4-$MAX_PERM}
QUIET=${5-$QUIET}

# Makes sure we have what we need to continue
check_args

