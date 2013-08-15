package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Oracle implements KeySuffix {

	KILL_AND_DROP("killAndDrop"), KILL_AND_DROP_RDS("killAndDrop.rds"), SCHEMA_STATS("schemaStats");

	private Oracle(String value) {
		this.value = value;
	}

	private final String value;

	@Override
	public String getKeySuffix() {
		return value;
	}

	public static List<KeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<KeySuffix>(Arrays.asList(Oracle.values())));
	}

}
