package org.kuali.common.devops.json.fruit;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Function;

public class JsonHtmlFunction implements Function<JsonDescriptor, String> {

	@Override
	public String apply(JsonDescriptor descriptor) {
		checkNotNull(descriptor, "descriptor");
		return descriptor.getType().getSimpleName();
	}

}
