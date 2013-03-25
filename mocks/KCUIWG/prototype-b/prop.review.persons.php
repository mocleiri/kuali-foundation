<?php
# Variables
$page = 'review-persons';
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
       <div class="boxHeader">
            <h3>Key Persons</h3>
          </div>
          
          <!-- <div class="boxHeader expandControl">
            <h3>Key Personnel</h3>
          </div>-->
          <div class="boxContent expandTarget" style="" >
            <div class="tabbable tabs-left clearfix">
              
              <!--   <div class="boxSubheader">
              <h4>About this page</h4>
              
            </div>-->
              <!-- Only required for left/right tabs -->
            
            <ul class="nav nav-tabs" style="display:block" >
              <li class="active">
                <a href="#art" data-toggle="tab">Haskell, Edward <small>(PI)</small></a>
              </li>
            </ul>


              <div class="tab-content">
                <div class="tab-pane active" id="art">
                  <div class="boxSubheader">
                    <h5 style="font-size:12px">Haskell, Edward <small>(PI)</small></h5>
                    <!-- <div class="boxControls">
                      <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-reorderpersonnel/frame.html">Reorder</a> | <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-deletepersonnel/frame.html">Delete</a>
                    </div> -->
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4> Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <dl class="table-display" style="margin-top:-13px;">
                      <!--<dt>Proposal Person Role Id</dt>
                      <dd>&nbsp;
                      </dd>-->
                      <dt>Full Name</dt>
                      <dd>
                        <a href="#" id="username" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Full Name">Haskell, Edward</a>
                      </dd>
                      <dt>User Name </dt>
                      <dd>haskelledw</dd>
                      <dt>Email Address</dt>
                      <dd>
                        <a href="#" id="email" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Email">haskelledw@inst.com</a>
                      </dd>
                      <dt>Office Phone</dt>
                      <dd>
                        <a href="#" id="phone" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Office Phone">917-422-5934</a>
                      </dd>
                      <!--<dt>Primary Title</dt>
                      <dd>
                        <a href="#" id="prititle" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Primary Title">+ Add</a>
                      </dd>
                      <dt>Directory Title</dt>
                      <dd>
                        <a href="#" id="dirtitle" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Directory Title">+ Add</a>
                      </dd>-->
                      <dt>Home Unit</dt>
                      <dd>UA-VPIT</dd>
                      <dt>Division</dt>
                      <dd>UNIVERSITY ADMINISTRATION </dd>
                      <!--<dt>Fax</dt>
                      <dd>
                        <a href="#" id="fax" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Fax">+ Add</a>
                      </dd>
                      <dt>Pager</dt>
                      <dd>
                        <a href="#" id="pager" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Pager">+ Add</a>
                      </dd>
                      <dt>Mobile</dt>
                      <dd>
                        <a href="#" id="mobile" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Mobile">+ Add</a>
                      </dd>
                      <dt>Office Location</dt>
                      <dd>
                        <a href="#" id="officelocation" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Office Location">+ Add</a>
                      </dd>
                      <dt>Secondary Office Location</dt>
                      <dd>
                        <a href="#" id="officelocation2" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Secondary Office Location">+ Add</a>
                      </dd>-->
                      <dt>Address Line 1</dt>
                      <dd>
                        <a href="#" id="+ Address1" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 1">CIB, 2709 E 10th Street</a>
                      </dd>
                      <!--<dt>Address Line 2</dt>
                      <dd>
                        <a href="#" id="+ Address2" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 2">+ Add</a>
                      </dd>
                      <dt>Address Line 3</dt>
                      <dd>
                        <a href="#" id="+ Address3" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Address Line 3">+ Add</a>
                      </dd>-->
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
                      <!--<dt>Education Level</dt>
                      <dd>
                        <a href="#" id="edlevel" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Education Level">+ Add</a>
                      </dd>
                      <dt>Year Graduated</dt>
                      <dd>
                        <a href="#" id="gradyear" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Year Graduated">+ Add</a>
                      </dd>
                      <dt>Major</dt>
                      <dd>
                        <a href="#" id="major" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Major">+ Add</a>
                      </dd>
                      <dt>Degree</dt>
                      <dd>
                        <a href="#" id="degree" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Degree">+ Add</a>
                      </dd>
                      <dt>School</dt>
                      <dd>
                        <a href="#" id="school" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter School">+ Add</a>
                      </dd>-->
                    </dl>
                    <div class="clearfix"></div>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Extended Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <p style="font-style:italic;">No expanded details have been provided.</p>
                    <!--<dl class="table-display" style="margin-top:-13px;">
                      <dt>Age by Fiscal Year </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Race </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Education Level </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Degree </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Major </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>KcPerson Id </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Is Handicapped </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Handicap Type </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Veteran </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Veteran Type </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Has Visa </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Visa Code </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Visa Type </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Visa Renewal Date </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Office Location </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Secondary Office Location </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>School </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Year Graduated </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Directory Department </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Primary Title </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Directory Title </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Is Vacation Accrual </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Is on Sabbatical </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>County </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Id Provided </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Id Verified </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Version Number </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                      <dt>Citzenship Type </dt>
                      <dd>
                        <a href="#" >+ Add</a>
                      </dd>
                    </dl>-->
                    <div class="clearfix"></div>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Degrees</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <table class="table table-striped table-bordered table-condensed">
                      <thead>
                        <tr>
                          <th>Degree Type</th>
                          <th>Description</th>
                          <th>Graduation Year</th>
                          <th>School</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>Doctor of Veterinary Medicine</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2010</td>
                          <td>Michigan State University</td>
                        </tr>
                        <tr>
                          <td>Master of Education</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2008</td>
                          <td>Stanislaus State University</td>
                        </tr>
                        <tr>
                          <td>Bachelor of Education</td>
                          <td>Deus meus, recorder in gratiarum actione tibi, et confitear misericordias tuas super me. perfundantur ossa mea dilectione tua,</td>
                          <td>2004</td>
                          <td>Cornell University</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="boxSubheader expandControl closed">
                    <h4>Unit Details</h4>
                  </div>
                  <div class="expandTarget" style="display:none">
                    <table class="table table-striped table-bordered table-condensed">
                      <thead>
                        <tr>
                          <th>Unit Name</th>
                          <th>Unit Number</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>ARCHIVES OF TRADITIONAL MUSIC</td>
                          <td>BL-ARVM</td>
                        </tr>
                        <tr>
                          <td>BLACK CULTURE CTR LIBRARY</td>
                          <td>BL-BCLB</td>
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
          </div>
          
        </div>
        <div style=" padd12px; text-align:center">
          <a href="prop.basics.keywords.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.keypersonnel.creditallocation.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
      </div>
    </div>
  </form>
</div>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>