package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;

public enum Oracle implements EnvironmentKeySuffix {

	KILL_AND_DROP("killAndDrop"), //
	KILL_AND_DROP_RDS("killAndDrop.rds"), //
	SCHEMA_STATS("schemaStats");

	private Oracle(String suffix) {
		this.suffix = suffix;
	}

	private final String suffix;

	@Override
	public String getValue() {
		return suffix;
	}

	public static List<EnvironmentKeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<EnvironmentKeySuffix>(Arrays.asList(values())));
	}

}
