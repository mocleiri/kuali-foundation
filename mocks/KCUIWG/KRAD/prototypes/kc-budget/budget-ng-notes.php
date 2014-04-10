<?php
$page = 'notes';
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

#budget-notes-area{width:100%; min-height:355px; margin-bottom: 1em;;}
        .data-row-table th{ padding:5px; text-align:right; }
    </style>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky=true class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader"> 
<div class="container">
	<nav id="u1osy4lo" class="navbar" role="navigation"> 
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span></button> <a class="navbar-brand" href="index.php"> 
	<div class="logoBrand">
		<h1>
			<img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"> 
		</h1>
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
						<h3 class="uif-headerText">
							Proposal Development
						</h3>
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
						<li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Backdoor Login <span class="caret"></span> </a> 
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
                        <h2 class="uif-headerText"><span class="uif-headerText-span">Budget Notes</span></h2>
                    </div>
                </header>
             <section>
                
                        <div class="row">
                          
                        </div>
                        <div class="row">
                          
                            <div class="col-md-12 clearfix">
                              <div class="col-md-3 pull-right text-right"><strong>Last updated:</strong> 01/22/2014</div>
                      
                            <div class="col-md-3 pull-right text-right"><strong>Updated by:</strong>  quickstart  </div>
                           
                             <label for="budget-notes-area" class=""><strong>Notes</strong></label><br><textarea id="budget-notes-area"  class="form-control input">Praesent laoreet dui nec arcu rhoncus pellentesque. Pellentesque quam nulla, congue in velit et, varius blandit risus. Integer pretium velit velit, et ultrices quam rhoncus sed. Praesent pretium velit a egestas bibendum. Fusce commodo justo quis lacus sollicitudin varius. In in ipsum ut justo tristique tincidunt id quis quam. Vivamus mattis tempus urna a ornare. Vestibulum lectus velit, dapibus quis mauris a, porta luctus ante. In hac habitasse platea dictumst.
                           </textarea> </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <button class="btn btn-default btn-sm pull-right">Consolidate expense justifications</button>
                            </div>
                        </div>
               
                </section>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <a href="budget-ng-modular.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem">Go back</a>
            <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save</button>
            <a href="budget-ng-summary2.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Save and complete budget</a>
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
</body>
</html>