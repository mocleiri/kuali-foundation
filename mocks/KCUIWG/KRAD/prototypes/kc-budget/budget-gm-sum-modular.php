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

<!-- carousel css -->
<style type="text/css">
.infiniteCarousel {
    max-width: 900px;
    position: relative;
    height: 45px;
}
.infiniteCarousel .carouselContainer {
    min-width: 315px;
    max-width: 850px; /* .infiniteCarousel width - (.carouselContainer margin-left + .carouselContainer margin-right) */
    overflow: hidden;
    height: 40px;
    position: relative;
    padding: 10px 5px 0;
    top: 0;
    left: 24px;
}
.infiniteCarousel .carouselContainer ul {
    width: 9999px;
    margin: 0;
    padding: 0;
    position: absolute;
    top: 0;
}
.infiniteCarousel ul li {
    min-width: 50px;
    float: left;
    padding: .5em;
    ;
    margin: 0 .45em 0 .45em;
    list-style: none;
}
.infiniteCarousel .arrowCarousel {
    position: absolute;
    top: -5px;
    font-size: 2em;
line-height:0  padding: 0 .25em 0;
    cursor: pointer;
    z-index: 10000;
}
.infiniteCarousel .forwardCarousel {
    right: 0;
}
.infiniteCarousel .backCarousel {
    left: 0;
}
.infiniteCarousel .forwardCarousel:hover {
}
.infiniteCarousel .backCarousel:hover {
}

/* is there a generic class in bootstrap to add padding to an element? */
.pad1em{padding:1em;}
/*whitesmoke looks to be a class in the less.js file */
.whitesmoke{background:#f5f5f5;}
</style>
<!-- // end carousel css -->

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
        <header class="container uif-viewHeader-contentWrapper">
            <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal">
                <h1 class="uif-headerText">
                    <p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> Proposal Budget Development </p>
                    <span class="uif-headerText-span"> Budget: Version 2</span> </h1>
                <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent="LabsProposal">
                    <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Initiator">
                        <label id="ujre4xu" for="u7lh763_span" class="uif-label" data-label_for="u1f206ki"> Final Version: </label>
                        <p id="u7lh763" class="uif-message"> No </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Created: </label>
                        <p id="u4ch8dm" class="uif-message"> 1/25/14 </p>
                    </div>
                    <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                        <label id="uj8x9wj" for="uauh5yk_span" class="uif-label" data-label_for="u1f206jn"> Parent Proposal: </label>
                        <p id="uauh5yk" class="uif-message"> <a id="uotglr8" class="uif-actionLink"  data-toggle="modal" data-target="#switchdoc">#23533</a></p>
                    </div>
                    <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
                        <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                            <h4 class="uif-headerText"> Document Info </h4>
                            <table id="u98wduy" class="table table-condensed uif-table-fixed" role="presentation">
                                <tbody>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                    </div>
                </div>
            </div>
            <div id="LabsProposal-DocActionBar" class="uif-actionBar uif-header-lowerGroup">
                <ul>
                    <li> <a id="uotglns" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;print&quot;}"><span class="icon-print"></span>Print</a> </li>
                    <li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy</a> </li>
                    <li> <a id="uotglr8" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;access&quot;}"><span class="icon-lock"></span>Access</a> </li>
                    <li> <a id="uotglr8" class="uif-actionLink"  data-toggle="modal" data-target="#budgetSettings"><span class="icon-cog"></span>Budget Settings</a> </li>
                    <li> <a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span class="icon-eye"></span>Summary</a> </li>
                </ul>
            </div>
        </header>
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div class="uif-pageHeader clearfix">
                        <h2 class="uif-headerText">
                            <span class="uif-headerText-span">Guided Mode</span> <span style="font-size:small">(<a href="#">Exit guided mode</a>)</span>
                        </h2>
                        <!-- carousel -->
                        <div id="divCarousel" class="infiniteCarousel">
                            <div class="carouselContainer">
                                <ul>
                                    <li>1. <a href="budget-gm-sum-period-query.php">Budget Periods &amp; Totals</a></li>
                                    <li>2. <a href="budget-gm-sum-modular.php">Modular</a></li>
                                    <li>3. <a href="budget-gm-sum-summary.php">Summary &amp; Review</a></li>
                                </ul>
                            </div>
                        </div>                    
                        <!-- // carousel -->
                    </div>
                </header>
                <div class="uif-boxLayoutVerticalItem clearfix">
                    <p><b>The following are the budget modules that correspond to your project dates.</b></p>
                    <p><b>Review and adjust modular totals as needed. Enter the consortium details as requested.</b></p>
                </div>
                <table id="u569ish_line0" class="table table-condensed table-bordered" role="presentation">
                    <thead>
                        <tr class="active">
                            <th>Period</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Months</th>
                            <th>Direct Less Consortium</th>
                            <th>Consortium F&amp;A</th>
                            <th>Direct Costs</th>
                            <th>Total F&amp;A</th>
                            <th>Total Direct Costs</th>
                            <th>Period Total</th>
                            <th><span class="sr-only">Actions</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="uif-new-row">
                            <td>1</td>
                            <td>01/01/2014</td>
                            <td>12/31/2014</td>
                            <td><div class="pull-right">12.0</div></td>
                            <td><label for="line_1_total"><span class="sr-only">Direct Less Consortium</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_direct_less_consortium" id="line_1_direct_less_consortium">
                                </label></td>
                            <td><label for="line_1_direct"><span class="sr-only">Consortium F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_consortium_fa" id="line_1_consortium_fa">
                                </label></td>
                            <td><label for="line_1_fa"><span class="sr-only">Direct Costs</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_direct_costs" id="line_1_direct_costs">
                                </label></td>
                            <td><label for="line_1_unrecovered"><span class="sr-only">Total F&amp;A</span>
                                    <span class="pull-right">$15,000</span>
                                </label></td>
                            <td><label for="line_1_cost-sharing"><span class="sr-only">Total Direct Costs</span>
                                    <span class="pull-right">$35,000</span>
                                </label></td>
                            <td><label for="line_1_cost-limit"><span class="sr-only">Period Total</span>
                                    <span class="pull-right">$50,000</span>
                                </label></td>
                            <td><a class="icon icon-edit"><span class="sr-only">Edit</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td>2</td>
                            <td>01/01/2015</td>
                            <td>12/31/2015</td>
                            <td><div class="pull-right">12.0</div></td>
                            <td><label for="line_1_total"><span class="sr-only">Direct Less Consortium</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_direct_less_consortium" id="line_2_direct_less_consortium">
                                </label></td>
                            <td><label for="line_1_direct"><span class="sr-only">Consortium F&amp;A</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_consortium_fa" id="line_2_consortium_fa">
                                </label></td>
                            <td><label for="line_1_fa"><span class="sr-only">Direct Costs</span>
                                    <input type="text" size="3" class="pull-right" name="line_2_direct_costs" id="line_2_direct_costs">
                                </label></td>
                            <td><label for="line_1_unrecovered"><span class="sr-only">Total F&amp;A</span>
                                    <span class="pull-right">$15,000</span>
                                </label></td>
                            <td><label for="line_1_cost-sharing"><span class="sr-only">Total Direct Costs</span>
                                    <span class="pull-right">$35,000</span>
                                </label></td>
                            <td><label for="line_1_cost-limit"><span class="sr-only">Period Total</span>
                                    <span class="pull-right">$50,000</span>
                                </label></td>
                            <td><a class="icon icon-edit"><span class="sr-only">Edit</span></a></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr class="active">                         
                            <td colspan="11">
                                <span class="pull-right">
                                    <a class="btn btn-default btn-xs" href="#">Reset default periods</a>
                                    <a class="btn btn-default btn-xs" href="#">Recalculate</a>
                                </span>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </main>
        </div>
        <div class="uif-footer clearfix">
            <a class="btn btn-default" href="budget-gm-sum-period-2.php"><span class="icon-chevron-left"></span> Back</a>
            <a class="btn btn-primary" href="budget-gm-sum-summary.php">Continue</a>
        </div> 
<!-- VIEW FOOTER --> <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;"> <div class="global-actions"> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save </a> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Reload </a> <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Complete Budget </a> </div>
            
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

<!-- Modal -->
<div class="modal fade" id="summary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
                            <td>Personnel Subtotal </td>
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
                            <td>Nonpersonnel Subtotal </td>
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

<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="budgetSettings" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Budget Settings</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal uif-cssGridGroup" role="form">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Project Start</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/21/2014</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Project End</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/20/2019</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Total Direct Cost Limit</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Budget Status</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>Complete</option>
                                <option>Incomplete</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">On/Off Campus</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>All On</option>
                                <option>All Off</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Residual Funds</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Total Cost Limit</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Unrecovered F&A Rate Type</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">F&A Rate Type:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">
                                    Modular Budget </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">
                                    Submit Cost Sharing </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">
                                    Submit this budget with your proposal </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Apply Changes</button>
            </div>
        </div>
    </div>
</div>

<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Open Proposal</h4>
            </div>
            <div class="modal-body">
                <p>You are about to open the proposal doument for this budget. Are you sure you want to do this?</p>
                <small>
                <label style="font-weight:normal">
                    <input type="checkbox">
                    Don't ask me this again </label>
                </small> </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Open Proposal Development Document</a> </div>
        </div>
    </div>
</div>

<!-- end Modal -->

<!-- Carousel Script -->
<script type="text/javascript">

(function($){
    $.fn.infiniteCarousel = function () {
        function repeat(str, num) {
            return new Array( num + 1 ).join( str );
        }
        
        return this.each(function () {
            var $wrapper = $('> div', this).css('overflow', 'hidden'),
                $slider = $wrapper.find('> ul'),
                $items = $slider.find('> li'),
                $single = $items.filter(':first'),
                singleWidth = $single.outerWidth(), 
                currentPage = 1;

            function recalculateAfterResize(){
                
                // Reset to the original carousel condition
                $('.empty', $wrapper).remove();
                $('.cloned', $wrapper).remove();
                $items = $slider.find('> li');
                $wrapper.visible = Math.floor($wrapper.innerWidth() / singleWidth),
                $wrapper.pages = Math.ceil($items.length / $wrapper.visible);
                // 1. Pad so that 'visible' number will always be seen, otherwise create empty items
                if (($items.length % $wrapper.visible) != 0) {
                    $slider.append(repeat('<li class="empty" />', $wrapper.visible - ($items.length % $wrapper.visible)));
                    $items = $slider.find('> li');
                }
                // 2. Top and tail the list with 'visible' number of items, top has the last section, and tail has the first
                $items.filter(':first').before($items.slice(- $wrapper.visible).clone().addClass('cloned'));
                $items.filter(':last').after($items.slice(0, $wrapper.visible).clone().addClass('cloned'));
                $items = $slider.find('> li'); // reselect
                $wrapper.scrollLeft(singleWidth * $wrapper.visible);
                $($slider).css('width', ($items.length+1) * singleWidth);
                page = 1;
            } 
    
            
            $(window).resize(recalculateAfterResize);
            if($(this).is(':visible')) recalculateAfterResize();
            
            // 4. paging function
            function gotoPage(page) {
                
                var dir = page < currentPage ? -1 : 1,
                n = Math.abs(currentPage - page),
                left = singleWidth * dir * $wrapper.visible * n;
                $wrapper.filter(':not(:animated)').animate({
                    scrollLeft : '+=' + left
                }, 500, function () {
                    if (page == 0) {
                        $wrapper.scrollLeft(singleWidth * $wrapper.visible * $wrapper.pages);
                        page = $wrapper.pages;
                    } else if (page > $wrapper.pages) {
                        $wrapper.scrollLeft(singleWidth * $wrapper.visible);
                        // reset back to start position
                        page = 1;
                    } 
                    
                    currentPage = page;
                });                
    
                return false;
            }

            function gotoNext(){
                return gotoPage(currentPage+1);
            };
            
            function gotoPrev(){
                return gotoPage(currentPage-1);
            };
            
            $wrapper.before('<a class="arrowCarousel backCarousel ">&lsaquo;</a><a class="arrowCarousel forwardCarousel ">&rsaquo;</a>');
    

            $('a.backCarousel', this).click(function () {
                return gotoPrev();                
            });
    
            $('a.forwardCarousel', this).click(function () {
                return gotoNext();
            });
    
            // create a public interface to move to a specific page
            $(this).bind('goto', function (event, page) {
                gotoPage(page);
            });
        });  
    };





    $('#divCarousel').infiniteCarousel();
    
    //set the carousel the last links...
                //$(".forwardCarousel").trigger('click'); 


})(jQuery);

            
                
            
        
</script>
        
<!-- // Carousel Script -->

</body>
</html>