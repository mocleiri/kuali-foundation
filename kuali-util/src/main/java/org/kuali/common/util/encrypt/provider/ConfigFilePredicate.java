package org.kuali.common.util.encrypt.provider;

import com.google.common.base.Predicate;

public enum ConfigFilePredicate implements Predicate<String> {
	INSTANCE;

	@Override
	public boolean apply(String input) {
		return input != null && !input.trim().equals("") && !input.trim().startsWith("#");
	}

}
