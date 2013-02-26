package org.kuali.common.util;

import java.io.File;
import java.util.List;

public class SyncRequest {

	File srcDir;
	List<File> srcFiles;
	File dstDir;
	List<File> dstFiles;

	public File getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(File srcDir) {
		this.srcDir = srcDir;
	}

	public List<File> getSrcFiles() {
		return srcFiles;
	}

	public void setSrcFiles(List<File> srcFiles) {
		this.srcFiles = srcFiles;
	}

	public File getDstDir() {
		return dstDir;
	}

	public void setDstDir(File dstDir) {
		this.dstDir = dstDir;
	}

	public List<File> getDstFiles() {
		return dstFiles;
	}

	public void setDstFiles(List<File> dstFiles) {
		this.dstFiles = dstFiles;
	}

}
