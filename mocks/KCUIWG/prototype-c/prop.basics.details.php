<?php
# Variables
$page = 'basics-details';
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
        <div class="span10">
          <h3> Proposal Details</h3>
          <div class=" formfields" style="display:">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <div class="control-group">
                  <label class="control-label" for="ProposalType">Proposal Type</label>
                  <div class="controls">
                    <select name="ProposalType" class="input-xlarge" id="ProposalType" required="required">
                      <option value="">select</option>
                      <option value="4">Continuation</option>
                      <option value="1" selected="selected">New</option>
                      <option value="3">Renewal</option>
                      <option value="2">Resubmission</option>
                      <option value="5">Revision</option>
                      <option value="6">Task Order</option>
                    </select>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="leadUnit">Lead Unit</label>
                  <div class="controls">
                    <select name="select" id="leadUnit" class="input-xlarge" title="Lead Unit" required="required">
                      <option value="">select</option>
                      <option value="000001" selected="selected">000001 - University</option>
                      <option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
                      <option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
                      <option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
                    </select>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="activityType">Activity Type</label>
                  <div class="controls">
                    <select name="select" id="activityType" class="input-xlarge" title="Activity Type" required="required">
                      <option value="">select</option>
                      <option value="4" selected="selected">Clinical Trial</option>
                      <option value="9">Construction</option>
                      <option value="7">Fellowship - Post-Doctoral</option>
                      <option value="6">Fellowship - Pre-Doctoral</option>
                      <option value="2">Instruction</option>
                      <option value="3">Public Service</option>
                      <option value="1">Research</option>
                      <option value="8">Student Services</option>
                      <option value="5">other</option>
                    </select>
                  </div>
                </div>
                <div class="control-group"> 
                  <!-- Text input-->
                  <label class="control-label" for="input01">Date Range</label>
                  <div class="controls">
                    <div style="display:inline-block; margin-right:12px;" >
                      <div style="display:inline">
                        <input type="text" placeholder="mm/dd/yyyy" class="input-small" value="<?php echo date('m/d/Y') ?>">
                      </div>
                      <span style="display:block; width:100px; color:#999"><small>start</small></span> </div>
                    <div style="display:inline-block" >
                      <div style="display:inline">
                        <input type="text" placeholder="mm/dd/yyyy" class="input-small" value="<?php $y = date('Y') + 1; echo date('m/d/') . $y; ?>">
                      </div>
                      <span style="display:block; width:100px;color:#999"><small>end</small></span> </div>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="orgdocnum">Sponsor Code</label>
                  <div class="controls">
                    <select name="orgdocnum" class="input-xlarge">
                      <option>select</option>
                      <option value="1" selected="selected">Chemistry - Physical Sciences B.02</option>
                    </select>
                    <!-- <input type="text" id="Description" placeholder="" class=" input-small"> --> 
                    <!-- <a href="#" class="btn lookup"><span>lookup</span></a> --> 
                  </div>
                </div>
                <div class="control-group"> 
                  <!-- Textarea -->
                  <label class="control-label" for="projectTitle">Project Title</label>
                  <div class="controls">
                    <textarea name="textarea" id="projectTitle" class="input-xlarge" required>This is a test project</textarea>
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="#" class="btn">Save</a> <a href="prop.basics.grantsgov.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
