// Toggles a tab to show / hide and changes the source image to properly reflect this
// change. Returns false to avoid post. Example usage:
// onclick="javascript: return toggleTab(document, this, ${currentTabIndex}) }
function toggleTab(doc, tabIndex) {
	if (doc.forms[0].elements['tabState[' + tabIndex + '].open'].value == 'false') {
		// replaced 'block' with '' to make budgetExpensesRow.tag happy.
		doc.getElementById('tab-' + tabIndex + '-div').style.display = '';
		doc.forms[0].elements['tabState[' + tabIndex + '].open'].value = 'true';
		doc.getElementById('tab-' + tabIndex + '-imageToggle').src = 'images/tinybutton-hide.gif';
	} else {
		doc.getElementById('tab-' + tabIndex + '-div').style.display = 'none';
		doc.forms[0].elements['tabState[' + tabIndex + '].open'].value = 'false';
		doc.getElementById('tab-' + tabIndex + '-imageToggle').src = 'images/tinybutton-show.gif';
	}
	return false;
}

function expandAllTab(doc, tabStatesSize) {
	for (var tabIndex = 0; tabIndex <= tabStatesSize.value; tabIndex++) {
		// replaced 'block' with '' to make budgetExpensesRow.tag happy.
		doc.getElementById('tab-' + tabIndex + '-div').style.display = '';
		doc.forms[0].elements['tabState[' + tabIndex + '].open'].value = 'true';
		doc.getElementById('tab-' + tabIndex + '-imageToggle').src = 'images/tinybutton-hide.gif';
	}

	return false;
}

function collapseAllTab(doc, tabStatesSize) {
	for (var tabIndex = 0; tabIndex <= tabStatesSize.value; tabIndex++) {
		doc.getElementById('tab-' + tabIndex + '-div').style.display = 'none';
		doc.forms[0].elements['tabState[' + tabIndex + '].open'].value = 'false';
		doc.getElementById('tab-' + tabIndex + '-imageToggle').src = 'images/tinybutton-show.gif';
	}

	return false;
}

var formHasAlreadyBeenSubmitted = false;
var excludeSubmitRestriction = false;
function hasFormAlreadyBeenSubmitted() {
    if (formHasAlreadyBeenSubmitted && !excludeSubmitRestriction) {
       alert("Page already being processed by the server.");
       return false;
    } else {
       formHasAlreadyBeenSubmitted = true;
       return true;
    }
    excludeSubmitRestriction = false;
}

function submitForm() {
    document.forms[0].submit();
}
	
/* script to prevent the return key from submitting a form unless the user is on a button or on a link. fix for KULFDBCK-555 */ 
function isReturnKeyAllowed(buttonPrefix , event) {
	/* use IE naming first then firefox. */
	var initiator = event.srcElement ? event.srcElement.name : event.target.name;
	var key = event.keyCode;
	/* initiator is undefined check is to prevent return from doing anything if not in a form field since the initiator is undefined */
	/* 13 is return key code */
	/* length &gt; 0 check is to allow user to hit return on links */
	if( initiator == undefined || ( initiator.indexOf(buttonPrefix) != 0 && key == 13 && initiator.length > 0) ) {
	  // disallow enter key from fields that dont match prefix.
	  return false;
	} else {
	  return;
	}
}

//The following javascript is intended to resize the route log iframe
// to stay at an appropriate height based on the size of the documents
// contents contained in the iframe.
//  NOTE: this will only work when the domain serving the content of kuali
//         is the same as the domain serving the content of workflow.
var routeLogResizeTimer = ""; // holds the timer for the  route log iframe resizer
var currentHeight = 500; // holds the current height of the iframe
var safari = navigator.userAgent.toLowerCase().indexOf('safari');

function setRouteLogIframeDimensions() {
  var routeLogFrame = document.getElementById("routeLogIFrame");
  var routeLogFrameWin = window.frames["routeLogIFrame"];
  var frameDocHeight = 0;
  try {
    frameDocHeight = routeLogFrameWin.document.height;
  } catch ( e ) {
    // unable to set due to cross-domain scripting
    frameDocHeight = 0;
  }
  if ( frameDocHeight > 0 ) {
	  if (routeLogFrame && routeLogFrameWin) {
	  	
	    if ((Math.abs(frameDocHeight - currentHeight)) > 20 ) {
	      if (safari > -1) {
	        if ((Math.abs(docHt - currentHeight)) > 59 ) {
	          currentHeight = frameDocHeight;
	          routeLogFrame.style.height = currentHeight + 30 + "px";
	        }
	      } else {    
	        currentHeight = frameDocHeight;
	        routeLogFrame.style.height = currentHeight + 30 + "px";
	      }
	    }
	  
	    if (routeLogResizeTimer == "" ) {
	      routeLogResizeTimer = setInterval("resizeTheRouteLogFrame()",300);
	    }
	  }
  }
}

function resizeTheRouteLogFrame() {
  setRouteLogIframeDimensions();
}
