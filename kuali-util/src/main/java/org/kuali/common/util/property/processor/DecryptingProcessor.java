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

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncryptionService;

public class DecryptingProcessor implements PropertyProcessor {

	public DecryptingProcessor(EncryptionService service) {
		Assert.noNulls(service);
		this.service = service;
	}

	private final EncryptionService service;

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getEncryptedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			String unwrapped = PropertyUtils.unwrapEncryptedValue(value);
			String decrypted = service.decrypt(unwrapped);
			properties.setProperty(key, decrypted);
		}
	}

	public EncryptionService getService() {
		return service;
	}

}
