package org.kuali.common.util.bind.api;

import org.springframework.validation.BindingResult;

import com.google.common.base.Optional;

public interface BinderService {

	<T> Optional<BindingResult> bind(T object);

}
