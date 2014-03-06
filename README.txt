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

---------------------------------
Commands for EC2 medium instances
---------------------------------

mcp -Ptomcat,6,medium -Djdk.version=jdk6/jdk7 -Dheap.max=2g -Dheap.min=512m -Dheap.maxPermSize=512m
mcp -Ptomcat,7,medium -Djdk.version=jdk6/jdk7 -Dheap.max=2g -Dheap.min=512m -Dheap.maxPermSize=512m


--------------------------------
Commands for EC2 large instances
--------------------------------

Tomcat 6 Commands

mvn clean install -Ptomcat,6
mvn clean install -Presetall,6



Tomcat 7 Commands

mvn clean install -Ptomcat
mvn clean install -Presetall
