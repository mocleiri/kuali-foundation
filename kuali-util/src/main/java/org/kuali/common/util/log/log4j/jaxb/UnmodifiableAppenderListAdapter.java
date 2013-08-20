package org.kuali.common.util.log.log4j.jaxb;

import java.util.List;

import org.kuali.common.util.log.log4j.model.Appender;

public class UnmodifiableAppenderListAdapter extends UnmodifiableListAdapter<Appender> {

	@Override
	protected Appender[] getArray(List<Appender> list) {
		if (list == null) {
			return new Appender[0];
		} else {
			return list.toArray(new Appender[list.size()]);
		}
	}
}
