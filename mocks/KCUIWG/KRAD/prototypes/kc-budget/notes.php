<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuali Coeus Budget POC</title>

    <!-- GLOBAL STYLES -->
    <?php include ('includes/global-styles.php') ?>
    <style type="text/css">



        #myCarousel {
            margin-top: 40px;
             border: 2px dotted blue;;
            width:600px;
        }

        .carousel-linked-nav,


        .carousel-linked-nav {
            width: 120px;
            margin-bottom: 20px;
        }
        #myCarousel .item{ border: 2px solid red;}

    </style>
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

            <div class="col-md-9" style="border: 1px solid green; ;">


                        <div class="uif-pageHeader clearfix">
                            <h2 class="uif-headerText">
                                <span class="uif-headerText-span">Budget</span>
                            </h2>
                            <h3>Guided View <small><a href="#" class="small">(Exit Guided View)</a> </small></h3>
                                </div>





            <div id="myCarousel" class="carousel slide">
                <!-- Carousel items -->
                <div class="carousel-inner">
                    <div class="active item">
                        link 1
                    </div>
                    <div class="item">
                     link 2
                    </div>
                    <div class="item">
               link 3
                    </div>
                </div>
                <!-- Carousel nav -->
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
            </div>






                        </div>

</div>  </div>


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