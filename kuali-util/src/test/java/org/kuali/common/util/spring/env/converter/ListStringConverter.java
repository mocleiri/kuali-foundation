package org.kuali.common.util.spring.env.converter;

import java.util.List;

import org.kuali.common.util.CollectionUtils;

public final class ListStringConverter implements Converter<String, List<String>> {

	@Override
	public List<String> convert(String s) {
		return CollectionUtils.getNoneSensitiveListFromCSV(s);
	}

}
