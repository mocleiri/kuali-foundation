package org.kuali.common.impex.pojo.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.common.base.Optional;

public class ColumnDefaultValueAdapter2 extends XmlAdapter<String, Optional<String>> {

	@Override
	public final String marshal(Optional<String> value) {
		if (value.isPresent()) {
			return value.get();
		} else {
			return null;
		}
	}

	@Override
	public Optional<String> unmarshal(String value) {
		if (value == null) {
			return Optional.<String> absent();
		} else {
			return Optional.of(value.trim());
		}
	}

	/**
	 * In theory, JDBC drivers wrap all text values in single quotes.  Thus we should be safe in trimming
	 * @param value
	 * @return
	 */
	protected String getTrimmedValue(String value) {
		String trimmed = value.trim();
		if (trimmed.startsWith("'") && trimmed.endsWith("'")) {
			return trimmed.substring(1, trimmed.length() - 1);
		} else {
			return trimmed;
		}
	}

}
