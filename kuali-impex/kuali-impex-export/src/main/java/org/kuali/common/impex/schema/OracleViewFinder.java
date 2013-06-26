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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.View;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.StringFilter;

public class OracleViewFinder implements ViewFinder {

	protected final static int VIEW_NAME_INDEX = 1;
	protected final static int VIEW_TEXT_INDEX = 2;

	protected final static String ORACLE_FIND_VIEWS_STATEMENT = "SELECT view_name, text FROM all_views WHERE owner = ?";

	public final static String SUPPORTED_VENDOR = "oracle";

	@Override
	public List<View> findViews(Connection connection, String schema, StringFilter nameFilter) throws SQLException {

		// Make JDBC calls to extract the view information
		List<View> views = getViews(connection, schema);

		// Use generic logic to filter out and sort the list of views
		ModelUtils.filterAndSortElements(views, nameFilter);

		// Return the sorted list
		return views;
	}

	/**
	 * Connect to a database and extract view information
	 */
	protected List<View> getViews(Connection connection, String schema) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<View> views = new ArrayList<View>();
			ps = connection.prepareStatement(ORACLE_FIND_VIEWS_STATEMENT);
			ps.setString(1, schema);
			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString(VIEW_NAME_INDEX);
				String query = rs.getString(VIEW_TEXT_INDEX);
				views.add(new View(name, query));
			}
			return views;
		} finally {
			JdbcUtils.closeQuietly(rs);
			JdbcUtils.closeQuietly(ps);
		}
	}
}
