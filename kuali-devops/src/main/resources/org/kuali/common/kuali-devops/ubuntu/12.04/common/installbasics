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

# Update Ubuntu repos and default packages
function get_upgrades {
  echo "update    -> package indexes"
  execute_quietly "apt-get update -y"
  echo "upgrade   -> packages"
  execute_quietly "apt-get upgrade -y"
}

# Install general purpose packages applicable to all kuali nodes
function install_packages {
  echo "install   -> custom packages"
  PACKAGES="zip unzip ntp"
  execute_quietly "apt-get install $PACKAGES -y"
}

# Functionally equivalent to running "dpkg-reconfigure unattended-upgrades" and answering "Yes" to the prompt
# http://askubuntu.com/questions/203337/enabling-unattended-upgrades-from-a-shell-script
function unattended_upgrades {
  echo "configure -> unattended upgrades"
  UNATTENDED_FILE="/etc/apt/apt.conf.d/20auto-upgrades"
  echo "APT::Periodic::Update-Package-Lists \"1\";" >  $UNATTENDED_FILE 
  echo "APT::Periodic::Unattended-Upgrade \"1\";"   >> $UNATTENDED_FILE 
}

MY_DIR="$( cd "$( dirname "$0" )" && pwd )"
source $MY_DIR/functions.sh

get_upgrades
install_packages
unattended_upgrades
