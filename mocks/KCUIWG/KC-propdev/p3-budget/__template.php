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

    <form action="#" method="post" accept-charset="UTF-8">
        <div id="LabsProposal" class="clearfix uif-formView">
            <?php include ('includes/header-docinfo.php') ?>
    
            <!-- VIEW CONTENT -->
            <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container">
    
                <!-- VIEW NAVIGATION -->
                <div class="col-md-3">
                    <?php include ('includes/navigation.php') ?>
                </div>

                <div class="col-md-9">
                    <main class="uif-page">
                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Budget</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>You don't have any budget versions created yet. Please create one now to get started.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                                <div class="row">
                                    <div class="col-md-3 uif-cssGridLabelCol">
                                        <label for="new-version-name" class="uif-label uif-labelBlock uif-required">Name: *</label>
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
                                                <label for="create-with-modular-true" class="clearfix"><input type="radio" name="create-with-modular" id="create-with-modular-true">Yes, allow for modular budgets</label>
                                                <label for="create-with-modular-false" class="clearfix"><input type="radio" name="create-with-modular" id="create-with-modular-false">No, there will be no modular budget</label>
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
                                                <label for="create-with-guided-asst-true" class="clearfix"><input type="radio" name="create-with-guided-asst" id="create-with-guided-asst-true">No, show me everything</label>
                                                <label for="create-with-guided-asst-false" class="clearfix"><input type="radio" name="create-with-guided-asst" id="create-with-guided-asst-false">Yes, please use guided assistance</label>
                                            </fieldset>
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