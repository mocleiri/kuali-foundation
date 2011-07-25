/*
 * Copyright 2006-2007 The Kuali Foundation
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

/**
 * Show growl with message, title and theme passed in
 *
 * @param message message of this jGrowl
 * @param title title of this jGrowl, can be empty string for none
 * @param theme class to append to jGrowl classes, can be empty string for none
 */
function showGrowl(message, title, theme){
	var context = getContext();
	if(theme){
		context.jGrowl(message, { header: title, theme: theme});
	}
	else{
		context.jGrowl(message, { header: title});
	}
}

/**
 * Set default growl options for this view
 *
 * @param options
 */
function setGrowlDefaults(options){
	var context = getContext();
	context.jGrowl.defaults = context.extend(context.jGrowl.defaults, options);
}

/**
 * Uses jQuery plug-in to show a loading notification for a page request. See
 * <link>http://plugins.jquery.com/project/showLoading</link> for documentation
 * on options.
 *
 * @param showLoading -
 *          boolean that indicates whether the loading indicator should be shown
 *          (true) or hidden (false)
 */
function createLoading(showLoading) {
	var methodToCall = jq("input[name='methodToCall']").val();
	var unblockUIOnLoading = jq("input[name='unblockUIOnLoading']").val();
	
	if(unblockUIOnLoading == null || unblockUIOnLoading.toUpperCase() == "false".toUpperCase()){
		if(top == self){
			//no portal
			if (showLoading) {
				if(methodToCall && methodToCall.toUpperCase() == "save".toUpperCase()){
					jq.blockUI({message: savingMessage});
				}
				else{
					jq.blockUI({message: loadingMessage});
				}
			}
			else {
				jq.unblockUI();
			}
		}
		else{
			if (showLoading) {
				if(methodToCall && methodToCall.toUpperCase() == "save".toUpperCase()){
					top.$.blockUI({message: savingMessage});
				}
				else{
					top.$.blockUI({message: loadingMessage});
				}
			}
			else {
				top.$.unblockUI();
			}
		}
	}
}

/**
* Applies the error coloring for fields with errors, warnings, or information
*/
function applyErrorColors(errorDivId, errorNum, warningNum, infoNum, clientSide){
	if(errorDivId){
		var div = jq("#" + errorDivId);
		var label = jq("#" + errorDivId.replace("errors_div", "label"));
		var highlightLine = "";

		//check to see if the option to highlight fields is on
		if(div.length > 0 && !div.hasClass("noHighlight")){
			if (div.parent().is("td") || (div.parent().is(".refreshWrapper") && div.parent().parent().is("td"))) {
				highlightLine = div.closest("td");
			}
			else{
				highlightLine = div.closest(".fieldLine");
			}

			if (highlightLine.length > 0) {
				if(errorNum && !clientSide){
					highlightLine.addClass("serverError");
					label.addClass("serverError");
				}
				else if(errorNum){
					highlightLine.addClass("clientError");
					label.addClass("clientError");
				}
				else if(warningNum){
					highlightLine.addClass("warning");
					label.addClass("warning");
				}
				else if(infoNum){
					highlightLine.addClass("information");
					label.addClass("information");
				}
				else{
					//we are only removing errors client side - no knowledge of warnings/infos
					if(div.parent().hasClass("errorsField")){
						var error_li = div.parent().find(".errorMessages").find("li");
						var moreErrors = false;
						error_li.each(function(){
							if(jq(this).css("display") != "none"){
								moreErrors = true;
							}
						});

						label.removeClass("clientError");
						if(!moreErrors){
							highlightLine.removeClass("clientError");
						}
					}
					else{
						highlightLine.removeClass("clientError");
						label.removeClass("clientError");
					}
				}
			}
		}

		//highlight tab that contains errors - no setting to turn this off because it is necessary
		var tabDiv = div.closest(".ui-tabs-panel");
		if(tabDiv.length > 0){
			var tabId = tabDiv.attr("id");
			var tabAnchor = jq("a[href='#" + tabId + "']");
			var errorIcon = jq("#" + tabId + "_errorIcon");

			if(tabAnchor.length > 0){
				var hasErrors = false;
				if(errorNum){
					hasErrors = true;
				}
				else{
					var error_li = tabDiv.find(".errorMessages").find("li");
					error_li.each(function(){
						if(jq(this).css("display") != "none"){
							hasErrors = true;
						}
					});
				}

				if(hasErrors){
					tabAnchor.addClass("clientError");
					if(errorIcon.length == 0){
						tabAnchor.append("<img id='"+ tabId +"_errorIcon' alt='error' src='/kr-dev/kr/static/images/errormark.gif'>");
					}
				}
				else if(!hasErrors){
					tabAnchor.removeClass("clientError");
					errorIcon.remove();
				}
			}
		}
	}
}

/**
 * Shows the field error icon if errorCount is greater than one and errorsField
 * has the option turned on
 */
function showFieldIcon(errorsDivId, errorCount){
	if(errorsDivId){
		var div = jq("#" + errorsDivId);
		var inputId = errorsDivId.replace("_errors_div", "");

		if(inputId){
			var input = jq("#" + inputId);
			var errorIcon = jq("#" + inputId + "_errorIcon");

			if (div.length > 0 && div.hasClass("addFieldIcon") && errorCount && errorIcon.length == 0) {
				if (input.length > 0) {
					input.after("<img id='"+ inputId +"_errorIcon' alt='error' src='/kr-dev/kr/static/images/errormark.gif'>");
				}
				else {
					// try for radios and checkboxes
					input = jq("#" + errorDivId.replace("errors_div", "attribute1"));
					if (input.length > 0) {
						input.after("<img id='"+ inputId +"_errorIcon' alt='error' src='/kr-dev/kr/static/images/errormark.gif'>");
					}
				}
			}
			else if(div.length > 0 && div.hasClass("addFieldIcon") && errorCount == 0){
				if(errorIcon.length > 0){
					errorIcon.remove();
				}
			}
	    }
	}
}

/**
 * Adds the icon that indicates the contents of a field have changed from the compared value (for instance the new side
 * on maintenance documents) to the field markers span
 *
 * @param fieldId - id for the field the icon should be added to
 */
function showChangeIcon(fieldId) {
    var fieldMarkerSpan = jq("#" + fieldId + "_markers");
    var fieldIcon = jq("#" + fieldId + "_changeIcon");

    if (fieldMarkerSpan.length > 0 && fieldIcon.length == 0) {
       fieldMarkerSpan.append("<img id='"+ fieldId +"_changeIcon' alt='change' src='/kr-dev/krad/images/asterisk_orange.png'>");
    }
}

/**
 * Add icon to a group header that indicates the data for the group has changed
 *
 * @param headerFieldId - id for the header field the icon should be added to
 */
function showChangeIconOnHeader(headerFieldId) {
    var headerSpan = jq("#" + headerFieldId + "_header");
    var headerIcon = jq("#" + headerFieldId + "_changeIcon");

    if (headerSpan.length > 0 && headerIcon.length == 0) {
       headerSpan.append("<img id='"+ headerFieldId +"_changeIcon' alt='change' src='/kr-dev/krad/images/asterisk_orange.png'>");
    }
}

// Applies the watermark to the input with the id specified
function createWatermark(id, watermark){
	jq("#" + id).watermark(watermark);
}

/**
 * If the content is an incident report view, replaces the current view with the incident report and
 * returns true, otherwise returns false
 *
 * @param content
 * @returns {Boolean} true if there was an incident, false otherwise
 */
function handleIncidentReport(content){
	var viewId = jq("#viewId", content);
	if(viewId.length && viewId.val() === "Incident-Report"){
		jq('#view_div').replaceWith(jq('#view_div', content));

		runHiddenScripts("view_div");
		return true;
	}
	else{
		return false;
	}
}