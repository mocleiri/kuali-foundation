package org.kuali.common.impex;

import java.io.File;
import java.util.List;

public class DumpTableResult {

	long rows;
	long size;
	long start;
	long finish;
	long elapsed;
	List<File> files;
	TableContext table;

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

	public TableContext getTable() {
		return table;
	}

	public void setTable(TableContext table) {
		this.table = table;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getFinish() {
		return finish;
	}

	public void setFinish(long finish) {
		this.finish = finish;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

}
