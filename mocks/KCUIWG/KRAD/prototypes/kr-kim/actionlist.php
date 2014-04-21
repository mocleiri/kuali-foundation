<?php
$section = '';
$page = 'actionlist';
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
.dl-horizontal dd {
	margin-left: 180px;
	clear: inherit;
}
</style>
<link rel="stylesheet" type="text/css" href="../../krad/scripts/datatables/DT_bootstrapss.css">

<!-- Scripts, ideally we'd load these in the footer and not use in-line scripting -->
<script src="../../krad/scripts/jquery-1.10.1.min.js"></script>
<script src="../../krad/scripts/jquery-migrate-1.2.1.min.js"></script>
<!--[if lt IE 9]><script src="bootstrap/js/html5shiv.js"></script><![endif]-->

<script type="text/javascript" src="../../krad/scripts/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../krad/scripts/datatables/DT_bootstrap.js"></script>
</head>
<body id="Uif-Application" style="padding-bottom: 48px;">

<!-- APPLICATION HEADER -->

<?php include ('includes/uif-applicationHeader.php') ?>
<form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER --> 
        
        <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            
            <div id="Uif-BreadcrumbUpdate" style="display:none;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left:;">
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"><span class="uif-headerText-span">My Action List </span></h2>
                    </div>
                </header>
                
                <hr>
                <p class="pull-left lead">You currently have 12 actionalble items.</p>
                <p class="pull-right"><span aria-hidden="true" class="icon-cog" style="color:#999"></span> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#actionlist-prefs" data-submit_data="" data-dismiss="modal">Preferences</a></p>
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#actions-current" data-toggle="tab">Current Items</a></li>
                    <li><a href="#actions-completed" data-toggle="tab">Completed Actions</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content clearfix">
                    <div class="tab-pane active" id="actions-current">
                        <h4>Current Items</h4>
                        <table class="table table-condensed table-smaller-text table-hover" id="example">
                            <thead>
                                <tr>
                                    <th> <a href="#">Doc ID</a></th>
                                    <th> <a href="#">Route Status</a></th>
                                    <th> <a href="#"> Type</a></th>
                                    <th> <a href="#">Description</a></th>
                                    <th> <a href="#"> Action Requested</a></th>
                                    <th> <a href="#">Created</a></th>
                                    <th style="width:130px"> <a href="#">Actions</a></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row">3231</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3273</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Test Title </td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3273</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3237</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3237</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3355</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3308</td>
                                    <td class="">Disapproved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol2</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3333</td>
                                    <td class="">Disapproved</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Approve</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3454</td>
                                    <td class="">Disapproved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Test Title </td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3247</td>
                                    <td class="">Exception</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3245</td>
                                    <td class="">Exception</td>
                                    <td scope="row">KC Award Budget</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3239</td>
                                    <td class="">Saved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Institutional Protocol2</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3388</td>
                                    <td class="">Saved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">My Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                                <tr>
                                    <td scope="row">3316</td>
                                    <td class="">Saved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Test Title </td>
                                    <td class="">Approve</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                            <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                                <p class="lead">Doc ID 3231</p>
                                                <hr>
                                                <h4>Routing</h4>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="u87qj9g" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                            <div class="progress-details">
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                                <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                                <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                                <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                            </div>
                                                            <div class="progress">
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                                <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4>Details</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Project Title:</dt>
                                                            <dd>Walk to Work</dd>
                                                            <dt>Proposal No</dt>
                                                            <dd>6</dd>
                                                            <dt>Proposal Type:</dt>
                                                            <dd>New</dd>
                                                            <dt>Activity Type:</dt>
                                                            <dd>Research</dd>
                                                            <dt>Sponsor:</dt>
                                                            <dd>NIH</dd>
                                                            <dt>Lead Unit:</dt>
                                                            <dd>000001</dd>
                                                            <dt>Cost Shares:</dt>
                                                            <dd></dd>
                                                        </dl>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <dl class="dl-horizontal">
                                                            <dt>Amounts:</dt>
                                                            <dd>$263,275.74 (Direct Cost)</dd>
                                                            <dd>$6,126.33 (F&amp;A)</dd>
                                                            <dd>$272,522.88 (All)</dd>
                                                            <dt>Dates:</dt>
                                                            <dd>04/08/2014 (Proposal Due Date)</dd>
                                                            <dd>04/15/2014 (Start Date)</dd>
                                                            <dd>04/30/2014 (End Date)</dd>
                                                        </dl>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-xs btn-primary">Open</a> </div></td>
                                </tr>
                            </tbody>
                        </table>
                        
                     <div class="text-center">   <a id="u15ecnro" class="btn btn-primary uif-action" tabindex="0" data-role="Action" data-toggle="modal" data-target="#actionlist-blanket" data-submit_data="" data-dismiss="modal">
Blanket Actions...
</a></div>


                    </div>
                    <div class="tab-pane" id="actions-completed">
                        <h4> Completed Actions</h4>
                        <table id="u569ish_line0" class="table table-condensed" role="presentation">
                            <tbody>
                                <tr>
                                    <th class="">Doc ID</th>
                                    <th scope="row">Route Status</th>
                                    <th scope="row">Type</th>
                                    <th class="uif-gridLayoutCell">Description</th>
                                    <th class="uif-gridLayoutCell">Action Taken</th>
                                    <th class="uif-gridLayoutCell">Created</th>
                                    <th style="width:140px" class="uif-gridLayoutCell">Action</th>
                                </tr>
                                <tr class="table table-condensed table-smaller-text">
                                    <td scope="row">3237</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Award</td>
                                    <td class="">Another Research Award</td>
                                    <td class="">Complete</td>
                                    <td> 03/08/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                            <p class="lead">Doc ID 3237</p>
                                            <hr>
                                            <h4>Routing</h4>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div id="u87qj9g4" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                        <div class="progress-details">
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                            <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                        </div>
                                                        <div class="progress">
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <h4>Details</h4>
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Project Title:</dt>
                                                        <dd>Walk to Work</dd>
                                                        <dt>Proposal No</dt>
                                                        <dd>6</dd>
                                                        <dt>Proposal Type:</dt>
                                                        <dd>New</dd>
                                                        <dt>Activity Type:</dt>
                                                        <dd>Research</dd>
                                                        <dt>Sponsor:</dt>
                                                        <dd>NIH</dd>
                                                        <dt>Lead Unit:</dt>
                                                        <dd>000001</dd>
                                                        <dt>Cost Shares:</dt>
                                                        <dd></dd>
                                                    </dl>
                                                </div>
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Amounts:</dt>
                                                        <dd>$263,275.74 (Direct Cost)</dd>
                                                        <dd>$6,126.33 (F&amp;A)</dd>
                                                        <dd>$272,522.88 (All)</dd>
                                                        <dt>Dates:</dt>
                                                        <dd>04/08/2014 (Proposal Due Date)</dd>
                                                        <dd>04/15/2014 (Start Date)</dd>
                                                        <dd>04/30/2014 (End Date)</dd>
                                                    </dl>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-xs btn-primary">Open</a></div></td>
                                </tr>
                                <tr class="table table-condensed table-smaller-text">
                                    <td scope="row">3355</td>
                                    <td class="">Approved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol1</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                            <p class="lead">Doc ID 3231</p>
                                            <hr>
                                            <h4>Routing</h4>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div id="u87qj9g3" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                        <div class="progress-details">
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                            <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                        </div>
                                                        <div class="progress">
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <h4>Details</h4>
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Project Title:</dt>
                                                        <dd>Walk to Work</dd>
                                                        <dt>Proposal No</dt>
                                                        <dd>6</dd>
                                                        <dt>Proposal Type:</dt>
                                                        <dd>New</dd>
                                                        <dt>Activity Type:</dt>
                                                        <dd>Research</dd>
                                                        <dt>Sponsor:</dt>
                                                        <dd>NIH</dd>
                                                        <dt>Lead Unit:</dt>
                                                        <dd>000001</dd>
                                                        <dt>Cost Shares:</dt>
                                                        <dd></dd>
                                                    </dl>
                                                </div>
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Amounts:</dt>
                                                        <dd>$263,275.74 (Direct Cost)</dd>
                                                        <dd>$6,126.33 (F&amp;A)</dd>
                                                        <dd>$272,522.88 (All)</dd>
                                                        <dt>Dates:</dt>
                                                        <dd>04/08/2014 (Proposal Due Date)</dd>
                                                        <dd>04/15/2014 (Start Date)</dd>
                                                        <dd>04/30/2014 (End Date)</dd>
                                                    </dl>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-xs btn-primary">Open</a></div></td>
                                </tr>
                                <tr class="table table-condensed table-smaller-text">
                                    <td scope="row">3308</td>
                                    <td class="">Disapproved</td>
                                    <td scope="row">KC Protocol</td>
                                    <td class="">Institutional Protocol2</td>
                                    <td class="">Complete</td>
                                    <td> 03/10/2014</td>
                                    <td class=""><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a>
                                        <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;left: -1001px;top: 27px;width: 1125px;padding:15px; background:;">
                                            <p class="lead">Doc ID 3231</p>
                                            <hr>
                                            <h4>Routing</h4>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div id="u87qj9g2" class="well well-sm uif-boxLayoutVerticalItem clearfix" role="progressbar" aria-valuetext="Current step:Submit" aria-valuemax="5" aria-valuemin="0" aria-valuenow="2">
                                                        <div class="progress-details">
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Initialize </div>
                                                            <div style="width: 20.0%;" class="uif-step complete"> <span class="sr-only">Step completed:</span> Save </div>
                                                            <div style="width: 20.0%;" class="uif-step active"> <span class="sr-only">Current step:</span> Submit </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Route </div>
                                                            <div style="width: 20.0%;" class="uif-step"> <span class="sr-only">Unfinished step:</span> Approve </div>
                                                        </div>
                                                        <div class="progress">
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-success"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-info"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                            <div style="width: 20.0%;" class="progress-bar progress-bar-empty"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <h4>Details</h4>
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Project Title:</dt>
                                                        <dd>Walk to Work</dd>
                                                        <dt>Proposal No</dt>
                                                        <dd>6</dd>
                                                        <dt>Proposal Type:</dt>
                                                        <dd>New</dd>
                                                        <dt>Activity Type:</dt>
                                                        <dd>Research</dd>
                                                        <dt>Sponsor:</dt>
                                                        <dd>NIH</dd>
                                                        <dt>Lead Unit:</dt>
                                                        <dd>000001</dd>
                                                        <dt>Cost Shares:</dt>
                                                        <dd></dd>
                                                    </dl>
                                                </div>
                                                <div class="col-md-4">
                                                    <dl class="dl-horizontal">
                                                        <dt>Amounts:</dt>
                                                        <dd>$263,275.74 (Direct Cost)</dd>
                                                        <dd>$6,126.33 (F&amp;A)</dd>
                                                        <dd>$272,522.88 (All)</dd>
                                                        <dt>Dates:</dt>
                                                        <dd>04/08/2014 (Proposal Due Date)</dd>
                                                        <dd>04/15/2014 (Start Date)</dd>
                                                        <dd>04/30/2014 (End Date)</dd>
                                                    </dl>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-xs btn-primary">Open</a></div></td>
                                </tr>
                            </tbody>
                        </table>
                        
                        
                        <div class="text-center">   <a id="u15ecnro" class="btn btn-primary uif-action" tabindex="0" data-role="Action" data-toggle="modal" data-target="" data-submit_data="" data-dismiss="modal">
Clear Completed Actions
</a></div>


                    </div>
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        
 

    
        <!-- DIALOGS/Placeholders -->
        <div id="Uif-Dialogs"></div>
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
    </span><span id="formComplete"></span>
</form>

<!-- MODALS -->
<?php include ('includes/modal-routelog.php') ?>

<?php include ('includes/modal-actionlist-prefs.php') ?>
<?php include ('includes/modal-actionlist-blanket.php') ?>
<?php include ('includes/modal-docsearch.php') ?>
<?php include ('includes/modal-docsearch-name.php') ?>
<?php include ('includes/modal-docsearch-results.php') ?>
<!-- FOOTER SCRIPTS --> 

<script type="text/javascript" src="../../themes/bootstrap/scripts/bootstrap.js"></script> 
<script type="text/javascript" src="../../plugins/scrollto/jquery.scrollTo-1.4.6.js"></script> 
<script type="text/javascript" src="../../plugins/jqform/jquery.form-3.31.0.js"></script> 
<script type="text/javascript" src="../../plugins/globalize/globalize.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.navigationMenu.js"></script> 
<script type="text/javascript" src="../../plugins/menu/krad.tabMenu.js"></script> 
<!--<script type="text/javascript" src="../../plugins/jgrowl/jquery.jgrowl.js"></script> --> 
<script type="text/javascript" src="../../plugins/textpopout/krad.textareaPopout.js"></script> 
<script type="text/javascript" src="../../plugins/validate/additional_validations.js"></script> 
<script type="text/javascript" src="../../plugins/cookie/jquery.cookie.js"></script> 
<script type="text/javascript" src="../../plugins/watermark/jquery.watermark.js"></script> 
<script type="text/javascript" src="../../plugins/countdown/jquery.countdown.js"></script> 
<!--<script type="text/javascript" src="../../plugins/blockUI/jquery.blockUI.js"></script> --> 
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
</body>
</html>