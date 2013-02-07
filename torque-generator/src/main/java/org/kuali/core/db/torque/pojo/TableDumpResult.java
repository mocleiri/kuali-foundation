package org.kuali.core.db.torque.pojo;

import java.io.File;
import java.util.List;

public class TableDumpResult {

	long rows;
	long size;
	List<File> files;

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

}
