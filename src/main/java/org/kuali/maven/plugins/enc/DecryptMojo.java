/**
 * Copyright 2009-2012 The Kuali Foundation
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;

/**
 * Decrypt the specified text using the specified password
 * 
 * @goal decrypt
 */
public class DecryptMojo extends AbstractMojo {

	/**
	 * 
	 * The password for decrypting the specified text. This must be the same password that was used to encrypt it.
	 * 
	 * @parameter expression="${enc.password}"
	 * @required
	 */
	private String password;

	/**
	 * 
	 * The encrypted text to decrypt. eg ENC(y9G0trn) -> FOO
	 * 
	 * @parameter expression="${enc.text}"
	 * @required
	 */
	private String text;

	/**
	 * 
	 * The encryption strength, BASIC or STRONG
	 * 
	 * @parameter expression="${enc.strength}" default-value="BASIC"
	 * @required
	 */
	private EncStrength strength;

	@Override
	public void execute() throws MojoExecutionException {
		Assert.noBlanks(password, text);
		Assert.noNulls(strength);
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password, strength);
		String unwrapped = EncUtils.isEncrypted(text) ? EncUtils.unwrap(text) : text;
		String decrypted = encryptor.decrypt(unwrapped);
		getLog().info(text + " -> " + decrypted);
	}
}
