<?php
# Variables
$section = 'basics';
$page = 'basics-search';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
	
	<div class="container">

		<div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
			  	<div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Opportunity Search</h2>
				</div>

				<div class="section-content">

			        <div class="well">
	            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup-oppsearch.html" data-modal-height="500">Change opportunity</button>
	            		<button class="btn btn-default" href="prop.basics.oppsearch-search.php">Remove opportunity</button>
	            	</div>

	                <div id="oppsearch-tabs">
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
								<table  class="table table-condensed">
									<tbody>
										<tr>
										<th style="width:30%"> Recieved Date</th>
										<td> 8/13/13 7:28 PM</td>
										</tr>
										<tr>
										<th style="width:30%"> Last Modified Date</th>
										<td> 8/13/13 7:29 PM</td>
										</tr>
										<tr>
										<th> Status</th>
										<td><a href="#" id="opp-status" data-type="select" data-pk="1" data-url="/post" data-title="Submission Details Status">VALIDATED</a></td>
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
								</table>
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
	                </div>
	            </div>

				<!-- // -->
					
			</div>

		</div>

	</div>

</section>

<div class="page-controls clearfix">
	<div class="page-actions">
		<div class="well"></div>
	</div>

	<div class="page-navigation">
		<div class="well">
			<button class="btn btn-default">Save</button>
			<button class="btn btn-primary" href="prop.basics.sponsor.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>