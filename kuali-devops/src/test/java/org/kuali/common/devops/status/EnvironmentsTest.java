package org.kuali.common.devops.status;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newTreeSet;
import static java.lang.String.format;
import static org.kuali.common.util.FormatUtils.JAVA_UTIL_DATE_TO_STRING_FORMAT;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.core.validate.Validation;
import org.kuali.common.devops.logic.Environments;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.logic.Html;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.table.Label;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class EnvironmentsTest {

	private static final Logger logger = newLogger();
	private static final File HTML_DIR = new CanonicalFile("./target/site/status");
	private static final String US_EASTERN_TIMEZONE_ID = "US/Eastern";

	@Test
	public void test() {
		try {
			Validation.getDefaultValidator();
			SortedMap<String, List<Environment>> map = Environments2.getEnvironments(false);
			Set<String> keys = newHashSet(map.keySet());
			for (String group : keys) {
				List<Environment> envs = map.get(group);
				if (envs.size() == 0) {
					map.remove(group);
				}
			}
			for (String group : map.keySet()) {
				List<Environment> envs = map.get(group);
				Table<Integer, Label, String> table = Environments.getTable(envs);
				String html = Html.html(table);
				File outputFile = new CanonicalFile(HTML_DIR, group + ".htm");
				FileUtils.write(outputFile, getHtml(group, html, newTreeSet(map.keySet())));
				logger.info(format("created -> %s", outputFile));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String getHeader(String currentGroup, SortedSet<String> groups) {
		Map<String, String> displayNames = getDisplayNames();
		StringBuilder sb = new StringBuilder();
		sb.append("<span id='title'>Managed Environments</span>\n");
		sb.append("<span id='group'>-</span>\n");
		List<String> names = newArrayList(groups);
		for (String group : Lists.reverse(names)) {
			String displayName = displayNames.get(group);
			if (group.equals(currentGroup)) {
				sb.append("<span id='currentGroup'>" + displayName + "</span>\n");
			} else {
				sb.append("<span id='group'><a href='" + group + ".htm'>" + displayName + "</a></span>\n");
			}
		}
		return sb.toString();
	}

	protected String getHtml(String group, String table, SortedSet<String> groups) {
		Map<String, String> displayNames = getDisplayNames();
		String location = "classpath:html/envs/template.htm";
		String content = LocationUtils.toString(location);
		String displayName = displayNames.get(group);
		Properties props = new Properties();
		props.setProperty("html.title", displayName + " Environments");
		props.setProperty("html.table", table);
		props.setProperty("html.header", getHeader(group, groups));
		props.setProperty("html.footer", "Generated by Kuali Foundation DevOps - " + getCurrentDateDisplay());
		PropertyPlaceholderHelper pph = new PropertyPlaceholderHelper("${", "}", ":", false);
		String resolved = pph.replacePlaceholders(content, props);
		return resolved;
	}

	protected String getCurrentDateDisplay() {
		TimeZone zone = TimeZone.getTimeZone(US_EASTERN_TIMEZONE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat(JAVA_UTIL_DATE_TO_STRING_FORMAT);
		sdf.setTimeZone(zone);
		return sdf.format(new Date());
	}

	protected Map<String, String> getDisplayNames() {
		Map<String, String> map = Maps.newHashMap();
		map.put("ole", "OLE");
		map.put("rice", "Rice");
		map.put("student", "Student");
		map.put("foundation", "Foundation");
		return map;
	}

}
