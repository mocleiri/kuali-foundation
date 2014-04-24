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
	<style>
#modular-f-a-4b div{margin-bottom: 1em;;}


	</style>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
<div id="u1xj79g4" class="uif-applicationHeader">
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
</div>

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
			<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left:235px;"> 
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

<!-- <div class="col-md-12">
				<button class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.proposal.add.php">Attach subawardee budget</button> 
			</div>
			<br> -->
<!-- -->
			<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
				<header class="uif-sectionHeader">
					<h3 class="uif-headerText">
						<a data-role="disclosureLink" data-linkfor="modular-f-a-4b" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"> <span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Attach Subawardee Budget</span></a> 
					</h3>
				</header>
				<div id="modular-f-a-4b" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
					<div class="col-sm-5">
						<label>Organization Name</label> 
						<div class="input-group">
							<input id="rate-4" class="form-control text-right" type="text" value="" name="rate-4" placeholder="search">
							<div class="input-group-addon"> <button style="border:none;padding:0;" type="submit">
							<span class="icon-search" style="padding:5px 0; background:#eee;">
							<span class="sr-only">search organizations</span></span></div> 
						</button></div>
					</div>
					<div class="col-sm-5">
						<label>Search for a PDF 
						<input type="file" title="Search for a file to add" class="btn" style="margin-left:0;padding-left:0;box-shadow:none;">
						</label> 
					</div>
					<div class="col-sm-2" style="border:1px dotted #fff;padding:20px 0 0;">
						<a class="btn btn-default btn-xs pull-right" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a> 
					</div>
					<div class="col-sm-12 clearfix" style="margin-top:-25px;padding-right:0;">
						<label class="pull-left">Comments: </label> 
						<textarea style="width:100%; height:55px;" class="form-control">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</textarea>
					</div>
				</div>
			</section>

<!-- // -->
<!-- -->
			<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
				<header class="uif-sectionHeader">
					<h3 class="uif-headerText">
						<a data-role="disclosureLink"  data-linkfor="modular-f-a-4b2" href="#" data-open="true" data-widgetid="modular-d" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span> <span style="display:none;" class="icon-caret-right"></span>Existing Subaward Attachments</span></a> 
					</h3>
				</header>
				<div id="modular-f-a-4b2" data-role="disclosureContent" data-open="true" data-backdrop="static" class="uif-disclosureContent">
					<table class=" pad5 table table-condensed table-bordered">
						<thead>
							<tr>
								<th class="col-sm-4">Organization Name</th>
								<th class="col-sm-4">File Name</th>
								<th class="col-sm-3">Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>General Atomics Company</td>
								<td>uploaded-budget-1_33-v3.pdf</td>
								<td> 
								<div class="dropdown dropdown-large pull-right">
									<a href="#" class="btn btn-default btn-xs uif-delete" data-toggle="true "><span class="sr-only">Delete</span>
									<span aria-hidden="true" class="icon-trash"></span></a> <a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a> 
									<div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -759px;top: 26px;width: 858px;padding:15px; background:#fcf8e3;">
										<div style="position:absolute;top:20px;right:-50px;width:400px;">
											<a href="#" class="btn btn-default btn-xs">View PDF</a> <a href="#" class="btn btn-default btn-xs">View XML</a> <a href="#" class="btn btn-default btn-xs ">Sync from PDF</a> <a href="" class="btn btn-default btn-xs">Replace</a> <a href="#" class="btn btn-default btn-xs">Delete</a> 
										</div>
										<p class="lead">
											Attachment Details 
										</p>
										<hr>
										<ul class="col-sm-12">
											<li class="col-sm-6"><strong>Organization Name:</strong> General Atomics Company</li>
											<li class="col-sm-6"><strong>Form Name:</strong> RR_Budget_1_3</li>
											<li class="col-sm-6"><strong>File Name:</strong> uploaded-budget-1_33-v3.pdf</li>
											<li class="col-sm-6"><strong>Attachments:</strong> dhk69731646fdas4f6469811</li>
											<li class="col-sm-6"><strong>PDF Last Updated:</strong> 04/23/2014</li>
											<li class="col-sm-6"><strong>XML Last Updated:</strong> 04/23/2014</li>
											<li class="col-sm-6"><strong>Subaward Status Code:</strong> 1</li>
											<li class="col-sm-6"><strong>Namespace:</strong> http://apply.grants.gov/forms/RR_Budget_1_#-v13</li>

<!-- -->
										</ul>
										<div class="col-sm-12" style="margin-left:7px;">
											<label>Comments: 
											<textarea style="width:100%; height:55px;" class="form-control">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</textarea>
											</label> <a class="btn btn-default btn-xs pull-right" style="margin:10px 0 ;" href="#"> Edit Comments </a> 
											<p class="col-sm-10 clearfix">
												<strong>Details</strong>
											</p>
											<table class=" pad5 table table-condensed table-bordered">
												<thead>
													<tr>
														<th>
<!-- numbers -->
														</th>
														<th>Direct Cost</th>
														<th>F&amp;A Cost</th>
														<th>Cost Sharing</th>
														<th>Total Cost</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>1</td>
														<td> <label class="input-group" for="base-4"> <span class="input-group-addon">$</span> 
														<input name="base-4" id="base-4" type="text" value="" class="form-control text-right" placeholder="0.00" />
														</label> </td>
														<td> <label class="input-group" for="base-4"> <span class="input-group-addon">$</span> 
														<input name="base-4" id="base-4" type="text" value="" class="form-control text-right" placeholder="0.00" />
														</label> </td>
														<td> <label class="input-group" for="base-4"> <span class="input-group-addon">$</span> 
														<input name="base-4" id="base-4" type="text" value="" class="form-control text-right" placeholder="0.00" />
														</label> </td>
														<td> <label class="input-group" for="base-4"> <span class="input-group-addon">$</span> 
														<input name="base-4" id="base-4" type="text" value="" class="form-control text-right" readonly placeholder="0.00" />
														</label> </td>
													</tr>
												<tr>
	<td>2</td>
	<td> <label class="input-group" for="base-5"> <span class="input-group-addon">$</span> 
	<input name="base-5" id="base-5" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-5"> <span class="input-group-addon">$</span> 
	<input name="base-5" id="base-5" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-5"> <span class="input-group-addon">$</span> 
	<input name="base-5" id="base-5" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-5"> <span class="input-group-addon">$</span> 
	<input name="base-5" id="base-5" type="text" value="" class="form-control text-right" readonly placeholder="0.00" />
	</label> </td>
</tr>

													<tr>
	<td>2</td>
	<td> <label class="input-group" for="base-6"> <span class="input-group-addon">$</span> 
	<input name="base-6" id="base-6" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-6"> <span class="input-group-addon">$</span> 
	<input name="base-6" id="base-6" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-6"> <span class="input-group-addon">$</span> 
	<input name="base-6" id="base-6" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-6"> <span class="input-group-addon">$</span> 
	<input name="base-6" id="base-6" type="text" value="" class="form-control text-right" readonly placeholder="0.00" />
	</label> </td>
</tr>

<tr>
	<td>2</td>
	<td> <label class="input-group" for="base-7"> <span class="input-group-addon">$</span> 
	<input name="base-7" id="base-7" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-7"> <span class="input-group-addon">$</span> 
	<input name="base-7" id="base-7" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-7"> <span class="input-group-addon">$</span> 
	<input name="base-7" id="base-7" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-7"> <span class="input-group-addon">$</span> 
	<input name="base-7" id="base-7" type="text" value="" class="form-control text-right" readonly placeholder="0.00" />
	</label> </td>
</tr>
<tr>
	<td>2</td>
	<td> <label class="input-group" for="base-8"> <span class="input-group-addon">$</span> 
	<input name="base-8" id="base-8" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-8"> <span class="input-group-addon">$</span> 
	<input name="base-8" id="base-8" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-8"> <span class="input-group-addon">$</span> 
	<input name="base-8" id="base-8" type="text" value="" class="form-control text-right" placeholder="0.00" />
	</label> </td>
	<td> <label class="input-group" for="base-8"> <span class="input-group-addon">$</span> 
	<input name="base-8" id="base-8" type="text" value="" class="form-control text-right" readonly placeholder="0.00" />
	</label> </td>
</tr>

												</tbody>
											</table>
										</div>
									</div>
								</div>

<!-- -->
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>
<!-- // sections -->
			</main> 
		</div>

<div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
    <div class="uif-footer-centered-control-group clearfix">
        <div class="global-navigate btn-group">
            <button type="button" href="budget-ng-cost-sharing.php" id="save-continue" class="btn btn-primary">Continue</button>
        </div>

        <div class="global-actions btn-group">
            <button type="button" href="budget-ng-personnelCosts-persPeriod.php" id="" class="btn btn-default">Back</button>
            <button type="button" id="" class="btn btn-default">Save</button>
            <button type="button" id="" class="btn btn-default">Reload</button>
            <button type="button" id="" class="btn btn-default">Complete Budget</button>
        </div>
    </div>
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
