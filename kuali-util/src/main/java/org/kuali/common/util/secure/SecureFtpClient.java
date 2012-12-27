package org.kuali.common.util.secure;

import java.io.File;
import java.io.InputStream;

import com.jcraft.jsch.ChannelSftp;

public interface SecureFtpClient {

	void copyFile(File source, ChannelSftp channel, RemoteFile destination);

	void copyFileToDirectory(File source, ChannelSftp channel, RemoteFile destination);

	void copyLocationToFile(String location, ChannelSftp channel, RemoteFile destination);

	void copyInputStreamToFile(InputStream source, ChannelSftp channel, RemoteFile destination);

	void copyLocationToDirectory(String location, ChannelSftp channel, RemoteFile destination);

	void copyFile(ChannelSftp channel, RemoteFile source, File destination);

}