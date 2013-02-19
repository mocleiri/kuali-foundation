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
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.PropertyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
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

    @Test
    public void test() throws Exception {
        logger.info("Here's a test!");
        System.out.println("Here's a test SYSOUT!");

        Properties p = getImpexProperties("*", 5, 15);
        ImpexContext sourceContext = getImpexContext(p);
        log(sourceContext);
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

    protected DataSource getDataSource(Properties p) {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName(p.getProperty("impex.driver"));
        dmds.setPassword(p.getProperty("impex.password"));
        dmds.setUsername(p.getProperty("impex.username"));
        dmds.setUrl(p.getProperty("impex.url"));
        return dmds;
    }

    protected Properties getImpexProperties(String includes, int metaDataThreads, int dataThreads) throws Exception {
        String tableIncludes = includes;
        String viewIncludes = includes;
        String sequenceIncludes = includes;

        Properties p = new Properties();
        p.setProperty("project.basedir", mavenProperties.getProperty("project.basedir"));
        p.setProperty("project.build.directory", p.getProperty("project.basedir") + "/target");
        p.setProperty("project.artifactId", mavenProperties.getProperty("project.artifactId"));
        p.setProperty("impex.antCompatibilityMode", "false");
        p.setProperty("impex.table.includes", tableIncludes);
        p.setProperty("impex.view.includes", viewIncludes);
        p.setProperty("impex.sequence.includes", sequenceIncludes);
        p.setProperty("impex.url", dbProperties.getProperty("jdbc.url"));
        p.setProperty("impex.driver", dbProperties.getProperty("jdbc.driver"));
        p.setProperty("impex.username", dbProperties.getProperty("jdbc.username"));
        p.setProperty("impex.password", p.getProperty("impex.username"));
        p.setProperty("impex.schema", p.getProperty("impex.username"));
        p.setProperty("impex.databaseVendor", mavenProperties.getProperty("db.vendor"));
        p.setProperty("impex.metadata.threads", metaDataThreads + "");
        p.setProperty("impex.data.threads", dataThreads + "");
        p.setProperty("impex.workingDir", p.getProperty("project.build.directory") + "/impex");
        p.setProperty("impex.databaseTablePropertiesFile", p.getProperty("project.basedir") + "/src/main/resources/" + p.getProperty("project.artifactId") + ".properties");
        return p;
    }

    protected ImpexContext getImpexContext(Properties p) {
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
