/*
 * Copyright 2005-2008 The Kuali Foundation
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
package org.kuali.rice.krad.datadictionary.validation.constraint;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.uif.UifConstants;


/**
 * Pattern for matching any printable character
 * 
 * 
 */
public class AnyCharacterPatternConstraint extends ValidCharactersPatternConstraint {
    protected boolean allowWhitespace = false;


    /**
     * @return allowWhitespace
     */
    public boolean getAllowWhitespace() {
        return allowWhitespace;
    }

    /**
     * @param allowWhitespace
     */
    public void setAllowWhitespace(boolean allowWhitespace) {
        this.allowWhitespace = allowWhitespace;
    }


    /**
     * @see org.kuali.rice.krad.datadictionary.validation.ValidationPattern#getRegexString()
     */
    protected String getRegexString() {
        StringBuffer regexString = new StringBuffer("[");


        regexString.append("\\x21-\\x7E");
        if (allowWhitespace) {
            regexString.append("\\t\\r\\n\\v\\f\\s");
        }
        regexString.append("]");

        return regexString.toString();
    }

	/**
	 * 
	 * @see org.kuali.rice.krad.datadictionary.validation.constraint.BaseConstraint#getLabelKey()
	 */
	@Override
	public String getLabelKey() {
		String labelKey = super.getLabelKey();
		if (StringUtils.isNotEmpty(labelKey)) {
			return labelKey;
		}
		if (getAllowWhitespace()) {
			return UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "noWhitespace";
		}
		else{
		    return UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "anyCharacterPattern";
		}
	}

//	@Override
//	protected String getValidationErrorMessageKeyOptions() {
//		if (getAllowWhitespace()) {
//			return ".allowWhitespace";
//		}
//		return KRADConstants.EMPTY_STRING;
//	}

}
