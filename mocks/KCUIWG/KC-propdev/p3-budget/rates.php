<?php
$page = 'rates';
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuali Coeus Budget POC</title>

    <!-- GLOBAL STYLES -->
    <?php include ('kboot/includes/global-styles.php') ?>
</head>
<body id="Uif-Application">

    <!-- STICKY HEADER AND NAV -->
    <?php include ('kboot/includes/header-sticky.php') ?>

    <form id="kualiForm" action="#" method="post" accept-charset="UTF-8">
        <div id="LabsProposal" class="clearfix uif-formView">
            <?php include ('kboot/includes/header-docinfo.php') ?>
    
            <!-- VIEW CONTENT -->
            <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
    
                <!-- VIEW NAVIGATION -->
                <div class="col-md-3">
                    <?php include ('kboot/includes/navigation.php') ?>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-one">Research F&amp;A</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-two">Fringe Benefits</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-three">Inflation</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-four">Vacation</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-five">Lab Allocation - Salaries</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-six">Lab Allocation - Other</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
                                                    <a data-toggle="collapse" data-parent="#accordion-fa" href="#collapse-seven">Other</a>
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
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Faculty Salaries</td>
                                                            <td>Yes</td>
                                                            <td>2014</td>
                                                            <td>07/02/2014</td>
                                                            <td>65.00</td>
                                                            <td>65.00</td>
                                                            <td><a class="icon icon-pencil btn-edit"><span class="sr-only">Edit</span></a></td>
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
            <div class="uif-footer clearfix" data-sticky_footer="true" data-parent="LabsProposal">
                <a class="btn btn-default" href="_periods-and-totals.php">Back</a>
                <a class="btn btn-default" href="#">Save</a>
                <a class="btn btn-primary" href="rates.php">Save and continue...</a>
            </div>
        </div>
    </form>

    <!-- MODALS -->
    <!-- <div id="modal-create-version" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="create-new-label">Create a budget version</h4>
                </div>
                <div class="modal-body">
                    <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                        <div class="row">
                            <div class="col-md-3 uif-cssGridLabelCol">
                                <label for="new-version-name" class="uif-label displayWith-uk9itqu uif-labelBlock uif-required">Name: *</label>
                            </div>
                            <div class="col-md-9">
                                <div class="uif-inputField" data-role="InputField" data-label="Name">
                                    <input id="new-version-name" type="text" name="new-version-name" value="" size="30" class="form-control input-sm uif-textControl required" data-role="Control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-9">
                                <div class="well">
                                    <fieldset>
                                        <legend>Does this NIH proposal allow modular budgets?</legend>
                                        <label for="create-with-modular-true" class="clearfix"><input type="radio" name="create-with-modular" id="create-with-modular-true"> Yes, allow for modular budgets</label>
                                        <label for="create-with-modular-false" class="clearfix"><input type="radio" name="create-with-modular" id="create-with-modular-false"> No, there will be no modular budget</label>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-9">
                                <div class="well">
                                    <fieldset>
                                        <legend>Would you like guided assistance to complete this budget?</legend>
                                        <label for="create-with-guided-asst-true" class="clearfix"><input type="radio" name="create-with-guided-asst" id="create-with-guided-asst-true"> No, show me everything</label>
                                        <label for="create-with-guided-asst-false" class="clearfix"><input type="radio" name="create-with-guided-asst" id="create-with-guided-asst-false"> Yes, please use guided assistance</label>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a role="button" class="btn btn-link" data-dismiss="modal" href="#">Cancel</a>
                    <a role="button" class="btn btn-primary" href="periods-and-totals.php">Create and open</a>
                </div>
            </div>
        </div>
    </div> -->

    <?php include ('kboot/includes/footer-scripts.php') ?>
</body>
</html>