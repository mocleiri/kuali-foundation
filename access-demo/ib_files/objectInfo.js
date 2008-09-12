var divSuffix = ".div";
var accountNumberSuffix = ".accountNumber";
var accountNameSuffix = ".account.accountName";
var subAccountNumberSuffix = ".subAccountNumber";
var subAccountNameSuffix = ".subAccount.subAccountName";
var chartCodeSuffix = ".chartOfAccountsCode";
var objectCodeSuffix = ".financialObjectCode";
var objectCodeNameSuffix = ".objectCode.financialObjectCodeName";
var subObjectCodeSuffix = ".financialSubObjectCode";
var subObjectCodeNameSuffix = ".subObjectCode.financialSubObjectCodeName";

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };

function findElPrefix( elName ) {
    var prefixIndex = elName.lastIndexOf("." );
           
    if( prefixIndex < 0 ) {
        prefixIndex = elName.length;
    }
    return elName.substring( 0, prefixIndex );
}

function getElementValue( name ) {
    var el = kualiElements[name];
    el.value = el.value.toUpperCase().trim();

    return el.value;
}

function setElementValue( name, value ) {
    var el = kualiElements[name];
    if ( el ) {
    	if ( el.tagName == "INPUT" ) {
    		if ( el.type == "text" ) {
			    el.value = value;
    		} else if ( el.type == "checkbox" ) {
    			el.checked = (value == true);
    		} else if ( el.type == "radio" ) {
    			if ( el.length ) {
		    		for ( var i = 0; i < el.length; i++ ) {
		    			if ( el[i].value == value ) {
		    				el[i].checked = true;
		    				break;
						}
					}			
    			} else {
	    			el.checked = (value == el.value);
    			}
    		} else {
			    el.value = value;
    		}
    	} else if ( el.tagName == "SELECT" ) {
    		for ( var i = 0; i < el.options.length; i++ ) {
    			if ( el.options[i].value == value ) {
    				el.selectedIndex = i;
    				break;
				}
			}			
    	} else if ( el.tagName == "TEXTAREA" ) {
		    el.value = value;
		}
    }
}

var previousKualiElementValues = new Object();

function valueChanged( name ) {
    var previousValue = previousKualiElementValues[ name ];
    var currentValue = getElementValue( name );
    previousKualiElementValues[ name ] = currentValue;
    // undefined (i.e., null) is not considered changed, for the sake of newly added accounting lines
    return previousValue != null && previousValue != currentValue;
}

function loadKualiObjectInfo(methodToCall, param1, param2, param3, param4, param5, recipient) {
    var req = new XMLHttpRequest();
    if (req) {
	    req.onreadystatechange = function() {
	        if (req.readyState == 4 && req.status == 200) {
	        	setRecipientValue(recipient, req.responseText);
	        }
	    };
	    req.open('GET', 'loadObjectInfo.do?methodToCall='+methodToCall+'&param1='+param1+'&param2='+param2+'&param3='+param3+'&param4='+param4+'&param5='+param5);
    	req.send(null);
    }
}

function clearRecipients(recipientBase) {
	setRecipientValue(recipientBase, '');
}

function setRecipientValue(recipientBase, value) {
    var containerHidden = kualiElements[recipientBase];
    var containerDiv = document.getElementById(recipientBase + divSuffix);
    if (containerDiv) {
		if (value == '') {
			containerDiv.innerHTML = '&nbsp;';
		} else {
			containerDiv.innerHTML = value;
		}
	}
    if (containerHidden) {
		containerHidden.value = value;
	}
}

function loadChartInfo(coaCodeName, recipient) {
        var coaCode = getElementValue( coaCodeName );

	if (coaCode=='') {
		clearRecipients(recipient);
	} else {
		loadKualiObjectInfo('chart', coaCode, null, null, null, null, recipient);
	}
}

function loadAccountInfo(accountCodeField, recipient) {
        var elPrefix = findElPrefix( accountCodeField );
        var accountCode = getElementValue( accountCodeField );
        var coaCode = getElementValue( elPrefix + chartCodeSuffix );

    if (valueChanged( accountCodeField )) {
        clearRecipients( elPrefix + subAccountNumberSuffix );
        clearRecipients( elPrefix + subAccountNameSuffix );
        clearRecipients( elPrefix + subObjectCodeSuffix );
        clearRecipients( elPrefix + subObjectCodeNameSuffix );
    }
    if (accountCode=='') {
		clearRecipients(recipient);
	} else if (coaCode=='') {
		setRecipientValue(recipient, 'chart code is empty');
	} else {
		loadKualiObjectInfo('account', coaCode, accountCode, null, null, null, recipient);
	}
}

function loadSubAccountInfo( subAccountCodeField, recipient) {
        var elPrefix = findElPrefix( subAccountCodeField );
        var accountCode = getElementValue( elPrefix + accountNumberSuffix );
        var subAccountCode = getElementValue( subAccountCodeField );
        var coaCode = getElementValue( elPrefix + chartCodeSuffix );

	if (subAccountCode=='') {
		clearRecipients(recipient);
	} else if (coaCode=='') {
		setRecipientValue(recipient, 'chart code is empty');
	} else if (accountCode=='') {
		setRecipientValue(recipient, 'account number is empty');
	} else {
		loadKualiObjectInfo('subAccount', coaCode, accountCode, subAccountCode, null, null, recipient);
	}
}

function loadObjectInfo(fiscalYear, objectTypeRecipient, objectTypeCodeRecipient, objectCodeName, recipient) {
    var elPrefix = findElPrefix( objectCodeName );
    var objectCode = getElementValue( objectCodeName );
    var coaCode = getElementValue( elPrefix + chartCodeSuffix );

    if (valueChanged( objectCodeName )) {
        clearRecipients( elPrefix + subObjectCodeSuffix );
        clearRecipients( elPrefix + subObjectCodeNameSuffix );
    }
	if (objectCode=='') {
		clearRecipients(recipient);
	} else if (coaCode=='') {
		setRecipientValue(recipient, 'chart code is empty');
	} else if (fiscalYear=='') {
		setRecipientValue(recipient, 'fiscal year is missing');
	} else {
		loadKualiObjectInfo('object', fiscalYear, coaCode, objectCode, null, null, recipient);
		loadKualiObjectInfo('objectTypeCodeByObject', fiscalYear, coaCode, objectCode, null, null, objectTypeCodeRecipient);
		loadKualiObjectInfo('objectTypeByObject', fiscalYear, coaCode, objectCode, null, null, objectTypeRecipient);
	}
}

function loadSubObjectInfo(fiscalYear, subObjectCodeName, recipient) {
        var elPrefix = findElPrefix( subObjectCodeName );
        var accountCode = getElementValue( elPrefix + accountNumberSuffix );
        var objectCode = getElementValue( elPrefix + objectCodeSuffix );
        var subObjectCode = getElementValue( subObjectCodeName );
        var coaCode = getElementValue( elPrefix + chartCodeSuffix );
        
	if (subObjectCode=='') {
		clearRecipients(recipient);
	} else if (coaCode=='') {
		setRecipientValue(recipient, 'chart code is empty');
	} else if (fiscalYear=='') {
		setRecipientValue(recipient, 'fiscal year is missing');
	} else if (objectCode=='') {
		setRecipientValue(recipient, 'object code is empty');
	} else {
		loadKualiObjectInfo('subObject', fiscalYear, coaCode, accountCode, objectCode, subObjectCode, recipient);
	}
}

function loadProjectInfo(projectCodeName, recipient) {
        var projectCode = getElementValue( projectCodeName );

	if (projectCode=='') {
		clearRecipients(recipient);
	} else {
		loadKualiObjectInfo('project', projectCode, null, null, null, null, recipient);
	}
}

function loadObjectTypeInfo(objectTypeCodeName, recipient) {
    var objectTypeCode = getElementValue( objectTypeCodeName );

    if (objectTypeCode=='') {
        clearRecipients(recipient);
    } else {
        loadKualiObjectInfo('objectType', objectTypeCode, null, null, null, null, recipient);
    }
}

function loadOriginationInfo(originationCodeName, recipient) {
    var originationCode = getElementValue(originationCodeName);

    if (originationCode == '') {
        clearRecipients(recipient);
    } else {
        loadKualiObjectInfo('originationCode', originationCode, null, null, null, null, recipient);
    }
}

function loadDocumentTypeInfo(documentTypeCodeName, recipient) {
    var documentTypeCode = getElementValue(documentTypeCodeName);

    if (documentTypeCode == '') {
        clearRecipients(recipient);
    } else {
        loadKualiObjectInfo('documentType', documentTypeCode, null, null, null, null, recipient);
    }
}

function loadOrganizationInfo(orgCodeName, recipient) {
        var elPrefix = findElPrefix( orgCodeName );
        var orgCode = getElementValue( orgCodeName );
        var coaCode = getElementValue( elPrefix + chartCodeSuffix );

	if (orgCode=='') {
		clearRecipients(recipient);
	} else if (coaCode=='') {
		setRecipientValue(recipient, 'chart code is empty');
	} else {
		loadKualiObjectInfo('organization', coaCode, orgCode, null, null, null, recipient);
	}
}

function loadUserInfo(userClass, userIdName, fieldName, recipient) {
	var userId = getElementValue( userIdName );
	
	if (userId == '') {
		clearRecipients(recipient);
	}
	else {
		loadKualiObjectWithCallback( function( responseText ) {
					setRecipientValue( recipient, responseText );
				}, "user", userClass, userId, fieldName, "", "", "" );
	}
}

function loadKualiObjectWithCallback( callbackFunction, methodToCall, param1, param2, param3, param4, param5 ) {
 var http = new HTTPClient();

  http.callback = callbackFunction;
  http.xmlResponse = false;
  http.makeRequest( 'loadObjectInfo.do?methodToCall='+methodToCall+'&param1='+param1+'&param2='+param2+'&param3='+param3+'&param4='+param4+'&param5='+param5 );  
}

function HTTPClient() {};

HTTPClient.prototype = {
  xmlhttp: null,
  callback: null,

  makeRequest: function(url) {

    this.xmlhttp = getHTTPObject();

    this.xmlhttp.open( "GET", url, true );

    // Have to assign "this" to a variable
    var self = this;

    // Assign a closure to the onreadystatechange callback
    this.xmlhttp.onreadystatechange = function() {
      if (self.xmlhttp.readyState == 4) {
        if (self.xmlhttp.status == 200) {
	        self.callback( self.xmlhttp.responseText, self );
        }
      }
    }
    this.xmlhttp.send(null);
  }
}

function getHTTPObject() {
  var xmlhttp;
  /*@cc_on
  @if (@_jscript_version >= 5)
    try {
      xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (E) {
        xmlhttp = false;
      }
    }
  @else
  xmlhttp = false;
  @end @*/
  if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
    try {
      xmlhttp = new XMLHttpRequest();
    } catch (e) {
      xmlhttp = false;
    }
  }
  return xmlhttp;
}

function evalCallback( jsStr ) {
  eval( jsStr );
}

