package org.kuali.common.devops.logic.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.substringBetween;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class TomcatVersionFunction implements Function<Optional<String>, Optional<String>> {

	@Override
	public Optional<String> apply(Optional<String> content) {
		checkNotNull(content);
		if (content.isPresent()) {
			return fromNullable(substringBetween(content.get(), "<h3>Apache Tomcat/", "</h3>"));
		} else {
			return absent();
		}
	}
}
