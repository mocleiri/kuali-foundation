package org.kuali.common.util.ignore;

import org.apache.commons.lang3.StringUtils;

public class PrefixSuffixIgnorer implements Ignore {

	String prefix;
	String suffix;

	public PrefixSuffixIgnorer() {
		this(null, null);
	}

	public PrefixSuffixIgnorer(String prefix, String suffix) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public boolean ignore(String line) {
		return StringUtils.startsWith(line, prefix) && StringUtils.endsWith(line, suffix);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
