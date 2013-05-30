package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectContext;

public class JdbcProjectContext extends DefaultProjectContext {

	private static ProjectContext instance = new JdbcProjectContext();

	private JdbcProjectContext() {
		super("kuali-jdbc");
	}

	public static ProjectContext getInstance() {
		return instance;
	}

	@Override
	public List<String> getPropertyLocations() {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/common/jdbc/jdbc.properties");
		locations.add("classpath:org/kuali/common/sql/sql.xml");
		locations.add("classpath:org/kuali/common/sql/mysql.xml");
		locations.add("classpath:org/kuali/common/sql/oracle.xml");
		locations.add("classpath:org/kuali/common/sql/h2.xml");
		locations.add("classpath:org/kuali/common/sql/derby.xml");
		return locations;
	}

}
