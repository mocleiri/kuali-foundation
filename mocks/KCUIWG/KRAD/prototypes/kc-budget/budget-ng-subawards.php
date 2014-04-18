<?php
$page = 'subawards';
$section = '';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Kuali :: Fluid Application Header</title>

<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
<header id="u1xj79g4" class="uif-applicationHeader">
	<div class="container">
		<nav id="u1osy4lo" class="navbar" role="navigation"> 
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span></button> <a class="navbar-brand" href="index.php"> 
		<div class="logoBrand">
			<h1>
				<img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"> 
			</h1>
		</div>
		</a>
	</div>
	<div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
		<ul class="nav navbar-nav navbar-right uif-listLayout">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Researcher </a> 
			<div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
				<div class="row ">
					<div class="col-md-12">
						<section id="u9tj3ss" class="uif-listGroup">
							<h3 class="uif-headerText">
								Proposal Development
							</h3>
							<ul class="uif-listLayout">
								<li><a href="prop-start.php" class="uif-actionLink" id="umdwwyj" tabindex="0" data-role="Action"> Create a Proposal </a></li>
								<li><a href="#" class="uif-actionLink" id="umdwwze" tabindex="0" data-role="Action"> Created a Proposal Budget</a></li>
							</ul>
						</section>
					</div>
				</div>
			</div>
			</li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Unit </a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Central Admin </a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Maintenance </a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> System Admin </a></li>
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
						<li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qku" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-ActionListView&quot;);"> Action List </a></li>
						<li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qlp" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-DocSearchView&quot;);"> Doc Search </a></li>
						<li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Backdoor Login <span class="caret"></span> </a> 
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

<!-- Backdoor info (here to inherit stickyness with the header, if set) -->
</header>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
<!-- VIEW -->
	<div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;">
<!-- BREADCRUMBS -->
<!-- VIEW HEADER -->
<!-- VIEW HEADER -->
<?php include ('includes/uif-viewHeader.php') ?>
<!-- // VIEW HEADER  -->
<!-- VIEW CONTENT -->
		<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
<!-- VIEW NAVIGATION -->
<?php include ('includes/uif-navigation-budget.php') ?>
			<div id="Uif-BreadcrumbUpdate" style="display:;">
			</div>
			<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;"> 
			<header class="clearfix uif-header-contentWrapper pull-left">
				<div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
					<h2 class="uif-headerText">
						<span class="uif-headerText-span">Subawards </span>
					</h2>
				</div>
			</header>

<!--
                <div class="pull-right"> <a href="#"> <span class="icon-compass"></span> Guide Me</a></div>
                -->
			<p class="uif-cssGridGroup uif-boxLayoutVerticalItem clearfix">
				Upload a pre-formatted budget document for a subawardee organization or enter details manually.
			</p>
			<div class="col-md-12">
				<button class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.proposal.add.php">Attach subawardee budget</button> 
			</div>
			<br>
			<section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
				<header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
					<h3 class="uif-headerText">
						<a data-role="disclosureLink" data-linkfor="umbrella" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Umbrella Corporation.pdf</span></a>
					</h3>
				</header>
				<div id="umbrella" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
					<div class="uif-cssGridGroup uif-boxLayoutVerticalItem clearfix">
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>Organization name:</b>
								</p>
							</div>
							<div class="col-md-4">
								<p>
									<b>Umbrella Corporation</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>Comments:</b>
								</p>
							</div>
							<div class="col-md-8">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>Form name:</b>
								</p>
							</div>
							<div class="col-md-4">
								<p>
									<b>Umbrella Corporation.pdf</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>PDF last updated:</b>
								</p>
							</div>
							<div class="col-md-4">
								<p>
									<b>03/11/2014 03:27 PM</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>XML last updated:</b>
								</p>
							</div>
							<div class="col-md-4">
								<p>
									<b>03/11/2014 03:27 PM</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>Subaward status code:</b>
								</p>
							</div>
							<div class="col-md-4">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<p>
									<b>Namespace:</b>
								</p>
							</div>
							<div class="col-md-4">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-1">
							</div>
							<div class="col-md-2">
								<p>
									<b>Direct cost</b>
								</p>
							</div>
							<div class="col-md-2">
								<p>
									<b>F&amp;A cost</b>
								</p>
							</div>
							<div class="col-md-2">
								<p>
									<b>Cost sharing</b>
								</p>
							</div>
							<div class="col-md-2">
								<p>
									<b>Total cost</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1">
								<p>
									<b>1</b>
								</p>
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<p>
									<b>0.00</b>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1">
								<p>
									<b>2</b>
								</p>
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" placeholder="0.00">
							</div>
							<div class="col-md-2">
								<p>
									<b>0.00</b>
								</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			</main> 
		</div>

<!-- VIEW FOOTER -->
		<div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
			<a href="budget-ng-non-personnel.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem">Go back</a> <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save</button> <a href="budget-ng-cost-sharing.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Save and Continue</a> 
		</div>

<!-- DIALOGS/Placeholders -->
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
	</span> 
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
					<td class="jquerybubblepopup-innerHtml">
					<div class="uif-clientMessageItems uif-clientErrorDiv">
						<ul>
							<li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
						</ul>
					</div>
					</td>
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
<!-- different results for casual user -->
<?php

$currentPage =  $_SERVER['QUERY_STRING'] ;//echo $currentPage;

if ($currentPage == "generate=no") {

?>
<!-- Note that screens such as cost-sharing, unrecovered F&A, and modular will display messages indicating that no data entry is required, if that is the case. -->
<script>


	(function($){

alert(" --Periods were not generated on the non personnel page --> Note that screens such as cost-sharing, unrecovered F&A, and modular will display messages indicating that no data entry is required, if that is the case.")
	
		
 }(jQuery))
</script>
<?php } else { ?>
<!-- -->
<?php } ?>
<!-- different results for casual user -->
<!-- Modal -->
<div class="modal fade" id="summary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
				<h4 class="modal-title" id="myModalLabel">
					Summary
				</h4>
			</div>
			<div class="modal-body">
				<p>
					Here's a summary of your current budget.
				</p>
				<table class="table table-condensed credit-allocation">
					<tbody>
						<tr>
							<th>&nbsp;</th>
							<th>P1</th>
							<th>P2</th>
							<th>P3</th>
							<th>P4</th>
							<th>P5</th>
							<th>Totals</th>
						</tr>
						<tr class="active">
							<td><strong> Personnel</strong></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="">
							<td><a href="#"> <span aria-hidden="true" class="icon-chevron-right"></span> Salary</a></td>
							<td>156934</td>
							<td>156934</td>
							<td>156934</td>
							<td>156934</td>
							<td>156934</td>
							<td>784670</td>
						</tr>
						<tr class="">
							<td><a href="#"> <span aria-hidden="true" class="icon-chevron-right"></span> Fringe</a></td>
							<td>37345</td>
							<td>37345</td>
							<td>37345</td>
							<td>37345</td>
							<td>37345</td>
							<td>186725</td>
						</tr>
						<tr class="">
							<td><a href="#"> <span aria-hidden="true" class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
							<td>28284</td>
							<td>28284</td>
							<td>28284</td>
							<td>28284</td>
							<td>28284</td>
							<td>141420</td>
						</tr>
						<tr class="">
							<td>Personnel Subtotal</td>
							<td><strong>222563</strong></td>
							<td><strong>222563</strong></td>
							<td><strong>222563</strong></td>
							<td><strong>222563</strong></td>
							<td><strong>222563</strong></td>
							<td><strong>837456</strong></td>
						</tr>
						<tr class="active">
							<td><strong> Non-personnel</strong></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="">
							<td><a href="#"> <span aria-hidden="true" class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
							<td>38546</td>
							<td>38546</td>
							<td>38546</td>
							<td>38546</td>
							<td>38546</td>
							<td>219348</td>
						</tr>
						<tr class="">
							<td>Nonpersonnel Subtotal</td>
							<td><strong>38546</strong></td>
							<td><strong>38546</strong></td>
							<td><strong>38546</strong></td>
							<td><strong>38546</strong></td>
							<td><strong>38546</strong></td>
							<td><strong>219348</strong></td>
						</tr>
						<tr class="active">
							<td><strong> Totals</strong></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Total Direct Cost</td>
							<td>723454</td>
							<td>723454</td>
							<td>723454</td>
							<td>723454</td>
							<td>723454</td>
							<td>496432</td>
						</tr>
						<tr>
							<td>Total F&amp;A Costs</td>
							<td>34537</td>
							<td>34537</td>
							<td>34537</td>
							<td>34537</td>
							<td>34537</td>
							<td>154578</td>
						</tr>

<!-- tr>
            <td colspan="5" >Totals</td>
          </tr> -->
						<tr class="active">
							<td>Total Costs</td>
							<td class=""><strong>238546</strong></td>
							<td class=""><strong>238546</strong></td>
							<td class=""><strong>238546</strong></td>
							<td class=""><strong>238546</strong></td>
							<td class=""><strong>238546</strong></td>
							<td class=""><strong>2219348</strong></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
			</div>
		</div>
	</div>
</div>

<!-- MODAL budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
</body>
</html>
