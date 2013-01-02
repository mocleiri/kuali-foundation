package org.kuali.common.util.secure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface SecureChannel {

	void open() throws IOException;

	void close();

	void copyFile(File source, RemoteFile destination);

	void copyLocationToFile(String location, RemoteFile destination);

	void copyInputStreamToFile(InputStream source, RemoteFile destination);

	void copyStringToFile(String string, RemoteFile destination);

	void copyLocationToDirectory(String location, RemoteFile destination);

	void copyFileToDirectory(File source, RemoteFile destination);

	void copyFile(RemoteFile source, File destination);

	void copyFileToDirectory(RemoteFile source, File destination);

	RemoteFile getMetaData(String absolutePath);

	boolean exists(String absolutePath);

	boolean isDirectory(String absolutePath);

	void deleteFile(String absolutePath);

	void createDirectory(RemoteFile dir);

	RemoteFile getWorkingDirectory();

	Result executeCommand(String command);

	Result executeCommand(String command, String stdin);

}