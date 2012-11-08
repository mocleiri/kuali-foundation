package org.kuali.common.util.property;

public class EndsWithPropertyMatcher implements PropertyMatcher {

	String endsWithToken;

	public EndsWithPropertyMatcher() {
		this(null);
	}

	public EndsWithPropertyMatcher(String endsWithToken) {
		super();
		this.endsWithToken = endsWithToken;
	}

	@Override
	public boolean isMatch(String key, String value) {
		return key.endsWith(endsWithToken);
	}

	public String getEndsWithToken() {
		return endsWithToken;
	}

	public void setEndsWithToken(String endsWithToken) {
		this.endsWithToken = endsWithToken;
	}

}
