package org.kuali.common.jdbc.sql.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.spring.env.model.EnvironmentKey;

public enum SqlKeys implements EnvironmentKey {

	THREADS("sql.threads"), //
	ENCODING("sql.encoding"), //
	SCHEMA("sql.schema"), //
	USERNAME("sql.username"), //
	PASSWORD("sql.password"), //
	VALIDATE("sql.validate"), //
	CREATE("sql.create"), //
	DROP("sql.drop"), //
	DBA_BEFORE("sql.dba.before"), //
	DBA_AFTER("sql.dba.after");

	private SqlKeys(String value) {
		this.value = value;
	}

	private String value;

	@Override
	public String getValue() {
		return this.value;
	}

	public static List<EnvironmentKey> asList() {
		return Collections.unmodifiableList(new ArrayList<EnvironmentKey>(Arrays.asList(values())));
	}
}
