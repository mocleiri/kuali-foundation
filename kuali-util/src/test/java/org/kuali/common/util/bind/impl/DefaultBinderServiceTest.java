package org.kuali.common.util.bind.impl;

import java.util.SortedSet;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.Str;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.model.User;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Sets;

public class DefaultBinderServiceTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			SortedSet<String> keys = Sets.newTreeSet(System.getProperties().stringPropertyNames());
			StringBuilder sb = new StringBuilder();
			for (String key : keys) {
				String value = System.getProperty(key);
				String flattened = Str.flatten(value, "CR", "LF");
				sb.append(key + "=" + flattened + "\n");
			}
			FileUtils.write(new CanonicalFile("/tmp/system.txt"), sb.toString());
			BinderService service = new DefaultBinderService();
			User user = new User();
			service.bind(user);
			logger.info(String.format("name=%s", user.getName()));
			logger.info(String.format("home=%s", user.getHome()));
			logger.info(String.format("timeZone=%s", user.getTimezone()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
