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
                        <div class="col-md-12">
                            <p>This area contains a consolidated list of justification notes that have been added to items in your budget. The last user to add a justification
                                is listed above the text box. To bring in the latest updates, click <strong>Consolidate Expense Justifications</strong>.</p>
                        </div> 
                    </div>
                    <div class="row">                        
                        <div class="col-md-12 clearfix">
                            <div class="col-md-3 pull-right text-right"><strong>Last updated:</strong> 01/22/2014</div>                   
                            <div class="col-md-3 pull-right text-right"><strong>Updated by:</strong>  mdiaz  </div>
                            <label for="budget-notes-area" class=""><strong>Notes</strong></label><br>
                                <textarea id="budget-notes-area"  class="form-control input">This microscope is essential to performing the research.</textarea> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-default btn-xs pull-right">Consolidate Expense Justifications</button>
                        </div>
                    </div>               
                </section>
            </main>
        </div> 
<!-- VIEW FOOTER --> <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;"> <div class="global-actions"> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save </a> <a id="" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Reload </a> <a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Complete Budget </a> </div>
           <div class="global-navigate"> 
            <a href="budget-ng-modular.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem"><span class="icon-chevron-left"></span> Back</a>
            <a href="budget-ng-summary.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Continute <span class="icon-chevron-right"></span></a></div>
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