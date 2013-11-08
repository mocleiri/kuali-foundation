/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.devops.ci;

import org.kuali.common.util.main.MainUtils;
import org.kuali.common.util.main.spring.AbstractMainRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProvisionCIMaster extends AbstractMainRunner {

	public static void main(String[] args) {
		MainUtils.runAndExit(ProvisionCIMaster.class, args, true);
	}

	@Override
	protected Class<?> getConfig() {
		return ProvisionCIMasterConfig.class;
	}

}
