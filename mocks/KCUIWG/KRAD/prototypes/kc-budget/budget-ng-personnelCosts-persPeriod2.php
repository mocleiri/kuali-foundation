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
                    </a></div>
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
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qku" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-ActionListView&quot;);"> Action List </a></li>
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qlp" tabindex="0" data-role="Action" data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-DocSearchView&quot;);"> Doc Search </a></li>
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
        <h2 class="uif-headerText"><span class="uif-headerText-span"> Assign Personnel to Periods</span></h2>
    </div>
</header>
<p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Assign personnel to one or all periods and configure efforts and charges.</p>
<br>
<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
    <li class="active"><a href="#p1" data-toggle="tab">Period 1 </a></li>
    <li class=""><a href="#p2" data-toggle="tab">Period 2</a></li>
    <li><a href="#p3" data-toggle="tab">Period 3</a></li>
    <li><a href="#p4" data-toggle="tab">Period 4</a></li>
    <li><a href="#p5" data-toggle="tab">Period 5</a></li>
</ul>
<div id="my-tab-content" class="tab-content">
<div class="tab-pane active" id="p1">
<h3 class="">Period 1 <small>(1/12/14 - 1/11/15)</small></h3>
<h4>Assign Personnel</h4>
<div class="well " style="margin-top:15px;">
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label for="">Person</label>
                <select class="form-control">
                    <option value="">select</option>
                    <option>Ward Cleaver</option>
                    <option>John Coltrane</option>
                    <option>Oliver Elgin</option>
                    <option>Darren Devaney</option>
                    <option>Emory Eagle</option>
                    <option>TBN</option>
                </select>
                <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label for="">Object Code</label>
                <select class="form-control">
                    <option value="">select</option>
                    <option value="400255">Administrative Staff - Off</option>
                    <option value="400250">Administrative Staff - On</option>
                    <option value="421568">CWSP Credit - Not MTDC</option>
                    <option value="400550">Electronic Assembly - On</option>
                    <option value="400654">Exempt Technical - On</option>
                    <option value="400135">Faculty Emeriti</option>
                    <option value="400136">Faculty Retired Non-Tenured - On</option>
                    <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                    <option value="400040">Faculty Salaries Non-Tenured - On</option>
                    <option value="400090">Faculty Salaries Tenured - Off</option>
                    <option value="400025">Faculty Salaries Tenured - On</option>
                    <option value="400315">Fellows - Non-Student- Not MTDC</option>
                    <option value="400700">Graduate Student Staff - On</option>
                    <option value="400452">Hourly Personnel - Off</option>
                    <option value="400450">Hourly Personnel - On</option>
                    <option value="400552">Mechanical Assembly - On</option>
                    <option value="400756">MIT Students - Off</option>
                    <option value="400754">MIT Students - On</option>
                    <option value="400155">Other Academic Staff - Off</option>
                    <option value="400140">Other Academic Staff - On</option>
                    <option value="400390">Post-Doctoral Staff</option>
                    <option value="400365">Program Manager</option>
                    <option value="400370">Project Engineering Staff - On</option>
                    <option value="400601">Project Support Staff - Off</option>
                    <option value="400600">Project Support Staff - On</option>
                    <option value="400556">Quality Control - On</option>
                    <option value="400708">Research Asst - Off</option>
                    <option value="400706">Research Asst - On</option>
                    <option value="400355">Research Staff - Off</option>
                    <option value="400350">Research Staff - On</option>
                    <option value="400115">Summer - Other Academic - Off</option>
                    <option value="400070">Summer Faculty - Off</option>
                    <option value="400005">Summer Faculty - On</option>
                    <option value="400050">Summer Other Academic - On</option>
                    <option value="420262">Temporary Help</option>
                    <option value="400768">Undergrad S&amp;W UROP - Off</option>
                    <option value="400770">Undergrad S&amp;W UROP - On</option>
                </select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label for="exampleInputPassword1">Group</label>
                <small class="text-muted"> (optional)</small>
                <select class="form-control">
                    <option value="">select</option>
                    <option>My Custom Group 1</option>
                    <option>My Custom Group 2</option>
                    <option>My Custom Group 3</option>
                    <option>My Custom Group 4</option>
                    <option>My Custom Group 5</option>
                    <option>Create New Group...</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 text-right"><a href="budget-ng-personnelCosts-persPeriod2.php" type="submit" class="btn btn-primary btn-xs">Assign to Period 1</a></div>
    </div>
</div>
<h4 class="pull-left" >Assigned Personnel</h4>
<div class="pull-right"><a href="#" class="btn btn-xs btn-default"  data-toggle="modal" data-target="#applyRates">Apply P1 Data to All Periods</a></div>
<section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Faculty Salaries - Tenured</span></a></h3>
    </header>
    <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
        <p class=""><a href="#" class="uif-actionLink"  data-toggle="modal" data-target="#details">Details &amp; Rates</a></p>
        <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
            <thead>
            <tr role="row">
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Person</label></th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ">Start</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 3: : activate to sort column ">End</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Effort</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ">Charged</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Period Type</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Requested Salary</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Calculated Fringe</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Actions</th>
            </tr>
            </thead>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <tr>
                <td>Ward Cleaver <small class="text-muted">(PI)</small></td>
                <td><span data-edit-type="input">1/12/14</span><small class="text-muted"></small></td>
                <td><span data-edit-type="input">1/12/15</span></td>
                <td class="text-right"><span class="date-edit-type">100%</span></td>
                <td><div class="dropdown dropdown-large">
                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#fcf8e3;">
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
                        </div>50%
                    </div>
                </td>
                <td>Calendar</td>
                <td class="text-right">$142,525.00</td>
                <td class="text-right">$0.00</td>
                <td>
                    <!--                                                <div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div>-->
                    <a class="icon icon-edit uif-btn-edit" href="#"><span class="sr-only">Edit</span></a>
                </td>
            </tr>
            <tr>
                <td>Oliver Elgin <small class="text-muted">Research Assistant</small></td>
                <td><span data-edit-type="input">1/12/14</span><small class="text-muted"></small></td>
                <td><span data-edit-type="input">1/12/15</span></td>
                <td class="text-right"><span class="date-edit-type">100%</span></td>
                <td><div class="dropdown dropdown-large">
                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#fcf8e3;">
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
                        </div>50%
                    </div>
                </td>
                <td>Calendar</td>
                <td class="text-right">$142,525.00</td>
                <td class="text-right">$0.00</td>
                <td>
                    <!--                                                <div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div>-->
                    <a class="icon icon-edit uif-btn-edit" href="#"><span class="sr-only">Edit</span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
<section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="postDoc" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Post Doctoral Staff</span></a></h3>
    </header>
    <div id="postDoc" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
        <p class=""><a href="#" class="uif-actionLink"  data-toggle="modal" data-target="#details">Details &amp; Rates</a></p>
        <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
            <thead>
            <tr role="row">
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Person</label></th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ">Start</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 3: : activate to sort column ">End</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Effort</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ">Charged</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Period Type</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Requested Salary</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Calculated Fringe</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Actions</th>
            </tr>
            </thead>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <tr>
                <td>John Coltrane <small class="text-muted">(Key Person)</small></td>
                <td>1/12/14<small class="text-muted"></small></td>
                <td>1/12/15</td>
                <td class="text-right">100%</td>
                <td><div class="dropdown dropdown-large">
                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#fcf8e3;">
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
                    <!--                                                <div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div>-->
                    <a class="icon icon-edit uif-btn-edit" href="#"><span class="sr-only">Edit</span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
<section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="summerFac" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Summer Faculty</span></a></h3>
    </header>
    <div id="summerFac" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
        <p class=""><a href="#" class="uif-actionLink"  data-toggle="modal" data-target="#details">Details &amp; Rates</a></p>
        <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
            <thead>
            <tr role="row">
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Person</label></th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ">Start</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 3: : activate to sort column ">End</th>
                <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Effort</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ">Charged</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Period Type</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Requested Salary</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Calculated Fringe</th>
                <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Actions</th>
            </tr>
            </thead>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <tr>
                <td>Ward Cleaver <small class="text-muted">(PI)</small></td>
                <td>1/12/14<small class="text-muted"></small></td>
                <td>1/12/15</td>
                <td class="text-right">100%</td>
                <td><div class="dropdown dropdown-large">
                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -808px;top: 26px;width: 890px;padding:15px; background:#fcf8e3;">
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
                    <!--                                                <div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div>-->
                    <a class="icon icon-edit uif-btn-edit" href="#"><span class="sr-only">Edit</span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
</div>
<div class="tab-pane" id="p2">
    <h3 class="">Period 2 <small>(1/12/15 - 1/11/16)</small></h3>
    <h4>Assign Personnel</h4>
    <div class="well " style="margin-top:15px;">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Person</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>Ward Cleaver</option>
                        <option>John Coltrane</option>
                        <option>Oliver Elgin</option>
                        <option>Darren Devaney</option>
                        <option>Emory Eagle</option>
                        <option>TBN</option>
                    </select>
                    <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Object Code</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option value="400255">Administrative Staff - Off</option>
                        <option value="400250">Administrative Staff - On</option>
                        <option value="421568">CWSP Credit - Not MTDC</option>
                        <option value="400550">Electronic Assembly - On</option>
                        <option value="400654">Exempt Technical - On</option>
                        <option value="400135">Faculty Emeriti</option>
                        <option value="400136">Faculty Retired Non-Tenured - On</option>
                        <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                        <option value="400040">Faculty Salaries Non-Tenured - On</option>
                        <option value="400090">Faculty Salaries Tenured - Off</option>
                        <option value="400025">Faculty Salaries Tenured - On</option>
                        <option value="400315">Fellows - Non-Student- Not MTDC</option>
                        <option value="400700">Graduate Student Staff - On</option>
                        <option value="400452">Hourly Personnel - Off</option>
                        <option value="400450">Hourly Personnel - On</option>
                        <option value="400552">Mechanical Assembly - On</option>
                        <option value="400756">MIT Students - Off</option>
                        <option value="400754">MIT Students - On</option>
                        <option value="400155">Other Academic Staff - Off</option>
                        <option value="400140">Other Academic Staff - On</option>
                        <option value="400390">Post-Doctoral Staff</option>
                        <option value="400365">Program Manager</option>
                        <option value="400370">Project Engineering Staff - On</option>
                        <option value="400601">Project Support Staff - Off</option>
                        <option value="400600">Project Support Staff - On</option>
                        <option value="400556">Quality Control - On</option>
                        <option value="400708">Research Asst - Off</option>
                        <option value="400706">Research Asst - On</option>
                        <option value="400355">Research Staff - Off</option>
                        <option value="400350">Research Staff - On</option>
                        <option value="400115">Summer - Other Academic - Off</option>
                        <option value="400070">Summer Faculty - Off</option>
                        <option value="400005">Summer Faculty - On</option>
                        <option value="400050">Summer Other Academic - On</option>
                        <option value="420262">Temporary Help</option>
                        <option value="400768">Undergrad S&amp;W UROP - Off</option>
                        <option value="400770">Undergrad S&amp;W UROP - On</option>
                    </select>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="exampleInputPassword1">Group</label>
                    <small class="text-muted"> (optional)</small>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>My Custom Group 1</option>
                        <option>My Custom Group 2</option>
                        <option>My Custom Group 3</option>
                        <option>My Custom Group 4</option>
                        <option>My Custom Group 5</option>
                        <option>Create New Group...</option>
                    </select>
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
    <h4>Assign Personnel</h4>
    <div class="well " style="margin-top:15px;">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Person</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>Ward Cleaver</option>
                        <option>John Coltrane</option>
                        <option>Oliver Elgin</option>
                        <option>Darren Devaney</option>
                        <option>Emory Eagle</option>
                        <option>TBN</option>
                    </select>
                    <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Object Code</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option value="400255">Administrative Staff - Off</option>
                        <option value="400250">Administrative Staff - On</option>
                        <option value="421568">CWSP Credit - Not MTDC</option>
                        <option value="400550">Electronic Assembly - On</option>
                        <option value="400654">Exempt Technical - On</option>
                        <option value="400135">Faculty Emeriti</option>
                        <option value="400136">Faculty Retired Non-Tenured - On</option>
                        <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                        <option value="400040">Faculty Salaries Non-Tenured - On</option>
                        <option value="400090">Faculty Salaries Tenured - Off</option>
                        <option value="400025">Faculty Salaries Tenured - On</option>
                        <option value="400315">Fellows - Non-Student- Not MTDC</option>
                        <option value="400700">Graduate Student Staff - On</option>
                        <option value="400452">Hourly Personnel - Off</option>
                        <option value="400450">Hourly Personnel - On</option>
                        <option value="400552">Mechanical Assembly - On</option>
                        <option value="400756">MIT Students - Off</option>
                        <option value="400754">MIT Students - On</option>
                        <option value="400155">Other Academic Staff - Off</option>
                        <option value="400140">Other Academic Staff - On</option>
                        <option value="400390">Post-Doctoral Staff</option>
                        <option value="400365">Program Manager</option>
                        <option value="400370">Project Engineering Staff - On</option>
                        <option value="400601">Project Support Staff - Off</option>
                        <option value="400600">Project Support Staff - On</option>
                        <option value="400556">Quality Control - On</option>
                        <option value="400708">Research Asst - Off</option>
                        <option value="400706">Research Asst - On</option>
                        <option value="400355">Research Staff - Off</option>
                        <option value="400350">Research Staff - On</option>
                        <option value="400115">Summer - Other Academic - Off</option>
                        <option value="400070">Summer Faculty - Off</option>
                        <option value="400005">Summer Faculty - On</option>
                        <option value="400050">Summer Other Academic - On</option>
                        <option value="420262">Temporary Help</option>
                        <option value="400768">Undergrad S&amp;W UROP - Off</option>
                        <option value="400770">Undergrad S&amp;W UROP - On</option>
                    </select>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="exampleInputPassword1">Group</label>
                    <small class="text-muted"> (optional)</small>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>My Custom Group 1</option>
                        <option>My Custom Group 2</option>
                        <option>My Custom Group 3</option>
                        <option>My Custom Group 4</option>
                        <option>My Custom Group 5</option>
                        <option>Create New Group...</option>
                    </select>
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
    <h4>Assign Personnel</h4>
    <div class="well " style="margin-top:15px;">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Person</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>Ward Cleaver</option>
                        <option>John Coltrane</option>
                        <option>Oliver Elgin</option>
                        <option>Darren Devaney</option>
                        <option>Emory Eagle</option>
                        <option>TBN</option>
                    </select>
                    <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Object Code</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option value="400255">Administrative Staff - Off</option>
                        <option value="400250">Administrative Staff - On</option>
                        <option value="421568">CWSP Credit - Not MTDC</option>
                        <option value="400550">Electronic Assembly - On</option>
                        <option value="400654">Exempt Technical - On</option>
                        <option value="400135">Faculty Emeriti</option>
                        <option value="400136">Faculty Retired Non-Tenured - On</option>
                        <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                        <option value="400040">Faculty Salaries Non-Tenured - On</option>
                        <option value="400090">Faculty Salaries Tenured - Off</option>
                        <option value="400025">Faculty Salaries Tenured - On</option>
                        <option value="400315">Fellows - Non-Student- Not MTDC</option>
                        <option value="400700">Graduate Student Staff - On</option>
                        <option value="400452">Hourly Personnel - Off</option>
                        <option value="400450">Hourly Personnel - On</option>
                        <option value="400552">Mechanical Assembly - On</option>
                        <option value="400756">MIT Students - Off</option>
                        <option value="400754">MIT Students - On</option>
                        <option value="400155">Other Academic Staff - Off</option>
                        <option value="400140">Other Academic Staff - On</option>
                        <option value="400390">Post-Doctoral Staff</option>
                        <option value="400365">Program Manager</option>
                        <option value="400370">Project Engineering Staff - On</option>
                        <option value="400601">Project Support Staff - Off</option>
                        <option value="400600">Project Support Staff - On</option>
                        <option value="400556">Quality Control - On</option>
                        <option value="400708">Research Asst - Off</option>
                        <option value="400706">Research Asst - On</option>
                        <option value="400355">Research Staff - Off</option>
                        <option value="400350">Research Staff - On</option>
                        <option value="400115">Summer - Other Academic - Off</option>
                        <option value="400070">Summer Faculty - Off</option>
                        <option value="400005">Summer Faculty - On</option>
                        <option value="400050">Summer Other Academic - On</option>
                        <option value="420262">Temporary Help</option>
                        <option value="400768">Undergrad S&amp;W UROP - Off</option>
                        <option value="400770">Undergrad S&amp;W UROP - On</option>
                    </select>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="exampleInputPassword1">Group</label>
                    <small class="text-muted"> (optional)</small>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>My Custom Group 1</option>
                        <option>My Custom Group 2</option>
                        <option>My Custom Group 3</option>
                        <option>My Custom Group 4</option>
                        <option>My Custom Group 5</option>
                        <option>Create New Group...</option>
                    </select>
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
    <h4>Assign Personnel</h4>
    <div class="well " style="margin-top:15px;">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Person</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>Ward Cleaver</option>
                        <option>John Coltrane</option>
                        <option>Oliver Elgin</option>
                        <option>Darren Devaney</option>
                        <option>Emory Eagle</option>
                        <option>TBN</option>
                    </select>
                    <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="">Object Code</label>
                    <select class="form-control">
                        <option value="">select</option>
                        <option value="400255">Administrative Staff - Off</option>
                        <option value="400250">Administrative Staff - On</option>
                        <option value="421568">CWSP Credit - Not MTDC</option>
                        <option value="400550">Electronic Assembly - On</option>
                        <option value="400654">Exempt Technical - On</option>
                        <option value="400135">Faculty Emeriti</option>
                        <option value="400136">Faculty Retired Non-Tenured - On</option>
                        <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                        <option value="400040">Faculty Salaries Non-Tenured - On</option>
                        <option value="400090">Faculty Salaries Tenured - Off</option>
                        <option value="400025">Faculty Salaries Tenured - On</option>
                        <option value="400315">Fellows - Non-Student- Not MTDC</option>
                        <option value="400700">Graduate Student Staff - On</option>
                        <option value="400452">Hourly Personnel - Off</option>
                        <option value="400450">Hourly Personnel - On</option>
                        <option value="400552">Mechanical Assembly - On</option>
                        <option value="400756">MIT Students - Off</option>
                        <option value="400754">MIT Students - On</option>
                        <option value="400155">Other Academic Staff - Off</option>
                        <option value="400140">Other Academic Staff - On</option>
                        <option value="400390">Post-Doctoral Staff</option>
                        <option value="400365">Program Manager</option>
                        <option value="400370">Project Engineering Staff - On</option>
                        <option value="400601">Project Support Staff - Off</option>
                        <option value="400600">Project Support Staff - On</option>
                        <option value="400556">Quality Control - On</option>
                        <option value="400708">Research Asst - Off</option>
                        <option value="400706">Research Asst - On</option>
                        <option value="400355">Research Staff - Off</option>
                        <option value="400350">Research Staff - On</option>
                        <option value="400115">Summer - Other Academic - Off</option>
                        <option value="400070">Summer Faculty - Off</option>
                        <option value="400005">Summer Faculty - On</option>
                        <option value="400050">Summer Other Academic - On</option>
                        <option value="420262">Temporary Help</option>
                        <option value="400768">Undergrad S&amp;W UROP - Off</option>
                        <option value="400770">Undergrad S&amp;W UROP - On</option>
                    </select>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="exampleInputPassword1">Group</label>
                    <small class="text-muted"> (optional)</small>
                    <select class="form-control">
                        <option value="">select</option>
                        <option>My Custom Group 1</option>
                        <option>My Custom Group 2</option>
                        <option>My Custom Group 3</option>
                        <option>My Custom Group 4</option>
                        <option>My Custom Group 5</option>
                        <option>Create New Group...</option>
                    </select>
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
<!-- VIEW FOOTER    <button type="button"  id="save-continue" class="btn btn-default">Complete Budget</button>--> <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;"> <div class="global-actions"> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save </a> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Reload </a>

 <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem"  data-toggle="modal" data-target="#modal-budget-complete" >Complete Budget </a> </div>
            
    <a href="budget-ng-personnelCosts-projPersonnel2.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem"><span class="icon-chevron-left"></span> Back</a>
    <a href="budget-ng-non-personnel.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Continue <span class="icon-chevron-right"></span></a>
</div>
<!-- DIALOGS/Placeholders --></div>
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
        $('#tabs').tab();
    });</script><!-- MODAL budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
<!-- MODAL budget complete buttons -->
<?php include ('includes/modal-budget-complete.php') ?>




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
                                <label for="inputEmail3" class="col-sm-3 control-label">Justification</label>
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
                <button type="button" class="btn btn-primary">Apply to Later Periods</button>
            </div>
        </div>
    </div>
</div>
<!-- end Modal -->


<!-- MODAL -- Apply Rates  -->




<div class="modal fade in" id="applyRates" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display:;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Apply Rates</h4>
            </div>
            <div class="modal-body">
                <p>Would you like to apply Period 1 personnel to all periods? Please note that all other personnel info will be overwritten.</p>
            </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Yes, Apply to All Periods</a> </div>
        </div>
    </div>
</div>









</body>
</html>