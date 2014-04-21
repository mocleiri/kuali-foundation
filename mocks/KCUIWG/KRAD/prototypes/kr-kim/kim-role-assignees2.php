<?php
$section = '';
$page = 'role-assignees';
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

table.dataTable th.sorting_asc, table.dataTable th.sorting { background-image:none}
</style>
<!--<link href="../css/kradSampleApp.css" rel="stylesheet" type="text/css"/>
   <link href="../css/labsProposal.css" rel="stylesheet" type="text/css"/>  -->

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
                <header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <h2 class="uif-headerText"> <span class="uif-headerText-span"> Assignees </span>  <a href="#" class="btn btn-default btn-xs pull-right"> Add Assignee</a> </h2>
                    </div>
                  
                </header>
                <div class="" style="margin-top:15px;">
                   
                 
                    <p class="lead">Currently, there are 7347 items.  </p>
                    
                    <hr>
                    <table class="table table-condensed table-smaller-text table-hover" id="example">
                        <thead>
                            <tr>
                                <th class="uif-gridLayoutCell"><a href="#">Name</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Full Name</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Type Code</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Memeber ID</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Namespace</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Active From</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Active To</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Org Code</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Chart Code</a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Account </a></th>
                                <th class="uif-gridLayoutCell"><a href="#"> Sub-Account </a></th>
                                <th class="uif-gridLayoutCell"><a href="#">Doc Type</a></th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010301</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010302</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010303</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010304</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010305</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010306</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010307</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010308</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010401</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                        <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                            <h4> Responsibility Actions</h4>
                                            <table id="u98wduy2" class="table table-condensed" role="presentation">
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Name</th>
                                                        <td class="uif-gridLayoutCell">All</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Type Code</th>
                                                        <td class="uif-gridLayoutCell">Approve</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Priority Number</th>
                                                        <td class="uif-gridLayoutCell">1</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Action Policy Code</th>
                                                        <td class="uif-gridLayoutCell">First</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Force Action</th>
                                                        <td class="uif-gridLayoutCell">No</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010402</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010403</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010404</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010405</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010406</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010407</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010408</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010301</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy20" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010302</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy19" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010303</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy18" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010304</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy17" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010305</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy16" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010306</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy15" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010307</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy14" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010308</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy13" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010401</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy12" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010402</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy11" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010403</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy10" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010404</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy9" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010405</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy8" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010406</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy7" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010407</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy6" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010408</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy5" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010409</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy4" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010301</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy37" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010302</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy36" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010303</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy35" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010304</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy34" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010305</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy33" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010306</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy32" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010307</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy31" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010308</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy30" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010401</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy29" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010402</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy28" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010403</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy27" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010404</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy26" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010405</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy25" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010406</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy24" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010407</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy23" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010408</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy22" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010409</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy21" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010301</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy54" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010302</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy53" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010303</td>
                                <td>CONST</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy52" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010304</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy51" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010305</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy50" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010306</td>
                                <td>CAMP</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy49" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010307</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy48" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010308</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy47" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_15</a></td>
                                <td>uarch_15</td>
                                <td>Group</td>
                                <td>6193815</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>UA - UNIV ADMIN</td>
                                <td>9010401</td>
                                <td>ADV</td>
                                <td>PREQ</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy46" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010402</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy45" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010403</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy44" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010404</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy43" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010405</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy42" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010406</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy41" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010407</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy40" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010408</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy39" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010409</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy38" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr> 
                            <tr class="table table-condensed">
                                <td><a href="#">uarch_12</a></td>
                                <td>uarch_12</td>
                                <td>Group</td>
                                <td>528450</td>
                                <td>KUALI</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>SE - SOUTHEAST</td>
                                <td>9010409</td>
                                <td>ADV</td>
                                <td>CONST</td>
                                <td class=""><div class="btn-group"> <a href="#" type="" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> Responisbility <span class="caret"></span></a>
                                    <div class="dropdown-menu" style="padding:15px; width: 280px" role="">
                                        <h4> Responsibility Actions</h4>
                                        <table id="u98wduy3" class="table table-condensed" role="presentation">
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Name</th>
                                                    <td class="uif-gridLayoutCell">All</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Type Code</th>
                                                    <td class="uif-gridLayoutCell">Approve</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Priority Number</th>
                                                    <td class="uif-gridLayoutCell">1</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Action Policy Code</th>
                                                    <td class="uif-gridLayoutCell">First</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Force Action</th>
                                                    <td class="uif-gridLayoutCell">No</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div></td>
                            </tr>
                        </tbody>
                    </table>
                    <p>&nbsp;</p>
                </div>
            </main>
        </div>
        <!-- VIEW FOOTER -->
        <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
            <button id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;saveContinue&quot;}"> Save and Submit </button>
            <button id="ufuknl9" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;save&quot;}"> Save </button>
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