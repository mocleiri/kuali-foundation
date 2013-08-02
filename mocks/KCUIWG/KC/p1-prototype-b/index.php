<?php
# Variables
$page = 'start';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">

        <div class="box">
          <div class="boxHeader  ">
            <h3>Proposal Details</h3>
          </div>
          <div class="boxContent " >
            <div class="boxSubheader">
              <h4><span class="req">*<span>required</span></span>Required</h4>
            </div>
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
              <label class="control-label" for="start_date">Start Date</label>
              <div class="controls date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                <input type="text" class="input-small" id="start_date" name="input" placeholder="mm-dd-yyyy">
                <button class="add-on"><i class="icon-calendar"></i></button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="end_date">End Date</label>
              <div class="controls date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                <input type="text" class="input-small" id="end_date" name="input" placeholder="mm-dd-yyyy">
                <button class="add-on"><i class="icon-calendar"></i></button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="orgdocnum">Sponsor Code</label>
              <div class="controls">
                <select name="orgdocnum">
                  <option>select</option>
                  <option value="1">Chemistry - Physical Sciences B.02</option>
                </select>
                <!-- <input type="text" id="Description" placeholder="" class=" input-small"> -->
                <!-- <a href="#" class="btn lookup"><span>lookup</span></a> -->
              </div>
            </div>

            <div class="control-group"> 
              <!-- Textarea -->
              <label class="control-label" for="projectTitle">Project Title</label>
              <div class="controls">
                <textarea name="textarea" id="projectTitle" class="input-xlarge" required></textarea>
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

        <div style=" padding: 12px; text-align:center">
          <a href="prop.basics.propdetails.php" class="btn btn-inverse">Create Proposal <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>