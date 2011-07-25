/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.krms.impl.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.kuali.rice.krad.web.controller.MaintenanceDocumentController;
import org.kuali.rice.krad.web.form.MaintenanceForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krms.impl.repository.AgendaBo;
import org.kuali.rice.krms.impl.repository.AgendaItemBo;
import org.kuali.rice.krms.impl.repository.ContextBo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the Test UI Page
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@Controller
@RequestMapping(value = "/krmsAgendaEditor")
public class AgendaEditorController extends MaintenanceDocumentController {

    @Override
    public MaintenanceForm createInitialForm(HttpServletRequest request) {
        return new MaintenanceForm();
    }
    
    /**
     * This overridden method does extra work on refresh to populate the context and agenda
     * 
     * @see org.kuali.rice.krad.web.spring.controller.UifControllerBase#refresh(org.kuali.rice.krad.web.spring.form.UifFormBase, org.springframework.validation.BindingResult, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(params = "methodToCall=" + "refresh")
    @Override
    public ModelAndView refresh(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        MapUtils.verbosePrint(System.out, "actionParameters", form.getActionParameters());
        MapUtils.verbosePrint(System.out, "requestParameters", request.getParameterMap());
        
        String agendaId = null;

        MaintenanceForm maintenanceForm = (MaintenanceForm) form;
        String conversionFields = maintenanceForm.getActionParameters().get("conversionFields");
        String refreshCaller = request.getParameter("refreshCaller");

        // handle return from agenda lookup
        // TODO: this condition is sloppy 
        if (refreshCaller != null && refreshCaller.contains("Agenda") && refreshCaller.contains("LookupView") &&
                conversionFields != null &&
                // TODO: this is sloppy
                conversionFields.contains("agenda.id")) {
            AgendaEditor editorDocument =
                    ((AgendaEditor) maintenanceForm.getDocument().getNewMaintainableObject().getDataObject());
            agendaId = editorDocument.getAgenda().getId();
            AgendaBo agenda = getBusinessObjectService().findBySinglePrimaryKey(AgendaBo.class, agendaId);
            editorDocument.setAgenda(agenda);

            if (agenda.getContextId() != null) {
                ContextBo context = getBusinessObjectService().findBySinglePrimaryKey(ContextBo.class, agenda.getContextId());
                editorDocument.setContext(context);
            }
        }
        
        return super.refresh(form, result, request, response);
    }

    public ModelAndView updateComponent(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

        MaintenanceForm maintenanceForm = (MaintenanceForm) form;
        AgendaEditor editorDocument =
                ((AgendaEditor) maintenanceForm.getDocument().getNewMaintainableObject().getDataObject());
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", editorDocument.getAgenda().getName());
        map.put("contextId", editorDocument.getContext().getId());

        AgendaBo agenda = getBusinessObjectService().findByPrimaryKey(AgendaBo.class, Collections.unmodifiableMap(map));
        editorDocument.setAgenda(agenda);

        return super.updateComponent(form, result, request, response);
    }

    
    /**
     * @return the ALWAYS {@link AgendaItemInstanceChildAccessor} for the last ALWAYS child of the instance accessed by the parameter.
     * It will by definition refer to null.  If the instanceAccessor parameter refers to null, then it will be returned.  This is useful
     * for adding a youngest child to a sibling group. 
     */
    private AgendaItemInstanceChildAccessor getLastChildsAlwaysAccessor(AgendaItemInstanceChildAccessor instanceAccessor) {
        AgendaItemBo next = instanceAccessor.getChild();
        if (next == null) return instanceAccessor;
        while (next.getAlways() != null) { next = next.getAlways(); };
        return new AgendaItemInstanceChildAccessor(AgendaItemChildAccessor.always, next);
    }

    /**
     * @return the accessor to the child with the given agendaItemId under the given parent.  This method will search both When TRUE and 
     * When FALSE sibling groups.  If the instance with the given id is not found, null is returned.
     */
    private AgendaItemInstanceChildAccessor getInstanceAccessorToChild(AgendaItemBo parent, String agendaItemId) {

        // first try When TRUE, then When FALSE via AgendaItemChildAccessor.levelOrderChildren
        for (AgendaItemChildAccessor levelOrderChildAccessor : AgendaItemChildAccessor.children) {

            AgendaItemBo next = levelOrderChildAccessor.getChild(parent);
            
            // if the first item matches, return the accessor from the parent
            if (next != null && agendaItemId.equals(next.getId())) return new AgendaItemInstanceChildAccessor(levelOrderChildAccessor, parent);

            // otherwise walk the children
            while (next != null && next.getAlwaysId() != null) {
                if (next.getAlwaysId().equals(agendaItemId)) return new AgendaItemInstanceChildAccessor(AgendaItemChildAccessor.always, next);
                // move down
                next = next.getAlways();
            }
        }
        
        return null;
    }
    
    @RequestMapping(params = "methodToCall=" + "moveUp")
    public ModelAndView moveUp(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       /* Rough algorithm for moving a node up.  This is a "level order" move.  Note that in this tree,
        * level order means something a bit funky.  We are defining a level as it would be displayed in the browser, 
        * so only the traversal of When FALSE or When TRUE links increments the level, since ALWAYS linked nodes are
        * considered siblings.
        *
        * find the following: 
        *   node := the selected node
        *   parent := the selected node's parent, its containing node (via when true or when false relationship)
        *   parentsOlderCousin := the parent's level-order predecessor (sibling or cousin)
        *
        * if (node is first child in sibling group)
        *     if (node is in When FALSE group) 
        *         move node to last position in When TRUE group
        *     else 
        *         find youngest child of parentsOlderCousin and put node after it
        * else 
        *     move node up within its sibling group
        */
        
        AgendaBo agenda = getAgenda(form, request);
        // this is the root of the tree:
        AgendaItemBo firstItem = getFirstAgendaItem(agenda);

        String selectedItemId = request.getParameter("agenda_item_selected");
        AgendaItemBo node = getAgendaItemById(firstItem, selectedItemId);
        AgendaItemBo parent = getParent(firstItem, selectedItemId);
        AgendaItemBo parentsOlderCousin = (parent == null) ? null : getNextOldestOfSameGeneration(firstItem, parent.getId());

        AgendaItemChildAccessor childAccessor = getOldestChildAccessor(node, parent);
        if (childAccessor != null) { // node is first child in sibling group
            if (childAccessor == AgendaItemChildAccessor.whenFalse) {
                // move node to last position in When TRUE group
                AgendaItemInstanceChildAccessor youngestWhenTrueSiblingInsertionPoint = 
                        getLastChildsAlwaysAccessor(new AgendaItemInstanceChildAccessor(AgendaItemChildAccessor.whenTrue, parent));
                youngestWhenTrueSiblingInsertionPoint.setChild(node);
                AgendaItemChildAccessor.whenFalse.setChild(parent, node.getAlways());
                AgendaItemChildAccessor.always.setChild(node, null);
                
            } else if (parentsOlderCousin != null) {
                // find youngest child of parentsOlderCousin and put node after it
                AgendaItemInstanceChildAccessor youngestWhenFalseSiblingInsertionPoint = 
                        getLastChildsAlwaysAccessor(new AgendaItemInstanceChildAccessor(AgendaItemChildAccessor.whenFalse, parentsOlderCousin));
                youngestWhenFalseSiblingInsertionPoint.setChild(node);
                AgendaItemChildAccessor.whenTrue.setChild(parent, node.getAlways());
                AgendaItemChildAccessor.always.setChild(node, null);
            }
        } else if (!selectedItemId.equals(firstItem.getId())) { // conditional to miss special case of first node
            
            AgendaItemBo bogusRootNode = null;
            if (parent == null) {
                // special case, this is a top level sibling. rig up special parent node
                bogusRootNode = new AgendaItemBo();
                AgendaItemChildAccessor.whenTrue.setChild(bogusRootNode, firstItem);
                parent = bogusRootNode;
            } 
            
            // move node up within its sibling group
            AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
            AgendaItemBo olderSibling = accessorToSelectedNode.getInstance();
            AgendaItemInstanceChildAccessor accessorToOlderSibling = getInstanceAccessorToChild(parent, olderSibling.getId());

            accessorToOlderSibling.setChild(node);
            accessorToSelectedNode.setChild(node.getAlways());
            AgendaItemChildAccessor.always.setChild(node, olderSibling);
            
            if (bogusRootNode != null) {
                // clean up special case with bogus root node
                agenda.setFirstItemId(bogusRootNode.getWhenTrueId());
            }
        }
        
        return super.refresh(form, result, request, response);
    }
    
    @RequestMapping(params = "methodToCall=" + "moveDown")
    public ModelAndView moveDown(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Rough algorithm for moving a node down.  This is a "level order" move.  Note that in this tree,
         * level order means something a bit funky.  We are defining a level as it would be displayed in the browser, 
         * so only the traversal of When FALSE or When TRUE links increments the level, since ALWAYS linked nodes are
         * considered siblings.
         *
         * find the following: 
         *   node := the selected node
         *   parent := the selected node's parent, its containing node (via when true or when false relationship)
         *   parentsYoungerCousin := the parent's level-order successor (sibling or cousin)
         *
         * if (node is last child in sibling group)
         *     if (node is in When TRUE group) 
         *         move node to first position in When FALSE group
         *     else 
         *         move to first child of parentsYoungerCousin
         * else 
         *     move node down within its sibling group
         */
        

        AgendaBo agenda = getAgenda(form, request);
        // this is the root of the tree:
        AgendaItemBo firstItem = getFirstAgendaItem(agenda);

        String selectedItemId = request.getParameter("agenda_item_selected");
        AgendaItemBo node = getAgendaItemById(firstItem, selectedItemId);
        AgendaItemBo parent = getParent(firstItem, selectedItemId);
        AgendaItemBo parentsYoungerCousin = (parent == null) ? null : getNextYoungestOfSameGeneration(firstItem, parent.getId());

        if (node.getAlways() == null) { // node is last child in sibling group
            // set link to selected node to null
            if (parent.getWhenTrue() != null && isSiblings(parent.getWhenTrue(), node)) { // node is in When TRUE group
                // move node to first child under When FALSE
                
                AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
                accessorToSelectedNode.setChild(null);

                AgendaItemBo parentsFirstChild = parent.getWhenFalse();
                AgendaItemChildAccessor.whenFalse.setChild(parent, node);
                AgendaItemChildAccessor.always.setChild(node, parentsFirstChild);
            } else if (parentsYoungerCousin != null) { // node is in the When FALSE group
                // move to first child of parentsYoungerCousin under When TRUE
                
                AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
                accessorToSelectedNode.setChild(null);

                AgendaItemBo parentsYoungerCousinsFirstChild = parentsYoungerCousin.getWhenTrue();
                AgendaItemChildAccessor.whenTrue.setChild(parent, node);
                AgendaItemChildAccessor.always.setChild(node, parentsYoungerCousinsFirstChild);
            }
        } else { // move node down within its sibling group
            
            AgendaItemBo bogusRootNode = null;
            if (parent == null) {
                // special case, this is a top level sibling. rig up special parent node
                bogusRootNode = new AgendaItemBo();
                AgendaItemChildAccessor.whenFalse.setChild(bogusRootNode, firstItem);
                parent = bogusRootNode;
            } 
            
            // move node down within its sibling group
            AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
            AgendaItemBo youngerSibling = node.getAlways();
            accessorToSelectedNode.setChild(youngerSibling);
            AgendaItemChildAccessor.always.setChild(node, youngerSibling.getAlways());
            AgendaItemChildAccessor.always.setChild(youngerSibling, node);
            
            if (bogusRootNode != null) {
                // clean up special case with bogus root node
                agenda.setFirstItemId(bogusRootNode.getWhenFalseId());
            }
        }
        
        return super.refresh(form, result, request, response);
    }

    @RequestMapping(params = "methodToCall=" + "moveLeft")
    public ModelAndView moveLeft(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* 
         * Move left means make it a younger sibling of it's parent.
         */
        

        AgendaBo agenda = getAgenda(form, request);
        // this is the root of the tree:
        AgendaItemBo firstItem = getFirstAgendaItem(agenda);

        String selectedItemId = request.getParameter("agenda_item_selected");
        AgendaItemBo node = getAgendaItemById(firstItem, selectedItemId);
        AgendaItemBo parent = getParent(firstItem, selectedItemId);

        if (parent != null) {
            AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
            accessorToSelectedNode.setChild(node.getAlways());
            
            AgendaItemChildAccessor.always.setChild(node, parent.getAlways());
            AgendaItemChildAccessor.always.setChild(parent, node);
        }
        
        return super.refresh(form, result, request, response);
    }
    
    @RequestMapping(params = "methodToCall=" + "moveRight")
    public ModelAndView moveRight(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* 
         * Move right prefers moving to bottom of upper sibling's When FALSE branch
         * ... otherwise ..
         * moves to top of lower sibling's When TRUE branch
         */

        AgendaBo agenda = getAgenda(form, request);
        // this is the root of the tree:
        AgendaItemBo firstItem = getFirstAgendaItem(agenda);

        String selectedItemId = request.getParameter("agenda_item_selected");
        AgendaItemBo node = getAgendaItemById(firstItem, selectedItemId);
        AgendaItemBo parent = getParent(firstItem, selectedItemId);

        AgendaItemBo bogusRootNode = null;
        if (parent == null) {
            // special case, this is a top level sibling. rig up special parent node
            bogusRootNode = new AgendaItemBo();
            AgendaItemChildAccessor.whenFalse.setChild(bogusRootNode, firstItem);
            parent = bogusRootNode;
        } 

        AgendaItemInstanceChildAccessor accessorToSelectedNode = getInstanceAccessorToChild(parent, node.getId());
        AgendaItemBo olderSibling = (accessorToSelectedNode.getInstance() == parent) ? null : accessorToSelectedNode.getInstance();

        if (olderSibling != null) {
            accessorToSelectedNode.setChild(node.getAlways());
            AgendaItemInstanceChildAccessor yougestWhenFalseSiblingInsertionPoint = 
                    getLastChildsAlwaysAccessor(new AgendaItemInstanceChildAccessor(AgendaItemChildAccessor.whenFalse, olderSibling));
            yougestWhenFalseSiblingInsertionPoint.setChild(node);
            AgendaItemChildAccessor.always.setChild(node, null);
        } else if (node.getAlways() != null) { // has younger sibling
            accessorToSelectedNode.setChild(node.getAlways());
            AgendaItemBo childsWhenTrue = node.getAlways().getWhenTrue();
            AgendaItemChildAccessor.whenTrue.setChild(node.getAlways(), node);
            AgendaItemChildAccessor.always.setChild(node, childsWhenTrue);
        }
        
        if (bogusRootNode != null) {
            // clean up special case with bogus root node
            agenda.setFirstItemId(bogusRootNode.getWhenFalseId());
        }
        
        return super.refresh(form, result, request, response);
    }

    
    
    private boolean isSiblings(AgendaItemBo cousin1, AgendaItemBo cousin2) {
        if (cousin1.equals(cousin2)) return true; // this is a bit abusive
        
        // can you walk to c1 from ALWAYS links of c2?
        AgendaItemBo candidate = cousin2;
        while (null != (candidate = candidate.getAlways())) {
            if (candidate.equals(cousin1)) return true;
        }
        // can you walk to c2 from ALWAYS links of c1?
        candidate = cousin1;
        while (null != (candidate = candidate.getAlways())) {
            if (candidate.equals(cousin2)) return true;
        }
        return false;
    }

    /**
     * This method returns the level order accessor (getWhenTrue or getWhenFalse) that relates the parent directly 
     * to the child.  If the two nodes don't have such a relationship, null is returned. 
     * Note that this only finds accessors for oldest children, not younger siblings.
     */
    private AgendaItemChildAccessor getOldestChildAccessor(
            AgendaItemBo child, AgendaItemBo parent) {
        AgendaItemChildAccessor levelOrderChildAccessor = null;
        
        if (parent != null) {
            for (AgendaItemChildAccessor childAccessor : AgendaItemChildAccessor.children) {
                if (child.equals(childAccessor.getChild(parent))) {
                    levelOrderChildAccessor = childAccessor;
                    break;
                }
            }
        }
        return levelOrderChildAccessor;
    }
    
    /**
     * This method finds and returns the first agenda item in the agenda, or null if there are no items presently
     * 
     * @param agenda
     * @return
     */
    private AgendaItemBo getFirstAgendaItem(AgendaBo agenda) {
        AgendaItemBo firstItem = null;
        for (AgendaItemBo agendaItem : agenda.getItems()) {
            if (agenda.getFirstItemId().equals(agendaItem.getId())) {
                firstItem = agendaItem;
                break;
            }
        }
        return firstItem;
    }
    
    /**
     * @return the closest younger sibling of the agenda item with the given ID, and if there is no such sibling, the closest younger cousin.
     * If there is no such cousin either, then null is returned.
     */
    private AgendaItemBo getNextYoungestOfSameGeneration(AgendaItemBo root, String agendaItemId) {
        AgendaItemBo result = getNextYoungestOfSameGenerationHelper(root, 0, new ArrayList<AgendaItemBo>(), agendaItemId);
        if (result == NULL_AGENDA_ITEM) result = null;
        return result;
    }
    
    private AgendaItemBo getNextYoungestOfSameGenerationHelper(AgendaItemBo node, int level, ArrayList<AgendaItemBo> youngerCousinByLevel, String agendaItemId) {
        AgendaItemBo result = null;
        if (agendaItemId.equals(node.getId())) {
            result = (youngerCousinByLevel.size() > level) ? youngerCousinByLevel.get(level) : NULL_AGENDA_ITEM;
        } else {
            for (int i=AgendaItemChildAccessor.linkedNodes.length; i >= 0; --i) {
                AgendaItemChildAccessor childAccessor = AgendaItemChildAccessor.linkedNodes[i];
                AgendaItemBo child = childAccessor.getChild(node);
                if (child != null) {
                    int levelNext = level;
                    // we don't change the level order parent when we traverse ALWAYS links
                    // and we only adjust the olderCousinByLevel when we traverse ALWAYS links
                    if (childAccessor == AgendaItemChildAccessor.always) {
                        // ensure that we can set the element at index=level
                        while (youngerCousinByLevel.size() <= level) youngerCousinByLevel.add(null);
                        youngerCousinByLevel.set(level, node);
                    } else {
                        levelNext = level +1;
                    }
                    result = getNextYoungestOfSameGenerationHelper(child, levelNext, youngerCousinByLevel, agendaItemId);
                    if (result != null) break;
                }
            }
        }
        return result;
    }
    
    /**
     * @return the closest older sibling of the agenda item with the given ID, and if there is no such sibling, the closest older cousin.
     * If there is no such cousin either, then null is returned.
     */
    private AgendaItemBo getNextOldestOfSameGeneration(AgendaItemBo root, String agendaItemId) {
        AgendaItemBo result = getNextOldestOfSameGenerationHelper(root, 0, new ArrayList<AgendaItemBo>(), agendaItemId);
        if (result == NULL_AGENDA_ITEM) result = null;
        return result;
    }
    
    // null object used for detecting when there is no predecessor without walking the whole tree needlessly.
    private static final AgendaItemBo NULL_AGENDA_ITEM = new AgendaItemBo();
    
    private AgendaItemBo getNextOldestOfSameGenerationHelper(AgendaItemBo node, int level, ArrayList<AgendaItemBo> olderCousinByLevel, String agendaItemId) {
        AgendaItemBo result = null;
        if (agendaItemId.equals(node.getId())) {
            result = (olderCousinByLevel.size() > level) ? olderCousinByLevel.get(level) : NULL_AGENDA_ITEM;
        } else {
            for (AgendaItemChildAccessor childAccessor : AgendaItemChildAccessor.linkedNodes) {
                AgendaItemBo child = childAccessor.getChild(node);
                if (child != null) {
                    int levelNext = level;
                    // we don't change the level order parent when we traverse ALWAYS links
                    // and we only adjust the olderCousinByLevel when we traverse ALWAYS links
                    if (childAccessor == AgendaItemChildAccessor.always) {
                        // ensure that we can set the element at index=level
                        while (olderCousinByLevel.size() <= level) olderCousinByLevel.add(null);
                        olderCousinByLevel.set(level, node);
                    } else {
                        levelNext = level +1;
                    }
                    result = getNextOldestOfSameGenerationHelper(child, levelNext, olderCousinByLevel, agendaItemId);
                    if (result != null) break;
                }
            }
        }
        return result;
    }

    /**
     * returns the parent of the item with the passed in id.  Note that {@link AgendaItemBo}s related by ALWAYS relationships are considered siblings.
     */ 
    private AgendaItemBo getParent(AgendaItemBo root, String agendaItemId) {
        return getParentHelper(root, null, agendaItemId);
    }
    
    private AgendaItemBo getParentHelper(AgendaItemBo node, AgendaItemBo levelOrderParent, String agendaItemId) {
        AgendaItemBo result = null;
        if (agendaItemId.equals(node.getId())) {
            result = levelOrderParent;
        } else {
            for (AgendaItemChildAccessor childAccessor : AgendaItemChildAccessor.linkedNodes) {
                AgendaItemBo child = childAccessor.getChild(node);
                if (child != null) {
                    // we don't change the level order parent when we traverse ALWAYS links 
                    AgendaItemBo lop = (childAccessor == AgendaItemChildAccessor.always) ? levelOrderParent : node;
                    result = getParentHelper(child, lop, agendaItemId);
                    if (result != null) break;
                }
            }
        }
        return result;
    }

    /**
     * Search the tree for the agenda item with the given id.
     */
    private AgendaItemBo getAgendaItemById(AgendaItemBo node, String agendaItemId) {
        if (node == null) throw new IllegalArgumentException("node must be non-null");

        AgendaItemBo result = null;
        
        if (agendaItemId.equals(node.getId())) {
            result = node;
        } else {
            for (AgendaItemChildAccessor childAccessor : AgendaItemChildAccessor.linkedNodes) {
                AgendaItemBo child = childAccessor.getChild(node);
                if (child != null) {
                    result = getAgendaItemById(child, agendaItemId);
                    if (result != null) break;
                }
            }
        } 
        return result;
    }

    /**
     * This method gets the agenda from the form
     * 
     * @param form
     * @param request
     * @return
     */
    private AgendaBo getAgenda(UifFormBase form, HttpServletRequest request) {
        MaintenanceForm maintenanceForm = (MaintenanceForm) form;
        AgendaEditor editorDocument = ((AgendaEditor)maintenanceForm.getDocument().getDocumentDataObject());
        AgendaBo agenda = editorDocument.getAgenda();
        return agenda;
    }
    
    private void treeToInOrderList(AgendaItemBo agendaItem, List<AgendaItemBo> listToBuild) {
        listToBuild.add(agendaItem);
        for (AgendaItemChildAccessor childAccessor : AgendaItemChildAccessor.linkedNodes) {
            AgendaItemBo child = childAccessor.getChild(agendaItem);
            if (child != null) treeToInOrderList(child, listToBuild);
        }
    }

    
    @RequestMapping(params = "methodToCall=" + "delete")
    public ModelAndView delete(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        AgendaBo agenda = getAgenda(form, request);
        AgendaItemBo firstItem = getFirstAgendaItem(agenda);

        String agendaItemSelected = request.getParameter("agenda_item_selected");
        
        if (firstItem != null) {
            // need to handle the first item here, our recursive method won't handle it.  
            if (agendaItemSelected.equals(firstItem.getAgendaId())) {
                agenda.setFirstItemId(firstItem.getAlwaysId());
            } else {
                deleteAgendaItem(firstItem, agendaItemSelected);
            }
        }
        
        return super.refresh(form, result, request, response);
    }

    // TODO: smarter delete would be desirable.
    private void deleteAgendaItem(AgendaItemBo root, String agendaItemIdToDelete) {
        if (deleteAgendaItem(root, AgendaItemChildAccessor.whenTrue, agendaItemIdToDelete) || 
                deleteAgendaItem(root, AgendaItemChildAccessor.whenFalse, agendaItemIdToDelete) || 
                deleteAgendaItem(root, AgendaItemChildAccessor.always, agendaItemIdToDelete)); // TODO: this is confusing, refactor
    }
    
    private boolean deleteAgendaItem(AgendaItemBo agendaItem, AgendaItemChildAccessor childAccessor, String agendaItemIdToDelete) {
        if (agendaItem == null || childAccessor.getChild(agendaItem) == null) return false;
        if (agendaItemIdToDelete.equals(childAccessor.getChild(agendaItem).getId())) {
            // delete the child in such a way that any ALWAYS children don't get lost from the tree
            AgendaItemBo grandchildToKeep = childAccessor.getChild(agendaItem).getAlways();
            childAccessor.setChild(agendaItem, grandchildToKeep);
            return true;
        } else {
            AgendaItemBo child = childAccessor.getChild(agendaItem);
            // recurse
            for (AgendaItemChildAccessor nextChildAccessor : AgendaItemChildAccessor.linkedNodes) {
                if (deleteAgendaItem(child, nextChildAccessor, agendaItemIdToDelete)) return true;
            }
        }
        return false;
    }

    /**
     * binds a child accessor to an AgendaItemBo instance
     */
    private static class AgendaItemInstanceChildAccessor {
        
        private final AgendaItemChildAccessor accessor;
        private final AgendaItemBo instance;

        public AgendaItemInstanceChildAccessor(AgendaItemChildAccessor accessor, AgendaItemBo instance) {
            this.accessor = accessor;
            this.instance = instance;
        }
        
        public void setChild(AgendaItemBo child) {
            accessor.setChild(instance, child);
        }
        
        public AgendaItemBo getChild() {
            return accessor.getChild(instance);
        }
        
        public AgendaItemBo getInstance() { return instance; }
    }
    
    /**
     * This class abstracts getting and setting a child of an AgendaItemBo, making some recursive operations 
     * require less boiler plate 
     */
    private static class AgendaItemChildAccessor {
        
        private enum Child { WHEN_TRUE, WHEN_FALSE, ALWAYS };
        
        private static final AgendaItemChildAccessor whenTrue = new AgendaItemChildAccessor(Child.WHEN_TRUE); 
        private static final AgendaItemChildAccessor whenFalse = new AgendaItemChildAccessor(Child.WHEN_FALSE); 
        private static final AgendaItemChildAccessor always = new AgendaItemChildAccessor(Child.ALWAYS); 
        
        /**
         * Accessors for all linked items
         */
        private static final AgendaItemChildAccessor [] linkedNodes = { whenTrue, whenFalse, always };
        
        /**
         * Accessors for children (so ALWAYS is omitted);
         */
        private static final AgendaItemChildAccessor [] children = { whenTrue, whenFalse };
        
        private final Child whichChild;
        
        private AgendaItemChildAccessor(Child whichChild) {
            if (whichChild == null) throw new IllegalArgumentException("whichChild must be non-null");
            this.whichChild = whichChild;
        }
        
        /**
         * @return the referenced child
         */
        public AgendaItemBo getChild(AgendaItemBo parent) {
            switch (whichChild) {
            case WHEN_TRUE: return parent.getWhenTrue();
            case WHEN_FALSE: return parent.getWhenFalse();
            case ALWAYS: return parent.getAlways();
            default: throw new IllegalStateException();
            }
        }
        
        /**
         * Sets the child reference and the child id 
         */
        public void setChild(AgendaItemBo parent, AgendaItemBo child) {
            switch (whichChild) {
            case WHEN_TRUE: 
                parent.setWhenTrue(child);
                parent.setWhenTrueId(child == null ? null : child.getId());
                break;
            case WHEN_FALSE:
                parent.setWhenFalse(child);
                parent.setWhenFalseId(child == null ? null : child.getId());
                break;
            case ALWAYS:
                parent.setAlways(child);
                parent.setAlwaysId(child == null ? null : child.getId());
                break;
            default: throw new IllegalStateException();
            }
        }
    }

}
