<?php
# Variables
$page = 'review-summary';
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
          
          
          	<ul id="myTab" class="nav nav-tabs" style="margin-top:25px;">
              <li><a href="#Summary" data-toggle="tab">Summary</a></li>
              <li><a href="#Submit" data-toggle="tab">Workflow Actions</a></li>
            </ul>
          
            <div class="tab-content">
              	<div class="tab-pane active" id="Summary">
              			
                    <!-- Progress Indication -->
                      <div class="box" style="margin-bottom:30px;">
                        <div class="boxHeader">
                          <h3>Workflow routing</h3>
                        </div>
                        <div class="boxContent" > 
                          
                          <div class="well">
                            <h4>Proposal progress</h4>
                            <div class="progress-details">
                              <div class="empty complete" style="width: 20%">Basics</div>
                              <div class="empty complete" style="width: 20%">Routing &amp; Review</div>
                              <div class="empty active" style="width: 20%">Final Institutional Review</div>
                              <div class="empty" style="width: 20%">Approved</div>
                              <div class="empty" style="width: 20%">Submitted to Sponsor</div>
                            </div>
                          	<div class="progress" style="height:3px;">
                              <div class="bar bar-success" style="width: 20%; height:3px;" title="Completed"></div>
                              <div class="bar bar-success" style="width: 20%; height:3px;" title="Completed"></div>
                              <div class="bar bar-warning" style="width: 20%; height:3px;" title="In Progress"></div>
                              <div class="bar bar-danger" style="width: 20%; height:3px; background-image:none; background-color:#CCC; color:#999; text-shadow:none;" title="Not Started"></div>
                              <div class="bar bar-danger" style="width: 20%; height:3px; background-image:none; background-color:#CCC; color:#999; text-shadow:none;" title="Not Started"></div>
                            </div>
                          </div>
                          
                        </div>
                      </div>
                        
					  <!-- Proposal Summary -->
                      <div class="box">
                        <div class="boxHeader expandControl opened panelhead">
                          <h3>Proposal Summary</h3>
                        </div>
                        <div class="boxContent expandTarget" style="min-height:300px"> 
                          
                          <!-- Proposal Summary -->
                          <div class="boxSubheader">
                            <h4 style="border-bottom:none;">Items to Note</h4>
                            <!--<div class="boxControls"><a href="#" class="btn ">Edit</a></div>--> 
                          </div>
                          <div class="alert alert-info" style="margin-top:-13px;">
                            <ul>
                              <li><a href="#">Part of project performed outside US</a>: (PI) Haskell</li>
                              <li><a href="#">Restricted access or use for some researchers</a>: (PI) Haskell</li>
                              <li><a href="#">Use of space other than submitting unit</a>: (PI) Haskell</li>
                              <li><a href="#">Will appoint individuals outside of submitting unit</a>: (PI) Haskell</li>
                              <li>Will ship outside the US: (PI) Haskell</li>
                            </ul>
                          </div>
                          <div class="clearfix"></div>
                          
                          <!-- Overview -->
                          <div class="boxSubheader">
                            <h4>Overview</h4>
                            <!--<div class="boxControls"><a href="#" class="btn ">Edit</a></div>--> 
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
                            <dd>02/01/2013 – 01/31/2015</dd>
                            <dt>Deadline Date</dt>
                            <dd>01/23/2013</dd>
                          </dl>
                          <div class="clearfix"></div>
                          
                          <!-- Budget Total -->
                          <div class="boxSubheader">
                            <h4 style="border-bottom:none;">Budget Total</h4>
                            <!--<div class="boxControls"><a href="#" class="btn ">Edit</a></div>--> 
                          </div>
                          <table class="table table-striped table-bordered table-condensed" style="margin-top:-13px;">
                            <thead>
                              <tr>
                                <th style="width:50%">&nbsp;</th>
                                <th nowrap="" style="text-align:center">First Year<br>
                                  <small style="font-weight:normal;">02/01/2013 – 12/31/2015</small></th>
                                <th nowrap="" style="text-align:center">Total<br>
                                  <small style="font-weight:normal;">02/01/2013 – 01/31/2015</small></th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <th>Direct Cost</th>
                                <td style="text-align:right">$56,34.20</td>
                                <td style="text-align:right">$216,298.39</td>
                              </tr>
                              <tr>
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
                          
                        </div>
                      </div>
                      
                      <!-- Key Persons -->
                      <div class="box">
                        <div class="boxHeader expandControl opened panelhead">
                          <h3>Key Persons</h3>
                        </div>
                        <div class="boxContent expandTarget"> 
                          
                          <!-- Key Persons -->
                          <table class="table table-striped table-bordered table-condensed">
                            <thead>
                              <tr>
                                <th nowrap="" style="text-align:center; width:175px;">Name</th>
                                <th nowrap="" style="text-align:center; width:190px;">Department</th>
                                <th nowrap="" style="text-align:center; width:75px;">Lead Unit</th>
                                <th nowrap="" style="text-align:center">Role</th>
                                <th nowrap="" style="text-align:center; width:115px;">Percent Effort</th>
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
                                <td style="text-align:left" colspan="4"><div class="boxSubheader expandControl closed">
                                    <h4 style="border-bottom:none; font-weight:normal;"> Proposal Person Certification <span class="alert alert-block" style="float:right; text-align:center; padding:3px;">Incomplete</span></h4>
                                  </div>
                                  <div class="expandTarget" style="display:none">
                                    <fieldset class="question">
                                      <span>I certify that (1) all information provided in this request or application is true, complete and accurate to the best of my knowledge and (2) I understand that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil or administrative penalties.</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>I certify that I agree to accept responsibility for the scientific conduct of the project, to provide the required progress reports, and to comply with the terms and conditions of the sponsoring agency.</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>Do you have a financial conflict of interest related to this project? For help, see http://researchadmin.iu.edu/COI/coi_home.html</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>If application is to a federal or federal pass-through sponsor, have any lobbying activities been or will any be conducted regarding this proposal?</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                  </div></td>
                              </tr>
                              <tr>
                                <th style="text-align:left" rowspan="2"><a href="#">Clayton, John</a></th>
                                <td style="text-align:left">Anthropology</td>
                                <td style="text-align:left">&nbsp;</td>
                                <td style="text-align:left">&nbsp;</td>
                                <td style="text-align:left">100%</td>
                              </tr>
                              <tr>
                                <td style="text-align:left" colspan="4"><div class="boxSubheader expandControl closed">
                                    <h4 style="border-bottom:none; font-weight:normal;"> Proposal Person Certification <span class="alert alert-success" style="float:right; text-align:center; padding:3px;">Complete</span></h4>
                                  </div>
                                  <div class="expandTarget" style="display:none">
                                    <fieldset class="question">
                                      <span>I certify that (1) all information provided in this request or application is true, complete and accurate to the best of my knowledge and (2) I understand that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil or administrative penalties.</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/check_box16.png" alt="checked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>I certify that I agree to accept responsibility for the scientific conduct of the project, to provide the required progress reports, and to comply with the terms and conditions of the sponsoring agency.</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/check_box16.png" alt="checked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>Do you have a financial conflict of interest related to this project? For help, see http://researchadmin.iu.edu/COI/coi_home.html</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/check_box16.png" alt="checked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>If application is to a federal or federal pass-through sponsor, have any lobbying activities been or will any be conducted regarding this proposal?</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/check_box16.png" alt="checked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                    <fieldset class="question">
                                      <span>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</span>
                                      <div class="questionControls">
                                        <img src="./assets/img/uncheck_box16.png" alt="unchecked" style="height:16px; width:17px; border:none; margin-left:15px;" /> Yes
                                        <img src="./assets/img/check_box16.png" alt="checked" style="height:16px; width:17px; border:none; margin-left:15px;" /> No
                                      </div>
                                    </fieldset>
                                  </div></td>
                              </tr>
                            </tbody>
                          </table>
                          <div class="clearfix"></div>
                          
                        </div>
                      </div>
                      
                      <!-- Attachments -->
                      <div class="box">
                      
                        <div class="boxHeader expandControl opened panelhead">
                          <h3>
                            Attachments
                          </h3>
                        </div>
                        <div class="boxContent expandTarget" style="min-height:500px">
                          
                          <div style="margin-bottom:10px;"><a href="#" class="btn" onClick="alert('open modal with add attachment form')">Upload and add attachment</a></div>
                          
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
                            
                          
                            <?PHP
                            /*
                            <div class="boxSubheader" style="margin-top:30px;">
                                <h4>Add Attachment</h4>
                            </div>
                            <fieldset>
                            <div class="control-group">
                              <label class="control-label">File </label>
                              <!-- File Upload -->
                              <div class="controls">
                                <input class="input-file" id="fileInput" type="file">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Select Basic -->
                              <label class="control-label">Attachment Type</label>
                              <div class="controls">
                                <select name="select" class="input-xlarge">
                                  <option>Enter</option>
                                  <option>Your</option>
                                  <option>Options</option>
                                  <option>Here!</option>
                                </select>
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Select Basic -->
                              <label class="control-label">Status</label>
                              <div class="controls">
                                <select name="select" class="input-xlarge">
                                  <option>Enter</option>
                                  <option>Your</option>
                                  <option>Options</option>
                                  <option>Here!</option>
                                </select>
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Contact Name</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Uploaded By:</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Email Address</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Posted Timestamp</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Phone Number</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Comments</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01">Description</label>
                              <div class="controls">
                                <input type="text" class="input-xlarge">
                              </div>
                            </div>
                            <div class="control-group"> 
                              <!-- Text input-->
                              <label class="control-label" for="input01"></label>
                              <div class="controls"> <a href="#" class="btn btn-success"><i class="icon-white icon-plus"></i>Upload and add attachment</a> </div>
                            </div>
                            
                            </fieldset>
                            */
                            ?>
                          
                        </div>
                        
                      </div>
                      
                      <!-- Special Review -->
                      <div class="box">
                        <div class="boxHeader expandControl opened panelhead">
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
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                              <tr>
                                <td>Animal Usage</td>
                                <td>Pending</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                            </tbody>
                          </table>
                          
                        </div>
                      </div>
                      
                       <!-- Budget Reports -->
                      <div class="box">
                        <div class="boxHeader expandControl opened panelhead">
                          <h3>Budget Reports</h3>
                        </div>
                        <div class="boxContent expandTarget">
                        
                          <div class="boxSubheader">
                            <h4>Budget Reports</h4>
                          </div>
                          <table class="table table-striped table-bordered table-condensed">
                            <thead>
                              <tr>
                                <th>Report Title</th>
                                <th>Include Comments</th>
                                <th style="width: 35px;"></th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>Budget Summary by Period</td>
                                <td><input type="checkbox" value="true" name="group"></td>
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                              <tr>
                                <td>Cost Sharing Summary by Period</td>
                                <td><input type="checkbox" value="true" name="group"></td>
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                              <tr>
                                <td>Cumulative Budget</td>
                                <td><input type="checkbox" value="true" name="group"></td>
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                              <tr>
                                <td>Industrial Budget by Period</td>
                                <td><input type="checkbox" value="true" name="group"></td>
                                <td><a href="#" class="btn " role="button" data-toggle="modal">View</a></td>
                              </tr>
                            </tbody>
                          </table>
                          
                        </div>
                      </div>
                      
                       <!-- Proposal Print -->
                      <div class="box">
                        <div class="boxHeader expandControl opened panelhead">
                          <h3>Proposal Print</h3>
                        </div>
                        <div class="boxContent expandTarget">
                          
                          <div class="boxSubheader expandControl closed">
                            <h4 style="border-bottom:none;">Grants.Gov</h4>
                          </div>
                          <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                          </dl>
                          <div class="clearfix"></div>
                          
                          <div class="boxSubheader expandControl closed">
                            <h4 style="border-bottom:none;">Sponsor Form Packages</h4>
                          </div>
                          <dl class="table-display expandTarget" style="margin-top:-13px; display:none;">
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                            <dt>xxx</dt>
                            <dd>yyy</dd>
                          </dl>
                          <div class="clearfix"></div>
                          
                        </div>
                      </div>
                    
              	</div>
              	<div class="tab-pane" id="Submit">
              		
                    <!-- Submit -->
                    <div class="box">
                        <div class="boxHeader">
                          <h3>Workflow Actions</h3>
                        </div>
                        <div class="boxContent">
                          
                          <div class="well" style="padding-bottom: 19px !important; margin-bottom:30px;">
                            <h4>Proposal actions</h4>
                            <a href="#" class="btn btn-success">Approve</a>
                            <a href="#" class="btn btn-danger">Reject</a>
                            <a href="#" class="btn">Recall</a>
                            <a href="#" class="btn">Send Notification</a>
                            <a href="#" class="btn btn-inverse pull-right">Submit to Sponsor</a>
                          </div>
                          
                          <div class="boxSubheader">
                            <h4>Comments</h4>
                          </div>
                          <div style="margin-bottom:10px;"> <a href="#" class="btn addcomplianceprotocol"><i class="icon icon-plus"></i> Add Comment</a> </div>
                          <div id="dl3" style="display:block">
                            <table class="table table-striped table-bordered table-condensed">
                              <thead>
                                <tr>
                                  <th style="width:80px;">Date</th>
                                  <th style="width:125px;">Person</th>
                                  <th>Comment</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <td>03/22/2013</td>
                                  <td>Bart Niedner</td>
                                  <td>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sed lorem in risus ornare semper vitae sit amet mauris. Nam imperdiet sollicitudin odio vel convallis. Nullam non malesuada nisl. Nulla tempor fermentum ornare. Fusce sollicitudin lobortis arcu ut malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lacinia interdum eleifend. Cras nec hendrerit nulla. Nulla facilisi. In lectus arcu, consectetur vitae auctor in, condimentum at libero. Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. Suspendisse at quam tortor, ac pulvinar augue.</td>
                                </tr>
                                <tr>
                                  <td>03/21/2013</td>
                                  <td>David White</td>
                                  <td>Cras cursus pellentesque erat non adipiscing. Proin tincidunt suscipit metus a pharetra. Proin non odio ac erat viverra commodo. Duis malesuada sollicitudin lorem quis commodo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                  
                  
                  
                </div>
                    </div>
                    
              	</div>
            </div>
<script>
$(function () {
  $('#myTab a:first').tab('show');
})
</script>
          
          
                    
          
                          
          
        </div>
      </div>
      
      <div class="docControls"> 
          <a href="http://localhost/kuali/prototype-c/prop.instspecdata.3.php" class="btn"><i class="icon-chevron-left"></i> Back</a> 
          <!--<a href="#" class="btn btn-success">Print</a>
          <a href="#" class="btn btn-success">Submit Proposal</a> 
          <a href="prop.review.actions.php" class="btn ">Continue <i class="icon-white icon-chevron-right"></i></a> -->
      </div>
      
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
