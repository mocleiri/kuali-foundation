package org.kuali.common.jdbc.vendor.model.keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Jdbc implements KeySuffix {

	URL("url"), DRIVER("driver");

	private Jdbc(String suffix) {
		this.suffix = suffix;
	}

	private final String suffix;

	@Override
	public String getValue() {
		return suffix;
	}

	public static List<KeySuffix> asList() {
		return Collections.unmodifiableList(new ArrayList<KeySuffix>(Arrays.asList(values())));
	}

}
