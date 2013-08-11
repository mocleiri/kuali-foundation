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
package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncryptionServiceContext;
import org.kuali.common.util.spring.env.EnvContext;
import org.kuali.common.util.spring.env.EnvironmentService;

public class EncConfigUtils {

	public static EncryptionServiceContext getEncryptionServiceContext(EnvironmentService service, String passwordKey, String enabledKey, String strengthKey) {
		boolean enabled = service.getBoolean(enabledKey, EncryptionServiceContext.DEFAULT_ENABLED);
		if (enabled) {
			String password = service.getString(passwordKey);
			EncStrength strength = getStrength(service, strengthKey);
			return new EncryptionServiceContext(password, strength, enabled);
		} else {
			return EncryptionServiceContext.DISABLED;
		}
	}

	public static EncStrength getStrength(EnvironmentService service, String key) {
		EncStrength defaultStrength = EncryptionServiceContext.DEFAULT_STRENGTH;
		EnvContext<EncStrength> ctx = EnvContext.<EncStrength> newCtx(key, EncStrength.class, defaultStrength);
		return service.getProperty(ctx);
	}

}
