<?php
$section = 'personnel';
$page = 'personnel-costs';
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
        <div id="LabsProposal" class="clearfix uif-formView" data-role="View">
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
                                    <span class="uif-headerText-span">Personnel Costs: Project Personnel</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Personnel added to the proposal are shown below. Review, configure, and add additional personnel to the budget.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion-group-rates">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-group-rates" href="#collapse-one">Personnel available for budgeting</a>
                                                    <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse in" id="collapse-one">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <td colspan="7">
                                                                Proposal personnel (automatically included in budget)
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>Name</th>
                                                            <th>Job code</th>
                                                            <th>Appointment type</th>
                                                            <th>Base salary</th>
                                                            <th>Salary effective date</th>
                                                            <th>Salary anniversary date</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr class="uif-new-row">
                                                            <td>
                                                                <span class="uif-text-medium">Ramen Noodle</span><br />
                                                                <span class="uif-text-faded">Principal Investigator</span>
                                                            </td>
                                                            <td><label for="line_1_job_code"><span class="sr-only">Job code</span><input type="input" size="3" name="line_1_job_code" id="line_1_job_code"></label></td>
                                                            <td>
                                                                <label for="line_1_appt_type"><span class="sr-only">Appointment type</span>
                                                                    <select name="line_1_appt_type" id="line_1_appt_type">
                                                                        <option value="12M">12 months</option>
                                                                        <option value="9M">9 months</option>
                                                                        <option vlaue="S">Summer</option>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                            <td><label for="line_1_base_salary"><span class="sr-only">Base salary</span><input type="input" size="3" name="line_1_base_salary" id="line_1_base_salary"></label></td>
                                                            <td><label for="line_1_salary_eff"><span class="sr-only">Salary effective date</span><input type="input" size="5" name="line_1_salary_eff" id="line_1_salary_eff" placeholder="mm/dd/yyyy"></label></td>
                                                            <td><label for="line_1_salary_anniv"><span class="sr-only">Salary anniversary date</span><input type="input" size="5" name="line_1_salary_anniv" id="line_1_salary_anniv" placeholder="mm/dd/yyyy"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr class="uif-new-row">
                                                            <td>
                                                                <span class="uif-text-medium">Marie Calendar</span><br />
                                                                <span class="uif-text-faded">Key Person<br />Research Assistant</span>
                                                            </td>
                                                            <td><label for="line_2_job_code"><span class="sr-only">Job code</span><input type="input" size="3" name="line_2_job_code" id="line_2_job_code"></label></td>
                                                            <td>
                                                                <label for="line_2_appt_type"><span class="sr-only">Appointment type</span>
                                                                    <select name="line_2_appt_type" id="line_2_appt_type">
                                                                        <option value="12M">12 months</option>
                                                                        <option value="9M">9 months</option>
                                                                        <option vlaue="S">Summer</option>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                            <td><label for="line_2_base_salary"><span class="sr-only">Base salary</span><input type="input" size="3" name="line_2_base_salary" id="line_2_base_salary"></label></td>
                                                            <td><label for="line_2_salary_eff"><span class="sr-only">Salary effective date</span><input type="input" size="5" name="line_2_salary_eff" id="line_2_salary_eff" placeholder="mm/dd/yyyy"></label></td>
                                                            <td><label for="line_2_salary_anniv"><span class="sr-only">Salary anniversary date</span><input type="input" size="5" name="line_2_salary_anniv" id="line_2_salary_anniv" placeholder="mm/dd/yyyy"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td colspan="7">
                                                                <a href="#" class="btn btn-default btn-sm">Sync all personnel effective dates</a> 
                                                                <a href="#" class="btn btn-default btn-sm pull-right uif-modal" data-modal="/kboot/modals/add-personnel-step-1.php" data-toggle="modal" data-target=".bs-example-modal-lg" href="#">Add more people to this budget</a>
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

                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Object Codes and Groups</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Configure object code and group rate pairs to be assigned to specific budget pay scales.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion-group-rates">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-group-rates" href="#collapse-two">Object code and group rate combinations</a>
                                                    <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse in" id="collapse-two">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <td></td>
                                                            <th>Object code</th>
                                                            <th>Group</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><a class="icon icon-angle-right"><span class="sr-only">Show/Hide details</span></td>
                                                            <td><label for="line_1_object_code"><span class="sr-only">Object code</span><input type="input" size="3" name="line_1_object_code" id="line_1_object_code"></label></td>
                                                            <td>
                                                                <label for="line_1_group_name"><span class="sr-only">Group name</span>
                                                                    <select name="line_1_group_name" id="line_1_group_name">
                                                                        <option value="Other">Some group that I've created</option>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                    </tbody>
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
                <button class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem">Save</button>
                <button class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem">Save and Continue</button>
            </div>
        </div>
    </form>

    <!-- MODALS -->
    <div id="modal-add-personnel" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
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
    </div> -->

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>