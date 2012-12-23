package org.kuali.common.util.service;

import java.util.List;

public interface ExecService {

	int execute(ExecContext context);

	int execute(String executable, List<String> args);

}
