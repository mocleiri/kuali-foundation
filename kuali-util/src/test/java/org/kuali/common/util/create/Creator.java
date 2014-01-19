package org.kuali.common.util.create;

import org.kuali.common.util.build.test.InstanceBuilder;

public interface Creator {

	<T> T create(InstanceBuilder<T> builder);

}
