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
        <?php include('includes/uif-viewHeader-budget.php') ?>
        <!-- // VIEW HEADER  --> 
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->   
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper pull-left">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span"> Periods &amp; Totals </span> </h2>
                    </div>
                </header>

                <!--    
                <div class="pull-right"> <a href="#"> <span class="icon-compass"></span> Guide Me</a></div>
                -->

                <table id="u569ish_line0-" class=" edit-table table table-condensed table-bordered" role="presentation">
                    <thead>
                        <tr class="active">
                            <th class="shortTd">Start</th>
                            <th class="shortTd">End</th>
                            <th class="shortTd">Months</th>
                            <th>Total</th>
                            <th>Direct</th>
                            <th>F&amp;A</th>
                            <th>Unrecovered F&amp;A</th>
                            <th>Cost Sharing</th>
                            <th>Cost Limits</th>
                            <th>Direct Limits</th>
                            <th><span class="sr-only">Actions</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="uif-new-row">
                            <td><span data-edit-type="date">02/01/2014</span></td>
                            <td><span data-edit-type="date">01/31/2015</span></td>
                            <td class="not-editable"><div class="pull-right">12.0</div></td>
                            <td><label for="line_1_total"><span class="sr-only">Period total</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_direct" id="line_1_direct">
                                </label></td>
                            <td><label for="line_1_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right " name="line_1_fa" id="line_1_fa">
                                </label></td>
                            <td><label for="line_1_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="pull-right  unrecovered" name="line_1_unrecovered" id="line_1_unrecovered">
                                </label></td>
                            <td><label for="line_1_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="cost-sharing pull-right " name="line_1_cost-sharing" id="line_1_cost-sharing" value="">
                                </label></td>
                            <td><label for="line_1_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right " name="line_1_cost-limit" id="line_1_cost-limit">
                                </label></td>
                            <td><label for="line_1_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_direct-limit" id="line_1_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td><span data-edit-type="date">02/01/2015</span></td>
                            <td><span data-edit-type="date">01/31/2016</span></td>
                            <td class="not-editable"><div class="pull-right">12.0</div></td>
                            <td><label for="line_2_total"><span class="sr-only">Period total</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_total" id="line_2_total">
                                </label></td>
                            <td><label for="line_2_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_direct" id="line_2_direct">
                                </label></td>
                            <td><label for="line_2_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_fa" id="line_2_fa">
                                </label></td>
                            <td><label for="line_2_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="unrecovered pull-right " name="line_2_unrecovered" id="line_2_unrecovered">
                                </label></td>
                            <td><label for="line_2_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="cost-sharing pull-right " name="line_2_cost-sharing" id="line_2_cost-sharing">
                                </label></td>
                            <td><label for="line_2_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_cost-limit" id="line_2_cost-limit">
                                </label></td>
                            <td><label for="line_2_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_direct-limit" id="line_2_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td><span data-edit-type="date">02/01/2016</span></td>
                            <td><span data-edit-type="date">01/31/2017</span></td>
                            <td class="not-editable"><div class="pull-right">12.0</div></td>
                            <td><label for="line_3_total"><span class="sr-only">Period total</span>
                                    <input type="text" size="3" class="pull-right" name="line_3_total" id="line_3_total">
                                </label></td>
                            <td><label for="line_3_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_3_direct" id="line_3_direct">
                                </label></td>
                            <td><label for="line_3_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_3_fa" id="line_3_fa">
                                </label></td>
                            <td><label for="line_3_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="pull-right  unrecovered" name="line_3_unrecovered" id="line_3_unrecovered">
                                </label></td>
                            <td><label for="line_3_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="pull-right  cost-sharing" name="line_3_cost-sharing" id="line_3_cost-sharing">
                                </label></td>
                            <td><label for="line_3_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_3_cost-limit" id="line_3_cost-limit">
                                </label></td>
                            <td><label for="line_3_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_3_direct-limit" id="line_3_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td><span data-edit-type="date"></span>02/01/2017</td>
                            <td><span data-edit-type="date"></span>01/31/2018</td>
                            <td class="not-editable"><div class="pull-right">12.0</div></td>
                            <td><label for="line_4_total"><span class="sr-only">Period total</span>
                                    <input type="text" size="3" class="pull-right" name="line_4_total" id="line_4_total">
                                </label></td>
                            <td><label for="line_4_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_4_direct" id="line_4_direct">
                                </label></td>
                            <td><label for="line_4_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_4_fa" id="line_4_fa">
                                </label></td>
                            <td><label for="line_4_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="pull-right  unrecovered" name="line_4_unrecovered" id="line_4_unrecovered">
                                </label></td>
                            <td><label for="line_4_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="pull-right  cost-sharing" name="line_4_cost-sharing" id="line_4_cost-sharing">
                                </label></td>
                            <td><label for="line_4_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_4_cost-limit" id="line_4_cost-limit">
                                </label></td>
                            <td><label for="line_4_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_4_direct-limit" id="line_4_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td><span data-edit-type="date">02/01/2018</span></td>
                            <td><span data-edit-type="date">01/31/2019</span></td>
                            <td class="not-editable"><div class="pull-right">12.0</div></td>
                            <td><label for="line_5_total"><span class="sr-only">Period total</span>
                                    <input type="text" size="3" class="pull-right" name="line_5_total" id="line_5_total">
                                </label></td>
                            <td><label for="line_5_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_5_direct" id="line_5_direct">
                                </label></td>
                            <td><label for="line_5_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_5_fa" id="line_5_fa">
                                </label></td>
                            <td><label for="line_5_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="pull-right  unrecovered" name="line_5_unrecovered" id="line_5_unrecovered">
                                </label></td>
                            <td><label for="line_5_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="pull-right  cost-sharing" name="line_5_cost-sharing" id="line_5_cost-sharing">
                                </label></td>
                            <td><label for="line_5_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_5_cost-limit" id="line_5_cost-limit">
                                </label></td>
                            <td><label for="line_5_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_5_direct-limit" id="line_5_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                            <tr class="uif-new-row">
                            <td><label for="line_7_start"><span class="sr-only">Period start</span>
                                    <input type="date" class="form-control input-sm" size="7" name="line_7_start" id="line_7_start" placeholder="mm/dd/yyyy">
                                </label></td>
                            <td><label for="line_7_end"><span class="sr-only">Period end</span>
                                    <input type="date" class="form-control input-sm" size="7" name="line_7_end" id="line_7_end" placeholder="mm/dd/yyyy">
                                </label></td>
                            <td></td>
                            <td><label for="line_7_total"><span class="sr-only">Period total</span>
                                    <input type="text" size="3" class="pull-right" name="line_7_total" id="line_7_total">
                                </label></td>
                            <td><label for="line_7_direct"><span class="sr-only">Period direct</span>
                                    <input type="text" size="3" class="pull-right" name="line_7_direct" id="line_7_direct">
                                </label></td>
                            <td><label for="line_7_fa"><span class="sr-only">Period F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_7_fa" id="line_7_fa">
                                </label></td>
                            <td><label for="line_7_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span>
                                    <input type="text" size="3" class="pull-right  unrecovered" name="line_7_unrecovered" id="line_7_unrecovered">
                                </label></td>
                            <td><label for="line_7_cost-sharing"><span class="sr-only">Period Cost sharing</span>
                                    <input type="text" size="3" class="pull-right  cost-sharing" name="line_7_cost-sharing" id="line_7_cost-sharing">
                                </label></td>
                            <td><label for="line_7_cost-limit"><span class="sr-only">Period Cost limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_7_cost-limit" id="line_7_cost-limit">
                                </label></td>
                            <td><label for="line_7_direct-limit"><span class="sr-only">Period Direct limit</span>
                                    <input type="text" size="3" class="pull-right" name="line_7_direct-limit" id="line_7_direct-limit">
                                </label></td>
                            <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr class="active">
                            <td></td>
                            <td></td>
                            <td class="uif-new-row"><div class="pull-right">60.0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td><div class="pull-right">$0</div></td>
                            <td></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs">Reset to period defaults</button>
                    <button class="btn btn-default btn-xs">Recalculate with changes</button>
<!--                    <div class="btn-group">-->
<!--                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Actions <span class="caret"></span> </button>-->
<!--                        <ul class="dropdown-menu" role="menu">-->
<!--                            <li><a href="#">Reset to period defaults</a></li>-->
<!--                            <li><a href="#">Recalculate with changes</a></li>-->
<!--                        </ul>-->
<!--                    </div>-->
                </div>
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