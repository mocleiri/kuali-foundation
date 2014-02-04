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
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	public static Table<Integer, Integer, ?> getTable(List<Environment> envs) {
		Table<Integer, Integer, Object> table = HashBasedTable.create();
		for (int row = 0; row < envs.size(); row++) {
			Environment env = envs.get(row);
			table.put(valueOf(row), valueOf(0), env.getName());
			table.put(valueOf(row), valueOf(1), env.getFqdn());
			table.put(valueOf(row), valueOf(2), env.getJava());
		}
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

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, ?> table) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		for (Comparable<R> rowKey : rowKeys) {
			sb.append(" <tr>\n");
			for (Comparable<C> colKey : colKeys) {
				Object object = table.get(rowKey, colKey);
				String value = getTableCellValue(object);
				sb.append(format("  <td>%s</td>", value));
			}
			sb.append(" </tr>\n");
		}
		sb.append("</table>\n");
		return sb.toString();
	}

	protected static String getTableCellValue(Object object) {
		if (object instanceof Table) {
			Table<?, ?, ?> nested = (Table<?, ?, ?>) object;
			return html(cast(nested));
		} else {
			return object.toString();
		}
	}

	@SuppressWarnings("unchecked")
	public static <R, C> Table<? extends Comparable<R>, ? extends Comparable<C>, ?> cast(Table<?, ?, ?> table) {
		return (Table<? extends Comparable<R>, ? extends Comparable<C>, ?>) table;
	}
}
