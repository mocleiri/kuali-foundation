/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.rice.krms.test;

import org.kuali.rice.core.api.lifecycle.Lifecycle;
import org.kuali.rice.core.impl.resourceloader.SpringResourceLoader;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.test.BaselineTestCase;
import org.kuali.rice.test.BaselineTestCase.BaselineMode;
import org.kuali.rice.test.BaselineTestCase.Mode;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * This is test base that should be used for all KRMS integration tests.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@BaselineMode(Mode.ROLLBACK_CLEAR_DB)
public abstract class KRMSTestCase extends BaselineTestCase {

	private static final String KRMS_MODULE_NAME = "krms";
	
	private SpringResourceLoader krmsTestResourceLoader;
	
	public KRMSTestCase() {
		super(KRMS_MODULE_NAME);
	}
	
	@Override
	protected List<Lifecycle> getSuiteLifecycles() {
		List<Lifecycle> suiteLifecycles = super.getSuiteLifecycles();
//		suiteLifecycles.add(new KEWXmlDataLoaderLifecycle("classpath:org/kuali/rice/kim/test/DefaultSuiteTestData.xml"));
		return suiteLifecycles;
	}
	
	@Override
	protected void loadSuiteTestData() throws Exception {
		super.loadSuiteTestData();
//		new SQLDataLoader("classpath:org/kuali/rice/kim/test/DefaultSuiteTestData.sql", "/").runSql();
//		new SQLDataLoader("classpath:org/kuali/rice/kim/test/CircularRolesTestData.sql", "/").runSql();
//		new SQLDataLoader("classpath:org/kuali/rice/kim/test/CircularGroupsTestData.sql", "/").runSql();
	}
	
	
	
	@Override
	protected Lifecycle getLoadApplicationLifecycle() {
	    if (krmsTestResourceLoader == null) {
	        krmsTestResourceLoader = new SpringResourceLoader(new QName("KRMSTestHarnessApplicationResourceLoader"), "classpath:KRMSTestHarnessSpringBeans.xml", null);
	        krmsTestResourceLoader.setParentSpringResourceLoader(getTestHarnessSpringResourceLoader());
	        getTestHarnessSpringResourceLoader().addResourceLoader(krmsTestResourceLoader);
	    }
    	return krmsTestResourceLoader;
	}
	
//	/**
//	 * Override the standard per-test lifecycles to prepend ClearDatabaseLifecycle and ClearCacheLifecycle
//	 * @see org.kuali.rice.test.RiceTestCase#getPerTestLifecycles()
//	 */
//	@Override
//	protected List<Lifecycle> getPerTestLifecycles() {
//		List<Lifecycle> lifecycles = super.getPerTestLifecycles();
//		lifecycles.add(new ClearCacheLifecycle());
//		return lifecycles;
//	}
//	
//	public class ClearCacheLifecycle extends BaseLifecycle {
//		@Override
//		public void stop() throws Exception {
//			KimApiServiceLocator.getIdentityManagementService().flushAllCaches();
//			KimApiServiceLocator.getRoleService().flushRoleCaches();
//			super.stop();
//		}
//
//	}
	
	protected List<String> getPerTestTablesNotToClear() {
		List<String> tablesNotToClear = new ArrayList<String>();
		tablesNotToClear.add("KRIM_.*");
		tablesNotToClear.add("KRNS_.*");
		tablesNotToClear.add("KREW_.*");
		tablesNotToClear.add("KREN_.*");
		return tablesNotToClear;
	}


	/**
     * @see org.kuali.rice.test.RiceTestCase#getModuleName()
     */
	@Override
	protected String getModuleName() {
		return KRMS_MODULE_NAME;
	}
	
//	protected KimTypeInfo getDefaultKimType() {
//		KimTypeInfo type = KIMServiceLocatorWeb.getTypeInfoService().getKimType("1");
//		if (type == null) {
//			fail("Failed to locate the default Kim Type.");
//		}
//		return type;
//	}
//	
//	protected KimPermissionTemplateImpl getDefaultPermissionTemplate() {
//		Map<String, Object> fieldValues = new HashMap<String, Object>();
//		fieldValues.put("namespaceCode", "KUALI");
//		fieldValues.put("name", "Default");
//		KimPermissionTemplateImpl template = (KimPermissionTemplateImpl) KRADServiceLocator.getBusinessObjectService().findByPrimaryKey(KimPermissionTemplateImpl.class, fieldValues);
//		if (template == null) {
//			fail("Failed to locate the default Permission Template.");
//		}
//		return template;
//	}
//	
//	protected String getNewRoleId() {
//		return getIdFromSequence("KRIM_ROLE_ID_S");
//	}
//	
//	protected String getNewRoleMemberId() {
//		return getIdFromSequence("KRIM_ROLE_MBR_ID_S");
//	}
//	
//	protected String getNewRolePermissionId() {
//		return getIdFromSequence("KRIM_ROLE_ID_S");
//	}
	
	protected String getIdFromSequence(String sequenceName) {
		Long sequenceId = KRADServiceLocator.getSequenceAccessorService().getNextAvailableSequenceNumber(sequenceName);
		return "" + sequenceId;
    }
}
