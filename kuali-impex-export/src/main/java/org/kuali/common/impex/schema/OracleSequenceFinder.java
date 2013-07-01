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

package org.kuali.common.impex.schema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.StringFilter;

public class OracleSequenceFinder implements SequenceFinder {

	public static final String SUPPORTED_VENDOR = "oracle";

	protected static final String SEQUENCE_QUERY_PREFIX = "SELECT SEQUENCE_NAME as name, LAST_NUMBER as value FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = '";
	protected static final String SEQUENCE_QUERY_SUFFIX = "'";

	protected static final String SEQUENCE_NAME_KEY = "name";
	protected static final String SEQUENCE_VALUE_KEY = "value";

	@Override
	public List<Sequence> findSequences(Connection connection, String schema, StringFilter nameFilter) throws SQLException {

		// Make sure we are configured correctly
		Assert.hasText(schema, "schema has no text");

		// Connect to the database and get Sequence objects
		List<Sequence> sequences = getSequences(connection, schema);

		// Filter out sequences we don't care about and sort by name
		ModelUtils.filterAndSortElements(sequences, nameFilter);

		// Return the list of sequences
		return sequences;
	}

	/**
	 * Given a schema name and a connection to a database, return all the sequences for that schema
	 */
	protected List<Sequence> getSequences(Connection connection, String schema) throws SQLException {

		// Setup the sequence query based on the schema we've been provided
		String query = SEQUENCE_QUERY_PREFIX + schema + SEQUENCE_QUERY_SUFFIX;

		// Setup JDBC objects
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Initialize a statement
			stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			// Connect to the database and extract the sequence information
			rs = stmt.executeQuery(query);

			// Setup some storage for the sequence objects
			List<Sequence> sequences = new ArrayList<Sequence>();

			// Iterate over the result set creating Sequence objects as we go
			while (rs.next()) {

				// Convert each row in the result set into a Sequence object
				Sequence sequence = getSequence(rs);

				// Add each sequence to the list
				sequences.add(sequence);
			}

			// Return what we've found
			return sequences;

		} finally {

			// Close the ResultSet
			JdbcUtils.closeQuietly(rs);

			// Close the Statement
			JdbcUtils.closeQuietly(stmt);
		}
	}

	/**
	 * Convert the current row in the ResultSet into a Sequence object
	 */
	protected Sequence getSequence(ResultSet rs) throws SQLException {
		String name = rs.getString(SEQUENCE_NAME_KEY);
		String value = rs.getString(SEQUENCE_VALUE_KEY);
		return new Sequence(name, value);
	}

}
