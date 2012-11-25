package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class TrimModifier implements PropertyProcessor {

	public TrimModifier() {
		this(null, null);
	}

	public TrimModifier(List<String> includes, List<String> excludes) {
		super();
		this.includes = includes;
		this.excludes = excludes;
	}

	List<String> includes;
	List<String> excludes;

	@Override
	public void process(Properties properties) {
		PropertyUtils.trim(properties, includes, excludes);
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

}
