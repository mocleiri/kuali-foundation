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
                                    <span class="uif-headerText-span">Guided Mode</span> <span style="font-size:small">(<a href="#">Exit guided mode</a>)</span>
                                </h2>
                                <h4 class="uif-headerText">
                                    <div class="col-md-4">1. Budget Periods &amp; Totals</div>
                                    <div class="col-md-2">2. Modular</div>
                                    <div class="col-md-3">3. Summary &amp; Review</div>
                                </h4>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="well">
                                    <div class="row">
                                        <div class="col-md-1">                                           
                                            <p><b>Period</b></p>                                    
                                        </div>
                                        <div class="col-md-2">
                                            <p><b>Start date</b></p>
                                        </div>
                                        <div class="col-md-2">
                                            <p><b>End date</b></p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-1">                                               
                                            <input type="text" class="form-control" placeholder="1">
                                        </div>
                                        <div class="col-md-2">                                          
                                            <input type="text" class="form-control" placeholder="mm/dd/yyyy">
                                        </div>
                                        <div class="col-md-2">                                 
                                            <input type="text" class="form-control" placeholder="mm/dd/yyyy">
                                        </div>
                                        <div class="col-md-2">                                 
                                            <a class="btn btn-default btn-sm" href="#">Delete period</a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-1">                                               
                                            <input type="text" class="form-control" placeholder="2">
                                        </div>
                                        <div class="col-md-2">                                          
                                            <input type="text" class="form-control" placeholder="mm/dd/yyyy">
                                        </div>
                                        <div class="col-md-2">                                 
                                            <input type="text" class="form-control" placeholder="mm/dd/yyyy">
                                        </div>
                                        <div class="col-md-2">                                 
                                            <a class="btn btn-default btn-sm" href="#">Delete period</a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <a class="btn btn-default" href="#">Add period</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </main>
                </div>
            </div>
            <!-- VIEW FOOTER -->
            <div class="uif-footer clearfix">
                <a class="btn btn-default" href="#">Go back</a>
                <a class="btn btn-primary" href="#">Continue</a>
            </div>
        </div>
    </form>

    <?php include ('includes/footer-scripts.php') ?>
</body>
</html>