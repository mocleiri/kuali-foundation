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

package org.kuali.common.impex.service;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

/**
 * @author andrewlubbers
 */
@Ignore
public class TestMySQLBigDataImpex {

	@Test
	public void test() {
		SpringService ss = new DefaultSpringService();
		// System.setProperty("jdbc.data.skip", "true");
		ss.load("classpath:impex/mysql-impex-context.xml");
	}

}
