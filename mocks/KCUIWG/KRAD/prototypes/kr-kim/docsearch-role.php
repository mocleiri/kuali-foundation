<?php
$section = '';
$page = 'actionlist';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css">
.container.uif-viewHeader-contentWrapper .uif-viewHeader, .uif-actionBar {
	margin-left: -8px;
}
.uif-actionBar {
	font-size: 13px;
	padding-bottom: 8px;
	padding-left: 0px;
	padding-top: 6px;
	background: white;
	margin-top: 0px;
}
.dl-horizontal dd {
	margin-left: 180px;
	clear: inherit;
}
.input-group .form-control {
	width: 375px;
	margin-bottom: 0;
}
</style>
<link rel="stylesheet" type="text/css" href="../../krad/scripts/datatables/DT_bootstrapss.css">
<link rel="stylesheet" href="../../krad/scripts/jquery-ui.css">
<script src="../../krad/scripts/jquery-1.10.1.min.js"></script>
<script src="../../krad/scripts/jquery-ui.js"></script>
</head>
<body id="Uif-Application" style="padding-bottom: 48px;">

<!-- APPLICATION HEADER -->

<?php include ('includes/uif-applicationHeader.php') ?>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER --> 
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            
            <div id="Uif-BreadcrumbUpdate" style="display:none;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left:;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"><span class="uif-headerText-span">Role Document Search </span></h2>
                    </div>
                </header>
                <hr>
                <script>
  $(function() {
    var availableTags = [

"Name",
"Award Budget Document",
"Award Document",
"Budget Document",
"Coi Disclosure Document",
"Committee Document",
"Common Committee Document",
"Iacuc Protocol Document",
"Iacuc Protocol Online Review Document",
"Institutional Proposal Document",
"Kc Maintenance Document",
"Negotiation Document",
"Person Mass Change Document",
"Proposal Development Document",
"Protocol Document",
"Protocol Online Review Document",
"Sub Award Document",
"Time And Money Document",
"Award Transaction Type Maintenance Document",
"Budget Columns To Alter Maintenance Document",
"Carrier Type Maintenance Document",
"Exemption Type Maintenance Document",
"Kc Awards Maintenance Document",
"Kc Compliance Maintenance Document",
"Kc Miscellaneous Maintenance Document",
"Kc Negotiations Maintenance Document",
"Kc Person Extended Attributes Maintenance Document",
"Kc Proposals Maintenance Document",
"Kc Shared Maintenance Document",
"Person Appointment Maintenance Document",
"Person Degree Maintenance Document",
"Person Editable Field Maintenance Document",
"Prop Location Maintenance Document",
"Proposal Columns To Alter Maintenance Document",
"Proposal Log Maintenance Document",
"Proposal Response Maintenance Document",
"Proposal Role Template Maintenance Document",
"Prop Per Doc Type Maintenance Document",
"Risk Level Maintenance Document",
"Sponsor Term Maintenance Document",
"Sponsor Term Type Maintenance Document",
"Tbn Person Maintenance Document",
"Unit Maintenance Document",
"Abstract Type Maintenance Document",
"Account Type Maintenance Document",
"Activity Type Maintenance Document",
"Kc Affiliation Type Maintenance Document",
"Appointment Type Maintenance Document",
"Arg Value Lookup Maintenance Document",
"Award Attachment Type Maintenance Document",
"Award Basis Of Payment Maintenance Document",
"Award Method Of Payment Maintenance Document",
"Award Status Maintenance Document",
"Award Template Comment Maintenance Document",
"Award Template Contact Maintenance Document",
"Award Template Maintenance Document",
"Award Template Report Term Maintenance Document",
"Award Template Report Term Recipient Maintenance Document",
"Award Template Term Maintenance Document",
"Award Type Maintenance Document",
"Batch Correspondence Detail Maintenance Document",
"Batch Correspondence Maintenance Document",
"Budget Category Maintenance Document",
"Budget Category Mapping Maintenance Document",
"Budget Category Map Maintenance Document",
"Budget Category Type Maintenance Document",
"Budget Status Maintenance Document",
"C F D A Maintenance Document",
"Citizenship Type Maintenance Document",
"Closeout Report Type Maintenance Document",
"Closeout Type Maintenance Document",
"Coeus Module Maintenance Document",
"Coeus Sub Module Maintenance Document",
"Coi Attachment Type Maintenance Document",
"Coi Committee Role Type Maintenance Document",
"Coi Disclosure Event Type Maintenance Document",
"Coi Disclosure Status Maintenance Document",
"Coi Disposition Status Maintenance Document",
"Coi Note Type Maintenance Document",
"Coi Recomended Action Type Maintenance Document",
"Coi Reviewer Maintenance Document",
"Coi Review Status Maintenance Document",
"Coi Review Type Maintenance Document",
"Coi Status Maintenance Document",
"Comment Type Maintenance Document",
"Committee Decision Motion Type Maintenance Document",
"Committee Membership Type Maintenance Document",
"Committee Type Maintenance Document",
"Contact Type Maintenance Document",
"Correspondent Type Maintenance Document",
"Cost Element Maintenance Document",
"Cost Share Type Maintenance Document",
"Custom Attribute Document Maintenance Document",
"Custom Attribute Maintenance Document",
"Cust Report Details Maintenance Document",
"Cust Report Type Maintenance Document",
"Cust Rpt Default Parms Maintenance Document",
"Cust Rpt Type Document Maintenance Document",
"Deadline Type Maintenance Document",
"Degree Type Maintenance Document",
"Distribution Maintenance Document",
"Exempt Studies Check List Maintenance Document",
"Expedited Review Check List Maintenance Document",
"Fanda Rate Type Maintenance Document",
"Financial Indirect Cost Recovery Type Code Maintenance Document",
"Financial Object Code Mapping Maintenance Document",
"Fin Entities Data Group Maintenance Document",
"Fin Entities Data Matrix Maintenance Document",
"Fin Int Entity Rel Type Maintenance Document",
"Fin Int Entity Status Maintenance Document",
"Formulated Type Maintenance Document",
"Frequency Base Maintenance Document",
"Frequency Maintenance Document",
"Funding Source Type Maintenance Document",
"Iacuc Alternate Search Database Maintenance Document",
"Iacuc Exception Category Maintenance Document",
"Iacuc Location Name Maintenance Document",
"Iacuc Location Type Maintenance Document",
"Iacuc Pain Category Maintenance Document",
"Iacuc Person Training Maintenance Document",
"Iacuc Procedure Category Custom Data Maintenance Document",
"Iacuc Procedure Category Maintenance Document",
"Iacuc Procedure Maintenance Document",
"Iacuc Protocol Action Type Maintenance Document",
"Iacuc Protocol Attachment Group Maintenance Document",
"Iacuc Protocol Attachment Status Maintenance Document",
"Iacuc Protocol Attachment Type Group Maintenance Document",
"Iacuc Protocol Attachment Type Maintenance Document",
"Document Type Document",
"Routing Rule Document",
"Kuali Notification",
"Send Notification Request",
"Namespace Maintenance Document",
"Parameter Type Maintenance Document",
"Parameter Detail Type Maintenance Document",
"Parameter Maintenance Document",
"Kuali Document",
"Rice Document",
"Routing Rule Delegation Maintenance Document",
"Campus Maintenance Document",
"Campus Type Maintenance Document",
"Country Maintenance Document",
"County Maintenance Document",
"Postal Code Maintenance Document",
"State Maintenance Document",
"Identity Management Review Responsibility Maintenance Document",
"Identity Management Generic Permission Maintenance Document",
"Kuali Document",
"K C",
"Valid Proto Sub Rev Type Maintenance Document",
"Valid Proto Sub Type Qual Maintenance Document",
"Valid Rates Maintenance Document",
"Valid Special Review Approval Maintenance Document",
"Watermark Maintenance Document",
"Ynq Maintenance Document",
"Identity Management Document",
"Identity Management Role Document",
"Identity Management Group Document",
"Identity Management Person Document",
"Agenda Editor Maintenance Document",
"Agenda Maintenance Document",
"Context Maintenance Document",
"People Flow Maintenance Document",
"Rule Maintenance Document",
"Term Maintenance Document",
"Term Specification Maintenance Document",
"Iacuc Protocol Correspondence Template Maintenance Document",
"Iacuc Protocol Correspondence Type Maintenance Document",
"Iacuc Protocol Online Review Determin Recommend Maintenance Document",
"Iacuc Protocol Online Review Status Maintenance Document",
"Iacuc Protocol Person Role Maintenance Document",
"Iacuc Protocol Project Type Maintenance Document",
"Iacuc Protocol Reference Type Maintenance Document",
"Iacuc Protocol Review Type Maintenance Document",
"Iacuc Protocol Submission Qualifier Type Maintenance Document",
"Iacuc Protocol Submission Type Maintenance Document",
"Iacuc Protocol Type Maintenance Document",
"Iacuc Research Areas Maintenance Document",
"Iacuc Species Count Type Maintenance Document",
"Iacuc Species Maintenance Document",
"Iacuc Valid Proto Sub Rev Type Maintenance Document",
"Iacuc Valid Proto Sub Type Qual Maintenance Document",
"Institute La Rate Maintenance Document",
"Institute Rate Maintenance Document",
"Intellectual Property Review Activity Type Maintenance Document",
"Intellectual Property Review Maintenance Document",
"Intellectual Property Review Requirement Type Maintenance Document",
"Intellectual Property Review Result Type Maintenance Document",
"Investigator Credit Type Maintenance Document",
"Job Code Maintenance Document",
"Kc Krms Term Function Maintenance Document",
"Kc Krms Term Fun Param Spec Maintenance Document",
"Mail By Maintenance Document",
"Mail Type Maintenance Document",
"Membership Role Maintenance Document",
"Message Of The Day Maintenance Document",
"Minute Entry Type Maintenance Document",
"Narrative Status Maintenance Document",
"Narrative Type Maintenance Document",
"Negotiation Activity Type Maintenance Document",
"Negotiation Agreement Type Maintenance Document",
"Negotiation Association Type Maintenance Document",
"Negotiation Location Maintenance Document",
"Negotiation Status Maintenance Document",
"Notice Of Opportunity Maintenance Document",
"Notification Module Role Maintenance Document",
"Notification Type Maintenance Document",
"Nsf Code Maintenance Document",
"Organization Correspondent Maintenance Document",
"Organization Maintenance Document",
"Organization Type List Maintenance Document",
"Organization Type Maintenance Document",
"Participant Type Maintenance Document",
"Person Maintenance Document",
"Person Signature Maintenance Document",
"Person Training Maintenance Document",
"Post Submission Status Maintenance Document",
"Proposal Location Type Maintenance Document",
"Proposal Log Status Maintenance Document",
"Proposal Log Type Maintenance Document",
"Proposal Status Maintenance Document",
"Proposal Type Maintenance Document",
"Protocol Action Type Maintenance Document",
"Protocol Attachment Group Maintenance Document",
"Protocol Attachment Status Maintenance Document",
"Protocol Attachment Type Group Maintenance Document",
"Protocol Attachment Type Maintenance Document",
"Protocol Contingency Maintenance Document",
"Protocol Correspondence Template Maintenance Document",
"Protocol Correspondence Type Maintenance Document",
"Protocol Notification Template Maintenance Document",
"Protocol Online Review Determin Recommend Maintenance Document",
"Protocol Online Review Status Maintenance Document",
"Protocol Organization Type Maintenance Document",
"Protocol Person Role Maintenance Document",
"Protocol Reference Type Maintenance Document",
"Protocol Reviewer Type Maintenance Document",
"Protocol Review Type Maintenance Document",
"Protocol Status Maintenance Document",
"Protocol Submission Qualifier Type Maintenance Document",
"Protocol Submission Status Maintenance Document",
"Protocol Submission Type Maintenance Document",
"Protocol Type Maintenance Document",
"Question Category Maintenance Document",
"Question Maintenance Document",
"Questionnaire Maintenance Document",
"Questionnaire Questions Maintenance Document",
"Questionnaire Usage Maintenance Document",
"Question Type Maintenance Document",
"Rate Class Base Exclusion Maintenance Document",
"Rate Class Base Inclusion Maintenance Document",
"Rate Class Maintenance Document",
"Rate Class Type Maintenance Document",
"Rate Type Maintenance Document",
"Report Class Maintenance Document",
"Report Maintenance Document",
"Report Status Maintenance Document",
"Research Areas Maintenance Document",
"Rolodex Maintenance Document",
"S2s Opp Form Questionnaire Maintenance Document",
"S2s Provider Maintenance Document",
"S2s Revision Type Maintenance Document",
"S2s Submission Type Maintenance Document",
"Schedule Act Item Type Maintenance Document",
"Schedule Status Maintenance Document",
"School Code Maintenance Document",
"Science Keyword Maintenance Document",
"Special Review Approval Type Maintenance Document",
"Special Review Type Maintenance Document",
"Special Review Usage Maintenance Document",
"Sponsor Forms Maintenance Document",
"Sponsor Form Template Maintenance Document",
"Sponsor Hierarchy Maintenance Document",
"Sponsor Maintenance Document",
"Sponsor Type Maintenance Document",
"Sub Award Approval Type Maintenance Document",
"Sub Award Forms Maintenance Document",
"Sub Award Invoice Maintenance Document",
"Sub Award Status Maintenance Document",
"Training Maintenance Document",
"Training Stipend Rate Maintenance Document",
"Unit Administrator Maintenance Document",
"Unit Administrator Type Maintenance Document",
"Unit Correspondent Maintenance Document",
"Unit Formulated Cost Maintenance Document",
"Valid Award Basis Payment Maintenance Document",
"Valid Basis Method Payment Maintenance Document",
"Valid Calc Type Maintenance Document",
"Valid Ce Job Code Maintenance Document",
"Valid Ce Rate Type Maintenance Document",
"Valid Class Report Freq Maintenance Document",
"Valid Frequency Base Maintenance Document",
"Valid Narr Forms Maintenance Document",
"Valid Protocol Action Correspondence Maintenance Document",
"Valid Protocol Action Action Maintenance Document",
"Employee Document",
"Role Document",


    ];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  });
  </script> 
                <script>
  $(function() {
    var availableTags2 = [
"Blanket Approver",
"Name",
"Award Budget Approver",
"Award Approver",
"Budget Approver",
"Coi Disclosure Approver",
"Committee Approver",
"Common Committee Approver",
"Iacuc Protocol Approver",


    ];
    $( "#tags2" ).autocomplete({
      source: availableTags2
    });
  });
  </script>
                <div class="form-horizontal" role="form">
                    <div class="row ">
                        <div class="col-md-9">
                        
                        
                        
                        
                        
                        <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Document Type</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">Role </p>
                        </div>
                    </div>
                    
                    
                    
                    
                    
                    
                    
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Role ID</label>
                                <div class="col-md-9">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="tags2" name="test-username-2" value="" style="background-image:none" required>
                                        <div class="uif-form-control-helpers input-group-addon"> 
                                            <!-- Lookups, datepickers, etc. --> 
                                            <a href="#"  data-role="Action" data-toggle="modal" data-target="" data-submit_data="" data-dismiss="modal"> <span aria-hidden="true" class="icon-search"></span></a> 
                                            <!-- // --> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            
                            
                            
                                 
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Role Namespace</label>
                                <div class="col-md-9">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="tags2" name="test-username-2" value="" style="background-image:none" required>
                                        <div class="uif-form-control-helpers input-group-addon"> 
                                            <!-- Lookups, datepickers, etc. --> 
                                            <a href="#"  data-role="Action" data-toggle="modal" data-target="" data-submit_data="" data-dismiss="modal"> <span aria-hidden="true" class="icon-search"></span></a> 
                                            <!-- // --> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                                                 
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Role Name</label>
                                <div class="col-md-9">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="tags2" name="test-username-2" value="" style="background-image:none" required>
                                        <div class="uif-form-control-helpers input-group-addon"> 
                                            <!-- Lookups, datepickers, etc. --> 
                                            <a href="#"  data-role="Action" data-toggle="modal" data-target="" data-submit_data="" data-dismiss="modal"> <span aria-hidden="true" class="icon-search"></span></a> 
                                            <!-- // --> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            
                            
                            
                       
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9"> <a id="u15ecnro" class="btn btn-default uif-action" tabindex="0" data-role="Action" data-toggle="modal" data-target="" data-submit_data="" data-dismiss=""> Cancel </a> <a href="docsearch-role-results.php" id="u15ecnro" class="btn btn-primary uif-action" data-role="Action" data-toggle="" data-target="" data-submit_data="" data-dismiss="modal"> Search </a> </div>
                            </div>
                        </div>
                    
                    </div>
                   
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER --> 
        
        <!-- DIALOGS/Placeholders -->
        <div id="Uif-Dialogs"></div>
    </div>
    <span id="formInfo">
    <input type="hidden" name="viewId" value="LabsProposal">
    <input type="hidden" name="formKey" value="2e468a13-a495-44cc-acd7-aac6b2ed97a0">
    <input type="hidden" name="requestedFormKey" value="2e468a13-a495-44cc-acd7-aac6b2ed97a0">
    <input type="hidden" name="sessionId" value="CAFCAB4387CB97D8567359A8C37D7712">
    <input type="hidden" name="flowKey" value="">
    <input type="hidden" name="view.applyDirtyCheck" value="true">
    <input type="hidden" name="dirtyForm" value="false">
    <input type="hidden" name="renderedInLightBox" value="false">
    <input type="hidden" name="view.singlePageView" value="true">
    <input type="hidden" name="view.disableBrowserCache" value="true">
    </span><span id="formComplete"></span>
</form>

<!-- MODALS -->
<?php include ('includes/modal-routelog.php') ?>
<?php include ('includes/modal-lookup.php') ?>
<?php include ('includes/modal-lookup-results.php') ?>


<!-- FOOTER SCRIPTS --> 

<!-- Scripts, ideally we'd load these in the footer and not use in-line scripting --> 
<script src="../../krad/scripts/jquery-1.10.1.min.js"></script> 
<script src="../../krad/scripts/jquery-migrate-1.2.1.min.js"></script> 
<!--[if lt IE 9]><script src="bootstrap/js/html5shiv.js"></script><![endif]--> 

<script type="text/javascript" src="../../krad/scripts/datatables/jquery.dataTables.js"></script> 
<script type="text/javascript" src="../../krad/scripts/datatables/DT_bootstrap.js"></script> 
<script type="text/javascript" src="../../themes/bootstrap/scripts/bootstrap.js"></script> 
<script type="text/javascript" src="../../plugins/scrollto/jquery.scrollTo-1.4.6.js"></script> 
<script type="text/javascript" src="../../plugins/jqform/jquery.form-3.31.0.js"></script> 
<script type="text/javascript" src="../../plugins/globalize/globalize.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.navigationMenu.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.tabMenu.js"></script> 
<!--<script type="text/javascript" src="../../plugins/jgrowl/jquery.jgrowl.js"></script> --> 
<script type="text/javascript" src="../../plugins/textpopout/krad.textareaPopout.js"></script> 
<script type="text/javascript" src="../../plugins/validate/additional_validations.js"></script> 
<script type="text/javascript" src="../../plugins/cookie/jquery.cookie.js"></script> 
<script type="text/javascript" src="../../plugins/watermark/jquery.watermark.js"></script> 
<script type="text/javascript" src="../../plugins/countdown/jquery.countdown.js"></script> 
<!--<script type="text/javascript" src="../../plugins/blockUI/jquery.blockUI.js"></script> --> 
<script type="text/javascript" src="../../plugins/color/jquery.color.js"></script> 
<script type="text/javascript" src="../../plugins/json/jquery.json-2.2.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/jquery.dataTables.rowGrouping.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/TableTools.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/ZeroClipboard.js"></script> 
<script type="text/javascript" src="../../plugins/jstree/jquery.jstree.js"></script> 
<script type="text/javascript" src="../../plugins/jqueryUI/jquery.ui.autocomplete.html.js"></script> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.js"></script> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.pack.js"></script> 
<script type="text/javascript" src="../../plugins/prettify/prettify.js"></script> 
<script type="text/javascript" src="../../plugins/easydrag/jquery.easydrag.js"></script> 
<script type="text/javascript" src="../../plugins/tooltip/jquery.bubblepopup.v2.3.1.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.variables.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.utility.js"></script> 
<!--<script type="text/javascript" src="../../krad/scripts/krad.initialize.js"></script> --> 
<script type="text/javascript" src="../../krad/scripts/krad.request.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.response.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.actions.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.dirty.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.lookup.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.message.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.session.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.url.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.validate.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.widget.js"></script> 
<script type="text/javascript" src="../../plugins/easing/jquery.easing-1.3.pack.js"></script> 
<script type="text/javascript" src="../../themes/kboot/scripts/less-1.5.0.min.js"></script> 
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</body>
</html>