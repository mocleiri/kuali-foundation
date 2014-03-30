package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.project.KualiProjectConstants.OLE_GROUP_ID;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.model.Database;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class Databases {

	private static final List<String> VENDORS = ImmutableList.of("oracle", "mysql");

	public static Optional<Database> getDatabase(String groupId, Properties config) {
		if (config.isEmpty()) {
			return Optional.absent();
		}
		if (groupId.equals(OLE_GROUP_ID)) {
			return Optional.of(getOleDatabase(config));
		}
		String username = config.getProperty("datasource.username");
		String url = config.getProperty("datasource.url");
		String vendor = getVendor(url);
		return Optional.of(Database.builder().vendor(vendor).username(username).url(url).build());
	}

	protected static Database getOleDatabase(Properties config) {
		String vendor = config.getProperty("db.vendor");
		String username = config.getProperty("jdbc.username");
		String urlKey = vendor + ".dba.url";
		String url = config.getProperty(urlKey);

		if (vendor.equals("mysql")) {
			url += "/" + username;
		}
		return Database.builder().vendor(vendor).username(username).url(url).build();
	}

	protected static String getVendor(String url) {
		for (String vendor : VENDORS) {
			if (url.contains(vendor)) {
				return vendor;
			}
		}
		throw illegalState("could not determine a vendor from url -> [%s]", url);
	}

}
