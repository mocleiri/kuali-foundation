<?php
$page = 'periods-and-totals';
$section = '';
?>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="robots" content="noindex,nofollow" />
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css">
.edit-table input[type="text"]{min-width:100%;}
.td{max-width:6em;}
</style>
</head>

<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span> </button>
                    <a class="navbar-brand" href="index.php">
                    <div class="logoBrand">
                        <h1> <img id="u2elq10" src="../../themes/kboot/images/logo-kc.png" alt="" class="uif-image"> </h1>
                    </div>
                    </a> </div>
                <div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
                    <ul class="nav navbar-nav navbar-right uif-listLayout">
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Researcher </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
                                <div class="row ">
                                    <div class="col-md-12">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText"> Proposal Development</h3>
                                            <ul class="uif-listLayout">
                                                <li> <a href="prop-start.php" class="uif-actionLink" id="umdwwyj" tabindex="0" data-role="Action"> Create a Proposal </a> </li>
                                                <li> <a href="#" class="uif-actionLink" id="umdwwze" tabindex="0" data-role="Action"> Created a Proposal Budget</a> </li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Unit </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Central Admin </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Maintenance </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> System Admin </a> </li>
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
        <?php include('includes/uif-viewHeader-budget.php') ?>
        <!-- // VIEW HEADER  --> 
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="uif-headerText"> <span class="uif-headerText-span"> Periods &amp; Totals </span> </h2>
                            </div>
                            <div class="col-md-6 uif-pagetools">
                                <button class="btn btn-default btn-xs">Reset to period defaults</button>
                                <button class="btn btn-default btn-xs">Recalculate with changes</button>
                            </div>
                        </div>
                    </div>
                </header>
                <table class="edit-table table table-condensed table-bordered uif-has-actions uif-lightTable dataTable">
                    <thead>
                        <tr>
                            <th>Start</th>
                            <th>End</th>
                            <th>Months</th>
                            <th>Total</th>
                            <th>Direct</th>
                            <th>F&amp;A</th>
                            <th>Unrecovered F&amp;A</th>
                            <th>Cost Sharing</th>
                            <th>Cost Limits</th>
                            <th>Direct Limits</th>
                            <th style="width:120px"><span class="sr-only">Actions</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>02/01/2014</td>
                            <td>01/31/2015</td>
                            <td class="text-right">12.0</td>
                            <td class="text-right">$148,000</td>
                            <td class="text-right">$100,000</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td><a href="aaa-tables2.php" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                        </tr>
                        <tr>
                            <td>02/01/2014</td>
                            <td>01/31/2015</td>
                            <td class="text-right">12.0</td>
                            <td class="text-right">$148,000</td>
                            <td class="text-right">$100,000</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td><a href="aaa-tables2.php" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                        </tr>
                        <tr class="warning">
                            <td><input type="text" class="form-control" value="02/01/2014"></td>
                            <td><input type="text" class="form-control" value="01/31/2015"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="12.0"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$148,000"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$100,000"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$0"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$0"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$0"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$0"></td>
                            <td class="text-right"><input type="text" class="form-control text-right" value="$0"></td>
                            <td><a href="aaa-tables1.php" class="btn btn-primary btn-xs">Save</a> <a href="aaa-tables1.php" class="btn btn-default btn-xs">Cancel</a></td>
                        </tr>
                        <tr>
                            <td>02/01/2014</td>
                            <td>01/31/2015</td>
                            <td class="text-right">12.0</td>
                            <td class="text-right">$148,000</td>
                            <td class="text-right">$100,000</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td><a href="aaa-tables2.php" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                        </tr>
                        <tr>
                            <td>02/01/2014</td>
                            <td>01/31/2015</td>
                            <td class="text-right">12.0</td>
                            <td class="text-right">$148,000</td>
                            <td class="text-right">$100,000</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td class="text-right">$0</td>
                            <td><a href="aaa-tables2.php" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr class="active">
                            <td colspan="2" class="text-right">Totals</td>
                            <td class="text-right"><strong>60</strong></td>
                            <td class="text-right"><strong>$740,000</strong></td>
                            <td class="text-right"><strong>$500,000</strong></td>
                            <td class="text-right"><strong>$0</strong></td>
                            <td class="text-right"><strong>$0</strong></td>
                            <td class="text-right"><strong>$0</strong></td>
                            <td class="text-right"><strong>$0</strong></td>
                            <td class="text-right"><strong>$0</strong></td>
                            <td>&nbsp;</td>
                        </tr>
                    </tfoot>
                </table>
            </main>
        </div>
        
        <!-- VIEW FOOTER -->
        
        <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <div class="uif-footer-centered-control-group clearfix">
                <div class="global-navigate btn-group">
                    <button type="button" href="budget-ng-summary2.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="budget-ng-rates.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>
                <div class="global-actions btn-group">
                    <button type="button" id="" class="btn btn-default">Save</button>
                    <button type="button" id="" class="btn btn-default">Reload</button>
                    <button type="button"  id="complete"  data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button>
                </div>
            </div>
        </div>
        
        <!-- DIALOGS/Placeholders --> </div>
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

<!-- different actions for casual user -->
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=no&amp;summary=yes") {
?>

<!-- CASUAL USER LOGIC --> 
<script>
/*

If a user selects summary budget and modular = no, then:

*/
(function($){
	
		




 $('body').on('click','#save-continue', function(e) {
	  e.preventDefault();
	  
	 

	
	
	
if  (($("input.unrecovered").val() != "") && ($("input.cost-sharing").val() != "")){
 document.location.href='budget-ng-cost-sharing.php?modular-budget=no&amp;summary=yes';
} else if ($("input.unrecovered").val() != "") {
 document.location.href='budget-ng-unrecovered-fa.php?modular-budget=no&amp;summary=yes';
} else if  ($("input.cost-sharing").val() != ""){
 document.location.href='budget-ng-cost-sharing.php?modular-budget=no&amp;summary=yes';
}
else {
   document.location.href='budget-ng-summary.php?modular-budget=no&amp;summary=yes'
} 
	

    


});  
  	  
}(jQuery))	
</script> 
<!-- // CASUAL USER LOGIC -->

<?php } ?>
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=yes&amp;summary=yes") {
?>
<script>

(function($){
	
		
 $('body').on('click','#save-continue', function(e) {
	  e.preventDefault();
	  
	 

	
	
	
if  (($("input.unrecovered").val() != "") && ($("input.cost-sharing").val() != "")){
 document.location.href='budget-ng-cost-sharing.php?modular-budget=yes&amp;summary=yes';
} else if ($("input.unrecovered").val() != "") {
document.location.href='budget-ng-unrecovered-fa.php?modular-budget=yes&amp;summary=yes';
} else if  ($("input.cost-sharing").val() != ""){
document.location.href='budget-ng-cost-sharing.php?modular-budget=yes&amp;summary=yes';
}
else {
   document.location.href='budget-ng-modular.php?modular-budget=yes&amp;summary=yes';
} 
	  
	  


  });  
  	  
}(jQuery))	
</script>
<?php } ?>
</div>
<!-- //different buttons for casual user --> 

<!-- MODAL  budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
<?php include ('includes/modal-budget-complete.php') ?>
</body>
</html>