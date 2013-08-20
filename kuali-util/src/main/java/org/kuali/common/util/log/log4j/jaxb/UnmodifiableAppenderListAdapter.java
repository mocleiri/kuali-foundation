package org.kuali.common.util.log.log4j.jaxb;

import java.util.List;

import org.kuali.common.util.log.log4j.model.Appender;

public class UnmodifiableAppenderListAdapter extends UnmodifiableListAdapter<Appender> {

	@Override
	protected Appender[] getArrayFromNonEmptyList(List<Appender> list) {
		return list.toArray(new Appender[list.size()]);
	}
}
