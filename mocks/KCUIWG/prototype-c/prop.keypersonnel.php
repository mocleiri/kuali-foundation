<?php
# Variables
$page = 'personnel-pers';
?>
<?php require_once( 'assets/inc/head.php' ) ?>








<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container"> <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
          <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>
        <div class="span10 content">
           <h3>Key Personnel</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
             
              
            
              
              <!-- <div class="boxHeader expandControl">
            <h3>Key Personnel</h3>
          </div>-->
              
              <div class="tabbable tabs-left clearfix">
              
              <!--   <div class="boxSubheader">
              <h4>About this page</h4>
              
            </div>-->
              <!-- Only required for left/right tabs -->
<p>            
            Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).</p> <hr>
          <!--    <div class="boxSubheader">
                <h4>Personnel</h4>
              </div>-->

          <ul class="nav nav-tabs" style="display:block" >
                <li class="active">
                  <a href="#art" data-toggle="tab">Haskell, Edward <small>(PI)</small></a>
                </li>
                <li >
                  <a href="#ben" data-toggle="tab">Hensler, Judith</a>
                </li>
                <li>
                  <a href="#ella" data-toggle="tab">Rutherford, Violet</a>
                </li>
                <li>
                  <a href="#" class="ajax-modal notab" data-backdrop="true" data-controls-modal="addemployee" data-keyboard="true" url="modal-addpersonnel/frame.html"><small><em>add person...</em></small></a>
                </li>
              </ul>
              <div class="tab-content">
                <div class="tab-pane active" id="art">
                  <div class="boxSubheader">
                    <h5 style="font-size:12px">Haskell, Edward <small>(PI)</small></h5>
                    <div class="boxControls">
                      <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-reorderpersonnel/frame.html">reorder</a> | <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-deletepersonnel/frame.html">delete</a>
                    </div>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <dl class="table-display" style="margin-top:-13px;">
                      <dt>Proposal Person Role Id</dt>
                      <dd>&nbsp;
                      </dd>
                      <dt>Full Name</dt>
                      <dd>
                        <a href="#" id="username" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Full Name">Tatum, Arthur</a>
                      </dd>
                      <dt>User Name </dt>
                      <dd>arttatum</dd>
                      <dt>Email Address</dt>
                      <dd>
                        <a href="#" id="email" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Email">arttatum@indiana.edu</a>
                      </dd>
                      <dt>Office Phone</dt>
                      <dd>
                        <a href="#" id="phone" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Office Phone">(812) 855-7441</a>
                      </dd>
                      <dt>Primary Title</dt>
                      <dd>
                        <a href="#" id="prititle" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Primary Title">add</a>
                      </dd>
                      <dt>Directory Title</dt>
                      <dd>
                        <a href="#" id="dirtitle" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Directory Title">add</a>
                      </dd>
                      <dt>Home Unit</dt>
                      <dd>UA-VPIT</dd>
                      <dt>Division</dt>
                      <dd>UNIVERSITY ADMINISTRATION </dd>
                      <dt>Fax</dt>
                      <dd>
                        <a href="#" id="fax" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Fax">add</a>
                      </dd>
                      <dt>Pager</dt>
                      <dd>
                        <a href="#" id="pager" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Pager">add</a>
                      </dd>
                      <dt>Mobile</dt>
                      <dd>
                        <a href="#" id="mobile" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Mobile">add</a>
                      </dd>
                      <dt>Office Location</dt>
                      <dd>
                        <a href="#" id="officelocation" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Office Location">add</a>
                      </dd>
                      <dt>Secondary Office Location</dt>
                      <dd>
                        <a href="#" id="officelocation2" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Secondary Office Location">add</a>
                      </dd>
                      <dt>Address Line 1</dt>
                      <dd>
                        <a href="#" id="address1" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 1">CIB, 2709 E 10th Street</a>
                      </dd>
                      <dt>Address Line 2</dt>
                      <dd>
                        <a href="#" id="address2" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 2">add</a>
                      </dd>
                      <dt>Address Line 3</dt>
                      <dd>
                        <a href="#" id="address3" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 3">add</a>
                      </dd>
                      <dt>City</dt>
                      <dd>
                        <a href="#" id="city" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter City">Bloomington</a>
                      </dd>
                      <dt>County</dt>
                      <dd>
                        <a href="#" id="county" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter County">Monroe</a>
                      </dd>
                      <dt>State</dt>
                      <dd>IN</dd>
                      <dt>Postal Code</dt>
                      <dd>
                        <a href="#" id="zip" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Postal Code">47408</a>
                      </dd>
                      <dt>Country</dt>
                      <dd>USA</dd>
                      <dt>Faculty</dt>
                      <dd>Yes</dd>
                      <dt>Education Level</dt>
                      <dd>
                        <a href="#" id="edlevel" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Education Level">add</a>
                      </dd>
                      <dt>Year Graduated</dt>
                      <dd>
                        <a href="#" id="gradyear" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Year Graduated">add</a>
                      </dd>
                      <dt>Major</dt>
                      <dd>
                        <a href="#" id="major" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Major">add</a>
                      </dd>
                      <dt>Degree</dt>
                      <dd>
                        <a href="#" id="degree" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Degree">add</a>
                      </dd>
                      <dt>School</dt>
                      <dd>
                        <a href="#" id="school" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter School">add</a>
                      </dd>
                    </dl>
                    <div class="clearfix"></div>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Extended Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <dl class="table-display" style="margin-top:-13px;">
                      <dt>Age by Fiscal Year </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Race </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Education Level </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Degree </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Major </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>KcPerson Id </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Is Handicapped </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Handicap Type </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Veteran </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Veteran Type </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Has Visa </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Visa Code </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Visa Type </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Visa Renewal Date </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Office Location </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Secondary Office Location </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>School </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Year Graduated </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Directory Department </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Primary Title </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Directory Title </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Is Vacation Accrual </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Is on Sabbatical </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>County </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Id Provided </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Id Verified </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Version Number </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                      <dt>Citzenship Type </dt>
                      <dd>
                        <a href="#" >add</a>
                      </dd>
                    </dl>
                    <div class="clearfix"></div>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Degrees</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                       <table class="table table-condensed table-hover">
                      <thead>
                        <tr>
                          <th>Degree Type</th>
                          <th>Description</th>
                          <th>Graduation Year</th>
                          <th>School</th>
                          <th style="width: 36px;"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td><select name="select" class=" input-small" title="* Degree Type">
                              <option value="">select</option>
                              <option value="AS">Associate in Science</option>
                              <option value="BA">Bachelor of Arts</option>
                              <option value="BComm">Bachelor of Commerce (Canada)</option>
                              <option value="BEd">Bachelor of Education</option>
                              <option value="BS">Bachelor of Science</option>
                              <option value="DA">Doctor of Arts</option>
                              <option value="DC">Doctor of Chiropractics</option>
                              <option value="DDS">Doctor of Dental Science</option>
                              <option value="DD">Doctor of Divinity</option>
                              <option value="DEng">Doctor of Engineering</option>
                              <option value="DFA">Doctor of Fine Arts</option>
                              <option value="DHA">Doctor of Hospital Administration</option>
                              <option value="DH">Doctor of Humanities</option>
                              <option value="DMin">Doctor of Ministry</option>
                              <option value="PharD">Doctor of Pharmacy</option>
                              <option value="PhD">Doctor of Philosophy</option>
                              <option value="DPA">Doctor of Public Administration</option>
                              <option value="ScD">Doctor of Science</option>
                              <option value="ThD">Doctor of Theology, in Religion</option>
                              <option value="DVM">Doctor of Veterinary Medicine</option>
                              <option value="DVS">Doctor of Veterinary Science</option>
                              <option value="DSN">Doctorate of Science, in Nursing</option>
                              <option value="HS">High School (or GED equivalent)</option>
                              <option value="JD">Jurum Doctor (Doctor of Laws)</option>
                              <option value="LLD">Legum Doctor (Doctor of Laws)</option>
                              <option value="LLM">Legum Magister (Master of Laws)</option>
                              <option value="MArch">Master of Architecture</option>
                              <option value="MA">Master of Arts</option>
                              <option value="MAEd">Master of Arts, in Education</option>
                              <option value="MBA">Master of Business Administration</option>
                              <option value="MDiv">Master of Divinity</option>
                              <option value="MEd">Master of Education</option>
                              <option value="MEE">Master of Electrical Engineering</option>
                              <option value="MEng">Master of Engineering</option>
                              <option value="MFA">Master of Fine Arts</option>
                              <option value="MIS">Master of Information Systems</option>
                              <option value="MLS">Master of Library Science</option>
                              <option value="MDS">Master of Medical Science</option>
                              <option value="MPd">Master of Pedagogy</option>
                              <option value="MPhil">Master of Philosophy</option>
                              <option value="MPA">Master of Public Administration</option>
                              <option value="MPE">Master of Public Education</option>
                              <option value="MPH">Master of Public Health</option>
                              <option value="MST">Master of Sacred Theology</option>
                              <option value="MS">Master of Science</option>
                              <option value="MSEd">Master of Science, in Education</option>
                              <option value="MSW">Master of Social Work</option>
                              <option value="MTh">Master of Theology, in Religion</option>
                              <option value="MD">Medical Doctor</option>
                              <option value="UKNW">No Degree information specified</option>
                            </select></td>
                          <td><input type="text" class=" input-medium" name="textfield" value="asdf" id="textfield2" tabindex="1" /></td>
                          <td><input type="text" class=" input-small" name="textfield5" id="textfield3" tabindex="1" /></td>
                          <td><input type="text" class=" input-medium" name="textfield6" id="textfield4" tabindex="1" /></td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-plus"></i></a></td>
                        </tr>
                        <tr>
                          <td>Doctor of Veterinary Medicine</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2010</td>
                          <td>Michigan State University</td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                        </tr>
                        <tr>
                          <td>Master of Education</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2008</td>
                          <td>Stanislaus State University</td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                        </tr>
                        <tr>
                          <td>Bachelor of Education</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2004</td>
                          <td>Cornell University</td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Unit Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                       <table class="table table-condensed table-hover">
                      <thead>
                        <tr>
                          <th>Unit Name</th>
                          <th>Unit Number</th>
                          <th style="width: 36px;"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td><input type="text" class=" input-medium" name="textfield2"  tabindex="1" /></td>
                          <td><input type="text" class=" input-medium" name="textfield"  tabindex="1" /></td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-plus"></i></a></td>
                        </tr>
                        <tr>
                          <td>ARCHIVES OF TRADITIONAL MUSIC</td>
                          <td>BL-ARVM</td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                        </tr>
                        <tr>
                          <td>BLACK CULTURE CTR LIBRARY</td>
                          <td>BL-BCLB</td>
                          <td><a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Proposal Person Certification (Incomplete)</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <fieldset class="question">
                      <legend>I certify that (1) all information provided in this request or application is true, complete and accurate to the best of my knowledge and (2) I understand that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil or administrative penalties.</legend>
                      <div class="questionControls">
                        <input type="radio" name="the_question1"  value="1">
                        <label for="the_question_yes" class="radio inline">Yes</label>
                        <input type="radio" name="the_question1"  value="0">
                        <label for="the_question_no" class="radio inline">No</label>
                      </div>
                    </fieldset>
                    <fieldset class="question">
                      <legend>I certify that I agree to accept responsibility for the scientific conduct of the project, to provide the required progress reports, and to comply with the terms and conditions of the sponsoring agency.</legend>
                      <div class="questionControls">
                        <input type="radio" name="the_question2"  value="1">
                        <label for="the_question_yes" class="radio inline">Yes</label>
                        <input type="radio" name="the_question2"  value="0">
                        <label for="the_question_no" class="radio inline">No</label>
                      </div>
                    </fieldset>
                    <fieldset class="question">
                      <legend>Do you have a financial conflict of interest related to this project? For help, see http://researchadmin.iu.edu/COI/coi_home.html</legend>
                      <div class="questionControls">
                        <input type="radio" name="the_question3"  value="1">
                        <label for="the_question_yes" class="radio inline">Yes</label>
                        <input type="radio" name="the_question3"  value="0">
                        <label for="the_question_no" class="radio inline">No</label>
                      </div>
                    </fieldset>
                    <fieldset class="question">
                      <legend>If application is to a federal or federal pass-through sponsor, have any lobbying activities been or will any be conducted regarding this proposal?</legend>
                      <div class="questionControls">
                        <input type="radio" name="the_question4"  value="1">
                        <label for="the_question_yes" class="radio inline">Yes</label>
                        <input type="radio" name="the_question4"  value="0">
                        <label for="the_question_no" class="radio inline">No</label>
                      </div>
                    </fieldset>
                    <fieldset class="question">
                      <legend>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</legend>
                      <div class="questionControls">
                        <input type="radio" name="the_question5"  value="1">
                        <label for="the_question_yes" class="radio inline">Yes</label>
                        <input type="radio" name="the_question5"  value="0">
                        <label for="the_question_no" class="radio inline">No</label>
                      </div>
                    </fieldset>
                  </div>
                </div>
                <div class="tab-pane " id="ben">
                
                  
                  
                  
                   <div class="boxSubheader">
                    <h5 style="font-size:12px">Hensler, Judith</h5>
                    <div class="boxControls">
                      <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-reorderpersonnel/frame.html">reorder</a> | <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-deletepersonnel/frame.html">delete</a>
                    </div>
                  </div>
                  
                  
                  
                  
                  
                  <div class="boxSubheader expandControl closed">
                    <h4> Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Extended Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Degrees</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Unit Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Proposal Person Certification (Incomplete)</h4>
                  </div>
                </div>
              <div class="tab-pane " id="ella">
                
                  
                  
                  
                   <div class="boxSubheader">
                    <h5 style="font-size:12px">Rutherford, Violet</h5>
                    <div class="boxControls">
                      <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-reorderpersonnel/frame.html">reorder</a> | <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-deletepersonnel/frame.html">delete</a>
                    </div>
                  </div>
                  
                  
                  
                  
                  
                  <div class="boxSubheader expandControl closed">
                    <h4> Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Extended Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Degrees</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Unit Details</h4>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Proposal Person Certification (Incomplete)</h4>
                  </div>
                </div>
              </div>
            </div>
              <div class="control-group buttons">
                <label class="control-label"></label>
                
                <!-- Button -->
                <!-- Button -->
                <div class="controls">
                  <a href="prop.basics.keywords.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
            <a href="#" class="btn">Save</a>
            <a href="prop.keypersonnel.creditintel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
                </div>
              </div>
            </form>
          </div>
          
          
          <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        </div>
      </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
