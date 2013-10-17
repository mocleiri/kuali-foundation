package org.kuali.common.jdbc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.spring.env.model.EnvironmentKey;

public enum JdbcKeys implements EnvironmentKey {

	URL("jdbc.url"), //
	USERNAME("jdbc.username"), //
	PASSWORD("jdbc.password"), //
	DRIVER("jdbc.driver"), //
	DBA_URL("jdbc.dba.url"), //
	DBA_USERNAME("jdbc.dba.username"), //
	DBA_PASSWORD("jdbc.dba.password"); //

	private JdbcKeys(String value) {
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
