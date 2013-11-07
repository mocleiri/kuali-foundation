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
 * Encrypt the specified text using the specified password
 * 
 * @goal encrypt
 */
public class EncryptMojo extends AbstractMojo {

	/**
	 * 
	 * The password for encrypting text. This same password must be used to decrypt the encrypted text
	 * 
	 * @parameter expression="${enc.password}"
	 * @required
	 */
	private String password;

	/**
	 * 
	 * The text to encrypt. eg FOO -> ENC(y9G0trn)
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
		Assert.isFalse(EncUtils.isEncrypted(text), "Already encrypted");
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password, strength);
		String encrypted = encryptor.encrypt(text);
		String wrapped = EncUtils.wrap(encrypted);
		getLog().info(text + " -> " + wrapped);
	}

}
