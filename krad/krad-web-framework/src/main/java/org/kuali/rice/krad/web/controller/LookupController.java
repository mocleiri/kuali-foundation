/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.rice.krad.web.controller;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.lookup.Lookupable;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.container.LookupView;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.form.LookupForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Properties;

/**
 * Controller that handles requests coming from a <code>LookupView</code>
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@Controller
@RequestMapping(value = "/lookup")
public class LookupController extends UifControllerBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LookupController.class);

    /**
     * @see UifControllerBase#createInitialForm(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected LookupForm createInitialForm(HttpServletRequest request) {
        return new LookupForm();
    }

    protected void supressActionsIfNeeded(LookupForm lookupForm) {
        try {
            Class<?> dataObjectClass = Class.forName(lookupForm.getDataObjectClassName());
            Person user = GlobalVariables.getUserSession().getPerson();
            // check if creating documents is allowed
            String documentTypeName = KRADServiceLocatorWeb.getDocumentDictionaryService()
                    .getMaintenanceDocumentTypeName(dataObjectClass);
            if ((documentTypeName != null) &&
                    !KRADServiceLocatorWeb.getDocumentHelperService().getDocumentAuthorizer(documentTypeName)
                            .canInitiate(documentTypeName, user)) {
                ((LookupView) lookupForm.getView()).setSuppressActions(true);
            }
        } catch (ClassNotFoundException e) {
            LOG.warn("Unable to load Data Object Class: " + lookupForm.getDataObjectClassName(), e);
        }
    }

    /**
     * @see UifControllerBase#checkAuthorization(org.kuali.rice.krad.web.form.UifFormBase, java.lang.String)
     */
    @Override
    public void checkAuthorization(UifFormBase form, String methodToCall) throws AuthorizationException {
        if (!(form instanceof LookupForm)) {
            super.checkAuthorization(form, methodToCall);
        } else {
            LookupForm lookupForm = (LookupForm) form;
            try {
                Class<?> dataObjectClass = Class.forName(lookupForm.getDataObjectClassName());
                Person user = GlobalVariables.getUserSession().getPerson();
                // check if user is allowed to lookup object
                if (!KimApiServiceLocator.getPermissionService()
                        .isAuthorizedByTemplateName(user.getPrincipalId(), KRADConstants.KRAD_NAMESPACE,
                                KimConstants.PermissionTemplateNames.LOOK_UP_RECORDS,
                                KRADUtils.getNamespaceAndComponentSimpleName(dataObjectClass),
                                null)) {
                    throw new AuthorizationException(user.getPrincipalName(),
                            KimConstants.PermissionTemplateNames.LOOK_UP_RECORDS, dataObjectClass.getSimpleName());
                }
            } catch (ClassNotFoundException e) {
                LOG.warn("Unable to load Data Object Class class: " + lookupForm.getDataObjectClassName(), e);
                super.checkAuthorization(lookupForm, methodToCall);
            }
        }
    }

    @RequestMapping(params = "methodToCall=start")
    @Override
    public ModelAndView start(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        LookupForm lookupForm = (LookupForm) form;
//		checkAuthorization(lookupForm, request.getParameter("methodToCall"));
        supressActionsIfNeeded(lookupForm);

        return super.start(lookupForm, result, request, response);
    }

    /**
     * Just returns as if return with no value was selected.
     */
    @Override
    @RequestMapping(params = "methodToCall=cancel")
    public ModelAndView cancel(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        LookupForm lookupForm = (LookupForm) form;
        supressActionsIfNeeded(lookupForm);

        Properties props = new Properties();
        props.put(UifParameters.METHOD_TO_CALL, UifConstants.MethodToCallNames.REFRESH);
        if (StringUtils.isNotBlank(lookupForm.getReturnFormKey())) {
            props.put(UifParameters.FORM_KEY, lookupForm.getReturnFormKey());
        }
        if (StringUtils.isNotBlank(lookupForm.getDocNum())) {
            props.put(UifParameters.DOC_NUM, lookupForm.getDocNum());
        }

        return performRedirect(lookupForm, lookupForm.getReturnLocation(), props);
    }

    /**
     * clearValues - clears the values of all the fields on the jsp.
     */
    @RequestMapping(params = "methodToCall=clearValues")
    public ModelAndView clearValues(@ModelAttribute("KualiForm") LookupForm lookupForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        supressActionsIfNeeded(lookupForm);

        Lookupable lookupable = (Lookupable) lookupForm.getLookupable();
        lookupForm.setCriteriaFields(lookupable.performClear(lookupForm, lookupForm.getCriteriaFields()));

        return getUIFModelAndView(lookupForm);
    }

    /**
     * search - sets the values of the data entered on the form on the jsp into a map and then searches for the
     * results.
     */
    @RequestMapping(params = "methodToCall=search")
    public ModelAndView search(@ModelAttribute("KualiForm") LookupForm lookupForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        supressActionsIfNeeded(lookupForm);
        GlobalVariables.getUserSession().removeObjectsByPrefix(KRADConstants.SEARCH_METHOD);

        Lookupable lookupable = lookupForm.getLookupable();
        if (lookupable == null) {
            LOG.error("Lookupable is null.");
            throw new RuntimeException("Lookupable is null.");
        }

        // validate search parameters
        lookupable.validateSearchParameters(lookupForm, lookupForm.getCriteriaFields());

        Collection<?> displayList =
                lookupable.performSearch(lookupForm, lookupForm.getCriteriaFields(), true);

        if (displayList instanceof CollectionIncomplete<?>) {
            request.setAttribute("reqSearchResultsActualSize",
                    ((CollectionIncomplete<?>) displayList).getActualSizeIfTruncated());
        } else {
            request.setAttribute("reqSearchResultsActualSize", new Integer(displayList.size()));
        }

        lookupForm.setSearchResults(displayList);

        return getUIFModelAndView(lookupForm);
    }
}
