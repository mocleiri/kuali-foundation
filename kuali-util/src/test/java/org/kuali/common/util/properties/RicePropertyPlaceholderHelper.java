package org.kuali.common.util.properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;

import com.google.common.collect.Sets;

public final class RicePropertyPlaceholderHelper extends PropertyPlaceholderHelper {

	private static final Logger logger = LoggerUtils.make();

	private final boolean convertUnresolvablePlaceholdersToEmpty;
	private final String placeholderRegex;
	private final Pattern pattern;

	private RicePropertyPlaceholderHelper(Builder builder) {
		super(builder.placeholderPrefix, builder.placeholderSuffix, builder.valueSeparator, builder.allowUnresolvablePlaceholders);
		this.convertUnresolvablePlaceholdersToEmpty = builder.convertUnresolvablePlaceholdersToEmpty;
		this.placeholderRegex = builder.placeholderRegex;
		this.pattern = builder.pattern;
	}

	public static RicePropertyPlaceholderHelper create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private boolean allowUnresolvablePlaceholders = true;
		private boolean convertUnresolvablePlaceholdersToEmpty = true;
		private String placeholderPrefix = "${";
		private String placeholderSuffix = "}";
		private String valueSeparator = ":";
		private String placeholderRegex = "\\$\\{([^{}]+)\\}";

		// Filled in by the build() method
		private Pattern pattern;

		public Builder placeholderRegex(String placeholderRegex) {
			this.placeholderRegex = placeholderRegex;
			return this;
		}

		public Builder convertUnresolvablePlaceholdersToEmpty(boolean convertUnresolvablePlaceholdersToEmpty) {
			this.convertUnresolvablePlaceholdersToEmpty = convertUnresolvablePlaceholdersToEmpty;
			return this;
		}

		public Builder placeholderPrefix(String placeholderPrefix) {
			this.placeholderPrefix = placeholderPrefix;
			return this;
		}

		public Builder placeholderSuffix(String placeholderSuffix) {
			this.placeholderSuffix = placeholderSuffix;
			return this;
		}

		public Builder valueSeparator(String valueSeparator) {
			this.valueSeparator = valueSeparator;
			return this;
		}

		public Builder allowUnresolvablePlaceholders(boolean allowUnresolvablePlaceholders) {
			this.allowUnresolvablePlaceholders = allowUnresolvablePlaceholders;
			return this;
		}

		public RicePropertyPlaceholderHelper build() {
			this.pattern = Pattern.compile(placeholderRegex);
			RicePropertyPlaceholderHelper instance = new RicePropertyPlaceholderHelper(this);
			validate(instance);
			return instance;
		}

		private static void validate(RicePropertyPlaceholderHelper instance) {
			checkArgument(!StringUtils.isBlank(instance.placeholderRegex), "'placeholderRegex' cannot be blank");
			checkNotNull(instance.pattern, "'pattern' cannot be null");
		}

		public boolean isAllowUnresolvablePlaceholders() {
			return allowUnresolvablePlaceholders;
		}

		public void setAllowUnresolvablePlaceholders(boolean allowUnresolvablePlaceholders) {
			this.allowUnresolvablePlaceholders = allowUnresolvablePlaceholders;
		}

		public boolean isConvertUnresolvablePlaceholdersToEmpty() {
			return convertUnresolvablePlaceholdersToEmpty;
		}

		public void setConvertUnresolvablePlaceholdersToEmpty(boolean convertUnresolvablePlaceholdersToEmpty) {
			this.convertUnresolvablePlaceholdersToEmpty = convertUnresolvablePlaceholdersToEmpty;
		}

		public String getPlaceholderPrefix() {
			return placeholderPrefix;
		}

		public void setPlaceholderPrefix(String placeholderPrefix) {
			this.placeholderPrefix = placeholderPrefix;
		}

		public String getPlaceholderSuffix() {
			return placeholderSuffix;
		}

		public void setPlaceholderSuffix(String placeholderSuffix) {
			this.placeholderSuffix = placeholderSuffix;
		}

		public String getValueSeparator() {
			return valueSeparator;
		}

		public void setValueSeparator(String valueSeparator) {
			this.valueSeparator = valueSeparator;
		}
	}

	public boolean isConvertUnresolvablePlaceholdersToEmpty() {
		return convertUnresolvablePlaceholdersToEmpty;
	}

	protected ConversionResult convertToEmpty(String value) {
		return convert(value, "");
	}

	public static class ConversionResult {

		public ConversionResult(String original, String converted, Set<String> keys) {
			checkNotNull(original, "original cannot be null");
			checkNotNull(converted, "converted cannot be null");
			checkNotNull(keys, "keys cannot be null");
			this.original = original;
			this.converted = converted;
			this.keys = keys;
		}

		private final String original;
		private final String converted;
		private final Set<String> keys;

		public String getOriginal() {
			return original;
		}

		public String getConverted() {
			return converted;
		}

		public Set<String> getKeys() {
			return keys;
		}
	}

	protected ConversionResult convert(String value, String token) {
		String result = value;
		Matcher matcher = pattern.matcher(value);
		Set<String> keys = Sets.newTreeSet();
		while (matcher.find()) {
			// Get the first, outermost ${} in the string
			// This removes the ${} and produces the enclosed key
			keys.add(matcher.group(1));

			// Replace the first ${} with token
			result = matcher.replaceFirst(token);

			// Reset the matcher so we can continue examining the string
			matcher = matcher.reset(result);
		}

		// All placeholders have been replaced with the empty string at this point
		return new ConversionResult(value, result, keys);
	}

	protected String convert(String string) {
		if (convertUnresolvablePlaceholdersToEmpty) {
			ConversionResult result = convert(string, "");
			for (String key : result.getKeys()) {
				logger.info("? unknown - [{}] - converted to \"\"", key);
			}
			return result.getConverted();
		} else {
			return string;
		}
	}

	@Override
	public String replacePlaceholders(String value, Properties properties) {
		return convert(super.replacePlaceholders(value, properties));
	}

	@Override
	public String replacePlaceholders(String value, PlaceholderResolver placeholderResolver) {
		return convert(super.replacePlaceholders(value, placeholderResolver));
	}

	public String getPlaceholderRegex() {
		return placeholderRegex;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
