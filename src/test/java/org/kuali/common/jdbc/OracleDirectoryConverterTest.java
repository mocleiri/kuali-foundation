/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc;

import org.junit.Test;
import org.kuali.common.jdbc.convert.DirectoryContext;
import org.kuali.common.jdbc.convert.DirectoryConverter;
import org.kuali.common.jdbc.convert.ImpexOracleInsertMerge;
import org.kuali.common.jdbc.convert.ReplaceWithConvertedProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Tests the oracle insert merge converter
 *
 * @author andrewlubbers
 */
public class OracleDirectoryConverterTest {

    private static final Logger logger = LoggerFactory.getLogger(OracleDirectoryConverterTest.class);

    @Test
    public void testConvert() {
        try {
            logger.info("");
            String dirname = "src/test/resources/converter/insertMerge/oracle";
            File directory = new File(dirname);

            String include = "*.sql";
            String exclude = "ks-rice-db*.sql";
            DirectoryContext context = new DirectoryContext();
            context.setConverter(new ImpexOracleInsertMerge());
            context.setDirectory(directory);
            context.setInclude(include);
            context.setExclude(exclude);
            context.setPostProcessor(new ReplaceWithConvertedProcessor());
            DirectoryConverter dc = new DirectoryConverter();
            dc.convert(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
