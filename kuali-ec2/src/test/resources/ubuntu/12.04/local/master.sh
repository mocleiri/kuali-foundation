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

FQDN=jeff.ci.kuali.org
SECRETS=$HOME/.ssh/secrets.zip
MASTER=/mnt/kuali-ec2/src/test/resources/ubuntu/12.04/jenkins/master.sh

scp $SECRETS root@$FQDN:/root/secrets.zip
ssh root@$FQDN "cd /mnt/kuali-ec2; svn up; $MASTER"
