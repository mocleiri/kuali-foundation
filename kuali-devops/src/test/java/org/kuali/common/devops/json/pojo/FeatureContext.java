package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.core.JsonFactory.Feature;

public final class FeatureContext {

	public FeatureContext(Feature feature, boolean state) {
		this.feature = checkNotNull(feature, "feature");
		this.state = state;
	}

	private final Feature feature;
	private final boolean state;

	public Feature getFeature() {
		return feature;
	}

	public boolean isState() {
		return state;
	}

}
