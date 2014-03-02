package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;

public final class DeserializeContext {

	public DeserializeContext(DeserializationFeature feature, boolean enabled) {
		this.feature = checkNotNull(feature, "feature");
		this.enabled = enabled;
	}

	private final DeserializationFeature feature;
	private final boolean enabled;

	public DeserializationFeature getFeature() {
		return feature;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
