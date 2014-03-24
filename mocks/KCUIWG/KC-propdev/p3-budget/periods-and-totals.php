<?php
$page = 'periods-and-totals';
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
                                    <span class="uif-headerText-span">Periods and Totals</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Budget periods have automatically been generated based on your proposal project dates. You can enter totals here, or enter budget details on the Personnel and Non-Personnel screens and allow the system to calculate the values.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion-periods-totals">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-periods-totals" href="#collapse-one">Budget Periods &amp; Totals</a>
                                                    <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                                </h4>
                                            </div>
                                            <div class="panel-collapse collapse in" id="collapse-one">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Start</th>
                                                            <th>End</th>
                                                            <th>Months</th>
                                                            <th>Total</th>
                                                            <th>Direct</th>
                                                            <th>F&amp;A</th>
                                                            <th>Unrecovered F&amp;A</th>
                                                            <th>Cost sharing</th>
                                                            <th>Cost limits</th>
                                                            <th>Direct limits</th>
                                                            <th><span class="sr-only">Actions</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr class="uif-new-row">
                                                            <td>02/01/2014</td>
                                                            <td>01/31/2015</td>
                                                            <td>12.0</td>
                                                            <td><label for="line_1_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_1_total" id="line_1_total"></label></td>
                                                            <td><label for="line_1_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_1_direct" id="line_1_direct"></label></td>
                                                            <td><label for="line_1_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_1_fa" id="line_1_fa"></label></td>
                                                            <td><label for="line_1_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_1_unrecovered" id="line_1_unrecovered"></label></td>
                                                            <td><label for="line_1_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_1_cost-sharing" id="line_1_cost-sharing"></label></td>
                                                            <td><label for="line_1_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_1_cost-limit" id="line_1_cost-limit"></label></td>
                                                            <td><label for="line_1_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_1_direct-limit" id="line_1_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr class="uif-new-row">
                                                            <td>02/01/2015</td>
                                                            <td>01/31/2016</td>
                                                            <td>12.0</td>
                                                            <td><label for="line_2_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_2_total" id="line_2_total"></label></td>
                                                            <td><label for="line_2_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_2_direct" id="line_2_direct"></label></td>
                                                            <td><label for="line_2_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_2_fa" id="line_2_fa"></label></td>
                                                            <td><label for="line_2_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_2_unrecovered" id="line_2_unrecovered"></label></td>
                                                            <td><label for="line_2_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_2_cost-sharing" id="line_2_cost-sharing"></label></td>
                                                            <td><label for="line_2_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_2_cost-limit" id="line_2_cost-limit"></label></td>
                                                            <td><label for="line_2_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_2_direct-limit" id="line_2_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr class="uif-new-row">
                                                            <td>02/01/2016</td>
                                                            <td>01/31/2017</td>
                                                            <td>12.0</td>
                                                            <td><label for="line_3_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_3_total" id="line_3_total"></label></td>
                                                            <td><label for="line_3_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_3_direct" id="line_3_direct"></label></td>
                                                            <td><label for="line_3_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_3_fa" id="line_3_fa"></label></td>
                                                            <td><label for="line_3_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_3_unrecovered" id="line_3_unrecovered"></label></td>
                                                            <td><label for="line_3_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_3_cost-sharing" id="line_3_cost-sharing"></label></td>
                                                            <td><label for="line_3_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_3_cost-limit" id="line_3_cost-limit"></label></td>
                                                            <td><label for="line_3_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_3_direct-limit" id="line_3_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr class="uif-new-row">
                                                            <td>02/01/2017</td>
                                                            <td>01/31/2018</td>
                                                            <td>12.0</td>
                                                            <td><label for="line_4_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_4_total" id="line_4_total"></label></td>
                                                            <td><label for="line_4_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_4_direct" id="line_4_direct"></label></td>
                                                            <td><label for="line_4_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_4_fa" id="line_4_fa"></label></td>
                                                            <td><label for="line_4_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_4_unrecovered" id="line_4_unrecovered"></label></td>
                                                            <td><label for="line_4_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_4_cost-sharing" id="line_4_cost-sharing"></label></td>
                                                            <td><label for="line_4_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_4_cost-limit" id="line_4_cost-limit"></label></td>
                                                            <td><label for="line_4_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_4_direct-limit" id="line_4_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr class="uif-new-row">
                                                            <td>02/01/2018</td>
                                                            <td>01/31/2019</td>
                                                            <td>12.0</td>
                                                            <td><label for="line_5_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_5_total" id="line_5_total"></label></td>
                                                            <td><label for="line_5_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_5_direct" id="line_5_direct"></label></td>
                                                            <td><label for="line_5_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_5_fa" id="line_5_fa"></label></td>
                                                            <td><label for="line_5_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_5_unrecovered" id="line_5_unrecovered"></label></td>
                                                            <td><label for="line_5_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_5_cost-sharing" id="line_5_cost-sharing"></label></td>
                                                            <td><label for="line_5_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_5_cost-limit" id="line_5_cost-limit"></label></td>
                                                            <td><label for="line_5_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_5_direct-limit" id="line_5_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><label for="line_5_start"><span class="sr-only">Period start</span><input type="text" size="5" name="line_5_start" id="line_5_start" placeholder="mm/dd/yyyy"></label></td>
                                                            <td><label for="line_5_end"><span class="sr-only">Period end</span><input type="text" size="5" name="line_5_end" id="line_5_end" placeholder="mm/dd/yyyy"></label></td>
                                                            <td></td>
                                                            <td><label for="line_5_total"><span class="sr-only">Period total</span><input type="text" size="3" name="line_5_total" id="line_5_total"></label></td>
                                                            <td><label for="line_5_direct"><span class="sr-only">Period direct</span><input type="text" size="3" name="line_5_direct" id="line_5_direct"></label></td>
                                                            <td><label for="line_5_fa"><span class="sr-only">Period F&amp;A</span><input type="text" size="3" name="line_5_fa" id="line_5_fa"></label></td>
                                                            <td><label for="line_5_unrecovered"><span class="sr-only">Period unrecovered F&amp;A</span><input type="text" size="3" name="line_5_unrecovered" id="line_5_unrecovered"></label></td>
                                                            <td><label for="line_5_cost-sharing"><span class="sr-only">Period Cost sharing</span><input type="text" size="3" name="line_5_cost-sharing" id="line_5_cost-sharing"></label></td>
                                                            <td><label for="line_5_cost-limit"><span class="sr-only">Period Cost limit</span><input type="text" size="3" name="line_5_cost-limit" id="line_5_cost-limit"></label></td>
                                                            <td><label for="line_5_direct-limit"><span class="sr-only">Period Direct limit</span><input type="text" size="3" name="line_5_direct-limit" id="line_5_direct-limit"></label></td>
                                                            <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <td></td>
                                                            <td></td>
                                                            <td>60.0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td>$0</td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="11">
                                                                <a href="#" class="btn btn-default btn-sm">Recalculate</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Reset to period defaults</a> 
                                                                <a href="#" class="btn btn-default btn-sm">Generate all periods based on the first period</a> 
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
                <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="#">Save</a>
                <a class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem" href="rates.php">Save and continue...</a>
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

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>