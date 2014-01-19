package org.kuali.common.util.build;

import org.kuali.common.util.build.test.Builder;

public interface BuilderFactory {

	Builder<?> getBuilder();

}
