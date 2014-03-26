<?php
$section = '';
$page = 'subawards';
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
                                    <span class="uif-headerText-span">Subawards</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Upload the pre-formatted budget document for a subawardee organization. The system will extract the necessary information.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="panel-group" id="accordion-subawards">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion-subawards" href="#collapse-one"><span class="icon icon-chevron-down"></span> Budget subawards</a>
                                            <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse in" id="collapse-one">
                                        <form action="#" method="#" class="form-inline">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Organization</th>
                                                        <th>Comments</th>
                                                        <th>Filename</th>
                                                        <th>Form name</th>
                                                        <th><span class="sr-only">Actions</span></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>ACME Inc.</td>
                                                        <td>Additional proposal contributor, partner</td>
                                                        <td>acme-subaward.pdf</td>
                                                        <td>Foobar</td>
                                                        <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                    </tr>
                                                    <tr class="uif-new-row">
                                                        <td>
                                                            <div class="form-group-sm">
                                                                <label for="line_2_org_name" class="control-label"><span class="sr-only">Organization name</span></label>
                                                                <input type="text" name="line_2_org_name" id="line_2_org_name" class="form-control">
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="form-group-sm">
                                                                <label for="line_2_comments" class="control-label"><span class="sr-only">Comments</span></label>
                                                                <input type="text" name="line_2_comments" id="line_2_comments" class="form-control">
                                                            </div>
                                                        <td>
                                                            <div class="form-group-sm">
                                                                <label for="line_2_filename" class="control-label"><span class="sr-only">File</span></label>
                                                                <input type="file" name="line_2_filename" id="line_2_filename" class="form-control">
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="form-group-sm">
                                                                <label for="line_2_formname" class="control-label"><span class="sr-only">Form name</span></label>
                                                                <input type="text" name="line_2_formname" id="line_2_formname" class="form-control">
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
                    </main>
                </div>
            </div>
            
            <!-- VIEW FOOTER -->
            <div class="uif-footer clearfix">
                <a class="btn btn-default" href="non-personnel.php">Back</a>
                <a class="btn btn-default" href="#">Save</a>
                <a class="btn btn-primary" href="cost-sharing.php">Save and continue...</a>
            </div>
        </div>
    </form>

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>