#!/bin/sh
#
# Copyright 2004-2012 The Kuali Foundation
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

JAVA_HOME=/var/lib/jenkins/tools/jdk7
CATALINA_HOME=/usr/local/tomcat
CATALINA_BASE=$CATALINA_HOME
CATALINA_PID=$CATALINA_BASE/logs/catalina.pid
CATALINA_OPTS="-Xmx2g -XX:MaxPermSize=256m"
JENKINS_HOME=/var/lib/jenkins

export JAVA_HOME CATALINA_HOME CATALINA_BASE CATALINA_OPTS JENKINS_HOME CATALINA_PID

case "$1" in
  start)
    $CATALINA_HOME/bin/startup.sh
    ;;
  stop)
    $CATALINA_HOME/bin/forced-shutdown.sh
    ;;
  cleanup)
    $CATALINA_HOME/bin/cleanup.sh
    ;;
  *)
    echo "Usage: $SCRIPTNAME {start|stop|cleanup}" >&2
    exit 3
    ;;
esac

