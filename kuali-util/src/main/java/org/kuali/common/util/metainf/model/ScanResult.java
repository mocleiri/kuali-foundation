package org.kuali.common.util.metainf.model;

import java.util.List;

public class ScanResult {

	MetaInfContext context;
	List<MetaInfResource> resources;

	public ScanResult() {
		this(null, null);
	}

	public ScanResult(MetaInfContext context, List<MetaInfResource> resources) {
		super();
		this.context = context;
		this.resources = resources;
	}

	public MetaInfContext getContext() {
		return context;
	}

	public void setContext(MetaInfContext context) {
		this.context = context;
	}

	public List<MetaInfResource> getResources() {
		return resources;
	}

	public void setResources(List<MetaInfResource> resources) {
		this.resources = resources;
	}

}
