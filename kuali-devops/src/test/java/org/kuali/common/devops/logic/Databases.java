package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.project.KualiProjectConstants;

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
		String groupId = app.getProject().getGroupId();
		if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			return getOleDatabase(config);
		}
		String username = getProperty(config, "datasource.username");
		String url = getProperty(config, "datasource.url");
		String vendor = getVendor(url);
		Database db = new Database();
		db.setVendor(vendor);
		db.setUsername(username);
		db.setUrl(url);
		return db;
	}

	protected static Database getOleDatabase(Properties config) {
		String vendor = getProperty(config, "db.vendor");
		String username = getProperty(config, "jdbc.username");
		String urlKey = vendor + ".dba.url";
		String url = getProperty(config, urlKey);

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
