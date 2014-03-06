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


function execute_quietly {
  COMMAND=$1
  if [ "$QUIET" = "-qq" ]; then
    $COMMAND > /dev/null 2>&1
  else
    $COMMAND
  fi
}

function install_mysql_server {

   echo "remove    -> mysql-server"
   # Remove all traces of MySQL
   execute_quietly "apt-get remove mysql-server -y"
   execute_quietly "apt-get purge  mysql-server -y"
   execute_quietly "apt-get autoremove -y"

   echo "install   -> mysql-server"
   # Setup the MySQL install so it won't prompt for a password
   MYSQL_ROOT_PASSWORD=password
   debconf-set-selections <<< "mysql-server mysql-server/root_password password $MYSQL_ROOT_PASSWORD"
   debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $MYSQL_ROOT_PASSWORD"
   
   # Install MySQL with no prompts  
   execute_quietly "apt-get install mysql-server -y"
   
   # Remove the password for the root user
   mysqladmin -u root -p$MYSQL_ROOT_PASSWORD password ""
   
}

QUIET=${1-$QUIET}

install_mysql_server