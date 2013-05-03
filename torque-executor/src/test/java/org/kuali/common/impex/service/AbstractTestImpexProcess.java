/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;

import junit.framework.Assert;
import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.springframework.util.ResourceUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author andrewlubbers
 */
public abstract class AbstractTestImpexProcess {

	protected static final String LF = "\n";

	@Resource
	protected ImpexContext impexContext;

	@Resource
	protected ImpexGeneratorService impexService;

	@Resource
	protected JdbcService jdbcService;

	protected byte[] getDataBytes(List<DumpTableResult> results) throws IOException {
		StringBuilder dataBuilder = new StringBuilder();
		List<File> allFiles = new ArrayList<File>();
		for (DumpTableResult result : results) {
			allFiles.addAll(result.getFiles());
		}

		Collections.sort(allFiles);
		for (File f : allFiles) {
			BufferedReader reader = LocationUtils.getBufferedReader(f.getAbsolutePath());
			String line = reader.readLine();
			while (line != null) {
				dataBuilder.append(line).append(LF);
				line = reader.readLine();
			}
		}

		return dataBuilder.toString().getBytes();
	}

	protected void compareLoadResults(List<DumpTableResult> initialLoadResults, List<DumpTableResult> secondaryLoadResults) {
		Assert.assertEquals("Number of dump table results must be equal", initialLoadResults.size(), secondaryLoadResults.size());

		// map the results by table name
		Map<String, DumpTableResult> initialTableMap = createColumnMap(initialLoadResults);
		Map<String, DumpTableResult> secondaryTableMap = createColumnMap(secondaryLoadResults);

		// assert all tables are the same
		Set<String> initialTableNames = initialTableMap.keySet();
		Set<String> secondaryTableNames = secondaryTableMap.keySet();

		assertTrue("Table names are not the same in each result set", initialTableNames.containsAll(secondaryTableNames) && secondaryTableNames.containsAll(initialTableNames));

		for (String tableName : initialTableNames) {
			DumpTableResult initialResult = initialTableMap.get(tableName);
			DumpTableResult secondaryResult = secondaryTableMap.get(tableName);

			assertEquals("Number of rows not equal", initialResult.getRows(), secondaryResult.getRows());
			assertEquals("Number of created files not equal", initialResult.getFiles().size(), secondaryResult.getFiles().size());
		}
	}

	protected Map<String, DumpTableResult> createColumnMap(List<DumpTableResult> results) {
		Map<String, DumpTableResult> colMap = new HashMap<String, DumpTableResult>(results.size());

		for (DumpTableResult result : results) {
			colMap.put(result.getTable().getName(), result);
		}

		return colMap;
	}

    private static Properties getTestMavenProperties(String fileName) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(ResourceUtils.getFile(fileName)));

        p.setProperty("project.groupId", "org.kuali.foundation");
        p.setProperty("project.artifactId", "torque-executor");
        p.setProperty("project.version", "2.1.8-SNAPSHOT");
        p.setProperty("project.encoding", "UTF-8");
        p.setProperty("project.orgId", "org.kuali");
        p.setProperty("project.orgId.code", "kuali");
        p.setProperty("project.orgId.path", "org/kuali");

        MavenUtils.augmentProjectProperties(p);

        return p;
    }

	protected void doTest(String initialPropertiesFile, String mpxPropertiesFile) throws Exception {
        ImpexUtils.log(impexContext);

        // Default Spring service will do what we need
        SpringService ss = new DefaultSpringService();

        // Setup a Spring context that uses maven properties for placeholder resolution
        SpringContext context = MavenUtils.getMavenizedSpringContext(ss, getTestMavenProperties(initialPropertiesFile), TestMavenPropertySourceConfig.class);

        // Reset the db using annotated config
        context.setAnnotatedClasses(CollectionUtils.asList(SqlControllerConfig.class));

        // Execute Spring
        ss.load(context);




		List<ImpexContext> contexts = Collections.singletonList(impexContext);

		DatabaseContext database = impexService.getDatabaseObjectLists(impexContext);
		impexService.fillInMetaData(impexContext, database);
		impexService.serializeSchemas(contexts, database);
		// service.generateDataDtds(contexts);
		impexService.generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
		List<DumpTableResult> initialLoadResults = impexService.dumpTables(impexContext, database);

		byte[] initialBytes = getDataBytes(initialLoadResults);

		ImpexUtils.doStats(initialLoadResults);

        // reset the db with the generated mpx files
        // Default Spring service will do what we need
        SpringService ssMpx = new DefaultSpringService();

        // Setup a Spring context that uses maven properties for placeholder resolution
        SpringContext mpxContext = MavenUtils.getMavenizedSpringContext(ssMpx, getTestMavenProperties(mpxPropertiesFile), TestMavenPropertySourceConfig.class);

        // Reset the db using annotated config
        mpxContext.setAnnotatedClasses(CollectionUtils.asList(MpxSupplierConfig.class, SqlControllerConfig.class));

        // Execute Spring
        ssMpx.load(context);

		// dump the tables again to compare the results
		List<DumpTableResult> secondaryLoadResults = impexService.dumpTables(impexContext, database);

		byte[] secondaryBytes = getDataBytes(secondaryLoadResults);

		compareLoadResults(initialLoadResults, secondaryLoadResults);

		assertTrue("Data files are not byte-for-byte equal", Arrays.equals(initialBytes, secondaryBytes));
	}

}
