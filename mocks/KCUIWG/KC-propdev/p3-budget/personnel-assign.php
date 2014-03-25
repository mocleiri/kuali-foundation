<?php
$section = 'personnel';
$page = 'personnel-assign';
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
                                    <span class="uif-headerText-span">Personnel Costs: Assign Personnel</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Assign personnel to one or all periods and configure efforts and charges.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                                <form action="#" method="#" class="form-horizontal">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="well clearfix">
                                                <section>
                                                    <div class="col-md-3">
                                                        <h4>Select working period</h4>
                                                    </div>
                                                    <div class="col-md-9">
                                                        <div class="form-group">
                                                            <label for="assign-working-period">Budget period:</label>
                                                            <select name="assign-working-period" id="assign-working-period" class="form-control">
                                                                <option value="p1">1: 01/01/2014 - 12/31/2014</option>
                                                                <option value="p1">2: 01/01/2015 - 12/31/2015</option>
                                                                <option value="p1">3: 01/01/2016 - 12/31/2016</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="well clearfix">
                                                <section>
                                                    <div class="col-md-3">
                                                        <h4>Assign Personnel</h4>
                                                        <span class="uif-current-period" id="current-period">Period 1</span><br />
                                                        <span class="uif-current-period-details" id="current-period-details">01/01/2014 - 12/31/2014</span>
                                                    </div>
                                                    <div class="col-md-9">
                                                        <div class="form-group">
                                                            <label for="assign-personnel" class="control-label">Personnel:</label>
                                                            <select name="assign-personnel" id="assign-personnel" class="form-control">
                                                                <option>- Select-</option>
                                                            </select>
                                                        </div>
                                                        <div class="input-group">
                                                            <label for="assign-object-code-group" class="control-label">Object code name:</label>
                                                            <select name="assign-object-code-group" id="assign-object-code-group" class="form-control col-md-11">
                                                                <option>Administrative Staff - Off</option>
                                                            </select>
                                                            <button class="btn btn-default icon icon-search input-group-addon"><span class="sr-only">Search</span></button>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="assign-group" class="control-label">Group:</label>
                                                            <select name="assign-group" id="assign-group" class="form-control col-md-10">
                                                                <option>Group that I've configured</option>
                                                            </select>
                                                            <a href="#" class="col-md-2">Create new group</a>
                                                        </div>
                                                            <!-- <button class="btn btn-primary">Assign to this period</button> -->
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion-grouped-faculty-salaries-tenured">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" data-parent="#accordion-grouped-faculty-salaries-tenured" href="#collapse-one">Personnel available for budgeting</a>
                                                        </h4>
                                                    </div>
                                                    <div class="panel-collapse collapse in" id="collapse-one">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>Name</th>
                                                                    <th>Start date</th>
                                                                    <th>End date</th>
                                                                    <th>% Effort</th>
                                                                    <th>% Charged</th>
                                                                    <th>Period type</th>
                                                                    <th>Requested salary</th>
                                                                    <th>Calculated fringe</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <span class="uif-text-medium">Ramen Noodle</span><br />
                                                                        <span class="uif-text-text-faded">Principal Investigator</span>
                                                                        <span class="uif-text-job-code">AA0000</span>
                                                                        <a href="#">Additional details</a>
                                                                    </td>
                                                                    <td><label for="line_1_start"><span class="sr-only">Start date</span><input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy"></label></td>
                                                                    <td><label for="line_1_end"><span class="sr-only">End date</span><input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy"></label></td>
                                                                    <td><label for="line_1_effort"><span class="sr-only">Percent effort</span><input type="text" size="3" name="line_1_effort" id="line_1_effort"></label></td>
                                                                    <td><label for="line_1_charged"><span class="sr-only">Percent charged</span><input type="text" size="3" name="line_1_charged" id="line_1_charged"></label></td>
                                                                    <td>
                                                                        <label for="line_1_period_type">
                                                                            <span class="sr-only">Percent effort</span>
                                                                            <select name="line_1_period_type" id="line_1_period_type">
                                                                                <option>Calendar</option>
                                                                            </select>
                                                                        </label>
                                                                    </td>
                                                                    <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                                                    <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <td colspan="8">
                                                                        <a href="#" class="btn btn-default btn-sm">Apply these settings to all periods</a>
                                                                    </td>
                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
            
            <!-- VIEW FOOTER -->
            <div class="uif-footer clearfix" data-sticky_footer="true" data-parent="LabsProposal">
                <button class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem">Save</button>
                <button class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem">Save and Continue</button>
            </div>
        </div>
    </form>

    <!-- MODALS -->
    <div id="modal-create-version" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="create-new-label">Create a budget version</h4>
                </div>
                <div class="modal-body">
                    <!-- Stuff here for modal -->
                </div>
                <div class="modal-footer">
                    <a role="button" class="btn btn-link" data-dismiss="modal" href="#">Cancel</a>
                    <a role="button" class="btn btn-primary" href="periods-and-totals.php">Create and open</a>
                </div>
            </div>
        </div>
    </div>

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>