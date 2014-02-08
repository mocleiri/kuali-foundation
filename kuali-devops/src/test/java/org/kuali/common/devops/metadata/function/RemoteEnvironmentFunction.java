package org.kuali.common.devops.metadata.function;

import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.apache.commons.lang.StringUtils.substringsBetween;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Properties;

import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class RemoteEnvironmentFunction implements Function<String, Optional<RemoteEnvironment>> {

	@Override
	public Optional<RemoteEnvironment> apply(String content) {
		checkNotNull(content, "content");
		Properties system = getPropertiesFromHtml("System Property", content);
		Properties environment = getPropertiesFromHtml("Environment Variable", content);
		return Optional.of(RemoteEnvironment.builder().system(system).environment(environment).build());
	}

	protected Properties getPropertiesFromHtml(String title, String html) {
		Optional<String> content = fromNullable(substringBetween(html, "<th>" + title + "</th>", "</table>"));
		if (!content.isPresent()) {
			return ImmutableProperties.of();
		}
		Optional<String[]> rows = fromNullable(substringsBetween(content.get(), "<tr>", "</tr>"));
		if (!rows.isPresent()) {
			return ImmutableProperties.of();
		}
		Properties properties = new Properties();
		for (String row : rows.get()) {
			Optional<String[]> data = fromNullable(substringsBetween(row, "<td>", "</td>"));
			if (data.isPresent() && data.get().length == 2) {
				String[] tokens = data.get();
				String key = tokens[0];
				String value = tokens[1].replace("<br>", "").replace("&nbsp;", "");
				properties.setProperty(key, value);
			}
		}
		return ImmutableProperties.copyOf(properties);
	}
}
