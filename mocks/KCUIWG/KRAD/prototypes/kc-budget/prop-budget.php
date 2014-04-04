<?php
$page = 'budgets';
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
</head>

<body id="Uif-Application" style="padding-bottom: 432px;">
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
        
        <!-- VIEW HEADER -->
        <?php include ('includes/uif-viewHeader-pd.php') ?>
        <!-- // VIEW HEADER  --> 
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-proposal.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span">Budgets</span> </h2>
                    </div>
                    <div id="uw4ggjs" class="uif-verticalBoxGroup uif-header-lowerGroup" data-parent="LabsProposal-Page"> </div>
                </header>
                
                <!-- radio -->
                
                <section id="u11ymttz_line0" class="uif-gridSubSection" data-parent="u10i8ym5_line0">
                    <header id="u1cnha8c_line0" class="uif-subSectionHeader" data-header_for="u11ymttz_line0">
                        <h4 class="uif-headerText"> <span class="uif-headerText-span"> Versions </span> </h4>
                    </header>
                    <p class="pull-left">The following budgets are linked to this proposal:</p>
                    <a href="" data-toggle="modal" data-target="#createNew" class="btn btn-primary btn-xs pull-right"> Create New</a> 
                    
                    <!-- Modal -->
                    <div class="modal fade" id="createNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Create a Budget Version</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-3 control-label">Parent Proposal</label>
                                            <div class="col-sm-9">
                                                <p class="form-control-static">#23533 </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-3 control-label">Budget Name</label>
                                            <div class="col-sm-9">
                                                <input type=" text" class="form-control" id="" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> Create <span class="caret"></span> </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Create and stay on Proposal</a></li>
                                            <li><a href="budget-start.php">Create and go to Budget</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- /Modal -->
                    <!-- BUDGET VERSIONS -->
                    <table id="u569ish_line0" class="table table-condensed table-bordered" role="presentation">
                        <tbody>
                            <tr>
                                <th scope="row" style="width:20px">&nbsp;</th>
                                <th scope="row">Name</th>
                                <th class="uif-gridLayoutCell">Direct Cost</th>
                                <th class="uif-gridLayoutCell">F&amp;A</th>
                                <th class="uif-gridLayoutCell">Total</th>
                                <th class="uif-gridLayoutCell">Status</th>
                                <th class="uif-gridLayoutCell">Actions</th>
                            </tr>
                            <tr>
                                <td scope="row"><input type="radio" value="radio" name="version-list" id="radio-version-1">
                                    <label for="radio"></label></td>
                                <td scope="row"><a href="" data-toggle="modal" data-target="#switchdoc"  class="versions">Version 1</a> <small>(for submission)</small></td>
                                <td class="">  22,835.00</td>
                                <td class="">  6,170.00</td>
                                <td class="">29,005.00</td>
                                <td class="">Complete</td>
                                <td class=""><!-- Single button -->
                                    
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Action <span class="caret"></span> </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span aria-hidden="true" class="icon-eye-open"></span> View Summary</a> </li>
                                            <li><a href=""  data-toggle="modal" data-target="#copyNew" class=""><span aria-hidden="true" class="icon-copy"></span> Copy</a></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-print"></span> Print</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-ok"></span> Submit with Proposal</a></li>
                                        </ul>
                                    </div></td>
                            </tr>
                            <tr>
                                <td scope="row"  class="success"><input type="radio"  value="radio" name="version-list" id="radio-version-2" checked>
                                    <label for="radio"></label></td>
                                <td class="showMe" scope="row"><a href="" data-toggle="modal" data-target="#switchdoc"   class="versions">Version 2</a> <small>(for submission)</small></td>
                                <td class="">  22,835.00</td>
                                <td class="">  6,170.00</td>
                                <td class="">29,005.00</td>
                                <td class="">Complete</td>
                                <td class=""><!-- Single button -->
                                    
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Action <span class="caret"></span></button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span aria-hidden="true" class="icon-eye-open"></span> View Summary</a></li>
                                            <li><a href=""  data-toggle="modal" data-target="#copyNew" class=""><span aria-hidden="true" class="icon-copy"></span> Copy</a></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-print"></span> Print</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-ok"></span> Submit with Proposal</a></li>
                                        </ul>
                                    </div></td>
                            </tr>
                            <tr>
                                <td scope="row"><input type="radio" value="radio" name="version-list" id="radio-version-3">
                                    <label for="radio"></label></td>
                                <td scope="row"><a href="" data-toggle="modal" data-target="#switchdoc"   class="versions">Version 3</a> <small>(for submission)</small></td>
                                <td class="">  22,835.00</td>
                                <td class="">  6,170.00</td>
                                <td class="">29,005.00</td>
                                <td class="">Incomplete</td>
                                <td class=""><!-- Single button -->
                                    
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Action <span class="caret"></span></button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span aria-hidden="true" class="icon-eye-open"></span> View Summary</a></li>
                                            <li><a href=""  data-toggle="modal" data-target="#copyNew" class=""><span aria-hidden="true" class="icon-copy"></span> Copy</a></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-print"></span> Print</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-ok"></span> Submit with Proposal</a></li>
                                        </ul>
                                    </div></td>
                            </tr>
                            <tr>
                                <td scope="row"><input type="radio" value="radio" name="version-list" id="radio-version-4">
                                    <label for="radio"></label></td>
                                <td scope="row"><a href="" data-toggle="modal" data-target="#switchdoc"  class="versions">Version 4</a> <small>(for submission)</small></td>
                                <td class="">  22,835.00</td>
                                <td class="">  6,170.00</td>
                                <td class="">29,005.00</td>
                                <td class="">Complete</td>
                                <td class=""><!-- Single button -->
                                    
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Action <span class="caret"></span></button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span aria-hidden="true" class="icon-eye-open"></span> View Summary</a></li>
                                            <li><a href=""  data-toggle="modal" data-target="#copyNew" class=""><span aria-hidden="true" class="icon-copy"></span> Copy</a></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-print"></span> Print</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><span aria-hidden="true" class="icon-ok"></span> Submit with Proposal</a></li>
                                        </ul>
                                    </div></td>
                            </tr>
                            <tr>
                                <td scope="row"><input type="radio"  value="radio" name="version-list" id="radio-version-5">
                                    <label for="radio"></label></td>
                                <td scope="row" colspan="6" class="versions">NONE <small>(for submission)</small></td>
                                    </td>
                            </tr>
                        </tbody>
                    </table>
                        <!-- //BUDGET VERSIONS -->
                    <!-- Modal -->
                    <div class="modal fade" id="copyNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Copy this Budget Version</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-3 control-label">Parent Proposal</label>
                                            <div class="col-sm-9">
                                                <p class="form-control-static">#23533 </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-3 control-label">Budget Name</label>
                                            <div class="col-sm-9">
                                                <input type=" text" class="form-control" id="" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <button type="button" class="btn btn-primary " data-toggle=""> Copy </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- /Modal --> 
                    
                    <!-- Modal -->
                    <div class="modal fade" id="openBudget" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Open Budget Version</h4>
                                </div>
                                <div class="modal-body"> You are about to leave this document to work on a budget document. </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <a href="budget-periods.php" class="btn btn-primary " data-toggle=""> Open Budget </a> </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- /Modal --> 
                    
                </section>
                
                <!-- // radio --> 
                
                <!-- Modal -->
                <div class="modal fade" id="copyNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Copy this Budget Version</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-3 control-label">Parent Proposal</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">#23533 </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword3" class="col-sm-3 control-label">Budget Name</label>
                                        <div class="col-sm-9">
                                            <input type=" text" class="form-control" id="" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <button type="button" class="btn btn-primary " data-toggle=""> Copy </button>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- /Modal --> 
                
                <!-- Modal -->
                <div class="modal fade" id="openBudget" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Open Budget Version</h4>
                            </div>
                            <div class="modal-body"> You are about to leave this document to work on a budget document. </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <a href="budget-periods.php" class="btn btn-primary " data-toggle=""> Open Budget </a> </div>
                        </div>
                    </div>
                </div>
                
                <!-- /Modal -->
                
                </section>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
            <button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}"> Save and Continue </button>
        </div>
        <!-- DIALOGS/Placeholders -->
        <div id="Uif-Dialogs"> </div>
    </div>
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
    </span> <span id="formComplete"></span>
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

<!-- scripts --> 

<script type="text/javascript" src="../../plugins/jquery/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="../../plugins/validate/jquery.validate.js"></script> 
<script type="text/javascript" src="../../plugins/jqueryUI/jquery-ui-1.9.2.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/jquery.dataTables.js"></script> 
<script type="text/javascript" src="../../themes/bootstrap/scripts/bootstrap.js"></script> 
<script type="text/javascript" src="../../plugins/scrollto/jquery.scrollTo-1.4.6.js"></script> 
<script type="text/javascript" src="../../plugins/jqform/jquery.form-3.31.0.js"></script> 
<script type="text/javascript" src="../../plugins/globalize/globalize.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.navigationMenu.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.tabMenu.js"></script> 

<!--

<script type="text/javascript" src="../../plugins/jgrowl/jquery.jgrowl.js"></script>
--> 
<script type="text/javascript" src="../../plugins/textpopout/krad.textareaPopout.js"></script> 
<script type="text/javascript" src="../../plugins/validate/additional_validations.js"></script> 
<script type="text/javascript" src="../../plugins/cookie/jquery.cookie.js"></script> 
<script type="text/javascript" src="../../plugins/watermark/jquery.watermark.js"></script> 
<script type="text/javascript" src="../../plugins/countdown/jquery.countdown.js"></script> 
<script type="text/javascript" src="../../plugins/blockUI/jquery.blockUI.js"></script> 
<script type="text/javascript" src="../../plugins/color/jquery.color.js"></script> 
<script type="text/javascript" src="../../plugins/json/jquery.json-2.2.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/jquery.dataTables.rowGrouping.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/TableTools.js"></script> 
<script type="text/javascript" src="../../plugins/datatables/ZeroClipboard.js"></script> 
<!-- <script type="text/javascript" src="../../plugins/jstree/jquery.jstree.js"></script>
<script type="text/javascript" src="../../plugins/jqueryUI/jquery.ui.autocomplete.html.js"></script> --- --> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.js"></script> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.pack.js"></script> 
<script type="text/javascript" src="../../plugins/prettify/prettify.js"></script> 
<script type="text/javascript" src="../../plugins/easydrag/jquery.easydrag.js"></script> 
<script type="text/javascript" src="../../plugins/tooltip/jquery.bubblepopup.v2.3.1.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.variables.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.utility.js"></script><!--
<script type="text/javascript" src="../../krad/scripts/krad.initialize.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.request.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.response.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.actions.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.dirty.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.lookup.js"></script>
<script type="text/javascript" src="../../krad/scripts/krad.message.js"></script>--> 
<script type="text/javascript" src="../../krad/scripts/krad.session.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.url.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.validate.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.widget.js"></script> 
<script type="text/javascript" src="../../plugins/easing/jquery.easing-1.3.pack.js"></script> 
<script type="text/javascript" src="../../themes/kboot/scripts/less-1.5.0.min.js"></script> 
<script type="text/javascript" src="../scripts/kradSampleApp.js"></script> 
<script type="text/javascript">
    //    setGrowlDefaults({position:"top-right",sticky:false,life:7000});setBlockUIDefaults({fadeIn:400,fadeOut:800,centerX:true,centerY:true,blockingImage:"../krad/images/loading.gif"}, 'navigation');setBlockUIDefaults({fadeIn:400,fadeOut:800,centerX:true,centerY:true,blockingImage:"../krad/images/loader.gif"}, 'refresh');setConfigParam("kradImageLocation","../krad/images/");setConfigParam("kradUrl","../kr-krad");setConfigParam("applicationUrl","http://localhost:8080/krad-dev");setConfigParam("scriptCleanup","true");
</script> 
<script type="text/javascript" src="../../themes/kboot/scripts/global.functions.js"></script>
<style type="text/css">
 #u569ish_line0  td:nth-child(2){min-width:160px;}
 small{display:none;}
    .showMe small { display:inline;}
</style>
<script>


(function($){
    var $radioButtons = $('#u569ish_line0  input[type="radio"]');
    $radioButtons.click(function() {
        $radioButtons.each(function() {
            $(this).parent().toggleClass("success", this.checked);
        });
       $("#u569ish_line0  td").removeClass("showMe");
       $(".success").next().addClass("showMe");


    });
}(jQuery))


</script> 

<!-- // end scripts --> 


   

<!-- Modal -->
<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Open Version</h4>
            </div>
            <div class="modal-body">
                <p>You are about to open a budget document attached to this proposal document. Are you sure you want to do this?</p>
                <small>
                <label style="font-weight:normal">
                    <input type="checkbox">
                    Don't ask me this again </label>
                </small> </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="budget-ng-summary.php" class="btn btn-primary">Open Budget Document</a> </div>
        </div>
    </div>
</div>

<!-- end Modal --> 

<!-- Modal -->
<div class="modal fade" id="summary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Budget Version 1 Summary</h4>
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
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
 
 

</body>
</html>