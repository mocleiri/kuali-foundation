package org.kuali.common.util.bind.api;

import java.util.List;

public interface Binder {

	<T> List<String> bind(T object);

}
