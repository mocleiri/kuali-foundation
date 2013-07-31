package org.kuali.common.util.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectUtils;

public class KualiUtilLocations {

	// classpath:org/kuali/common/kuali-util
	private static final String PREFIX = ProjectUtils.getClasspathPrefix(KualiUtilProjectConstants.PROJECT_IDENTIFIER);
	private static final String ENCODING = KualiProjectConstants.ENCODING;

	public static List<Location> getAll() {
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(Scm.getLocations());
		locations.addAll(MetaInf.Mpx.getLocations());
		locations.addAll(MetaInf.Mpx.Build.getLocations());
		locations.addAll(MetaInf.Sql.getLocations());
		locations.addAll(MetaInf.Sql.Build.getLocations());
		return Collections.unmodifiableList(locations);
	}

	protected static Location getLoc(String value) {
		return new Location(value, ENCODING);
	}

	public static class Scm {
		public static List<Location> getLocations() {
			return Collections.singletonList(getLoc(PREFIX + "/scm.properties"));
		}
	}

	public static class MetaInf {
		private static final String METAINF_PREFIX = PREFIX + "/metainf";
		private static Location COMMON = getLoc(METAINF_PREFIX + "/common.properties");
		private static Location COMMON_BUILD = getLoc(METAINF_PREFIX + "/build/common.properties");

		public static class Mpx {
			private static Location MPX = getLoc(METAINF_PREFIX + "/mpx.properties");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, MPX));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(COMMON_BUILD);
					locations.add(MPX);
					locations.add(getLoc(METAINF_PREFIX + "/build/mpx.properties"));
					return Collections.unmodifiableList(locations);
				}
			}
		}

		public static class Sql {
			private static Location SQL = getLoc(METAINF_PREFIX + "/sql.properties");

			public static List<Location> getLocations() {
				return Collections.unmodifiableList(Arrays.asList(COMMON, SQL));
			}

			public static class Build {
				public static List<Location> getLocations() {
					List<Location> locations = new ArrayList<Location>();
					locations.add(COMMON);
					locations.add(COMMON_BUILD);
					locations.add(SQL);
					locations.add(getLoc(METAINF_PREFIX + "/build/sql.properties"));
					return Collections.unmodifiableList(locations);
				}
			}
		}
	}
}
