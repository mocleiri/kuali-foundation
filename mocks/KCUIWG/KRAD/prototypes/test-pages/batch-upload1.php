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

<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet"href="../../plugins/batch-file-upload/css/jquery.fileupload.css">
<link rel="stylesheet"href="../../plugins/batch-file-upload/css/jquery.fileupload-ui.css">
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript>
<link rel="stylesheet"href="../../plugins/batch-file-upload/css/jquery.fileupload-noscript.css">
</noscript>
<noscript>
<link rel="stylesheet"href="../../plugins/batch-file-upload/css/jquery.fileupload-ui-noscript.css">
</noscript>
</head><body id="Uif-Application" style="padding-bottom: 33px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky="true" class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span> </button>
                    <a class="navbar-brand" href="index.html">
                    <div class="logoBrand">
                        <h1> <img id="u2elq10" src="../images/logo.png" alt="" class="uif-image"> </h1>
                    </div>
                    </a> </div>
                <div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
                    <ul class="nav navbar-nav navbar-right uif-listLayout">
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Navigate </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
                                <div class="row ">
                                    <div class="col-md-12">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText"> Uif-HeaderText</h3>
                                            <ul class="uif-listLayout">
                                                <li> <a href="prop-start.html" class="uif-actionLink" id="umdwwyj" tabindex="0" data-role="Action"> Navigate </a> </li>
                                                <li> <a href="#" class="uif-actionLink" id="umdwwze" tabindex="0" data-role="Action"> Navigate</a> </li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Navigate </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Navigate </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Navigate </a> </li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Navigate </a> </li>
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
<form id="fileupload" action="http://jquery-file-upload.appspot.com" method="POST" enctype="multipart/form-data">
    
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"> 
        <!-- BREADCRUMBS --> 
        <!-- VIEW HEADER -->
        <header class="container uif-viewHeader-contentWrapper">
            <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal">
                <h1 class="uif-headerText">
                    <p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> &lt;Area Title&gt; </p>
                    <span class="uif-headerText-span"> UIF-Header Text</span> <span class="uif-supportTitle-wrapper">
                    <div id="u1hgnm9q" class="uif-viewHeader-supportTitle" data-parent="ueqbqhn">&lt;Support Title&gt;<em></em> </div>
                    </span> </h1>
                <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent="LabsProposal">
                    <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                        <label id="uj8x9wj" for="uauh5yk_span" class="uif-label" data-label_for="u1f206jn"> DocInfo: </label>
                        <p id="uauh5yk" class="uif-message"> Some Value </p>
                    </div>
                    <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Initiator">
                        <label id="ujre4xu" for="u7lh763_span" class="uif-label" data-label_for="u1f206ki"> DocInfo: </label>
                        <p id="u7lh763" class="uif-message"> Some Value </p>
                    </div>
                    <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                        <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> DocInfo: </label>
                        <p id="u4ch8dm" class="uif-message"> <a href="#">Linked Value </a></p>
                    </div>
                    <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
                        <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                            <h4 class="uif-headerText"> Document Info </h4>
                            <table id="u98wduy" class="table table-condensed uif-table-fixed" role="presentation">
                                <tbody>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse12" class="uif-messageField" data-label="Doc Nbr">
                                                <p id="u1hmj9zj" class="uif-message"> Some Value</p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="upf4ga4" class="uif-linkField" data-label="Initiator"><span class="uif-message">Some Value</span></div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse2s" class="uif-messageField" data-label="Status">
                                                <p id="u1b4jcel" class="uif-message">Some Value</p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse3n" class="uif-messageField" data-label="PI">
                                                <p id="u17vjdm4" class="uif-message"><a href="#">Linked Value</a></p>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse4i" class="uif-messageField" data-label="Created"><span class="uif-message">Some Value</span></div></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">DocInfo</th>
                                        <td class="uif-gridLayoutCell"><div id="u1qpse5d" class="uif-messageField" data-label="Updated">
                                                <p id="u11djg16" class="uif-message">Some Value</p>
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
                    <li> <a id="uotglns" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;print&quot;}"><span class="icon-print"></span>Action</a> </li>
                    <li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Action</a> </li>
                    <li> <a id="uotglpi" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;medusa&quot;}"><span class="icon-tasks"></span>Action</a> </li>
                    <li> <a id="uotglqd" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;showHierarchy&quot;}"><span class="icon-list-ol"></span>Action</a> </li>
                </ul>
            </div>
        </header>
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            <nav id="Uif-Navigation" style="position:absolute; display:">
                <div id="LabsProposal-Menu" class="uif-menuNavigationGroup">
                    <div class="sidebar-collapse"> <span class="icon-angle-left"></span> </div>
                    <!-- NAVIGATION -->
                    <ul class="nav nav-list">
                        <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-file-alt"></span> <span id="u242f54" class="uif-innerText"> Navigate </span> <span class="arrow icon-angle-right"></span> </a>
                            <ul class="submenu uif-listLayout">
                                <li> <a id="ua6f4k" class="uif-actionLink" href="prop-basics-details.html"> Navigate</a> </li>
                                <li> <a id="ua6f5f" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;refresh&quot;}"> Navigate </a> </li>
                                <li> <a id="ua6f6a" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="ua6f75" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="ua6f80" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                            </ul>
                        </li>
                        <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-user3"></span> <span id="u1sp7yfb" class="uif-innerText">Navigate </span> <span class="arrow icon-angle-right"></span> </a>
                            <ul class="submenu uif-listLayout">
                                <li> <a id="u3s0ej9" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="u3s0ek4" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="u3s0ekz" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="u3s0elu" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="u3s0emp" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                            </ul>
                        </li>
                        <li> <a id="u79gehe" class="uif-navigationActionLink" ><span class="icon-ok"></span><span class="uif-innerText">Navigate</span></a> </li>
                        <li> <a id="u79gei9" class="uif-navigationActionLink" ><span class="icon-paper-clip"></span><span class="uif-innerText">Navigate</span></a> </li>
                        <li> <a id="u79gej4" class="uif-navigationActionLink" ><span class="icon-question2"></span><span class="uif-innerText">Navigate</span></a> </li>
                        <li> <a id="u79gej4" class="uif-navigationActionLink" href="prop-budget.html"><span class="icon-money"></span><span class="uif-innerText">Navigate</span></a> </li>
                        <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-bell2"></span> <span id="umhfcyq" class="uif-innerText"> Navigate </span> <span class="arrow icon-angle-right"></span> </a>
                            <ul class="submenu uif-listLayout">
                                <li> <a id="ul96bkq" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                                <li> <a id="ul96bll" class="uif-actionLink" tabindex="0"  data-role="Action"> Navigate </a> </li>
                            </ul>
                        </li>
                        <li> <a id="u79gelp" class="uif-navigationActionLink" ><span class="icon-signout"></span><span class="uif-innerText">Navigate</span></a> </li>
                    </ul>
                </div>
            </nav>
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span"> Batch File Upload</span> </h2>
                    </div>
                </header>
                
                <!-- Redirect browsers with JavaScript disabled to the origin page -->
                <noscript>
                <input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/">
                </noscript>
                <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                <div class="row fileupload-buttonbar">
                    <div class="col-lg-7"> 
                        <!-- The fileinput-button span is used to style the file input field as button --> 
                        <span class="btn btn-primary btn-xs fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
                        <input type="file" name="files[]" multiple>
                        </span>
                        <button type="submit" class="btn btn-default btn-xs start"> <i class="glyphicon glyphicon-upload"></i> <span>Start upload</span> </button>
                        <button type="reset" class="btn btn-default btn-xs cancel"> <i class="glyphicon glyphicon-ban-circle"></i> <span>Cancel upload</span> </button>
                        <button type="button" class="btn btn-default btn-xs delete"> <i class="glyphicon glyphicon-trash"></i> <span>Delete</span> </button>
                        <input type="checkbox" class="toggle" style="width:20px">
                        <!-- The global file processing state --> 
                        <span class="fileupload-process"></span> </div>
                    <!-- The global progress state -->
                    <div class="col-lg-5 fileupload-progress fade"> 
                        <!-- The global progress bar -->
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                        </div>
                        <!-- The extended global progress state -->
                        <div class="progress-extended">&nbsp;</div>
                    </div>
                </div>
                <!-- The table listing the files available for upload/download -->
                
              
                
                
                
                <table role="presentation" class="table table-condensed">
                    <tbody class="files">
                        
                    </tbody>
                    
                    
                    
                </table>
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
    
</form>
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
<script type="text/javascript" src="../../plugins/jgrowl/jquery.jgrowlllllllllll.js"></script> 
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
<script type="text/javascript" src="../../plugins/jstree/jquery.jstree.js"></script> 
<script type="text/javascript" src="../../plugins/jqueryUI/jquery.ui.autocomplete.html.js"></script> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.js"></script> 
<script type="text/javascript" src="../../plugins/fancybox/jquery.fancybox.pack.js"></script> 
<script type="text/javascript" src="../../plugins/prettify/prettify.js"></script> 
<script type="text/javascript" src="../../plugins/easydrag/jquery.easydrag.js"></script> 
<script type="text/javascript" src="../../plugins/tooltip/jquery.bubblepopup.v2.3.1.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.variables.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.utility.js"></script> 
<!--<script type="text/javascript" src="../../krad/scripts/krad.initialize.js"></script> --> 
<script type="text/javascript" src="../../krad/scripts/krad.request.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.response.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.actions.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.dirty.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.lookup.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.message.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.session.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.url.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.validate.js"></script> 
<script type="text/javascript" src="../../krad/scripts/krad.widget.js"></script> 
<script type="text/javascript" src="../../plugins/easing/jquery.easing-1.3.pack.js"></script> 
<script type="text/javascript" src="../../themes/kboot/scripts/less-1.5.0.min.js"></script> 
<script type="text/javascript" src="../scripts/kradSampleApp.js"></script> 

<!-- The template to display files available for upload --> 
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary btn-xs start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-default btn-xs cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script> 
<!-- The template to display files available for download --> 
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-default btn-xs delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script> 
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included --> 
<script src="../../plugins/batch-file-upload/js/vendor/jquery.ui.widget.js"></script> 
<!-- The Templates plugin is included to render the upload/download listings --> 
<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script> 
<!-- The Load Image plugin is included for the preview images and image resizing functionality --> 
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script> 
<!-- The Canvas to Blob plugin is included for image resizing functionality --> 
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script> 
<!-- Bootstrap JS is not required, but included for the responsive demo navigation --> 
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> 
<!-- blueimp Gallery script --> 
<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script> 
<!-- The Iframe Transport is required for browsers without support for XHR file uploads --> 
<script src="../../plugins/batch-file-upload/js/jquery.iframe-transport.js"></script> 
<!-- The basic File Upload plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload.js"></script> 
<!-- The File Upload processing plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-process.js"></script> 
<!-- The File Upload image preview & resize plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-image.js"></script> 
<!-- The File Upload audio preview plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-audio.js"></script> 
<!-- The File Upload video preview plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-video.js"></script> 
<!-- The File Upload validation plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-validate.js"></script> 
<!-- The File Upload user interface plugin --> 
<script src="../../plugins/batch-file-upload/js/jquery.fileupload-ui.js"></script> 
<!-- The main application script --> 
<script src="../../plugins/batch-file-upload/js/main.js"></script> 
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<div id="jstree-marker" style="display: none;">»</div>
<div id="jstree-marker-line" style="display: none;"></div>
<div id="vakata-contextmenu"></div>
</body>
</html>