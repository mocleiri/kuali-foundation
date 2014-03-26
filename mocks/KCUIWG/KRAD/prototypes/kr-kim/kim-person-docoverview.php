<?php
$section = 'asdf';
$page = 'asdf';
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
≠ margin-top: 0px;
}
</style>
<!--<link href="../css/kradSampleApp.css" rel="stylesheet" type="text/css"/><link href="../css/labsProposal.css" rel="stylesheet" type="text/css"/>  -->
</head>
<body id="Uif-Application" style="padding-bottom: 48px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-reorder"></span></button>
                    <a class="navbar-brand" href="index.php">
                    <div class="logoBrand">
                        <h1><img id="u2elq10" src="http://ux.kuali.org/prototypes/rice/assets/img/logo.png" alt="" class="uif-image"></h1>
                    </div>
                    </a></div>
                <div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
                    <ul class="nav navbar-nav navbar-right uif-listLayout">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Main Menu </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 500px; right: -180px;">
                                <div class="row ">
                                    <div class="col-md-6">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText"> Workflow</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">User Preferences</a></li>
                                                <li><a  href="#" title="asdf">Quicklinks</a></li>
                                                <li><a  href="#" title="asdf">Routing Report</a></li>
                                                <li><a  href="#" title="asdf">Routing Rules</a></li>
                                                <li><a  href="#" title="asdf">Routing Rules Delegation</a></li>
                                                <li><a  href="#" title="asdf">Routing and Identity Management Document Type Hierarchy</a></li>
                                                <li><a  href="#" title="asdf">eDoc Lite</a></li>
                                                <li><a  href="#" title="asdf">People Flow</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                    <div class="col-md-6">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText">KRMS Rules</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Agenda Lookup</a></li>
                                                <li><a  href="#" title="asdf">Context Lookup</a></li>
                                                <li><a  href="#" title="asdf">Attribute Definition Lookup</a></li>
                                                <li><a  href="#" title="asdf">Term Lookup</a></li>
                                                <li><a  href="#" title="asdf">Term Specification Lookup</a></li>
                                                <li><a  href="#" title="asdf">Category Lookup</a></li>
                                            </ul>
                                            <h3 class="uif-headerText">Notifications</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Notification Search</a></li>
                                                <li><a  href="#" title="asdf">Channel Subscriptions</a></li>
                                                <li><a  href="#" title="asdf">Delivery Types</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Administration </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 700px; right: 0px;">
                                <div class="row ">
                                    <div class="col-md-4">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText"> Workflow</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Rule Attribute</a></li>
                                                <li><a  href="#" title="asdf">Rule Template</a></li>
                                                <li><a  href="#" title="asdf">XML Stylesheets</a></li>
                                                <li><a  href="#" title="asdf">XML Ingester</a></li>
                                                <li><a  href="#" title="asdf">Statistics</a></li>
                                                <li><a  href="#" title="asdf">Document Operation</a></li>
                                                <li><a  href="#" title="asdf">Document Type</a></li>
                                            </ul>
                                            <h3 class="uif-headerText"> Notification</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Send Simple Notification</a></li>
                                                <li><a  href="#" title="asdf">Send Event Notification</a></li>
                                                <li><a  href="#" title="asdf">Manage Content Types</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                    <div class="col-md-4">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText"> Identity</h3>
                                            <strong>Identity</strong>
                                            <ul class="uif-listLayout">
                                                <li><a  href="kim-person-docoverview.php" title="asdf">Person</a></li>
                                                <li><a  href="#" title="asdf">Group</a></li>
                                                <li><a  href="#" title="asdf">Role</a></li>
                                                <li><a  href="#" title="asdf">Permission</a></li>
                                                <li><a  href="#" title="asdf">Responsibility</a></li>
                                            </ul>
                                            <strong>Locations</strong>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Campus</a></li>
                                                <li><a  href="#" title="asdf">Country</a></li>
                                                <li><a  href="#" title="asdf">County</a></li>
                                                <li><a  href="#" title="asdf">Postal Code</a></li>
                                                <li><a  href="#" title="asdf">State</a></li>
                                            </ul>
                                            <strong>Reference</strong>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Address Type</a></li>
                                                <li><a  href="#" title="asdf">Affiliation Type</a></li>
                                                <li><a  href="#" title="asdf">Campus Type</a></li>
                                                <li><a  href="#" title="asdf">Citizenship Status</a></li>
                                                <li><a  href="#" title="asdf">Email Type</a></li>
                                                <li><a  href="#" title="asdf">Employment Status</a></li>
                                                <li><a  href="#" title="asdf">Employment Type</a></li>
                                                <li><a  href="#" title="asdf">Entity Type</a></li>
                                                <li><a  href="#" title="asdf">External Identifier Type</a></li>
                                                <li><a  href="#" title="asdf">Name Type</a></li>
                                                <li><a  href="#" title="asdf">Phone Type</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                    <div class="col-md-4">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText">Configuration</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Parameter</a></li>
                                                <li><a  href="#" title="asdf">Parameter Type</a></li>
                                                <li><a  href="#" title="asdf">Component</a></li>
                                                <li><a  href="#" title="asdf">Namespace</a></li>
                                                <li><a  href="#" title="asdf">Pessimistic Lock</a></li>
                                                <li><a  href="#" title="asdf">Configuration Viewer</a></li>
                                            </ul>
                                            <h3 class="uif-headerText">Service Bus</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Message Queue</a></li>
                                                <li><a  href="#" title="asdf">Thread Pool</a></li>
                                                <li><a  href="#" title="asdf">Service Registry</a></li>
                                                <li><a  href="#" title="asdf">Service Bus</a></li>
                                                <li><a  href="#" title="asdf">Quartz</a></li>
                                                <li><a  href="#" title="asdf">Security Management</a></li>
                                            </ul>
                                            <h3 class="uif-headerText">Miscellaneous</h3>
                                            <ul class="uif-listLayout">
                                                <li><a  href="#" title="asdf">Cache Admin</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> KRAD </a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div id="upils8b" class="uif-cssGridGroup toolbar">
            <div class="container">
                <div class="row ">
                    <div class="col-md-12">
                        <div id="u1i3m5yh" class="uif-listGroup" data-parent="upils8b">
                            <ul class="uif-listLayout nav pull-right">
                                <li class="pull-right"><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#actionlist" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}">Action List</a></li>
                                <li class="pull-right"><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#docsearch" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}">Doc Search</a></li>
                                <li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Backdoor Login <span class="caret"></span></a>
                                    <ul class="dropdown-menu uif-listLayout">
                                        <li><a href="#" class="uif-actionLink" id="u101xf6k" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Preferences </a></li>
                                        <li><a href="#" class="uif-actionLink" id="u101xf7f" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Logout </a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Backdoor info (here to inherit stickyness with the header, if set) --></header>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
        <header class="container uif-viewHeader-contentWrapper">
            <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal">
                <h1 class="uif-headerText">
                    <p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> Identity Management </p>
                    <span class="uif-headerText-span"> Edit Person </span><span class="uif-supportTitle-wrapper">
                    <div id="u1hgnm9q" class="uif-viewHeader-supportTitle" data-parent="ueqbqhn">Principle Name: <em>thrclark</em></div>
                    </span></h1>
                <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent="LabsProposal">
                    <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                        <label id="uj8x9wj" for="uauh5yk_span" class="uif-label" data-label_for="u1f206jn"> Doc Nbr: </label>
                        <p id="uauh5yk" class="uif-message"> 2743 </p>
                    </div>
                    <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Initiator">
                        <label id="ujre4xu" for="u7lh763_span" class="uif-label" data-label_for="u1f206ki"> Initiator: </label>
                        <p id="u7lh763" class="uif-message"> thrclark </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Status: </label>
                        <p id="u4ch8dm" class="uif-message"> In Progress </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Created: </label>
                        <p id="u4ch8dm" class="uif-message">03:28 AM 03/12/2014</p>
                    </div>
                    <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
                        <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                            <h4 class="uif-headerText"> Document Info </h4>
                            <table id="u98wduy" class="table table-condensed uif-table-fixed" role="presentation">
                                <tbody>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Value</td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                    </div>
                </div>
            </div>
            <div id="LabsProposal-DocActionBar" class="uif-actionBar uif-header-lowerGroup">
                <ul>
                    <li><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy Document</a></li>
                    <li><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-male"></span>Ad Hoc Recipients</a></li>
                    <li><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-magic"></span>Super User Actions</a></li>
                    <li><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#routelog" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-road"></span>Route Log</a></li>
                </ul>
            </div>
        </header>
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            <nav id="Uif-Navigation" style="position:absolute; display:none">
                <div id="LabsProposal-Menu" class="uif-menuNavigationGroup">
                    <div class="sidebar-collapse"><span class="icon-angle-left"></span></div>
                    <!-- NAVIGATION -->
                    <ul class="nav nav-list">
                        <li class="active"><a href="kim-person-docoverview.php" id="u79gei9" class="uif-navigationActionLink" ><span class="icon-file-alt"></span><span class="uif-innerText">Document Overview</span></a></li>
                        <li><a href="kim-person-personoverview.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-user"></span><span class="uif-innerText">Person Overview</span></a></li>
                        <li><a href="kim-person-contactinfo.php" id="u79gei9" class="uif-navigationActionLink" ><span class="icon-list-alt"></span><span class="uif-innerText">Contact Info</span></a></li>
                        <li><a href="kim-person-affiliations.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-puzzle"></span><span class="uif-innerText">Affiliations</span></a></li>
                        <li><a href="kim-person-memberships.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-group"></span><span class="uif-innerText">Memberships</span></a></li>
                    </ul>
                </div>
            </nav>
            <div id="Uif-BreadcrumbUpdate" style="display:none;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"><span class="uif-headerText-span"> Document Overview </span></h2>
                    </div>
                    <div id="uw4ggjs" class="uif-verticalBoxGroup uif-header-lowerGroup" data-parent="LabsProposal-Page">
                        <div id="u1ndzhxa" class="text-muted uif-boxLayoutVerticalItem clearfix" data-parent="uw4ggjs">
                            <p>* Indicates required fields</p>
                        </div>
                    </div>
                </header>
                <div id="u14jg6xp" class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix" data-parent="LabsProposal-Page">
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Description* </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <input id="u11k8c13_control" type="text" name="field1" value="" size="30" class="form-control input-sm uif-textControl required error" data-role="Control" data-control_for="u11k8c13" aria-required="true" aria-invalid="true">
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Org Doc Number </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <input id="u11k8c13_control" type="text" name="field1" value="" size="30" class="form-control input-sm uif-textControl required error" data-role="Control" data-control_for="u11k8c13" aria-required="true" aria-invalid="true">
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Explaination </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <textarea class="form-control" rows="4"></textarea>
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}"> Save and Submit </button>
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
        </div>
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
    <div class="jquerybubblepopup jquerybubblepopup-kr-error-cs" style="margin: 0px 0px 0px 395.5px; opacity: 0; top: 227px; left: 542px; position: absolute; display: none;" id="jquerybubblepopup-1393862100-0" data-for="u11k8c13_control">
        <table>
            <tbody>
                <tr>
                    <td class="jquerybubblepopup-top-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-left.png);"></td>
                    <td class="jquerybubblepopup-top-middle" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-middle.png);"></td>
                    <td class="jquerybubblepopup-top-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-right.png);"></td>
                </tr>
                <tr>
                    <td class="jquerybubblepopup-middle-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-left.png);"></td>
                    <td class="jquerybubblepopup-innerHtml"><div class="uif-clientMessageItems uif-clientErrorDiv">
                            <ul>
                                <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                            </ul>
                        </div></td>
                    <td class="jquerybubblepopup-middle-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-right.png);"></td>
                </tr>
                <tr>
                    <td class="jquerybubblepopup-bottom-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-left.png);"></td>
                    <td class="jquerybubblepopup-bottom-middle" style="background-image: url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-middle.png); text-align: left;"><img src="../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/tail-bottom.png" alt="" class="jquerybubblepopup-tail"></td>
                    <td class="jquerybubblepopup-bottom-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-right.png);"></td>
                </tr>
            </tbody>
        </table>
    </div>
</form>
<?php include ('includes/footer-scripts.php') ?>

<!-- Modal -->
<div class="modal fade" id="routelog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Route Log</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <ul class="timeline">
                        <li>
                            <div class="timeline-badge warning"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Action Taken: Approve</h4>
                                    <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> 11 hours ago </small></p>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Action</th>
                                            <td>Approved</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Ben Webster</a></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Time/Date</th>
                                            <td>03:17 PM 03/12/2014</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Annotations</th>
                                            <td>Paisis, filhis, espiritis santis. </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-badge warning"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Action Taken: FYI</h4>
                                    <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> 8 hours ago </small></p>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Action</th>
                                            <td>FYI</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Lester Young</a></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Time/Date</th>
                                            <td>03:17 PM 03/12/2014</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Annotations</th>
                                            <td>Mé faiz elementum girarzis</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="timeline-badge success"><i class="glyphicon glyphicon-credit-card icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Pending Action: Approve</h4>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Oscar Peterson</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-badge"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Future Action: Approve</h4>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Bill Evans</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer"><a href="" class="btn btn-default" data-dismiss="modal">Close</a></div>
        </div>
    </div>
</div>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="actionlist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"> My Action List</h4>
            </div>
            <div class="modal-body">
                <p class="pull-left lead">You currently have 12 actionalble items.</p>
                <p class="pull-right"><span aria-hidden="true" class="icon-cog" style="color:#999"></span><a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#actionlist-prefs" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"  data-dismiss="modal">Preferences</a></p>
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#actions-current" data-toggle="tab">Current Items</a></li>
                    <li><a href="#actions-completed" data-toggle="tab">Completed Actions</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content clearfix">
                    <div class="tab-pane active" id="actions-current">
                        <h4>Current Items</h4>
                        <table id="u569ish_line0" class="table table-condensed table-bordered table-hover" role="presentation">
                            <tbody>
                                <tr>
                                    <th class="">Route Status</th>
                                    <th scope="row">ID</th>
                                    <th scope="row">Type</th>
                                    <th class="uif-gridLayoutCell">Title</th>
                                    <th class="uif-gridLayoutCell">Action Requested</th>
                                    <th class="uif-gridLayoutCell">Created</th>
                                    <th style="width:140px" class="uif-gridLayoutCell">Action</th>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3231</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3273</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Test Title </td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3237</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3355</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-danger"> Disapproved </span></td>
                                    <td scope="row">3308</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol2</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-danger"> Disapproved </span></td>
                                    <td scope="row">3333</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Approve</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-danger"> Disapproved </span></td>
                                    <td scope="row">3454</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Test Title </td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-warning">Exception</span></td>
                                    <td scope="row">3247</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-warning">Exception</span></td>
                                    <td scope="row">3245</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-primary"> Saved </span></td>
                                    <td scope="row">3239</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Institutional Protocol2</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-primary"> Saved </span></td>
                                    <td scope="row">3388</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-primary"> Saved </span></td>
                                    <td scope="row">3316</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Test Title </td>
                                    <td class="">Approve</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a><a href="#" class="btn btn-xs btn-primary">Open</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="actions-completed">
                        <h4> Completed Actions</h4>
                        <table id="u569ish_line0" class="table table-condensed table-bordered table-hover" role="presentation">
                            <tbody>
                                <tr>
                                    <th class="">Route Status</th>
                                    <th scope="row">ID</th>
                                    <th scope="row">Type</th>
                                    <th class="uif-gridLayoutCell">Title</th>
                                    <th class="uif-gridLayoutCell">Action Requested</th>
                                    <th class="uif-gridLayoutCell">Created</th>
                                    <th style="width:140px" class="uif-gridLayoutCell">Action</th>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3355</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Test Title </td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3308</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
                                </tr>
                                <tr>
                                    <td class=""><span class="label label-success"> Approved </span></td>
                                    <td scope="row">3333</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer"><a href="" class="btn btn-default" data-dismiss="modal">Close</a></div>
        </div>
    </div>
</div>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="actionlist-prefs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Action List Preferences</h4>
            </div>
            <div class="modal-body uif-cssGridSection">
                <p class="pull-right"><a href="#"><span aria-hidden="true" class="icon-arrow-left" style="color:#999"></span> Return to List</a></p>
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#general" data-toggle="tab">General</a></li>
                    <li><a href="#rowhighlight" data-toggle="tab">Row Highlighting</a></li>
                    <li><a href="#email" data-toggle="tab">Email Notifications</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content clearfix">
                    <div class="tab-pane active" id="general">
                        <h4>General</h4>
                        <div class="form-horizontal" role="form">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group ">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Displayed Columns</label>
                                        <div class="col-sm-9 well">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Document Type </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Title </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Action Requested </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Initiator </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" >
                                                            Delegator </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Date Created </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Date Approved </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Current Route Node(s) </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" >
                                                            WorkGroup Request </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Document Route Status </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" >
                                                            Clear FYI </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value="" checked>
                                                            Use Outbox </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">List Refresh Rate</label>
                                        <div class="col-sm-2">
                                            <select class="form-control">
                                                <option>never</option>
                                                <option>1 min</option>
                                                <option>5 min</option>
                                                <option>10 min</option>
                                                <option>1 hour</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Items per Page</label>
                                        <div class="col-sm-2">
                                            <select class="form-control">
                                                <option value="Primary Delegates only on Filter Page" selected>10</option>
                                                <option value="Primary Delegates on Action List Page">25</option>
                                                <option value="Primary Delegates on Action List Page">100</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Delegator Filter</label>
                                        <div class="col-sm-4">
                                            <select class="form-control">
                                                <option value="Secondary Delegators only on Filter Page">Secondary Delegators only on Filter Page</option>
                                                <option value="Secondary Delegators on Action List Page" selected="selected">Secondary Delegators on Action List Page</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Primary Delegate Filter</label>
                                        <div class="col-sm-4">
                                            <select class="form-control">
                                                <option value="Primary Delegates only on Filter Page">Primary Delegates only on Filter Page</option>
                                                <option value="Primary Delegates on Action List Page" selected="selected">Primary Delegates on Action List Page</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="rowhighlight">
                        <h4>Row Highlighting</h4>
                        <p>Stuff goes here</p>
                    </div>
                    <div class="tab-pane" id="email">
                        <h4>Email Notifications</h4>
                        <div class="form-horizontal" role="form">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Send Emails</label>
                                        <div class="col-sm-4">
                                            <select class="form-control">
                                                <option value="no">Never</option>
                                                <option value="daily">Daily</option>
                                                <option value="weekly">Weekly</option>
                                                <option value="immediate" selected="selected">Immediately</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox">
                                                    Receive Primary Delegate Emails </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox">
                                                    Receive Secondary Delegate Emails </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Doc Type Notifications </label>
                                        <div class="col-sm-6">
                                            <table id="u569ish_line0" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Doc Type</th>
                                                        <th class="uif-gridLayoutCell">Frequency</th>
                                                        <th class="uif-gridLayoutCell" style="width:30px;">Actions</th>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">Identity Management Role</td>
                                                        <td class="">Weekly</td>
                                                        <td class=""><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">Term Maintenance </td>
                                                        <td class="">Weekly</td>
                                                        <td class=""><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">State Maintenance</td>
                                                        <td class="">Daily</td>
                                                        <td class=""><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">&nbsp;</td>
                                                        <td class="">&nbsp;</td>
                                                        <td class=""><a href="#" class="btn btn-xs btn-default"><span class="icon-plus" style="font-size:10px"></span> Add New</a></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><a href="" class="btn btn-primary" data-dismiss="">Save Changes</a><a href="" class="btn btn-default" data-dismiss="modal">Cancel</a></div>
            </div>
        </div>
    </div>
</div>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="docsearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Document Search</h4>
            </div>
            <div class="modal-body uif-cssGridSection">
                <div class="form-horizontal" role="form">
                    <div class="row ">
                        <div class="col-md-9">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Document Type</label>
                                <div class="col-md-8">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="test-username-1" name="test-username-1" value="" placeholder="" required>
                                        <div class="uif-form-control-helpers input-group-addon"><!-- Lookups, datepickers, etc. --><a class="btn btn-link btn-xs icon-search"><span class="sr-only">Search</span></a><a class="btn btn-link btn-xs icon-book"><span class="sr-only">Calendar</span></a><!-- // --></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Initiator</label>
                                <div class="col-md-8">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="test-username-1" name="test-username-1" value="" placeholder="" required>
                                        <div class="uif-form-control-helpers input-group-addon"><!-- Lookups, datepickers, etc. --><a class="btn btn-link btn-xs icon-search"><span class="sr-only">Search</span></a><a class="btn btn-link btn-xs icon-book"><span class="sr-only">Calendar</span></a><!-- // --></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Document ID</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="col-md-3 uif-cssGridLabelCol">
                                <label id="u1206a7s_label" for="u1206a7s_fieldset" class="uif-label displayWith-u1206a7s uif-labelBlock" data-label_for="u1206a7s"> Date Created </label>
                            </div>
                            <div class="col-md-6">
                                <div id="u1206a7s" class="uif-fieldGroup" data-parent="u1rttg5q" data-label="Project Dates" data-group="ufs91v9">
                                    <fieldset aria-labelledby="u1206a7s_label" id="u1206a7s_fieldset">
                                        <legend style="display: none">Project Dates</legend>
                                        <div id="ufs91v9" class="uif-cssGridGroup" data-parent="u1206a7s">
                                            <div class="row ">
                                                <div class="col-md-6">
                                                    <div id="u190upu7" class="uif-inputField" data-parent="ufs91v9" data-role="InputField">
                                                        <div class="input-group">
                                                            <input id="u190upu7_control" type="text" name="field4" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker valid" data-role="Control" data-control_for="u190upu7" placeholder="mm/dd/yyyy ">
                                                            <span class="input-group-msg">
                                                            <p id="uo9cnii" class="uif-message"> to </p>
                                                            </span></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div id="u190upv2" class="uif-inputField" data-parent="ufs91v9" data-role="InputField">
                                                        <input id="u190upv2_control" type="text" name="field5" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="u190upv2" placeholder="mm/dd/yyyy ">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">
                                            Save criteria for future searches </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown"> Load Previous Search <span class="caret"></span> </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation" class="dropdown-header">Recent History</li>
                                    <li><a href="#">Initiator: jckahler </a></li>
                                    <li><a href="#">Initiator: awddocmaintainer </a></li>
                                    <li><a href="#">Created: 03/25/2014 </a></li>
                                    <li class="divider"></li>
                                    <li role="presentation" class="dropdown-header">Saved Searches</li>
                                    <li><a href="#">My Documents</a></li>
                                    <li><a href="#">Jims's Documents</a></li>
                                    <li><a href="#">Carol's Documents</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#"> <span aria-hidden="true" class="icon-trash"></span> Clear All</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"> <a id="uotglon" class="btn btn-primary" tabindex="0" data-role="Action" data-toggle="modal" data-target="#docsearch-name" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}" data-dismiss="modal">Doc Search</a> <a href="" class="btn btn-link" data-dismiss="modal">Cancel</a> </div>
            </div>
        </div>
    </div>
</div>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="docsearch-name" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Document Search</h4>
            </div>
            <div class="modal-body uif-cssGridSection">
                <div class="form-horizontal" role="form">
                    <div class="row ">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Name this Search</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="i.e., My Documents">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"> <a href="" class="btn btn-primary" data-toggle="modal" data-dismiss="modal" data-target="#docsearch-results">Save and Search</a> <a href="" class="btn btn-link" data-toggle="modal" data-dismiss="modal" data-target="#docsearch">Go Back</a> </div>
            </div>
        </div>
    </div>
</div>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="docsearch-results" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Document Search</h4>
            </div>
            <div class="modal-body uif-cssGridSection">
                <h4>Results</h4>
                <table id="u569ish_line0" class="table table-condensed table-bordered table-hover" role="presentation">
                    <tbody>
                        <tr>
                            <th scope="row">ID</th>
                            <th scope="row">Type</th>
                            <th class="uif-gridLayoutCell">Title</th>
                            <th class="uif-gridLayoutCell">Status</th>
                            <th class="uif-gridLayoutCell">Initiator</th>
                            <th class="uif-gridLayoutCell">Created</th>
                            <th style="" class="uif-gridLayoutCell">Routing</th>
                        </tr>
                        <tr class>
                            <td><a href="#" target="_blank" title="">3384</a></td>
                            <td>Budget Document</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 5</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">admin, admin</a></td>
                            <td>03/26/2014 09:12 am</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3382</a></td>
                            <td>Proposal Development Document</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 9</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 08:13 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3381</a></td>
                            <td>Proposal Log</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 9</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 08:11 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3378</a></td>
                            <td>KC TimeAndMoney</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 9</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">admin, admin</a></td>
                            <td>03/25/2014 08:09 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3373</a></td>
                            <td>Budget Document</td>
                            <td>Budget Document - budget1</td>
                            <td>Enroute</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 06:30 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3372</a></td>
                            <td>Budget Document</td>
                            <td>KC Award - RH KRACOEUS 6309</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 06:13 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3368</a></td>
                            <td>Proposal Development Document</td>
                            <td>KC TimeAndMoney - timeandmoney document</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 06:06 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3367</a></td>
                            <td>Proposal Log</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 5</td>
                            <td>Saved</td>
                            <td><a href="#" target="_self" title="">admin, admin</a></td>
                            <td>03/25/2014 05:59 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3366</a></td>
                            <td>KC TimeAndMoney</td>
                            <td>KC Award - test award</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 05:56 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3357</a></td>
                            <td>KC Award</td>
                            <td>KC Protocol Review - BYLER/Protocol# 1403000021</td>
                            <td>Saved</td>
                            <td><a href="#" target="_self" title="">admin, admin</a></td>
                            <td>03/25/2014 05:52 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3354</a></td>
                            <td>KC TimeAndMoney</td>
                            <td>KC Institutional Proposal - Generated by Dev Proposal 5</td>
                            <td>Saved</td>
                            <td><a href="#" target="_self" title="">admin, admin</a></td>
                            <td>03/25/2014 03:26 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank" title="">3350</a></td>
                            <td>Award Budget Document</td>
                            <td>Budget Document - test budget</td>
                            <td>Final</td>
                            <td><a href="#" target="_self" title="">McGregor, Geoff</a></td>
                            <td>03/25/2014 03:25 pm</td>
                            <td class=""><a href="#" class="">View</a></td>
                        </tr>
                    </tbody>
                </table>
                <div class="modal-footer"><a href="" class="btn btn-link"data-toggle="modal" data-dismiss="modal" data-target="#docsearch"><span aria-hidden="true" class="icon-arrow-left"></span> Refine Search</a> </div>
            </div>
        </div>
    </div>
</div>
<!-- end Modal -->

</body>
</html>