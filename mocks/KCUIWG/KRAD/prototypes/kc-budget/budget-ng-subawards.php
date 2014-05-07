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
				<img id="u2elq10" src="../../themes/kboot/images/logo-kc.png" alt="" class="uif-image"> 
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
				<li class="dropdown pull-right"><a href="#" class="">Doc Search</a></li>
						<li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Action list <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li><a href="#"><i class="icon icon-file"></i><span>Approve: Doc #5547</span></a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="icon icon-inbox"></i><span>Full Action List</span></a></li>
							</ul>
						</li>
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
<?php include('includes/uif-viewHeader-budget.php') ?>
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
					<h2 class="uif-headerText"><span class="uif-headerText-span">Subawards </span></h2>
				</div>
			</header>
			<p class="uif-cssGridGroup uif-boxLayoutVerticalItem clearfix">Upload a pre-formatted budget document for a subawardee organization or enter details manually.</p>
            <div class="col-md-12 uif-pagetools clearfix">
                <a href="#" class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target="#modal-add-subaward"><span aria-hidden="true" class="icon-plus"></span> Add Subaward</a>
            </div><br />

			<section>
                <table class="pad5 table table-condensed table-bordered uif-has-actions">
                    <thead>
                        <tr>
                            <th>Organization Name</th>
                            <th>File Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>General Atomics Company</td>
                            <td>uploaded-budget-1_33-v3.pdf</td>
                            <td>
                                <div class="dropdown dropdown-large">
                                    <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal-edit-subaward">Details</a>
                                    <a href="#" class="btn btn-default btn-xs uif-delete" data-toggle="true "><span class="sr-only">Delete</span> <span aria-hidden="true" class="icon-trash"></span></a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
			</section>
			</main> 
		</div>

<div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
    <div class="uif-footer-centered-control-group clearfix">
        <div class="global-navigate btn-group">
            <button type="button" href="budget-ng-personnelCosts-persPeriod.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="budget-ng-cost-sharing.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>

                <div class="global-actions btn-group">

            <button type="button" id="" class="btn btn-default">Save</button>
            <button type="button" id="" class="btn btn-default">Reload</button>
                  <button type="button"  id="complete"  data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button>
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
<!-- MODAL budget complete buttons -->
<?php include ('includes/modal-budget-complete.php') ?>
<!-- MODAL add subaward -->
<?php include ('includes/modal-budget-add-subaward.php') ?>
<?php include ('includes/modal-budget-edit-subaward.php') ?>
</body>
</html>
