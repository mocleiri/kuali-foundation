<?php
$section = 'personnel';
$page = 'personnel-assign';
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
    <div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
      <!-- VIEW HEADER --> 
	<?php include ('includes/uif-viewHeader.php') ?>
	<!-- // VIEW HEADER -->
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages=false data-role=Page data-parent=LabsProposal style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for=LabsProposal-Page>
                        <h2 class="uif-headerText"><span class="uif-headerText-span">Project Assign Personnel to Periods</span></h2>
                    </div>
                </header>
                <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Assign personnel to one or all periods and configure efforts and charges.</p>
                <br>
<!--                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">-->
<!--                    <li class="active"><a href="#p1" data-toggle="tab">Period 1 </a></li>-->
<!--                    <li><a href="#p2" data-toggle="tab">Period 2</a></li>-->
<!--                    <li><a href="#p3" data-toggle="tab">Period 3</a></li>-->
<!--                    <li><a href="#p4" data-toggle="tab">Period 4</a></li>-->
<!--                    <li><a href="#p5" data-toggle="tab">Period 5</a></li>-->
<!--                </ul>-->

                <div class="well">
                    <div class="row">
                        <form action="" method="" class="form-horizontal">
                            <div class="form-group">
                                <label for="personnel-working-period" class="control-label col-sm-2">Select period:</label>
                                <div class="col-sm-8">
                                    <select name="personnel-working-period" id="personnel-working-period" class="form-control input-sm uif-dropdownControl">
                                        <option value="1">1: 01/12/2014 - 01/12/2015</option>
                                        <option value="2">2: 01/12/2015 - 01/12/2016</option>
                                        <option value="3">3: 01/12/2016 - 01/12/2017</option>
                                        <option value="4">4: 01/12/2017 - 01/12/2018</option>
                                        <option value="5">5: 01/12/2018 - 01/12/2019</option>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <button class="btn btn-default">Load period</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="p1">
                        <div class="col-md-12">
                            <div class="well well-sm" style="margin-top:15px;">

                                <div class="row">
                                    <div class="col-md-4">
                                        <h3>Period 1<br /><small>01/12/2014 - 01/12/2015</small></h3>
                                    </div>
                                    <div class="col-md-8 text-right">
                                        <div class="btn-group" style="margin-top:15px">
                                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Detailed View <span class="caret"></span> </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="">Summary View</a> </li>
                                                <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="">Detailed View</a> </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <hr />

                                <h4> Assign Personnel</h4>
                                <hr>
                                <div class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Personnel</label>
                                        <div class="col-sm-10">
                                            <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl" data-role="Control" data-control_for="u11k8c5e">
                                                <option value="B">- Select -</option>
                                                <option>Jason Bourne</option>
                                                <option>Marie Calendar</option>
                                                <option>Maynard James Keenan</option>
                                                <option>Ramen Noodle</option>
                                                <option>TBN</option>
                                            </select>
<!--                                            <br /><a href="#" data-toggle="modal" data-target="add-multi-personnel">Add multiple</a>-->
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Object Code Name</label>
                                        <div class="col-sm-10">
                                            <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl" data-role="Control" data-control_for="u11k8c5e">
                                                <option value="B">Select</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-2 control-label">Group</label>
                                        <div class="col-sm-10">
                                            <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl" data-role="Control" data-control_for="u11k8c5e">
                                                <option value="B">Select</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button type="submit" class="btn btn-default btn-xs">Assign to Period 1</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<!--                    <div class="tab-pane" id="p2">-->
<!--                        <p>orange orange orange orange orange</p>-->
<!--                    </div>-->
<!--                    <div class="tab-pane" id="p3">-->
<!--                        <p>yellow yellow yellow yellow yellow</p>-->
<!--                    </div>-->
<!--                    <div class="tab-pane" id="p4">-->
<!--                        <p>green green green green green</p>-->
<!--                    </div>-->
<!--                    <div class="tab-pane" id="p5">-->
<!--                        <p>blue blue blue blue blue</p>-->
<!--                    </div>-->
                </div>
<!--                <script type="text/javascript">-->
<!--    jQuery(document).ready(function ($) {-->
<!--        $('#tabs').tab();-->
<!--    });-->
<!--</script> -->
                <br>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Faculty Salaries - Tenured</span></a></h3>
                    </header>
                    <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Apply Settings to All Periods</a></div>
                        <br>
                        <br>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Start date</th>
                                    <th>End date</th>
                                    <th>% Effort</th>
                                    <th>% Charged</th>
                                    <th>Period type</th>
                                    <th>Requested salary</th>
                                    <th>Calculated fringe</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span class="uif-text-medium">Ramen Noodle</span> <small class="text-muted">(PI)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Marie Calendar</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Jim Selmer</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                
                
                
                
                
                
                
                
                
                
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="postDoc" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Post Doctoral Staff</span></a></h3>
                    </header>
                    <div id="postDoc" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Apply Settings to All Periods</a></div>
                        <br>
                        <br>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Start date</th>
                                    <th>End date</th>
                                    <th>% Effort</th>
                                    <th>% Charged</th>
                                    <th>Period type</th>
                                    <th>Requested salary</th>
                                    <th>Calculated fringe</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span class="uif-text-medium">Ramen Noodle</span> <small class="text-muted">(PI)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Marie Calendar</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Jim Selmer</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                
                
                
                
                
                
                
                
                
                
                
                
                
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                        <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="summerFac" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Summer Faculty</span></a></h3>
                    </header>
                    <div id="summerFac" data-role="disclosureContent" data-open="true" class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Apply Settings to All Periods</a></div>
                        <br>
                        <br>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Start date</th>
                                    <th>End date</th>
                                    <th>% Effort</th>
                                    <th>% Charged</th>
                                    <th>Period type</th>
                                    <th>Requested salary</th>
                                    <th>Calculated fringe</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span class="uif-text-medium">Ramen Noodle</span> <small class="text-muted">(PI)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Marie Calendar</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                                <tr>
                                    <td><span class="uif-text-medium">Jim Selmer</span> <small class="text-muted">(KP)</small></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_start" class="control-label"> <span class="sr-only">Start date</span> </label>
                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_end" class="control-label"> <span class="sr-only">End date</span> </label>
                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_effort" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_charged" class="control-label"> <span class="sr-only">Percent charged</span> </label>
                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                        </div></td>
                                    <td><div class="form-group-sm">
                                            <label for="line_1_period_type" class="control-label"> <span class="sr-only">Percent effort</span> </label>
                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                <option>Calendar</option>
                                            </select>
                                        </div></td>
                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                
                
                
                
                
                
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer=true data-parent=LabsProposal style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role=Action data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}">Save</button>
            <button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role=Action data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}">Save and Continue</button>
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
    <div class="jquerybubblepopup jquerybubblepopup-kr-error-cs" style="margin: 0px 0px 0px 395.5px; opacity: 0; top: 227px; left: 542px; position: absolute; display: none;" id="jquerybubblepopup-1393862100-0" data-for=u11k8c13_control>
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

<!-- MODAL -- budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL -- budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>

<!-- MODAL -- budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>




<!-- NO QUOTES AROUND ATTRIBUTES
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
                        <tr class="">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Salary</a></td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>156934</td>
                            <td>784670</td>
                        </tr>
                        <tr class="">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Fringe</a></td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>37345</td>
                            <td>186725</td>
                        </tr>
                        <tr class="">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>28284</td>
                            <td>141420</td>
                        </tr>
                        <tr class="">
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
                        <tr class="">
                            <td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>38546</td>
                            <td>219348</td>
                        </tr>
                        <tr class="">
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
 --><!--
<div class="modal fade" id="budgetSettings" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Budget Settings</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal uif-cssGridGroup" role=form>
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
 Modal --><!-- 
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
 Modal -->
</body>
</html>