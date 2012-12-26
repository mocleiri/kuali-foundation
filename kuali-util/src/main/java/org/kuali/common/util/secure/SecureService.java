package org.kuali.common.util.secure;

import java.io.File;

import com.jcraft.jsch.JSch;

public interface SecureService {

	void copyLocation(JSch jsch, SessionContext context, String location, RemoteFile destination);

	void copyFile(JSch jsch, SessionContext context, File source, RemoteFile destination);

	void copyFile(JSch jsch, SessionContext context, RemoteFile source, File destination);

}
