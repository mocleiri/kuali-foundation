/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krad.datadictionary.validation.constraint;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.uif.UifConstants;

/**
 * Validation pattern for matching floating point numbers, optionally matching negative numbers
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class FloatingPointPatternConstraint extends ConfigurationBasedRegexPatternConstraint {

    protected boolean allowNegative;

    /**
     * @see org.kuali.rice.krad.datadictionary.validation.constraint.ValidCharactersPatternConstraint#getRegexString()
     */
    @Override
    protected String getRegexString() {
        StringBuffer regex = new StringBuffer();

        if (isAllowNegative()) {
            regex.append("-?");
        }
        regex.append(super.getRegexString());

        return regex.toString();
    }

    /**
     * @return the allowNegative
     */
    public boolean isAllowNegative() {
        return this.allowNegative;
    }

    /**
     * @param allowNegative the allowNegative to set
     */
    public void setAllowNegative(boolean allowNegative) {
        this.allowNegative = allowNegative;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.krad.datadictionary.validation.constraint.ValidDataPatternConstraint#getValidationMessageParams()
     */
    @Override
    public List<String> getValidationMessageParams() {
        if (validationMessageParams == null) {
            validationMessageParams = new ArrayList<String>();
            ConfigurationService configService = KRADServiceLocator.getKualiConfigurationService();
            if (allowNegative) {
                validationMessageParams.add(configService
                        .getPropertyValueAsString(UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX
                                + "positiveOrNegative"));
            } else {
                validationMessageParams.add(configService
                        .getPropertyValueAsString(UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "positive"));
            }
        }
        return validationMessageParams;
    }
}
