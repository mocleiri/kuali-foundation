#!/bin/sh
#
# Copyright 2004-2011 The Kuali Foundation
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


ENV=$1
if [ "$ENV" = "" ]; then
  echo "Must pass in an environment eg 'db.sh 1'"
  exit
fi

cd impex/master
URL=jdbc:oracle:thin:@oracle.rice.kuali.org:1521:ORACLE
ARGS=-Pdb,oracle
ARGS="$ARGS -Dimpex.url=$URL"
ARGS="$ARGS -Dimpex.dba.url=$URL"
ARGS="$ARGS -Dimpex.dba.username=master"
ARGS="$ARGS -Dimpex.username=RICEENV$ENV"
ARGS="$ARGS -Dimpex.password=RICEENV$ENV"
mvn clean install $ARGS

cd ../..
