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

package org.kuali.rice.kns.web.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kns.question.Question;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.WebUtils;

/**
 * This class is the action form for all Question Prompts.
 * 
 * 
 */
public class QuestionPromptForm extends KualiForm {
    private static final long serialVersionUID = 1L;
    private ArrayList buttons;
    private String caller;

    private String formKey;
    private String questionIndex;
    private String questionText;
    private String questionType;
    private String title;
    private String context;
    private String reason;
    private String showReasonField;
    private String questionAnchor;
    private String methodToCallPath;
    private String docNum;

    /**
	 * @return the docNum
	 */
	public String getDocNum() {
		return this.docNum;
	}

	/**
	 * @param docNum the docNum to set
	 */
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	/**
     * @return boolean
     */
    public String getShowReasonField() {
        return showReasonField;
    }

    /**
     * @param showReasonField
     */
    public void setShowReasonField(String showReasonField) {
        this.showReasonField = showReasonField;
    }

    /**
     * @return Returns the buttons.
     */
    public ArrayList getButtons() {
        return buttons;
    }

    /**
     * @return Returns the caller.
     */
    public String getCaller() {
        return caller;
    }

    /**
     * @return Returns the formKey.
     */
    public String getFormKey() {
        return formKey;
    }

    /**
     * @return Returns the questionIndex.
     */
    public String getQuestionIndex() {
        return questionIndex;
    }

    /**
     * @return Returns the questionText.
     */
    public String getQuestionText() {
        return questionText != null ? WebUtils.filterHtmlAndReplaceRiceMarkup(questionText) : questionText;
    }

    /**
     * @return Returns the questionName.
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @see org.kuali.rice.kns.web.struts.pojo.PojoForm#populate(javax.servlet.http.HttpServletRequest)
     */
    public void populate(HttpServletRequest request) {
        super.populate(request);

        // set the title of the jsp, this should come from a resource bundle
        title = KNSConstants.QUESTION_PAGE_TITLE;

        if (request.getAttribute(KNSConstants.DOC_FORM_KEY) != null) {
            this.setFormKey((String) request.getAttribute(KNSConstants.DOC_FORM_KEY));
        }
        else if (request.getParameter(KNSConstants.DOC_FORM_KEY) != null) {
            this.setFormKey(request.getParameter(KNSConstants.DOC_FORM_KEY));
        }
        
        if (request.getAttribute(KNSConstants.DOC_NUM) != null) {
            this.setFormKey((String) request.getAttribute(KNSConstants.DOC_NUM));
        }
        

        if (request.getParameter(KNSConstants.RETURN_LOCATION_PARAMETER) != null) {
            this.setBackLocation(request.getParameter(KNSConstants.RETURN_LOCATION_PARAMETER));
        }

        if (getMethodToCall().equals(KNSConstants.START_METHOD)) { // don't do this for the processAnswer action otherwise it blows up
            Question kualiQuestion = KNSServiceLocatorWeb.getQuestion(questionType);
            if (kualiQuestion == null) {
                throw new RuntimeException("question implementation not found: " + request.getParameter(KNSConstants.QUESTION_IMPL_ATTRIBUTE_NAME));
            }


            // some questions types default these so we should default if not
            // present in request
            if (questionText == null) {
                questionText = kualiQuestion.getQuestion();
            }

            if (buttons == null) {
                buttons = kualiQuestion.getButtons();
            }
        }
    }

    /**
     * @param buttons The buttons to set.
     */
    public void setButtons(ArrayList buttons) {
        this.buttons = buttons;
    }

    /**
     * @param caller The caller to set.
     */
    public void setCaller(String caller) {
        this.caller = caller;
    }

    /**
     * @param formKey The formKey to set.
     */
    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    /**
     * @param questionIndex The questionIndex to set.
     */
    public void setQuestionIndex(String questionIndex) {
        this.questionIndex = questionIndex;
    }

    /**
     * @param questionText The questionText to set.
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * @param questionName The questionName to set.
     */
    public void setQuestionType(String questionName) {
        this.questionType = questionName;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return String
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getQuestionAnchor() {
        return questionAnchor;
    }

    public void setQuestionAnchor(String questionAnchor) {
        this.questionAnchor = questionAnchor;
    }

    public String getMethodToCallPath() {
        return methodToCallPath;
}
    public void setMethodToCallPath(String methodToCallPath) {
        this.methodToCallPath = methodToCallPath;
    }
    
}
