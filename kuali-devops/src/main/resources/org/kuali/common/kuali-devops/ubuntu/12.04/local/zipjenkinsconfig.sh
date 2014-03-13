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
  echo requires GPG_PASSPHRASE SUBDOMAIN
  echo usage: zipjenkinsconfig.sh gpg_passphrase subdomain
  exit 1
}

function check_args {
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  check_not_blank SUBDOMAIN $SUBDOMAIN
}

# connect to a remote server, zip jenkins config, and scp it to the local machine
function scp_zip_file {

  ZIP_FILE="master.zip"
  SERVER_ZIP="/mnt/$ZIP_FILE"
  LOCAL_ZIP="/tmp/$ZIP_FILE"
  GPG_FILE="${project.basedir}/src/main/resources/${project.groupId.path}/${project.artifactId}/jenkins/$ZIP_FILE.gpg"
  
  SSH1="rm -f $SERVER_ZIP"
  SSH2="cd /home/tomcat7"
  #SSH3="zip -qq -r $SERVER_ZIP .jenkins -x '**/plugins/**' '**/workspace/**'"
  SSH3="zip -qq -r $SERVER_ZIP .jenkins -x '**/plugins/**' '**/jobs/**' '**/config-history/**'"
  SSH="$SSH1; $SSH2; $SSH3"

  echo "zip -> root@$FQDN:$SERVER_ZIP"
  ssh root@$FQDN "$SSH"
  echo "scp -> $LOCAL_ZIP"
  scp root@$FQDN:$SERVER_ZIP $LOCAL_ZIP > /dev/null 2>&1
  gpg --batch --yes --passphrase $GPG_PASSPHRASE --cipher-algo AES256 --symmetric --output $GPG_FILE $ZIP_FILE

}

echo $(date)

# import generic functions
source preconditions.sh

# Module specific variables
GPG_PASSPHRASE=${1-$GPG_PASSPHRASE}
SUBDOMAIN=$2

# Make sure we have what we need
check_args

# import bootstrap variables/functions
source bootstrap.sh $SUBDOMAIN

scp_zip_file

echo $(date)
