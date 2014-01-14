package org.kuali.common.util.bind.api;

import org.springframework.validation.BindingResult;


public interface Binder {

	<T> BindingResult bind(T object);

}
