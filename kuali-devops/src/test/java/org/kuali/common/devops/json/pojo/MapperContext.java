package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.fasterxml.jackson.databind.MapperFeature;

public final class MapperContext {

	public MapperContext(MapperFeature feature, boolean enabled) {
		this.feature = checkNotNull(feature, "feature");
		this.enabled = enabled;
	}

	private final MapperFeature feature;
	private final boolean enabled;

	public MapperFeature getFeature() {
		return feature;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
