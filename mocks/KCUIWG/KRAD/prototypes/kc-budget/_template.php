<?php
$page = 'pageName';
$section = 'sectionName';
?>

<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="robots" content="noindex,nofollow" />
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
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
                        <h1> <img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"> </h1>
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
                <header class="clearfix uif-header-contentWrapper pull-left">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span">  Section with a Table</span> </h2>
                    </div>
                </header>
                <div class="pull-right"> <a href="#"> <span class="icon-compass"></span> Guide Me</a></div>
                <table id="u569ish_line0" class="table table-condensed table-bordered" role="presentation">
                    <thead>
                        <tr class="active">
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th>Category</th>
                            <th><span class="sr-only">Actions</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="uif-new-row">
                            <td>Item</td>
                            <td>Item</td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                    <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                                </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><label for="line_1_total"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total" id="line_1_total">
                            </label></td>
                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td>Item</td>
                            <td>Item</td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><label for="line_1_total2"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total2">
                            </label></td>
                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td>Item</td>
                            <td>Item</td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><label for="line_1_total3"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total3">
                            </label></td>
                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td>Item</td>
                            <td>Item</td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><label for="line_1_total4"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total4">
                            </label></td>
                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                        <tr class="uif-new-row">
                            <td>Item</td>
                            <td>Item</td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><label for="line_1_total5"><span class="sr-only">Label</span>
                                <input type="text" size="3" class="pull-right" name="line_1_total2" id="line_1_total5">
                            </label></td>
                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr class="active">
                            <td></td>
                            <td></td>
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
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Actions <span class="caret"></span> </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Reset Default Periods</a></li>
                            <li><a href="#">Recalculate</a></li>
                            <li><a href="#">Generate all periods based on the first period</a></li>
                        </ul>
                    </div>
                </div>
                
                
             <header class="clearfix uif-header-contentWrapper">
<div id="u1qii8i5" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
<h2 class="uif-headerText">
<span class="uif-headerText-span">
Section with Form Controls
</span>
</h2>
</div>
<div id="uetqy9j" class="uif-verticalBoxGroup uif-header-lowerGroup" data-parent="LabsProposal-Page">
<div id="u1j37iun" class="text-muted uif-boxLayoutVerticalItem clearfix" data-parent="uetqy9j">
<p>* Indicates required fields</p>
</div>
</div>
</header>
                
                <div id="u14jg6xp" class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix" data-parent="LabsProposal-Page">
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Text Input: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <input id="u11k8c13_control" type="text" name="field1" value="" size="30" class="form-control input-sm uif-textControl required error" data-role="Control" data-control_for="u11k8c13" aria-required="true" aria-invalid="true">
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c1y_label" for="u11k8c1y_control" class="uif-label displayWith-u11k8c1y" data-label_for="u11k8c1y"> Text Field: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c1y" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Project Title">
                                <textarea id="u11k8c1y_control" name="field2" rows="3" cols="40" class="form-control input-sm uif-mediumTextAreaControl has-helper required" data-role="Control" data-control_for="u11k8c1y" aria-required="true"></textarea>
                                
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u1javsi1_label" for="u1javsi1_fieldset" class="uif-label displayWith-u1javsi1" data-label_for="u1javsi1"> Date Range: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u1javsi1" class="uif-fieldGroup" data-parent="u14jg6xp" data-label="Project Dates" data-group="u18wn9hi">
                                <fieldset aria-labelledby="u1javsi1_label" id="u1javsi1_fieldset">
                                    <legend style="display: none">Project Dates</legend>
                                    <div id="u18wn9hi" class="uif-cssGridGroup" data-parent="u1javsi1">
                                        <div class="row ">
                                            <div class="col-md-6">
                                                <div id="uec5xzy" class="uif-inputField" data-parent="u18wn9hi" data-role="InputField">
                                                    <div class="input-group">
                                                        <input id="uec5xzy_control" type="text" name="field4" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="uec5xzy" placeholder="mm/dd/yyyy ">
                                                        <span class="input-group-msg">
                                                        <p id="u21vmor" class="uif-message"> to </p>
                                                        </span> </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div id="uec5y0t" class="uif-inputField" data-parent="u18wn9hi" data-role="InputField">
                                                    <input id="uec5y0t_control" type="text" name="field5" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="uec5y0t" placeholder="mm/dd/yyyy ">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c3o_label" for="u11k8c3o_control" class="uif-label displayWith-u11k8c3o" data-label_for="u11k8c3o"> Checkbox: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c3o" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Indicator">
                                <input type="hidden" name="_bField1" value="on">
                                <input type="checkbox" id="u11k8c3o_control" name="bField1" class="uif-checkboxControl" data-role="Control" data-control_for="u11k8c3o">
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c4j_label" for="u11k8c4j_control" class="uif-label displayWith-u11k8c4j" data-label_for="u11k8c4j"> Field with Lookup: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="" size="30" class="form-control input-sm uif-textControl has-helper" data-role="Control" data-control_for="u11k8c4j">
                                    
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-search" tabindex="0" data-onclick="createLightBoxPost(&quot;u11k8c4j_quickfinder_act&quot;,{autoSize:true,openEffect:&quot;fade&quot;,closeEffect:&quot;fade&quot;,openSpeed:200,closeSpeed:200,helpers:{overlay:{css:{cursor:'arrow'},closeClick:false}},type:&quot;iframe&quot;},true);" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act" data-submit_data="{&quot;actionParameters[conversionFields]&quot;:&quot;number:field3&quot;,&quot;actionParameters[viewName]&quot;:&quot;LabsProposal-Lookup&quot;,&quot;methodToCall&quot;:&quot;performLookup&quot;,&quot;actionParameters[quickfinderId]&quot;:&quot;u11k8c4j_quickfinder&quot;,&quot;actionParameters[multipleValuesSelect]&quot;:&quot;false&quot;,&quot;actionParameters[hideCriteriaOnSearch]&quot;:&quot;true&quot;,&quot;actionParameters[dataObjectClassName]&quot;:&quot;org.kuali.rice.krad.demo.travel.dataobject.TravelAccount&quot;,&quot;actionParameters[baseLookupUrl]&quot;:&quot;../kr-krad/lookup&quot;}"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c5e_label" for="u11k8c5e_control" class="uif-label displayWith-u11k8c5e" data-label_for="u11k8c5e"> Dropdown Select: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c5e" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Keywords">
                                <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl" data-role="Control" data-control_for="u11k8c5e">
                                    <option value="B">Biology</option>
                                    <option value="C">Chemistry</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
            <button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}"> Save and Continue </button>
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
    <div class="jquerybubblepopup jquerybubblepopup-kr-error-cs" style="margin: 0px 0px 0px 395.5px; opacity: 0; top: 227px; left: 542px; position: absolute; display: none;" id="jquerybubblepopup-1393862100-0" data-for="u11k8c13_control">
        <table>
            <tbody>
                <tr>
                    <td class="jquerybubblepopup-top-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-left.png);"></td>
                    <td class="jquerybubblepopup-top-middle" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-middle.png);"></td>
                    <td class="jquerybubblepopup-top-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-right.png);"></td>
                </tr>
                <tr>
                    <td class="jquerybubblepopup-middle-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-left.png);"></td>
                    <td class="jquerybubblepopup-innerHtml"><div class="uif-clientMessageItems uif-clientErrorDiv">
                            <ul>
                                <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                            </ul>
                        </div></td>
                    <td class="jquerybubblepopup-middle-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-right.png);"></td>
                </tr>
                <tr>
                    <td class="jquerybubblepopup-bottom-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-left.png);"></td>
                    <td class="jquerybubblepopup-bottom-middle" style="background-image: url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-middle.png); text-align: left;"><img src="../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/tail-bottom.png" alt="" class="jquerybubblepopup-tail"></td>
                    <td class="jquerybubblepopup-bottom-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-right.png);"></td>
                </tr>
            </tbody>
        </table>
    </div>
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
                <div class="form-horizontal uif-cssGridSection" role="form">
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

</body>
</html>