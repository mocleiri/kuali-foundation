package org.kuali.common.devops.metadata.model.format;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Locale;

import org.kuali.common.devops.metadata.model.EC2Tag;
import org.kuali.common.util.base.string.Replacer;
import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class TagListFormatter implements Formatter<List<EC2Tag>> {

	private static final Replacer REPLACER = Replacer.builder().add("|", "${formatter.separator}").add("=", "${formatter.equals}").build();
	private static final Splitter SPLITTER = Splitter.on('|');
	private static final Joiner JOINER = Joiner.on('|');

	@Override
	public String print(List<EC2Tag> tags, Locale locale) {
		if (tags.isEmpty()) {
			return null;
		}
		List<String> strings = newArrayList();
		for (EC2Tag tag : tags) {
			String string = REPLACER.replace(tag.getKey()) + "=" + REPLACER.replace(tag.getValue());
			strings.add(string);
		}
		return JOINER.join(strings);
	}

	@Override
	public List<EC2Tag> parse(String text, Locale locale) {
		if (text == null) {
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
		return ImmutableList.copyOf(tags);
	}

}
