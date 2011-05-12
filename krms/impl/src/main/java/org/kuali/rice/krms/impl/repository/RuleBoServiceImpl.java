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
package org.kuali.rice.krms.impl.repository;


import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.krms.api.repository.action.ActionDefinition;
import org.kuali.rice.krms.api.repository.proposition.PropositionDefinition;
import org.kuali.rice.krms.api.repository.rule.RuleAttribute;
import org.kuali.rice.krms.api.repository.rule.RuleDefinition;

import java.util.*;

public final class RuleBoServiceImpl implements RuleBoService {

	private BusinessObjectService businessObjectService;

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#createRule(org.kuali.rice.krms.api.repository.rule.RuleDefinition)
	 */
	@Override
	public void createRule(RuleDefinition rule) {
		if (rule == null){
			throw new IllegalArgumentException("rule is null");
		}
		final String nameKey = rule.getName();
		final String namespaceKey = rule.getNamespace();
		final RuleDefinition existing = getRuleByNameAndNamespace(nameKey, namespaceKey);
		if (existing != null){
			throw new IllegalStateException("the rule to create already exists: " + rule);			
		}	
		businessObjectService.save(RuleBo.from(rule));
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#updateRule(org.kuali.rice.krms.api.repository.rule.RuleDefinition)
	 */
	@Override
	public void updateRule(RuleDefinition rule) {
		if (rule == null){
			throw new IllegalArgumentException("rule is null");
		}
		final String ruleIdKey = rule.getId();
		final RuleDefinition existing = getRuleByRuleId(ruleIdKey);
		if (existing == null) {
			throw new IllegalStateException("the rule does not exist: " + rule);
		}
		final RuleDefinition toUpdate;
		if (!existing.getId().equals(rule.getId())){
			final RuleDefinition.Builder builder = RuleDefinition.Builder.create(rule);
			builder.setId(existing.getId());
			toUpdate = builder.build();
		} else {
			toUpdate = rule;
		}

		businessObjectService.save(RuleBo.from(toUpdate));
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#getRuleByRuleId(java.lang.String)
	 */
	@Override
	public RuleDefinition getRuleByRuleId(String ruleId) {
		if (StringUtils.isBlank(ruleId)){
			throw new IllegalArgumentException("rule id is null");
		}
		RuleBo bo = businessObjectService.findBySinglePrimaryKey(RuleBo.class, ruleId);
		return RuleBo.to(bo);
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#getRuleByRuleId(java.lang.String)
	 */
	@Override
	public RuleDefinition getRuleByNameAndNamespace(String name, String namespace) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name is blank");
        }
        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("namespace", namespace);

        RuleBo myRule = businessObjectService.findByPrimaryKey(RuleBo.class, Collections.unmodifiableMap(map));
		return RuleBo.to(myRule);
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#createRuleAttribute(org.kuali.rice.krms.api.repository.rule.RuleAttribute)
	 */
	@Override
	public void createRuleAttribute(RuleAttribute attribute) {
		if (attribute == null){
			throw new IllegalArgumentException("rule attribute is null");
		}
		final String attrIdKey = attribute.getId();
		final RuleAttribute existing = getRuleAttributeById(attrIdKey);
		if (existing != null){
			throw new IllegalStateException("the rule attribute to create already exists: " + attribute);			
		}

		businessObjectService.save(RuleAttributeBo.from(attribute));		
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#updateRuleAttribute(org.kuali.rice.krms.api.repository.rule.RuleAttribute)
	 */
	@Override
	public void updateRuleAttribute(RuleAttribute attribute) {
		if (attribute == null){
			throw new IllegalArgumentException("rule attribute is null");
		}
		final String attrIdKey = attribute.getId();
		final RuleAttribute existing = getRuleAttributeById(attrIdKey);
		if (existing == null) {
			throw new IllegalStateException("the rule attribute does not exist: " + attribute);
		}
		final RuleAttribute toUpdate;
		if (!existing.getId().equals(attribute.getRuleId())){
			final RuleAttribute.Builder builder = RuleAttribute.Builder.create(attribute);
			builder.setId(existing.getId());
			toUpdate = builder.build();
		} else {
			toUpdate = attribute;
		}

		businessObjectService.save(RuleAttributeBo.from(toUpdate));
	}

	/**
	 * This method ...
	 * 
	 * @see org.kuali.rice.krms.impl.repository.RuleBoService#getRuleAttributeById(java.lang.String)
	 */
	public RuleAttribute getRuleAttributeById(String attrId) {
		if (StringUtils.isBlank(attrId)){
			return null;			
		}
		RuleAttributeBo bo = businessObjectService.findBySinglePrimaryKey(RuleAttributeBo.class, attrId);
		return RuleAttributeBo.to(bo);
	}

	/**
	 * Sets the businessObjectService attribute value.
	 *
	 * @param businessObjectService The businessObjectService to set.
	 */
	public void setBusinessObjectService(final BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/**
	 * Converts a List<RuleBo> to an Unmodifiable List<Rule>
	 *
	 * @param RuleBos a mutable List<RuleBo> to made completely immutable.
	 * @return An unmodifiable List<Rule>
	 */
	public List<RuleDefinition> convertListOfBosToImmutables(final Collection<RuleBo> ruleBos) {
		ArrayList<RuleDefinition> rules = new ArrayList<RuleDefinition>();
		for (RuleBo bo : ruleBos) {
			RuleDefinition rule = RuleBo.to(bo);
			rules.add(rule);
		}
		return Collections.unmodifiableList(rules);
	}

}
