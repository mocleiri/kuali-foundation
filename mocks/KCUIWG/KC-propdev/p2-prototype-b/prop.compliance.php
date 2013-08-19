<?php
# Variables
$page = 'compliance';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
		
	<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Compliance</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Add compliance requirements for this proposal</legend>
							<div class="form-group col-md-2">
								<label for="compliance_type" class="control-label">Type:<br />&nbsp;</label>
								<div>
									<select name="compliance_type" id="compliance_type" class="form-control input-sm">
										<option value="">select</option>
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

							<div class="form-group col-md-2">
								<label for="approval_status" class="control-label">Approval status:</label>
								<div>
									<select name="approval_status" id="approval_status" class="form-control input-sm">
										<option value="">select</option>
										<option value="2">Approved</option>
										<option value="4">Exempt</option>
										<option value="6">Link to IACUC</option>
										<option value="5">Link to IRB</option>
										<option value="3">Not yet applied</option>
										<option value="1">Pending</option>
									</select>
								</div>
							</div>

							<div class="form-group col-md-2">
								<label for="protocol_number" class="control-label">Protocol number:</label>
								<div>
									<input type="text" class="form-control input-sm" name="protocol_number" id="protocol_number" />
								</div>
							</div>

							<div class="form-group col-md-2">
								<label for="app_date" class="control-label">Application date:</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="app_date" id="app_date" />
									<span class="input-group-btn">
										<button class="btn btn-default input-sm">@</button>
									</span>
								</div>
							</div>

							<div class="form-group col-md-2">
								<label for="approval_date" class="control-label">Approval date:</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="approval_date" id="approval_date" />
									<span class="input-group-btn">
										<button class="btn btn-default input-sm">@</button>
									</span>
								</div>
							</div>

							<div class="form-group col-md-2">
								<label for="exp_date" class="control-label">Expiration date:</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="exp_date" id="exp_date" />
									<span class="input-group-btn">
										<button class="btn btn-default input-sm">@</button>
									</span>
								</div>
							</div>

							<div class="form-group col-md-2">
								<label for="excemption" class="control-label">Exemption #:<br />&nbsp;</label>
								<div>
									<input type="text" class="form-control input-sm" name="excemption" id="excemption" />
								</div>
							</div>

							<div class="form-group col-md-2">
								<button class="btn btn-danger btn-xs">- Delete</button>
							</div>
						</fieldset>

						<button class="btn btn-success">+ Add row</button>
					</form>
				</div>

				<!-- // -->

			</div>

		</div>

	</div>

	<!-- Button row -->
	<div class="button-row">
		<div class="container">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
	</div>
	<!-- // -->

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>