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

					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-6">
										<h4 class="panel-title">
											<a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span aria-hidden="true" class="icon-caret-right"></span> International Programs</a>
											<span>Approved</span>
											<span>8/30/2013</span>
											<span>7236471AC</span>
											<span>888JJKDF</span>
										</h4>
									</div>
									<div class="col-md-6">
										<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
									</div>
								</div>
							</div>
							<div id="collapse1" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="form-group clearfix">
										<label for="type" class="control-label col-md-3">Type:</label>
										<div class="col-md-9">
											<select name="type" id="type" class="form-control input-sm col-md-8">
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
									</div>

									<div class="form-group clearfix">
										<label for="approval_status" class="control-label col-md-3">Approval status:</label>
										<div class="col-md-9">
											<select name="type" id="approval_status" class="form-control input-sm col-md-8">
												<option value="1" selected="selected">Approved</option>
												<option value="2">Exempt</option>
												<option value="3">Link to IACUC</option>
												<option value="4">Link to IRB</option>
												<option value="5">Not yet applied</option>
												<option value="6">Pending</option>
											</select>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="protocol_no" class="control-label col-md-3">Protocol number:</label>
										<div class="col-md-9">
											<input type="text" name="protocol_no" id="protocol_no" class="form-control input-sm col-md-8" value="7236471AC" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="exemption_no" class="control-label col-md-3">Exemption number:</label>
										<div class="col-md-9">
											<input type="text" name="exemption_no" id="exemption_no" class="form-control input-sm col-md-8" value="888JJKDF" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="application_date" class="control-label col-md-3">Application date:</label>
										<div class="col-md-9">
											<input type="text" name="application_date" id="application_date" class="form-control input-sm col-md-8 uif-dateControl" placeholder="mm/dd/yyyy" value="12/12/2013" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="expiration_date" class="control-label col-md-3">Expiration date:</label>
										<div class="col-md-9">
											<input type="text" name="expiration_date" id="expiration_date" class="form-control input-sm col-md-8 uif-dateControl" placeholder="mm/dd/yyyy" value="05/01/2014" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-default" id="add_entry">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-6">
										<h4 class="panel-title">
											<a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span aria-hidden="true" class="icon-caret-right"></span> [New compliance]</a>
											<!-- <span>-</span>
											<span>-</span>
											<span>-</span>
											<span>-</span> -->
										</h4>
									</div>
									<div class="col-md-6">
										<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
									</div>
								</div>
							</div>
							<div id="collapse2" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="form-group clearfix">
										<label for="type2" class="control-label col-md-3">Type:</label>
										<div class="col-md-9">
											<select name="type2" id="type2" class="form-control input-sm col-md-8">
												<option></option>
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
									</div>

									<div class="form-group clearfix">
										<label for="approval_status2" class="control-label col-md-3">Approval status:</label>
										<div class="col-md-9">
											<select name="approval_status2" id="approval_status2" class="form-control input-sm col-md-8">
												<option></option>
												<option value="1">Approved</option>
												<option value="2">Exempt</option>
												<option value="3">Link to IACUC</option>
												<option value="4">Link to IRB</option>
												<option value="5">Not yet applied</option>
												<option value="6">Pending</option>
											</select>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="protocol_no2" class="control-label col-md-3">Protocol number:</label>
										<div class="col-md-9">
											<input type="text" name="protocol_no2" id="protocol_no2" class="form-control input-sm col-md-8" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="exemption_no2" class="control-label col-md-3">Exemption number:</label>
										<div class="col-md-9">
											<input type="text" name="exemption_no2" id="exemption_no2" class="form-control input-sm col-md-8" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="application_date2" class="control-label col-md-3">Application date:</label>
										<div class="col-md-9">
											<input type="text" name="application_date2" id="application_date2" class="form-control input-sm col-md-8 uif-dateControl" placeholder="mm/dd/yyyy" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="expiration_date2" class="control-label col-md-3">Expiration date:</label>
										<div class="col-md-9">
											<input type="text" name="expiration_date2" id="expiration_date2" class="form-control input-sm col-md-8 uif-dateControl" placeholder="mm/dd/yyyy" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</fieldset>
			</form><br />

			<div class="button-row">
				<button class="btn btn-success" id="compliance_add">+ Add entry</button>
			</div>
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.attachments.proposal.php'" class="btn btn-primary">Save and Continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>








<script type="text/javascript">
$('#add_entry').hide();
$('#compliance_add').click(function() {
	$('#add_entry').fadeIn();
});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>