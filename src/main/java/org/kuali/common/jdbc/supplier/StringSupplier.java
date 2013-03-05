package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.springframework.util.Assert;

public class StringSupplier implements SqlSupplier {

	private int index = 0;

	List<String> strings;

	@Override
	public synchronized String getSql() {

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
