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
                                                        <div class="form-group">
                                                            <label for="assign-object-code-group" class="control-label">Object code name:</label>
                                                            <select name="assign-object-code-group" id="assign-object-code-group" class="form-control">
                                                                <option>Administrative Staff - Off</option>
                                                            </select>
                                                            <!-- <button class="btn btn-default icon icon-search pull-right"><span class="sr-only">Search</span></button> -->
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="assign-group" class="control-label">Group:</label>
                                                            <select name="assign-group" id="assign-group" class="form-control">
                                                                <option>Group that I've configured</option>
                                                            </select>
                                                            <a href="#">Create new group</a>
                                                        </div>
                                                        <button class="btn btn-primary">Assign to this period</button>
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
                                                            <a data-toggle="collapse" data-parent="#accordion-grouped-faculty-salaries-tenured" href="#collapse-one"><span class="icon icon-chevron-down"></span> Personnel available for budgeting</a>
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
                                                                        <span class="uif-text-text-faded">Principal Investigator</span><br />
                                                                        <span class="uif-text-job-code">AA0000</span><br />
                                                                        <a href="#">Additional details</a>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_start" class="control-label">
                                                                                <span class="sr-only">Start date</span>
                                                                            </label>
                                                                            <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_end" class="control-label">
                                                                                <span class="sr-only">End date</span>
                                                                            </label>
                                                                            <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_effort" class="control-label">
                                                                                <span class="sr-only">Percent effort</span>
                                                                            </label>
                                                                            <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_charged" class="control-label">
                                                                                <span class="sr-only">Percent charged</span>
                                                                            </label>
                                                                            <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_period_type" class="control-label">
                                                                                <span class="sr-only">Percent effort</span>
                                                                            </label>
                                                                            <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                                                <option>Calendar</option>
                                                                            </select>
                                                                        </div>
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
            <div class="uif-footer clearfix">
                <a class="btn btn-default" href="personnel-roster.php">Back</a>
                <a class="btn btn-default" href="#">Save</a>
                <a class="btn btn-primary" href="non-personnel.php">Save and continue...</a>
            </div>
        </div>
    </form>

    <!-- MODALS -->

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>