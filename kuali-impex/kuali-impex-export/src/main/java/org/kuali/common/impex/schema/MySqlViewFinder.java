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

public class MySqlViewFinder implements ViewFinder {

	protected final static int VIEW_NAME_INDEX = 1;
	protected final static int VIEW_TEXT_INDEX = 2;

	protected final static String MYSQL_FIND_VIEWS_STATEMENT = "SELECT table_name, view_definition FROM information_schema.views WHERE table_schema = ?";

	public final static String SUPPORTED_VENDOR = "mysql";

	@Override
	public List<View> findViews(Connection connection, String schema, StringFilter nameFilter) throws SQLException {
		List<View> views = getViews(connection, schema);
		ModelUtils.filterAndSortElements(views, nameFilter);
		return views;
	}

	protected List<View> getViews(Connection connection, String schema) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<View> views = new ArrayList<View>();
			ps = connection.prepareStatement(MYSQL_FIND_VIEWS_STATEMENT);
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
