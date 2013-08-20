package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Threshold;

public class RepositoryThresholdAdapter extends XmlAdapter<String, Threshold> {

	@Override
	public final String marshal(Threshold value) {
		if (Threshold.DEFAULT_REPOSITORY_VALUE.equals(value)) {
			return null;
		} else {
			return value.name().toLowerCase();
		}
	}

	@Override
	public final Threshold unmarshal(String value) {
		if (value == null) {
			return Threshold.DEFAULT_REPOSITORY_VALUE;
		} else {
			return Threshold.valueOf(value.toUpperCase());
		}
	}

}
