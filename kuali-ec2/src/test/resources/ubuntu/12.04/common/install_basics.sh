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

# Update Ubuntu repos and packages
function get_upgrades {
  echo "update    -> package indexes"
  # not even -qq shuts this up
  apt-get $QUIET -y update > /dev/null 2>&1
  echo "upgrade   -> packages"
  # not even -qq shuts this up
  apt-get $QUIET -y upgrade > /dev/null 2>&1
}

# install custom packages
function install_packages {
  echo "install   -> custom packages"
  # not even -qq shuts this up
  apt-get install zip unzip ntp expect -y $QUIET > /dev/null 2>&1
}

get_upgrades
install_packages
