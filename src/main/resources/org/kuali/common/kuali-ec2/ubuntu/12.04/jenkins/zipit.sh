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
function check_not_blank {
  if [ ! -n "$2" ]; then 
    echo $1 cannot be blank
    exit 1
  fi
}

ZIP_PASSWORD=${1-$ZIP_PASSWORD}
check_not_blank ZIP_PASSWORD $ZIP_PASSWORD

cd /mnt
rm -rf /mnt/.jenkins /mnt/jenkins.zip
cp -R /home/tomcat7/.jenkins /mnt
rm -rf /mnt/.jenkins/plugins /mnt/.jenkins/config-history
zip -r -e --password $ZIP_PASSWORD jenkins.zip .jenkins
