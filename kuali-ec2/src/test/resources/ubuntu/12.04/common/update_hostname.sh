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
  echo requires HOSTNAME DOMAIN
  echo usage: update_hostname.sh hostname domain
  echo
  exit 1
}

function check_args {
  check_not_blank HOSTNAME $HOSTNAME
  check_not_blank DOMAIN $DOMAIN
}

# Set hostname and FQDN
function set_hostname {

  MYIP=$(ifconfig eth0 |grep inet | awk '{ print $2 }' | awk 'BEGIN { FS=":" } { print $2 }')

  ETC_HOSTS="$MYIP $HOSTNAME.$DOMAIN $HOSTNAME"
  echo "hostname  -> $HOSTNAME"
  echo "hosts     -> $ETC_HOSTS"

  echo "$HOSTNAME" > /etc/hostname
  hostname $HOSTNAME

  echo '127.0.0.1 localhost' > /etc/hosts
  echo $ETC_HOSTS >> /etc/hosts
 
  # Mike's magic line here always appends to /etc/hosts
  # This breaks with the concept of idempotence
  # And also if for some reason we gave the machine a new DNS name I think we'd have issues 
  #eval "sed -i -e '/127.0.0.1/a$ETC_HOSTS' /etc/hosts"
  
}

# module specific variables
HOSTNAME=${1-$HOSTNAME}
DOMAIN=${2-$DOMAIN}

# Make sure we have what we need to continue
check_args

set_hostname
