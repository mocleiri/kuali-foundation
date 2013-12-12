package org.kuali.common.util.validate;

import com.google.common.base.Optional;

public interface Validator {

	Optional<Errors> validate(Object instance);

}
