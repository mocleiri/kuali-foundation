package org.kuali.common.util.bind.api;

import java.util.List;

public interface Binder {

	<T> List<String> bind(T object);

	<T> List<String> bind(String prefix, T object);

}
