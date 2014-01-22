package org.kuali.common.util.bind.api;

import java.util.List;

public interface BinderService {

	<T> List<String> bind(T object);

}
