package org.kuali.common.util.create;

import org.kuali.common.util.build.test.Builder;

public interface Creator {

	<T> T create(Builder<T> builder);

}
