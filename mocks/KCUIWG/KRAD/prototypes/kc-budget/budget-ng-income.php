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
<div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
 <!-- VIEW HEADER -->
 <?php include ('includes/uif-viewHeader.php') ?>
<!-- VIEW CONTENT -->
<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
<?php include ('includes/uif-navigation-budget.php') ?>
<div id="Uif-BreadcrumbUpdate" style="display:;"></div>
<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages=false data-role=Page data-parent=LabsProposal style="margin-left: 235px;">
    <header class="clearfix uif-header-contentWrapper">
        <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for=LabsProposal-Page>
            <h2 class="uif-headerText"><span class="uif-headerText-span">Project Income</span></h2>
        </div>
    </header>
    <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Verify and adjust additional program income costs as necessary for this budget.</p>
   
    
            <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                <thead>
                <tr role=row>
                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Period</label></th>
                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Income</label></th>
                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Description</label></th>
                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                </thead>
                <tbody role=alert aria-live=polite aria-relevant=all>
                <tr>
                    <td><span data-edit-type="select" data-edit-type-options="1: 01/01/2014 - 12/31/2014,2: 01/01/2015 - 12/31/2016,3: 01/01/2016 - 12/31/2017">1: 01/01/2014 - 12/31/2014</span></td>
                    <td>$500.00</td>
                    <td>Lorem ipsum dolar amet isset</td>
                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                </tr>
                <tr class="uif-new-row">
                    <td>
                        <label for="line_2_period" class="form-label"><span class="sr-only">Period</span></label>
                        <select name="line_2_period" id="line_2_period" class="form-control input-sm">
                            <option>- Please select -</option>
                            <option value="1">1: 01/01/2014 - 12/31/2014</option>
                            <option value="2">2: 01/01/2015 - 12/31/2015</option>
                            <option value="3">3: 01/01/2016 - 12/31/2016</option>
                        </select>
                    </td>
                    <td><label for="line_2_income"><span class="sr-only">Income</span>
                            <input type="text" name="line_2_income" id="line_2_income">
                        </label></td>
                    <td><label for="line_2_desc"><span class="sr-only">Description</span>
                            <input type="text" name="line_2_desc" id="line_2_desc">
                        </label></td>
                    <td><a tabindex="0" class="icon icon-save"><span class="sr-only">Save</span></a></td>
                </tr>
                </tbody>
            </table>
            <!-- summary -->
            <div class="well well-sm"  style="margin-top:-1.4em;">
                <h4> Project Income Summary</h4>
                <table  class="data-row-table ">

                    <tr>
                        <th scope="row">Period 1</th>
                        <td>$100</td>
                    </tr>
                    <tr>
                        <th scope="row">Total:</th>
                        <td>$100</td>
                    </tr>

                </table>
                
                  </div>
                <div class="pull-right">

<button class="btn btn-default btn-xs">Recalculate</button>
</div>
                
                
              <!--//summary  -->
  
</main>
</div>


    <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
        <div class="uif-footer-centered-control-group clearfix">
            <div class="global-navigate btn-group">
                <button type="button" href="budget-ng-modular.php" id="save-continue" class="btn btn-primary">Continue</button>
            </div>

            <div class="global-actions btn-group">
                <button type="button" href="budget-ng-unrecovered-fa.php" id="" class="btn btn-default">Back</button>
                <button type="button" id="" class="btn btn-default">Save</button>
                <button type="button" id="" class="btn btn-default">Reload</button>
                <button type="button" id="" class="btn btn-default">Complete Budget</button>
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


</body>
</html>