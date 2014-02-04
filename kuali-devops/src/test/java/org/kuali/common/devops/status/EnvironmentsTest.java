package org.kuali.common.devops.status;

import static java.lang.String.format;

import java.io.File;
import java.util.List;
import java.util.SortedMap;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.devops.logic.Environments;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.table.Label;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.validate.Validation;
import org.slf4j.Logger;

import com.google.common.collect.Table;

public class EnvironmentsTest {

	private static final Logger logger = Loggers.make();
	private static final File HTML_DIR = new CanonicalFile("./target/envs/html");

	@Test
	public void test() {
		try {
			Validation.getDefaultValidator();
			SortedMap<String, List<Environment>> maps = Environments2.getEnvironments(false);
			for (String group : maps.keySet()) {
				List<Environment> envs = maps.get(group);
				Table<Integer, Label, String> table = Environments.getTable(envs);
				String html = Environments.html(table);
				File outputFile = new CanonicalFile(HTML_DIR, group + ".htm");
				FileUtils.write(outputFile, html);
				logger.info(format("created -> %s", outputFile));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
