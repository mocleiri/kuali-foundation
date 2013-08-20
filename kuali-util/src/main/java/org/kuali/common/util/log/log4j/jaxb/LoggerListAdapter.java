package org.kuali.common.util.log.log4j.jaxb;

import java.util.List;

import org.kuali.common.util.log.log4j.model.Logger;

public class LoggerListAdapter extends UnmodifiableListAdapter<Logger> {

	@Override
	protected Logger[] getArrayFromNonEmptyList(List<Logger> list) {
		return list.toArray(new Logger[list.size()]);
	}
}
