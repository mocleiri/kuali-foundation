package org.kuali.common.util.build;

public interface InstanceBuilder<T> extends Builder<T> {

	T getInstance();

}
