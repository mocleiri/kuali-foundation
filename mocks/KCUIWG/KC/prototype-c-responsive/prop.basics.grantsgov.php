<?php
# Variables
$page = 'basics-grantsgov';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">   

<?php require_once( 'assets/inc/document-header.php' ) ?>
      
      
      <div class="leftnavContent">
      <?php require_once( 'assets/inc/document-nav.php' ) ?>
      
      <div id="content" role="application">
        <div class="row-fluid">
          






<div class="span12 content"> <h3>Grants.gov</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <div class="control-group">
                  <label class="control-label" for="Description">Opportunity ID</label>
                  <div class="controls">
                    <input type="text" id="Description" placeholder="" class="input-xlarge">
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="Description">CFDA Number</label>
                  <div class="controls">
                    <input type="text" id="Description" placeholder="" class="input-xlarge">
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>










        </div> </div>
      




</div>
<div class="docControls">  <a href="prop.basics.details.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
                  <a href="#" class="btn">Save</a>
                  <a href="prop.basics.sponsor.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a></div>




    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
