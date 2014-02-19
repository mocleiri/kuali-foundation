package org.kuali.common.util.spring.format.optional;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public final class OptionalTimeZoneFormatter extends AbstractOptionalFormatter<TimeZone> {

	public OptionalTimeZoneFormatter(String absentToken) {
		super(absentToken);
	}

	private static final Set<String> TIMEZONE_IDS = ImmutableSet.copyOf(TimeZone.getAvailableIDs());

	@Override
	protected String getString(TimeZone timeZone, Locale locale) {
		return timeZone.getID();
	}

	@Override
	public Optional<TimeZone> getOptional(String timeZoneId, Locale locale) {
		checkArgument(TIMEZONE_IDS.contains(timeZoneId), "unknown timezone id -> [%s]", timeZoneId);
		return Optional.of(TimeZone.getTimeZone(timeZoneId));
	}

}
