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
import org.kuali.common.impex.KualiDatabaseFactoryBean;
import org.kuali.common.impex.MpxLocationSupplier;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.jdbc.supplier.LocationSuppliersFactoryBean;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LocationUtils;

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
    protected JdbcContext resetContext;

    @Resource
    protected JdbcContext initialContext;

    @Resource
    protected JdbcContext schemaContext;

    @Resource
    protected JdbcContext mpxContext;

    @Resource
    protected ImpexGeneratorService impexService;

    @Resource
    protected JdbcService jdbcService;

    @Resource
    protected MpxLocationSupplier prototypeMpxLocationSupplier;

    @Resource
    protected Properties kdfProperties;

    @Resource
    protected LocationSupplierSourceBean mpxLocationSourceBean;

    @Resource
    protected Map<String, LocationSupplierSourceBean> testExtensionMappings;

    @Resource
    protected Properties testLocationSuppliersFactoryProperties;

    protected final static String DATABASE_VENDOR_KEY = "databaseVendor";
    protected final static String LOCATION_KEY = "location";
    protected final static String MPX_LOCATION_SUPPLIER_PROPERTY = "locationKeys";

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
        Assert.assertEquals("Number of dump table results must be equal", initialLoadResults.size(), secondaryLoadResults.size());

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

    protected void doTest() throws Exception, IOException {
        ImpexUtils.log(impexContext);

        // clear db of data
        jdbcService.executeSql(resetContext);

        // load the db with data
        jdbcService.executeSql(initialContext);

        List<ImpexContext> contexts = Collections.singletonList(impexContext);

        DatabaseContext database = impexService.getDatabaseObjectLists(impexContext);
        impexService.fillInMetaData(impexContext, database);
        impexService.serializeSchemas(contexts, database);
        // service.generateDataDtds(contexts);
        impexService.generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
        List<DumpTableResult> initialLoadResults = impexService.dumpTables(impexContext, database);

        byte[] initialBytes = getDataBytes(initialLoadResults);

        ImpexUtils.doStats(initialLoadResults);

        // clear db of data
        jdbcService.executeSql(resetContext);

        // load only schema
        jdbcService.executeSql(schemaContext);

        // import the data from the generated mpx files
        // Have to create a kuali database factory here because the table meta data does not exist at startup,
        // it is created by the previous call to impexService.serializeSchemas(contexts, database)
        KualiDatabaseFactoryBean kdfBean = new KualiDatabaseFactoryBean();
        kdfBean.setDatabaseVendor(kdfProperties.getProperty(DATABASE_VENDOR_KEY));
        kdfBean.setLocation(kdfProperties.getProperty(LOCATION_KEY));

        prototypeMpxLocationSupplier.setDatabase(kdfBean.getObject());

        mpxLocationSourceBean.setSupplierInstance(prototypeMpxLocationSupplier);

        LocationSuppliersFactoryBean mpxLocationSupplier = new LocationSuppliersFactoryBean();
        mpxLocationSupplier.setExtensionMappings(testExtensionMappings);
        mpxLocationSupplier.setProperty(MPX_LOCATION_SUPPLIER_PROPERTY);
        mpxLocationSupplier.setProperties(testLocationSuppliersFactoryProperties);

        List<SqlSupplier> sqlSuppliers = new ArrayList<SqlSupplier>(mpxLocationSupplier.getObject());

        mpxContext.setSuppliers(sqlSuppliers);

        jdbcService.executeSql(mpxContext);

        // dump the tables again to compare the results
        List<DumpTableResult> secondaryLoadResults = impexService.dumpTables(impexContext, database);

        byte[] secondaryBytes = getDataBytes(secondaryLoadResults);

        compareLoadResults(initialLoadResults, secondaryLoadResults);

        assertTrue("Data files are not byte-for-byte equal", Arrays.equals(initialBytes, secondaryBytes));
    }

}
