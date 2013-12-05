<?php   session_start();

if(isset($_SESSION['select-opportunity']) && $_SESSION['select-opportunity'] == 1){
    // header('Location: prop.basics.oppsearch-search-done.php');
}

# Variables
$section = 'basics';
$page = 'basics-search';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1"> <!-- Main content goes here -->
        
        <div class="section-title">
          <h3>S2S Opportunity Search</h3>
        </div>

        <div class="uif-toolbar">
          <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-oppsearch.php" data-modal-height="500"><span aria-hidden="true" class="icon-exchange"></span> Change opportunity</button>
          <button class="btn btn-default btn-xs" href="prop.basics.oppsearch-search.php"><span aria-hidden="true" class="icon-remove"></span> Remove opportunity</button>
        </div>

        <div id="oppsearch-tabs">
          <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#opp-details" data-toggle="tab">Opportunity</a></li>
            <li><a href="#opp-forms" data-toggle="tab">Forms</a></li>
            <li><a href="#opp-submission" data-toggle="tab">Submission Details</a></li>
          </ul>
          <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active" id="opp-details">
              <h4>Opportunity</h4>
              <table  class="table table-condensed">
                <tbody>
                  <tr>
                    <th class="col-md-3"> Opportunity ID</th>
                    <td>PA-13-302</td>
                  </tr>
                  <tr>
                    <th>Opportunity Title</th>
                    <td>Parent R01</td>
                  </tr>
                  <tr>
                    <th>Submission Type</th>
                    <td><select name="document.developmentProposalList[0].s2sOpportunity.s2sSubmissionTypeCode" tabindex="0">
                        <option value="2">Application</option>
                        <option value="1">Preapplication</option>
                        <option value="3">Change/Corrected Application</option>
                      </select></td>
                  </tr>
                  <tr>
                    <th>Revision Type</th>
                    <td><select class="has-other" name="revision-type" id="revision-type">
                        <option></option>
                        <option value="B">Decrease Award</option>
                        <option value="BD">Decrease Award &amp; Decrease Duration</option>
                        <option value="BC">Decrease Award &amp; Increase Duration</option>
                        <option value="D">Decrease Duration</option>
                        <option value="A">Increase Award</option>
                        <option value="AD">Increase Award &amp; Decrease Duration</option>
                        <option value="AC">Increase Award &amp; Increase Duration</option>
                        <option value="C">Increase Duration</option>
                        <option value="OTHER">Other...</option>
                      </select>

                    </td>
                  </tr>
                  <tr>
                    <th>Prev Grants.gov tracking ID</th>
                    <td>
                      <input type="text" id="prev_grantsgov_tracking" name="prev_grantsgov_tracking" class="form-control input-sm" />
                    </td>
                  </tr>
                  <tr>
                    <th>Agency Routing Identifier</th>
                    <td><input type="text" id="agency_routing_identifier" name="agency_routing_identifier" class="form-control input-sm"></td>
                  </tr>
                  <tr>
                    <th>CFDA Number</th>
                    <td>00.000</td>
                  </tr>
                  <tr>
                    <th>Competition Id</th>
                    <td>NEWRRFORM </td>
                  </tr>
                  <tr>
                    <th>Opening Date</th>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <th>Closing Date</th>
                    <td>12/15/2018 5:00 PM</td>
                  </tr>
                  <tr>
                    <th>Instruction Page</th>
                    <td><a href="http://at07apply.grants.gov/apply/opportunities/instructions/oppCSS-120809-SF424RR-V12-cfda00.000-cidNEWRRFORM-instructions.doc" target="_blank">Download instructions</a></td>
                  </tr>
                  <tr>
                    <th>Schema URL</th>
                    <td><a href="http://at07apply.grants.gov/apply/opportunities/schemas/applicant/oppCSS-120809-SF424RR-V12-cfda00.000-cidNEWRRFORM.xsd" target="_blank">View schema</a></td>
                  </tr>
                  <tr>
                    <th>S2S Provider</th>
                    <td>Grants.gov</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="tab-pane" id="opp-submission">
              <h4>Submission Details</h4>
              <p>Submission details will be available after the proposal is submitted.</p>
              <!-- <table  class="table table-condensed">
                  <tbody>
                    <tr>
                    <th class="col-md-3"> Recieved Date</th>
                    <td> 8/13/13 7:28 PM</td>
                    </tr>
                    <tr>
                    <th class="col-md-3"> Last Modified Date</th>
                    <td> 8/13/13 7:29 PM</td>
                    </tr>
                    <tr>
                    <th> Status</th>
                    <td><a href="#" id="opp-status" data-type="select" data-pk="1" data-url="/post" data-title="Submission Details Status">Validated</a></td>
                    </tr>
                    <tr>
                    <th> S2S Tracking Id</th>
                    <td> GRANT00571006</td>
                    </tr>
                    <tr>
                    <th> Agency Tracking Id</th>
                    <td> </td>
                    </tr>
                    <tr>
                    <th> Comments</th>
                    <td> <a href="#" id="opp-comments" data-type="textarea" data-pk="1" data-url="/post" data-title="Submission Details Comments">Trying to submit to S2S</a></td>
                    </tr>
                    <tr>
                    <th> Attachments</th>
                    <td> N-1_Budget_Justification_Fed_NonFed</td>
                    </tr>
                  </tbody>
                </table> --> 
            </div>
            <div class="tab-pane" id="opp-forms">
              <h4>Forms</h4>
              <table class="table table-condensed table-striped table-hover">
                <tbody>
                  <tr>
                    <th><a href="#" class="" title="A collection of forms from your chosen sponsor/provider. Select the forms you want to be included in this proposal.">Form Name</a></th>
                    <th><a href="#" class="" title="Indicates those forms that are required for this proposal based on the sponsor or provider's requirements.">Mandatory</a></th>
                    <th> <div class="dropdown"> <a class="dropdown-toggle " data-toggle="dropdown" href="#" title="Check the box next to each additional form you want to be included in this proposal.">Include</a>
                        <ul class="dropdown-menu" style="  text-align:left;" >
                          <li><a href="#">Select All</a></li>
                          <li><a href="#">Select None</a></li>
                        </ul>
                      </div>
                    </th>
                    <th><a href="#" class="" title="Indicates whether a description is available for this form.">Description</a></th>
                    <th style="width:80px"> <div class="dropdown"> <a class="dropdown-toggle " data-toggle="dropdown" href="#" title="Check the box next to each form you want to be included in a single PDF printout.">Select</a>
                        <ul class="dropdown-menu" style="  text-align:left; left:-80px" >
                          <li><a href="#">Select All</a></li>
                          <li><a href="#">Select None</a></li>
                        </ul>
                      </div>
                    </th>
                  </tr>
                  <tr>
                    <td > RR_SF424_1_2-V1.2 </td>
                    <td> Yes </td>
                    <td> Yes </td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint" checked="checked" disabled></td>
                  </tr>
                  <tr>
                    <td > Attachments-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="18"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_Budget-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="23"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_Budget10-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="28"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFedBudget-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="33"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFedBudget10-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="38"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFed_SubawardBudget-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="43"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFedSubawardBudget10_10-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="48"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFed_SubawardBudget10_30-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="53"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_FedNonFed_SubawardBudget30-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="58"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_KeyPerson-V1.1 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="63"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_KeyPersonExpanded_1_2-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="68"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_OtherProjectInfo_1_3-V1.3 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="73"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_PersonalData_1_2-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="78"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_SubawardBudget-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="83"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_SubawardBudget10_10-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="88"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_SubawardBudget10_30-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="93"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td > RR_SubawardBudget30-V1.2 </td>
                    <td> No </td>
                    <td><input type="checkbox" tabindex="98"  title="Include"></td>
                    <td > Available </td>
                    <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[0].selectToPrint" value="on">-->
                      
                      <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
                  </tr>
                  <tr>
                    <td >&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td >&nbsp;</td>
                    <td align="center" ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[17].selectToPrint" value="on">-->
                      
                      <button class="btn btn-default btn-xs">Create PDF</button>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> 
          <div class="btn-row-page-action">
            <button href="prop.basics.details.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.basics.deliveryinfo.php" class="btn btn-primary">Save and Continue</button>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>