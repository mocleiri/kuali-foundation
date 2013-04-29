<?php
# Variables
$page = 'ib-docOverview';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">
      <div class="row-fluid">
        
        <?php require_once( 'assets/inc/document-header.php' ) ?>
      </div>
      <div class="leftnavContent">
        <?php require_once( 'assets/inc/document-nav.php' ) ?>
        <div id="content" role="application">
          <div class="row-fluid">
            <div class="span12 content">
              <h3>Document Overview</h3>
              <div class=" formfields">
                <form  class="form-horizontal" method="get" action="">
                  <fieldset style="">
                    <div class="boxContent " style=" display:nonee">
                      <fieldset>
                        <div class="control-group">
                          <!-- Text input-->
                          <label class="control-label" for="input2">Description</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge">
                          </div>
                        </div>
                        <div class="control-group">
                          <!-- Text input-->
                          <label class="control-label" for="input3">Org Doc Number</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge">
                          </div>
                        </div>
                        <div class="control-group">
                          <!-- Text input-->
                          <label class="control-label" for="input4">Explanation</label>
                          <div class="controls">                     
    <textarea id="textarea" name="textarea" class="input-xlarge"></textarea>
  </div>
                        </div>
                      </fieldset>
                    </div>
                  </fieldset>
                </form>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.attachments.proposal.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.attachments.internal.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
