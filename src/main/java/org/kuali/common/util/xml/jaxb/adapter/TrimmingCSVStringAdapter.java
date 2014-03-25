package org.kuali.common.util.xml.jaxb.adapter;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

import com.google.common.collect.ImmutableList;

/**
 * Trim each element from List&lt;String> to create the CSV when going from Object -> XML.<br>
 * Convert the CSV back into List&lt;String> when going from XML -> Object.<br>
 * The List&lt;String> returned when going from XML -> Object is immutable.</br>
 * 
 * @throws NullPointerException
 *             If the list is null or any strings in the list are null
 * @throws IllegalArgumentException
 *             If any strings in the list contain a comma
 */
public class TrimmingCSVStringAdapter extends XmlAdapter<String, List<String>> {

	private static final String DELIMITER = ",";

	@Override
	public final String marshal(List<String> strings) {
		if (strings.size() == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			if (i != 0) {
				sb.append(DELIMITER);
			}
			String trimmed = strings.get(i).trim();
			Assert.isFalse(StringUtils.contains(trimmed, DELIMITER), "[" + trimmed + "] contains '" + DELIMITER + "'");
			sb.append(trimmed);
		}
		return sb.toString();
	}

	@Override
	public final List<String> unmarshal(String string) {
		if (string == null) {
			return ImmutableList.of();
		} else {
			return ImmutableList.copyOf(CollectionUtils.getTrimmedListFromCSV(string));
		}
	}

}
