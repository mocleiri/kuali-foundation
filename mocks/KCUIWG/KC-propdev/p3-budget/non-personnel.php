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
                                                                    <div class="form-group">
                                                                        <label class="control-label">Object code name</label><br />
                                                                        Not MTDC<br />
                                                                        <span class="text-muted">421818</span>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Base cost</label><br />
                                                                        $2,000.00
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Quantity</label><br />
                                                                        1.0
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Cost sharing</label><br />
                                                                        $1,000.00
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Description</label><br />
                                                                        A description would go here.
                                                                    </div>
                                                                </td>
                                                                <td rowspan="2"><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Object code name</label><br />
                                                                        Not MTDC<br />
                                                                        <span class="text-muted">421818</span>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Base cost</label><br />
                                                                        $2,000.00
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Quantity</label><br />
                                                                        1.0
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Cost sharing</label><br />
                                                                        $1,000.00
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label class="control-label">Description</label><br />
                                                                        A description would go here.
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr class="uif-new-row">
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_object_code" class="control-label">
                                                                            <span class="sr-only">Object code</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_object_code" id="line_3_object_code" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_cost" class="control-label">
                                                                            <span class="sr-only">Base cost</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_cost" id="line_3_cost" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_quantity" class="control-label">
                                                                            <span class="sr-only">Quantity</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_quantity" id="line_3_quantity" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_costsharing" class="control-label">
                                                                            <span class="sr-only">Cost sharing</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_costsharing" id="line_3_costsharing" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_description" class="control-label">
                                                                            <span class="sr-only">Description</span>
                                                                        </label>
                                                                        <textarea name="line_3_description" id="line_3_description" class="form-control"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
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
                                                <form action="#" method="#" class="form-inline">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>Object code</th>
                                                                <th>Base cost</th>
                                                                <th>Quantity</th>
                                                                <th>Cost sharing</th>
                                                                <th>Description</th>
                                                                <th><span class="sr-only">Actions</span></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr class="uif-new-row">
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_object_code" class="control-label">
                                                                            <span class="sr-only">Object code</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_object_code" id="line_3_object_code" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_cost" class="control-label">
                                                                            <span class="sr-only">Base cost</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_cost" id="line_3_cost" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_quantity" class="control-label">
                                                                            <span class="sr-only">Quantity</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_quantity" id="line_3_quantity" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_costsharing" class="control-label">
                                                                            <span class="sr-only">Cost sharing</span>
                                                                        </label>
                                                                        <input type="text" name="line_3_costsharing" id="line_3_costsharing" class="form-control">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <label for="line_3_description" class="control-label">
                                                                            <span class="sr-only">Description</span>
                                                                        </label>
                                                                        <textarea name="line_3_description" id="line_3_description" class="form-control"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
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