package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Liquibase implements KeySuffix {

	DROP("liquibase.drop"), CREATE("liquibase.create");

	private Liquibase(String suffix) {
		this.suffix = suffix;
	}

	private final String suffix;

	@Override
	public String getKeySuffix() {
		return suffix;
	}

	public static List<KeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<KeySuffix>(Arrays.asList(values())));
	}

}
