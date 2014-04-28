<?php
$section = 'asdf';
$page = 'memberships';
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


<?php include ('includes/uif-applicationHeader.php') ?>




<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
	<!-- VIEW -->
	<div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"> 
		<!-- BREADCRUMBS --> 
		<!-- VIEW HEADER --> <?php include ('includes/uif-viewHeader-person.php') ?>
		
		<!-- VIEW CONTENT -->
		<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
			<!-- VIEW NAVIGATION -->
			 <?php include ('includes/uif-navigation-person.php') ?>
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
		</div>        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">

<div class="global-navigate btn-group">
                    <button type="button" href="asdf.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="asdf.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>




<div class="global-actions btn-group">

                    <button type="button" id="" class="btn btn-default">Save</button>
                   <button type="button" id="" class="btn btn-default">Reload</button>
                   <button type="button"  id="" class="btn btn-default">Submit Document</button>
                </div>
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