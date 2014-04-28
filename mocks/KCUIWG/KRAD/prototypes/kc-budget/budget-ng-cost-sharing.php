<?php
$section = 'institute';
$page = 'cost-sharing';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Kuali :: Fluid Application Header</title>

<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
	<style type="text/css">
	
.data-row-table th{ padding:5px; text-align:right; font-weight:bold; }
.edit-table input,.edit-table select{min-width: 10em;width:100%; text-align: right;}
.edit-table td{text-align: right;}
#u19v7dpm{padding:1em 0 ;}

tfoot th[scope="row"] strong{ float:right; margin-top:5px;}
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
						<span class="uif-headerText-span">Cost Sharing</span> 
					</h2>
				</div>
			</header>
			<p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">
				Assign and distribute any additional unallocated expenses to stakeholders, institutions, or other individuals. 
			</p>
			<br>
			<section>
				<table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
					<thead>
						<tr role="row">
							<th class="sorting" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Project Period</label></th>
							<th class="sorting" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label">Percentage</label></th>
							<th class="sorting" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Source Account</label></th>
							<th class="sorting" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Amount</label></th>
							<th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
					</thead>
					<tbody role="alert" aria-live="polite" aria-relevant="all">
						<tr class="not-deletable">
							<td>1</td>
							<td>6</td>
							<td class="text-left">Cate Start Up</td>
							<td>500.00</td>
							<td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
						</tr>
						<tr class="uif-new-row">
							<td><label for="line_3_total"><span class="sr-only">Period total</span> 
							<input type="text" size="3" name="line_3_total" id="line_3_total" value="2">
							</label></td>
							<td><label for="line_3_direct"><span class="sr-only">Period direct</span> 
							<input type="text" size="3" name="line_3_direct" id="line_3_direct">
							</label></td>
							<td><label for="line_3_fa"><span class="sr-only">Period F&amp;A</span> 
							<input type="text" size="3" name="line_3_fa" id="line_3_fa">
							</label></td>
							<td><label for="line_3_cost-limit"><span class="sr-only">Period Cost limit</span> 
							<input type="text" size="3" name="line_3_cost-limit" id="line_3_cost-limit">
							</label></td>
							<td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
						</tr>

<!-- 							<tr class="uif-new-row">
							<td><label for="line_4_total"><span class="sr-only">Period total</span> 
							 <input type="text" size="4" name="line_4_total" id="line_4_total" value="3">	
							</label></td>
							<td><label for="line_4_direct"><span class="sr-only">Period direct</span> 
							<input type="text" size="4" name="line_4_direct" id="line_4_direct">
							</label></td>
							<td><label for="line_4_fa"><span class="sr-only">Period F&amp;A</span> 
							<input type="text" size="4" name="line_4_fa" id="line_4_fa">
							</label></td>
						
						
							<td><label for="line_4_cost-limit"><span class="sr-only">Period Cost limit</span> 
							<input type="text" size="4" name="line_4_cost-limit" id="line_4_cost-limit">
							</label></td>
							<td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
						</tr>
						
									<tr class="uif-new-row">
							<td><label for="line_5_total"><span class="sr-only">Period total</span> 
							 <input type="text" size="5" name="line_5_total" id="line_5_total" value="4">	
							</label></td>
							<td><label for="line_5_direct"><span class="sr-only">Period direct</span> 
							<input type="text" size="5" name="line_5_direct" id="line_5_direct">
							</label></td>
							<td><label for="line_5_fa"><span class="sr-only">Period F&amp;A</span> 
							<input type="text" size="5" name="line_5_fa" id="line_5_fa">
							</label></td>
						
						
							<td><label for="line_5_cost-limit"><span class="sr-only">Period Cost limit</span> 
							<input type="text" size="5" name="line_5_cost-limit" id="line_5_cost-limit">
							</label></td>
							<td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
						</tr>
						
						-->
					</tbody>
					<tfoot class="active"> 
					<tr>
						<th class="text-right" colspan="3"  scope="row"><strong>Total Allocated:</strong></th>
						<td>
						<input type="text" size="5" name="line_5_cost-limit" id="line_5_cost-limit" value="500.00" class="text-right">
						</td>
						<td></td>
					</tr>
					<tr>
						<th class="text-right" colspan="3"  scope="row"><strong>Unallocated:</strong></th>
						<td>
						<input type="text" size="5" name="line_5_cost-limit" id="line_5_cost-limit" value="" class="text-right">
						</td>
						<td></td>
					</tr>
					</tfoot> 
				</table>

<!-- summary -->
				<div class="well well-sm" style="margin-top:-1.4em;">
					<h4>
						Cost Sharing Summary 
					</h4>
					<table class="data-row-table ">
						<tr>
							<th scope="row">Period 1</th>
							<td>$100</td>
						</tr>
						<tr>
							<th scope="row">Period 2</th>
							<td>$100</td>
						</tr>
						<tr>
							<th scope="row">Period 3</th>
							<td>$100</td>
						</tr>
						<tr>
							<th scope="row">Period 4</th>
							<td>$100</td>
						</tr>
						<tr>
							<th scope="row">Period 5</th>
							<td>$100</td>
						</tr>
						<tr>
							<th scope="row">Total:</th>
							<td>$500</td>
						</tr>
					</table>
				</div>

<!--//summary  -->                      <div class="pull-right">
                    <button class="btn btn-default btn-xs">Reset to Default</button>
                    <button class="btn btn-default btn-xs">Recalculate</button>
                </div> 
			</section>
			</main> 
		</div>

        <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <div class="uif-footer-centered-control-group clearfix">
                <div class="global-navigate btn-group">
                <button type="button" href="budget-ng-subawards.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="budget-ng-unrecovered-fa.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>

                <div class="global-actions btn-group">

                    <button type="button" id="" class="btn btn-default">Save</button>
                    <button type="button" id="" class="btn btn-default">Reload</button>
                   <button type="button"  id="complete"  data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button>
                </div>
            </div>
        </div>


<!-- //different buttons for casual user -->
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

<!-- casual user -->
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=no&amp;summary=yes") {
?>

<script>

(function($){
	
		
 $('body').on('click','#save-continue', function(e) {
	  e.preventDefault();
	  
	 
  document.location.href='budget-ng-summary.php?modular-budget=no&amp;summary=yes';

  });  
  	  
}(jQuery))	
</script>

<?php } ?>

<!-- casual user -->
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=yes&amp;summary=yes") {
?>

<script>

(function($){
	
		
 $('body').on('click','#save-continue', function(e) {
	  e.preventDefault();
	  
	 
  document.location.href='budget-ng-modular.php?modular-budget=yes&amp;summary=yes';

  });  
  	  
}(jQuery))	
</script>

<?php } ?>	

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
</body>
</html>
