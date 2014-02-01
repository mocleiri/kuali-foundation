package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;
import java.util.Properties;
import java.util.SortedSet;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	public static Table<Integer, Integer, String> getTable(List<Environment> envs) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int row = 0; row < envs.size(); row++) {
			Environment env = envs.get(row);
			table.put(Integer.valueOf(row), Integer.valueOf(0), env.getProject());
			table.put(Integer.valueOf(row), Integer.valueOf(1), env.getId().substring(3));
			table.put(Integer.valueOf(row), Integer.valueOf(2), env.getFqdn());
			table.put(Integer.valueOf(row), Integer.valueOf(3), env.getJava());
			table.put(Integer.valueOf(row), Integer.valueOf(4), env.getType());
			table.put(Integer.valueOf(row), Integer.valueOf(5), env.getTomcat().getVersion());
			table.put(Integer.valueOf(row), Integer.valueOf(6), env.getTomcat().getStartup());
			table.put(Integer.valueOf(row), Integer.valueOf(7), env.getTomcat().getUptime());
			Project project = getProject(env);
			Database db = getDatabase(env);
			table.put(Integer.valueOf(row), Integer.valueOf(8), project.getArtifactId());
			table.put(Integer.valueOf(row), Integer.valueOf(9), project.getVersion());
			table.put(Integer.valueOf(row), Integer.valueOf(10), db.getVendor());
			table.put(Integer.valueOf(row), Integer.valueOf(11), db.getUrl());
			table.put(Integer.valueOf(row), Integer.valueOf(12), db.getUsername());
		}
		return table;
	}

	protected static Database getDatabase(Environment env) {
		if (env.getApplication().isPresent()) {
			Application app = env.getApplication().get();
			return app.getDatabase();
		} else {
			return new Database();
		}
	}

	protected static Project getProject(Environment env) {
		if (env.getApplication().isPresent()) {
			Application app = env.getApplication().get();
			return app.getProject();
		} else {
			return new ImmutableProject("na", "na", "na", new Properties());
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
				sb.append(format("  <td>%s</td>", table.get(rowKey, colKey).toString()));
			}
			sb.append(" </tr>\n");
		}
		sb.append("</table>\n");
		return sb.toString();
	}
}
