<?php
$section = 'asdf';
$page = 'asdf';
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

<body id="Uif-Application" style="padding-bottom: 176px;">
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
                    <span class="uif-headerText-span"> Budget: Version 5</span> <span class="uif-supportTitle-wrapper">
                    <div id="u1hgnm9q" class="uif-viewHeader-supportTitle" data-parent="ueqbqhn">&lt;something here&gt;</div>
                    </span> </h1>
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
                        <p id="uauh5yk" class="uif-message"> <a href="prop-basics-details.php">#23533</a></p>
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
                    <li> <a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#budgetSettings"><span class="icon-cog"></span>Budget Settings</a> </li>
                    <li> <a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span class="icon-eye"></span>Summary</a> </li>
                </ul>
            </div>
        </header>
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="">
                <div class="row">
                    <div class="col-md-12 text-center" ><br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <p>You have created a new budget version for Proposal #25233. How would you like to proceeed?</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 text-right" ><a href="#" class="btn btn-primary btn-lg">Guide me through the steps.</a></div>
                    <div class="col-md-6 text-left"> <a href="budget-ng-summary.php" class="btn btn-primary btn-lg">I'll go through on my own.</a></div>
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER --> 
        
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
<!-- MODAL -- budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="budgetSettings" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Budget Settings</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
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
                        <label for="inputEmail3" class="col-sm-3 control-label">Unrecovered F&amp;A Rate Type</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">F&amp;A Rate Type:</label>
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
                                    Use for Proposal Submission </label>
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

<div id="jstree-marker" style="display: none;">»</div>
<div id="jstree-marker-line" style="display: none;"></div>
<div id="vakata-contextmenu"></div>
</body>
</html>