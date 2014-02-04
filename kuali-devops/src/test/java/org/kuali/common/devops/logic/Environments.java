package org.kuali.common.devops.logic;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

import java.util.List;
import java.util.SortedSet;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	public static Table<Integer, Integer, String> getTable(List<Environment> envs) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (Integer row = 0; row < envs.size(); row++) {
			addRow(table, getRowData(envs.get(row)));
		}
		return table;
	}

	public static List<String> getRowData(Environment env) {
		String java = env.getJava().isPresent() ? env.getJava().get() : "na";
		return ImmutableList.of(env.getName(), env.getFqdn(), java);
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
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		for (Comparable<R> rowKey : rowKeys) {
			sb.append(" <tr>\n");
			for (Comparable<C> colKey : colKeys) {
				sb.append(format("  <td>%s</td>\n", table.get(rowKey, colKey)));
			}
			sb.append(" </tr>\n");
		}
		sb.append("</table>\n");
		return sb.toString();
	}

}
