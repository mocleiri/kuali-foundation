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
	margin-top: 0px;
}
</style>
<!--<link href="../css/kradSampleApp.css" rel="stylesheet" type="text/css"/>
   <link href="../css/labsProposal.css" rel="stylesheet" type="text/css"/>  -->

</head>

<body id="Uif-Application" style="padding-bottom: 48px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
	<header id="u1xj79g4" class="uif-applicationHeader">
		<div class="container">
			<nav id="u1osy4lo" class="navbar" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span> </button>
					<a class="navbar-brand" href="index.php">
					<div class="logoBrand">
						<h1> <img id="u2elq10" src="http://ux.kuali.org/prototypes/rice/assets/img/logo.png" alt="" class="uif-image"> </h1>
					</div>
					</a> </div>
				<div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
					<ul class="nav navbar-nav navbar-right uif-listLayout">
						<li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Main Menu </a>
							<div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 500px; right: -180px;">
								<div class="row ">
									<div class="col-md-6">
										<section id="u9tj3ss" class="uif-listGroup">
											<h3 class="uif-headerText"> Workflow</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">User Preferences</a> </li>
												<li> <a  href="#" title="asdf">Quicklinks</a> </li>
												<li> <a  href="#" title="asdf">Routing Report</a> </li>
												<li> <a  href="#" title="asdf">Routing Rules</a> </li>
												<li> <a  href="#" title="asdf">Routing Rules Delegation</a> </li>
												<li> <a  href="#" title="asdf">Routing and Identity Management Document Type Hierarchy</a> </li>
												<li> <a  href="#" title="asdf">eDoc Lite</a> </li>
												<li> <a  href="#" title="asdf">People Flow</a> </li>
											</ul>
										</section>
									</div>
									<div class="col-md-6">
										<section id="u9tj3ss" class="uif-listGroup">
											<h3 class="uif-headerText">KRMS Rules</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Agenda Lookup</a> </li>
												<li> <a  href="#" title="asdf">Context Lookup</a> </li>
												<li> <a  href="#" title="asdf">Attribute Definition Lookup</a> </li>
												<li> <a  href="#" title="asdf">Term Lookup</a> </li>
												<li> <a  href="#" title="asdf">Term Specification Lookup</a> </li>
												<li> <a  href="#" title="asdf">Category Lookup</a> </li>
											</ul>
											<h3 class="uif-headerText">Notifications</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Notification Search</a> </li>
												<li> <a  href="#" title="asdf">Channel Subscriptions</a> </li>
												<li> <a  href="#" title="asdf">Delivery Types</a> </li>
											</ul>
										</section>
									</div>
								</div>
							</div>
						</li>
						<li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Administration </a>
							<div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 700px; right: 0px;">
								<div class="row ">
									<div class="col-md-4">
										<section id="u9tj3ss" class="uif-listGroup">
											<h3 class="uif-headerText"> Workflow</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Rule Attribute</a> </li>
												<li> <a  href="#" title="asdf">Rule Template</a> </li>
												<li> <a  href="#" title="asdf">XML Stylesheets</a> </li>
												<li> <a  href="#" title="asdf">XML Ingester</a> </li>
												<li> <a  href="#" title="asdf">Statistics</a> </li>
												<li> <a  href="#" title="asdf">Document Operation</a> </li>
												<li> <a  href="#" title="asdf">Document Type</a> </li>
											</ul>
											<h3 class="uif-headerText"> Notification</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Send Simple Notification</a> </li>
												<li> <a  href="#" title="asdf">Send Event Notification</a> </li>
												<li> <a  href="#" title="asdf">Manage Content Types</a> </li>
											</ul>
										</section>
									</div>
									<div class="col-md-4">
										<section id="u9tj3ss" class="uif-listGroup">
											<h3 class="uif-headerText"> Identity</h3>
											<strong>Identity</strong>
											<ul class="uif-listLayout">	<li> <a  href="kim-person-docoverview.php" title="asdf">Person</a> </li>
												<li> <a  href="#" title="asdf">Group</a> </li>
												<li> <a  href="#" title="asdf">Role</a> </li>
												<li> <a  href="#" title="asdf">Permission</a> </li>
												<li> <a  href="#" title="asdf">Responsibility</a> </li>
											</ul>
											<strong>Locations</strong>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Campus</a> </li>
												<li> <a  href="#" title="asdf">Country</a> </li>
												<li> <a  href="#" title="asdf">County</a> </li>
												<li> <a  href="#" title="asdf">Postal Code</a> </li>
												<li> <a  href="#" title="asdf">State</a> </li>
											</ul>
											<strong>Reference</strong>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Address Type</a> </li>
												<li> <a  href="#" title="asdf">Affiliation Type</a> </li>
												<li> <a  href="#" title="asdf">Campus Type</a> </li>
												<li> <a  href="#" title="asdf">Citizenship Status</a> </li>
												<li> <a  href="#" title="asdf">Email Type</a> </li>
												<li> <a  href="#" title="asdf">Employment Status</a> </li>
												<li> <a  href="#" title="asdf">Employment Type</a> </li>
												<li> <a  href="#" title="asdf">Entity Type</a> </li>
												<li> <a  href="#" title="asdf">External Identifier Type</a> </li>
												<li> <a  href="#" title="asdf">Name Type</a> </li>
												<li> <a  href="#" title="asdf">Phone Type</a> </li>
											</ul>
										</section>
									</div>
									<div class="col-md-4">
										<section id="u9tj3ss" class="uif-listGroup">
											<h3 class="uif-headerText">Configuration</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Parameter</a> </li>
												<li> <a  href="#" title="asdf">Parameter Type</a> </li>
												<li> <a  href="#" title="asdf">Component</a> </li>
												<li> <a  href="#" title="asdf">Namespace</a> </li>
												<li> <a  href="#" title="asdf">Pessimistic Lock</a> </li>
												<li> <a  href="#" title="asdf">Configuration Viewer</a> </li>
											</ul>
											<h3 class="uif-headerText">Service Bus</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Message Queue</a> </li>
												<li> <a  href="#" title="asdf">Thread Pool</a> </li>
												<li> <a  href="#" title="asdf">Service Registry</a> </li>
												<li> <a  href="#" title="asdf">Service Bus</a> </li>
												<li> <a  href="#" title="asdf">Quartz</a> </li>
												<li> <a  href="#" title="asdf">Security Management</a> </li>
											</ul>
											<h3 class="uif-headerText">Miscellaneous</h3>
											<ul class="uif-listLayout">
												<li> <a  href="#" title="asdf">Cache Admin</a> </li>
											</ul>
										</section>
									</div>
								</div>
							</div>
						</li>
						<li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> KRAD </a> </li>
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
								<li class="pull-right"> <a href="#" class="uif-actionLink" id="u1o09qku" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-ActionListView&quot;);"> Action List </a> </li>
								<li class="pull-right"> <a href="#" class="uif-actionLink" id="u1o09qlp" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-DocSearchView&quot;);"> Doc Search </a> </li>
								<li class="dropdown pull-right"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Backdoor Login <span class="caret"></span> </a>
									<ul class="dropdown-menu uif-listLayout">
										<li> <a href="#" class="uif-actionLink" id="u101xf6k" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Preferences </a> </li>
										<li> <a href="#" class="uif-actionLink" id="u101xf7f" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Logout </a> </li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Backdoor info (here to inherit stickyness with the header, if set) --> 
</header>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
	<!-- VIEW -->
	<div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"> 
		<!-- BREADCRUMBS --> 
		<!-- VIEW HEADER -->
		<header class="container uif-viewHeader-contentWrapper">
			<div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal">
				<h1 class="uif-headerText">
					<p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> Identity Management </p>
					<span class="uif-headerText-span"> Edit Person </span> <span class="uif-supportTitle-wrapper">
					<div id="u1hgnm9q" class="uif-viewHeader-supportTitle" data-parent="ueqbqhn">Principle Name: <em>thrclark</em></div>
					</span> </h1>
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
					<div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
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
					<li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy Document</a> </li>
					<li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-male"></span>Ad Hoc Recipients</a> </li>
					<li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-magic"></span>Super User Actions</a> </li>
					<li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#routelog" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-road"></span>Route Log</a> </li>
				</ul>
			</div>
		</header>
		
		<!-- VIEW CONTENT -->
		<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
			<!-- VIEW NAVIGATION -->
			<nav id="Uif-Navigation" style="position:absolute; display:none">
				<div id="LabsProposal-Menu" class="uif-menuNavigationGroup">
					<div class="sidebar-collapse"> <span class="icon-angle-left"></span> </div>
					<!-- NAVIGATION -->
					<ul class="nav nav-list">
						<li> <a href="kim-person-docoverview.php" id="u79gei9" class="uif-navigationActionLink" ><span class="icon-file-alt"></span><span class="uif-innerText">Document Overview</span></a> </li>
						<li> <a href="kim-person-personoverview.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-user"></span><span class="uif-innerText">Person Overview</span></a> </li>
						<li > <a href="kim-person-contactinfo.php" id="u79gei9" class="uif-navigationActionLink" ><span class="icon-list-alt"></span><span class="uif-innerText">Contact Info</span></a> </li>
						<li> <a href="kim-person-affiliations.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-puzzle"></span><span class="uif-innerText">Affiliations</span></a> </li>
						<li class="active"> <a href="kim-person-memberships.php"id="u79gei9" class="uif-navigationActionLink" ><span class="icon-group"></span><span class="uif-innerText">Memberships</span></a> </li>
					</ul>
				</div>
			</nav>
			<div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
			<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
				<header class="clearfix uif-header-contentWrapper pull-left">
					<div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
						<h2 class="uif-headerText"> <span class="uif-headerText-span"> Memberships </span> </h2>
					</div>
				</header>
				<section id="u1rttg5q" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="ue1o6s7">
					<header id="u16xlbc5" class="uif-sectionHeader" data-header_for="u1rttg5q">
						<h3 class="uif-headerText"> <a data-role="disclosureLink" data-linkfor="u1rttg5q_disclosureContent" href="#" id="u1rttg5q_toggle" data-open="true" data-widgetid="u7s3xen" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1rttg5q_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1rttg5q_toggle_col" class="icon-caret-right"></span> Groups </span></a> </h3>
					</header>
					<div id="u1rttg5q_disclosureContent" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
						<table id="u569ish_line0" class="table table-condensed" role="presentation">
							<tbody>
								<tr>
									<th scope="row">Group</th>
									<th class="uif-gridLayoutCell">Namespace Code</th>
									<th class="uif-gridLayoutCell">Name</th>
									<th class="uif-gridLayoutCell">Type</th>
									<th class="uif-gridLayoutCell">Active From Date</th>
									<th class="uif-gridLayoutCell">Active From Date</th>
									<th class="uif-gridLayoutCell" style="width:120px;">Actions</th>
								</tr>
								<tr>
									<td scope="row">2008</td>
									<td class="">KUALI</td>
									<td class="">Kuali Developers</td>
									<td class="">Default</td>
									<td class="">03/12/2014</td>
									<td class="">03/12/2015</td>
									<td class=""> <a href="#" class="btn btn-xs btn-default">Inactivate</a></td>
								</tr>
								<tr>
									<td scope="row">2009</td>
									<td class="">KUALI</td>
									<td class="">Kuali Administration</td>
									<td class="">Default</td>
									<td class="">03/12/2014</td>
									<td class="">03/12/2015</td>
												<td class=""> <a href="#" class="btn btn-xs btn-default">Inactivate</a></td>
								</tr>
								<tr>
									<td scope="row">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class=""><a href="#" class="btn btn-xs btn-default"> <span class="icon-plus" style="font-size:10px"></span> Add New</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</section>
				
				<!--
				<section id="u1rttg5q" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="ue1o6s7">
					<header id="u16xlbc5" class="uif-sectionHeader" data-header_for="u1rttg5q">
						<h3 class="uif-headerText"> <a data-role="disclosureLink" data-linkfor="u1rttg5q_disclosureContent" href="#" id="u1rttg5q_toggle" data-open="true" data-widgetid="u7s3xen" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1rttg5xx_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1rttg5q_toggle_col" class="icon-caret-right"></span> Addresses </span></a> </h3>
					</header>
					<div id="u1rttg5xx_disclosureContent" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
						<table id="u569ish_line0" class="table table-condensed" role="presentation">
							<tbody>
								<tr>
									<th scope="row">Type</th>
									<th class="uif-gridLayoutCell">Line 1</th>
									<th class="uif-gridLayoutCell">Line 2</th>
									<th class="uif-gridLayoutCell">Line 3</th>
									<th class="uif-gridLayoutCell">City</th>
									<th class="uif-gridLayoutCell">State/Province</th>
									<th class="uif-gridLayoutCell">Postal Code</th>
									<th class="uif-gridLayoutCell">Country</th>
									<th class="uif-gridLayoutCell">Default</th>
									<th class="uif-gridLayoutCell">Active</th>
									<th class="uif-gridLayoutCell" style="width:120px;">Actions</th>
								</tr>
								<tr>
									<td scope="row">Home</td>
									<td class="">1234 Walnut St</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">Hughson</td>
									<td class="">CA</td>
									<td class="">95634</td>
									<td class="">United States</td>
									<td class="">&nbsp;</td>
									<td class=""><span aria-hidden="true" class="icon-ok" style="color:green"></span></td>			<td class=""><a href="#" class="btn btn-xs btn-default">Edit</a> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
								<tr>
									<td scope="row">Other</td>
									<td class="">3642 Elm St</td>
									<td class="">Annex #3</td>
									<td class="">&nbsp;</td>
									<td class="">Chicago</td>
									<td class="">IL</td>
									<td class="">52534</td>
									<td class="">United States</td>
									<td class="">&nbsp;</td>
									<td class=""><span aria-hidden="true" class="icon-ok" style="color:green"></span></td>
									<td class=""><a href="#" class="btn btn-xs btn-default">Edit</a> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
								<tr>
									<td scope="row">Work</td>
									<td class="">1504 E Tenth St</td>
									<td class="">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class="">Bloomington</td>
									<td class="">IN</td>
									<td class="">47408</td>
									<td class="">United States</td>
									<td class=""><span aria-hidden="true" class="icon-ok" style="color:green"></span></td>
									<td class=""><span aria-hidden="true" class="icon-ok" style="color:green"></span></td>
									<td class=""><a href="#" class="btn btn-xs btn-default">Edit</a> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</section>
				-->
				
				<section id="u1rttg5q" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="ue1o6s7">
					<header id="u16xlbc5" class="uif-sectionHeader" data-header_for="u1rttg5q">
						<h3 class="uif-headerText"> <a data-role="disclosureLink" data-linkfor="u1rttg5q_disclosureContent" href="#" id="u1rttg5q_toggle" data-open="true" data-widgetid="u7s3xen" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1rttg5q_toggle_exp" class="icon-caret-down" style="display: none;"></span><span style="" id="u1rttg5q_toggle_col" class="icon-caret-right"></span> Roles </span></a> </h3>
					</header>
					<div id="u1rttg5q_disclosureContent" data-role="disclosureContent" data-open="false" class="uif-disclosureContent" style="overflow: hidden; display: none;"> asdf </div>
				</section>
				<section id="u1rttg5q" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="ue1o6s7">
					<header id="u16xlbc5" class="uif-sectionHeader" data-header_for="u1rttg5q">
						<h3 class="uif-headerText"> <a data-role="disclosureLink" data-linkfor="u1rttg5q_disclosureContent" href="#" id="u1rttg5q_toggle" data-open="true" data-widgetid="u7s3xen" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1rttg5q_toggle_exp" class="icon-caret-down" style="display: none;"></span><span style="" id="u1rttg5q_toggle_col" class="icon-caret-right"></span>  Delegations</span></a> </h3>
					</header>
					<div id="u1rttg5q_disclosureContent" data-role="disclosureContent" data-open="false" class="uif-disclosureContent" style="overflow: hidden; display: none;"> asdf </div>
				</section>
			</main>
		</div>
		<!-- VIEW FOOTER -->
		<div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">		<button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}"> Save and Submit </button>

<button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
			
		</div>
		<!-- DIALOGS/Placeholders -->
		<div id="Uif-Dialogs"> </div>
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
	</span> <span id="formComplete"></span>
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
			<div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Close</a> </div>
		</div>
	</div>
</div>

<!-- end Modal -->

</body>
</html>