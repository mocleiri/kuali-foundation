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

TOMCAT=tomcat7
BASEDIR=/var/lib/$TOMCAT
LOGS=$BASEDIR/logs
CONF=$BASEDIR/conf
WORK=$BASEDIR/work

# Copy custom jsp's into the conf directory
cp $LOGS/*.jsp $CONF

# Clean out logs, work, and conf/Catalina/localhost
rm -rf $LOGS/* $WORK/* $CONF/Catalina/localhost/*

# Copy custom jsp's back into the logs directory
cp $CONF/*.jsp $LOGS

# Make sure the jsp's are owned by tomcat
chown $TOMCAT:$TOMCAT $LOGS/*.jsp
