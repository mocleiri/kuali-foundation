package org.kuali.common.util.service;

import java.util.List;

import org.springframework.core.env.PropertySource;

public class PropertySourceContext {

	public static final boolean DEFAULT_REMOVE_EXISTING_SOURCES = false;
	public static final boolean DEFAULT_LAST_ONE_IN_WINS = true;
	public static final PropertySourceAddPriority DEFAULT_PRIORITY = PropertySourceAddPriority.LAST;

	// If true, any existing property sources are removed and replaced by the list from this context
	boolean removeExistingSources = DEFAULT_REMOVE_EXISTING_SOURCES;

	// If true, the last PropertySource in the list has the highest priority
	// That is to say, Spring will search for property values starting at the bottom of the list and work its way upwards
	boolean lastOneInWins = DEFAULT_LAST_ONE_IN_WINS;

	// Can add property sources before or after existing property sources
	PropertySourceAddPriority priority = DEFAULT_PRIORITY;

	// The list of property source objects to add to the environment
	List<PropertySource<?>> sources;

	public PropertySourceContext() {
		this(null);
	}

	public PropertySourceContext(List<PropertySource<?>> sources) {
		this(sources, DEFAULT_REMOVE_EXISTING_SOURCES);
	}

	public PropertySourceContext(List<PropertySource<?>> sources, boolean removeExistingSources) {
		super();
		this.sources = sources;
		this.removeExistingSources = removeExistingSources;
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

	public PropertySourceAddPriority getPriority() {
		return priority;
	}

	public void setPriority(PropertySourceAddPriority priority) {
		this.priority = priority;
	}

}
