/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.secure;

import java.io.File;
import java.util.Properties;

public class SecureContext {

	Properties properties = SSHUtil.getDefaultProperties();
	int port = SSHUtil.DEFAULT_SSH_PORT;
	File knownHosts = SSHUtil.DEFAULT_KNOWN_HOSTS;
	File configFile = SSHUtil.DEFAULT_CONFIG_FILE;
	File privateKey;
	String passphrase;
	boolean trust = false;
	String username;
	String password;
	String hostname;
}
