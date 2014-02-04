package org.kuali.common.devops.logic;

import static java.lang.System.currentTimeMillis;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.EC2Instance;
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
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	private static final NumberFormat AGE = getAgeFormatter();
	private static final SimpleDateFormat BUILD = new SimpleDateFormat("yyyy-MM-dd HH:mm z");

	public static Table<Integer, Label, String> getTable(List<Environment> envs) {
		Table<Integer, Label, String> table = HashBasedTable.create();
		for (Integer row = 0; row < envs.size(); row++) {
			addRow(table, getRowData(envs.get(row)));
		}
		return table;
	}

	public static Map<Label, String> getRowData(Environment env) {
		String url = "http://" + env.getFqdn();
		String java = env.getJava().isPresent() ? env.getJava().get() : "na";
		Map<Label, String> map = Maps.newHashMap();
		map.put(EnvTable.NAME.getLabel(), env.getName());
		map.put(EnvTable.URL.getLabel(), href(url, url));
		map.put(EnvTable.JAVA.getLabel(), java);
		map.put(EnvTable.SERVER.getLabel(), getServer(env.getServer()));
		map.put(EnvTable.TOMCAT.getLabel(), getTomcat(env.getTomcat()));
		map.put(EnvTable.APP.getLabel(), getApplication(env.getApplication()));
		return map;
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

	public static String getTomcat(Optional<Tomcat> optional) {
		if (!optional.isPresent()) {
			return "na";
		} else {
			Tomcat tomcat = optional.get();
			TableContext context = TableContext.builder().headers(false).border(false).build();
			String uptime = null;
			if (tomcat.getStartupTime() == -1) {
				uptime = "n/a";
			} else {
				uptime = FormatUtils.getTime(currentTimeMillis() - tomcat.getStartupTime(), AGE);
			}
			Table<Integer, Integer, String> table = HashBasedTable.create();
			addRow(table, tomcat.getVersion());
			addRow(table, "uptime " + uptime);
			return html(context, table);
		}
	}

	public static String getApplication(Optional<Application> optional) {
		if (!optional.isPresent()) {
			return "na";
		} else {
			Application app = optional.get();
			Project project = app.getProject();
			Optional<Database> database = app.getDatabase();

			TableContext context = TableContext.builder().headers(false).border(false).build();
			Table<Integer, Integer, String> table = HashBasedTable.create();

			String buildId = project.getArtifactId() + "::" + project.getVersion() + "::" + getBuildDate(project);
			String databaseId = getDatabaseId(database);
			String scm = getScmDisplay(app.getScm(), project);

			addRow(table, buildId);
			addRow(table, databaseId);
			addRow(table, scm);

			return html(context, table);
		}
	}

	protected static String getScmDisplay(Optional<Scm> optional, Project project) {
		String vendor = getScmVendor(project);
		if (optional.isPresent()) {
			Scm scm = optional.get();
			return href(scm.getUrl(), vendor + ":revision:" + scm.getRevision());
		} else {
			return vendor + " : n/a";
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

	public static String getServer(EC2Instance instance) {
		TableContext context = TableContext.builder().headers(false).border(false).build();
		String age = FormatUtils.getTime(currentTimeMillis() - instance.getLaunchTime(), AGE);
		Table<Integer, Integer, String> table = HashBasedTable.create();
		addRow(table, ImmutableList.of(instance.getType()));
		addRow(table, ImmutableList.of("age " + age));
		return html(context, table);
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

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		return html(table, 0);
	}

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, String> table, int indent) {
		return html(TableContext.builder().indent(indent).build(), table);
	}

	public static String getBorder(Optional<Integer> border) {
		if (border.isPresent()) {
			return "border=\"" + border.get() + "\"";
		} else {
			return "";
		}
	}

	public static <C> String getHeader(TableContext context, SortedSet<Comparable<C>> colKeys, String padding) {
		if (context.isHeaders()) {
			StringBuilder sb = new StringBuilder();
			for (Comparable<C> colKey : colKeys) {
				sb.append(padding + " <th>" + colKey + "</th>");
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static <R, C> String html(TableContext context, Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		String padding = StringUtils.repeat(" ", context.getIndent());
		StringBuilder sb = new StringBuilder();
		sb.append(padding + "<table " + getBorder(context.getBorder()) + ">\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		sb.append(getHeader(context, colKeys, padding));
		for (Comparable<R> rowKey : rowKeys) {
			sb.append(padding + " <tr>\n");
			for (Comparable<C> colKey : colKeys) {
				sb.append(padding + "  <td>\n");
				sb.append(padding + "   " + table.get(rowKey, colKey) + "\n");
				sb.append(padding + "  </td>\n");
			}
			sb.append(padding + " </tr>\n");
		}
		sb.append(padding + "</table>\n");
		return sb.toString();
	}
}
