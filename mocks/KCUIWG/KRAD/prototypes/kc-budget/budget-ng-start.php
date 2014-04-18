 <!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="robots" content="noindex,nofollow" />
	<title>Kuali :: Fluid Application Header</title>

<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
	<style>
 label { padding:0 5px;}
.topMargin1em{margin-top:1em;}


.shake {
	animation-name: shake;
	-webkit-animation-name: shake;
	animation-duration: .99s;
	-webkit-animation-duration: .99s;
	-webkit-animation-timing-function: ease-in;
	-webkit-animation-timing-function: ease-out;
	-webkit-animation-iteration-count: 1s;
	-webkit-animation-timing-function: linear;
	visibility: visible !important; color:red;;
	
}
 @keyframes shake {
 0%, 100% {
	 
-webkit-transform:translate(2px, 1px)   rotate(0deg); 
-ms-transform:translateX(0);
transform:translateX(0)
}
10%, 30%, 50%, 70%, 90% {
-webkit-transform:translate(2px,-11px)   rotate(0deg); 
-ms-transform:translateX(-10px);
transform:translateX(-10px)
}
20%, 40%, 60%, 80% {
-webkit-transform:translate(2px,11px)   rotate(0deg);
-ms-transform:translateX(10px);
transform:translateX(10px)
}



	</style>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
<header id="u1xj79g4" class="uif-applicationHeader">
	<div class="container">
		<nav id="u1osy4lo" class="navbar" role="navigation"> 
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span> </button> <a class="navbar-brand" href="index.php"> 
		<div class="logoBrand">
			<h1>
				<img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"> 
			</h1>
		</div>
		</a> 
	</div>
	<div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
		<ul class="nav navbar-nav navbar-right uif-listLayout">
			<li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Researcher </a> 
			<div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
				<div class="row ">
					<div class="col-md-12">
						<section id="u9tj3ss" class="uif-listGroup">
							<h3 class="uif-headerText">
								Proposal Development
							</h3>
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
<?php include ('includes/uif-viewHeader.php') ?>
<!-- // VIEW HEADER  -->
<!-- VIEW CONTENT -->
		<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
<!-- VIEW NAVIGATION -->
<?php include ('includes/uif-navigation-budget.php') ?>
			<div id="Uif-BreadcrumbUpdate" style="display:;">
			</div>
			<main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;"> 
			<header class="clearfix uif-header-contentWrapper pull-left">
				<div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
					<h2 class="uif-headerText">
						<span class="uif-headerText-span">Detailed or Summary Budget</span> 
					</h2>
				</div>
			</header>

<!--    
                <div class="pull-right"> <a href="#"> <span class="icon-compass"></span> Guide Me</a></div>
      =          -->
<!-- -->
			<div class="uif-verticalBoxGroup uif-header-lowerGroup">
				<div class="uif-boxLayoutVerticalItem clearfix">
					<p>
						<b>Would you like to create a detailed budget or enter a summary only?</b>
					</p>
				</div>
			</div>
			</header>
			<div class="uif-cssGridGroup uif-boxLayoutVerticalItem clearfix well active">
				<!-- <div class="row">
					<div class="col-md-3">
						<a class="btn btn-default" id="start-detail-budget" href="#">Start a detailed budget</a> 
					</div>
					<div class="col-md-3">
						<a class="btn btn-default" id="start-summary-budget" href="#">Start a summary-only budget</a> 
					</div>
				</div> -->
				
				<div class="row">
				<p><label for="radio-start-detail-budget"><input type="radio" name="radio-start-budget" value="detail" id="radio-start-detail-budget"> Start a detailed budget</label></p>
			<p><label for="radio-start-summary-budget"><input type="radio" name="radio-start-budget" value="summary" id="radio-start-summary-budget"> Start a summary budget</label></p>
				
				</div>
				
				
				<div class="uif-boxLayoutVerticalItem ">
					<p class="topMargin1em">
						Will this be a modular budget? 
						<label> <input type="radio" name="radio-modular-budget" value="modular" id="radio-modular-budget_yes">
						Yes</label>
						 <label> <input type="radio" name="radio-modular-budget" value="periods" id="radio-modular-budget_no">
						No</label> 
					</p>
				</div>
			</div>

<!-- -->
			</main> 
		</div>

<!-- VIEW FOOTER -->
		<div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
		
			<!-- <a href="budget-ng-summary2.php" id="ufuknm4" class="btn btn-default uif-primaryActionButton uif-boxLayoutHorizontalItem">Go back</a> -->
			<a id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem">Save</button> 
			<a href="budget-ng-rates.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Save and Continue</a> 
		</div>

<!-- DIALOGS/Placeholders -->
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
	</span> 
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
					<td class="jquerybubblepopup-innerHtml">
					<div class="uif-clientMessageItems uif-clientErrorDiv">
						<ul>
							<li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
						</ul>
					</div>
					</td>
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
<script>

//replacing the "version 5"

		(function($){
  			/* $('.uif-headerText-span:contains(": Version 5")').html(function(i, h) {
 			 return h.replace(/: Version 5/g, '');
				});
*/        

			
	 $("#ufuknm4").click(function (e) {	
	  e.preventDefault();		
	
	/*
	
If a user selects summary budget and modular = yes, then:
User should land on the periods and totals page.
Upon save and continue, if cost sharing and or unrecovered F&A has been entered, user should be taken to those pages to enter data
--> Upon save and continue, user should be taken to the modular page to review the pre-synced information and adjust as needed.
Upon save and continue, user should be taken to the summary page to review

	*/
	
	
		if ($("#radio-start-summary-budget").is(":checked") & $("#radio-modular-budget_yes").is(":checked") ){
			document.location.href='budget-ng-periods.php?modular-budget=yes&amp;summary=yes';
			}
			
			
			
/*
If a user selects summary budget and modular = no, then:
	User should land on the periods and totals page.
	Upon save and continue, if cost sharing and or unrecovered F&A has been entered, user should be taken to those pages to enter data. Upon save and continue, user should be taken to the summary page to review.
	If no cost sharing or unrecovered F&A has been entered, user should be taken directly to the summary page to review.
		*/
		
		
		
		else if ($("#radio-start-summary-budget").is(":checked") & $("#radio-modular-budget_no").is(":checked") ){
			document.location.href='budget-ng-periods.php?modular-budget=no&amp;summary=yes';
			}
		
		
		
		
		
		
		
		//NO modular 
		/*
		
	If a user selects detailed budget and modular = no, then:
User should land on the Project Personnel page. 

Upon save and continue, user is taken to the Assign Personnel to Periods page. 
Note: if user attempts to navigate to a period other than period 1, the following warning will display: “You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue

Upon save and continue, user is taken to the Non-Personnel Page to enter data.  Note: if user attempts to navigate to a period other than period 1, the following warning will display: “You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue?”
Upon save and continue, the following message should display: “Would you like to use the generate all periods functionality to auto-calculate later periods based on period 1 data? Please note that this action can only be taken once.”

If yes, periods are generated. Upon save and continue, user is taken to subawards tab to continue with any additional budget data entry.
If no, out year data is not generated. Upon save and continue, user is taken to subawards tab to continue with any additional budget data entry.
Note that screens such as cost-sharing, unrecovered F&A, and modular will display messages indicating that no data entry is required, if that is the case.

			
			*/
		
		
		
		
		
			else if ($("#radio-start-detail-budget").is(":checked") & $("#radio-modular-budget_no").is(":checked") ){
			document.location.href='budget-ng-personnelCosts-projPersonnel1.php?modular-budget=no&amp;detail=yes';
			}
		
			
	
		
		// YES MODULAR
		
		/*
		If a user selects detailed budget and modular = yes, then same process as above, but when user arrives at modular screen, values will be populated based on detailed budget entry (“pre-synced”).
If user returns to a budget version in progress, the landing page will differ based on whether the user had indicated at the time of version creation whether he was doing detailed or summary budget. If detailed, user will land on the summary screen and can navigate around from there. If summary budget, user will land on the periods and totals screen.

		
		*/
			
		
		else if ($("#radio-start-detail-budget").is(":checked") & $("#radio-modular-budget_yes").is(":checked") ){
			document.location.href='budget-ng-personnelCosts-projPersonnel1.php?modular-budget=yes&amp;detail=yes';
			}
		
	
		
		
		
		else{
			
			
				$(".topMargin1em").addClass("shake").one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
				$(this).removeClass("shake");
				});
			
			}
		
		
		
		
		
  });

$(".tabWarning").click(function(e){

  alert("[MODAL WINDOW] --- You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue? {{ CANCEL (button)  | CONTINUE (button) }} ---[MODAL WINDOW]");
return false;

});
}(jQuery))

</script>
<!-- MODAL budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL- budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>
<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
</body>
</html>
