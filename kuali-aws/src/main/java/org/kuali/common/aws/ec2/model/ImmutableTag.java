package org.kuali.common.aws.ec2.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.amazonaws.services.ec2.model.Tag;

public final class ImmutableTag extends Tag {

	private static final long serialVersionUID = 1L;
	private static final String UOE_MSG = "Not supported for immutable tags";

	public ImmutableTag(String key, String value) {
		super.setKey(checkNotBlank(key, "key"));
		super.setValue(checkNotBlank(value, "value"));
	}

	@Override
	public void setKey(String key) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public Tag withKey(String key) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setValue(String value) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public Tag withValue(String value) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

}
