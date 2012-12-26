package org.kuali.common.util.secure;

import java.io.File;

public interface SecureService {

	void copyFile(File source, RemoteFile destination);

	void copyFile(RemoteFile source, File destination);

}
