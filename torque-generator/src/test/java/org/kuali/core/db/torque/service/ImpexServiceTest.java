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
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.torque.task.TorqueDataModelTask;
import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.core.db.torque.DumpTask;
import org.kuali.core.db.torque.KualiTorqueDataDumpTask;
import org.kuali.core.db.torque.KualiTorqueSchemaDumpTask;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.w3c.dom.Document;

public class ImpexServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ImpexServiceTest.class);

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			Properties p = getProperties();
			ImpexContext context = getImpexContext(p);
			log(context);
			prepareFileSystem(context);
			ImpexService service = new DefaultImpexService();
			DatabaseContext database = service.getDatabaseObjectLists(context);
			service.fillInMetaData(context, database);
			Document document = service.getDocument(context, database);
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(context.getSchemaXmlFile()));
			service.serialize(document, context.getSchemaXmlFile(), context.getEncoding());
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void log(ImpexContext context) {
		logger.info("---------------------------------------------------------------");
		logger.info("Impex XML Dump");
		logger.info("---------------------------------------------------------------");
		logger.info("Database Vendor - {}", context.getDatabaseVendor());
		logger.info("Url - {}", context.getUrl());
		logger.info("Schema - {}", context.getSchema());
		logger.info("Username - {}", context.getUsername());
		logger.info("Password - {}", LoggerUtils.getPassword(context.getUsername(), context.getPassword()));
		logger.info("Driver - {}", context.getDriver());
		logger.info("Table Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableIncludes()));
		logger.info("Table Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableExcludes()));
		logger.info("View Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getViewIncludes()));
		logger.info("View Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getViewExcludes()));
		logger.info("Sequence Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getSequenceIncludes()));
		logger.info("Sequence Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getSequenceExcludes()));
		logger.info("---------------------------------------------------------------");
	}

	protected DataSource getDataSource(Properties p) {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(p.getProperty("impex.driver"));
		dmds.setPassword(p.getProperty("impex.password"));
		dmds.setUsername(p.getProperty("impex.username"));
		dmds.setUrl(p.getProperty("impex.url"));
		return dmds;
	}

	protected void prepareFileSystem(ImpexContext context) throws IOException {
		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, context.getContextProperties());
	}

	protected Properties getProperties() {
		String tableIncludes = "";
		String viewIncludes = tableIncludes;
		String sequenceIncludes = tableIncludes;
		int threads = 15;

		Properties p = new Properties();
		p.setProperty("project.basedir", System.getProperty("user.home") + "/ws/kuali-jdbc-2.0");
		p.setProperty("project.build.directory", p.getProperty("project.basedir") + "/target");
		p.setProperty("project.artifactId", "ks-rice-db");
		p.setProperty("impex.table.includes", tableIncludes);
		p.setProperty("impex.view.includes", viewIncludes);
		p.setProperty("impex.sequence.includes", sequenceIncludes);
		p.setProperty("impex.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
		p.setProperty("impex.driver", "oracle.jdbc.driver.OracleDriver");
		p.setProperty("impex.username", "KS_SOURCE_DB_SPRING");
		p.setProperty("impex.password", p.getProperty("impex.username"));
		p.setProperty("impex.schema", p.getProperty("impex.username"));
		p.setProperty("impex.databaseVendor", "oracle");
		p.setProperty("impex.dateFormat", "yyyyMMddHHmmss");
		p.setProperty("impex.antCompatibilityMode", "true");
		p.setProperty("impex.threads", threads + "");
		p.setProperty("impex.workingDir", p.getProperty("project.build.directory") + "/impex");
		// p.setProperty("impex.schemaXMLFile", p.getProperty("impex.workingDir") + "/xml/schema.xml");
		// p.setProperty("impex.reportFile", "../reports/report." + p.getProperty("project.artifactId") + ".datadtd.generation");
		// p.setProperty("impex.contextProperties", p.getProperty("impex.workingDir") + "/reports/context.datadtd.properties");
		// p.setProperty("impex.comment", "Generated by the spring-maven-plugin");
		// p.setProperty("impex.processTables", "true");
		// p.setProperty("impex.processSequences", "true");
		// p.setProperty("impex.processViews", "true");
		// p.setProperty("impex.controlTemplate", "data/Control.vm");
		// p.setProperty("impex.printMetaInfLists", "false");
		return p;
	}

	protected Task getGenerateDtdTask(ImpexContext context, Project project) {
		TorqueDataModelTask task = new TorqueDataModelTask();
		task.setProject(project);
		task.setOutputDirectory(context.getWorkingDir());
		task.setXmlFile(LocationUtils.getCanonicalPath(context.getSchemaXmlFile()));
		task.setTargetDatabase(context.getDatabaseVendor());
		task.setContextProperties(LocationUtils.getCanonicalPath(context.getContextProperties()));
		task.setUseClasspath(true);
		task.setControlTemplate(context.getControlTemplate());
		task.setOutputFile(context.getReportFile());
		return task;
	}

	protected Task getDataDumpTask(ImpexContext context, Project project) {
		KualiTorqueDataDumpTask task = new KualiTorqueDataDumpTask();
		fillInTask(task, context, project);
		task.setBuildDirectory(context.getWorkingDir());
		task.setDataXMLDir(context.getWorkingDir());
		task.setDateFormat(context.getDateFormat());
		task.setPrintMetaInfLists(context.isPrintMetaInfLists());
		return task;
	}

	protected Task getSchemaDumpTask(ImpexContext context, Project project) {
		KualiTorqueSchemaDumpTask task = new KualiTorqueSchemaDumpTask();
		fillInTask(task, context, project);
		task.setSchemaXMLFile(context.getSchemaXmlFile());
		task.setProcessTables(context.isProcessTables());
		task.setProcessSequences(context.isProcessSequences());
		task.setProcessViews(context.isProcessViews());
		task.setDataSource(context.getDataSource());
		task.setThreads(context.getThreads());
		return task;
	}

	protected void fillInTask(DumpTask task, ImpexContext context, Project project) {
		task.setProject(project);
		task.setTargetDatabase(context.getDatabaseVendor());
		task.setArtifactId(context.getArtifactId());
		task.setSchema(context.getSchema());
		task.setDriver(context.getDriver());
		task.setUrl(context.getUrl());
		task.setUsername(context.getUsername());
		task.setPassword(context.getPassword());
		task.setComment(context.getComment());
		task.setTableIncludes(context.getTableIncludes());
		task.setTableExcludes(context.getTableExcludes());
		task.setSequenceIncludes(context.getSequenceIncludes());
		task.setSequenceExcludes(context.getSequenceExcludes());
		task.setViewIncludes(context.getViewIncludes());
		task.setViewExcludes(context.getViewExcludes());
		task.setAntCompatibilityMode(context.isAntCompatibilityMode());
	}

	protected ImpexContext getImpexContext(Properties p) {
		ImpexContext context = new ImpexContext();
		context.setArtifactId(p.getProperty("project.artifactId"));
		context.setSchema(p.getProperty("impex.schema"));
		context.setDriver(p.getProperty("impex.driver"));
		context.setUrl(p.getProperty("impex.url"));
		context.setUsername(p.getProperty("impex.username"));
		context.setPassword(p.getProperty("impex.password"));
		context.setDatabaseVendor(p.getProperty("impex.databaseVendor"));
		context.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.includes")));
		context.setTableExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.excludes")));
		context.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.includes")));
		context.setSequenceExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.excludes")));
		context.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.includes")));
		context.setViewExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.excludes")));
		context.setDateFormat(p.getProperty("impex.dateFormat"));
		context.setWorkingDir(new File(p.getProperty("impex.workingDir")));

		context.setSchemaXmlFile(new File(context.getWorkingDir(), "schema.xml"));

		// Don't mess with these two
		context.setContextProperties(new File(context.getWorkingDir(), "/reports/context.datadtd.properties"));
		context.setReportFile("../reports/report." + context.getArtifactId() + ".datadtd.generation");

		// Setup the datasource
		context.setDataSource(getDataSource(p));

		if (p.getProperty("impex.comment") != null) {
			context.setComment(p.getProperty("impex.comment"));
		}
		if (p.getProperty("impex.schemaXMLFile") != null) {
			context.setSchemaXmlFile(new File(p.getProperty("impex.schemaXMLFile")));
		}
		if (p.getProperty("impex.threads") != null) {
			context.setThreads(new Integer(p.getProperty("impex.threads")));
		}
		if (p.getProperty("impex.antCompatibilityMode") != null) {
			context.setAntCompatibilityMode(new Boolean(p.getProperty("impex.antCompatibilityMode")));
		}
		if (p.getProperty("impex.encoding") != null) {
			context.setEncoding(p.getProperty("impex.encoding"));
		}
		// context.setProcessTables(new Boolean(p.getProperty("impex.processTables")));
		// context.setProcessSequences(new Boolean(p.getProperty("impex.processSequences")));
		// context.setProcessViews(new Boolean(p.getProperty("impex.processViews")));
		// context.setControlTemplate(p.getProperty("impex.controlTemplate"));
		// context.setPrintMetaInfLists(new Boolean(p.getProperty("impex.printMetaInfLists")));
		return context;
	}
}
