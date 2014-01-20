package org.kuali.common.util.create;

import org.kuali.common.util.build.InstanceBuilder;

public interface Creator {

	<T> T create(InstanceBuilder<T> builder);

}
