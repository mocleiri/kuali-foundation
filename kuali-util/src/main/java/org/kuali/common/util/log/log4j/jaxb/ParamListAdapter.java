package org.kuali.common.util.log.log4j.jaxb;

import java.util.List;

import org.kuali.common.util.log.log4j.model.Param;

public class ParamListAdapter extends UnmodifiableListAdapter<Param> {

	@Override
	protected Param[] getArrayFromNonEmptyList(List<Param> list) {
		return list.toArray(new Param[list.size()]);
	}
}
