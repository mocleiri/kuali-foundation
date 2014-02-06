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

import com.google.common.base.Optional;

public final class JasyptDecryptingProcessor implements PropertyProcessor {

	public JasyptDecryptingProcessor(Optional<TextEncryptor> encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	private final Optional<TextEncryptor> encryptor;

	@Override
	public void process(Properties properties) {
		Assert.noNulls(properties);
		if (encryptor.isPresent()) {
			PropertyUtils.decrypt(properties, encryptor.get());
		}
	}

	public Optional<TextEncryptor> getTextEncryptor() {
		return encryptor;
	}

}
