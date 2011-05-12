/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.kew.rule;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.exception.WorkflowRuntimeException;
import org.kuali.rice.kew.service.KEWServiceLocator;


/**
 * A rule expression implementation that uses Bean Scripting Framework.
 * The language is given by the type qualifier, e.g.:
 * &lt;expression type="BSF:groovy"&gt;...
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class BSFRuleExpression implements RuleExpression {
    static {
        BSFManager.registerScriptingEngine(
                "groovy", 
                "org.codehaus.groovy.bsf.GroovyEngine", 
                new String[] { "groovy", "gy" }
        );
    }
    public RuleExpressionResult evaluate(Rule rule, RouteContext context) throws WorkflowException {
        RuleBaseValues ruleDefinition = rule.getDefinition();
        String type = ruleDefinition.getRuleExpressionDef().getType();
        String lang = parseLang(type, "groovy");
        String expression = ruleDefinition.getRuleExpressionDef().getExpression();
        RuleExpressionResult result;
        BSFManager manager = new BSFManager();
        try {
            declareBeans(manager, rule, context);
            result = (RuleExpressionResult) manager.eval(lang, null, 0, 0, expression);
        } catch (BSFException e) {
            throw new WorkflowException("Error evaluating " + type + " expression: '" + expression + "'", e);
        }
        if (result == null) {
            return new RuleExpressionResult(rule, false);
        } else {
            return result;
        }
    }

    /**
     * Parses the language component from the type string
     * @param type the type string
     * @param deflt the default language if none is present in the type string
     * @return the language component or null
     */
    protected String parseLang(String type, String deflt) {
        int colon = type.indexOf(':');
        if (colon > -1) {
            return type.substring(colon + 1);
        } else {
            return deflt;
        }
    }

    /**
     * Populates the BSFManager with beans that are accessible to BSF scripts.  May be overridden by
     * subclasses.  The standard implementation exposes the rule and routeContext
     * @param manager the BSFManager
     * @param rule the current Rule object
     * @param context the current RouteContext
     */
    protected void declareBeans(BSFManager manager, Rule rule, RouteContext context) throws BSFException {
        manager.declareBean("rule", rule, Rule.class);
        manager.declareBean("routeContext", context, RouteContext.class);
        manager.declareBean("workflow", new WorkflowRuleAPI(context), WorkflowRuleAPI.class);
    }

    /**
     * A helper bean that is declared for use by BSF scripts.
     * This functionality should really be part of a single internal API that can be exposed
     * to various pieces of code that are plugged into KEW.  For comparison EDocLite also
     * has its own such API that it exposes. 
     */
    protected static final class WorkflowRuleAPI {
        private final RouteContext context;
        WorkflowRuleAPI(RouteContext context) {
            this.context = context;
        }
        /**
         * Evaluates a named rule
         * @param name the rule name
         * @return the RuleExpressionResult
         * @throws WorkflowException 
         */
        public RuleExpressionResult invokeRule(String name) throws WorkflowException {
            RuleBaseValues rbv = KEWServiceLocator.getRuleService().getRuleByName(name);
            if (rbv == null) throw new WorkflowRuntimeException("Could not find rule named \"" + name + "\"");
            Rule r = new RuleImpl(rbv);
            return r.evaluate(r, context);
        }
    }
}
