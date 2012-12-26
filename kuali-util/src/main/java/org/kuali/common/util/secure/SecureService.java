package org.kuali.common.util.secure;

import java.io.File;

public interface SecureService {

	void copyFile(SessionContext context, File source, RemoteFile destination);

	void copyFile(SessionContext context, RemoteFile source, File destination);

}
