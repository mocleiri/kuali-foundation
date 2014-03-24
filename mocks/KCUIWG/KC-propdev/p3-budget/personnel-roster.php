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
                                                                <a href="#" class="btn btn-default btn-sm pull-right uif-modal" data-modal="/modals/add-personnel-step-1.php" data-toggle="modal" data-target=".bs-example-modal-lg" href="#">Add more people to this budget</a>
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
                <form action="#" method="#">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="create-new-label">Add budget personnel</h4>
                    </div>
                    <div class="modal-body">
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row uif-bottom-shadow uif-indicator-arrow uif-indicator-arrow-center">
                                <div class="col-md-3">
                                    <div class="uif-personnel-search-for">
                                        <fieldset>
                                            <legend>Search for:</legend>
                                            <label for="employee"><input type="radio" name="personnel-search-for" id="personnel-search-for-employee"> Employee</label>
                                            <label for="nonemployee"><input type="radio" name="personnel-search-for" id="personnel-search-for-nonemployee"> Non-Employee</label>
                                            <label for="tbn"><input type="radio" name="personnel-search-for" id="personnel-search-for-tbn"> TBN</label>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="uif-personnel-search-form active" id="personnel-search-form-employee">
                                        <fieldset>
                                            <legend>Search by:</legend>
                                            <div class="col-md-3">
                                                <label for="personnel-search-by-type">
                                                    <select name="personnel-search-by-type" id="personnel-search-by-type">
                                                        <option value="first">First name</option>
                                                        <option value="last">Last name</option>
                                                        <option value="email">Email address</option>
                                                    </select>
                                                </label>
                                            </div>
                                            <div class="col-md-9">
                                                <label for="personnel-search-by-text">
                                                    <span class="sr-only">Search text</span>
                                                    <input type="text" name="personnel-search-by-text" id="personnel-search-by-text">
                                                    <button class="btn btn-default">Search</button>
                                                </label>
                                                <p class="clearfix helper">Results from your search will appear in the box below.</p>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <div class="row uif-bg-shaded">
                                <div class="col-md-5">
                                    <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                        <h5>We found <span id="found-total">13 <span id="search-type">Employee</span>'s' with the first name "<span id="search-text">john</span>":</h5>
                                        <p class="helper">Select personnel to add to the budget.</p>
                                        <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                        <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                            <ul>
                                                <li>
                                                    <label for="personnel-result-1">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-1"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-2">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-2"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-3">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-3"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-4">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-4"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-5">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-5"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-6">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-6"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-7">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-7"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <button class="btn btn-default"><span class="icon icon-plus"></span><span class="sr-only">Add to list</span></button>
                                    <button class="btn btn-default"><span class="icon icon-minus"></span><span class="sr-only">Remove from list</span></button>
                                </div>
                                <div class="col-md-5">
                                    <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                        <h5>We found <span id="found-total">13 <span id="search-type">Employee</span>'s' with the first name "<span id="search-text">john</span>":</h5>
                                        <p class="helper">Select personnel to add to the budget.</p>
                                        <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                        <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                            <ul>
                                                <li>
                                                    <label for="personnel-result-1">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-1"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-2">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-2"> 
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title faded">Research Scientist</span>
                                                        <span class="pull-right personnel-type">Employee</span>
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a role="button" class="btn btn-link" data-dismiss="modal" href="#">Cancel</a>
                        <a role="button" class="btn btn-primary" href="_personnel-roster.php">Add selected</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </div>

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>