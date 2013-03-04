<?php
# Variables
$page = 'start';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" style="margin-left:0px" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader ">
            <h3>Proposal Details</h3>
          </div>
          <div class="boxContent " style="display:block" >
            <div class="control-group">
              <label class="control-label" for="ProposalType">Proposal Type</label>
              <div class="controls"><select id="ProposalType" class="input-xlarge">
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
              <label class="control-label" for="asdf">Lead Unit</label>
              <div class="controls">
                <select title="* Lead Unit" class="input-xlarge">
                  <option value="">select</option>
                  <option value="000001">000001 - University</option>
                  <option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
                  <option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
                  <option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="asdf">Activity Type</label>
              <div class="controls">
                <select title="* Activity Type" class="input-xlarge">
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
              <div class="controls date" data-date="05-03-2013" data-date-format="dd-mm-yyyy">
                <input type="text" class="input-small" id="start_date" name="input" placeholder="from...">
                <span class="add-on"><i class="icon-calendar"></i></span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="end_date">End Date</label>
              <div class="controls date" data-date="05-03-2013" data-date-format="dd-mm-yyyy">
                <input type="text" class="input-small" id="end_date" name="input" placeholder="from...">
                <span class="add-on"><i class="icon-calendar"></i></span>
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
              <label class="control-label">Project Title</label>
              <div class="controls">
                <div class="textarea">
                  <textarea class="input-xlarge"> </textarea>
                </div>
              </div>
            </div>
            <!-- <div class="control-group">
              <label class="control-label" for="orgdocnum">Award ID</label>
              <div class="controls">
                <input type="text" id="Description2" placeholder="" class=" input-small">
                <a href="#" class="btn lookup"><span>lookup</span></a></div>
            </div>
            <div class="control-group">
              <label class="control-label" for="orgdocnum">Original Institutional Prop ID</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class=" input-small">
                <a href="#" class="btn lookup"><span>lookup</span></a></div>
            </div> -->
          </div>
        </div>       
   <div style=" padding: 12px; text-align:center">     <a href="prop.proposal.php" class="btn btn-inverse">Create Proposal <i class="icon-white icon-chevron-right"></i></a>
        
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