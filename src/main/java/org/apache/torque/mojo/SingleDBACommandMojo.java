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
 * Runs a command that performs a single operation on a database (create,drop etc)
 */
public abstract class SingleDBACommandMojo extends AbstractDBACommandMojo {

	public abstract String getCommand();

	@Override
	protected void configureTransactions() throws MojoExecutionException {
		Properties properties = getContextProperties();
		SQLGenerator generator = new SQLGenerator(properties, url, DatabaseCommand.valueOf(getCommand().toUpperCase()));
		try {
			generator.setEncoding(getEncoding());
			String sql = generator.getSQL();
			Transaction t = new Transaction();
			t.addText(sql);
			t.setDescription(getTransactionDescription(DatabaseCommand.valueOf(getCommand().toUpperCase())));
			transactions.add(t);
		} catch (IOException e) {
			throw new MojoExecutionException("Error configuring transactions", e);
		}
	}
}
