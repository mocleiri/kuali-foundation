package org.kuali.common.util.project.model;

import org.kuali.common.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public final class Feature {

	public Feature(FeatureIdentifier identifier, String name, String description) {
		Assert.noNulls(identifier);
		Assert.noBlanks(name);
		this.identifier = identifier;
		this.name = name;
		this.description = description;
	}

	private final FeatureIdentifier identifier;
	private final String name;
	private final String description;

	public FeatureIdentifier getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
