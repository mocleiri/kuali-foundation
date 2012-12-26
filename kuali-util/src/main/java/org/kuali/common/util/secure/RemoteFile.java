package org.kuali.common.util.secure;

public interface RemoteFile {

	/**
	 * Hostname where the file resides.
	 */
	String getHostname();

	/**
	 * Absolute path to the file on that host
	 */
	String getAbsolutePath();

	/**
	 * uid for the owner of the file (optional)
	 */
	Integer getUserId();

	/**
	 * gid for the file (optional)
	 */
	Integer getGroupId();

	/**
	 * permissions for the file (optional)
	 */
	Integer getPermissions();

	/**
	 * Size of the file in bytes (optional)
	 */
	Long getSize();

	/**
	 * Directory indicator
	 */
	boolean isDirectory();

	/**
	 * Last modified timestamp in millis since the epoch (optional)
	 */
	Long getLastModified();

	/**
	 * Indicates what is known about the existence of the file (UNKNOWN, TRUE, FALSE)
	 */
	Exists getExists();
}