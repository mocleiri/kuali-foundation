package org.kuali.common.util.env.adapter;

import java.util.List;

import org.kuali.common.util.CollectionUtils;

public final class CSVToListAdapter implements Adapter<String, List<String>> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public List<String> convert(String s) {
		return CollectionUtils.getNoneSensitiveListFromCSV(s);
	}

}
