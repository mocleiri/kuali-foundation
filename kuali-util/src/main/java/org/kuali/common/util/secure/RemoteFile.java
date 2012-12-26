package org.kuali.common.util.secure;

public interface RemoteFile {

	String getUsername();

	String getHostname();

	String getFilename();

	Owner getOwner();

	Group getGroup();

	long getSize();

	boolean isDirectory();

	long getLastModified();

}