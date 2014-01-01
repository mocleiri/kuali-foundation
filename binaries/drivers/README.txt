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

- Updated JDBC drivers that are in sync with Amazon RDS
- The JDBC drivers checked into src/main/resources/drivers/*.jar are deleted before Maven packages this project
- The JDBC drivers in this directory are then copied into classes/drivers/*.jar so the Ant build scripting can find and use them
- The reason for this, is that Oracle blows up if you try and run this tooling against an Oracle 11g database using the ojdbc6.jar checked into SVN

- This whole setup is a completely hacked together bunch of ridiculousness.
