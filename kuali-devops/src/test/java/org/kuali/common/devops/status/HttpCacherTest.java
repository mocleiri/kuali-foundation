package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkState;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.logic.exec.BasicsCacherExecutable;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class HttpCacherTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			SortedMap<String, List<Environment.Builder>> map = Environments2.getBuilders(false);
			List<String> fqdns = getFqdns(map);
			logger.info(String.format("fqdns: %s", fqdns.size()));
			for (String fqdn : fqdns) {
				logger.info(String.format("examining -> %s", fqdn));
				Executable executable = new BasicsCacherExecutable(fqdn);
				executable.execute();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<String> getFqdns(SortedMap<String, List<Environment.Builder>> map) {
		Set<String> strings = Sets.newHashSet();
		for (List<Environment.Builder> builders : map.values()) {
			for (Environment.Builder builder : builders) {
				String fqdn = builder.getFqdn();
				checkState(strings.add(fqdn), "duplicate fqdn -> %s", fqdn);
			}
		}
		List<String> list = Lists.newArrayList(strings);
		Collections.sort(list);
		return list;
	}

}
