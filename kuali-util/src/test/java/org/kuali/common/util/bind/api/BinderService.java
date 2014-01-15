package org.kuali.common.util.bind.api;

import org.springframework.validation.BindingResult;


public interface BinderService {

	<T> BindingResult bind(T object);

}
