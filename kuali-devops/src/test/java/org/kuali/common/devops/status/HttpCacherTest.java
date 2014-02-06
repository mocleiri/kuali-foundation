package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.logic.exec.EnvironmentBasicsFunction;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.EnvironmentBasics;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class HttpCacherTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			SortedMap<String, List<Environment.Builder>> map = Environments2.getBuilders(false);
			List<String> fqdns = getFqdns(map);
			logger.info(format("fqdns: %s", fqdns.size()));
			Stopwatch stopwatch = Stopwatch.createStarted();
			for (String fqdn : fqdns) {
				logger.info(String.format("examining -> %s", fqdn));
				EnvironmentBasics eb = new EnvironmentBasicsFunction(true).apply(fqdn);
				break;
			}
			logger.info(format("elapsed -> %s", FormatUtils.getTime(stopwatch)));
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
