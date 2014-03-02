package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.databind.SerializationFeature;

public final class SerializeContext {

	public SerializeContext(SerializationFeature feature, boolean enabled) {
		this.feature = checkNotNull(feature, "feature");
		this.enabled = enabled;
	}

	private final SerializationFeature feature;
	private final boolean enabled;

	public SerializationFeature getFeature() {
		return feature;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
