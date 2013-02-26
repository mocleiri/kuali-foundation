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

import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.jdbc.DatabaseResetExecutable;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LocationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author andrewlubbers
 */
public abstract class AbstractTestImpexProcess {

    protected static final String LF = "\n";

    protected byte[] getDataBytes(List<DumpTableResult> results) throws IOException {
        StringBuilder dataBuilder = new StringBuilder();
        List<File> allFiles = new ArrayList<File>();
        for(DumpTableResult result : results) {
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

    protected Map<String, DumpTableResult> createColumnMap(List<DumpTableResult> results) {
        Map<String, DumpTableResult> colMap = new HashMap<String, DumpTableResult>(results.size());

        for(DumpTableResult result : results) {
            colMap.put(result.getTable().getName(), result);
        }

        return colMap;
    }

    protected void doTest() throws SQLException, IOException {
        ImpexUtils.log(getImpexContext());

        // clear db of data
        getResetExec().execute();

        List<ImpexContext> contexts = Collections.singletonList(getImpexContext());

        DatabaseContext database = getImpexService().getDatabaseObjectLists(getImpexContext());
        getImpexService().fillInMetaData(getImpexContext(), database);
        getImpexService().serializeSchemas(contexts, database);
        // service.generateDataDtds(contexts);
        getImpexService().generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
        List<DumpTableResult> initialLoadResults = getImpexService().dumpTables(getImpexContext(), database);

        byte[] initialBytes = getDataBytes(initialLoadResults);

        ImpexUtils.doStats(initialLoadResults);

        // update the database reset context to clean the database of any data
        Collection<String> cleanPropNames = getCleanDatabaseProperties().stringPropertyNames();
        for (String name : cleanPropNames) {
            getResetExec().getContext().getProperties().setProperty(name, getCleanDatabaseProperties().getProperty(name));
        }

        // clear db of data
        getResetExec().execute();

        // import the data from the generated mpx files
        getImpexExecutorService().importData(getImpexContext(), getSqlExecutionContext());

        // dump the tables again to compare the results
        List<DumpTableResult> secondaryLoadResults = getImpexService().dumpTables(getImpexContext(), database);

        byte[] secondaryBytes = getDataBytes(secondaryLoadResults);

        compareLoadResults(initialLoadResults, secondaryLoadResults);

        assertTrue("Data files are not byte-for-byte equal", Arrays.equals(initialBytes, secondaryBytes));
    }

    public abstract Properties getCleanDatabaseProperties();

    public abstract ImpexContext getImpexContext();

    public abstract ImpexExecutorService getImpexExecutorService();

    public abstract ImpexGeneratorService getImpexService();

    public abstract DatabaseResetExecutable getResetExec();

    public abstract ExecutionContext getSqlExecutionContext();
}
