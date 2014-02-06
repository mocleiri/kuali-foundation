package org.kuali.common.util.metainf.model;

import java.util.List;

import org.kuali.common.util.ListUtils;

public class ScanResult {

	private final MetaInfContext context;
	private final List<MetaInfResource> resources;

	public ScanResult(MetaInfContext context, List<MetaInfResource> resources) {
		this.context = context;
		this.resources = ListUtils.newImmutableArrayList(resources);
	}

	public MetaInfContext getContext() {
		return context;
	}

	public List<MetaInfResource> getResources() {
		return resources;
	}

}
