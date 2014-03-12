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

mci -Pslave; mvn test -Pslave -Dtest=CreateBuildSlaveAMI

SLAVE:
cd ~/ws/kuali-devops; mci -Pslave; chmod -R 755 ~/ws/kuali-devops/target/classes; cd ~/ws/kuali-devops/target/classes/org/kuali/common/kuali-devops/ubuntu/12.04/local;
./jenkins.sh   NEXUS_PASSWORD SVN_PASSWORD ec2-54-81-87-75.compute-1 slave -qq

MASTER:
cd ~/ws/kuali-devops; mci; chmod -R 755 ~/ws/kuali-devops/target/classes; cd ~/ws/kuali-devops/target/classes/org/kuali/common/kuali-devops/ubuntu/12.04/local;
./appserver.sh NEXUS_PASSWORD ZIP_PASSWORD beta.ci jdk7 tomcat7 5g 256m -qq
./jenkins.sh   NEXUS_PASSWORD ZIP_PASSWORD beta.ci master

