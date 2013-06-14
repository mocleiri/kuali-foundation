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

public class OracleSequenceFinder implements SequenceFinder {

	protected static final String SEQUENCE_QUERY_PREFIX = "SELECT SEQUENCE_NAME as name, LAST_NUMBER as value FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = '";
	protected static final String SEQUENCE_QUERY_SUFFIX = "'";

	protected static final String SEQUENCE_NAME_KEY = "name";
	protected static final String SEQUENCE_VALUE_KEY = "value";

	String sequenceOwner;

	Connection connection;

	public OracleSequenceFinder(Connection connection, String sequenceOwner) {
		this.connection = connection;
		this.sequenceOwner = sequenceOwner;
	}

	@Override
	public List<Sequence> findSequences() throws SQLException {
		String query = SEQUENCE_QUERY_PREFIX + sequenceOwner + SEQUENCE_QUERY_SUFFIX;

		Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(query);

		List<Sequence> results = new ArrayList<Sequence>();

		while (rs.next()) {
			String name = rs.getString(SEQUENCE_NAME_KEY);
			String value = rs.getString(SEQUENCE_VALUE_KEY);

			results.add(new Sequence(name, value));
		}

		return results;
	}
}
