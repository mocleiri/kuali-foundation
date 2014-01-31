package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;

public class Databases {

	public static void update(List<Environment> envs) {
		for (Environment env : envs) {
			if (env.getApplication().isPresent()) {
				Application app = env.getApplication().get();
				Database db = getDatabase(app);
				app.setDatabase(db);
			}
		}
	}

	public static Database getDatabase(Application app) {
		Properties config = app.getConfiguration();
		String vendor = config.getProperty("db.vendor");
		String username = config.getProperty("datasource.username");
		String url = config.getProperty("datasource.url");
		Database db = new Database();
		db.setVendor(vendor);
		db.setUsername(username);
		db.setUrl(url);
		return db;
	}
}
