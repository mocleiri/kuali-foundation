package org.kuali.common.util;

import java.util.SortedSet;
import java.util.TreeSet;

public class PrintSystemProperties {

	public static void main(String[] args) {
		SortedSet<String> keys = new TreeSet<String>(System.getProperties().stringPropertyNames());
		for (String key : keys) {
			String value = System.getProperty(key).replace("\r", "CR").replace("\n", "LF");
			System.out.println(key + "=" + value);
		}
	}

}
