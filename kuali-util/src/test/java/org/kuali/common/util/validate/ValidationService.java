package org.kuali.common.util.validate;

import com.google.common.base.Optional;

public interface ValidationService {

	Optional<Errors> validate(Object instance);

}
