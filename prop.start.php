<?php
# Variables
$page = 'prop-start';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <div class="row-fluid">
        <div class="span12" style="padding-top:4px">
          <div class="docHeader">
            <h2 style="display:inline;">Create Proposal</h2>
          </div>
          <div style="padding-top:20px; padding-bottom:20px;">
            <form  class="form-horizontal" method="get" action="prop.basics.details.php">
              <fieldset>
                <div class="formfields">
                
                
             
                
                
                  <div class="control-group">
                    <label class="control-label" for="ProposalType">Proposal Type</label>
                    <div class="controls">
                      <select name="ProposalType" class="input-xlarge" id="ProposalType" required="required">
                        <option value="">select</option>
                        <option value="4">Continuation</option>
                        <option value="1">New</option>
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
                        <option value="000001">000001 - University</option>
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
                        <option value="4">Clinical Trial</option>
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
                          <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                        </div>
                        <span style="display:block; width:100px; color:#999"><small>project start</small></span>
                      </div>
                      <div style="display:inline-block" >
                        <div style="display:inline">
                          <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                        </div>
                        <span style="display:block; width:100px;color:#999"><small>project end</small></span>
                      </div>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="orgdocnum">Sponsor Code</label>
                    <div class="controls">
                  
                      <input type="text" id="Description" placeholder="" class=" input-small"> 
                      <a href="#" id="" class="btn lookup"><span>lookup</span></a> 
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Textarea -->
                    <label class="control-label" for="projectTitle">Project Title</label>
                    <div class="controls">
                      <textarea name="textarea" id="projectTitle" class="input-xlarge" required></textarea>
                    </div>
                  </div>
                  
                    
                       
                       <div class="control-group">
                    <label class="control-label" for="orgdocnum">Award ID</label>
                    <div class="controls">
                  
                      <input type="text" id="Description" placeholder="" class=" input-small"> 
                      <a href="#" id="" class="btn lookup"><span>lookup</span></a> 
                    </div>
                  </div><div class="control-group">
                    <label class="control-label" for="orgdocnum">Original Institutional Proposal ID</label>
                    <div class="controls">
                  
                      <input type="text" id="Description" placeholder="" class=" input-small"> 
                      <a href="#" id="" class="btn lookup"><span>lookup</span></a> 
                    </div>
                  </div>
                  <div class="control-group ">
                    <label class="control-label"></label>
                    
                    <!-- Button -->
                    <!-- Button -->
                    <div class="controls">
                     
                      <button id="button" type="submit" class="btn btn-inverse">Create Proposal</button>
                    <button id="button" class="btn btn-link">Cancel</button> </div>
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<script>
$(document).ready(function() {
    $(".lookup").on('click', function() {
        $.fancybox.open({
            href: 'modal/lookup.html',
            type: 'iframe',
            padding: 0,
            width: 640,
        });
    });
});
</script>


    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->




