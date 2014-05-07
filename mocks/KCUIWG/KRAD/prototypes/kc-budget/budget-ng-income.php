<?php
$page = 'project-income';
$section = '';
?>
<!DOCTYPE HTML>
<html lang=en>
    <head>
    <meta charset=UTF-8>
    <title>Kuali :: Fluid Application Header</title>
    <!-- GLOBAL STYLES -->
    <?php include ('includes/styles.php') ?>
    <style type="text/css">

        .data-row-table th{ padding:5px; text-align:right; }
    </style>
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
                            <h1><img id="u2elq10" src="../../themes/kboot/images/logo-kc.png" alt="" class="uif-image"> </h1>
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
        <div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER --> 
        <!-- VIEW HEADER -->
        <?php include('includes/uif-viewHeader-budget.php') ?>
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
                <?php include ('includes/uif-navigation-budget.php') ?>
                <div id="Uif-BreadcrumbUpdate" style="display:;"></div>
                <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages=false data-role=Page data-parent=LabsProposal style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                        <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <div class="row">
                                <div class="col-md-6">
                                <h2 class="uif-headerText"> <span class="uif-headerText-span"> Project Income </span> </h2>
                            </div>
                                <div class="col-md-6 uif-pagetools text-right"> <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#income"><span aria-hidden="true" class="icon-plus"></span> Add Income</a> <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#incomesummary">View Summary</a> </div>
                            </div>
                    </div>
                    </header>
                <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Verify and adjust additional program income costs as necessary for this budget.</p>
                <style>
				#projincome tr td:first-of-type{width:1%; white-space:nowrap }
				</style>
                <table class="table table-condensed table-bordered uif-has-actions uif-lightTable dataTable" id="projincome" aria-describedby="Demo-LightTableGroup1_lightTable_info">
                        <thead>
                        <tr role="row">
                                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Period</label></th>
                                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 3: : activate to sort column ">Description</th>
                                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ">Income </th>
                                <th>&nbsp;</th>
                            </tr>
                    </thead>
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <tr class="not-deletable">
                                <td>1 <small class="text-muted">(05/14/2014 - 05/13/2015)</small></td>
                                <td><p>Considered an invitation do introduced sufficient understood instrument it. Of decisively friendship in as collecting at. No affixed be husband ye females brother garrets proceed. Least child who seven happy yet balls young.</p></td>
                                <td>3,273.27</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>2 <small class="text-muted">(05/14/2015 - 05/13/2016)</small></td>
                                <td>Style never met and those among great. At no or september sportsmen he perfectly happiness attending. </td>
                                <td>634.74</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>3 <small class="text-muted">(05/14/2016 - 05/13/2017)</small></td>
                                <td>Delightful unreserved impossible few estimating men favourable see entreaties. She propriety immediate was improving.</td>
                                <td>2,882.45</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>3 <small class="text-muted">(05/14/2016 - 05/13/2017)</small></td>
                                <td>New had happen unable uneasy. Drawings can followed improved out sociable not. Earnestly so do instantly pretended.</td>
                                <td>100.00</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>3 <small class="text-muted">(05/14/2016 - 05/13/2017)</small></td>
                                <td>She literature discovered increasing how diminution understood. Though and highly the enough county for man. Of it up he still court alone widow seems. Suspected he remainder rapturous my sweetness. All vanity regard sudden nor simple can. World mrs and vexed china since after often. </td>
                                <td>100.00</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>4 <small class="text-muted">(05/14/2017 - 05/13/2018)</small></td>
                                <td>and so forth.  In addition, Ogilvy's wire to the Astronomical Exchange had roused every observatory in the three kingdoms. There were half a dozen flies or more from the Woking station standing in the road by the sand pits, a basket-chaise from Chobham, and a rather lordly carriage.  Besides that, there</td>
                                <td>727.77</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                        <tr class="not-deletable">
                                <td>5 <small class="text-muted">(05/14/2018 - 05/13/2019)</small></td>
                                <td><p>&quot;Why, he is a man,' said the other, and I quite agreed with him.  The farmer carried me under his arm to the cornfield, and set me up on a tall stick, where you found me. </p></td>
                                <td>437.99</td>
                                <td><a href="" class="btn btn-default btn-xs">Edit</a> <a href="#" class="btn btn-default btn-xs">Delete</a></td>
                            </tr>
                    </tbody>
                    </table>
             
               
             
            </main>
            </div>
        <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
                <div class="uif-footer-centered-control-group clearfix">
                <div class="global-navigate btn-group">
                        <button type="button" href="budget-ng-unrecovered-fa.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                        <button type="button" href="budget-ng-modular.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                    </div>
                <div class="global-actions btn-group">
                        <button type="button" id="" class="btn btn-default">Save</button>
                        <button type="button" id="" class="btn btn-default">Reload</button>
                        <button type="button"  id="complete"  data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button>
                    </div>
            </div>
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

<!-- MODAL -- budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL -- budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>

<!-- MODAL -- budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
<!-- MODAL budget complete buttons -->
<?php include ('includes/modal-budget-complete.php') ?>

<!-- MODAL Add project income -->
<?php include ('includes/modal-budget-projIncome.php') ?>
<!-- MODAL Add project income -->
<?php include ('includes/modal-budget-incomesumary.php') ?>




</body>
</html>