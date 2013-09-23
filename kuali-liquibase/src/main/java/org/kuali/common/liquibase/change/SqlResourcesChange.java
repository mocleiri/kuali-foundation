package org.kuali.common.liquibase.change;

import java.util.List;

import liquibase.change.AbstractSQLChange;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.change.DatabaseChangeProperty;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.database.Database;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.exception.Warnings;
import liquibase.util.StringUtils;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

/**
 * 
 */
@DatabaseChange(name = "sqlResources", description = "Execute SQL from a location", priority = ChangeMetaData.PRIORITY_DEFAULT)
public class SqlResourcesChange extends AbstractSQLChange {

	public static final String RESOURCES_SUFFIX = ".resources";

	private String location;
	private String encoding;

	@DatabaseChangeProperty(description = "A location containing SQL, or a list of SQL resources", requiredForDatabase = "all", exampleValue = "classpath:sql.resources")
	public String getLocation() {
		return location;
	}

	@DatabaseChangeProperty(exampleValue = "UTF-8")
	public String getEncoding() {
		return encoding;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void finishInitialization() throws SetupException {
		Assert.noBlanks(location, encoding);
		Assert.exists(location);
		String content = getContent(location, encoding);
		String expandedSql = getExpandedSql(content);
		super.setSql(expandedSql);
	}

	protected String getContent(String location, String encoding) {
		if (location.endsWith(RESOURCES_SUFFIX)) {
			List<String> locations = LocationUtils.getLocations(location, encoding);
			StringBuilder sb = new StringBuilder();
			for (String loc : locations) {
				String content = LocationUtils.toString(loc);
				sb.append(content);
			}
			return sb.toString();
		} else {
			return LocationUtils.toString(location, encoding);
		}
	}

	protected String getExpandedSql(String sql) {
		ChangeSet changeSet = getChangeSet();
		if (changeSet == null) {
			return sql;
		}
		ChangeLogParameters params = changeSet.getChangeLogParameters();
		if (params == null) {
			return sql;
		}
		return params.expandExpressions(sql);
	}

	@Override
	public ValidationErrors validate(Database database) {
		ValidationErrors validationErrors = new ValidationErrors();
		if (StringUtils.trimToNull(encoding) == null) {
			validationErrors.addError("'encoding' is required");
		}
		if (StringUtils.trimToNull(location) == null) {
			validationErrors.addError("'location' is required");
		}
		if (!LocationUtils.exists(location)) {
			validationErrors.addError("[" + location + "] does not exist");
		}
		return validationErrors;
	}

	@Override
	public Warnings warn(Database database) {
		return new Warnings();
	}

	@Override
	public String getConfirmationMessage() {
		return "SQL in [" + location + "] executed";
	}

}
