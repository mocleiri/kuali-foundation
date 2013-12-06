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

import org.apache.maven.plugin.AbstractMojo;
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
	 * 
	 * The password for decrypting the specified text. This must be the same password that was used to encrypt it.
	 * 
	 * @parameter expression="${enc.password}"
	 * @required
	 */
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

	protected abstract void execute(EncryptionService enc, String text);

	@Override
	public void execute() {
		Assert.noBlanks(text);
		EncContext context = new EncContext.Builder(password).strength(strength).required(true).build();
		EncryptionService enc = new DefaultEncryptionService(context.getTextEncryptor().get());
		execute(enc, text);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EncStrength getStrength() {
		return strength;
	}

	public void setStrength(EncStrength strength) {
		this.strength = strength;
	}
}
