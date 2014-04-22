<?php
$page = 'non-personnel';
$section = '';
?>
<!DOCTYPE HTML>
<html lang=en>
<head>
<meta charset=UTF-8>
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky=true class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role=navigation>
                <div class="navbar-header">
                    <button type=button class="navbar-toggle" data-toggle=collapse data-target=.navbar-ex1-collapse><span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span></button>
                    <a class="navbar-brand" href="index.php">
                    <div class="logoBrand">
                        <h1><img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"> </h1>
                    </div>
                    </a></div>
                <div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
                    <ul class="nav navbar-nav navbar-right uif-listLayout">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Researcher </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
                                <div class="row ">
                                    <div class="col-md-12">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText">Proposal Development</h3>
                                            <ul class="uif-listLayout">
                                                <li><a href="prop-start.php" class="uif-actionLink" id="umdwwyj" tabindex=0 data-role=Action> Create a Proposal </a></li>
                                                <li><a href="#" class="uif-actionLink" id="umdwwze" tabindex=0 data-role=Action> Created a Proposal Budget</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Unit </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Central Admin </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Maintenance </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> System Admin </a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div id="upils8b" class="uif-cssGridGroup toolbar">
            <div class="container">
                <div class="row ">
                    <div class="col-md-12">
                        <div id="u1i3m5yh" class="uif-listGroup" data-parent=upils8b>
                            <ul class="uif-listLayout nav pull-right">
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qku" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-ActionListView&quot;);"> Action List </a></li>
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qlp" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-DocSearchView&quot;);"> Doc Search </a></li>
                                <li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Backdoor Login <span class="caret"></span> </a>
                                    <ul class="dropdown-menu uif-listLayout">
                                        <li><a href="#" class="uif-actionLink" id="u101xf6k" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Preferences </a></li>
                                        <li><a href="#" class="uif-actionLink" id="u101xf7f" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Logout </a></li>
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
<form id="kualiForm" action="../kr-krad/uicomponents" method=post accept-charset=UTF-8>
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS -->
        <!-- VIEW HEADER -->
        <?php include ('includes/uif-viewHeader.php') ?>
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages=false data-role=Page data-parent=LabsProposal style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for=LabsProposal-Page>
                        <h2 class="uif-headerText"><span class="uif-headerText-span">Non-Personnel Costs </span></h2>
                    </div>
                </header>

                <!--
                <div class="pull-right"> <a href="#"> <span class="icon-compass"></span> Guide Me</a></div>
                -->

                <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Add and configure non-personnel items for this budget period.</p>
                <br>
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#p1" data-toggle="tab">Period 1 </a></li>
                    <li class=""><a href="#p2" data-toggle="tab" class="tabWarning" >Period 2</a></li>
                    <li><a href="#p3" data-toggle="tab" class="tabWarning">Period 3</a></li>
                    <li><a href="#p4" data-toggle="tab" class="tabWarning">Period 4</a></li>
                    <li><a href="#p5" data-toggle="tab" class="tabWarning">Period 5</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="p1">
                        <h3 class="">Period 1 <small>(1/12/14 - 1/11/15)</small></h3>
                        <h4>Assign Non-Personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="">Category</label>
                                        <select class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment</option>
                                            <option>Travel</option>
                                            <option>Participant Support</option>
                                            <option>Other</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="">Object Code Name</label>
                                        <select class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment</option>
                                            <option>Equipment Rental</option>
                                            <option>Fabricated Equipment</option>
                                            <option>Reactor User</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="">Total Base Cost</label>
                                        <input type="text" class="form-control" placeholder="0.00">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-right"><a href="#" type="submit" class="btn btn-primary btn-xs">Assign to Period 1</a></div>
                            </div>
                        </div>
                        <h4 class="pull-left" >Assigned Non-personnel</h4>
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default"  data-toggle="modal" data-target="#applyRates">Expand All Panels</a> <a href="#" class="btn btn-xs btn-default"  data-toggle="modal" data-target="#applyRates">Copy Non-Personnel to All Periods...</a></div>

                        <section id="a1" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                            <header id="u1l3ufy30" class="uif-sectionHeader" data-header_for="a1">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTena1" href="#" id="a1_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="a1_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="a1_toggle_col" class="icon-caret-right"></span>Equipment</span></a></h3>
                            </header>
                            <div id="facSalTena1" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
                                
                                <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
                                    <thead>
                                        <tr role="row">
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label=""><label id="urh9zx8" class="uif-label">Item</label></th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Start</th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">End</th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Quantity</th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">On/Off Campus?</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Unrecovered F&amp;A</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Total Base Cost</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Apply Inflation?</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Cost Sharing</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="">Submit Cost Sharing?</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                                        <tr>
                                            <td><span data-edit-type="select" data-edit-type-options="Equipment,Equipment Rental">Microscope</span></td>
                                            <td>01/01/14</td>
                                            <td>12/31/14</td>
                                            <td>1</td>
                                            <td><input type="checkbox"></td>
                                            <td class="text-right">$0.00</td>
                                            <td class="text-right">$0.00</td>
                                            <td><input type="checkbox"></td>
                                            <td class="text-right">$0.00</td>
                                            <td><input type="checkbox"></td>
                                            <td>
<!--                                                <div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div></td>-->
                                                <a class="icon icon-edit uif-edit-btn" href="#"><span class="sr-only">Edit</span></a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </section>

                        <section id="a2" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example2">
                            <header id="u1l3ufy3a" class="uif-sectionHeader" data-header_for="a2">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTena2" href="#" id="a2_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="a2_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="a2_toggle_col" class="icon-caret-right"></span>Travel</span></a></h3>
                            </header>
                            <div id="facSalTena2" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style=""></div>
                        </section>

                        <section id="a3" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example3">
                            <header id="u1l3ufy3b" class="uif-sectionHeader" data-header_for="a3">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTena3" href="#" id="a3_toggle" data-open="true" data-widgetid="u1vpenbx" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="a3_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="a3_toggle_col" class="icon-caret-right"></span>Participant Support</span></a></h3>
                            </header>
                            <div id="facSalTena3" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style=""></div>
                        </section>

                        <section id="a4" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example4">
                            <header id="u1l3ufy3c" class="uif-sectionHeader" data-header_for="a4">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTena4" href="#" id="a4_toggle" data-open="true" data-widgetid="u1vpenby" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="a4_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="a4_toggle_col" class="icon-caret-right"></span>Other</span></a></h3>
                            </header>
                            <div id="facSalTena4" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style=""></div>
                        </section>

                    </div>
                    <div class="tab-pane" id="p2">
                        <h3 class="">Period 2 <small>(1/12/15 - 1/11/16)</small></h3>
                        <h4>Assign  Non-personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Object Code Name</label>
                                        <select name="select" class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment - Not MTDC</option>
                                            <option>Equipment Rental - Not MTDC</option>
                                            <option>Travel Expenses</option>
                                        </select>
                                        <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>-->
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Total Base Cost</label>
                                        <input type="text" class="form-control" placeholder="0.00">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-right"><a href="#" type="submit" class="btn btn-primary btn-xs">Assign to Period 2</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="p3">
                        <h3 class="">Period 3 <small>(1/12/16 - 1/11/17)</small></h3>
                        <h4>Assign  Non-personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Object Code Name</label>
                                        <select name="select2" class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment - Not MTDC</option>
                                            <option>Equipment Rental - Not MTDC</option>
                                            <option>Travel Expenses</option>
                                        </select>
                                        <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>-->
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Total Base Cost</label>
                                        <input type="text" class="form-control" placeholder="0.00">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-right"><a href="#" type="submit" class="btn btn-primary btn-xs">Assign to Period 3</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="p4">
                        <h3 class="">Period 4 <small>(1/12/17 - 1/11/18)</small></h3>
                        <h4>Assign  Non-personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Object Code Name</label>
                                        <select name="select3" class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment - Not MTDC</option>
                                            <option>Equipment Rental - Not MTDC</option>
                                            <option>Travel Expenses</option>
                                        </select>
                                        <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>-->
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Total Base Cost</label>
                                        <input type="text" class="form-control" placeholder="0.00">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-right"><a href="#" type="submit" class="btn btn-primary btn-xs">Assign to Period 4</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="p5">
                        <h3 class="">Period 5 <small>(1/12/18 - 1/11/19)</small></h3>
                        <h4>Assign  Non-personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Object Code Name</label>
                                        <select name="select4" class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment - Not MTDC</option>
                                            <option>Equipment Rental - Not MTDC</option>
                                            <option>Travel Expenses</option>
                                        </select>
                                        <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>-->
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="label">Total Base Cost</label>
                                        <input type="text" class="form-control" placeholder="0.00">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-right"><a href="#" type="submit" class="btn btn-primary btn-xs">Assign to Period 5</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div> 
<!-- VIEW FOOTER --> <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;"> <div class="global-actions"> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save </a> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Reload </a> <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Complete Budget </a> </div>
            
           <div class="global-navigate"> <a href="budget-ng-personnelCosts-persPeriod.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem"><span class="icon-chevron-left"></span> Back</a>
            <a href="#" id="save-modal-button" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-toggle="modal" data-target="#save-modal">Continue <span class="icon-chevron-right"></span></a></div>
        </div>
        <!-- DIALOGS/Placeholders --></div>
    <span id="formInfo">
    <input type=hidden name="viewId" value=LabsProposal>
    <input type=hidden name="formKey" value=2e468a13-a495-44cc-acd7-aac6b2ed97a0>
    <input type=hidden name="requestedFormKey" value=2e468a13-a495-44cc-acd7-aac6b2ed97a0>
    <input type=hidden name="sessionId" value=CAFCAB4387CB97D8567359A8C37D7712>
    <input type=hidden name="flowKey" value="">
    <input type=hidden name="view.applyDirtyCheck" value=true>
    <input type=hidden name="dirtyForm" value=false>
    <input type=hidden name="renderedInLightBox" value=false>
    <input type=hidden name="view.singlePageView" value=true>
    <input type=hidden name="view.disableBrowserCache" value=true>
    </span>
    
</form>
<?php include ('includes/footer-scripts.php') ?>


<script>

		(function($){


$(".tabWarning").click(function(e){

  alert("[MODAL WINDOW] --- You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue? {{ CANCEL (button)  | CONTINUE (button) }} ---[MODAL WINDOW]");
return false;

});
}(jQuery))

</script>



<div class="modal fade" id="save-modal" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Generate All Periods?</h4>
            </div>
            <div class="modal-body">
                <p>Would you like to use the generate all periods functionality to auto-calculate later periods based on period 1 data? Please note that this action can only be taken once.</p></div>
            <div class="modal-footer"><a href="budget-ng-summary.php?generate=no" class="btn btn-default">No</a> <a href="budget-ng-subawards.php?generate=yes" class="btn btn-primary">Yes Generate All Periods</a></div>
        </div>
    </div>
</div>











<!-- MODAL  budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL  budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>

<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>

<div class="modal fade in" id="applyRates" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display:;">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">Copy Non-Personnel to All Periods</h4>
        </div>
        <div class="modal-body">
            <p>Would you like to apply Period 1 non-personnel to all periods? Please note that all other non-personnel info will be overwritten.</p>
        </div>
        <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Yes, Apply to All Periods</a> </div>
    </div>
</div>

<!-- NO QUOTES
<div class="modal fade" id="summary" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Summary</h4>
            </div>
            <div class="modal-body">
                <p>Here's a summary of your current budget.</p>
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
                        <tr class="not-deletable">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Salary</a></td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>784670</td>
                        </tr>
                        <tr class="not-deletable">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Fringe</a></td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>186725</td>
                        </tr>
                        <tr class="not-deletable">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>141420</td>
                        </tr>
                        <tr class="not-deletable">
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
                        <tr class="not-deletable">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>219348</td>
                        </tr>
                        <tr class="not-deletable">
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
          </tr> --><!--
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
                <button type=button class="btn btn-default" data-dismiss=modal>Close</button>
            </div>
        </div>
    </div>
</div>
 --> 

<!--NO QUOTES --


<div class="modal fade" id="budgetSettings" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Budget Settings</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal uif-cssGridSection" role=form>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Project Start</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/21/2014</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Project End</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/20/2019</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Total Direct Cost Limit</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Budget Status</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>Complete</option>
                                <option>Incomplete</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">On/Off Campus</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>All On</option>
                                <option>All Off</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Residual Funds</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Total Cost Limit</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Unrecovered F&A Rate Type</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">F&A Rate Type:</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Modular Budget </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Submit Cost Sharing </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Submit this budget with your proposal </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type=button class="btn btn-default" data-dismiss=modal>Close</button>
                <button type=button class="btn btn-primary">Apply Changes</button>
            </div>
        </div>
    </div>
</div>

<!-- NO QUOTES ?
<div class="modal fade" id="switchdoc" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Open Proposal</h4>
            </div>
            <div class="modal-body">
                <p>You are about to open the proposal doument for this budget. Are you sure you want to do this?</p>
                <small>
                <label style=font-weight:normal>
                    <input type=checkbox>
                    Dont ask me this again </label>
                </small></div>
            <div class="modal-footer"><a href="" class="btn btn-default" data-dismiss=modal>Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Open Proposal Development Document</a></div>
        </div>
    </div>
</div>
 end Modal -->
</body>
</html>