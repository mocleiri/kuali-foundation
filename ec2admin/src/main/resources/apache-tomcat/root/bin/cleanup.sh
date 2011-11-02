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

OUT=$CATALINA_BASE/logs/*.out
LOG=$CATALINA_BASE/logs/*.log
TLOG=$CATALINA_BASE/logs/*.tlog
TXT=$CATALINA_BASE/logs/*.txt
PID=$CATALINA_BASE/logs/catalina.pid
WORK=$CATALINA_BASE/work

echo Removing $OUT
echo Removing $LOG
echo Removing $TLOG
echo Removing $TXT
echo Removing $PID
echo Removing $WORK

rm -f $OUT
rm -f $LOG
rm -f $TLOG
rm -f $TXT
rm -f $PID
rm -rf $WORK
