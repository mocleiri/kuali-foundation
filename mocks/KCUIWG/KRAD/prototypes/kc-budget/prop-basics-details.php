<?php
$page = 'basics';
$section = 'basics-proposalDetails';
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
                    <p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> Proposal Development </p>
                    <span class="uif-headerText-span"> Proposal: #23533 </span> <span class="uif-supportTitle-wrapper">
                    <div id="u1hgnm9q" class="uif-viewHeader-supportTitle" data-parent="ueqbqhn"> PI:&nbsp; Edward Haskell</div>
                    </span> </h1>
                <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent="LabsProposal">
                    <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                        <label id="uj8x9wj" for="uauh5yk_span" class="uif-label" data-label_for="u1f206jn"> Doc Nbr: </label>
                        <p id="uauh5yk" class="uif-message"> 2743 </p>
                    </div>
                    <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Initiator">
                        <label id="ujre4xu" for="u7lh763_span" class="uif-label" data-label_for="u1f206ki"> Initiator: </label>
                        <p id="u7lh763" class="uif-message"> thrclark </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Status: </label>
                        <p id="u4ch8dm" class="uif-message"> In Progress </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Budget: </label>
                        <p id="u4ch8dm" class="uif-message"> <a href="" data-toggle="modal" data-target="#switchdoc" class="">Version 2</a></p>
                    </div>
                    <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
                        <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                            <h4 class="uif-headerText"> Document Info </h4>
                            <table id="u98wduy" class="table table-condensed uif-table-fixed" role="presentation">
                                <tbody>
                                    <tr>
                                        <th scope="row"> <label id="u1v1pkxy" for="u1hmj9zj_span" class="uif-label displayWith-u1qpse12" data-label_for="u1qpse12"> Doc Nbr </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse12" class="uif-messageField" data-label="Doc Nbr">
                                                <p id="u1hmj9zj" class="uif-message"> 2743 </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1vk6fz9" for="u91iqlm" class="uif-label displayWith-upf4ga4" data-label_for="upf4ga4"> Initiator </label></th>
                                        <td class="uif-gridLayoutCell"><div id="upf4ga4" class="uif-linkField" data-label="Initiator"> <a id="u91iqlm" href="#" target="_self" class="uif-link">thrclark</a> </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1w2nb0k" for="u1b4jcel_span" class="uif-label displayWith-u1qpse2s" data-label_for="u1qpse2s"> Status </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse2s" class="uif-messageField" data-label="Status">
                                                <p id="u1b4jcel" class="uif-message"> In Progress </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1wl461v" for="u17vjdm4_span" class="uif-label displayWith-u1qpse3n" data-label_for="u1qpse3n"> PI </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse3n" class="uif-messageField" data-label="PI">
                                                <p id="u17vjdm4" class="uif-message"> Ken Graves </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1x3l136" for="u14mjetn_span" class="uif-label displayWith-u1qpse4i" data-label_for="u1qpse4i"> Created </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse4i" class="uif-messageField" data-label="Created">
                                                <p id="u14mjetn" class="uif-message"> 04:27pm 07/09/2013 </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1xm1w4h" for="u11djg16_span" class="uif-label displayWith-u1qpse5d" data-label_for="u1qpse5d"> Updated </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse5d" class="uif-messageField" data-label="Updated">
                                                <p id="u11djg16" class="uif-message"> 12:22pm 07/12/2013 </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1y4ir5s" for="uy4jh8p_span" class="uif-label displayWith-u1qpse68" data-label_for="u1qpse68"> Proposal Nbr </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse68" class="uif-messageField" data-label="Proposal Nbr">
                                                <p id="uy4jh8p" class="uif-message"> #23533 </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1ymzm73" for="uuvjig8_span" class="uif-label displayWith-u1qpse73" data-label_for="u1qpse73"> Sponsor Name </label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse73" class="uif-messageField" data-label="Sponsor Name">
                                                <p id="uuvjig8" class="uif-message"> NIH </p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"> <label id="u1ymzm73" for="uuvjig8_span" class="uif-label displayWith-u1qpse73" data-label_for="u1qpse73"> Budget Versions</label></th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse73" class="uif-messageField" data-label="Sponsor Name">
                                                <ul id="ud9tgcp_control" class="uif-optionList" data-role="Control" data-control_for="ud9tgcp">
                                                    <li class="uif-optionList-item"> <span data-key="1"><a href="#">Version 1</a></span> </li>
                                                    <li class="uif-optionList-item"> <span data-key="2"><a href="#"><strong>Version 2</strong></a></span> </li>
                                                    <li class="uif-optionList-item"> <span data-key="3"><a href="#">Version 3</a></span> </li>
                                                    <li class="uif-optionList-item"> <span data-key="3"><a href="#">Version 4</a></span> </li>
                                                </ul>
                                            </div></td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                    </div>
                </div>
            </div>
            <div id="LabsProposal-DocActionBar" class="uif-actionBar uif-header-lowerGroup">
                <ul>
                    <li> <a id="uotglmx" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;toggleAuditMode&quot;}"> Data Validation <span style="color:#090">(on)</span> </a> </li>
                    <li> <a id="uotglns" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;print&quot;}"><span class="icon-print"></span>Print</a> </li>
                    <li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy</a> </li>
                    <li> <a id="uotglpi" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;medusa&quot;}"><span class="icon-tasks"></span>Medusa</a> </li>
                    <li> <a id="uotglqd" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;showHierarchy&quot;}"><span class="icon-list-ol"></span>Hierarchy</a> </li>
                    <li> <a id="uotglr8" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;access&quot;}"><span class="icon-lock"></span>Access</a> </li>
                </ul>
            </div>
        </header>
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-proposal.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span"> Proposal Details </span> </h2>
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
                            <div id="u11k8c13" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Type">
                                <input id="u11k8c13_control" type="text" name="field1" value="" size="30" class="form-control input-sm uif-textControl required error" data-role="Control" data-control_for="u11k8c13" aria-required="true" aria-invalid="true">
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
                            <label id="u11k8c1y_label" for="u11k8c1y_control" class="uif-label displayWith-u11k8c1y" data-label_for="u11k8c1y"> Project Title: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c1y" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Project Title">
                                <textarea id="u11k8c1y_control" name="field2" rows="3" cols="40" class="form-control input-sm uif-mediumTextAreaControl has-helper required" data-role="Control" data-control_for="u11k8c1y" aria-required="true"></textarea>
                                <div class="uif-helperText" style="overflow: hidden; display: none;"> Give this proposal a title. Be detailed but concise. </div>
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
                            <label id="u11k8c3o_label" for="u11k8c3o_control" class="uif-label displayWith-u11k8c3o" data-label_for="u11k8c3o"> Proposal Indicator: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c3o" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Proposal Indicator">
                                <input type="hidden" name="_bField1" value="on">
                                <input type="checkbox" id="u11k8c3o_control" name="bField1" class="uif-checkboxControl" data-role="Control" data-control_for="u11k8c3o">
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c4j_label" for="u11k8c4j_control" class="uif-label displayWith-u11k8c4j" data-label_for="u11k8c4j"> Award ID: </label>
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
                        <div class="col-md-3 uif-cssGridLabelCol">
                            <label id="u11k8c5e_label" for="u11k8c5e_control" class="uif-label displayWith-u11k8c5e" data-label_for="u11k8c5e"> Keywords: </label>
                        </div>
                        <div class="col-md-9">
                            <div id="u11k8c5e" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Keywords">
                                <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl" data-role="Control" data-control_for="u11k8c5e">
                                    <option value="B">Biology</option>
                                    <option value="C">Chemistry</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
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
<?php include ('includes/footer-scripts.php') ?>

<!-- Modal -->
<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Open Budget</h4>
            </div>
            <div class="modal-body">
                <p>You are about to open a budget document attached to this proposal document. Are you sure you want to do this?</p>
                <small>
                <label style="font-weight:normal">
                    <input type="checkbox">
                    Don't ask me this again </label>
                </small> </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="budget-periods.php" class="btn btn-primary">Open Budget Document</a> </div>
        </div>
    </div>
</div>

<!-- end Modal -->

</body>
</html>