package org.kuali.common.util.secure;

import java.io.File;

public interface ScpService {

	int copy(File source, String destination);

	int copy(String source, File destination);

}
