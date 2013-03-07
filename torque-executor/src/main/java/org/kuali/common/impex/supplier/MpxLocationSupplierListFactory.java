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

package org.kuali.common.impex.supplier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.ImportContext;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.util.LocationUtils;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * This class builds a list of MpxLocationSupplier objects from an ImportContext
 *
 * @author andrewlubbers
 */
public class MpxLocationSupplierListFactory implements FactoryBean<List<MpxLocationSupplier>> {

    private static final Logger logger = LoggerFactory.getLogger(MpxLocationSupplierListFactory.class);

    final ImportContext context;

    List<MpxLocationSupplier> mpxLocationSuppliers;

    public MpxLocationSupplierListFactory(ImportContext contextArg) throws IOException {
        context = contextArg;

        initialize();
    }

    protected void initialize() throws IOException {
        Platform platform = PlatformFactory.getPlatformFor(context.getDatabaseVendor());

        // extract all mpx locations from the list of location resources
        List<String> mpxLocations = LocationUtils.getLocations(context.getDataLocations());

        Map<String, Table> locationTableMap = getLocationTableMap(context.getDatabaseVendor(), context.getSchemaXmlLocation(), mpxLocations);
        mpxLocationSuppliers = new ArrayList<MpxLocationSupplier>(mpxLocations.size());

        // build a map of locations to tables
        for(String location : mpxLocations) {
            Table table = locationTableMap.get(location);

            SqlProducer sqlProducer = platform.getSqlProducer();
            sqlProducer.setBatchDataSizeLimit(context.getBatchDataSize());
            sqlProducer.setBatchRowCountLimit(context.getBatchRowCount());

            MpxLocationSupplier supplier = new MpxLocationSupplier();
            supplier.setEncoding(context.getEncoding());
            supplier.setLocation(location);
            supplier.setSqlProducer(sqlProducer);
            supplier.setTable(table);

            mpxLocationSuppliers.add(supplier);
        }

    }

    @SuppressWarnings("unchecked")
    protected Map<String, Table> getLocationTableMap(String databaseVendor, String tablesXmlLocation, List<String> locations) {

        Map<String, Table> results = new HashMap<String, Table>(locations.size());

        Database database;

        try {
            // Get an xml parser for schema.xml
            KualiXmlToAppData xmlParser = new KualiXmlToAppData(databaseVendor, "");

            // Parse schema.xml into a database object
            database = xmlParser.parseResource(tablesXmlLocation);
        } catch (Exception e) {
            logger.info("Execption thrown when processing xml location: " + tablesXmlLocation);
            throw new IllegalStateException(e);
        }

        // Populate map with locations as keys and database table objects as values
        for(String location : locations) {
            String filename = LocationUtils.getFilename(location);
            String tableName = StringUtils.substring(filename, 0, StringUtils.indexOf(filename, "."));
            results.put(location, database.getTable(tableName));
        }

        return results;
    }

    @Override
    public List<MpxLocationSupplier> getObject() throws Exception {
        return mpxLocationSuppliers;
    }

    @Override
    public Class<?> getObjectType() {
        return List.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
