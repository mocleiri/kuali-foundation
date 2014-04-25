<?php
$page = 'modular';
$section = '';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Kuali :: Fluid Application Header</title>

<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
	<style type="text/css">
.data-row-table th {
	padding: 5px;
	text-align: right;
}
.pad5 td, .pad5 th {
	padding: 5px;
}
.pull-left {
	width: 20em;
}
table {
	width: 100%;
}
.btn-actions {
	margin: 0px 0 10px;
}
input {
	max-width: 200px;
}
.direct-cost-table {
	max-width: 500px;
	border-collapse: collapse;
}
.direct-cost-table th, .direct-cost-table td {
	max-width: 250px;
}

	</style>
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
<!-- VIEW CONTENT -->
		<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
<!-- VIEW NAVIGATION -->
<?php include ('includes/uif-navigation-budget.php') ?>
			<div id="Uif-BreadcrumbUpdate" style="display:;">
			</div>
			<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;"> 
			<header class="clearfix uif-header-contentWrapper">
				<div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
					<h2 class="uif-headerText">
						<span class="uif-headerText-span">Modular</span>
					</h2>
				</div>
			</header>
			<p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">
				Review the modular summary below for your NIH proposal submission. 
			</p>
			<br>

<!--TABS -->
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#p1" data-toggle="tab">Period 1 </a></li>
				<li class=""><a href="#p2" data-toggle="tab">Period 2</a></li>
				<li><a href="#p3" data-toggle="tab">Period 3</a></li>
				<li><a href="#p4" data-toggle="tab">Period 4</a></li>
				<li><a href="#p5" data-toggle="tab">Period 5</a></li>
			</ul>
			<div id="my-tab-content" class="tab-content">

<!-- period content -->
				<div class="tab-pane active" id="p1">
					<h3 class="">
						Period 1 <small>(1/12/14 - 1/11/15)</small>
					</h3>

<!-- sections -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-budget-overview" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Modular Budget Overview</span></a>
							</h3>
						</header>
						<div id="modular-budget-overview" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="table table-condensed table-bordered">
								<thead>
									<tr class="active">
										<th>Period 1 Start Date</th>
										<th>Period 1 End Date</th>
										<th>Period 1 Total Requested Cost</th>
										<th>Project Total Requested Cost</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1/12/14</td>
										<td>1/11/15</td>
										<td>$500.00</td>
										<td>$2500.00</td>
								</tbody>
							</table>
						</div>
					</section>

<!-- -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-direct-cost-1" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Direct Cost </span></a>
							</h3>
						</header>
						<div id="modular-direct-cost-1" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="direct-cost-table pad5">
								<tr>
									<th class=" text-right">Direct Cost Less Consortium F&amp;A:</th>
									<td>
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right">Consortium F&amp;A:</th>
									<td class="input-group">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="1000.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right"> Total Direct Cost:</th>
									<td class=" text-right">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input readonly type="number" value="1500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
							</table>
						</div>
					</section>

<!--F&amp;A: -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-f-a" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> F&amp;A</span></a>
							</h3>
						</header>
						<div id="modular-f-a" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class=" pad5 table table-condensed table-bordered">
								<thead>
									<tr>
										<th>F&amp;A Rate Type</th>
										<th>F&amp;A Rate</th>
										<th>F&amp;A Base</th>
										<th>Funds Requested</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-1"> 
										<input id="rate-1" type="text" value="" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-1"><span class="input-group-addon">$</span> 
										<input name="base-1" id="base-1" type="text" value="" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-2"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-2" type="text" value="" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a></td>
									</tr>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-100"> 
										<input id="rate-100" type="text" value="10" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-1000"><span class="input-group-addon">$</span> 
										<input id="base-1000" type="text" value="100.00" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-100"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-100" type="text" value="10.00" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-minus" aria-hidden="true"></span>Delete</a></td>
									</tr>
								</tbody>
								<tfoot> 
								<tr class="active">
									<td colspan="3"><strong class="pull-right">Total:</strong></td>
									<td><label class="input-group input-group-sm"><span class="input-group-addon">$</span> 
									<input readonly type="text" value="10.00" class="form-control text-right">
									</label></td>
									<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Recalculate</a></td>
								</tr>
								</tfoot> 
							</table>
						</div>
					</section>
					<div class="pull-right btn-actions">
						<a class="btn btn-default  btn-xs" href="#"><span class="icon-tab" aria-hidden="true"></span>Sync</a> <a class="btn btn-default  btn-xs" href="#"><span class="icon-refresh2" aria-hidden="true"></span>Reload</a> <a class="btn btn-default btn-xs" href="#"><span class="icon-save" aria-hidden="true"></span>Save</a> 
					</div>

<!-- // sections -->
				</div>
<!--  // period content -->
				<div class="tab-pane" id="p2">
					<h3 class="">
						Period 2 <small>(1/12/15 - 1/11/16)</small>
					</h3>

<!-- sections -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-budget-overview-2" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Modular Budget Overview</span></a>
							</h3>
						</header>
						<div id="modular-budget-overview-2" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="table table-condensed table-bordered">
								<thead>
									<tr class="active">
										<th>Period 1 Start Date</th>
										<th>Period 1 End Date</th>
										<th>Period 1 Total Requested Cost</th>
										<th>Project Total Requested Cost</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1/12/15</td>
										<td>1/11/16</td>
										<td>$500.00</td>
										<td>$2500.00</td>
								</tbody>
							</table>
						</div>
					</section>

<!-- -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-direct-cost" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Direct Cost </span></a>
							</h3>
						</header>
						<div id="modular-direct-cost" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="direct-cost-table pad5">
								<tr>
									<th class=" text-right">Direct Cost Less Consortium F&amp;A:</th>
									<td>
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right">Consortium F&amp;A:</th>
									<td class="input-group">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="1000.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right"> Total Direct Cost:</th>
									<td class=" text-right">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input readonly type="number" value="1500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
							</table>
						</div>
					</section>

<!--F&amp;A: -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-f-a-2" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> F&amp;A</span></a>
							</h3>
						</header>
						<div id="modular-f-a-2" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class=" pad5 table table-condensed table-bordered">
								<thead>
									<tr>
										<th>F&amp;A Rate Type</th>
										<th>F&amp;A Rate</th>
										<th>F&amp;A Base</th>
										<th>Funds Requested</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-22a"> 
										<input id="rate-22a" type="text" value="" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-22"><span class="input-group-addon">$</span> 
										<input id="base-22" type="text" value="" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-22"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-22" type="text" value="" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a></td>
									</tr>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-22"> 
										<input id="rate-22" type="text" value="10" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-22a"><span class="input-group-addon">$</span> 
										<input id="base-22a" type="text" value="100.00" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-22l"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-22l" type="text" value="10.00" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-minus" aria-hidden="true"></span>Delete</a></td>
									</tr>
								</tbody>
								<tfoot> 
								<tr class="active">
									<td colspan="3"><strong class="pull-right">Total:</strong></td>
									<td><label class="input-group input-group-sm"><span class="input-group-addon">$</span> 
									<input readonly type="text" value="10.00" class="form-control text-right">
									</label></td>
									<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Recalculate</a></td>
								</tr>
								</tfoot> 
							</table>
					</section>
					<div class="pull-right btn-actions">
						<a class="btn btn-default  btn-xs" href="#"><span class="icon-tab" aria-hidden="true"></span>Sync</a> <a class="btn btn-default  btn-xs" href="#"><span class="icon-refresh2" aria-hidden="true"></span>Reload</a> <a class="btn btn-default btn-xs" href="#"><span class="icon-save" aria-hidden="true"></span>Save</a> 
					</div>

<!-- // sections -->
				</div>
				<div class="tab-pane" id="p3">
					<h3 class="">
						Period 3 <small>(1/12/16 - 1/11/17)</small>
					</h3>

<!-- sections -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-budget-overview-3" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Modular Budget Overview</span></a>
							</h3>
						</header>
						<div id="modular-budget-overview-3" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="table table-condensed table-bordered">
								<thead>
									<tr class="active">
										<th>Period 1 Start Date</th>
										<th>Period 1 End Date</th>
										<th>Period 1 Total Requested Cost</th>
										<th>Project Total Requested Cost</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1/12/16</td>
										<td>1/11/17</td>
										<td>$500.00</td>
										<td>$2500.00</td>
								</tbody>
							</table>
						</div>
					</section>

<!-- -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-direct-cost-4" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Direct Cost </span></a>
							</h3>
						</header>
						<div id="modular-direct-cost-4" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="direct-cost-table pad5">
								<tr>
									<th class=" text-right">Direct Cost Less Consortium F&amp;A:</th>
									<td>
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right">Consortium F&amp;A:</th>
									<td class="input-group">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="1000.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right"> Total Direct Cost:</th>
									<td class=" text-right">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input readonly type="number" value="1500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
							</table>
						</div>
					</section>

<!--F&amp;A: -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-f-a" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> F&amp;A</span></a>
							</h3>
						</header>
						<div id="modular-f-a-4" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class=" pad5 table table-condensed table-bordered">
								<thead>
									<tr>
										<th>F&amp;A Rate Type</th>
										<th>F&amp;A Rate</th>
										<th>F&amp;A Base</th>
										<th>Funds Requested</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-3"> 
										<input id="rate-3" type="text" value="" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-3"><span class="input-group-addon">$</span> 
										<input id="base-3" type="text" value="" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-3"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-3" type="text" value="" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a></td>
									</tr>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-3b"> 
										<input name="rate-3b" id="rate-3b" type="text" value="10" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-3b"><span class="input-group-addon">$</span> 
										<input name="base-3" id="base-3b" type="text" value="100.00" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-3b"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-3b" type="text" value="10.00" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-minus" aria-hidden="true"></span>Delete</a></td>
									</tr>
								</tbody>
								<tfoot> 
								<tr class="active">
									<td colspan="3"><strong class="pull-right">Total:</strong></td>
									<td><label class="input-group input-group-sm"><span class="input-group-addon">$</span> 
									<input readonly type="text" value="10.00" class="form-control text-right">
									</label></td>
									<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Recalculate</a></td>
								</tr>
								</tfoot> 
							</table>
						</div>
					</section>
					<div class="pull-right btn-actions">
						<a class="btn btn-default  btn-xs" href="#"><span class="icon-tab" aria-hidden="true"></span>Sync</a> <a class="btn btn-default  btn-xs" href="#"><span class="icon-refresh2" aria-hidden="true"></span>Reload</a> <a class="btn btn-default btn-xs" href="#"><span class="icon-save" aria-hidden="true"></span>Save</a> 
					</div>

<!-- // sections -->
				</div>
				<div class="tab-pane" id="p4">
					<h3 class="">
						Period 4 <small>(1/12/17 - 1/11/18)</small>
					</h3>

<!-- sections -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-budget-overview-4" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Modular Budget Overview</span></a>
							</h3>
						</header>
						<div id="modular-budget-overview-4" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="table table-condensed table-bordered">
								<thead>
									<tr class="active">
										<th>Period 1 Start Date</th>
										<th>Period 1 End Date</th>
										<th>Period 1 Total Requested Cost</th>
										<th>Project Total Requested Cost</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1/12/17</td>
										<td>1/11/18</td>
										<td>$500.00</td>
										<td>$2500.00</td>
								</tbody>
							</table>
						</div>
					</section>

<!-- -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-direct-cost-4b" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Direct Cost </span></a>
							</h3>
						</header>
						<div id="modular-direct-cost-4b" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="direct-cost-table pad5">
								<tr>
									<th class=" text-right">Direct Cost Less Consortium F&amp;A:</th>
									<td>
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right">Consortium F&amp;A:</th>
									<td class="input-group">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="1000.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right"> Total Direct Cost:</th>
									<td class=" text-right">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input readonly type="number" value="1500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
							</table>
						</div>
					</section>

<!--F&amp;A: -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-f-a-4b" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> F&amp;A</span></a>
							</h3>
						</header>
						<div id="modular-f-a-4b" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class=" pad5 table table-condensed table-bordered">
								<thead>
									<tr>
										<th>F&amp;A Rate Type</th>
										<th>F&amp;A Rate</th>
										<th>F&amp;A Base</th>
										<th>Funds Requested</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-4"> 
										<input name="rate-4" id="rate-4" type="text" value="" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-4"><span class="input-group-addon">$</span> 
										<input name="base-4" id="base-4" type="text" value="" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-4"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-4" name="funds-requested-4" type="text" value="" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a></td>
									</tr>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-4b"> 
										<input name="rate-4b" id="rate-4b" type="text" value="10" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-4b"><span class="input-group-addon">$</span> 
										<input name="base-4b" id="base-4b" type="text" value="100.00" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-4b"><span class="input-group-addon">$</span> 
										<input readonly name="funds-requested-4b" id="funds-requested-4b" type="text" value="10.00" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-minus" aria-hidden="true"></span>Delete</a></td>
									</tr>
								</tbody>
								<tfoot> 
								<tr class="active">
									<td colspan="3"><strong class="pull-right">Total:</strong></td>
									<td><label class="input-group input-group-sm"><span class="input-group-addon">$</span> 
									<input readonly type="text" value="10.00" class="form-control text-right">
									</label></td>
									<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Recalculate</a></td>
								</tr>
								</tfoot> 
							</table>
						</div>
					</section>
					<div class="pull-right btn-actions">
						<a class="btn btn-default  btn-xs" href="#"><span class="icon-tab" aria-hidden="true"></span>Sync</a> <a class="btn btn-default  btn-xs" href="#"><span class="icon-refresh2" aria-hidden="true"></span>Reload</a> <a class="btn btn-default btn-xs" href="#"><span class="icon-save" aria-hidden="true"></span>Save</a> 
					</div>

<!-- // sections -->
				</div>
				<div class="tab-pane" id="p5">
					<h3 class="">
						Period 5 <small>(1/12/18 - 1/11/19)</small>
					</h3>

<!-- sections -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-budget-overview-5" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Modular Budget Overview</span></a>
							</h3>
						</header>
						<div id="modular-budget-overview-5" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="table table-condensed table-bordered">
								<thead>
									<tr class="active">
										<th>Period 1 Start Date</th>
										<th>Period 1 End Date</th>
										<th>Period 1 Total Requested Cost</th>
										<th>Project Total Requested Cost</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1/12/18</td>
										<td>1/11/19</td>
										<td>$500.00</td>
										<td>$2500.00</td>
								</tbody>
							</table>
						</div>
					</section>

<!-- -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-direct-cost-5" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> Direct Cost </span></a>
							</h3>
						</header>
						<div id="modular-direct-cost-5" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class="direct-cost-table pad5">
								<tr>
									<th class=" text-right">Direct Cost Less Consortium F&amp;A:</th>
									<td>
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right">Consortium F&amp;A:</th>
									<td class="input-group">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input type="number" value="1000.00" class="form-control text-right">
									</div>
									</td>
								</tr>
								<tr>
									<th class=" text-right"> Total Direct Cost:</th>
									<td class=" text-right">
									<div class="input-group">
										<span class="input-group-addon">$</span> 
										<input readonly type="number" value="1500.00" class="form-control text-right">
									</div>
									</td>
								</tr>
							</table>
						</div>
					</section>

<!--F&amp;A: -->
					<section class="uif-disclosure uif-boxLayoutVerticalItem clearfix">
						<header class="uif-sectionHeader">
							<h3 class="uif-headerText">
								<a data-role="disclosureLink" data-linkfor="modular-f-a-5" href="#" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span class="icon-caret-down"></span><span style="display:none;" class="icon-caret-right"></span> F&amp;A</span></a>
							</h3>
						</header>
						<div id="modular-f-a-5" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
							<table class=" pad5 table table-condensed table-bordered">
								<thead>
									<tr>
										<th>F&amp;A Rate Type</th>
										<th>F&amp;A Rate</th>
										<th>F&amp;A Base</th>
										<th>Funds Requested</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-5"> 
										<input name="rate-5" id="rate-5" type="text" value="" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-5"><span class="input-group-addon">$</span> 
										<input name="base-5" id="base-5" type="text" value="" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-5"><span class="input-group-addon">$</span> 
										<input readonly name="funds-requested-5" id="funds-requested-5" type="text" value="" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Add</a></td>
									</tr>
									<tr>
										<td>
										<select class="form-control input-sm">
											<option selected="selected" value="MTDC">MTDC</option>
											<option value="TDC">TDC</option>
											<option value="S&amp;W">S&amp;W</option>
											<option value="Fund with Transaction Fee (FUNSN)">Fund with Transaction Fee (FUNSN)</option>
											<option value="MTDC - AWARD">MTDC - AWARD</option>
										</select>
										</td>
										<td><label class="input-group" for="rate-5b"> 
										<input name="rate-5b" id="rate-5b" type="text" value="10" class="form-control text-right">
										<span class="input-group-addon">%</span></label></td>
										<td><label class="input-group" for="base-5b"><span class="input-group-addon">$</span> 
										<input name="base-5b" id="base-5b" type="text" value="100.00" class="form-control text-right">
										</label></td>
										<td><label class="input-group" for="funds-requested-5b"><span class="input-group-addon">$</span> 
										<input readonly id="funds-requested-5b" name="funds-requested-5b" type="text" value="10.00" class="form-control text-right">
										</label></td>
										<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-minus" aria-hidden="true"></span>Delete</a></td>
									</tr>
								</tbody>
								<tfoot> 
								<tr class="active">
									<td colspan="3"><strong class="pull-right">Total:</strong></td>
									<td><label class="input-group input-group-sm"><span class="input-group-addon">$</span> 
									<input readonly type="text" value="10.00" class="form-control text-right">
									</label></td>
									<td><a class="btn btn-default btn-xs" data-target="#modal-add-personnel1" data-toggle="modal" href="#"><span class="icon-plus" aria-hidden="true"></span>Recalculate</a></td>
								</tr>
								</tfoot> 
							</table>
						</div>
					</section>
					<div class="pull-right btn-actions">
						<a class="btn btn-default  btn-xs" href="#"><span class="icon-tab" aria-hidden="true"></span>Sync</a> <a class="btn btn-default  btn-xs" href="#"><span class="icon-refresh2" aria-hidden="true"></span>Reload</a> <a class="btn btn-default btn-xs" href="#"><span class="icon-save" aria-hidden="true"></span>Save</a> 
					</div>

<!-- // sections -->
				</div>
			</div>
<!--//END TABS-->
			</main> 
		</div>
		<div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
			<div class="uif-footer-centered-control-group clearfix">
				<div class="global-navigate btn-group">
					<button type="button" href="budget-ng-income.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button> 
					

					
					<button type="button" href="budget-ng-summary.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button> 
					
			
				
			
					
					
				</div>
				<div class="global-actions btn-group">
					<button type="button" id="" class="btn btn-default">Save</button> <button type="button" id="" class="btn btn-default">Reload</button> <button type="button" id="complete" data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button> 
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
<!-- MODAL  budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL  budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
<!-- MODAL budget complete buttons -->
<?php include ('includes/modal-budget-complete.php') ?>

<!--casual user -->
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=yes&amp;detail=yes") {
?>
<!--  casual user view -->
<script>
(function($){
	
 $('body').on('click','#save-continue', function(e) {

   document.location.href='budget-ng-summary.php?modular-budget=yes&amp;detail=yes'; 

});  
  	  
}(jQuery))	
</script>

<?php } ?>



<!--casual user -->
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=yes&amp;summary=yes") {
?>
<!--  casual user view -->
<script>
(function($){
	
 $('body').on('click','#save-continue', function(e) {

   document.location.href='budget-ng-summary.php?modular-budget=yes&amp;summary=yes'; 

});  
  	  
}(jQuery))	
</script>

<?php } ?>

</body>
</html>
