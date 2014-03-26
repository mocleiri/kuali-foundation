====
    Copyright 2004-2014 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
====

# spin up a master/slave combo
svn up; mci; mvn test -Dtest=CreateBuildSlaveAMI -Dec2.stack=test -Dec2.region=us-west-2
svn up; mci; mvn test -Dtest=SpinUpJenkinsMaster -Dec2.stack=test -Dec2.region=us-west-2

# Sync maven.kuali.org/release and maven.kuali.org/external to the local m2 repo
mvn initialize -Pupdate -Dorg.slf4j.simpleLogger.log.org.kuali.maven.wagon=warn -f /root/.bootstrap/kuali-devops/META-INF/maven/org.kuali.common/kuali-devops/pom.xml