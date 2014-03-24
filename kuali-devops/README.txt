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


1,099,511,627,776

amzn-ami-minimal-pv-2013.09.2.x86_64-ebs - ami-863909c3
latest amazon linux ami -> ami-863909c3

mci -Pslave; mvn test -Pslave -Dtest=CreateBuildSlaveAMI

SLAVE:
cd ~/ws/kuali-devops; mci -Pslave; chmod -R 755 ~/ws/kuali-devops/target/classes; cd ~/ws/kuali-devops/target/classes/org/kuali/common/kuali-devops/ubuntu/12.04/local;
./jenkins.sh   NEXUS_PASSWORD ZIP_PASSWORD ec2-54-81-87-75.compute-1 slave -qq

MASTER:
cd ~/ws/kuali-devops; mci; chmod -R 755 ~/ws/kuali-devops/target/classes; cd ~/ws/kuali-devops/target/classes/org/kuali/common/kuali-devops/ubuntu/12.04/local;
./appserver.sh NEXUS_PASSWORD ZIP_PASSWORD beta.ci jdk7 tomcat7 5g 256m -qq
./jenkins.sh   NEXUS_PASSWORD ZIP_PASSWORD beta.ci master -qq


encrypt:
gpg --batch --yes --passphrase <password> --cipher-algo AES256 --symmetric secrets.zip

decrypt:
gpg --batch --yes --passphrase <password> --quiet --decrypt --output secrets.zip secrets.zip.gpg
