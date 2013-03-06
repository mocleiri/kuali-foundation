<?php
# Variables
$page = 'basics';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">

        <!-- Examples of error messages -->
        <div id="error_message_container" class="alert alert-error" tabindex="-1">
          <h3><i class="icon icon-alert"></i>We found the following <span id="error_total">7</span> errors when submitting this form:</h3>
          <ul>
            <li><a href="#ProposalType">Proposal Type: This field cannot be empty.</a></li>
            <li><a href="#leadUnit">Lead Unit: This field cannot be empty.</a></li>
            <li><a href="#activityType">Activity Type: This field cannot be empty.</a></li>
            <li><a href="#startDate">Proposal Start Date: You must select a date when this proposal begins.</a></li>
            <li><a href="#endDate">Proposal End Date: You must select a date when this propsal is due.</a></li>
            <li><a href="#orgdocnum2">Sponsor Code: You must enter your sponsor code.</a></li>
            <li><a href="#projectTitle">Project Title: You must supply a title for this project.</a></li>
          </ul>
        </div>
        <!-- // -->

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3>Proposal Details</h3>
          </div>
          <div class="boxContent expandTarget" style="display:none" >
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
        
        <div class="box">
          <div class="boxHeader expandControl">
            <h3>Grants.gov</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >
            <h4>Opportunity Search</h4>
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
            <!-- <div class="control-group" style="text-align: right; width:405px;">
              <button class="btn btn-mini" >Search </button>
            </div> -->
            <!-- <h4>Opportunity</h4>
            <div class="clearfix">
              <dl class="table-display" style="margin-top:-13px;">
                <dt> Opportunity ID</dt>
                <dd></dd>
                <dt>Opportunity Title</dt>
                <dd></dd>
                <dt>Submission Type</dt>
                <dd></dd>
                <dt>S2S Revision Type</dt>
                <dd></dd>
                <dt> CFDA Number</dt>
                <dd></dd>
                <dt>Competition Id</dt>
                <dd></dd>
                <dt>Opening Date</dt>
                <dd></dd>
                <dt>Closing Date</dt>
                <dd></dd>
                <dt>Instruction Page</dt>
                <dd></dd>
                <dt>Schema URL</dt>
                <dd></dd>
              </dl>
              <div class="clearfix"></div>
            </div>
            <h4>Submission Details</h4>
            Submission details will be available after the proposal is submitted.
            <h4>Forms</h4>
            No forms are currently available for the Grants.gov opportunity selected. -->
          </div>
        </div>
        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3> Sponsor &amp; Program Information </h3>
          </div>
          <div class="boxContent expandTarget" style="display:none" >

            <div class="control-group">
              <label class="control-label" for="start_date">Start Date</label>
              <div class="controls date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                <input type="text" class="input-small" id="start_date" name="input" placeholder="mm-dd-yyyy" value="03-06-2013">
                <button class="add-on"><i class="icon-calendar"></i></button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="ProposalType4">Notice of Opportunity</label>
              <div class="controls">
                <select name="select2" class="input-xlarge" title="Notice of Opportunity" >
                  <option value="">select</option>
                  <option value="1">Federal Solicitation</option>
                  <option value="2">Unsolicited</option>
                  <option value="3">Verbal Request for Proposal</option>
                  <option value="4">SBIR Solicitation</option>
                  <option value="5">STTR Solicitation</option>
                  <option value="6">Non-Federal Solicitation</option>
                  <option value="7">Internal</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType5">Sponsor Deadline Type</label>
              <div class="controls">
                <select class="input-xlarge" title="Sponsor Deadline Type">
                  <option value="">select</option>
                  <option value="P">Postmark</option>
                  <option value="R">Receipt</option>
                  <option value="T">Target</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description3">CFDA Number</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description4">Sponsor Name</label>
              <div class="controls">
                <input type="text" id="Description4" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description5">Opportunity ID</label>
              <div class="controls">
                <input type="text" id="Description5" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description6">Prime Sponsor ID</label>
              <div class="controls">
                <input type="text" id="Description6" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description7">Sponsor Proposal ID</label>
              <div class="controls">
                <input type="text" id="Description7" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType6">NSF Science Code</label>
              <div class="controls">
                <select name="ProposalType2" class="input-xlarge" id="ProposalType6">
                  <option value="">select</option>
                  <option value="A.01">Aeronautical and Astronautical - Engineering: A.01</option>
                  <option value="F.01">Agricultural - Life Sciences: F.01</option>
                  <option value="B.01">Astronomy - Physical Sciences: B.01</option>
                  <option value="C.01">Atmospheric - Environmental Sciences: C.01</option>
                  <option value="A.02">Bioengineering/Biomedical - Engineering: A.02</option>
                  <option value="F.02">Biological - Life Sciences: F.02</option>
                  <option value="J.05">Business and Management - Non-Science and Engineering Fields: J.05</option>
                  <option value="A.03">Chemical - Engineering: A.03</option>
                  <option value="B.02">Chemistry - Physical Sciences: B.02</option>
                  <option value="A.04">Civil - Engineering: A.04</option>
                  <option value="J.06">Communications, Journalism and Library Sciences - Non-Science and Engineering Fields: J.06</option>
                  <option value="E.01">Computer Sciences: E.01</option>
                  <option value="Z.99">Costs not included in NSF report - used for reconciliation purposes: Z.99</option>
                  <option value="J.98">Costs to be allocated: J.98</option>
                  <option value="C.02">Earth Sciences - Environmental Sciences: C.02</option>
                  <option value="H.01">Economics - Social Sciences: H.01</option>
                  <option value="J.01">Education - Non-Science and Engineering Fields: J.01</option>
                  <option value="A.05">Electrical - Engineering: A.05</option>
                  <option value="J.03">Humanities - Non-Science and Engineering Fields: J.03</option>
                  <option value="J.02">Law - Non-Science and Engineering Fields: J.02</option>
                  <option value="D.01">Mathematical Science: D.01</option>
                  <option value="A.06">Mechanical  - Engineering: A.06</option>
                  <option value="F.03">Medical - Life Sciences: F.03</option>
                  <option value="A.07">Metallurgical and Materials  - Engineering: A.07</option>
                  <option value="C.03">Oceanography - Environmental Sciences: C.03</option>
                  <option value="A.99">Other - Engineering: A.99</option>
                  <option value="C.99">Other - Environmental Sciences: C.99</option>
                  <option value="F.99">Other - Life Sciences: F.99</option>
                  <option value="J.99">Other - Non-Science and Engineering Fields: J.99</option>
                  <option value="B.99">Other - Physical Sciences: B.99</option>
                  <option value="H.99">Other - Social Sciences: H.99</option>
                  <option value="I.01">Other Sciences, n.e.c.: I.01</option>
                  <option value="B.03">Physics - Physical Sciences: B.03</option>
                  <option value="H.02">Political Science - Social Sciences: H.02</option>
                  <option value="G.01">Psychology: G.01</option>
                  <option value="J.07">Social Work - Non-Science and Engineering Fields: J.07</option>
                  <option value="H.03">Sociology - Social Sciences: H.03</option>
                  <option value="J.04">Visual and Performing Arts - Non-Science and Engineering Fields: J.04</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType7">Subawards</label>
              <div class="controls">
                <label class="checkbox">
                  <input type="checkbox" value="">
                  Does this proposal include subaward(s)?</label>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description9">Sponsor Div Code</label>
              <div class="controls">
                <input type="text" id="Description9" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description10">Sponsor Program Code</label>
              <div class="controls">
                <input type="text" id="Description10" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"> 
              <!-- Textarea -->
              <label class="control-label">Opportunity Title</label>
              <div class="controls">
                <div class="textarea">
                  <textarea name="textarea2" class="input-xlarge"> </textarea>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3>Organization/Location</h3>
          </div>
          <div class="boxContent expandTarget" style="display:none" >
            <div class="boxSubheader">
              <h4>Applicant Organization</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Organization</dt>
              <dd></dd>
              <dt>Address 1</dt>
              <dd></dd>
              <dt>Address 2</dt>
              <dd></dd>
              <dt>City</dt>
              <dd></dd>
              <dt>State</dt>
              <dd></dd>
              <dt>Zip</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
            </dl>
            <div class="clearfix"></div>
            <div class="boxSubheader">
              <h4>Performing Organization</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Organization</dt>
              <dd></dd>
              <dt>Address 1</dt>
              <dd></dd>
              <dt>Address 2</dt>
              <dd></dd>
              <dt>City</dt>
              <dd></dd>
              <dt>State</dt>
              <dd></dd>
              <dt>Zip</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
              <dt>Congressional District</dt>
              <dd></dd>
            </dl>
            <div class="clearfix"></div>
            <div class="boxSubheader">
              <h4>Performance Site Locations (optional)</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-success"><i class="icon-white icon-plus"></i>Add</a></div>
            </div>
            <div class="boxSubheader">
              <h4>Other Organizations (optional)</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-success"><i class="icon-white icon-plus"></i>Add</a></div>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3>Delivery Info</h3>
          </div>
          <div class="boxContent expandTarget" style="display:none" >
            <div class="boxSubheader">
              <h4>Recipient</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Organization</dt>
              <dd></dd>
              <dt>Address 1</dt>
              <dd></dd>
              <dt>Address 2</dt>
              <dd></dd>
              <dt>City</dt>
              <dd></dd>
              <dt>State</dt>
              <dd></dd>
              <dt>Zip</dt>
              <dd></dd>
            </dl>
            <div class="clearfix"></div>
            <div class="boxSubheader">
              <h4>Delivery Details </h4>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType4">Mail By</label>
              <div class="controls">
                <select name="select2" class="input-xlarge" title="Notice of Opportunity" >
                  <option value="">select</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType4">Type</label>
              <div class="controls">
                <select name="select2" class="input-xlarge" title="Notice of Opportunity" >
                  <option value="">select</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description3">Mail Account ID</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description3">Number of Copies</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"> 
              <!-- Textarea -->
              <label class="control-label">Mail Description</label>
              <div class="controls">
                <div class="textarea">
                  <textarea name="textarea2" class="input-xlarge"> </textarea>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3>Keywords</h3>
          </div>
          <div class="boxContent expandTarget" style="display:none" >
            <dl class="table-display" style="margin-top:;">
              <dt>1</dt>
              <dd></dd>
            </dl>
            <div class="clearfix"></div>
          </div>
        </div>
        
        <div style=" padd12px; text-align:center">
          <a href="index.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <!-- <a href="#" id="validate_data" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div> -->
          <a href="prop.keypersonnel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
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