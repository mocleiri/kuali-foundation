/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import junit.textui.TestRunner;

public class DatabaseUtilsTest extends TestCase {
	public static void main(String[] args) {
		TestRunner.run(DatabaseUtilsTest.class);
	}

	@SuppressWarnings("unchecked")
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(JDBCUtils.JDBC_CONTEXT);
		List<JDBCConfiguration> databaseConfigs = (List<JDBCConfiguration>) ctx.getBean(JDBCUtils.JDBC_CONFIGURATIONS);
		assertNotNull(databaseConfigs);
	}

	@SuppressWarnings("unchecked")
	public void test2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(JDBCUtils.JDBC_CONTEXT);
		List<JDBCConfiguration> databaseConfigs = (List<JDBCConfiguration>) ctx.getBean(JDBCUtils.JDBC_CONFIGURATIONS);
		assertNotNull(databaseConfigs);
		DatabaseType[] typesArray = DatabaseType.values();

		Set<DatabaseType> types = new HashSet<DatabaseType>();
		for (DatabaseType type : typesArray) {
			types.add(type);
		}

		for (JDBCConfiguration databaseConfig : databaseConfigs) {
			assertTrue(types.contains(databaseConfig.getType()));
		}

	}
}
