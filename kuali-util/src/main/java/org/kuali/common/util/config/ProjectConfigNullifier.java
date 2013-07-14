package org.kuali.common.util.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Nullifier;

public class ProjectConfigNullifier implements Nullifier {

	ProjectConfig config;

	public ProjectConfigNullifier() {
		super();
	}

	public ProjectConfigNullifier(ProjectConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void nullify() {

		Assert.notNull(config, "config is null");

		nullifyLocations(config.getLocations());
		nullifyContexts(config.getContexts());

	}

	protected void nullifyContexts(List<ContextConfig> contexts) {
		for (ContextConfig context : CollectionUtils.toEmptyList(contexts)) {
			nullifyLocations(context.getLocations());
			nullifyContexts(context.getChildren());
		}
	}

	protected void nullifyLocations(List<Location> locations) {
		for (Location location : CollectionUtils.toEmptyList(locations)) {
			if (StringUtils.equals(Location.DEFAULT_ENCODING, location.getEncoding())) {
				location.setEncoding(null);
			}
			if (Location.DEFAULT_MISSING_MODE.equals(location.getMissingMode())) {
				location.setMissingMode(null);
			}
		}
	}

	public ProjectConfig getConfig() {
		return config;
	}

	public void setConfig(ProjectConfig config) {
		this.config = config;
	}

}
