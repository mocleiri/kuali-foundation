package org.kuali.common.util.service;

import java.util.List;

import org.springframework.core.env.PropertySource;

public class PropertySourceContext {

	// If true, any existing property sources are removed and replaced by the list from this context
	boolean removeExistingSources;

	// If true, the last PropertySource in the list has the highest priority
	// That is to say, Spring will search for property values starting at the bottom of the list and work its way upwards
	boolean lastOneInWins = true;

	// The list of property source objects to add to the environment
	List<PropertySource<?>> sources;

	public PropertySourceContext() {
		this(null);
	}

	public PropertySourceContext(List<PropertySource<?>> sources) {
		super();
		this.sources = sources;
	}

	public boolean isRemoveExistingSources() {
		return removeExistingSources;
	}

	public void setRemoveExistingSources(boolean removeExistingSources) {
		this.removeExistingSources = removeExistingSources;
	}

	public boolean isLastOneInWins() {
		return lastOneInWins;
	}

	public void setLastOneInWins(boolean lastOneInWins) {
		this.lastOneInWins = lastOneInWins;
	}

	public List<PropertySource<?>> getSources() {
		return sources;
	}

	public void setSources(List<PropertySource<?>> sources) {
		this.sources = sources;
	}

}
