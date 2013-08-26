<?php
# Variables
$page = 'basics-grantsgov';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<script>
    $(document).ready(function () {
        $('#opp-title').editable();
        $('#opp-subtype').editable({
            value: 1,
            source: [
                {
                    value: 1,
                    text: 'Application'
                },
                {
                    value: 2,
                    text: 'Preapplication'
                },
                {
                    value: 3,
                    text: 'Change/Corrected'
                }
           ]

        });
        $('#opp-revisiontype').editable({
            value: 5,
            source: [
                {
                    value: 1,
                    text: 'Decrease Award'
                },
                {
                    value: 2,
                    text: 'Decrease Award & Decrease Duration'
                },
                {
                    value: 3,
                    text: 'Decrease Award & Increase Duration'
                }, {
                    value: 4,
                    text: 'Decrease Duration'
                }, {
                    value: 5,
                    text: 'Increase Award'
                }, {
                    value: 6,
                    text: 'Increase Award & Decrease Duration'
                }, {
                    value: 7,
                    text: 'Increase Award & Increase Duration'
                }, {
                    value: 8,
                    text: 'Increase Duration'
                },
           ]

        });
    });
</script>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">
      <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="leftnavContent">
        <?php require_once( 'assets/inc/document-nav.php' ) ?>
        <div id="content" role="application">
          <div class="row-fluid">
            <div class="span12 content">
              <h3>Opportunity Search</h3>
              <div class="well well-small">
                <h5 style="margin-top:0px;">Opportunity actions</h5>
                <a href="#"id="addopp" class="btn btn-small">Load New...</a> <a href="prop.basics.oppsearch-search.php" class="btn btn-small">Remove Current</a> </div>
              <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                <li class="active"><a href="#opp-details" data-toggle="tab">Opportunity</a></li>
                <li><a href="#opp-forms" data-toggle="tab">Forms</a></li>
                <li><a href="#opp-submission" data-toggle="tab">Submission Details</a></li>
              </ul>
              <div id="my-tab-content" class="tab-content">
                <div class="tab-pane active" id="opp-details">
                  <table  class="table table-condensed">
                    <tbody>
                      <tr>
                        <th style="width:30%"> Opportunity ID</th>
                        <td> CSS-120809-SF424RR-V12 </td>
                      </tr>
                      <tr>
                        <th> Opportunity Title</th>
                        <td><a href="#" id="opp-title" data-type="text" data-pk="1" data-url="/post" data-title="Opportunity Title">testing new SF424 RR form</a></td>
                      </tr>
                      <tr>
                        <th> Submission Type</th>
                        <td><a href="#" id="opp-subtype" data-type="select" data-pk="1" data-url="/post" data-title=" Submission Type">Application</a></td>
                      </tr>
                      <tr>
                        <th>Revision Type</th>
                        <td><a href="#" id="opp-revisiontype" data-type="select" data-pk="1" data-url="/post" data-title="Revision Type">Increase Award</a></td>
                      </tr>
                      <tr>
                        <th> CFDA Number</th>
                        <td> 00.000 </td>
                      </tr>
                      <tr>
                        <th> Competition Id</th>
                        <td> NEWRRFORM </td>
                      </tr>
                      <tr>
                        <th> Opening Date</th>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th> Closing Date</th>
                        <td> 12/31/2014 12:00 AM </td>
                      </tr>
                      <tr>
                        <th> Instruction Page</th>
                        <td><a href="http://at07apply.grants.gov/apply/opportunities/instructions/oppCSS-120809-SF424RR-V12-cfda00.000-cidNEWRRFORM-instructions.doc" target="_blank">download</a></td>
                      </tr>
                      <tr>
                        <th> Schema URL</th>
                        <td><a href="http://at07apply.grants.gov/apply/opportunities/schemas/applicant/oppCSS-120809-SF424RR-V12-cfda00.000-cidNEWRRFORM.xsd" target="_blank">view</a></td>
                      </tr>
                      <tr>
                        <th>Opportunity Search Domain</th>
                        <td>Grants.Gov&nbsp;</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="tab-pane" id="opp-submission">
                  <p>Submission details will be available after the proposal is submitted.</p>
                </div>
                <div class="tab-pane" id="opp-forms">
                  <table  class="table table-condensed table-striped table-hover">
                    <tbody>
                      <tr>
                        <th> Form Name </th>
                        <th> Mandatory </th>
                        <th> <div class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">Include</a>
                            <ul class="dropdown-menu" style="  text-align:left;" >
                              <li><a href="#">Select All</a></li>
                              <li><a href="#">Select None</a></li>
                            </ul>
                          </div>
                        </th>
                        <th> Desc </th>
                        <th style="width:80px"> <div class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">PDF</a>
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
                          
                          <input type="checkbox" tabindex="15"  title="selectToPrint"></td>
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
                        <td ><!--  <input type="checkbox" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[17].selectToPrint" value="on">--> 
                          
                          <a class="btn btn-small" href="#">Create PDF</a></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
    });
</script> 
              
              <!--<div class=" formfields">
                <form  class="form-horizontal" method="get" action="">
                  <fieldset style="">
                
                    <div class="control-group">
                      <label class="control-label" for="orgdocnum2"> Search Domain</label>
                      <div class="controls">
                        <select name="select" id="activityType" class="input-medium" title="Activity Type" required="required">
                          <option value="" selected="selected">select</option>
                          <option value="4" >Grants.Gov</option>
                          <option value="9">Research.Gov</option>
                        </select>
                        <a href="#" id="" class="btn lookup"><span>lookup</span></a> </div>
                    </div>
                  
                    <div class="control-group">
                      <label class="control-label" for="Description">Opportunity ID</label>
                      <div class="controls">
                        <input type="text" id="Description" placeholder="" class=" input-medium">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="Description">CFDA Number</label>
                      <div class="controls">
                        <input type="text" id="Description" placeholder="" class="input-medium">
                      </div>
                    </div><div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-small btn-default">Search</button>
  </div>
</div>
                  </fieldset>
                </form>
              </div>-->
              
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.basics.details.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.basics.sponsor.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a></div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<script>
$(document).ready(function() {
    $("#addopp").on('click', function() {
        $.fancybox.open({
            href: 'modal/lookup-oppsearch.html',
            type: 'iframe',
            padding: 0,
            width: 800,
        });
    });
});
</script>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
