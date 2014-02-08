package org.kuali.common.devops.logic.function;

import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class TomcatVersionFunction implements Function<Optional<String>, Optional<String>> {

	@Override
	public Optional<String> apply(Optional<String> content) {
		checkNotNull(content,"content");
		return fromNullable(substringBetween(content.get(), "<h3>Apache Tomcat/", "</h3>"));
	}
}
