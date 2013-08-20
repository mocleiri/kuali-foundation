package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Threshold;

public class LoggerAdapter extends XmlAdapter<Threshold, Threshold> {

	@Override
	public final Threshold marshal(Threshold threshold) {
		if (Threshold.DEFAULT_LOGGER_VALUE.equals(threshold)) {
			return null;
		} else {
			return threshold;
		}
	}

	@Override
	public final Threshold unmarshal(Threshold threshold) {
		if (threshold == null) {
			return Threshold.DEFAULT_LOGGER_VALUE;
		} else {
			return threshold;
		}
	}

}
