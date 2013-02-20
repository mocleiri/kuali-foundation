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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.service.DefaultImpexService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * This class tests the impex process from a sample db
 *
 * @author andrewlubbers
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mpxTests/build-db-context.xml"})
public class TestImpexProcess {

    private static final Logger logger = LoggerFactory.getLogger(TestImpexProcess.class);

    @Resource(name = "deploy.mavenProperties")
    private Properties mavenProperties;

    @Resource(name = "deploy.properties")
    private Properties dbProperties;

    @Resource
    private ImpexContext impexContext;

    @Resource(name = "deploy.databaseResetContext")
    private DatabaseResetContext resetContext;

    @Test
    public void test() throws Exception {
        logger.info("Starting database dump");

        log(impexContext);

        List<ImpexContext> contexts = Collections.singletonList(impexContext);

        ImpexService service = new DefaultImpexService();
        DatabaseContext database = service.getDatabaseObjectLists(impexContext);
        service.fillInMetaData(impexContext, database);
        service.serializeSchemas(contexts, database);
        // service.generateDataDtds(contexts);
        service.generateSchemaSql(contexts, Arrays.asList("oracle", "mysql"));
        List<DumpTableResult> results = service.dumpTables(impexContext, database);

        ImpexUtils.doStats(results);
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
