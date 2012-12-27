package org.kuali.common.util.secure;

import java.io.File;
import java.io.InputStream;

public interface SecureFtpClient {

	void copyFile(File source, RemoteFile destination);

	void copyFileToDirectory(File source, RemoteFile destination);

	void copyLocationToFile(String location, RemoteFile destination);

	void copyInputStreamToFile(InputStream source, RemoteFile destination);

	void copyLocationToDirectory(String location, RemoteFile destination);

	void copyFile(RemoteFile source, File destination);

}