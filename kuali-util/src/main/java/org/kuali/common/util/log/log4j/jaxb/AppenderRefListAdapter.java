package org.kuali.common.util.log.log4j.jaxb;

import java.util.List;

import org.kuali.common.util.log.log4j.model.AppenderRef;

public class AppenderRefListAdapter extends UnmodifiableListAdapter<AppenderRef> {

	@Override
	protected AppenderRef[] getArrayFromNonEmptyList(List<AppenderRef> list) {
		return list.toArray(new AppenderRef[list.size()]);
	}
}
