<?php
# Variables
$page = 'review-summary';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        
        <!-- Proposal Summary -->
        <div class="box">
          <div class="boxHeader expandControl opened">
            <h3>Proposal Summary</h3>
          </div>
          <div class="boxContent expandTarget" style="min-height:300px" >
            
            <!-- Proposal Summary -->
            <div class="boxSubheader">
              <h4 style="border-bottom:none;">Items to Note</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <div class="alert alert-info" style="margin-top:-13px;">
            	<ul>
                	<li><a href="#">Part of project performed outside US</a>: (PI) Haskell</li>
                	<li><a href="#">Restricted access or use for some researchers</a>: (PI) Haskell</li>
                	<li><a href="#">Use of space other than submitting unit</a>: (PI) Haskell</li>
                	<li><a href="#">Will appoint individuals outside of submitting unit</a>: (PI) Haskell</li>
                	<li><a href="#">Will ship outside the US</a>: (PI) Haskell</li>
                </ul>
            </div>
            <div class="clearfix"></div>
            
            <!-- Overview -->
            <div class="boxSubheader">
              <h4>Overview</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Investigator</dt>
              <dd>Haskell, Edward</dd>
              <dt>Agency/Sponsor</dt>
              <dd>006039 : Novartis Pharma AG</dd>
              <dt>Title</dt>
              <dd>Computationally guided strategies to mitigate viscosities of...</dd>
              <dt>Proposal Number</dt>
              <dd>0015753 (Approval In Progress)</dd>
              <dt>Proposal Period</dt>
              <dd>02/01/2013 &ndash; 01/31/2015</dd>
              <dt>Deadline Date</dt>
              <dd>01/23/2013</dd>
            </dl>
            <div class="clearfix"></div>
            
            <!-- Budget Total -->
            <div class="boxSubheader">
              <h4 style="border-bottom:none;">Budget Total</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <table class="table table-striped table-bordered table-condensed" style="margin-top:-13px;">
              <thead>
                <tr>
                  <th style="width:50%">&nbsp;</th>
                  <th nowrap style="text-align:center" >First Year<br /><small style="font-weight:normal;">02/01/2013 &ndash; 12/31/2015</small></th>
                  <th nowrap style="text-align:center" >Total<br /><small style="font-weight:normal;">02/01/2013 &ndash; 01/31/2015</small></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th>Direct Cost</th>
                  <td style="text-align:right">$56,34.20</td>
                  <td style="text-align:right">$216,298.39</td>
                </tr>                <tr>
                  <th>Indirect Cost</th>
                  <td style="text-align:right">$45,000.00</td>
                  <td style="text-align:right">$118,327.10</td>
                </tr>
                <tr>
                  <th>Sponsor Cost</th>
                  <td style="text-align:right">$34,102.63</td>
                  <td style="text-align:right">$334,625.49</td>
                </tr>
                <tr>
                  <th>Under Recovery</th>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                </tr>
                <tr>
                  <th>Cost Share</th>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                </tr>
                <tr>
                  <th>Total Project Cost</th>
                  <td style="text-align:right">$34,102.63</td>
                  <td style="text-align:right">$334,625.49</td>
                </tr>
              </tbody>
            </table>
            <div class="clearfix"></div>
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.summary.jpg" alt="temp screenshot" style="width:732px; height:186px;" />
            </div>-->
          
          </div>
        </div>
		
        <!-- Key Persons -->
        <div class="box">
            <div class="boxHeader expandControl opened">
                <h3>Key Persons</h3>
            </div>
            <div class="boxContent expandTarget">
            
                <!-- Key Persons -->
                <table class="table table-striped table-bordered table-condensed">
                  <thead>
                    <tr>
                      <th nowrap style="text-align:center; width:175px;" >Name</th>
                      <th nowrap style="text-align:center; width:190px;" >Department</th>
                      <th nowrap style="text-align:center; width:75px;" >Lead Unit</th>
                      <th nowrap style="text-align:center" >Role</th>
                      <th nowrap style="text-align:center; width:115px;" >Percent Effort</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th style="text-align:left" rowspan="2"><a href="#">Haskell, Edward</a> <small>(PI)</small></th>
                      <td style="text-align:left">Chemical Engineering</td>
                      <td style="text-align:left">yes</td>
                      <td style="text-align:left">Principal Investigator</td>
                      <td style="text-align:left">25%</td>
                    </tr>
                    <tr>
                      <td style="text-align:left" colspan="4">
                        <div class="boxSubheader expandControl closed">
                            <h4 style="border-bottom:none; font-weight:normal;"> Proposal Person Certification <span class="alert alert-block" style="float:right; text-align:center; padding:3px;">Incomplete</span></h4>
                        </div>
                        <div class="expandTarget" style="display:none">
                        <fieldset class="question">
                          <legend>I certify that (1) all information provided in this request or application is true, complete and accurate to the best of my knowledge and (2) I understand that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil or administrative penalties.</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question1"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question1"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>I certify that I agree to accept responsibility for the scientific conduct of the project, to provide the required progress reports, and to comply with the terms and conditions of the sponsoring agency.</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question2"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question2"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>Do you have a financial conflict of interest related to this project? For help, see http://researchadmin.iu.edu/COI/coi_home.html</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question3"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question3"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>If application is to a federal or federal pass-through sponsor, have any lobbying activities been or will any be conducted regarding this proposal?</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question4"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question4"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question5"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question5"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                      </div>
                      </td>
                    </tr>
                    <tr>
                      <th style="text-align:left" rowspan="2"><a href="#">Clayton, John</a></th>
                      <td style="text-align:left">Anthropology</td>
                      <td style="text-align:left">&nbsp;</td>
                      <td style="text-align:left">&nbsp;</td>
                      <td style="text-align:left">100%</td>
                    </tr>
                    <tr>
                      <td style="text-align:left" colspan="4">
                        <div class="boxSubheader expandControl closed">
                            <h4 style="border-bottom:none; font-weight:normal;"> Proposal Person Certification <span class="alert alert-success" style="float:right; text-align:center; padding:3px;">Complete</span></h4>
                        </div>
                        <div class="expandTarget" style="display:none">
                        <fieldset class="question">
                          <legend>I certify that (1) all information provided in this request or application is true, complete and accurate to the best of my knowledge and (2) I understand that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil or administrative penalties.</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question1"  value="1" disabled="disabled" checked>
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question1"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>I certify that I agree to accept responsibility for the scientific conduct of the project, to provide the required progress reports, and to comply with the terms and conditions of the sponsoring agency.</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question2"  value="1" disabled="disabled" checked>
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question2"  value="0" disabled="disabled">
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>Do you have a financial conflict of interest related to this project? For help, see http://researchadmin.iu.edu/COI/coi_home.html</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question3"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question3"  value="0" disabled="disabled" checked>
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>If application is to a federal or federal pass-through sponsor, have any lobbying activities been or will any be conducted regarding this proposal?</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question4"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question4"  value="0" disabled="disabled" checked>
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                        <fieldset class="question">
                          <legend>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</legend>
                          <div class="questionControls">
                            <input type="radio" name="the_question5"  value="1" disabled="disabled">
                            <label for="the_question_yes" class="radio inline">Yes</label>
                            <input type="radio" name="the_question5"  value="0" disabled="disabled" checked>
                            <label for="the_question_no" class="radio inline">No</label>
                          </div>
                        </fieldset>
                      </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div class="clearfix"></div>
                
            </div>
        </div>
        
        <!-- Attachments -->
        <div class="box">
          <div class="boxHeader expandControl opened">
            <h3>Attachments</h3>
          </div>
          <div class="boxContent expandTarget" style="min-height:500px" >
            
            <div class="boxSubheader">
              <h4>Proposal</h4>
            </div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
            
            <div class="boxSubheader" style="margin-top:30px;">
              <h4>Abstracts</h4>
            </div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
            
            <div class="boxSubheader" style="margin-top:30px;">
              <h4>Notes</h4>
            </div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader expandControl closed">
                  <h4 style="border-bottom:none;">this_file.pdf</h4>
                </div>
                <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                    <dt>File </dt>
                    <dd> this_file.pdf </dd>
                    <dt>Attachment Type:</dt>
                    <dd>Bibliography</dd>
                    <dt>Status</dt>
                    <dd>Incomplete</dd>
                    <dt>Contact Name</dt>
                    <dd>Tom Clark</dd>
                    <dt>Uploaded By</dt>
                    <dd>thrclark</dd>
                    <dt>Email Address</dt>
                    <dd>thrclark@indiana.edu</dd>
                    <dt>Posted Timestamp</dt>
                    <dd>2013-01-18T19:33:30+00:00</dd>
                    <dt>Phone Number</dt>
                    <dd>123-456-7890</dd>
                    <dt>Comments</dt>
                    <dd>Accipe sacrificium confessionum mearum de manu</dd>
                    <dt>Description</dt>
                    <dd>um: sed solvis eam, cum voles, aut miserans </dd>
                </dl>
                <div class="clearfix"></div>
            
            
            
            
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.attachments.jpg" alt="temp screenshot" style="width:718px; height:90px;" />
            </div>-->
          
          </div>
        </div>
        
        <!-- Special Review -->
       	<div class="box">
          <div class="boxHeader expandControl opened">
            <h3>Special Review</h3>
          </div>
          <div class="boxContent expandTarget">
            
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Name of Review</th>
                  <th>Approval</th>
                  <th>Protocol Number</th>
                  <th>Application Date</th>
                  <th>Approval Date</th>
                  <th style="width: 35px;"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>International Programs</td>
                  <td>Pending</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
                <tr>
                  <td>Animal Usage</td>
                  <td>Pending</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
              </tbody>
            </table>
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.specialreview.jpg" alt="temp screenshot" style="width:716px; height:92px;" />
            </div>-->
          
          </div>
        </div>
        
        <div style=" padd12px; text-align:center">
          <a href="prop.medusa.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn btn-success">Print</a>
          <a href="prop.review.actions.php" class="btn btn-inverse"><!--Save and -->Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>