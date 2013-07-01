package org.kuali.common.impex.data.impl.oracle;

import java.util.Deque;
import java.util.List;

import org.kuali.common.impex.data.impl.DataBean;
import org.kuali.common.impex.model.Column;

public class OracleLongClob {

	private Deque<String> clobChunks;
	private Column column;
	private List<DataBean> primaryKeys;

	public Deque<String> getClobChunks() {
		return clobChunks;
	}

	public void setClobChunks(Deque<String> clobChunks) {
		this.clobChunks = clobChunks;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public List<DataBean> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<DataBean> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

}
