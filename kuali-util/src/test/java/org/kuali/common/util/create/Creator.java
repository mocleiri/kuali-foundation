package org.kuali.common.util.create;

import org.kuali.common.util.build.test.Builder;

public interface Creator {

	<T> void create(Builder<T> builder, T instance);

}
