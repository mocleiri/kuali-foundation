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
package org.kuali.common.util.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides an easy way to make Spring to "do something". Somewhat similar to <code>public static void main(String[] args)</code> in a regular Java class. This provides a common
 * spot for extending classes to capture what it is they want to to in an <code>Executable</code> and then have Spring automatically execute it after the designated
 * <code>Executable</code> has been fully initialized.
 */
@Configuration
public abstract class ExecutableConfig {

	protected abstract Executable getExecutable();

	@Bean(initMethod = "execute")
	public Executable executable() {
		return getExecutable();
	}
}
