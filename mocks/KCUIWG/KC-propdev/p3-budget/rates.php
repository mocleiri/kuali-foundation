<?php
$page = 'rates';
$section = '';
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuali Coeus Budget POC</title>

    <!-- GLOBAL STYLES -->
    <?php include ('includes/global-styles.php') ?>
</head>
<body id="Uif-Application">

    <!-- STICKY HEADER AND NAV -->
    <?php include ('includes/header-sticky.php') ?>

    <form id="kualiForm" action="#" method="post" accept-charset="UTF-8">
        <div id="LabsProposal" class="clearfix uif-formView">
            <?php include ('includes/header-docinfo.php') ?>
    
            <!-- VIEW CONTENT -->
            <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
    
                <!-- VIEW NAVIGATION -->
                <div class="col-md-3">
                    <?php include ('includes/navigation.php') ?>
                </div>

                <div class="col-md-9">
                    <main id="LabsProposal-Page" class="uif-page">
                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Rates</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Verify the default rates set by your institution. You can override them if necessary by clicking the "edit" icon to the right of each row.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion-fa">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-one"><span class="icon icon-chevron-down"></span> Research F&amp;A</a>
                                                    <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse in" id="collapse-one">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-two"><span class="icon icon-chevron-right"></span> Fringe Benefits</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-two">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-three"><span class="icon icon-chevron-right"></span> Inflation</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-three">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>                                        

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-four"><span class="icon icon-chevron-right"></span> Vacation</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-four">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>                                       

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-five"><span class="icon icon-chevron-right"></span> Lab Allocation - Salaries</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-five">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>                                    

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-six"><span class="icon icon-chevron-right"></span> Lab Allocation - Other</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-six">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>                                    

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-seven"><span class="icon icon-chevron-right"></span> Other</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-seven">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Description</th>
                                                            <th>Campus Contact</th>
                                                            <th>Fiscal Year</th>
                                                            <th>Start Date</th>
                                                            <th>Institution Rate</th>
                                                            <th>Application Rate</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>MTDC</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>48.00</td>
                                                            <td>48.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                            </td>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
            
            <!-- VIEW FOOTER -->
            <div class="uif-footer clearfix">
                <a class="btn btn-default" href="_periods-and-totals.php">Back</a>
                <a class="btn btn-default" href="#">Save</a>
                <a class="btn btn-primary" href="personnel-roster.php">Save and continue...</a>
            </div>
        </div>
    </form>

    <!-- MODALS -->

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>