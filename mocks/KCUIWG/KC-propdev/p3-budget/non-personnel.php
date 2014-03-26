<?php
$section = '';
$page = 'non-personnel';
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
                                    <span class="uif-headerText-span">Non-Personnel</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Add and configure non-personnel items for this budget.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="well">
                                    <div class="row">
                                        <h4>Add a new non-personnel item</h4>

                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_object_code">Object code name</label><br />
                                                <select name="line_2_object_code" id="line_2_object_code" class="form-control">
                                                    <option>- Select -</option>
                                                    <option value="equip-no-mtdc">Equipment - Not MTDC</option>
                                                    <option value="equip-rent-no-mtdc">Equipment Rental - Not MTDC</option>
                                                    <option value="equip-fabricated-no-mtdc">Fabricated Equipment - Not MTDC</option>
                                                    <option value="reactor-use">Reactor use</option>
                                                </select>
                                            </div>
                                        </div>
                                            <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_base_cost">Base cost</label><br />
                                                <input type="text" name="line_2_base_cost" id="line_2_base_cost" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_quantity">Quantity</label><br />
                                                <input type="text" name="line_2_quantity" id="line_2_quantity" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group uif-align-center">
                                                <label class="control-label" for="line_2_app_inf">Apply inflation?<br />
                                                    <input type="checkbox" name="line_2_app_inf" id="line_2_app_inf" class="form-control" value="1">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group uif-align-center">
                                                <label class="control-label" for="line_2_send_cost_sharing">Send cost shared?<br />
                                                    <input type="checkbox" name="line_2_send_cost_sharing" id="line_2_send_cost_sharing" class="form-control" value="1">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group uif-align-center">
                                                <label class="control-label" for="line_2_on_campus">On campus?<br />
                                                    <input type="checkbox" name="line_2_on_campus" id="line_2_on_campus" class="form-control" value="1">
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_start">Start date</label><br />
                                                <input type="text" name="line_2_start" id="line_2_start" class="form-control" placeholder="mm/dd/yyyy">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_end">End date</label><br />
                                                <input type="text" name="line_2_end" id="line_2_end" class="form-control" placeholder="mm/dd/yyyy">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_unrecovered_fa">Unrecoverd F&amp;A</label><br />
                                                <input type="text" name="line_2_unrecovered_fa" id="line_2_unrecovered_fa" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_cost_sharing">Cost sharing</label><br />
                                                <input type="text" name="line_2_cost_sharing" id="line_2_cost_sharing" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="control-label" for="line_2_description">Description</label><br />
                                                <textarea name="line_2_description" id="line_2_description" class="form-control"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <a class="btn btn-primary pull-right" href="#">Add object</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion-non-personnel">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-non-personnel" href="#collapse-one">Equipment</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse in" id="collapse-one">
                                                <form action="#" method="#" class="form-inline">
                                                    <table class="table">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <div class="row">
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Object code name</label><br />
                                                                                Not MTDC<br />
                                                                                <span class="text-muted">421818</span>
                                                                            </div>
                                                                        </div>
                                                                            <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Base cost</label><br />
                                                                                $2,000.00
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Quantity</label><br />
                                                                                1.0
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group uif-align-center">
                                                                                <label class="control-label">Apply inflation?</label><br />
                                                                                No
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group uif-align-center">
                                                                                <label class="control-label">Send cost shared?</label><br />
                                                                                Yes
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group uif-align-center">
                                                                                <label class="control-label">On campus?</label><br />
                                                                                Yes
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Start date</label><br />
                                                                                10/01/2014
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">End date</label><br />
                                                                                09/30/2015
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Unrecoverd F&amp;A</label><br />
                                                                                $0
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Cost sharing</label><br />
                                                                                $1,000.00
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label class="control-label">Description</label><br />
                                                                                A description would go here.
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>                                                                
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-non-personnel" href="#collapse-two">Travel</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-two">
                                                
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-non-personnel" href="#collapse-three">Participant Support</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-three">
                                                
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-non-personnel" href="#collapse-four">Other</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapse-four">
                                                
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
                <a class="btn btn-default" href="personnel-assign.php">Back</a>
                <a class="btn btn-default" href="#">Save</a>
                <a class="btn btn-primary" href="subawards.php">Save and continue...</a>
            </div>
        </div>
    </form>

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>