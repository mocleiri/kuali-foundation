package org.kuali.common.util.log.log4j.jaxb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Appender;

public class UnmodifiableAppenderListAdapter extends XmlAdapter<Appender[], List<Appender>> {

	private static final List<Appender> EMPTY_LIST = Collections.<Appender> emptyList();
	private static final Appender[] EMPTY_ARRAY = {};

	@Override
	public Appender[] marshal(List<Appender> list) {
		if (list == null || list.size() == 0) {
			return EMPTY_ARRAY;
		} else {
			return list.toArray(new Appender[list.size()]);
		}
	}

	@Override
	public List<Appender> unmarshal(Appender[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_LIST;
		} else {
			return Arrays.asList(array);
		}
	}

}
