<?php
# Variables
$page = 'instspecdata-3';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container"> <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
             <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>  <div class="span10">
         <h3>Institution Data 3</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
            <div class="control-group">
              <label class="control-label" for="Description">Field 1</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 2</span>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 3</span>
              <div class="controls">
                <input type="text" id="Description4" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 4</span>
              <div class="controls">
                <input type="text" id="Description5" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 5</span>
              <div class="controls">
                <input type="text" id="Description6" placeholder="" class="input-xlarge">
              </div>
            </div>
      
              <div class="control-group buttons">
                <label class="control-label"></label>
                
                <!-- Button -->
                <!-- Button -->
                <div class="controls">
                  <a href="prop.basics.details.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
                  <a href="#" class="btn">Save</a>
                  <a href="prop.basics.sponsor.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
                </div>
              </div>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->


</body>
</html>
