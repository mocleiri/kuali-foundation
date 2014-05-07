<?php
$section = 'personnel';
$page = 'personnel-assign';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="robots" content="noindex,nofollow" />
    <title>Kuali :: Fluid Application Header</title>
    <!-- GLOBAL STYLES -->
    <?php include ('includes/styles.php') ?>
    <style>
        /* setting the width to align with inputs */
        .col-sm-2 select{ width:200px;}
    </style>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-reorder"></span></button>
                    <a class="navbar-brand" href="index.php">
                        <div class="logoBrand">
                            <h1><img id="u2elq10" src="../../themes/kboot/images/logo-kc.png" alt="" class="uif-image"></h1>
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
                                            <h3 class="uif-headerText"> Proposal Development</h3>
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
                                <li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Backdoor Login <span class="caret"></span></a>
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
    <!-- Backdoor info (here to inherit stickyness with the header, if set) --></header>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
<!-- VIEW -->
<div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
<!-- VIEW HEADER -->
<?php include('includes/uif-viewHeader-budget.php') ?>
<!-- // VIEW HEADER -->
<!-- VIEW CONTENT -->
<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
<?php include ('includes/uif-navigation-budget.php') ?>
<div id="Uif-BreadcrumbUpdate" style="display:;"></div>
<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
<header class="clearfix uif-header-contentWrapper">
    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
        <h2 class="uif-headerText"><span class="duif-headerText-span"> Assign Personnel to Periods</span></h2>
    </div>
</header>
<p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Assign personnel to one or all periods and configure efforts and charges.</p>
<br>
<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
    <li class="active"><a href="#p1" data-toggle="tab">Period 1 </a></li>
    <li class=""><a href="#p2" data-toggle="tab" class="tabWarning">Period 2</a></li>
    <li><a href="#p3" data-toggle="tab" class="tabWarning">Period 3</a></li>
    <li><a href="#p4" data-toggle="tab" class="tabWarning">Period 4</a></li>
    <li><a href="#p5" data-toggle="tab" class="tabWarning">Period 5</a></li>
</ul>
<div id="my-tab-content" class="tab-content">
<div class="tab-pane active" id="p1"><br>
<header class="clearfix uif-header-contentWrapper">
    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
        <div class="row">
            <div class="col-md-6">
                <h3 class="">Period 1 <small style="font-family: 'Arial', sans-serif;">(1/12/14 - 1/11/15)</small></h3>
            </div>
            <div class="col-md-6 uif-pagetools btn-group">
                <button type="button" class="btn btn-default btn-xs pull-right">Download Salaries</button>
                <button type="button" class="btn btn-default btn-xs pull-right">Calculate Current Period</button>
                <button type="button" class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target="#persperiod">Assign Personnel...</button>
            </div>
        </div>
    </div>
</header>

<section class="clearfix">
    <div>
        <table class="table table-condensed table-bordered uif-has-actions uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
            <thead>
                <tr role="row">
                    <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Person</label></th>
                    <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="Field 2: : activate to sort column ">Start</th>
                    <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="Field 3: : activate to sort column ">End</th>
                    <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="Field 4: : activate to sort column ascending">Effort</th>
                    <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="Field 4: : activate to sort column ">Charged</th>
                    <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="Field 4: : activate to sort column ">Period Type</th>
                    <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="Field 4: : activate to sort column ">Requested Salary</th>
                    <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="Field 4: : activate to sort column ">Calculated Fringe</th>
                    <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="Field 4: : activate to sort column ">Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr class="active">
                    <td colspan="9">
                        <p class="uif-headerText pull-left" style="margin: 2px 0 2px 0;"><strong>Faculty Salaries - Tenured</strong></p>
                        <a href="#" class="uif-actionLink pull-right" data-toggle="modal" data-target="#details">Details &amp; Rates</a>
                    </td>
                </tr>
                <tr>
                    <td>Ward Cleaver <small class="text-muted">(PI)</small></td>
                    <td><span data-edit-type="input">1/12/14</span><small class="text-muted"></small></td>
                    <td><span data-edit-type="input">1/12/15</span></td>
                    <td class="text-right"><span class="date-edit-type">100%</span></td>
                    <td>
                        <div class="dropdown dropdown-large">
                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#ffffff;">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <div class="col-sm-9"></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9"></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            50%
                        </div>
                    </td>
                    <td>Calendar</td>
                    <td class="text-right">$142,525.00</td>
                    <td class="text-right">$0.00</td>
                    <td>
                        <div class="dropdown dropdown-large">
                            <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal-edit-assigned-personnel-01">Details</a>
                            <a href="#" class="btn btn-default btn-xs uif-delete"><span class="sr-only">Delete</span><span class="icon icon-trash"></span></a>
                        </div>
                    </td>
                </tr>
                <tr class="active">
                    <td colspan="9">
                        <p class="uif-headerText pull-left" style="margin: 2px 0 2px 0;"><strong>Post Doctoral Staff</strong></p>
                        <a href="#" class="uif-actionLink pull-right" data-toggle="modal" data-target="#details">Details &amp; Rates</a>
                    </td>
                </tr>
                <tr>
                    <td>John Coltrane <small class="text-muted">(Key Person)</small></td>
                    <td>1/12/14<small class="text-muted"></small></td>
                    <td>1/12/15</td>
                    <td class="text-right">100%</td>
                    <td>
                        <div class="dropdown dropdown-large">
                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#ffffff;">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <div class="col-sm-9"></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9"></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-9">
                                                    <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                                        <div class="input-group"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            50%</div></td>
                    <td>Calendar</td>
                    <td class="text-right">$142,525.00</td>
                    <td class="text-right">$0.00</td>
                    <td>
                        <div class="dropdown dropdown-large">
                            <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal-edit-assigned-personnel-01">Details</a>
                            <a href="#" class="btn btn-default btn-xs uif-delete"><span class="sr-only">Delete</span><span class="icon icon-trash"></span></a>
                        </div>
                    </td>
                </tr>
                <tr class="active">
                    <td colspan="9">
                        <p class="uif-headerText pull-left" style="margin: 2px 0 2px 0;"><strong>Summer Faculty</strong></p>
                        <a href="#" class="uif-actionLink pull-right" data-toggle="modal" data-target="#details">Details &amp; Rates</a>
                    </td>
                </tr>
                <tr>
                    <td>Warf <small class="text-muted">(Key Person)</small></td>
                    <td>1/12/14<small class="text-muted"></small></td>
                    <td>1/12/15</td>
                    <td class="text-right">100%</td>
                    <td>50%</td>
                    <td>Calendar</td>
                    <td class="text-right">$142,525.00</td>
                    <td class="text-right">$0.00</td>
                    <td>
                        <div class="dropdown dropdown-large">
                            <a href="#" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal-edit-assigned-personnel-01">Details</a>
                            <a href="#" class="btn btn-default btn-xs uif-delete"><span class="sr-only">Delete</span><span class="icon icon-trash"></span></a>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>
</div>
<div class="tab-pane" id="p2"></div>
<div class="tab-pane" id="p3"></div>
<div class="tab-pane" id="p4"></div>
<div class="tab-pane" id="p5"></div>
</div>
</main>
</div>
<div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
    <div class="uif-footer-centered-control-group clearfix">
        <div class="global-navigate btn-group">
            <button type="button" href="budget-ng-personnelCosts-projPersonnel1.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
            <button type="button" href="budget-ng-non-personnel.php" id="save-continue" class="btn btn-primary save-continue-btn">Continue <span class="icon-chevron-right"></span></button>
        </div>
        <div class="global-actions btn-group">
            <button type="button" id="" class="btn btn-default">Save</button>
            <button type="button" id="" class="btn btn-default">Reload</button>
            <button type="button"  id="complete"  data-toggle="modal" data-target="#modal-budget-complete" class="btn btn-default">Complete Budget</button>
        </div>
    </div>
</div>

></div>
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
<script type="text/javascript">
    jQuery(document).ready(function ($) {


        $(".tabWarning").click(function(e){

            alert("[MODAL WINDOW] --- You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue? {{ CANCEL (button)  | CONTINUE (button) }} ---[MODAL WINDOW]");
            return false;

        });




        $('#tabs').tab();
    });</script>
<?php
$currentPage =  $_SERVER['QUERY_STRING'] ;
if ($currentPage == "modular-budget=no&amp;detail=yes") {
    ?>
    <!--  casual user functions -->
    <script>
        (function($){




            $('body').on('click','.save-continue-btn', function(e) {

                document.location.href='budget-ng-non-personnel.php?modular-budget=no&amp;detail=yes';

            });

        }(jQuery))
    </script>
<?php } ?>

<!-- MODAL budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
<!-- MODAL add person to period -->
<?php include ('includes/modal-budget-addPersPeriod.php') ?>
<!-- MODAL edit personnel details -->
<?php include ('includes/modal-budget-edit-assigned-personnel-01.php') ?>

<!-- Modal -->
<div class="modal fade" id="details" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Details &amp; Rates</h4>
            </div>
            <div class="modal-body">
                <ul id="rateDetails" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#general" data-toggle="tab">General</a></li>
                    <li><a href="#rates" data-toggle="tab">Rates</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="general">
                        <h4>Details</h4>
                        <div class="form-horizontal uif-cssGridGroup" role="form">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Budget Category</label>
                                <div class="col-sm-9">
                                    <select class="form-control">
                                        <option value="1">Senior Personnel</option>
                                        <option value="2">Trainee/Participant Costs - Other</option>
                                        <option value="3">Materials </option>
                                        <option value="4">Human Subjects</option>
                                        <option value="5">Vertebrate Animals</option>
                                        <option value="6">Publication Costs/Documentation/Dissemenation</option>
                                        <option value="7">Travel - Domestic</option>
                                        <option value="8">Alteration and Renovations</option>
                                        <option value="9">Inpatient Care Costs</option>
                                        <option value="10">Duplicating</option>
                                        <option value="11">Postage</option>
                                        <option value="12">Telephone, Fax</option>
                                        <option value="13">Equipment Rental</option>
                                        <option value="14">Service Agreement(s)</option>
                                        <option value="15">Communications/Marketing</option>
                                        <option value="16">Software</option>
                                        <option value="17">Computer Time</option>
                                        <option value="18">Meeting Costs</option>
                                        <option value="19">Other Operating Expenses</option>
                                        <option value="20">Equipment</option>
                                        <option value="21">Professional Services/Consultant</option>
                                        <option value="22">Subcontracts</option>
                                        <option value="23">Travel - Foreign</option>
                                        <option value="25">Postdoctoral</option>
                                        <option value="26" selected="selected">Other Professionals</option>
                                        <option value="27">Graduate Students</option>
                                        <option value="28">Undergraduate Students</option>
                                        <option value="29">Project Support Staff</option>
                                        <option value="30">Other Personnel</option>
                                        <option value="31">Trainee/Participant Costs - Travel</option>
                                        <option value="32">Trainee/Participant Costs - Stipends</option>
                                        <option value="33">Outpatient costs</option>
                                        <option value="34">Calculated Costs</option>
                                        <option value="35">Trainee/Participant Costs - Tuition</option>
                                        <option value="36">Trainee/Participant Costs - Subsistence</option>
                                        <option value="37">Proposal Hierarchy Sub-Projects</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Unrecovered F&amp;A</label>
                                <div class="col-sm-9">
                                    <p class="form-control-static">0.00</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Cost Sharing</label>
                                <div class="col-sm-9">
                                    <p class="form-control-static">97,777.68</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Notes</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Group Description</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label"># of Persons&nbsp;</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">
                                            Apply Inflation </label>
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
                                            On Campus </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="rates">
                        <h4>Rates</h4>
                        <table class="table table-condensed">
                            <tbody>
                            <tr>
                                <th > Class </th>
                                <th > Type </th>
                                <th > Rate Cost </th>
                                <th > Rate Cost Sharing </th>
                                <th > Apply Rate? </th>
                            </tr>
                            <tr>
                                <td> Lab Allocation - Salaries </td>
                                <td> Lab Allocation - Salaries </td>
                                <td> 9,955.55 </td>
                                <td> 7,822.21 </td>
                                <td><input type="checkbox" name="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[0].applyRateFlag" tabindex="108" value="on" checked="checked" onclick="" onchange="" onblur="" id="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[0].applyRateFlag" style="" class="" title="Apply Rate?" />
                                    <input type="hidden" name="checkboxToReset" value="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[0].applyRateFlag" /></td>
                            </tr>
                            <tr>
                                <td> Lab Allocation - M&amp;S </td>
                                <td> Lab Allocation - M&amp;S </td>
                                <td> 1,244.44 </td>
                                <td> 977.78 </td>
                                <td><input type="checkbox" name="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[1].applyRateFlag" tabindex="113" value="on" checked="checked" onclick="" onchange="" onblur="" id="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[1].applyRateFlag" style="" class="" title="Apply Rate?" />
                                    <input type="hidden" name="checkboxToReset" value="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[1].applyRateFlag" /></td>
                            </tr>
                            <tr>
                                <td> Lab Allocation - Utilities </td>
                                <td> Lab Allocation - Utilities </td>
                                <td> 9,955.55 </td>
                                <td> 7,822.21 </td>
                                <td><input type="checkbox" name="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[2].applyRateFlag" tabindex="118" value="on" checked="checked" onclick="" onchange="" onblur="" id="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[2].applyRateFlag" style="" class="" title="Apply Rate?" />
                                    <input type="hidden" name="checkboxToReset" value="document.budget.budgetPeriods[1].budgetLineItems[0].budgetLineItemCalculatedAmounts[2].applyRateFlag" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <script type="text/javascript">
                    jQuery(document).ready(function ($) {
                        $('#rateDetails').tab();
                    });
                </script></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Apply to Later Periods</button>
            </div>
        </div>
    </div>
</div>
<!-- end Modal -->

<!-- MODAL -- Apply Rates  -->

<!--
    
    <div class="modal fade in" id="applyRates" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display:;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Copy Personnel to All Periods</h4>
            </div>
            <div class="modal-body">
                <p>Would you like to apply Period 1 personnel to all periods? Please note that all other personnel info will be overwritten.</p>
               </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Yes, Apply to All Periods</a> </div>
        </div>
    </div>

    -->
<!-- MODAL budget complete buttons -->
<?php include ('includes/modal-budget-complete.php') ?>
</div>
</body>
</html>