package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Threshold;

public class RepositoryThresholdAdapter extends XmlAdapter<String, Threshold> {

	@Override
	public final String marshal(Threshold threshold) {
		if (Threshold.DEFAULT_REPOSITORY_VALUE.equals(threshold)) {
			return null;
		} else {
			return threshold.name().toLowerCase();
		}
	}

	@Override
	public final Threshold unmarshal(String threshold) {
		if (threshold == null) {
			return Threshold.DEFAULT_REPOSITORY_VALUE;
		} else {
			return Threshold.valueOf(threshold.toUpperCase());
		}
	}

}
