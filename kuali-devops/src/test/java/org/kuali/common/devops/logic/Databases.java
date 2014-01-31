package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;

import com.google.common.collect.ImmutableList;

public class Databases {

	private static final List<String> VENDORS = ImmutableList.of("oracle", "mysql");

	public static void update(List<Environment> envs) {
		for (Environment env : envs) {
			if (env.getApplication().isPresent()) {
				System.out.println(env.getFqdn());
				Application app = env.getApplication().get();
				Database db = getDatabase(app);
				app.setDatabase(db);
			}
		}
	}

	public static Database getDatabase(Application app) {
		Properties config = app.getConfiguration();
		String username = getProperty(config, "datasource.username", "jdbc.username");
		String url = getProperty(config, "datasource.url");
		String vendor = getVendor(url);
		Database db = new Database();
		db.setVendor(vendor);
		db.setUsername(username);
		db.setUrl(url);
		return db;
	}

	protected static String getVendor(String url) {
		for (String vendor : VENDORS) {
			if (url.contains(vendor)) {
				return vendor;
			}
		}
		return "na";
	}

	protected static String getProperty(Properties properties, String... keys) {
		for (String key : keys) {
			String value = properties.getProperty(key);
			if (value != null) {
				return value;
			}
		}
		return null;
	}
}
