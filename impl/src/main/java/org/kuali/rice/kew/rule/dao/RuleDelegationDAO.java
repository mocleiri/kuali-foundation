/*
 * Copyright 2005-2007 The Kuali Foundation
 *
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
package org.kuali.rice.kew.rule.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.kew.rule.RuleDelegation;


public interface RuleDelegationDAO {

    public List findByDelegateRuleId(Long ruleId);
    public void save(RuleDelegation ruleDelegation);
    public List findAllCurrentRuleDelegations();
    public void delete(Long ruleDelegationId);
    public RuleDelegation findByRuleDelegationId(Long ruleDelegationId);
    public List<RuleDelegation> findByResponsibilityIdWithCurrentRule(Long responsibiliytId);
    public List<RuleDelegation> search(String parentRuleBaseVaueId, String parentResponsibilityId, String docTypeName, Long ruleId, Long ruleTemplateId, String ruleDescription, String workgroupId, String workflowId, String delegationType, Boolean activeInd, Map extensionValues, String workflowIdDirective);
    public List<RuleDelegation> search(String parentRuleBaseVaueId, String parentResponsibilityId, String docTypeName, Long ruleTemplateId, String ruleDescription, Collection<String> workgroupIds, String workflowId, String delegationType, Boolean activeInd, Map extensionValues, Collection actionRequestCodes);

}
