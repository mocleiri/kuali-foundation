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


if [ "$CATALINA_BASE" = "" ]; then
  echo "CATALINA_BASE is not set"
  exit
fi

ENV=$CATALINA_BASE/logs/env.jsp
TAIL=$CATALINA_BASE/logs/tail.jsp
cp $ENV $CATALINA_BASE/conf/env.jsp
cp $TAIL $CATALINA_BASE/conf/tail.jsp

LOGS=$CATALINA_BASE/logs
WORK=$CATALINA_BASE/work

echo Removing $LOGS
echo Removing $WORK

rm -rf $LOGS
rm -rf $WORK

mkdir $LOGS
cp $CATALINA_BASE/conf/env.jsp $ENV
cp $CATALINA_BASE/conf/tail.jsp $TAIL
