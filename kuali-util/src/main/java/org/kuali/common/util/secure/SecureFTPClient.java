package org.kuali.common.util.secure;

import java.io.File;
import java.io.InputStream;

import com.jcraft.jsch.ChannelSftp;

public interface SecureFTPClient {

	void copyFile(File srcFile, ChannelSftp channel, RemoteFile dstFile);

	void copyInputStreamToFile(InputStream source, ChannelSftp channel, RemoteFile destination);

	void copyLocationToDirectory(String source, ChannelSftp channel, RemoteFile destination);

	void copyLocationToFile(String location, ChannelSftp channel, RemoteFile destination);

	void copyFileToDirectory(File source, ChannelSftp channel, RemoteFile destination);

	// void copyDirectory(File srcDir, ChannelSftp channel, RemoteFile dstDir);

	// void copyLocations(List<String> locations, ChannelSftp channel, List<RemoteFile> destinations);

}
