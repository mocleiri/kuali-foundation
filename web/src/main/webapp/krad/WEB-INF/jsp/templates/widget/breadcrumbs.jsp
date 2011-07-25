<%--
 Copyright 2006-2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/krad/WEB-INF/jsp/tldHeader.jsp"%>

<tiles:useAttribute name="widget"
	classname="org.kuali.rice.krad.uif.widget.BreadCrumbs" />
	
<c:set var="current" value="${KualiForm.formHistory.generatedCurrentBreadcrumb}"/>
<c:set var="crumbs" value="${KualiForm.formHistory.generatedBreadcrumbs}"/> 

<%--Create the breadcrumbs using the generatedBreadcrumbs from history, note that current
is omitted by default, but the link to it is still present, it can be shown as a clickable
link again through jquery as in setPageBreadcrumb when needed --%>
<c:if test="${(fn:length(crumbs) >= 1) || (widget.displayBreadcrumbsWhenOne && fn:length(crumbs) == 0)}">
	<div id="breadcrumbs" class="${widget.styleClassesAsString}">
		<c:forEach var="entry" items="${crumbs}" >
	        <a href="${entry.url}">${entry.title}</a><span> &raquo; </span>
	    </c:forEach>
	   	<span id="current_breadcrumb_span" class="current">${current.title}</span>
   		<a id="current_breadcrumb_anchor" style="display:none;" href="${current.url}"/>${current.title}</a>
	</div>
</c:if>