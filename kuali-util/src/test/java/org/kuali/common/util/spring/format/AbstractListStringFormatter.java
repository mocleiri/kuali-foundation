package org.kuali.common.util.spring.format;

import java.util.List;
import java.util.Locale;

import org.kuali.common.util.nullify.NullUtils;
import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public abstract class AbstractListStringFormatter implements Formatter<List<String>> {

	private final Splitter splitter;
	private final Joiner joiner;

	public AbstractListStringFormatter(char separator) {
		this.splitter = Splitter.on(separator);
		this.joiner = Joiner.on(separator);
	}

	@Override
	public List<String> parse(String files, Locale locale) {
		if (NullUtils.trimToNull(files) == null) {
			return Lists.newArrayList();
		} else {
			return Lists.newArrayList(splitter.split(files));
		}
	}

	@Override
	public String print(List<String> files, Locale locale) {
		return joiner.join(files.iterator());
	}

}
