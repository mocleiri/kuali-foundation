package org.kuali.common.util.spring.format.optional;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public abstract class AbstractOptionalFormatter<T> implements Formatter<Optional<T>> {

	public AbstractOptionalFormatter(String absentToken) {
		this.absentToken = checkNotBlank(absentToken, "absentToken");
	}

	private final String absentToken;

	@Override
	public String print(Optional<T> optional, Locale locale) {
		if (!optional.isPresent()) {
			return absentToken;
		} else {
			return getString(optional.get(), locale);
		}
	}

	protected String getString(T reference, Locale locale) {
		return reference.toString();
	}

	public String getAbsentToken() {
		return absentToken;
	}
}
