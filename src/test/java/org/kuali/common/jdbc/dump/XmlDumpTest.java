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
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.KualiTorqueSchemaDumpTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDumpTest {

	private static final Logger logger = LoggerFactory.getLogger(XmlDumpTest.class);

	@Test
	public void test() {
		try {
			Properties p = new Properties();
			p.setProperty("project.basedir", System.getProperty("user.home") + "/ws/kuali-jdbc-2.0");
			p.setProperty("project.build.directory", p.getProperty("project.basedir") + "/target");
			p.setProperty("project.artifactId", "ks-rice-db");
			p.setProperty("impex.workingDir", p.getProperty("project.build.directory") + "/impex");
			p.setProperty("impex.includes", "KRCR_CMPNT.*");
			p.setProperty("impex.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
			p.setProperty("impex.driver", "oracle.jdbc.driver.OracleDriver");
			p.setProperty("impex.username", "KS_SOURCE_DB_SPRING");
			p.setProperty("impex.password", p.getProperty("impex.username"));
			p.setProperty("impex.schema", p.getProperty("impex.username"));
			p.setProperty("impex.targetDatabase", "oracle");
			p.setProperty("impex.dateFormat", "yyyyMMddHHmmss");
			p.setProperty("impex.schemaXMLFile", p.getProperty("impex.workingDir") + "/schema.xml");
			p.setProperty("impex.comment", "Generated by the spring-maven-plugin - http://site.kuali.org/maven/plugins/spring-maven-plugin/latest/plugin-info.html");

			long start = System.currentTimeMillis();
			Project project = getInitializedAntProject();
			DumpContext context = getDumpContext(p);
			Task schemaDumpTask = getSchemaDumpTask(context, project);
			schemaDumpTask.execute();
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Task getSchemaDumpTask(DumpContext context, Project project) {
		KualiTorqueSchemaDumpTask task = new KualiTorqueSchemaDumpTask();
		task.setProject(project);
		task.setTargetDatabase(context.getVendor());
		task.setArtifactId(context.getArtifactId());
		task.setSchema(context.getSchemaName());
		task.setSchemaXMLFile(context.getSchemaXmlFile());
		task.setDriver(context.getDriver());
		task.setUrl(context.getUrl());
		task.setUsername(context.getSchemaName());
		task.setPassword(context.getSchemaName());
		task.setComment(context.getComment());
		task.setIncludePatterns(context.getIncludes());
		task.setExcludePatterns(context.getExcludes());
		task.setProcessTables(true);
		task.setProcessSequences(true);
		task.setProcessViews(true);
		return task;
	}

	protected DumpContext getDumpContext(Properties p) {
		DumpContext context = new DumpContext();
		context.setSchemaXmlFile(new File(p.getProperty("impex.schemaXMLFile")));
		context.setSchemaName(p.getProperty("impex.schema"));
		context.setDriver(p.getProperty("impex.driver"));
		context.setUrl(p.getProperty("impex.url"));
		context.setArtifactId(p.getProperty("project.artifactId"));
		context.setVendor(p.getProperty("impex.targetDatabase"));
		context.setIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.includes")));
		context.setExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.excludes")));
		context.setComment(p.getProperty("impex.comment"));
		context.setDateFormat(p.getProperty("impex.dateFormat"));
		context.setDataXMLDir(new File(p.getProperty("impex.workingDir")));
		context.setBuildDirectory(new File(p.getProperty("impex.workingDir")));
		return context;
	}

	protected Project getInitializedAntProject() {
		Project antProject = new Project();
		antProject.init();
		return antProject;
	}

}
