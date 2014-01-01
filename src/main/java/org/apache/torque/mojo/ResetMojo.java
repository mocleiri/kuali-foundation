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
package org.apache.torque.mojo;

import java.io.IOException;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.db.DatabaseCommand;
import org.kuali.db.SQLGenerator;
import org.kuali.db.Transaction;

/**
 * Does a DROP->CREATE of a database and the corresponding default user. See also <code>impex:create</code> and
 * <code>impex:drop</code>
 * 
 * @goal reset
 */
public class ResetMojo extends AbstractDBACommandMojo {

	protected Transaction getTransaction(Properties properties, DatabaseCommand command) throws IOException {
		SQLGenerator generator = new SQLGenerator(properties, url, command);
		generator.setEncoding(getEncoding());
		String sql = generator.getSQL();
		Transaction t = new Transaction();
		t.setDescription(getTransactionDescription(command));
		t.addText(sql);
		return t;
	}

	@Override
	protected void configureTransactions() throws MojoExecutionException {
		Properties properties = getContextProperties();
		try {
			transactions.add(getTransaction(properties, DatabaseCommand.DROP));
			transactions.add(getTransaction(properties, DatabaseCommand.CREATE));
		} catch (IOException e) {
			throw new MojoExecutionException("Error generating SQL", e);
		}
	}
}
