/*
 * Copyright 2007-2008 The Kuali Foundation
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
package org.kuali.rice.kns.web.taglib.html;

import org.apache.struts.taglib.html.HiddenTag;

import javax.servlet.jsp.JspException;

/**
 * This is a description of what this class does - bhargavp don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class KNSHiddenTag extends HiddenTag {

	protected boolean renderHiddenField = true;
	
	@Override
    public int doStartTag() throws JspException {
		int returnVal = SKIP_BODY;
		if(renderHiddenField)
			returnVal = super.doStartTag();
		return returnVal;
	}

	/**
	 * @return the renderHiddenField
	 */
	public boolean isRenderHiddenField() {
		return this.renderHiddenField;
	}

	/**
	 * @param renderHiddenField the renderHiddenField to set
	 */
	public void setRenderHiddenField(boolean renderHiddenField) {
		this.renderHiddenField = renderHiddenField;
	}
}
