package org.kuali.common.util.xml.jaxb.adapter;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.CollectionUtils;

import com.google.common.collect.ImmutableList;

public class TrimmingCSVStringAdapter extends XmlAdapter<String, List<String>> {

	private static final String DELIMITER = ",";

	@Override
	public final String marshal(List<String> strings) {
		if (strings.size() == 0) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.size(); i++) {
				if (i != 0) {
					sb.append(DELIMITER);
				}
				String value = strings.get(i).trim();
				if (StringUtils.contains(value, DELIMITER)) {
					throw new IllegalStateException("[" + value + "] contains '" + DELIMITER + "'");
				}
				sb.append(value);
			}
			return sb.toString();
		}
	}

	@Override
	public final List<String> unmarshal(String string) {
		if (string == null) {
			return ImmutableList.<String> of();
		} else {
			return ImmutableList.copyOf(CollectionUtils.getTrimmedListFromCSV(string));
		}
	}

}
