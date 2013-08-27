package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;

public enum Dba implements EnvironmentKeySuffix {

	USERNAME("dba.username"), //
	URL("dba.url"), //
	BEFORE("dba.before"), //
	AFTER("dba.after"), //
	PASSWORD("dba.password");

	private Dba(String value) {
		this.value = value;
	}

	private final String value;

	@Override
	public String getValue() {
		return value;
	}

	public static List<EnvironmentKeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<EnvironmentKeySuffix>(Arrays.asList(values())));
	}

}
