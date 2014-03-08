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
  echo requires ZIP_PASSWORD SUBDOMAIN
  echo usage: zipjenkinsconfig.sh zip_password subdomain
  exit 1
}

function check_args {
  check_not_blank ZIP_PASSWORD $ZIP_PASSWORD
  check_not_blank SUBDOMAIN $SUBDOMAIN
}

function create_zip_file {

  ZIP_FILE="master_config.zip"
  SERVER_ZIP="/tmp/$ZIP_FILE"
  LOCAL_ZIP="$BASEDIR/${project.groupId.path}/${project.artifactId}/jenkins/$ZIP_FILE"
  
  SSH1="rm -f $SERVER_ZIP"
  SSH2="cd /home/tomcat7"
  SSH3="zip -e --password $ZIP_PASSWORD -r $SERVER_ZIP .jenkins -x '**/plugins/*' '**/config-history/*' '**/jobs/*' '**/users/*'"
  SSH="$SSH1; $SSH2; $SSH3"

  ssh root@$FQDN "$SSH"
  scp root@$FQDN:$SERVER_ZIP $LOCAL_ZIP 

}

echo $(date)

# import generic functions
source preconditions.sh

# Module specific variables
ZIP_PASSWORD=$1
SUBDOMAIN=$2

# Make sure we have what we need
check_args

# import bootstrap variables/functions
source bootstrap.sh $SUBDOMAIN

echo $(date)
