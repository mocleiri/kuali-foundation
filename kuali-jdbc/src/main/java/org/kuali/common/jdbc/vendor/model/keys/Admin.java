package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Admin implements KeySuffix {

	VALIDATE("validate"), DROP("drop"), CREATE("create");

	private Admin(String suffix) {
		this.suffix = suffix;
	}

	private final String suffix;

	@Override
	public String getKeySuffix() {
		return suffix;
	}

	public static List<KeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<KeySuffix>(Arrays.asList(Admin.values())));
	}

}
