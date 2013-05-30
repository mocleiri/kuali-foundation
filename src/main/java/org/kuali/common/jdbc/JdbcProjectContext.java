package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.ProjectUtils;

public class JdbcProjectContext implements ProjectContext {

	private static ProjectContext instance = new JdbcProjectContext();

	private JdbcProjectContext() {
		super();
	}

	public static ProjectContext getInstance() {
		return instance;
	}

	@Override
	public String getGroupId() {
		return ProjectUtils.KUALI_COMMON_GROUP_ID;
	}

	@Override
	public String getArtifactId() {
		return "kuali-jdbc";
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
