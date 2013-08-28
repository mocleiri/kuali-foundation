<?php
# Variables
$section = 'basics';
$page = 'basics-sponsors';

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

			<div class="col-md-9" id="content" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Sponsor &amp; Program Information</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Enter sponsor information</legend>
							<div class="form-group clearfix">
								<label for="sponsor_name" class="control-label col-md-3">Sponsor name:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="sponsor_name" id="sponsor_name" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_deadline_date" class="control-label col-md-3">Sponsor deadline date:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="sponsor_deadline_date" id="sponsor_deadline_date" />
									<span class="input-group-btn">
										<button name="sponsor_date_search" id="sponsor_date_search" class="btn btn-default input-sm">Search</button>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_deadline_time" class="control-label col-md-3">Sponsor deadline time:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="sponsor_deadline_time" id="sponsor_deadline_time" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="notice_of_opp" class="control-label col-md-3">Notice of Opportunity:</label>
								<div class="col-md-5">
									<select name="notice_of_opp" id="notice_of_opp" class="form-control input-sm col-md-8 chzn">
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
								<label for="opportunity_id" class="control-label col-md-3">Opportunity ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="opportunity_id" id="opportunity_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="cfda_number" class="control-label col-md-3">CFDA number:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="cfda_number" id="cfda_number" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="prime_sponsor_id" class="control-label col-md-3">Prime sponsor ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="prime_sponsor_id" id="prime_sponsor_id" />
								</div>
							</div>

							<div class="form-group checkbox clearfix">
								<div class="blank col-md-3"></div>
								<label for="subawards control-label col-md-5" class="subawards">
									<input type="checkbox" value="" name="subawards" id="subawards" class="checkbox-radio-reset-margin" /> This proposal includes subaward(s)
								</label>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_proposal_id" class="control-label col-md-3">Sponsor proposal ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="sponsor_proposal_id" id="sponsor_proposal_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_div_code" class="control-label col-md-3">Sponsor DIV code:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="sponsor_div_code" id="sponsor_div_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_program_code" class="control-label col-md-3">Sponsor program code:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="sponsor_program_code" id="sponsor_program_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="nsf_science_code" class="control-label col-md-3">NSF Science code:</label>
								<div class="col-md-5">
									<select name="nsf_science_code" id="nsf_science_code" class="form-control input-sm col-md-8 chzn">
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
								</div>
							</div>

							<!-- <div class="form-group clearfix">
								<label for="sponsor_deadline_type" class="control-label col-md-3">Sponsor deadline type:</label>
								<div class="col-md-5">
									<select name="sponsor_deadline_type" id="sponsor_deadline_type" class="form-control input-sm col-md-8 chzn">
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
								</div>
							</div> -->

							<div class="form-group clearfix">
								<label for="anticipated_award_type" class="control-label col-md-3">Anticipated award type:</label>
								<div class="col-md-5">
									<select name="anticipated_award_type" id="anticipated_award_type" class="form-control input-sm col-md-8 chzn">
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="opportunity_title" class="control-label col-md-3">Opportunity title:</label>
								<div class="col-md-5">
									<textarea name="opportunity_title" id="opportunity_title" class="form-control input-sm disabled" disabled>Non-editable value</textarea>
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

<?php require_once( 'themes/kc/inc/footer.php' ); ?>