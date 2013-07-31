package org.kuali.common.util.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class KualiUtilProperties {

	public static class Scm {
		public static List<Location> getLocations() {
			return Collections.singletonList(new Location("${classpath.prefix}/scm.properties"));
		}
	}

	public static class MetaInf {
		static Location COMMON = new Location("${metainf.common}");

		public static class Mpx {
			static Location MPX = new Location("${metainf.mpx}");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, MPX));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(new Location("${metainf.common.build}"));
					locations.add(MPX);
					locations.add(new Location("${metainf.mpx.build}"));
					return Collections.unmodifiableList(locations);
				}
			}
		}

		public static class Sql {
			static Location SQL = new Location("${metainf.sql}");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, SQL));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(new Location("${metainf.common.build}"));
					locations.add(SQL);
					locations.add(new Location("${metainf.sql.build}"));
					return Collections.unmodifiableList(locations);
				}
			}
		}
	}

}
