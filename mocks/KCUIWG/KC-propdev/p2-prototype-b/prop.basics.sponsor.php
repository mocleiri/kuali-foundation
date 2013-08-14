<?php
# Variables
$page = 'basics-sponsors';

# Includes
require_once( 'kc/inc/head.php' );
require_once( 'kc/inc/nav.php' );
require_once( 'kc/inc/toolbar.php' );
?>

<section id="main">
		
	<?php require_once( 'kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<?php require_once( 'kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-lg-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Sponsor &amp; Program Information</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Enter sponsor information</legend>
							<div class="form-group clearfix">
								<label for="notice_of_opp" class="control-label col-lg-3">Notice of Opportunity:</label>
								<div class="col-lg-5">
									<select name="notice_of_opp" id="notice_of_opp" class="form-control col-lg-8">
										<option value="">select</option>
										<option value="1">Federal Solicitation</option>
										<option value="2">Unsolicited</option>
										<option value="3">Verbal Request for Proposal</option>
										<option value="4">SBIR Solicitation</option>
										<option value="5">STTR Solicitation</option>
										<option value="6">Non-Federal Solicitation</option>
										<option value="7">Internal</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_deadline_type" class="control-label col-lg-3">Sponsor deadline type:</label>
								<div class="col-lg-5">
									<select name="sponsor_deadline_type" id="sponsor_deadline_type" class="form-control col-lg-8">
										<option value="">select</option>
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="cfda_number" class="control-label col-lg-3">CFDA number:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="cfda_number" id="cfda_number" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_name" class="control-label col-lg-3">Sponsor name:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="sponsor_name" id="sponsor_name" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="opportunity_id" class="control-label col-lg-3">Opportunity ID:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="opportunity_id" id="opportunity_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="prime_sponsor_id" class="control-label col-lg-3">Prime sponsor ID:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="prime_sponsor_id" id="prime_sponsor_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_proposal_id" class="control-label col-lg-3">Sponsor proposal ID:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="sponsor_proposal_id" id="sponsor_proposal_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="nsf_science_code" class="control-label col-lg-3">NSF Science code:</label>
								<div class="col-lg-5">
									<select name="nsf_science_code" id="nsf_science_code" class="form-control col-lg-8">
										<option value="">select</option>
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
								</div>
							</div>

							<div class="form-group checkbox clearfix">
								<label for="subawards">
									<input type="checkbox" value="" name="subawards" id="subawards" /> This proposal includes subaward(s)
								</label>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_div_code" class="control-label col-lg-3">Sponsor Div code:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="sponsor_div_code" id="sponsor_div_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_program_code" class="control-label col-lg-3">Sponsor program code:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="sponsor_program_code" id="sponsor_program_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="opportunity_title" class="control-label col-lg-3">Opportunity title:</label>
								<div class="col-lg-5">
									<textarea name="opportunity_title" id="opportunity_title" class="form-control"></textarea>
								</div>
							</div>
						</fieldset>
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

<?php require_once( 'kc/inc/footer.php' ); ?>