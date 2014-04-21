<?php
$section = '';
$page = '';
?>

<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
</head>

<body id="Uif-Application" style="padding-bottom: 48px;">
<!-- APPLICATION HEADER -->




 <?php include ('includes/uif-applicationHeader.php') ?>
 
 
 
 
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;">
        <!-- BREADCRUMBS -->
        <!-- VIEW HEADER -->

        <header class="container uif-viewHeader-contentWrapper">
            <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal" style="
    padding-top: 30px;
">
                <h1 class="uif-headerText"> <span class="uif-headerText-span">Your Dashboard</span> <span class="uif-supportTitle-wrapper"> </span> </h1>
            </div>
        </header>

        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
            <!-- VIEW NAVIGATION -->

            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
        </div>
        <!-- VIEW FOOTER -->

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
    
</form>

<?php include ('includes/footer-scripts.php') ?>
<div id="jstree-marker" style="display: none;">»</div>
<div id="jstree-marker-line" style="display: none;"></div>
<div id="vakata-contextmenu"></div>

<div id="jstree-marker" style="display: none;">»</div>
<div id="jstree-marker-line" style="display: none;"></div>
<div id="vakata-contextmenu"></div>
<div id="jGrowl" class="top-right jGrowl">
    <div class="jGrowl-notification"></div>
</div>

<div id="jstree-marker" style="display: none;">»</div><div id="jstree-marker-line" style="display: none;"></div><div id="vakata-contextmenu"></div></body>
</html>