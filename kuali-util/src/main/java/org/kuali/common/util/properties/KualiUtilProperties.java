package org.kuali.common.util.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectIdentifier;
import org.kuali.common.util.project.ProjectUtils;

public class KualiUtilProperties {

	private static final ProjectIdentifier PID = KualiUtilProjectConstants.PROJECT_IDENTIFIER;
	private static final String PREFIX = ProjectUtils.getClasspathPrefix(PID);

	public static class Scm {
		public static List<Location> getLocations() {
			return Collections.singletonList(new Location(PREFIX + "/scm.properties"));
		}
	}

	public static class MetaInf {
		private static final String METAINF_PREFIX = PREFIX + "/metainf";
		private static Location COMMON = new Location(METAINF_PREFIX + "/common.properties");
		private static Location COMMON_BUILD = new Location(METAINF_PREFIX + "/build/common.properties");

		public static class Mpx {
			private static Location MPX = new Location(METAINF_PREFIX + "/mpx.properties");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, MPX));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(COMMON_BUILD);
					locations.add(MPX);
					locations.add(new Location(METAINF_PREFIX + "/build/mpx.properties"));
					return Collections.unmodifiableList(locations);
				}
			}
		}

		public static class Sql {
			private static Location SQL = new Location(METAINF_PREFIX + "/sql.properties");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, SQL));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(COMMON_BUILD);
					locations.add(SQL);
					locations.add(new Location(METAINF_PREFIX + "/build/sql.properties"));
					return Collections.unmodifiableList(locations);
				}
			}
		}
	}
}
