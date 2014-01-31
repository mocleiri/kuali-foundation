package org.kuali.common.devops.logic;

import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;

public class Databases {

	protected static Database getDatabase(Application app) {
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
