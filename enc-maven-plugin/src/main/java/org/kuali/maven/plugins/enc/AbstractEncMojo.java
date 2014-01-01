/**
 * Copyright 2013-2014 The Kuali Foundation
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
import org.apache.maven.plugins.annotations.Parameter;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncContext;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncryptionService;

/**
 * 
 */
public abstract class AbstractEncMojo extends AbstractMojo {

	/**
	 * The password used to encrypt/decrypt text values.
	 */
	@Parameter(property = "enc.password", required = true)
	private String password;

	/**
	 * 
	 * The text to encrypt/decrypt.
	 * 
	 * <pre>
	 * FOO -> enc--y9G0trn
	 * 
	 * enc--y9G0trn -> FOO
	 * </pre>
	 */
	@Parameter(property = "enc.text", required = true)
	private String text;

	/**
	 * The encryption strength, BASIC or STRONG
	 */
	@Parameter(property = "enc.strength", required = true, defaultValue = "BASIC")
	private EncStrength strength;

	protected abstract void execute(EncryptionService enc, String text);

	@Override
	public void execute() {
		Assert.noBlanks(text);
		EncContext context = new EncContext.Builder(password).strength(strength).required(true).build();
		EncryptionService enc = new DefaultEncryptionService(context.getTextEncryptor().get());
		execute(enc, text);
	}

}
