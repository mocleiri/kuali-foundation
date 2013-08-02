<?php
# Variables
$page = 'basics-propdetails';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">

        <!-- Examples of error messages --><!-- // -->

        <div class="box">
          <div class="boxHeader  ">
            <h3>Proposal Details</h3>
          </div>
          <div class="boxContent "  >
            <div class="boxSubheader">
              <h4><span class="req">*<span>required</span></span>Required</h4>
            </div>
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
                  <option value="4">Clinical Trial</option>
                  <option value="9">Construction</option>
                  <option value="7">Fellowship - Post-Doctoral</option>
                  <option value="6">Fellowship - Pre-Doctoral</option>
                  <option value="2">Instruction</option>
                  <option value="3">Public Service</option>
                  <option value="1" selected="selected">Research</option>
                  <option value="8">Student Services</option>
                  <option value="5">other</option>
                </select>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="start_date">Start Date</label>
              <div class="controls date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                <input type="text" class="input-small" id="start_date" name="input" placeholder="mm-dd-yyyy" value="03-06-2013">
                <button class="add-on"><i class="icon-calendar"></i></button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="end_date">End Date</label>
              <div class="controls date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                <input type="text" class="input-small" id="end_date" name="input" placeholder="mm-dd-yyyy" value="08-31-2015">
                <button class="add-on"><i class="icon-calendar"></i></button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="orgdocnum">Sponsor Code</label>
              <div class="controls">
                <select name="orgdocnum">
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
                <textarea name="textarea" id="projectTitle" class="input-xlarge" required>The effect of maternal health on child nutrition</textarea>
              </div>
            </div>
            <!-- <div class="boxSubheader">
              <h4>Conditionally Required Institutional Fields</h4>
            </div>
            <div class="control-group">
              <label class="control-label" for="orgdocnum4">Award ID</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class=" input-small">
                <a href="#" class="btn lookup"><span>lookup</span></a> </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="orgdocnum5">Original Institutional Prop ID</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class=" input-small">
                <a href="#" class="btn lookup"><span>lookup</span></a> </div>
            </div> -->
          </div>
        </div>
        <div style=" padd12px; text-align:center">
          <!-- <a href="index.php" class="btn"><i class="icon-chevron-left"></i> Back</a> -->
          <a href="#" class="btn">Save</a>
          <!-- <a href="#" id="validate_data" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div> -->
        <a href="prop.basics.grantsgov.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>


<!--
Action list sticky box
Chris Rodriguez, clrux@bu.edu
-->
<script>
// Loads file into iframe
// We'll use an iframe for the purposes of this prototype. Comment this line and uncomment the line above to switch methods.

$('.action-list').on('click', function() {
  if ($('body').find('#action_list').is(':visible')) {
  } else {
    $('body').append('<div id="action_list" class="sticky-panel"><a href="#" class="close" data-dismiss="alert">&times;</a><div class="actions-padded" id="actions_container"><iframe src="prop.actions-cr1.php"></iframe></div>');
    // Loads the above file
    // If we're on a web server just use the filename (plus path if necessary)
    //$('#action_list #actions_container').load(actions_list_file);
    return false;
  }
});
</script>
<!-- // -->


</body>
</html>