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

LOGS=$CATALINA_BASE/logs 
WORK=$CATALINA_BASE/work
TEMP=$CATALINA_BASE/temp
CONF=$CATALINA_BASE/conf

cp $LOGS/*.jsp $CONF

echo Cleaning $LOGS/* $WORK/* $TEMP/* $CONF/Catalina/localhost/*
rm -rf $LOGS/* $WORK/* $TEMP/* $CONF/Catalina/localhost/*

cp $CONF/*.jsp $LOGS
