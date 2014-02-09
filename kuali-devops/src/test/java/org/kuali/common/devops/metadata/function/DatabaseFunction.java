package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.model.Database;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DatabaseFunction implements Function<Properties, Optional<Database>> {

	private static final List<String> VENDORS = ImmutableList.of("oracle", "mysql");

	public DatabaseFunction(Project project) {
		this.project = ImmutableProject.copyOf(checkNotNull(project, "project"));
	}

	private final ImmutableProject project;

	@Override
	public Optional<Database> apply(Properties config) {
		checkNotNull(config, "config");
		return getDatabase(project.getGroupId(), config);
	}

	protected Optional<Database> getDatabase(String groupId, Properties config) {
		if (config.isEmpty()) {
			return Optional.absent();
		}
		if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			return Optional.of(getOleDatabase(config));
		}
		String username = config.getProperty("datasource.username");
		String url = config.getProperty("datasource.url");
		String vendor = getVendor(url);
		return Optional.of(Database.builder().vendor(vendor).username(username).url(url).build());
	}

	protected Database getOleDatabase(Properties config) {
		String vendor = config.getProperty("db.vendor");
		String username = config.getProperty("jdbc.username");
		String urlKey = vendor + ".dba.url";
		String url = config.getProperty(urlKey);

		if (vendor.equals("mysql")) {
			url += "/" + username;
		}
		return Database.builder().vendor(vendor).username(username).url(url).build();
	}

	protected String getVendor(String url) {
		for (String vendor : VENDORS) {
			if (url.contains(vendor)) {
				return vendor;
			}
		}
		throw illegalState("could not determine a vendor from url -> [%s]", url);
	}

	public Project getProject() {
		return project;
	}

}
