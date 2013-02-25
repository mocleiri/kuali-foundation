/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.core.db.torque.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.service.DefaultImpexGeneratorService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

public class DumpTablesTest {

	private static final Logger logger = LoggerFactory.getLogger(DumpTablesTest.class);

	@Test
	@Ignore
	public void test() {
		try {
			long start = System.currentTimeMillis();
			// Going beyond 5/15 for the metadata/data threads doesn't improve things much
			// Properties p = getProperties("T.*", 5, 15);
			Properties p = getProperties("KR.*,KS.*", 5, 15);
			// Properties p = getProperties("KRIM.*", 5, 15);
			// Properties p = getProperties("KSEN_MSTONE.*", 5, 15);
			ImpexContext sourceContext = getImpexContext(p);
			ImpexUtils.log(sourceContext);

			ImpexContext bundledContext = ImpexUtils.clone(sourceContext, "KS.*,KR.*", "ks-bundled-db");
			ImpexContext riceContext = ImpexUtils.clone(sourceContext, "KR.*", "ks-rice-db");
			ImpexContext appContext = ImpexUtils.clone(sourceContext, "KS.*", "ks-app-db");

			List<ImpexContext> contexts = Arrays.asList(bundledContext, riceContext, appContext);

			ImpexGeneratorService service = new DefaultImpexGeneratorService();
			DatabaseContext database = service.getDatabaseObjectLists(sourceContext);
			service.fillInMetaData(sourceContext, database);
			service.serializeSchemas(contexts, database);
			// service.generateDataDtds(contexts);
			service.generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
			List<DumpTableResult> results = service.dumpTables(sourceContext, database);
			if (sourceContext.isStoreDatabaseTableProperties()) {
				// ImpexUtils.updateAndStoreDatabaseProperties(sourceContext.getDatabaseTableProperties(),
				// sourceContext.getDatabaseTablePropertiesLocation(), results);
			}
			ImpexUtils.doStats(results);
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static DataSource getDataSource(Properties p) {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(p.getProperty("impex.driver"));
		dmds.setPassword(p.getProperty("impex.password"));
		dmds.setUsername(p.getProperty("impex.username"));
		dmds.setUrl(p.getProperty("impex.url"));
		return dmds;
	}

	protected static Properties getProperties(String includes, int metaDataThreads, int dataThreads) {
		String tableIncludes = includes;
		String viewIncludes = includes;
		String sequenceIncludes = includes;

		Properties p = new Properties();
		p.setProperty("project.basedir", System.getProperty("user.home") + "/ws/impex-2.0/torque-generator");
		p.setProperty("project.build.directory", p.getProperty("project.basedir") + "/target");
		p.setProperty("project.artifactId", "ks-source-db");
		p.setProperty("impex.antCompatibilityMode", "false");
		p.setProperty("impex.table.includes", tableIncludes);
		p.setProperty("impex.view.includes", viewIncludes);
		p.setProperty("impex.sequence.includes", sequenceIncludes);
		p.setProperty("impex.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
		p.setProperty("impex.driver", "oracle.jdbc.driver.OracleDriver");
		p.setProperty("impex.username", "KS_SOURCE_DB_SPRING");
		p.setProperty("impex.password", p.getProperty("impex.username"));
		p.setProperty("impex.schema", p.getProperty("impex.username"));
		p.setProperty("impex.databaseVendor", "oracle");
		p.setProperty("impex.metadata.threads", metaDataThreads + "");
		p.setProperty("impex.data.threads", dataThreads + "");
		p.setProperty("impex.workingDir", p.getProperty("project.build.directory") + "/impex");
		p.setProperty("impex.databaseTablePropertiesFile", p.getProperty("project.basedir") + "/src/main/resources/" + p.getProperty("project.artifactId") + ".properties");
		return p;
	}

	protected static ImpexContext getImpexContext(Properties p) {
		ImpexContext context = new ImpexContext();
		// simple property copying
		context.setArtifactId(p.getProperty("project.artifactId"));
		context.setSchema(p.getProperty("impex.schema"));
		context.setDriver(p.getProperty("impex.driver"));
		context.setUrl(p.getProperty("impex.url"));
		context.setUsername(p.getProperty("impex.username"));
		context.setPassword(p.getProperty("impex.password"));
		context.setDatabaseVendor(p.getProperty("impex.databaseVendor"));
		context.setWorkingDir(new File(p.getProperty("impex.workingDir")));
		context.setBaseDir(new File(p.getProperty("project.basedir")));
		context.setBuildDir(new File(p.getProperty("project.build.directory")));
		context.setDatabaseTablePropertiesLocation(p.getProperty("impex.databaseTablePropertiesFile"));

		// Default to [artifactId].xml
		context.setSchemaXmlFile(new File(context.getWorkingDir(), context.getArtifactId() + ".xml"));

		// Setup the datasource
		context.setDataSource(getDataSource(p));

		// Properties that already have default values, don't override unless the corresponding property is explicitly set
		if (p.getProperty("impex.dateFormat") != null) {
			context.setDateFormat(p.getProperty("impex.dateFormat"));
		}
		if (p.getProperty("impex.comment") != null) {
			context.setComment(p.getProperty("impex.comment"));
		}
		if (p.getProperty("impex.schemaXMLFile") != null) {
			context.setSchemaXmlFile(new File(p.getProperty("impex.schemaXMLFile")));
		}
		if (p.getProperty("impex.metadata.threads") != null) {
			context.setMetaDataThreads(new Integer(p.getProperty("impex.metadata.threads")));
		}
		if (p.getProperty("impex.data.threads") != null) {
			context.setDataThreads(new Integer(p.getProperty("impex.data.threads")));
		}
		if (p.getProperty("impex.antCompatibilityMode") != null) {
			context.setAntCompatibilityMode(new Boolean(p.getProperty("impex.antCompatibilityMode")));
		}
		if (p.getProperty("impex.encoding") != null) {
			context.setEncoding(p.getProperty("impex.encoding"));
		}

		// Properties that need processing in some way
		Assert.notNull(context.getDatabaseTablePropertiesLocation());
		if (LocationUtils.exists(context.getDatabaseTablePropertiesLocation())) {
			context.setDatabaseTableProperties(PropertyUtils.load(context.getDatabaseTablePropertiesLocation()));
		} else {
			context.setDatabaseTableProperties(new Properties());
		}
		context.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.includes")));
		context.setTableExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.excludes")));
		context.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.includes")));
		context.setSequenceExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.excludes")));
		context.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.includes")));
		context.setViewExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.excludes")));
		return context;
	}
}
