#!/bin/sh
#
# Copyright 2004-2013 The Kuali Foundation
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


CATALINA_BASE=${catalina.base}

cp $CATALINA_BASE/logs/*.jsp $CATALINA_BASE/conf

LOGS=$CATALINA_BASE/logs 
WORK=$CATALINA_BASE/work
TEMP=$CATALINA_BASE/temp
CONF=$CATALINA_BASE/conf/Catalina/localhost

echo Cleaning $LOGS $WORK $CONF $TEMP

rm -rf $LOGS/* $WORK/* $TEMP/* $CONF/*

cp $CATALINA_BASE/conf/*.jsp $CATALINA_BASE/logs
