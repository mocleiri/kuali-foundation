/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.execute;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class SetSourceDbSchemaNameExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SetSourceDbSchemaNameExecutable.class);

	String sourceDbSchemaNameKey = "jdbc.source.db.username";
	String dbBranchQualifierKey = "db.branch.qualifier";
	// eg KS_SOURCE_DB
	String baseSourceDbSchemaName;
	Properties mavenProperties;
	String version;
	boolean skip;

	@Override
	public void execute() {
		// Might be skipping execution altogether
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(mavenProperties, "mavenProperties is null");
		Assert.hasText(baseSourceDbSchemaName, "baseSourceDbSchemaName is blank");

		// Check to see if the source db name is already set
		String existingValue = mavenProperties.getProperty(sourceDbSchemaNameKey);
		if (!StringUtils.isBlank(existingValue)) {
			logger.info("Existing value - [{}={}]", sourceDbSchemaNameKey, existingValue);
			return;
		}

		// Get the qualifier we will append to KS_SOURCE_DB for this branch
		String qualifier = getQualifier(mavenProperties, dbBranchQualifierKey, version);

		// This is the full schema name that Impex will use
		String sourceDbSchemaName = baseSourceDbSchemaName + qualifier;

		// Update the internal Maven properties object with the new value
		mavenProperties.setProperty(sourceDbSchemaNameKey, sourceDbSchemaName);
		logger.debug("Set property - [{}={}]", sourceDbSchemaNameKey, sourceDbSchemaName);
	}

	protected String getQualifier(Properties mavenProperties, String explicitQualifierKey, String version) {
		// Determine if the qualifier is already set
		String qualifier = mavenProperties.getProperty(explicitQualifierKey);
		if (!StringUtils.isBlank(qualifier)) {
			return "_" + qualifier;
		}

		// Parse the version string into a version pojo
		Version v = VersionUtils.getVersion(version);

		// If the version qualifier is blank there is nothing more to do
		if (StringUtils.isBlank(v.getQualifier())) {
			return qualifier;
		}

		// Transform and append the qualifier based on the qualifier from the version
		qualifier = v.getQualifier();
		qualifier = StringUtils.upperCase(qualifier);
		qualifier = StringUtils.replace(qualifier, "-", "_");
		qualifier = StringUtils.replace(qualifier, ".", "_");
		qualifier = "_" + qualifier;
		return qualifier;
	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public void setMavenProperties(Properties mavenProperties) {
		this.mavenProperties = mavenProperties;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String getBaseSourceDbSchemaName() {
		return baseSourceDbSchemaName;
	}

	public void setBaseSourceDbSchemaName(String sourceDbSchemaName) {
		this.baseSourceDbSchemaName = sourceDbSchemaName;
	}

	public String getSourceDbSchemaNameKey() {
		return sourceDbSchemaNameKey;
	}

	public void setSourceDbSchemaNameKey(String sourceDbSchemaNameKey) {
		this.sourceDbSchemaNameKey = sourceDbSchemaNameKey;
	}

	public String getDbBranchQualifierKey() {
		return dbBranchQualifierKey;
	}

	public void setDbBranchQualifierKey(String dbBranchQualifierKey) {
		this.dbBranchQualifierKey = dbBranchQualifierKey;
	}

}
