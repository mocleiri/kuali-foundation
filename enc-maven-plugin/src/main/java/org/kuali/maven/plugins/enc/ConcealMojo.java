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
import org.kuali.common.util.Str;

/**
 * Conceal the specified text. Do <b>NOT</b> use this in an attempt to obscure sensitive data. (eg passwords). The algorithm is trivial and highly insecure. It will only defeat
 * people in sales and marketing :)
 * 
 * @goal conceal
 */
public class ConcealMojo extends AbstractMojo {

	/**
	 * 
	 * The text to conceal
	 * 
	 * @parameter expression="${enc.text}"
	 * @required
	 */
	private String text;

	@Override
	public void execute() {
		Assert.noBlanks(text);
		getLog().info(text + " -> " + Str.conceal(text));
	}
}
