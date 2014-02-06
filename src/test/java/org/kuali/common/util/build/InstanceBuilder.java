package org.kuali.common.util.build;

import org.apache.commons.lang3.builder.Builder;

public interface InstanceBuilder<T> extends Builder<T> {

	T getInstance();

}
