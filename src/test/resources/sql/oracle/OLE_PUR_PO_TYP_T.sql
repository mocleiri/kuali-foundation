--
-- Copyright 2010-2013 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

TRUNCATE TABLE OLE_PUR_PO_TYP_T DROP STORAGE
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (1.0,'Firm, Fixed',1.0,'OLE1','Y','Firm orders are commitments from a library to purchase a quantity of a title from a provider.  Once all quantities are resolved on a firm order (received & paid for, or cancelled) the order is considered complete and that no further relationship continues with a provider with regards to that firm order.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (2.0,'Standing',1.0,'OLE2','Y','Standing orders are commitments from a library to purchase titles published over time, e.g., a series.  A standing order is an order where it is not known what all the titles in the series are going to be.  Each title comes as it is published and it is then that the library describes it, receives it, and pays for it.  Unless discontinued, a standing order will be modified by year-end so that it continues against a new fiscal year.  Unlike subscriptions, standing orders are continued as opposed to being renewed.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (3.0,'Subscription',1.0,'OLE3','Y','Subscription orders are commitments from a library to purchase a run [subscription] of a title from a provider.  A subscription order can be for a package of titles, often when the titles are e-journals.  Subscriptions last for a given term (most common being one year), and are renewed to continue the relationship with a provider.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (4.0,'Membership',1.0,'OLE4','Y','Membership orders are commitments from a library to purchase a subscription to a membership from a provider.  Having a membership may give a library permission to order materials from the provider or might cause the provider to periodically send materials to the library. Generally, memberships are renewed to continue the relationship with a provider.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (5.0,'Approval',1.0,'OLE5','Y','Approval orders are tentative commitments from a library to purchase titles from a provider; approval orders generally come from a provider with whom the library has established an approval plan (automatic shipment of materials based on a librarys acquisitions profile).  Once approved and quantities are resolved (received & paid) the approval order is considered complete and no further relationship continues with the provider with regards to that approval order. In this respect, an approval order resembles a firm order.  If the provider provides records for titles sent on approval, the orders created through an ingest would be set to order type=approval.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (6.0,'Firm (multi part)',6.0,'OLE6','Y','A firm order for a title in a finite number of parts.  The parts may be published all at once (like an encyclopedia) or over time (like a set of collected works, each one coming out when it is edited).  Sometimes called an "extended" order.  Some libraries will want to use serials checkin functionality to keep track of which parts have been received.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (7.0,'Blanket',7.0,'OLE7','Y','Blanket orders are commitments from a library to purchase (sometimes after approval) all titles from a provider that fit within a predestinated profile (Author, subject, publisher, etc.)  The titles to be sent from the provider are not known until they are received at the library.  The library processes each title as it is sent from the provider by describing it, receiving it, and invoicing it.  Unless discontinued, a blanket order will be modified by year-end so that they continue against a new fiscal year.  Unlike subscriptions, blanket orders are continued as opposed to being renewed.')
/
INSERT INTO OLE_PUR_PO_TYP_T (OLE_PO_TYPE_ID,OLE_PO_TYPE,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (8.0,'Integrating resource',8.0,'OLE8','Y','These are similar to subscriptions, except that each new piece supersedes or replaces a previous one.  A common example is legal publications, in which each section of a work is periodically reissued as laws change, and the previous version of that section discarded.')
/
