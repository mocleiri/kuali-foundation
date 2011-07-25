/*
 * Copyright 2004 Jonathan M. Lehr
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * MODIFIED BY THE KUALI FOUNDATION
 */
// begin Kuali Foundation modification
package org.kuali.rice.kns.web.format;
// end Kuali Foundation modification

import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.kew.api.action.DelegationType;
import org.kuali.rice.kew.util.KEWConstants;

/**
 * begin Kuali Foundation modification
 * This class is used to format boolean values.
 * end Kuali Foundation modification
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class DelegationTypeFormatter extends Formatter {
    private static final long serialVersionUID = -4109390572922205211L;
	/*
    protected Object convertToObject(String target) {
        if (Formatter.isEmptyValue(target))
            return null;

        String stringValue = target.getClass().isArray() ? unwrapString(target) : (String) target;
        stringValue = stringValue.trim().toUpperCase();

        return KEWConstants.DELEGATION_TYPES.get(stringValue);
        if (TRUE_VALUES.contains(stringValue))
            return Boolean.TRUE;
        if (FALSE_VALUES.contains(stringValue))
            return Boolean.FALSE;

		// begin Kuali Foundation modification
		// was: throw new FormatException(CONVERT_MSG + stringValue);
        throw new FormatException("converting", RiceKeyConstants.ERROR_BOOLEAN, stringValue);
        // end Kuali Foundation modification
    }
*/
    public Object format(Object target) {
        if (target == null) {
            return null;
        }
        // begin Kuali Foundation modification
        if (target instanceof String) {
        	DelegationType delegationType = DelegationType.fromCode(((String)target).trim().toUpperCase());
        	if (delegationType != null) {
        		return delegationType.getLabel();
        	} else {
        		return KEWConstants.DELEGATION_BOTH_LABEL;
        	}
        } else {
            return "";
        }
    }
}
