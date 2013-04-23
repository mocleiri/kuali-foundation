package org.kuali.core.db.torque.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParsingTest {

	private static final Logger logger = LoggerFactory.getLogger(DateParsingTest.class);

	@Test
	public void test() {
		try {
			String[] ids = TimeZone.getAvailableIDs();
			Arrays.sort(ids);
			List<TimeZone> timeZones = new ArrayList<TimeZone>();
			for (String id : ids) {
				TimeZone timeZone = TimeZone.getTimeZone(id);
				timeZones.add(timeZone);
			}

			logger.info("Time zones: {}", timeZones.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
