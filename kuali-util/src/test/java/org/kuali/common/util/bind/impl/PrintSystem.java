package org.kuali.common.util.bind.impl;

import java.util.SortedSet;
import java.util.TreeSet;

public class PrintSystem {

	public static void main(String[] args) {
		try {
			SortedSet<String> keys = new TreeSet<String>(System.getProperties().stringPropertyNames());
			for (String key : keys) {
				String value = System.getProperty(key).replace("\r", "CR").replace("\n", "LF");
				System.out.println(key + "=" + value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
