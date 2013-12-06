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
import org.apache.maven.plugins.annotations.Parameter;
import org.kuali.common.util.Assert;

public abstract class AbstractCncMojo extends AbstractMojo {

	/**
	 * The text to conceal / reveal
	 * 
	 * <pre>
	 * FOO -> cnc--SBB
	 * cnc--SBB -> FOO
	 * </pre>
	 */
	@Parameter(property = "enc.text", required = true)
	private String text;

	protected abstract void execute(String text);

	@Override
	public void execute() {
		Assert.noBlanks(text);
		execute(text);
	}

}
