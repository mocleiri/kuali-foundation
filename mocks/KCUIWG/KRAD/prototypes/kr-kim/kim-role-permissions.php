<?php
$section = '';
$page = 'role-permissions';
?>

<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css">
.container.uif-viewHeader-contentWrapper .uif-viewHeader, .uif-actionBar {
	margin-left: -8px;
}
.uif-actionBar {
	font-size: 13px;
	padding-bottom: 8px;
	padding-left: 0px;
	padding-top: 6px;
	background: white;
	margin-top: 0px;
}
</style>
<!--<link href="../css/kradSampleApp.css" rel="stylesheet" type="text/css"/>
   <link href="../css/labsProposal.css" rel="stylesheet" type="text/css"/>  -->

</head>

<body id="Uif-Application" style="padding-bottom: 48px;">
<!-- APPLICATION HEADER -->

<?php include ('includes/uif-applicationHeader.php') ?>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"> 
        <!-- BREADCRUMBS --> 
        <!-- VIEW HEADER -->
        <?php include ('includes/uif-viewHeader-role.php') ?>
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"> 
            <!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-role.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:none;"> </div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                <header class="clearfix uif-header-contentWrapper pull-left">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span"> Permissions </span> </h2>
                    </div>
                </header>
         
                <table id="u569ish_line0" class="table table-condensed" role="presentation">
                    <tbody>
                        <tr>
                            <th scope="row">Permission Namespace</th>
                            <th class="uif-gridLayoutCell">Permission Identifier</th>
                            <th class="uif-gridLayoutCell">Permission Name</th>
                            <th class="uif-gridLayoutCell">Permission Detail Values</th>
                            <th class="uif-gridLayoutCell">Active</th>
                            <th class="uif-gridLayoutCell text-right" style="width:80px;">Actions</th>
                        </tr>
                        <tr>
                            <td scope="row">KUALI - Kuali Systems</td>
                            <td>170</td>
                            <td>Take Requested Apprive Action</td>
                            <td>actionRequestCd=A</td>
                            <td><input type="checkbox"></td>
                            <td class="text-right"><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                        </tr>
                        <tr>
                            <td scope="row">KUALI - Kuali Systems</td>
                            <td>181</td>
                            <td>Edit Kuali ENROUTE Document Route Status Code R</td>
                            <td>documentTypeName=KualiDocument, routeStatusCode=R</td>
                            <td><input type="checkbox"></td>
                          <td class="text-right"><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                        </tr>
                        <tr>
                            <td scope="row">KR-SYS - Enterprise Infrastructure</td>
                            <td>334</td>
                            <td>Send Approve Request Kuali Document</td>
                            <td>actionRequestCd=A, documentTypeName=KualiDocument</td>
                            <td><input type="checkbox"></td>
                         <td class="text-right"><a href="#" class="btn btn-xs btn-default">Delete</a></td>
                        </tr>
                        <tr>
                            <td scope="row">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><a href="#" class="btn btn-xs btn-default"> <span class="icon-plus" style="font-size:10px"></span> Add New</a></td>
                        </tr>
                    </tbody>
                </table>
            </main>
        </div>        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">

<div class="global-navigate btn-group">
                    <button type="button" href="asdf.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="asdf.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>




<div class="global-actions btn-group">

                    <button type="button" id="" class="btn btn-default">Save</button>
                   <button type="button" id="" class="btn btn-default">Reload</button>
                   <button type="button"  id="" class="btn btn-default">Submit Document</button>
                </div>
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
<?php include ('includes/footer-scripts.php') ?>

<!-- Modal -->
<div class="modal fade" id="routelog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Route Log</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <ul class="timeline">
                        <li>
                            <div class="timeline-badge warning"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Action Taken: Approve</h4>
                                    <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> 11 hours ago </small></p>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Action</th>
                                            <td>Approved</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Ben Webster</a></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Time/Date</th>
                                            <td>03:17 PM 03/12/2014</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Annotations</th>
                                            <td>Paisis, filhis, espiritis santis. </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-badge warning"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Action Taken: FYI</h4>
                                    <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> 8 hours ago </small></p>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Action</th>
                                            <td>FYI</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Lester Young</a></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Time/Date</th>
                                            <td>03:17 PM 03/12/2014</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Annotations</th>
                                            <td>Mé faiz elementum girarzis</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="timeline-badge success"><i class="glyphicon glyphicon-credit-card icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Pending Action: Approve</h4>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Oscar Peterson</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-badge"><i class="glyphicon glyphicon-check icon-ok"></i></div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title">Future Action: Approve</h4>
                                </div>
                                <div class="timeline-body">
                                    <p>&nbsp;</p>
                                    <table class="table table-condensed">
                                        <tr>
                                            <th scope="row">Taken By</th>
                                            <td><a href="#">Bill Evans</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Close</a> </div>
        </div>
    </div>
</div>

<!-- end Modal -->

</body>
</html>