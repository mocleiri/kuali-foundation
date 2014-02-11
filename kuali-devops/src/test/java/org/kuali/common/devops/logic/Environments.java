package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.kuali.common.devops.metadata.model.EC2Instance;
import org.kuali.common.devops.metadata.model.EC2Tag;
import org.kuali.common.devops.metadata.model.Memory;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Scm;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.devops.table.Label;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.Size;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class Environments extends Examiner {

	private static final String BUILD_DATE_DISPLAY_FORMAT = "yyyy-MM-dd HH:mm z";
	private static final String BUILD_DATE_DISPLAY_TIME_ZONE = "US/Eastern";
	private static final String NOT_AVAILABLE = "n/a";

	public static Table<Integer, Label, String> getTable(List<Environment> envs) {
		Table<Integer, Label, String> table = HashBasedTable.create();
		for (Integer row = 0; row < envs.size(); row++) {
			addRow(table, getRowData(envs.get(row)));
		}
		return table;
	}

	protected static SimpleDateFormat getBuildDateFormatter() {
		TimeZone zone = TimeZone.getTimeZone(BUILD_DATE_DISPLAY_TIME_ZONE);
		SimpleDateFormat sdf = new SimpleDateFormat(BUILD_DATE_DISPLAY_FORMAT);
		sdf.setTimeZone(zone);
		return sdf;
	}

	protected static Map<Label, String> getRowData(Environment env) {
		String dest = PROTOCOL + env.getFqdn();
		String href = href(dest, dest);
		String java = env.getJava().isPresent() ? env.getJava().get() : NOT_AVAILABLE;
		Optional<Application> app = env.getApplication();
		Map<Label, String> map = Maps.newHashMap();
		map.put(EnvironmentTableColumns.NAME.getLabel(), getEnvironmentInteger(env.getName()) + "");
		map.put(EnvironmentTableColumns.URL.getLabel(), href);
		map.put(EnvironmentTableColumns.APP.getLabel(), app.isPresent() ? app.get().getProject().getArtifactId() : NOT_AVAILABLE);
		map.put(EnvironmentTableColumns.VERSION.getLabel(), app.isPresent() ? app.get().getProject().getVersion() : NOT_AVAILABLE);
		map.put(EnvironmentTableColumns.PURPOSE.getLabel(), getPurpose(env.getServer()));
		map.put(EnvironmentTableColumns.BUILD_DATE.getLabel(), app.isPresent() ? getBuildDate(app.get().getProject()) : NOT_AVAILABLE);
		map.put(EnvironmentTableColumns.SCM.getLabel(), getScmDisplay(env));
		map.put(EnvironmentTableColumns.DATABASE.getLabel(), app.isPresent() ? getDatabaseUrl(app.get().getDatabase()) : NOT_AVAILABLE);
		map.put(EnvironmentTableColumns.SCHEMA.getLabel(), app.isPresent() ? getDatabaseSchema(app.get().getDatabase()) : NOT_AVAILABLE);
		map.put(EnvironmentTableColumns.JAVA.getLabel(), java);
		map.put(EnvironmentTableColumns.SERVER.getLabel(), getServer(env.getServer()));
		map.put(EnvironmentTableColumns.TOMCAT.getLabel(), getTomcat(env));
		return map;
	}

	protected static String getToolTip(String hover, String tip) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href='#' class='tooltip'>");
		sb.append(hover);
		sb.append("<span>" + tip + "</span>");
		sb.append("</a>");
		return sb.toString();
	}

	protected static Optional<String> getTagValue(EC2Instance instance, String tagName) {
		for (EC2Tag tag : instance.getTags()) {
			if (tag.getKey().equals(tagName)) {
				return Optional.of(tag.getValue());
			}
		}
		return absent();

	}

	protected static String getPurpose(EC2Instance server) {
		Optional<String> purpose = getTagValue(server, "Purpose");
		Optional<String> desc = getTagValue(server, "Description");
		if (!purpose.isPresent()) {
			return NOT_AVAILABLE;
		}
		if (!desc.isPresent()) {
			return purpose.get();
		}
		return getToolTip(purpose.get(), desc.get());
	}

	protected static int getEnvironmentInteger(String environmentName) {
		checkArgument(environmentName.startsWith("env"), "[%s] does not start with 'env'");
		return Integer.parseInt(environmentName.substring(3));
	}

	protected static String href(String dest, String show) {
		return "<a href='" + dest + "'>" + show + "</a>";
	}

	protected static NumberFormat getAgeFormatter() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		return nf;
	}

	protected static String getTomcat(Environment env) {
		Optional<Tomcat> optional = env.getTomcat();
		if (!optional.isPresent()) {
			return NOT_AVAILABLE;
		} else {
			Optional<Memory> memory = env.getMemory();
			Tomcat tomcat = optional.get();
			String uptime = getTime(tomcat.getStartupTime());
			StringBuilder sb = new StringBuilder();
			sb.append("<div id='data'>" + tomcat.getVersion() + "</div>\n");
			sb.append("<div id='data'>uptime " + uptime + "</div>\n");
			sb.append(getMemory(memory));
			return sb.toString();
		}
	}

	protected static String getMemory(Optional<Memory> optional) {
		if (!optional.isPresent()) {
			return NOT_AVAILABLE;
		} else {
			Memory memory = optional.get();
			String max = FormatUtils.getSize(memory.getMax(), Size.GB);
			String used = FormatUtils.getSize(memory.getUsed(), Size.GB);
			return format("<div id='data'>mem [used:%s, max:%s]</div>\n", used, max);
		}
	}

	protected static String getTime(Optional<Long> millis) {
		if (!millis.isPresent()) {
			return NOT_AVAILABLE;
		} else {
			return FormatUtils.getTime(currentTimeMillis() - millis.get(), getAgeFormatter());
		}
	}

	protected static String getApplication(Environment env) {
		if (!env.getApplication().isPresent()) {
			return NOT_AVAILABLE;
		} else {
			Application app = env.getApplication().get();
			Project project = app.getProject();
			return project.getArtifactId();
			/*
			 * Optional<Database> database = app.getDatabase();
			 * 
			 * TableContext context = TableContext.builder().columnLabels(false).border(false).build(); Table<Integer, Integer, String> table = HashBasedTable.create();
			 * 
			 * String buildId = project.getArtifactId() + " :: " + project.getVersion() + " :: " + getBuildDate(project); String databaseId = getDatabaseId(database);
			 * 
			 * addRow(table, buildId); addRow(table, databaseId);
			 * 
			 * // return Html.html(context, table); return "<div>" + buildId + "</div><div>" + databaseId + "</div>";
			 */
		}
	}

	protected static String getScmDisplay(Environment env) {
		if (!env.getApplication().isPresent()) {
			return NOT_AVAILABLE;
		}
		Application app = env.getApplication().get();
		Project project = app.getProject();
		Optional<Scm> optional = app.getScm();
		String vendor = getScmVendor(project);
		if (optional.isPresent()) {
			Scm scm = optional.get();
			return href(scm.getUrl(), vendor + ":" + scm.getRevision());
		} else {
			return vendor + ":" + NOT_AVAILABLE;
		}
	}

	protected static String getDatabaseUrl(Optional<Database> optional) {
		if (!optional.isPresent()) {
			return NOT_AVAILABLE;
		} else {
			return optional.get().getUrl();
		}
	}

	protected static String getDatabaseSchema(Optional<Database> optional) {
		if (!optional.isPresent()) {
			return NOT_AVAILABLE;
		} else {
			return optional.get().getUsername();
		}
	}

	protected static String getBuildDate(Project project) {
		String property = project.getProperties().getProperty("project.build.timestamp.millis");
		if (property == null) {
			return NOT_AVAILABLE;
		} else {
			long millis = Long.parseLong(property);
			Date date = new Date(millis);
			return getBuildDateFormatter().format(date);
		}
	}

	protected static String getScmVendor(Project project) {
		Optional<String> vendor = fromNullable(project.getProperties().getProperty("project.scm.vendor"));
		if (!vendor.isPresent()) {
			// Any Kuali app without this property is old enough that it was sourced from Subversion for sure
			return "svn";
		} else {
			return vendor.get();
		}
	}

	protected static String getServer(EC2Instance instance) {
		// TableContext context = TableContext.builder().columnLabels(false).border(false).build();
		String age = FormatUtils.getTime(currentTimeMillis() - instance.getLaunchTime(), getAgeFormatter());
		Table<Integer, Integer, String> table = HashBasedTable.create();
		addRow(table, ImmutableList.of(instance.getType()));
		addRow(table, ImmutableList.of("age " + age));
		// return Html.html(context, table);
		return "<div>" + instance.getType() + "</div><div>age " + age + "</div>";
	}

	protected static void addRow(Table<Integer, Label, String> table, Map<Label, String> map) {
		Integer row = table.rowKeySet().size();
		for (Label label : map.keySet()) {
			table.put(row, label, map.get(label));
		}
	}

	protected static void addRow(Table<Integer, Integer, String> table, String string) {
		addRow(table, ImmutableList.of(string));
	}

	protected static void addRow(Table<Integer, Integer, String> table, List<String> strings) {
		Integer row = table.rowKeySet().size();
		for (Integer column = 0; column < strings.size(); column++) {
			table.put(row, column, strings.get(column));
		}
	}

}
