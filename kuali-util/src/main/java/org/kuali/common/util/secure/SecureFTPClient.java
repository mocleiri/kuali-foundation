package org.kuali.common.util.secure;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.jcraft.jsch.ChannelSftp;

public interface SecureFTPClient {

	void copyFile(File srcFile, ChannelSftp channel, RemoteFile dstFile);

	void copyFileToDirectory(File source, ChannelSftp channel, RemoteFile destination);

	void copyDirectory(File srcDir, ChannelSftp channel, RemoteFile dstDir);

	void copyLocationToDirectory(String source, ChannelSftp channel, RemoteFile destination);

	void copyLocationToFile(String location, ChannelSftp channel, RemoteFile destination);

	void copyLocations(List<String> locations, ChannelSftp channel, List<RemoteFile> destinations);

	void copyInputStreamToFile(InputStream source, ChannelSftp channel, RemoteFile destination);

}
