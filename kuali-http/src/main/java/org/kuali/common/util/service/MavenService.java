package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface MavenService {

	void execute(MavenContext context);

	void execute(File workingDir, List<String> options, List<String> goals, List<String> phases);

}
