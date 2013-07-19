package org.kuali.common.util.file;

import java.io.File;

public class MD5Result {

	File source;
	String sourceChecksum;
	File target;
	String targetChecksum;

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public String getSourceChecksum() {
		return sourceChecksum;
	}

	public void setSourceChecksum(String sourceChecksum) {
		this.sourceChecksum = sourceChecksum;
	}

	public File getTarget() {
		return target;
	}

	public void setTarget(File target) {
		this.target = target;
	}

	public String getTargetChecksum() {
		return targetChecksum;
	}

	public void setTargetChecksum(String targetChecksum) {
		this.targetChecksum = targetChecksum;
	}

}
