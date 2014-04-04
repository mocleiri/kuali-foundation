<?php
$page = 'rates';
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
        <!-- VIEW HEADER -->
        <?php include ('includes/uif-viewHeader.php') ?>
        <!-- // VIEW HEADER  --> 
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages=false data-role=Page data-parent=LabsProposal style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for=LabsProposal-Page>
                        <h2 class="uif-headerText"><span class="uif-headerText-span">Rates </span></h2>
                    </div>
                </header>
                <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Verify the default rates set by your institution. You can override them if necessary by clicking the "edit" icon to the right of each row.</p>
                <br>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=reserachFA href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Research F&amp;A</span></a></h3>
                    </header>
                    <div id="reserachFA" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2008</td>
                                    <td>07/01/2007</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2008</td>
                                    <td>07/01/2007</td>
                                    <td>48.00</td>
                                    <td>48.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Materials and Services</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2002</td>
                                    <td>07/01/2001</td>
                                    <td>10.00</td>
                                    <td>10.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Materials and Services</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2002</td>
                                    <td>07/01/2001</td>
                                    <td>10.00</td>
                                    <td>10.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>S&amp;W</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>1980</td>
                                    <td>07/01/1979</td>
                                    <td>62.00</td>
                                    <td>62.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>S&amp;W</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>1980</td>
                                    <td>07/01/1979</td>
                                    <td>63.00</td>
                                    <td>63.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Salaries</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>9.00</td>
                                    <td>9.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Salaries</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>62.00</td>
                                    <td>62.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>TDC</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2004</td>
                                    <td>07/01/2003</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>TDC</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2004</td>
                                    <td>07/01/2003</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2007</td>
                                    <td>01/07/2007</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2007</td>
                                    <td>01/07/2007</td>
                                    <td>48.00</td>
                                    <td>48.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=fringebenefits href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Fringe Benefits</span></a></h3>
                    </header>
                    <div id="fringebenefits" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td>EB on LA</td>
                                    <td>No</td>
                                    <td>Yes</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>25.00</td>
                                    <td>25.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>EB on LA</td>
                                    <td>Yes</td>
                                    <td>Yes</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>27.00</td>
                                    <td>27.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Research Rate</td>
                                    <td>No</td>
                                    <td>Yes</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>25.00</td>
                                    <td>25.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Research Rate</td>
                                    <td>Yes</td>
                                    <td>Yes</td>
                                    <td>2006</td>
                                    <td>07/01/2005</td>
                                    <td>27.00</td>
                                    <td>27.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>UROP Rate</td>
                                    <td>No</td>
                                    <td>Yes</td>
                                    <td>2004</td>
                                    <td>07/01/2003</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>UROP Rate</td>
                                    <td>Yes</td>
                                    <td>Yes</td>
                                    <td>2004</td>
                                    <td>07/01/2003</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=inflation href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Inflation</span></a></h3>
                    </header>
                    <div id="inflation" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td class="result-table">Administrative Salaries (7/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Administrative Salaries (7/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Administrative Salaries (7/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Administrative Salaries (7/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Faculty Salaries (6/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Faculty Salaries (6/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Faculty Salaries (6/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Faculty Salaries (6/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Materials and Services</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Materials and Services</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Materials and Services</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Materials and Services</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">07/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Research Staff (1/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">01/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Research Staff (1/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">01/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Research Staff (1/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">01/01/2015</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Research Staff (1/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2015</td>
                                    <td class="result-table">01/01/2015</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Students (6/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2011</td>
                                    <td class="result-table">06/01/2011</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Students (6/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2011</td>
                                    <td class="result-table">06/01/2011</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Support Staff Salaries (4/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">04/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Support Staff Salaries (4/1)</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">04/01/2014</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2008</td>
                                    <td>07/01/2007</td>
                                    <td>0.00</td>
                                    <td>0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>MTDC</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2008</td>
                                    <td>07/01/2007</td>
                                    <td>48.00</td>
                                    <td>48.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Materials and Services</td>
                                    <td>No</td>
                                    <td>No</td>
                                    <td>2002</td>
                                    <td>07/01/2001</td>
                                    <td>10.00</td>
                                    <td>10.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td>Materials and Services</td>
                                    <td>Yes</td>
                                    <td>No</td>
                                    <td>2002</td>
                                    <td>07/01/2001</td>
                                    <td>10.00</td>
                                    <td>10.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=vacation href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Vacation</span></a></h3>
                    </header>
                    <div id="vacation" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td class="result-table">Vacation</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2006</td>
                                    <td class="result-table">07/01/2005</td>
                                    <td class="result-table">10.50</td>
                                    <td class="result-table">10.50</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Vacation</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2006</td>
                                    <td class="result-table">07/01/2005</td>
                                    <td class="result-table">9.50</td>
                                    <td class="result-table">9.50</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Vacation on LA</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2006</td>
                                    <td class="result-table">07/01/2005</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Vacation on LA</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2006</td>
                                    <td class="result-table">07/01/2005</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Administrative Salaries (7/1)</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2014</td>
                                    <td class="result-table">07/01/2013</td>
                                    <td class="result-table">3.00</td>
                                    <td class="result-table">3.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=laballocationSalaries href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Lab Allocation - Salaries</span></a></h3>
                    </header>
                    <div id="laballocationSalaries" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - Salaries</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2012</td>
                                    <td class="result-table">07/01/2011</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - Salaries</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2012</td>
                                    <td class="result-table">07/01/2011</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=laballocationOther href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Lab Allocation - Other</span></a></h3>
                    </header>
                    <div id="laballocationOther" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="edit-table table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - Utilities</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2005</td>
                                    <td class="result-table">07/01/2004</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - Utilities</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2005</td>
                                    <td class="result-table">07/01/2004</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - M&amp;S</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2011</td>
                                    <td class="result-table">07/01/2010</td>
                                    <td class="result-table">1.00</td>
                                    <td class="result-table">1.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - M&amp;S</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2011</td>
                                    <td class="result-table">07/01/2010</td>
                                    <td class="result-table">1.00</td>
                                    <td class="result-table">1.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Lab Allocation - Salaries</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">2012</td>
                                    <td class="result-table">07/01/2011</td>
                                    <td class="result-table">8.00</td>
                                    <td class="result-table">8.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section id="u1qq592w" class="uif-disclosure uif-boxLayoutVerticalItem clearfix" data-parent=Demo-Disclosure-Example1>
                    <header id="u1l3ufy3" class="uif-sectionHeader" data-header_for=u1qq592w>
                        <h3 class="uif-headerText"><a data-role=disclosureLink data-linkfor=other href="#" id="u1qq592w_toggle" data-open=true data-widgetid=u1vpenbn data-speed=500 data-ajax=false><span class="uif-headerText-span"><span id="u1qq592w_toggle_exp" class="icon-caret-down"></span><span style="display:none;" id="u1qq592w_toggle_col" class="icon-caret-right"></span> Other</span></a></h3>
                    </header>
                    <div id="other" data-role=disclosureContent data-open=true class="uif-disclosureContent">
                        <div class="pull-right"><a href="#" class="btn btn-xs btn-default">Reset to Institutional Defaults</a> <a href="#" class="btn btn-xs btn-default">Refresh Rates</a></div>
                        <br>
                        <br>
                        <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby=Demo-LightTableGroup1_lightTable_info>
                            <thead>
                                <tr role=row>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-sort=ascending aria-label="
 Field 1: : activate to sort column descending"><label id="urh9zx8" class="uif-label">Description</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> Campus Contract </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 2: : activate to sort column ascending"><label id="u9vt7yu" class="uif-label"> On Campus </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 3: : activate to sort column ascending"><label id="uosze9s" class="uif-label"> Fiscal Year </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Start Date </label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Institution Rate</label></th>
                                    <th class="sorting" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> Application Rate </label></th>
                                    <th class="" role=columnheader tabindex=0 aria-controls=Demo-LightTableGroup1_lightTable rowspan=1 colspan=1 aria-label="
 Field 4: : activate to sort column ascending"><label id="u9u34v6" class="uif-label"> </label></th>
                                <tr class="not-deletable">
                                    <td class="result-table">Other</td>
                                    <td class="result-table">No</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2000</td>
                                    <td class="result-table">07/01/1999</td>
                                    <td class="result-table">0.00</td>
                                    <td class="result-table">0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                                <tr class="not-deletable">
                                    <td class="result-table">Other</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">Yes</td>
                                    <td class="result-table">2000</td>
                                    <td class="result-table">07/01/1999</td>
                                    <td class="result-table">0.00</td>
                                    <td class="result-table">0.00</td>
                                    <td><a href="#" class="icon icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a></td>
                                </tr>
                            </thead>
                            <tbody role=alert aria-live=polite aria-relevant=all>
                            </tbody>
                        </table>
                    </div>
                </section>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <a href="budget-ng-periods-saved.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem">Go back</a>
            <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save</button>
            <a href="budget-ng-personnelCosts-projPersonnel1.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Save and Continue</a>
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
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
 

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