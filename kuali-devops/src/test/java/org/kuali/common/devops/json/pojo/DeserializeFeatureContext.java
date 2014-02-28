package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;

public final class DeserializeFeatureContext {

	public DeserializeFeatureContext(DeserializationFeature feature, boolean state) {
		this.feature = checkNotNull(feature, "feature");
		this.state = state;
	}

	private final DeserializationFeature feature;
	private final boolean state;

	public DeserializationFeature getFeature() {
		return feature;
	}

	public boolean isState() {
		return state;
	}

}
