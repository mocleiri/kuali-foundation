package org.kuali.common.devops.metadata.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringAfter;
import static org.apache.commons.lang.StringUtils.substringBefore;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.apache.commons.lang.StringUtils.substringsBetween;
import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.devops.metadata.model.Memory;
import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

public final class RemoteEnvironmentFunction implements Function<String, RemoteEnvironment> {

	public RemoteEnvironmentFunction() {
		this("yyyy-MM-dd HH:mm:ss.S z");
	}

	public RemoteEnvironmentFunction(String timestampFormat) {
		this.timestampFormat = checkNotBlank(timestampFormat, "timestampFormat");
	}

	private final String timestampFormat;

	@Override
	public RemoteEnvironment apply(String html) {
		checkNotNull(html, "html");
		Properties system = getProperties("System Property", html);
		Properties environment = getProperties("Environment Variable", html);
		Optional<Long> millis = getCurrentTimeMillis(html);
		Optional<Integer> processors = getProcessors(html);
		return RemoteEnvironment.builder().system(system).environment(environment).currentTimeMillis(millis).processors(processors).build();
	}

	protected Optional<Memory> getMemory(String html) {
		// <li>mem: [used=0.62g, free=1.16g, allocated=0.94g, max=1.78g]</li>
		Optional<String> token = fromNullable(substringBetween(html, "<li>mem: [", "]</li>"));
		if (token.isPresent()) {
			List<String> tokens = Splitter.on(',').splitToList(token.get());
			Map<String, Long> map = Maps.newHashMap();
			for (String element : tokens) {
				String key = substringBefore(element.trim(), "=");
				Long value = FormatUtils.getBytes(substringAfter(element.trim(), "="));
				map.put(key, value);
			}
			Memory memory = Memory.builder().used(map.get("used")).free(map.get("free")).allocated(map.get("allocated")).max(map.get("max")).build();
			return Optional.of(memory);
		}
		return absent();
	}

	protected Optional<Integer> getProcessors(String html) {
		// <li>processors: 2</li>
		Optional<String> token = getToken(html, "processors");
		if (token.isPresent()) {
			return Optional.of(Integer.parseInt(token.get()));
		}
		return absent();
	}

	protected Optional<Long> getCurrentTimeMillis(String html) {
		// <li>time: 2014-02-08 18:26:06.873 UTC</li>
		Optional<String> token = getToken(html, "time");
		return token.isPresent() ? getTime(token.get()) : Optional.<Long> absent();
	}

	protected Optional<Long> getTime(String token) {
		try {
			SimpleDateFormat parser = new SimpleDateFormat(timestampFormat);
			Date date = parser.parse(token);
			return Optional.of(date.getTime());
		} catch (ParseException e) {
			throw illegalArgument(e, "unexpected parse error -> [%s]", token);
		}
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

	protected Optional<String> getToken(String html, String item) {
		String token = substringBetween(html, "<li>" + item + ":", "</li>");
		if (token == null) {
			return absent();
		} else {
			return Optional.of(token.trim());
		}
	}

}
