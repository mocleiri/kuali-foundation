/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionContext;

public final class ContextDecryptingProcessor implements PropertyProcessor {

	public ContextDecryptingProcessor() {
		this(EncryptionContext.DEFAULT);
	}

	public ContextDecryptingProcessor(EncryptionContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final EncryptionContext context;

	@Override
	public void process(Properties properties) {
		Assert.noNulls(properties);
		if (!context.isEnabled()) {
			return;
		}
		String password = context.getPassword().get();
		EncStrength strength = context.getStrength();
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password, strength);
		PropertyUtils.decrypt(properties, encryptor);
	}

	public EncryptionContext getContext() {
		return context;
	}

}
