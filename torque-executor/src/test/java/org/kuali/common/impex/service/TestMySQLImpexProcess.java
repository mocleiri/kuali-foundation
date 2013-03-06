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

import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.DatabaseResetExecutable;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author andrewlubbers
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mpxTests/mysql-impex-context.xml" })
@Ignore
public class TestMySQLImpexProcess extends AbstractTestImpexProcess {

	private static final Logger logger = LoggerFactory.getLogger(TestMySQLImpexProcess.class);

	@Test
	public void test() throws Exception {
		logger.info("Starting database dump");

		doTest();
	}

}
