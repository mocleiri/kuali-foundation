package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.System.currentTimeMillis;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.kuali.common.devops.metadata.model.EC2Instance;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Scm;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.devops.table.Label;
import org.kuali.common.devops.table.TableContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class Environments extends Examiner {

	private static final NumberFormat AGE = getAgeFormatter();
	private static final SimpleDateFormat BUILD = new SimpleDateFormat("yyyy-MM-dd HH:mm z");

	public static Table<Integer, Label, String> getTable(List<Environment> envs) {
		Table<Integer, Label, String> table = HashBasedTable.create();
		for (Integer row = 0; row < envs.size(); row++) {
			addRow(table, getRowData(envs.get(row)));
		}
		return table;
	}

	protected static Map<Label, String> getRowData(Environment env) {
		String dest = PROTOCOL + env.getFqdn();
		String href = href(dest, dest);
		String java = env.getJava().isPresent() ? env.getJava().get() : "na";
		Map<Label, String> map = Maps.newHashMap();
		map.put(EnvironmentTableColumns.NAME.getLabel(), getEnvironmentInteger(env.getName()) + "");
		map.put(EnvironmentTableColumns.URL.getLabel(), href);
		map.put(EnvironmentTableColumns.APP.getLabel(), getApplication(env));
		map.put(EnvironmentTableColumns.SCM.getLabel(), getScmDisplay(env));
		map.put(EnvironmentTableColumns.JAVA.getLabel(), java);
		map.put(EnvironmentTableColumns.SERVER.getLabel(), getServer(env.getServer()));
		map.put(EnvironmentTableColumns.TOMCAT.getLabel(), getTomcat(env.getTomcat()));
		return map;
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

	protected static String getTomcat(Optional<Tomcat> optional) {
		if (!optional.isPresent()) {
			return "na";
		} else {
			Tomcat tomcat = optional.get();
			TableContext context = TableContext.builder().columnLabels(false).border(false).build();
			String uptime = getTime(tomcat.getStartupTime());
			Table<Integer, Integer, String> table = HashBasedTable.create();
			addRow(table, tomcat.getVersion());
			addRow(table, "uptime " + uptime);
			return Html.html(context, table);
		}
	}

	protected static String getTime(Optional<Long> millis) {
		if (!millis.isPresent()) {
			return "n/a";
		} else {
			return FormatUtils.getTime(currentTimeMillis() - millis.get(), AGE);
		}
	}

	protected static String getApplication(Environment env) {
		if (!env.getApplication().isPresent()) {
			return "n/a";
		} else {
			Application app = env.getApplication().get();
			Project project = app.getProject();
			Optional<Database> database = app.getDatabase();

			TableContext context = TableContext.builder().columnLabels(false).border(false).build();
			Table<Integer, Integer, String> table = HashBasedTable.create();

			String buildId = project.getArtifactId() + " :: " + project.getVersion() + " :: " + getBuildDate(project);
			String databaseId = getDatabaseId(database);

			addRow(table, buildId);
			addRow(table, databaseId);

			return Html.html(context, table);
		}
	}

	protected static String getScmDisplay(Environment env) {
		if (!env.getApplication().isPresent()) {
			return "n/a";
		}
		Application app = env.getApplication().get();
		Project project = app.getProject();
		Optional<Scm> optional = app.getScm();
		String vendor = getScmVendor(project);
		if (optional.isPresent()) {
			Scm scm = optional.get();
			return href(scm.getUrl(), vendor + " :: revision " + scm.getRevision());
		} else {
			return vendor + " :: n/a";
		}
	}

	protected static String getDatabaseId(Optional<Database> optional) {
		if (!optional.isPresent()) {
			return "n/a";
		}
		Database database = optional.get();
		if (database.getVendor().equals("mysql")) {
			return database.getUrl();
		} else {
			return database.getUrl() + "&nbsp;&nbsp;::&nbsp;&nbsp" + database.getUsername();
		}
	}

	protected static String getBuildDate(Project project) {
		String property = project.getProperties().getProperty("project.build.timestamp.millis");
		if (property == null) {
			return "n/a";
		} else {
			long millis = Long.parseLong(property);
			Date date = new Date(millis);
			return BUILD.format(date);
		}
	}

	protected static String getScmVendor(Project project) {
		String vendor = project.getProperties().getProperty("project.scm.vendor");
		if (vendor == null) {
			vendor = "svn";
		}
		return vendor;
	}

	protected static String getServer(EC2Instance instance) {
		TableContext context = TableContext.builder().columnLabels(false).border(false).build();
		String age = FormatUtils.getTime(currentTimeMillis() - instance.getLaunchTime(), AGE);
		Table<Integer, Integer, String> table = HashBasedTable.create();
		addRow(table, ImmutableList.of(instance.getType()));
		addRow(table, ImmutableList.of("age " + age));
		return Html.html(context, table);
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
