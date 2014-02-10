package org.kuali.common.devops.status;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.kuali.common.util.FormatUtils.JAVA_UTIL_DATE_TO_STRING_FORMAT;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.devops.logic.Environments;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.logic.Html;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.table.Label;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.validate.Validation;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;

import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class EnvironmentsTest {

	private static final Logger logger = Loggers.make();
	private static final File HTML_DIR = new CanonicalFile("./target/site/status");
	private static final String US_EASTERN_TIMEZONE_ID = "US/Eastern";

	@Test
	public void test() {
		try {
			Validation.getDefaultValidator();
			SortedMap<String, List<Environment>> maps = Environments2.getEnvironments(false);
			for (String group : maps.keySet()) {
				List<Environment> envs = maps.get(group);
				Table<Integer, Label, String> table = Environments.getTable(envs);
				String html = Html.html(table);
				File outputFile = new CanonicalFile(HTML_DIR, group + ".htm");
				FileUtils.write(outputFile, getHtml(group, html));
				logger.info(format("created -> %s", outputFile));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String getHtml(String group, String table) {
		Map<String, String> displayNames = getDisplayNames();
		String location = "classpath:html/envs/template.htm";
		String content = LocationUtils.toString(location);
		String displayName = displayNames.get(group);
		if (displayName == null) {
			displayName = capitalize(group);
		}
		Properties props = new Properties();
		props.setProperty("html.title", displayName + " Environments");
		props.setProperty("html.table", table);
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
		map.put("ole", "Kuali OLE");
		map.put("rice", "Kuali Rice");
		map.put("student", "Kuali Student");
		map.put("foundation", "Kuali Foundation");
		return map;
	}

}
