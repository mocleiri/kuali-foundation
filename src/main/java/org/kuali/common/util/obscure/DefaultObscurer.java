package org.kuali.common.util.obscure;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;

public class DefaultObscurer implements Obscurer {

	String replacement = Constants.WILDCARD;

	@Override
	public String obscure(String string) {
		return StringUtils.repeat(replacement, string.length());
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

}
