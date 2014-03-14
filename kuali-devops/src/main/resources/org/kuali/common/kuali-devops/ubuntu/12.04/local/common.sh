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

function check_args {
  check_not_blank DOMAIN $DOMAIN
  check_not_blank SUBDOMAIN $SUBDOMAIN
}

usage() { echo "Usage: $ME [-h] [-q] [-d kuali.org] subdomain" 1>&2; exit 1; }

DOMAIN=kuali.org

QUIET=false
while getopts hqd: flag; do
  case $flag in
    h)
      usage;
      ;;
    q)
      QUIET="true";
      ;;
    d)
      DOMAIN="$OPTARG"
      ;;
    ?)
      usage;
      exit;
      ;;
  esac
done

shift $(( OPTIND - 1 ));

SUBDOMAIN=$1

check_args

FQDN=$SUBDOMAIN.$DOMAIN
