package org.kuali.common.util.secure;

public interface RemoteFile {

	String getUsername();

	String getHostname();

	String getFilename();

	Integer getUserId();

	Integer getGroupId();

	Long getSize();

	boolean isDirectory();

	Long getLastModified();

}