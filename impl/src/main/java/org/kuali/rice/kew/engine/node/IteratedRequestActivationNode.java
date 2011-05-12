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
package org.kuali.rice.kew.engine.node;

import org.apache.log4j.MDC;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.engine.RouteHelper;
import org.kuali.rice.kew.exception.ResourceUnavailableException;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.PerformanceLogger;
import org.kuali.rice.kew.util.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * A node which will iteratively activate any requests pending on it.  Subclasses are responsible for generating the requests
 * to be activated, and then delegating to superclass {@link #process(RouteContext, RouteHelper)} to activate
 * those requests.
 *
 * This node can be used to serve multiple batches of requests, and can be re-entered multiple times.  The algorithm it implements is:
 * 
 * <ol>
 *   <li>activate any existing requests (requests may have been generated by an external party)
 *   <li>while request fulfillment criteria indicates that any pending requests have been satisfied
 *     <ol>
 *       <li>generate new requests</li>
 *       <li><b>if no requests were generated, we are done, return and transition</b></li>
 *       <li>otherwise if requests were generated
 *         <ol>
 *           <li>activate requests (depending on activation policy, serial or parallel, <i>NOT ALL GENERATED REQUESTS MAY BE ACTIVATED AT THIS POINT</i>)</li>
 *           <li>determine a request fulfillment criteria</li>
 *           <li>continue in loop</li>
 *         </ol>
 *       </li>
 *   </li>
 *   <li>otherwise if pending requests have not been satisfied, block until invoked again</li>
 * </ol>
 * This node transitions/completes when there are no remaining <i>blocking</i> action requests (i.e., no approval or
 * completion requests).
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class IteratedRequestActivationNode implements SimpleNode {

    protected final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(getClass());
    private static long generatedRequestPriority = 0;

    protected static interface RequestFulfillmentCriteria {
        public boolean pendingRequestsAreFulfilled(boolean activatedBlockingRequests, RouteContext routeContext);
    }

    private static class AllBlockingRequestsCompleteCriteria implements RequestFulfillmentCriteria {
        public boolean pendingRequestsAreFulfilled(boolean activatedBlockingRequests, RouteContext routeContext) {
            // determine whether there are any pending blocking requests
            if (activatedBlockingRequests) {
                // if we just activated blocking requests then surely there are pending blocking requests
                assert(activatedBlockingRequests == blockingRequestsArePending(routeContext.getDocument(), routeContext.getNodeInstance())) : "Blocking requests were activated but none were subsequently found";
                return false;
            } else {
                // otherwise let's see if there are any pre-existing blocking requests associated with this node instance
                return !blockingRequestsArePending(routeContext.getDocument(), routeContext.getNodeInstance());
            }
        }
    }

    private static class SimulatingCriteria implements RequestFulfillmentCriteria {
        public boolean pendingRequestsAreFulfilled(boolean activatedBlockingRequests, RouteContext routeContext) {
            // when simulating, never block for requests to be fulfilled
            return true;
        }
    }

    public SimpleResult process(RouteContext routeContext, RouteHelper routeHelper) throws Exception {
        // if previous requests were satisfied (if no requests have been generated yet, this should return true
        RequestFulfillmentCriteria criteria = getRequestFulfillmentCriteria(routeContext);
        // generate new requests until they are not satisfied or none are generated
        // if none are generated then we should transition immediately
        boolean activatedBlockingRequests = activateRequests(routeContext); // activation should always occur as something might have generated unactived requests
        boolean initialRequestGeneration = routeContext.getNodeInstance().isInitial();
        while (criteria.pendingRequestsAreFulfilled(activatedBlockingRequests, routeContext)) {
            boolean newRequestsGenerated = generateNewRequests(initialRequestGeneration, routeContext, routeHelper);
            initialRequestGeneration = false;

            if (!newRequestsGenerated) {
                // if the pending requests were fulfilled
                // ...and we didn't generate any new requests
                // ...then there are no further requests to activate (XXX: WRONG)
                // ...and we have no more processing to do
                return new SimpleResult(true);
            }

            // activate any requests that were generated
            activatedBlockingRequests = activateRequests(routeContext);

            // set the request fulfillment criteria for the new set of requests
            // if we are simulating, always set a criteria that indicates fulfillment
            if (routeContext.isSimulation()) {
                criteria = new SimulatingCriteria();
            } else {
                criteria = getRequestFulfillmentCriteria(routeContext);
            }
        }
        // if we got here, then for some reason pending requests have not been fulfilled
        // so wait/block for request fulfillment
        return new SimpleResult(false);
    }

    /**
     * Template method that subclasses should override to indicate when activated requests have been fulfilled.
     * The default implementation is that all pending blocking requests must be fulfilled.
     * @return a RequestFulfillmentCriteria that indicates when activated requests have been fulfilled.
     */
    protected RequestFulfillmentCriteria getRequestFulfillmentCriteria(RouteContext routeContext) {
        return new AllBlockingRequestsCompleteCriteria();
    }

    /**
     * Template method that subclasses should override to generates new requests
     * @param initial whether this is the very first request generation, that is, the first generation in the first invocation of the node
     * @return whether new requests were generated
     * @throws Exception 
     */
    protected boolean generateNewRequests(boolean initial, RouteContext context, RouteHelper routeHelper) throws WorkflowException, Exception {
        return false;
    }

    /**
     * Activates any pending requests and returns whether there are outstanding blocking requests
     * @param context the RouteContext
     * @throws WorkflowException if anything goes wrong...
     * @return whether there are outstanding blocking requests
     */
    protected boolean activateRequests(RouteContext routeContext) throws WorkflowException {
        DocumentRouteHeaderValue document = routeContext.getDocument();
        RouteNodeInstance nodeInstance = routeContext.getNodeInstance();
        if (routeContext.isSimulation()) {
            // this seems to indicate whether, when we are simulating, to activate requests...
            if (routeContext.getActivationContext().isActivateRequests()) {
                activateRequests(routeContext, document, nodeInstance);
            }
            // if we are in simulation, don't block, just transition out
            return false;
        } else {
            // activate any unactivated pending requests on this node instance
            return activateRequests(routeContext, document, nodeInstance);
        }
    }

    /**
     * @return whether there are any pending requests at the given route node instance which are blocking (i.e., 'approve' or 'complete')
     */
    private static boolean blockingRequestsArePending(DocumentRouteHeaderValue document, RouteNodeInstance nodeInstance) {
        // returns blocking requests that are *activated*
        List<ActionRequestValue> requests = KEWServiceLocator.getActionRequestService().findPendingRootRequestsByDocIdAtRouteNode(document.getDocumentId(), nodeInstance.getRouteNodeInstanceId());
        boolean blockingRequestsArePending = false;
        for (ActionRequestValue request: requests) {
            if (request.isApproveOrCompleteRequest()) {
                blockingRequestsArePending = true;
                break;
            }
        }
        return blockingRequestsArePending;
    }

    /**
     * Activates the action requests that are pending at this routelevel of the document. The requests are processed by priority and then request ID.
     * It is implicit in the access that the requests are activated according to the route level above all.
     * <p>
     * FYI and acknowledgement requests do not cause the processing to stop. Only action requests for approval or completion cause the processing to
     * stop and then only for route level with a serialized activation policy. Only requests at the current document's current route level are activated.
     * Inactive requests at a lower level cause a routing exception.
     * <p>
     * Exception routing and adhoc routing are processed slightly differently.
     * 
     * 
     * @param context the RouteContext
     * @param document the document we are processing
     * @param nodeInstance the node instance we are processing
     * @return True if the any blocking actions requests (approve or complete) were activated.
     * @throws ResourceUnavailableException
     * @throws WorkflowException
     */
    private boolean activateRequests(RouteContext context, DocumentRouteHeaderValue document, RouteNodeInstance nodeInstance) throws WorkflowException {
        MDC.put("docId", document.getDocumentId());
        PerformanceLogger performanceLogger = new PerformanceLogger(document.getDocumentId());
        List generatedActionItems = new ArrayList();
        List<ActionRequestValue> requests = KEWServiceLocator.getActionRequestService().findPendingRootRequestsByDocIdAtRouteNode(document.getDocumentId(), nodeInstance.getRouteNodeInstanceId());
        if (context.isSimulation()) {
            requests.addAll(context.getEngineState().getGeneratedRequests());
        }
        // this will sort higher priority requests to the front
        // blocking requests are higher priority, so all blocking requests will be
        // activated before non-blocking requests
        Collections.sort(requests, new Utilities.PrioritySorter());
        LOG.info("Pending Root Requests " + requests.size());
        String activationType = nodeInstance.getRouteNode().getActivationType();
        boolean isParallel = KEWConstants.ROUTE_LEVEL_PARALLEL.equals(activationType);
        boolean activatedApproveRequest = false;
        for (Iterator iter = requests.iterator(); iter.hasNext();) {
            if (activatedApproveRequest && !isParallel) {
                LOG.info("Already activated an apprve request and serial, so not activating any more");
                break;
            }
            ActionRequestValue request = (ActionRequestValue) iter.next();
            LOG.info("ActionRequestValue: " + request);
            if (request.getParentActionRequest() != null || request.getNodeInstance() == null) {
                // 1. disregard request if it's not a top-level request
                // 2. disregard request if it's a "future" request and hasn't been attached to a node instance yet
                continue; 
            }
            if (request.isActive()) {
                activatedApproveRequest = activatedApproveRequest || request.isApproveOrCompleteRequest();
                continue;
            }
            logProcessingMessage(request);   
            LOG.info("Activating request. " + request);
            activatedApproveRequest = activateRequest(context, request, nodeInstance, generatedActionItems) || activatedApproveRequest;
        }
        // now let's send notifications, since this code needs to be able to activate each request individually, we need
        // to collection all action items and then notify after all have been generated
        if (!context.isSimulation()) {
            KEWServiceLocator.getNotificationService().notify(generatedActionItems);
        }
        performanceLogger.log("Time to activate requests.");
        return activatedApproveRequest;
    }

    private boolean activateRequest(RouteContext context, ActionRequestValue actionRequest, RouteNodeInstance nodeInstance, List generatedActionItems) {
        if (actionRequest.isRoleRequest()) {
            List actionRequests = KEWServiceLocator.getActionRequestService().findPendingRootRequestsByDocIdAtRouteNode(actionRequest.getDocumentId(), nodeInstance.getRouteNodeInstanceId());
            for (Iterator iterator = actionRequests.iterator(); iterator.hasNext();) {
                ActionRequestValue siblingRequest = (ActionRequestValue) iterator.next();
                if (actionRequest.getRoleName().equals(siblingRequest.getRoleName())) {
                    generatedActionItems.addAll(KEWServiceLocator.getActionRequestService().activateRequestNoNotification(siblingRequest, context.getActivationContext()));
                }
            }
        }
        generatedActionItems.addAll(KEWServiceLocator.getActionRequestService().activateRequestNoNotification(actionRequest, context.getActivationContext()));
        return actionRequest.isApproveOrCompleteRequest() && ! actionRequest.isDone();
    }
    
    protected void saveActionRequest(RouteContext context, ActionRequestValue actionRequest) {
        if (!context.isSimulation()) {
            KEWServiceLocator.getActionRequestService().saveActionRequest(actionRequest);
        } else {
            actionRequest.setActionRequestId(new Long(generatedRequestPriority++));
            context.getEngineState().getGeneratedRequests().add(actionRequest);    
        }
        
    }
    
    private void logProcessingMessage(ActionRequestValue request) {
        //if (LOG.isDebugEnabled()) {
                RouteNodeInstance nodeInstance = request.getNodeInstance();
            StringBuffer buffer = new StringBuffer();
            buffer.append("Processing AR: ").append(request.getActionRequestId()).append("\n");
            buffer.append("AR Node Name: ").append(nodeInstance != null ? nodeInstance.getName() : "null").append("\n");
            buffer.append("AR RouteLevel: ").append(request.getRouteLevel()).append("\n");
            buffer.append("AR Request Code: ").append(request.getActionRequested()).append("\n");
            buffer.append("AR Request priority: ").append(request.getPriority()).append("\n");
            LOG.info(buffer);
        //}
    }
            
}
