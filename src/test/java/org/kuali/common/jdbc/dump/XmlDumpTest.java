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
package org.kuali.common.jdbc.dump;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class XmlDumpTest {

	private static final Logger logger = LoggerFactory.getLogger(XmlDumpTest.class);

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			Project project = getInitializedAntProject();
			Properties p = getProperties();
			DumpContext context = getDumpContext(p);
			log(context);
			prepareFileSystem(context);
			Task schemaDump = getSchemaDumpTask(context, project);
			Task dataDump = getDataDumpTask(context, project);
			Task generateDtd = getGenerateDtdTask(context, project);
			// schemaDump.execute();
			// dataDump.execute();
			// generateDtd.execute();
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void log(DumpContext context) {
		logger.info("--------------------------------------------------");
		logger.info("Kuali Database XML Dumper");
		logger.info("--------------------------------------------------");
		logger.info("Database Vendor - {}", context.getDatabaseVendor());
		logger.info("Url - {}", context.getUrl());
		logger.info("Schema - {}", context.getSchemaName());
		logger.info("Username - {}", context.getUsername());
		logger.info("Password - {}", LoggerUtils.getPassword(context.getUsername(), context.getPassword()));
		logger.info("Driver - {}", context.getDriver());
		logger.info("Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableIncludes()));
		logger.info("Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableExcludes()));
		logger.info("--------------------------------------------------");
	}

	protected DataSource getDataSource(Properties p) {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(p.getProperty("impex.driver"));
		dmds.setPassword(p.getProperty("impex.password"));
		dmds.setUsername(p.getProperty("impex.username"));
		dmds.setUrl(p.getProperty("impex.url"));
		return dmds;
	}

	protected void prepareFileSystem(DumpContext context) throws IOException {
		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, context.getContextProperties());
	}

	protected Properties getProperties() {
		String tableIncludes = "KRSB_Q.*";
		String viewIncludes = ".*"; // tableIncludes; // "KRCR_CMPNT.*";
		String sequenceIncludes = ".*"; // tableIncludes; // "KRCR_CMPNT.*";

		Properties p = new Properties();
		p.setProperty("project.basedir", System.getProperty("user.home") + "/ws/kuali-jdbc-2.0");
		p.setProperty("project.build.directory", p.getProperty("project.basedir") + "/target");
		p.setProperty("project.artifactId", "ks-rice-db");
		p.setProperty("impex.workingDir", p.getProperty("project.build.directory") + "/impex");
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
		p.setProperty("impex.schemaXMLFile", p.getProperty("impex.workingDir") + "/schema.xml");
		p.setProperty("impex.comment", "Generated by the spring-maven-plugin - http://site.kuali.org/maven/plugins/spring-maven-plugin/latest/plugin-info.html");
		p.setProperty("impex.antCompatibilityMode", "true");
		p.setProperty("impex.processTables", "true");
		p.setProperty("impex.processSequences", "true");
		p.setProperty("impex.processViews", "true");
		p.setProperty("impex.printMetaInfLists", "false");
		p.setProperty("impex.contextProperties", p.getProperty("project.build.directory") + "/reports/context.datadtd.properties");
		p.setProperty("impex.controlTemplate", "data/Control.vm");
		p.setProperty("impex.reportFile", "../reports/report." + p.getProperty("project.artifactId") + ".datadtd.generation");
		return p;
	}

	protected Task getGenerateDtdTask(DumpContext context, Project project) {
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

	protected Task getDataDumpTask(DumpContext context, Project project) {
		KualiTorqueDataDumpTask task = new KualiTorqueDataDumpTask();
		fillInTask(task, context, project);
		task.setBuildDirectory(context.getWorkingDir());
		task.setDataXMLDir(context.getWorkingDir());
		task.setDateFormat(context.getDateFormat());
		task.setPrintMetaInfLists(context.isPrintMetaInfLists());
		return task;
	}

	protected Task getSchemaDumpTask(DumpContext context, Project project) {
		KualiTorqueSchemaDumpTask task = new KualiTorqueSchemaDumpTask();
		fillInTask(task, context, project);
		task.setSchemaXMLFile(context.getSchemaXmlFile());
		task.setProcessTables(context.isProcessTables());
		task.setProcessSequences(context.isProcessSequences());
		task.setProcessViews(context.isProcessViews());
		task.setDataSource(context.getDataSource());
		return task;
	}

	protected void fillInTask(DumpTask task, DumpContext context, Project project) {
		task.setProject(project);
		task.setTargetDatabase(context.getDatabaseVendor());
		task.setArtifactId(context.getArtifactId());
		task.setSchema(context.getSchemaName());
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
	}

	protected DumpContext getDumpContext(Properties p) {
		DumpContext context = new DumpContext();
		context.setArtifactId(p.getProperty("project.artifactId"));
		context.setSchemaXmlFile(new File(p.getProperty("impex.schemaXMLFile")));
		context.setSchemaName(p.getProperty("impex.schema"));
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
		context.setComment(p.getProperty("impex.comment"));
		context.setDateFormat(p.getProperty("impex.dateFormat"));
		context.setWorkingDir(new File(p.getProperty("impex.workingDir")));
		context.setProcessTables(new Boolean(p.getProperty("impex.processTables")));
		context.setProcessSequences(new Boolean(p.getProperty("impex.processSequences")));
		context.setProcessViews(new Boolean(p.getProperty("impex.processViews")));
		context.setPrintMetaInfLists(new Boolean(p.getProperty("impex.printMetaInfLists")));
		context.setContextProperties(new File(p.getProperty("impex.contextProperties")));
		context.setControlTemplate(p.getProperty("impex.controlTemplate"));
		context.setReportFile(p.getProperty("impex.reportFile"));
		context.setDataSource(getDataSource(p));
		return context;
	}

	protected Project getInitializedAntProject() {
		Project antProject = new Project();
		antProject.init();
		return antProject;
	}

}
