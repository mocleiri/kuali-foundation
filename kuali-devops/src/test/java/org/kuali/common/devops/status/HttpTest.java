package org.kuali.common.devops.status;

import static java.lang.String.format;

import org.junit.Test;
import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class HttpTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		String url = "http://env1.ks.kuali.org/tomcat/logs/env.jsp";
		Optional<String> content = HttpCacher.getContent(url);
		if (content.isPresent()) {
			logger.info(format("\n\n[%s]\n\n%s\n\n", url, content.get()));
		} else {
			logger.info(format("no content was located @ [%s]", url));
		}
	}
}
