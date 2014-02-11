package org.kuali.common.devops.metadata.model.format;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.kuali.common.devops.metadata.model.EC2Tag;
import org.kuali.common.util.base.string.Replacer;
import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public final class TagListFormatter implements Formatter<List<EC2Tag>> {

	private static final Replacer REPLACER = getReplacer();
	private static final Splitter SPLITTER = Splitter.on('|');
	private static final Joiner JOINER = Joiner.on('|');
	private static final String MAGIC_EMPTY_LIST_TOKEN = "${tags.empty}";

	@Override
	public String print(List<EC2Tag> tags, Locale locale) {
		if (tags.isEmpty()) {
			return MAGIC_EMPTY_LIST_TOKEN;
		}
		List<EC2Tag> mutable = newArrayList(tags);
		Collections.sort(mutable);
		List<String> strings = newArrayList();
		for (EC2Tag tag : mutable) {
			String string = REPLACER.replace(tag.getKey()) + "=" + REPLACER.replace(tag.getValue());
			strings.add(string);
		}
		return JOINER.join(strings);
	}

	@Override
	public List<EC2Tag> parse(String text, Locale locale) {
		if (MAGIC_EMPTY_LIST_TOKEN.equals(text)) {
			return ImmutableList.<EC2Tag> of();
		}
		List<String> strings = SPLITTER.splitToList(text);
		List<EC2Tag> tags = newArrayList();
		Splitter equals = Splitter.on('=');
		for (String string : strings) {
			List<String> tokens = equals.splitToList(string);
			checkState(tokens.size() == 2, "expected exactly 2 tokens, but there were %s intead -> [%s]", tokens.size(), string);
			String key = REPLACER.restore(tokens.get(0));
			String value = REPLACER.restore(tokens.get(1));
			EC2Tag tag = EC2Tag.create(key, value);
			tags.add(tag);
		}
		Collections.sort(tags);
		return ImmutableList.copyOf(tags);
	}

	private static final Replacer getReplacer() {
		Replacer.Builder builder = Replacer.builder();
		builder.add("|", "${tags.separator}");
		builder.add("=", "${tags.equals}");
		builder.add("\r", "${tags.cr}");
		builder.add("\n", "${tags.lf}");
		return builder.build();
	}

}
