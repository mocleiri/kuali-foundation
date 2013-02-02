package org.kuali.common.util.ignore;

import org.apache.commons.lang3.StringUtils;

public class StartsWithIgnorer implements Ignore {

	String prefix;

	public StartsWithIgnorer(String prefix) {
	    super();
	    this.prefix = prefix;
    }

	@Override
	public boolean ignore(String line) {
		return StringUtils.startsWith(line, prefix);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
