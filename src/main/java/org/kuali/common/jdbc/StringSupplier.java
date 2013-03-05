package org.kuali.common.jdbc;

import java.util.List;

import org.springframework.util.Assert;

public class StringSupplier implements SqlSupplier {

	List<String> strings;
	int index = 0;

	@Override
	public String getSql() {

		Assert.notNull(strings, "strings is null");

		if (index < strings.size()) {
			return strings.get(index++);
		} else {
			return null;
		}
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
