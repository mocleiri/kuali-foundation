/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo;

/**
 * Authenticates to a database server using credentials with DBA authority and creates an empty database along with a
 * default user for accessing the newly created database. See also <code>impex:drop</code>
 * 
 * @goal create
 */
public class CreateMojo extends SingleDBACommandMojo {

	/**
	 * The database command to execute
	 * 
	 * @parameter expression="${command}" default-value="CREATE"
	 * @required
	 */
	String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
