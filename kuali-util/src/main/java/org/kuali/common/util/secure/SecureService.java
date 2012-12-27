package org.kuali.common.util.secure;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.jcraft.jsch.JSch;

public interface SecureService {

	void copyFileToDirectory(JSch jsch, SessionContext context, File source, RemoteFile destination);

	void copyDirectory(JSch jsch, SessionContext context, File srcDir, RemoteFile dstDir);

	void copyFileToFile(JSch jsch, SessionContext context, File srcFile, RemoteFile dstFile);

	void copyLocationToDirectory(JSch jsch, SessionContext context, String source, RemoteFile destination);

	void copyLocationToFile(JSch jsch, SessionContext context, String location, RemoteFile destination);

	void copyLocations(JSch jsch, SessionContext context, List<String> locations, List<RemoteFile> destinations);

	void copyInputStreamToFile(JSch jsch, SessionContext context, InputStream source, RemoteFile destination);

}
