#!/bin/bash
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

M2_REPO=~/.m2/repository
GROUPID=org/kuali/common/util
ARTIFACTID=crlf
CLASSIFIER=jar-with-dependencies
VERSION=${project.version}
JAR=$M2_REPO/$GROUPID/$ARTIFACTID/$VERSION/$ARTIFACTID-$VERSION-$CLASSIFIER.jar

java -jar $JAR ${@}