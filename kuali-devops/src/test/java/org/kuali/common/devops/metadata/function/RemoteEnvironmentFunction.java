package org.kuali.common.devops.metadata.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.apache.commons.lang.StringUtils.substringsBetween;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class RemoteEnvironmentFunction implements Function<String, RemoteEnvironment> {

	@Override
	public RemoteEnvironment apply(String content) {
		checkNotNull(content, "content");
		Properties system = getProperties("System Property", content);
		Properties environment = getProperties("Environment Variable", content);
		return RemoteEnvironment.builder().system(system).environment(environment).build();
	}

	protected Optional<Long> getTimestamp(String html) {
		// <li>time: 2014-02-08 18:26:06.873 UTC</li>
		Optional<String> token = fromNullable(substringBetween(html, "<li>time:", "</li>"));
		if (token.isPresent()) {
			try {
				SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");
				Date date = parser.parse(token.get().trim());
				return Optional.of(date.getTime());
			} catch (ParseException e) {
				return absent();
			}
		}
		return absent();
	}

	protected Properties getProperties(String title, String html) {
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
