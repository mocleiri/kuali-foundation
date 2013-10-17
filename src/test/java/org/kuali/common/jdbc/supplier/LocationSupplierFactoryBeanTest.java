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
package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:org/kuali/common/jdbc/location-supplier-test-context.xml" })
public class LocationSupplierFactoryBeanTest {

	private static final Logger logger = LoggerFactory.getLogger(LocationSupplierFactoryBeanTest.class);

    @Autowired
    LocationSuppliersFactoryBean factoryBean;

	@Test
	public void test() throws Exception {
        logger.info("Context loaded");

        List<LocationSupplier> suppliers = factoryBean.getObject();

        assertEquals(2, suppliers.size());

        Collection<String> expectedLocations = new ArrayList<String>(2);
        expectedLocations.add("classpath:org/kuali/common/jdbc/test1.sql");
        expectedLocations.add("classpath:org/kuali/common/jdbc/test2.sql");

        for(LocationSupplier supplier : suppliers) {
            expectedLocations.remove(supplier.getLocation());
        }

        assertTrue("All expected locations were not accounted for", expectedLocations.isEmpty());
	}
}
