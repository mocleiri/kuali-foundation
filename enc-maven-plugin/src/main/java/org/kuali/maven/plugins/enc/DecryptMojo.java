/**
 * Copyright 2013-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.enc;

import org.kuali.common.util.enc.EncryptionService;

/**
 * Decrypt the specified text using the specified password
 * 
 * @goal decrypt
 */
public class DecryptMojo extends AbstractEncMojo {

	@Override
	public void execute(EncryptionService enc, String text) {
		String decrypted = enc.decrypt(text);
		getLog().info(text + " -> " + decrypted);
	}
}
