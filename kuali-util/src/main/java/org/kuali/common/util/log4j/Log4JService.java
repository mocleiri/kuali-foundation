package org.kuali.common.util.log4j;

import java.util.List;

public interface Log4JService {

	void reset();

	void configure(List<LoggerContext> contexts);

	void configure(LoggerContext context);

}
