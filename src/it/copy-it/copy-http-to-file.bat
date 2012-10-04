@REM
@REM Copyright 2008-2012 The Kuali Foundation
@REM
@REM Licensed under the Educational Community License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM http://www.opensource.org/licenses/ecl2.php
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM

REM work with full path on target's file protocol
REM

mvn -e org.codehaus.mojo:wagon-maven-plugin:1.0-beta-1:copy -Dwagon.source=http://people.apache.org/~olamy/staging-repo -Dwagon.target=file:///dev/mojo/sandbox/wagon-maven-plugin/src/it/copy-it/target/maven-repo -Djava.io.tmpdir=target