/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.ec2;

import org.kuali.common.util.main.MainUtils;
import org.kuali.common.util.main.spring.AbstractMainRunner;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvokeEC2Service extends AbstractMainRunner {

	public static void main(String[] args) {
		MainUtils.runAndExit(InvokeEC2Service.class, args, true);
	}

	@Override
	protected Class<? extends PropertySourceConfig> getPropertySourceConfig() {
		return InvokeEC2ServicePSC.class;
	}

	@Override
	protected Class<?> getConfig() {
		return InvokeEC2ServiceConfig.class;
	}

}
