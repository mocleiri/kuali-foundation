package org.kuali.common.util.build;

import org.kuali.common.util.base.Builder;

public interface InstanceBuilder<T> extends Builder<T> {

	T getInstance();

}
