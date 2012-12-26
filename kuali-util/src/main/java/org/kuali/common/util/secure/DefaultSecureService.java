package org.kuali.common.util.secure;

import java.io.File;

import com.jcraft.jsch.JSch;

public class DefaultSecureService implements SecureService {

	@Override
	public void copyFile(File source, RemoteFile destination) {
		try {
			JSch jsch = JSchUtilss.getDefaultJsch();
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
	}

}
