#!/bin/sh
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


if [ "$CATALINA_BASE" = "" ]; then
  echo "CATALINA_BASE is not set"
  exit
fi

# JSP's in the logs dir
ENV1=$CATALINA_BASE/logs/env.jsp
TAIL1=$CATALINA_BASE/logs/tail.jsp

# JSP's in the conf dir
ENV2=$CATALINA_BASE/conf/env.jsp
TAIL2=$CATALINA_BASE/conf/tail.jsp

cp $ENV1 $ENV2
cp $TAIL1 $TAIL2

LOGS1=$CATALINA_BASE/logs 
LOGS2=/home/tomcat/logs
WORK=$CATALINA_BASE/work
CONF=$CATALINA_BASE/conf/Catalina/localhost

echo Removing $LOGS1
echo Removing $LOGS2
echo Removing $WORK
echo Removing $CONF

rm -rf $LOGS1
rm -rf $LOGS2
rm -rf $WORK
rm -rf $CONF

mkdir -p $LOGS1
cp $ENV2 $ENV1
cp $TAIL2 $TAIL1
