/**
 * Copyright 2006-2013 The Kuali Foundation
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
package org.codehaus.mojo.sql;

import java.util.Vector;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * Unit test for simple SqlExecMojo.
 */
public class SqlExecMojoTest {
	private SqlExecMojo mojo = new SqlExecMojo();

	@Test
	public void test1() throws MojoExecutionException {
		try {
			mojo.setResourceListingLocation("classpath:locations.listing");
			SqlResource[] resources = mojo.getResources(null, mojo.getResourceListingLocation());
			System.out.println(resources.length);
			for (SqlResource resource : resources) {
				System.out.println(resource.getResource().getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() throws MojoExecutionException {
		try {
			mojo.setResourceListingLocation("classpath:locations.listing");
			String[] locations = new String[] { "classpath:a.properties" };
			SqlResource[] resources = mojo.getResources(locations, mojo.getResourceListingLocation());
			System.out.println(resources.length);
			for (SqlResource resource : resources) {
				System.out.println(resource.getResource().getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3() throws MojoExecutionException {
		try {
			mojo.setResourceListingLocation("classpath:locations.listing");
			mojo.addResourcesToTransactions();
			Vector<SqlExecMojo.Transaction> transactions = mojo.transactions;
			System.out.println(transactions.size());
			for (SqlExecMojo.Transaction transaction : transactions) {
				System.out.println("[" + transaction.resource.location + "] [" + transaction.resource.getResource().getURI() + "]");
			}
			DefaultResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(mojo.getResourceListingLocation());
			System.out.println(resource.getURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
