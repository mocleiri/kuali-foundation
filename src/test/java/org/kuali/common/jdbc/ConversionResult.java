package org.kuali.common.jdbc;

import java.util.List;

public class ConversionResult {

	SqlMetaData before;
	SqlMetaData after;
	List<MorphResult> morphResults;

	public SqlMetaData getBefore() {
		return before;
	}

	public void setBefore(SqlMetaData before) {
		this.before = before;
	}

	public SqlMetaData getAfter() {
		return after;
	}

	public void setAfter(SqlMetaData after) {
		this.after = after;
	}

	public List<MorphResult> getMorphResults() {
		return morphResults;
	}

	public void setMorphResults(List<MorphResult> morphResults) {
		this.morphResults = morphResults;
	}

}
