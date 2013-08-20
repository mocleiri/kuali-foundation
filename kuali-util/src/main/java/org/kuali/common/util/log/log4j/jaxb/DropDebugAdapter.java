package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Debug;

public abstract class DropDebugAdapter extends XmlAdapter<Debug, Debug> {

	@Override
	public final Debug marshal(Debug value) {
		if (Debug.DEFAULT_VALUE.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Debug unmarshal(Debug value) {
		if (value == null) {
			return Debug.DEFAULT_VALUE;
		} else {
			return value;
		}
	}

}
