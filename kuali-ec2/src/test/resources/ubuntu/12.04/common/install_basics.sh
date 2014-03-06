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

# module specific functions
function show_usage {
  echo
  echo requires BASEDIR
  echo usage: install_basics.sh basedir [quiet]
  echo
  exit 1
}

function check_args {
  check_not_blank BASEDIR $BASEDIR
}

# Update Ubuntu repos and packages
function get_upgrades {
  echo "update    -> package indexes"
  apt-get $QUIET -y update
  echo "upgrade   -> packages"
  apt-get $QUIET -y upgrade
}

# Enable unattended upgrades
function unattended_upgrades {
  echo "configure -> unattended upgrades"
  $BASEDIR/src/test/resources/ubuntu/12.04/common/unattended-upgrades.sh > /dev/null 2>&1
}

# install custom packages
function install_packages {
  echo "install   -> custom packages"
  apt-get install unzip ntp expect -y $QUIET 
}

# module specific variables
BASEDIR=${1-$BASEDIR}
QUIET=${2-$QUIET}

# Make sure we have what we need to continue
check_args

get_upgrades
unattended_upgrades
install_packages
