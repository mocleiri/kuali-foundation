<?php
$section = 'asdf';
$page = 'asdf';
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
                                                <li> <a href="prop-start.php" class="uif-actionLink"> Create a Proposal </a> </li>
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
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span">Create Proposal</span> </h2>
                    </div>
                    <div id="uw4ggjs" class="uif-verticalBoxGroup uif-header-lowerGroup" data-parent="LabsProposal-Page">
                        <div id="u1ndzhxa" class="text-muted uif-boxLayoutVerticalItem clearfix" data-parent="uw4ggjs">
                            <p>* Indicates required fields</p>
                        </div>
                    </div>
                </header>
                <div id="u14jg6xp" class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix" data-parent="LabsProposal-Page">
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Proposal Type: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField uif-hasError" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <select name="document.developmentProposalList[0].proposalTypeCode" tabindex="2" onchange="" onblur="" id="document.developmentProposalList[0].proposalTypeCode" style="" class="form-control input-sm uif-dropdownControl" title="* Proposal Type">
                                    <option value="">select</option>
                                    <option value="4">Continuation</option>
                                    <option value="1">New</option>
                                    <option value="3">Renewal</option>
                                    <option value="2">Resubmission</option>
                                    <option value="5">Revision</option>
                                    <option value="6">Task Order</option>
                                </select>
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Lead Unit: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField uif-hasError" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <select name="document.developmentProposalList[0].proposalTypeCode" tabindex="2" onchange="" onblur="" id="document.developmentProposalList[0].proposalTypeCode" style="" class="form-control input-sm uif-dropdownControl" title="* Proposal Type">
                                    <option value="">select</option>
                                    <option value="000001">000001 - University</option>
                                    <option value="BL-BL">BL-BL - BLOOMINGTON CAMPUS</option>
                                    <option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY</option>
                                    <option value="BL-RCEN">BL-RCEN - RESEARCH CENTERS</option>
                                    <option value="BL-RUGS">BL-RUGS - OFFICE OF VP FOR RESEARCH</option>
                                    <option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
                                    <option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
                                    <option value="IN-IN">IN-IN - IND UNIV-PURDUE UNIV INDPLS</option>
                                    <option value="IN-MDEP">IN-MDEP - MEDICINE DEPT</option>
                                    <option value="IN-MED">IN-MED - SCHOOL OF MEDICINE</option>
                                    <option value="IN-PED">IN-PED - PEDIATRICS</option>
                                    <option value="IN-PERS">IN-PERS - PED-EMERGENCY ROOM SERVICES</option>
                                    <option value="IU-UNIV">IU-UNIV - UNIVERSITY LEVEL</option>
                                </select>
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c13_label" for="u11k8c13_control" class="uif-label displayWith-u11k8c13" data-label_for="u11k8c13"> Activity Type: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c13" class="uif-inputField uif-hasError" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <select name="document.developmentProposalList[0].proposalTypeCode" tabindex="2" onchange="" onblur="" id="document.developmentProposalList[0].proposalTypeCode" style="" class="form-control input-sm uif-dropdownControl" title="* Proposal Type">
                                    <option value="">select</option>
                                    <option value="4">Clinical Trial</option>
                                    <option value="9">Construction</option>
                                    <option value="7">Fellowship - Post-Doctoral</option>
                                    <option value="6">Fellowship - Pre-Doctoral</option>
                                    <option value="2">Instruction</option>
                                    <option value="3">Public Service</option>
                                    <option value="1">Research</option>
                                    <option value="8">Student Services</option>
                                    <option value="5">other</option>
                                </select>
                                <div id="u11k8c13_errors" class="uif-validationMessages" data-messages_for="u11k8c13" style="display: none;">
                                    <div class="uif-clientMessageItems uif-clientErrorDiv">
                                        <ul>
                                            <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u1javsi1_label" for="u1javsi1_fieldset" class="uif-label displayWith-u1javsi1" data-label_for="u1javsi1"> Project Dates: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u1javsi1" class="uif-fieldGroup" data-parent="u14jg6xp" data-label="Project Dates" data-group="u18wn9hi">
                                <fieldset aria-labelledby="u1javsi1_label" id="u1javsi1_fieldset">
                                    <legend style="display: none">Project Dates</legend>
                                    <div id="u18wn9hi" class="uif-cssGridGroup" data-parent="u1javsi1">
                                        <div class="row ">
                                            <div class="col-md-6">
                                                <div id="uec5xzy" class="uif-inputField" data-parent="u18wn9hi" data-role="InputField">
                                                    <div class="input-group">
                                                        <input id="uec5xzy_control" type="text" name="field4" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="uec5xzy" placeholder="mm/dd/yyyy ">
                                                        <span class="input-group-msg">
                                                        <p id="u21vmor" class="uif-message"> to </p>
                                                        </span> </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div id="uec5y0t" class="uif-inputField" data-parent="u18wn9hi" data-role="InputField">
                                                    <input id="uec5y0t_control" type="text" name="field5" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="uec5y0t" placeholder="mm/dd/yyyy ">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c1y_label" for="u11k8c1y_control" class="uif-label displayWith-u11k8c1y" data-label_for="u11k8c1y"> Project Title: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c1y" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Project Title">
                                <textarea id="u11k8c1y_control" name="field2" rows="3" cols="40" class="form-control input-sm uif-mediumTextAreaControl has-helper required error" data-role="Control" data-control_for="u11k8c1y" aria-required="true" aria-invalid="true"></textarea>
                                <div class="uif-helperText" style="overflow: hidden; display: none;"> Give this proposal a title. Be detailed but concise. </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c4j_label" for="u11k8c4j_control" class="uif-label displayWith-u11k8c4j" data-label_for="u11k8c4j">Sponsor: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="" size="30" class="form-control input-sm uif-textControl has-helper" data-role="Control" data-control_for="u11k8c4j">
                                    <div class="uif-helperText" style="overflow: hidden; display: none;"> Enter the award ID for this proposal. </div>
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-search" tabindex="0" data-onclick="createLightBoxPost(&quot;u11k8c4j_quickfinder_act&quot;,{autoSize:true,openEffect:&quot;fade&quot;,closeEffect:&quot;fade&quot;,openSpeed:200,closeSpeed:200,helpers:{overlay:{css:{cursor:'arrow'},closeClick:false}},type:&quot;iframe&quot;},true);" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act" data-submit_data="{&quot;actionParameters[conversionFields]&quot;:&quot;number:field3&quot;,&quot;actionParameters[viewName]&quot;:&quot;LabsProposal-Lookup&quot;,&quot;methodToCall&quot;:&quot;performLookup&quot;,&quot;actionParameters[quickfinderId]&quot;:&quot;u11k8c4j_quickfinder&quot;,&quot;actionParameters[multipleValuesSelect]&quot;:&quot;false&quot;,&quot;actionParameters[hideCriteriaOnSearch]&quot;:&quot;true&quot;,&quot;actionParameters[dataObjectClassName]&quot;:&quot;org.kuali.rice.krad.demo.travel.dataobject.TravelAccount&quot;,&quot;actionParameters[baseLookupUrl]&quot;:&quot;../kr-krad/lookup&quot;}"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-9">
                            <div id="u11k8c5e" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Keywords"> </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
            <a id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" href="prop-basics-details.php"> Save and Continue </a> </div>
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
</body>
</html>