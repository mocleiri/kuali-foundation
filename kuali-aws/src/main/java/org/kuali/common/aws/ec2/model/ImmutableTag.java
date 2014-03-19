package org.kuali.common.aws.ec2.model;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.List;

import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

public final class ImmutableTag extends Tag {

	private static final long serialVersionUID = 1L;
	private static final String UOE_MSG = "Not supported for immutable tags";

	public static ImmutableList<Tag> copyOf(List<Tag> tags) {
		List<Tag> list = newArrayList();
		for (Tag tag : tags) {
			list.add(copyOf(tag));
		}
		return ImmutableList.copyOf(list);
	}

	public static ImmutableTag copyOf(Tag tag) {
		if (tag instanceof ImmutableTag) {
			return (ImmutableTag) tag;
		} else {
			return new ImmutableTag(tag.getKey(), tag.getValue());
		}
	}

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
