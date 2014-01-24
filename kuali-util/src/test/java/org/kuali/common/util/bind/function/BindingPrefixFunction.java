package org.kuali.common.util.bind.function;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import org.kuali.common.util.Annotations;
import org.kuali.common.util.bind.api.BindingPrefix;
import org.kuali.common.util.bind.test.Prefixes;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class BindingPrefixFunction implements Function<Field, String> {

	@Override
	public String apply(Field field) {
		checkNotNull(field, "'field' cannot be null'");
		Optional<String> prefix = Prefixes.get(field.getType(), Annotations.get(field, BindingPrefix.class));
		if (prefix.isPresent()) {
			return prefix.get();
		} else {
			return field.getName();
		}
	}

}
