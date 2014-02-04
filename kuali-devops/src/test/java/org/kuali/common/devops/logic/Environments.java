package org.kuali.common.devops.logic;

import static java.lang.Integer.valueOf;
import static java.lang.System.currentTimeMillis;

import java.text.NumberFormat;
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

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	private static final NumberFormat AGE = getAgeFormatter();

	public static Table<Integer, Label, String> getTable(List<Environment> envs) {
		Table<Integer, Label, String> table = HashBasedTable.create();
		for (Integer row = 0; row < envs.size(); row++) {
			addRow(table, getRowData(envs.get(row)));
		}
		return table;
	}

	public static Map<Label, String> getRowData(List<Label> columns) {
		Map<Label, String> map = Maps.newHashMap();
		for (Label label : columns) {
			map.put(label, label.getText());
		}
		return map;
	}

	public static Map<Label, String> getRowData(Environment env) {
		String url = "http://" + env.getFqdn();
		String java = env.getJava().isPresent() ? env.getJava().get() : "na";
		Map<Label, String> map = Maps.newHashMap();
		map.put(EnvTable.NAME.getLabel(), env.getName());
		map.put(EnvTable.URL.getLabel(), href(url, url));
		map.put(EnvTable.JAVA.getLabel(), java);
		map.put(EnvTable.SERVER.getLabel(), getHtml(env.getServer()));
		map.put(EnvTable.TOMCAT.getLabel(), getHtml(env.getTomcat()));
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

	public static String getHtml(Optional<Tomcat> tomcat) {
		if (!tomcat.isPresent()) {
			return "na";
		} else {
			TableContext context = TableContext.builder().headers(false).border(false).build();
			String uptime = FormatUtils.getTime(currentTimeMillis() - tomcat.get().getStartupTime(), AGE);
			Table<Integer, Integer, String> table = HashBasedTable.create();
			addRow(table, ImmutableList.of("version", tomcat.get().getVersion()));
			addRow(table, ImmutableList.of("uptime", uptime));
			return html(context, table);
		}
	}

	public static String getApplication(Optional<Application> optional) {
		if (!optional.isPresent()) {
			return "na";
		} else {
			Application app = optional.get();
			Project project = app.getProject();
			Database database = app.getDatabase();
			TableContext context = TableContext.builder().headers(false).border(false).build();
			Table<Integer, Integer, String> table = HashBasedTable.create();
			addRow(table, ImmutableList.of(project.getArtifactId() + "::" + project.getVersion()));
			addRow(table, ImmutableList.of(database.getVendor() + "::" + database.getUsername()));
			addRow(table, ImmutableList.of(database.getUrl()));
			if (app.getScm().isPresent()) {
				Scm scm = app.getScm().get();
				String href = href(scm.getUrl(), "scm");
				addRow(table, ImmutableList.of(href + " revision:" + scm.getRevision()));
			}
			return html(context, table);
		}
	}

	public static String getHtml(EC2Instance instance) {
		TableContext context = TableContext.builder().headers(false).border(false).build();
		return html(context, getTable(instance));
	}

	public static Table<Integer, Integer, String> getTable(EC2Instance instance) {
		String age = FormatUtils.getTime(currentTimeMillis() - instance.getLaunchTime(), AGE);
		Table<Integer, Integer, String> table = HashBasedTable.create();
		addRow(table, ImmutableList.of("type", instance.getType()));
		addRow(table, ImmutableList.of("age", age));
		return table;
	}

	protected static Table<Integer, Integer, ?> getTable(Project project) {
		String revision = project.getProperties().getProperty("project.scm.revision");
		if (StringUtils.isBlank(revision)) {
			revision = "na";
		}
		String url = project.getProperties().getProperty("project.scm.url");
		if (StringUtils.isBlank(url) || "na".equals(url)) {
			url = "na";
		} else {
			List<String> tokens = Lists.newArrayList(Splitter.on(':').splitToList(url));
			tokens.remove(0); // scm
			tokens.remove(0); // svn
			url = Joiner.on(':').join(tokens.iterator());
			url = "<a href=\"" + url + "\">public url</a>";
		}
		Table<Integer, Integer, Object> table = HashBasedTable.create();
		addRow(table, "application", project.getArtifactId());
		addRow(table, "version", project.getVersion());
		addRow(table, "scm", url);
		addRow(table, "revision", revision);
		return table;
	}

	protected static Table<Integer, Integer, ?> getTable(Database db) {
		Table<Integer, Integer, Object> table = HashBasedTable.create();
		addRow(table, "vendor", db.getVendor());
		addRow(table, "url", db.getUrl());
		addRow(table, "username", db.getUsername());
		return table;
	}

	protected static Table<Integer, Integer, ?> getTable(Tomcat tomcat) {
		String uptime = getUptime(tomcat);
		Table<Integer, Integer, Object> table = HashBasedTable.create();
		addRow(table, "version", tomcat.getVersion());
		addRow(table, "uptime", uptime);
		return table;
	}

	protected static String getUptime(Tomcat tomcat) {
		long millis = System.currentTimeMillis() - tomcat.getStartupTime();
		String uptime = FormatUtils.getTime(millis);
		int pos = uptime.indexOf('.');
		if (pos != -1) {
			String uom = uptime.endsWith("ms") ? "ms" : uptime.substring(uptime.length() - 1);
			uptime = uptime.substring(0, pos) + uom;
		}
		return uptime;
	}

	protected static void addRow(Table<Integer, Label, String> table, Map<Label, String> map) {
		Integer row = table.rowKeySet().size();
		for (Label label : map.keySet()) {
			table.put(row, label, map.get(label));
		}
	}

	protected static void addRow(Table<Integer, Integer, String> table, List<String> strings) {
		Integer row = table.rowKeySet().size();
		for (Integer column = 0; column < strings.size(); column++) {
			table.put(row, column, strings.get(column));
		}
	}

	protected static void addRow(Table<Integer, Integer, Object> table, Object... objects) {
		int row = table.rowKeySet().size();
		for (int col = 0; col < objects.length; col++) {
			table.put(valueOf(row), valueOf(col), objects[col]);
		}
	}

	protected static Optional<Database> getDatabase(Environment env) {
		if (env.getApplication().isPresent()) {
			Application app = env.getApplication().get();
			return Optional.of(app.getDatabase());
		} else {
			return Optional.absent();
		}
	}

	protected static Optional<Project> getProject(Environment env) {
		if (env.getApplication().isPresent()) {
			Application app = env.getApplication().get();
			return Optional.of(app.getProject());
		} else {
			return Optional.absent();
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

	public static <R, C> String html(TableContext context, Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		String padding = StringUtils.repeat(" ", context.getIndent());
		StringBuilder sb = new StringBuilder();

		sb.append(padding + "<table " + getBorder(context.getBorder()) + ">\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		if (context.isHeaders()) {
			for (Comparable<C> colKey : colKeys) {
				sb.append(padding + " <th>" + colKey + "</th>");
			}
		}
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
