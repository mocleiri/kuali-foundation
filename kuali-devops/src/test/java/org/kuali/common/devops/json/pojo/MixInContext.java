package org.kuali.common.devops.json.pojo;

import static org.kuali.common.util.base.Precondition.checkNotNull;

public final class MixInContext {

	public MixInContext(Class<?> target, Class<?> source) {
		this.target = checkNotNull(target, "target");
		this.source = checkNotNull(source, "source");
	}

	private final Class<?> target;
	private final Class<?> source;

	public Class<?> getTarget() {
		return target;
	}

	public Class<?> getSource() {
		return source;
	}

}
