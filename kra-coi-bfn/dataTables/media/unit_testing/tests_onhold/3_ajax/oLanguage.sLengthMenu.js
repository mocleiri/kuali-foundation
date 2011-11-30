// DATA_TEMPLATE: empty_table
oTest.fnStart( "oLanguage.sLengthMenu" );

$(document).ready( function () {
	/* Check the default */
	var oTable = $('#example').dataTable( {
		"sAjaxSource": "../../../examples/examples_support/json_source.txt"
	} );
	var oSettings = oTable.fnSettings();
	
	oTest.fnWaitTest( 
		"Menu language is 'Show _MENU_ entries' by default",
		null,
		function () { return oSettings.oLanguage.sLengthMenu == "Show _MENU_ entries"; }
	);
	
	oTest.fnTest(
		"_MENU_ macro is replaced by select menu in DOM",
		null,
		function () { return $('select', oSettings.anFeatures.l).length == 1 }
	);
	
	oTest.fnTest(
		"Default is put into DOM",
		null,
		function () {
			var anChildren = oSettings.anFeatures.l.childNodes;
			var bReturn =
				anChildren[0].nodeValue == "Show " &&
				anChildren[2].nodeValue == " entries";
			return bReturn;
		}
	);
	
	
	oTest.fnWaitTest( 
		"Menu length language can be defined - no _MENU_ macro",
		function () {
			oSession.fnRestore();
			oTable = $('#example').dataTable( {
				"sAjaxSource": "../../../examples/examples_support/json_source.txt",
				"oLanguage": {
					"sLengthMenu": "unit test"
				}
			} );
			oSettings = oTable.fnSettings();
		},
		function () { return oSettings.oLanguage.sLengthMenu == "unit test"; }
	);
	
	oTest.fnTest( 
		"Menu length language definition is in the DOM",
		null,
		function () {
			var anChildren = oSettings.anFeatures.l.childNodes;
			return anChildren[0].nodeValue == "unit test";
		}
	);
	
	
	oTest.fnWaitTest( 
		"Menu length language can be defined - with _MENU_ macro",
		function () {
			oSession.fnRestore();
			oTable = $('#example').dataTable( {
				"sAjaxSource": "../../../examples/examples_support/json_source.txt",
				"oLanguage": {
					"sLengthMenu": "unit _MENU_ test"
				}
			} );
			oSettings = oTable.fnSettings();
		},
		function () {
			var anChildren = oSettings.anFeatures.l.childNodes;
			var bReturn =
				anChildren[0].nodeValue == "unit " &&
				anChildren[2].nodeValue == " test";
			return bReturn;
		}
	);
	
	
	oTest.fnWaitTest( 
		"Only the _MENU_ macro",
		function () {
			oSession.fnRestore();
			oTable = $('#example').dataTable( {
				"sAjaxSource": "../../../examples/examples_support/json_source.txt",
				"oLanguage": {
					"sLengthMenu": "_MENU_"
				}
			} );
			oSettings = oTable.fnSettings();
		},
		function () {
			var anChildren = oSettings.anFeatures.l.childNodes;
			var bReturn =
				anChildren.length == 1 &&
				$('select', oSettings.anFeatures.l).length == 1;
			return bReturn;
		}
	);
	
	
	oTest.fnComplete();
} );