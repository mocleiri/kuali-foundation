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

	void copyStringToFile(String string, String encoding, RemoteFile destination);

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

	ExecResult executeCommand(String command);

	ExecResult su(String login, String command);

	ExecResult mkdir(String path);

	ExecResult rm(String path);

	ExecResult chown(String owner, String group, String path);

	ExecResult chownr(String owner, String group, String path);

	ExecResult chmod(String permissions, String path);

}