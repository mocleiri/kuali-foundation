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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.jdbc.DatabaseResetExecutable;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
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
 * This class tests the impex process from a sample db
 *
 * @author andrewlubbers
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mpxTests/oracle-impex-context.xml"})
public class TestOracleImpexProcess extends AbstractTestImpexProcess {

    private static final Logger logger = LoggerFactory.getLogger(TestOracleImpexProcess.class);

    @Resource
    protected ImpexContext impexContext;

    @Resource
    protected DatabaseResetExecutable resetExec;

    @Resource
    protected Properties cleanDatabaseProperties;

    @Resource
    protected JdbcService jdbcService;

    @Resource
    protected ImpexExecutorService impexExecutorService;

    @Resource
    protected ImpexGeneratorService impexService;

    @Resource
    protected ExecutionContext sqlExecutionContext;

    @Test
    public void test() throws Exception {
        logger.info("Starting database dump");

        doTest();
    }

    public Properties getCleanDatabaseProperties() {
        return cleanDatabaseProperties;
    }

    public void setCleanDatabaseProperties(Properties cleanDatabaseProperties) {
        this.cleanDatabaseProperties = cleanDatabaseProperties;
    }

    public ImpexContext getImpexContext() {
        return impexContext;
    }

    public void setImpexContext(ImpexContext impexContext) {
        this.impexContext = impexContext;
    }

    public ImpexExecutorService getImpexExecutorService() {
        return impexExecutorService;
    }

    public void setImpexExecutorService(ImpexExecutorService impexExecutorService) {
        this.impexExecutorService = impexExecutorService;
    }

    public ImpexGeneratorService getImpexService() {
        return impexService;
    }

    public void setImpexService(ImpexGeneratorService impexService) {
        this.impexService = impexService;
    }

    public JdbcService getJdbcService() {
        return jdbcService;
    }

    public void setJdbcService(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

    public DatabaseResetExecutable getResetExec() {
        return resetExec;
    }

    public void setResetExec(DatabaseResetExecutable resetExec) {
        this.resetExec = resetExec;
    }

    public ExecutionContext getSqlExecutionContext() {
        return sqlExecutionContext;
    }

    public void setSqlExecutionContext(ExecutionContext sqlExecutionContext) {
        this.sqlExecutionContext = sqlExecutionContext;
    }
}
