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
    <div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
        <header class="container uif-viewHeader-contentWrapper">
            <div id="ueqbqhn" class="uif-viewHeader" data-header_for=LabsProposal>
                <h1 class="uif-headerText">
                    <p id="u1p8pc9q" class="uif-viewHeader-areaTitle">Proposal Budget Development</p>
                    <span class="uif-headerText-span"> Budget: Version 2</span></h1>
                <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent=LabsProposal>
                    <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label=Initiator>
                        <label id="ujre4xu" for=u7lh763_span class="uif-label" data-label_for=u1f206ki> Final Version: </label>
                        <p id="u7lh763" class="uif-message">No</p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label=Status>
                        <label id="uk9uzz5" for=u4ch8dm_span class="uif-label" data-label_for=u1f206ld> Created: </label>
                        <p id="u4ch8dm" class="uif-message">1/25/14</p>
                    </div>
                    <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                        <label id="uj8x9wj" for=uauh5yk_span class="uif-label" data-label_for=u1f206jn> Parent Proposal: </label>
                        <p id="uauh5yk" class="uif-message"><a id="uotglr8" class="uif-actionLink" data-toggle=modal data-target="#switchdoc">#23533</a></p>
                    </div>
                    <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"><a href="#" class="dropdown-toggle" data-toggle=dropdown> more info... </a>
                        <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                            <h4 class="uif-headerText">Document Info</h4>
                            <table id="u98wduy" class="edit-table table table-condensed uif-table-fixed" role="presentation">
                                <tbody>
                                    <tr>
                                        <th scope=row>Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope=row>Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope=row>Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope=row>Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope=row>Label</th>
                                        <td class="uif-gridLayoutCell">Item</td>
                                    </tr>
                                    <tr>
                                        <th scope=row>Label</th>
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
                    <li><a id="uotglns" class="uif-actionLink" tabindex=0 data-role=Action data-submit_data="{&quot;methodToCall&quot;:&quot;print&quot;}"><span class="icon-print"></span>Print</a></li>
                    <li><a id="uotglon" class="uif-actionLink" tabindex=0 data-role=Action data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy</a></li>
                    <li><a id="uotglr8" class="uif-actionLink" tabindex=0 data-role=Action data-submit_data="{&quot;methodToCall&quot;:&quot;access&quot;}"><span class="icon-lock"></span>Access</a></li>
                    <li><a id="uotglr8" class="uif-actionLink" data-toggle=modal data-target="#budgetSettings"><span class="icon-cog"></span>Budget Settings</a></li>
                    <li><a id="uotglr8" class="uif-actionLink" data-toggle=modal data-target="#summary"><span class="icon-eye"></span>Summary</a></li>
                </ul>
            </div>
        </header>
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
                    <li class=""><a href="#p2" data-toggle="tab">Period 2</a></li>
                    <li><a href="#p3" data-toggle="tab">Period 3</a></li>
                    <li><a href="#p4" data-toggle="tab">Period 4</a></li>
                    <li><a href="#p5" data-toggle="tab">Period 5</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="p1">
                        <h3 class="">Period 1 <small>(1/12/14 - 1/11/15)</small></h3>
                        <h4>Assign Non-Personnel</h4>
                        <div class="well " style="margin-top:15px;">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="">Object Code Name</label>
                                        <select class="form-control">
                                            <option value="">select</option>
                                            <option>Equipment - Not MTDC</option>
                                            <option>Equipment Rental - Not MTDC</option>
                                            <option>Travel Expenses</option>
                                        </select>
                                        <!--<p id="u1iaxrzf" class="uif-message text-right"><a href="budget-ng-personnelCosts-projPersonnel1.php">Add Personnel</a></p>--></div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="">Description</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="">Quantity</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
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
                        <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                            <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span>Equipment</span></a></h3>
                            </header>
                            <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
                                
                                <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
                                    <thead>
                                        <tr role="row">
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Item</label></th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Description</th>
                                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Quantity</th>                                          
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ">Total Base Cost</th>
                                            <th class="" style="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" aria-label="
 Field 4: : activate to sort column ">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                                        <tr class="not-deletable">
                                            <td>Equipment - Not MTDC</td>
                                            <td>Microscope</td>
                                            <td>1</td>
                                            <td class="text-right">$0.00</td>
                                            <td><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Edit</a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span><span aria-hidden="true" class="icon-trash"></span></a></div></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </section>
                        <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                            <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span>Travel</span></a></h3>
                            </header>
                            <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
                                
                            </div>
                        </section>
                        <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                            <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span>Participant Support</span></a></h3>
                            </header>
                            <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
                                
                            </div>
                        </section>
                        <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent="Demo-Disclosure-Example1">
                            <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for="u1qq592w">
                                <h3 class="uif-headerText"><a data-role="disclosureLink" data-linkfor="facSalTen" href="#" id="u1qq592w_toggle" data-open="true" data-widgetid="u1vpenbn" data-speed="500" data-ajax="false"><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span>Other</span></a></h3>
                            </header>
                            <div id="facSalTen" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="">
                                
                            </div>
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

Modal --> 

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