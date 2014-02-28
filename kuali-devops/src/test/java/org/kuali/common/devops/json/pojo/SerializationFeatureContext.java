package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.databind.SerializationFeature;

public final class SerializationFeatureContext {

	public SerializationFeatureContext(SerializationFeature feature, boolean state) {
		this.feature = checkNotNull(feature, "feature");
		this.state = state;
	}

	private final SerializationFeature feature;
	private final boolean state;

	public SerializationFeature getFeature() {
		return feature;
	}

	public boolean isState() {
		return state;
	}

}
