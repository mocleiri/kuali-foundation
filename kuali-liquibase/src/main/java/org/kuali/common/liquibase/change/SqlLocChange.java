package org.kuali.common.liquibase.change;

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
@DatabaseChange(name = "sqlLoc", description = "Execute SQL from a location", priority = ChangeMetaData.PRIORITY_DEFAULT)
public class SqlLocChange extends AbstractSQLChange {

	private String location;
	private String encoding;

	@DatabaseChangeProperty(description = "The location of the SQL file to load", requiredForDatabase = "all", exampleValue = "classpath:foo.sql")
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
		String sql = LocationUtils.toString(location, encoding);
		String expandedSql = getExpandedSql(sql);
		super.setSql(expandedSql);
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
