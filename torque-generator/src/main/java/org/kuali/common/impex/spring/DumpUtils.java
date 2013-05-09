package org.kuali.common.impex.spring;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.DumpExecutable;
import org.kuali.common.impex.ImpexContextCloningFactoryBean;
import org.kuali.common.impex.ImpexContextFactoryBean;
import org.kuali.common.impex.SyncFilesExecutable;
import org.kuali.common.impex.service.DefaultImpexGeneratorService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.service.ScmService;
import org.kuali.common.util.spring.ScmServiceFactoryBean;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DumpUtils {

	private static final String FS = File.separator;

	public static ImpexContext getImpexContext(Environment env, String artifactId, ImpexContext sourceContext) {
		String includes = SpringUtils.getProperty(env, "impex.includes");
		return getBaseContext(env, artifactId, includes, sourceContext);
	}

	public static SyncFilesExecutable getSyncFilesExecutable(Environment env, List<ImpexContext> dumpContexts) {
		SyncFilesExecutable sfe = new SyncFilesExecutable();
		sfe.setService(DumpUtils.getScmService(env));
		sfe.setContexts(dumpContexts);

		// Skip everything related to sync'ing files
		sfe.setSkip(SpringUtils.getBoolean(env, "impex.sync.skip", false));

		// By default, do not commit any changes to SCM
		sfe.setCommitChanges(SpringUtils.getBoolean(env, "impex.scm.commit", false));

		return sfe;
	}

	public static String getFinalDirName(Environment env, String artifactId) {
		StringBuilder sb = new StringBuilder();
		sb.append(SpringUtils.getProperty(env, "project.basedir"));
		sb.append(FS);
		sb.append(artifactId);
		sb.append(FS);
		sb.append("src");
		sb.append(FS);
		sb.append("main");
		sb.append(FS);
		sb.append("resources");
		return sb.toString();
	}

	public static ImpexContext getBaseContext(Environment env, String artifactId, String include, ImpexContext sourceContext) {
		String finalDirName = getFinalDirName(env, artifactId);
		File finalDirectory = new File(finalDirName);
		String schemaFileInclude = "**/" + artifactId + "*";

		ImpexContextCloningFactoryBean factory = new ImpexContextCloningFactoryBean();
		factory.setArtifactId(artifactId);
		factory.setFinalDirectory(finalDirectory);
		factory.setInclude(include);
		factory.setCopyDataFiles(true);
		factory.setSchemaFileInclude(schemaFileInclude);
		factory.setSourceContext(sourceContext);
		return factory.getObject();
	}

	public static DumpExecutable getDumpExecutable(Environment env, ImpexContext sourceContext, List<ImpexContext> dumpContexts) {
		Assert.notNull(dumpContexts, "dumpContexts is null");
		DumpExecutable executable = new DumpExecutable();
		executable.setSourceContext(sourceContext);
		executable.setDatabaseVendors(Arrays.asList("oracle", "mysql"));
		executable.setService(new DefaultImpexGeneratorService());
		executable.setSkip(SpringUtils.getBoolean(env, "impex.dump.skip", false));
		executable.setContexts(dumpContexts);
		return executable;
	}

	public static ScmService getScmService(Environment env) {
		String url = SpringUtils.getProperty(env, "project.scm.developerConnection");
		ScmServiceFactoryBean factory = new ScmServiceFactoryBean();
		factory.setUrl(url);
		return factory.getObject();
	}

	public static ImpexContext getImpexSourceContext(Environment env, Properties properties) {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(SpringUtils.getProperty(env, "impex.driver"));
		dmds.setUrl(SpringUtils.getProperty(env, "impex.url"));
		dmds.setUsername(SpringUtils.getProperty(env, "impex.username"));
		dmds.setPassword(SpringUtils.getProperty(env, "impex.password"));

		ImpexContextFactoryBean factory = new ImpexContextFactoryBean();
		factory.setDataSource(dmds);
		factory.setProperties(properties);
		return factory.getObject();
	}

}
