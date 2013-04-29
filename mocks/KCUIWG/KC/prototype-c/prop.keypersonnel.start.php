<?php
# Variables
$page = 'personnel-pers';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>

<section>
    <div class="sectionContents">
        <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>

        <div class="container">
            <?php require_once( 'assets/inc/document-header.php' ) ?>

            <div class="row-fluid column-navleft">
                <div class="span2 vertNav">
                    <?php require_once( 'assets/inc/document-nav.php' ) ?>
                </div>

                <div class="span10 content">
                    <h3>Key Personnel</h3>
                    <div class=" formfields">
                        <p>Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).</p>
                        <p><a id="personnel_add_step_1" class="btn" href="#"><i class="icon icon-plus"></i> Add Personnel</a></p>
                    </div>
                </div>
            </div>

            <div class="docControls">
                <a href="prop.basics.keywords.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.keypersonnel.creditintel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
            </div>
        </div>
    </div>

    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>

<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

<script>
$(document).ready(function() {
    $("#personnel_add_step_1").on('click', function() {
        $.fancybox.open({
            href: 'modal/modal-addpersonnel/start.html',
            type: 'iframe',
            padding: 0,
            width: 640,
        });
    });
});
</script>

</body>
</html>
