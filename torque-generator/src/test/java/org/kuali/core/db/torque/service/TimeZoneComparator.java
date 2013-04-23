package org.kuali.core.db.torque.service;

import java.util.Comparator;
import java.util.TimeZone;

public class TimeZoneComparator implements Comparator<TimeZone> {

	@Override
	public int compare(TimeZone one, TimeZone two) {
		int compare = Double.compare(one.getRawOffset(), two.getRawOffset());
		if (compare == 0) {
			return one.getDisplayName().compareTo(two.getDisplayName());
		} else {
			return compare;
		}
	}

}
