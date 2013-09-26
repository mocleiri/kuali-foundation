<?php
# Variables
$page = 'compliance';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->

				
					<h3>Compliance</h3>
				

				
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Add compliance requirements for this proposal</legend>
							<div class="entry-row row" id="compliance_edit">
								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="compliance_type" class="control-label">Type:</label>
										<select name="compliance_type" id="compliance_type" class="form-control input-sm chzn">
											<option value="1">Human Subjects</option>
											<option value="2">Animal Usage</option>
											<option value="3">Recombinant DNA</option>
											<option value="4">Radioactive Isotopes</option>
											<option value="5">Biohazard Materials</option>
											<option value="6" selected="selected">International Programs</option>
											<option value="7">Space Change</option>
											<option value="8">TLO Review - No conflict (A)</option>
											<option value="9">TLO review - Reviewed, no conflict (B1)</option>
											<option value="10">TLO Review - Potential Conflict (B2)</option>
											<option value="11">TLO PR-Previously Reviewed</option>
											<option value="12">Foundation Relations</option>
										</select>
									</div>

									<div class="col-md-3">
										<label for="approval_status" class="control-label">Approval status:</label>
										<select name="approval_status" id="approval_status" class="form-control input-sm chzn">
											<option value="2" selected="selected">Approved</option>
											<option value="4">Exempt</option>
											<option value="6">Link to IACUC</option>
											<option value="5">Link to IRB</option>
											<option value="3">Not yet applied</option>
											<option value="1">Pending</option>
										</select>
									</div>

									<div class="col-md-2">
										<label for="protocol_number" class="control-label">Protocol #:</label>
										<input type="text" class="form-control input-sm" name="protocol_number" id="protocol_number" value="7236471AC" />
									</div>

									<div class="col-md-2">
										<label for="excemption" class="control-label">Exemption #:</label>
										<input type="text" class="form-control input-sm" name="excemption" id="excemption" value="888JJKDF" />
									</div>

									<div class="col-md-2">
										<button id="link_save" class="btn btn-success btn-xs">Save</button><br />
										<a id="link_cancel" class="link-edit" href="#">Cancel</a>
									</div>
								</div>

								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="app_date" class="control-label">Application date:</label>
										<input type="text" class="form-control input-sm" name="app_date" id="app_date" value="8/30/2013" />
									</div>

									<div class="col-md-3">
										<label for="approval_date" class="control-label">Approval date:</label>
										<input type="text" class="form-control input-sm" name="approval_date" id="approval_date" value="8/30/2013" />
									</div>

									<div class="col-md-3">
										<label for="exp_date" class="control-label">Expiration date:</label>
										<input type="text" class="form-control input-sm" name="exp_date" id="exp_date" value="9/1/2014" />
									</div>
								</div>
							</div>

							<div class="entry-row row" id="compliance_1">
								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="compliance_type" class="control-label">Type:</label><br />
										International Programs
									</div>

									<div class="col-md-3">
										<label for="approval_status" class="control-label">Approval status:</label>
										Approved
									</div>

									<div class="col-md-2">
										<label for="protocol_number" class="control-label">Protocol #:</label>
										7236471AC
									</div>

									<div class="col-md-2">
										<label for="excemption" class="control-label">Exemption #:</label>
										888JJKDF
									</div>

									<div class="col-md-2">
										<a id="link_delete" class="link-delete" href="#">Delete</a><br />
										<a id="link_edit" class="link-edit" href="#">Edit</a>
									</div>
								</div>

								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="app_date" class="control-label">Application date:</label>
										8/30/2013
									</div>

									<div class="col-md-3">
										<label for="approval_date" class="control-label">Approval date:</label><br />
										8/30/2013
									</div>

									<div class="col-md-3">
										<label for="exp_date" class="control-label">Expiration date:</label><br />
										9/1/2014
									</div>
								</div>
							</div>

							<div class="entry-row row" id="compliance_2">
								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="compliance_type" class="control-label">Type:</label>
										<select name="compliance_type" id="compliance_type" class="form-control input-sm chzn">
											<option value="1">Human Subjects</option>
											<option value="2">Animal Usage</option>
											<option value="3">Recombinant DNA</option>
											<option value="4">Radioactive Isotopes</option>
											<option value="5">Biohazard Materials</option>
											<option value="6">International Programs</option>
											<option value="7">Space Change</option>
											<option value="8">TLO Review - No conflict (A)</option>
											<option value="9">TLO review - Reviewed, no conflict (B1)</option>
											<option value="10">TLO Review - Potential Conflict (B2)</option>
											<option value="11">TLO PR-Previously Reviewed</option>
											<option value="12">Foundation Relations</option>
										</select>
									</div>

									<div class="col-md-3">
										<label for="approval_status" class="control-label">Approval status:</label>
										<select name="approval_status" id="approval_status" class="form-control input-sm chzn">
											<option value="2">Approved</option>
											<option value="4">Exempt</option>
											<option value="6">Link to IACUC</option>
											<option value="5">Link to IRB</option>
											<option value="3">Not yet applied</option>
											<option value="1">Pending</option>
										</select>
									</div>

									<div class="col-md-2">
										<label for="protocol_number" class="control-label">Protocol #:</label>
										<input type="text" class="form-control input-sm" name="protocol_number" id="protocol_number" />
									</div>

									<div class="col-md-2">
										<label for="excemption" class="control-label">Exemption #:</label>
										<input type="text" class="form-control input-sm" name="excemption" id="excemption" />
									</div>
								</div>

								<div class="entry-row-internal">
									<div class="col-md-3">
										<label for="app_date" class="control-label">Application date:</label>
										<input type="text" class="form-control input-sm" name="app_date" id="app_date" />
									</div>

									<div class="col-md-3">
										<label for="approval_date" class="control-label">Approval date:</label>
										<input type="text" class="form-control input-sm" name="approval_date" id="approval_date" />
									</div>

									<div class="col-md-3">
										<label for="exp_date" class="control-label">Expiration date:</label>
										<input type="text" class="form-control input-sm" name="exp_date" id="exp_date" />
									</div>
								</div>
							</div>
						</fieldset>

						<button class="btn btn-success" id="compliance_add">+ Add entry</button>
					</form>
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter" style="position:fixed; left: 0; bottom: 0px; width:100%"> 
          
         
		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.attachments.proposal.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>








<script type="text/javascript">
$('#compliance_2').hide();
$('#compliance_edit').hide();

$('#compliance_add').click(function(e) {
	e.preventDefault();
	$('#compliance_2').show();
});

$('#link_edit').click(function(e) {
	e.preventDefault();
	$('#compliance_1').hide();
	$('#compliance_edit').show();
});

$('#link_delete').click(function(e) {
	e.preventDefault();
	$('#compliance_1').fadeOut();
});

$('#link_save').click(function(e) {
	e.preventDefault();
	$('#compliance_edit').hide();
	$('#compliance_1').show();
});

$('#link_cancel').click(function(e) {
	e.preventDefault();
	$('#compliance_edit').hide();
	$('#compliance_1').show();
});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>