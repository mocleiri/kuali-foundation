package org.kuali.core.db.torque.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParsingTest {

	private static final Logger logger = LoggerFactory.getLogger(DateParsingTest.class);

	@Test
	public void test() {
		try {
			String format = ImpexContext.MPX_DATE_FORMAT;
			Comparator<TimeZone> c = new TimeZoneComparator();
			List<TimeZone> timeZones = getSortedTimeZones(c);
			List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
			List<Object[]> rows = new ArrayList<Object[]>();
			for (TimeZone timeZone : timeZones) {
				String offset = FormatUtils.getTime(timeZone.getRawOffset());
				if (!StringUtils.equals("2.000h", offset)) {
					continue;
				}
				rows.add(new Object[] { offset, timeZone.getDisplayName(), timeZone.getID() });
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.setTimeZone(timeZone);
				formatters.add(sdf);
			}
			List<String> columns = Arrays.asList("UTC Offset", "Name", "Id");
			LoggerUtils.logTable(columns, rows, LoggerLevel.INFO, logger);

			String dateString = "2013-02-18T13:23:34.000+0000";
			for (SimpleDateFormat sdf : formatters) {
				Date date1 = sdf.parse(dateString);
				String s1 = sdf.format(date1);
				Date date2 = sdf.parse(s1);
				String s2 = sdf.format(date2);
				if (!StringUtils.equals(s1, s2)) {
					TimeZone timeZone = sdf.getTimeZone();
					String id = timeZone.getID();
					String name = timeZone.getDisplayName();
					// logger.info(s1 + "   " + s2 + "   " + name + "   " + id);
				}
			}

			// logger.info("Formatters: {}", formatters.size());

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public List<TimeZone> getSortedTimeZones(Comparator<TimeZone> comparator) {
		String[] ids = TimeZone.getAvailableIDs();
		List<TimeZone> timeZones = new ArrayList<TimeZone>();
		for (String id : ids) {
			TimeZone timeZone = TimeZone.getTimeZone(id);
			timeZones.add(timeZone);
		}
		Collections.sort(timeZones, comparator);
		return timeZones;
	}

}
