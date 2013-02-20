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

package org.kuali.core.db.torque.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.jdbc.DatabaseResetExecutable;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * This class tests the impex process from a sample db
 *
 * @author andrewlubbers
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mpxTests/oracle-impex-context.xml"})
public class TestOracleImpexProcess {

    private static final Logger logger = LoggerFactory.getLogger(TestOracleImpexProcess.class);

    private static final String LF = "\n";

    @Resource
    private ImpexContext impexContext;

    @Resource(name = "deploy.databaseResetExecutable")
    private DatabaseResetExecutable resetExec;

    @Resource
    private Properties cleanDatabaseProperties;

    @Resource
    private JdbcService jdbcService;

    @Resource
    private ImpexService impexService;

    @Resource
    private ExecutionContext sqlExecutionContext;

    @Test
    public void test() throws Exception {
        logger.info("Starting database dump");

        log(impexContext);

        List<ImpexContext> contexts = Collections.singletonList(impexContext);

        DatabaseContext database = impexService.getDatabaseObjectLists(impexContext);
        impexService.fillInMetaData(impexContext, database);
        impexService.serializeSchemas(contexts, database);
        // service.generateDataDtds(contexts);
        impexService.generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
        List<DumpTableResult> initialLoadResults = impexService.dumpTables(impexContext, database);

        byte[] initialBytes = getDataBytes(initialLoadResults);

        ImpexUtils.doStats(initialLoadResults);

        // update the database reset context to clean the database of any data
        Collection<String> cleanPropNames = cleanDatabaseProperties.stringPropertyNames();
        for (String name : cleanPropNames) {
            resetExec.getContext().getProperties().setProperty(name, cleanDatabaseProperties.getProperty(name));
        }

        // clear db of data
        resetExec.execute();

        // import the data from the generated mpx files
        impexService.importData(impexContext, sqlExecutionContext);

        // dump the tables again to compare the results
        List<DumpTableResult> secondaryLoadResults = impexService.dumpTables(impexContext, database);

        byte[] secondaryBytes = getDataBytes(secondaryLoadResults);

        compareLoadResults(initialLoadResults, secondaryLoadResults);

        assertTrue("Data files are not byte-for-byte equal", Arrays.equals(initialBytes, secondaryBytes));
    }

    private byte[] getDataBytes(List<DumpTableResult> results) throws IOException {
        StringBuilder dataBuilder = new StringBuilder();
        for(DumpTableResult result : results) {
            List<File> sortedFiles = new ArrayList<File>(result.getFiles());
            Collections.sort(sortedFiles);
            for (File f : sortedFiles) {
                BufferedReader reader = LocationUtils.getBufferedReader(f.getAbsolutePath());
                String line = reader.readLine();
                while (line != null) {
                    dataBuilder.append(line).append(LF);
                    line = reader.readLine();
                }
            }
        }

        return dataBuilder.toString().getBytes();
    }

    protected void compareLoadResults(List<DumpTableResult> initialLoadResults, List<DumpTableResult> secondaryLoadResults) {
        assertEquals("Number of dump table results must be equal", initialLoadResults.size(), secondaryLoadResults.size());

        // map the results by table name
        Map<String, DumpTableResult> initialTableMap = createColumnMap(initialLoadResults);
        Map<String, DumpTableResult> secondaryTableMap = createColumnMap(secondaryLoadResults);

        // assert all tables are the same
        Set<String> initialTableNames = initialTableMap.keySet();
        Set<String> secondaryTableNames = secondaryTableMap.keySet();

        assertTrue("Table names are not the same in each result set", initialTableNames.containsAll(secondaryTableNames) && secondaryTableNames.containsAll(initialTableNames));

        for(String tableName : initialTableNames) {
            DumpTableResult initialResult = initialTableMap.get(tableName);
            DumpTableResult secondaryResult = secondaryTableMap.get(tableName);

            assertEquals("Number of rows not equal", initialResult.getRows(), secondaryResult.getRows());
            assertEquals("Number of created files not equal", initialResult.getFiles().size(), secondaryResult.getFiles().size());
        }
    }

    private Map<String, DumpTableResult> createColumnMap(List<DumpTableResult> results) {
        Map<String, DumpTableResult> colMap = new HashMap<String, DumpTableResult>(results.size());

        for(DumpTableResult result : results) {
            colMap.put(result.getTable().getName(), result);
        }

        return colMap;
    }

    protected void log(ImpexContext context) {
        logger.info("---------------------------------------------------------------");
        logger.info("Impex Database Dump");
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

}
