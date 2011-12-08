/**
 * Copyright 2011-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.jenkins;

/**
 * Abstraction for a mojo that sends an XML file to a Jenkins server. Jenkins CLI has 2 different commands related to
 * pushing XML files out to a jenkins server.<br>
 *
 * 1 - "create-job"<br>
 * 2 - "update-job"<br>
 */
public abstract class PushJobsMojo extends BaseMojo {

    public abstract String getCmd();

}