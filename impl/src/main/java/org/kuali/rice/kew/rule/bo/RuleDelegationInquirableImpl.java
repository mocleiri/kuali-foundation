/*
 * Copyright 2007-2009 The Kuali Foundation
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
package org.kuali.rice.kew.rule.bo;

import java.util.List;
import java.util.Map;

import org.kuali.rice.kew.rule.RuleDelegation;
import org.kuali.rice.kew.rule.web.WebRuleUtils;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.kns.web.ui.Section;

/**
 * This is a description of what this class does - ewestfal don't forget to fill this in.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class RuleDelegationInquirableImpl extends KualiInquirableImpl {

	@Override
	public Object getDataObject(Map fieldValues){
		RuleDelegation rule = (RuleDelegation)super.getDataObject(fieldValues);
		WebRuleUtils.populateRuleMaintenanceFields(rule.getDelegationRuleBaseValues());
		return rule;
    }
	
	/**
	 * This overridden method ...
	 *
	 * @see org.kuali.rice.kns.inquiry.Inquirable#getBusinessObject(java.util.Map)
	 */
	public BusinessObject getBusinessObject(Map fieldValues) {
		RuleDelegation rule = (RuleDelegation)super.getBusinessObject(fieldValues);
		WebRuleUtils.populateRuleMaintenanceFields(rule.getDelegationRuleBaseValues());
		return rule;
	}

	/**
	 * This overridden method ...
	 *
	 * @see org.kuali.rice.kns.inquiry.Inquirable#getSections(org.kuali.rice.kns.bo.BusinessObject)
	 */
	public List getSections(BusinessObject bo) {
		List<Section> sections = super.getSections(bo);
		
		return WebRuleUtils.customizeSections(((RuleDelegation)bo).getDelegationRuleBaseValues(), sections, true);
		
	}

}
