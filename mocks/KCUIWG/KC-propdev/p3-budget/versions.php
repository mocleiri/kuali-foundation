<?php
# Page variables
$page = 'budget-versions';
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
        <div id="LabsProposal" class="clearfix uif-formView" data-role="View">
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
                            <div class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Budget Versions</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>The following budgets are linked to this proposal.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <table class="table table-condensed">
                                        <thead>
                                            <tr>
                                                <th>Final</th>
                                                <th>Name</th>
                                                <th>Direct cost</th>
                                                <th>F&amp;A</th>
                                                <th>Total</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="final-version">
                                                <td><label for="make_final_1"><input type="radio" name="name_final" id="make_final_1" value="version_1" checked="checked"></label></td>
                                                <td>
                                                    <span class="icon icon-lock"><span class="sr-only">Version locked</span></span> 
                                                    <a href="#">Test budget version</a><br />
                                                    <span class="final-version-selected">Will be submitted with proposal</span><br />
                                                    <dl>
                                                        <dt>From:</dt>
                                                        <dd>1/1/2014</dd>
                                                        <dt>To:</dt>
                                                        <dd>12/31/2019</dd>
                                                    </dl>
                                                </td>
                                                <td>$6,000</td>
                                                <td>$1,050</td>
                                                <td>$7,050</td>
                                                <td>Complete</td>
                                                <td>
                                                    <label for="version_1_actions"><span class="sr-only">Available actions</span></label>
                                                    <select id="version_1_actions" name="version_1_actions">
                                                        <option>- Actions -</option>
                                                        <option>View summary</option>
                                                        <option>Copy</option>
                                                        <option>Validate data</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="make_final_2"><input type="radio" name="name_final" id="make_final_2" value="version_2"></label></td>
                                                <td>
                                                    <a href="#">Some other test budget</a><br />
                                                    <dl>
                                                        <dt>From:</dt>
                                                        <dd>1/1/2014</dd>
                                                        <dt>To:</dt>
                                                        <dd>12/31/2019</dd>
                                                    </dl>
                                                </td>
                                                <td>$6,000</td>
                                                <td>$1,050</td>
                                                <td>$7,050</td>
                                                <td>Complete</td>
                                                <td>
                                                    <label for="version_1_actions"><span class="sr-only">Available actions</span></label>
                                                    <select id="version_1_actions" name="version_1_actions">
                                                        <option>- Actions -</option>
                                                        <option>View summary</option>
                                                        <option>Copy</option>
                                                        <option>Validate data</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="make_final_3"><input type="radio" name="name_final" id="make_final_3" value="version_1"></label></td>
                                                <td>
                                                    <a href="#">Another test budget version</a><br />
                                                    <dl>
                                                        <dt>From:</dt>
                                                        <dd>1/1/2014</dd>
                                                        <dt>To:</dt>
                                                        <dd>12/31/2019</dd>
                                                    </dl>
                                                </td>
                                                <td>$6,000</td>
                                                <td>$1,050</td>
                                                <td>$7,050</td>
                                                <td>Complete</td>
                                                <td>
                                                    <label for="version_1_actions"><span class="sr-only">Available actions</span></label>
                                                    <select id="version_1_actions" name="version_1_actions">
                                                        <option>- Actions -</option>
                                                        <option>View summary</option>
                                                        <option>Copy</option>
                                                        <option>Validate data</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <a class="btn btn-default btn-sm pull-right uif-modal" data-modal="/kboot/modals/create-version.php" href="#" data-toggle="modal" data-target=".bs-example-modal-lg" href="#">Create a new budget version</a>
                                        </tfoot>
                                    </table>

                                    <div class="well">
                                        <div class="version-complete-container">
                                            <h4>Complete your budget</h4>
                                            <label for="version-complete"><input type="checkbox" name="version-complete" id="version-complete" value="1" checked="checked"> Mark the above selected budget version as complete and lock editing of the budget version.</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
            
            <!-- VIEW FOOTER -->
            <!-- <div class="uif-footer clearfix" data-sticky_footer="true" data-parent="LabsProposal">
                <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="#">Save</a>
                <a class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem" href="rates.php">Save and continue...</a>
            </div> -->
        </div>
    </form>

    <!-- MODALS -->
    <div id="modal-create-version" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
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
    </div>

    <?php include ('kboot/includes/footer-scripts.php') ?>
</body>
</html>