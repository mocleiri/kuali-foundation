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

function check_arg {
  if [ -n "$1" ]; then 
    show_usage
  else
    echo $1
  fi
}

function show_usage {
  echo
  echo usage: simple.sh jdk6/jdk7 
  echo
  exit 1
}

JDK=jdk8
JDK=${1-$JDK}
echo $JDK

JDK=$(check_arg $JDK)
