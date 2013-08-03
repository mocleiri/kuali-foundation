package org.kuali.common.util.metainf.model;

import java.util.List;

public final class ScanResult {

	private final MetaInfContext context;
	private final List<MetaInfResource> resources;

	public ScanResult(MetaInfContext context, List<MetaInfResource> resources) {
		super();
		this.context = context;
		this.resources = resources;
	}

	public MetaInfContext getContext() {
		return context;
	}

	public List<MetaInfResource> getResources() {
		return resources;
	}

}
