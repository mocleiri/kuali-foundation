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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
	
	<div class="container">
		<div class="row-temp-disabled">
		
			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Sponsor &amp; Program Information</h2>
				</div>

				<div class="section-content">
					<!-- <div class="tab-title-container clearfix">
						<h4>Review sponsor information</h4>

			            <div class="page-controls">
			            	<div class="well">
			            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
			            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Add new</button>
			            	</div>
			            </div>
			        </div> -->

					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Sponsor details</legend>
							<div class="form-group clearfix">
								<label for="sponsor_name" class="control-label col-md-3">Sponsor name:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="sponsor_name" id="sponsor_name" placeholder="i.e., National Science Foundation" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-md-3">Sponsor deadline:</label>
								<div class="col-md-5">
									<div class="col-md-6 date">
										<input type="text" size="12" class="form-control input-sm uif-dateControl" name="deadline_date" id="deadline_date" placeholder="mm/dd/yyyy" />
										<label for="deadline_date" class="helper-text">Date</label>
									</div>
									<div class="col-md-6 date">
										<input type="text" size="12" class="form-control input-sm" name="deadline_time" id="deadline_time" placeholder="17:00" />
										<label for="deadline_time" class="helper-text">Time</label>
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="notice_of_opp" class="control-label col-md-3">Notice of Opportunity:</label>
								<div class="col-md-5">
									<select name="notice_of_opp" id="notice_of_opp" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="1">Federal Solicitation</option>
										<option value="2">Unsolicited</option>
										<option value="3">Verbal Request for Proposal</option>
										<option value="4">SBIR Solicitation</option>
										<option value="5">STTR Solicitation</option>
										<option value="6">Non-Federal Solicitation</option>
										<option value="7">Internal</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="opportunity_id" class="control-label col-md-3">Opportunity ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="opportunity_id" id="opportunity_id" placeholder="i.e., 12345" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="cfda_number" class="control-label col-md-3">CFDA number:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="cfda_number" id="cfda_number" placeholder="i.e., ABCD123" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="prime_sponsor_id" class="control-label col-md-3">Prime sponsor ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="prime_sponsor_id" id="prime_sponsor_id" placeholder="i.e., 000NUN" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group checkbox clearfix">
								<div class="blank col-md-3"></div>
								<label for="subawards" class="subawards col-md-5">
									<input type="checkbox" value="" name="subawards" id="subawards" class="checkbox-radio-reset-margin" /> This proposal includes subaward(s)
								</label>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_proposal_id" class="control-label col-md-3">Sponsor proposal ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="sponsor_proposal_id" id="sponsor_proposal_id" placeholder="i.e., 012" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_div_code" class="control-label col-md-3">Sponsor DIV code:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="sponsor_div_code" id="sponsor_div_code" placeholder="i.e., NSF" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_program_code" class="control-label col-md-3">Sponsor program code:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm has-helper" name="sponsor_program_code" id="sponsor_program_code" placeholder="i.e., NSFBU01" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="nsf_science_code" class="control-label col-md-3">NSF Science code:</label>
								<div class="col-md-5">
									<select name="nsf_science_code" id="nsf_science_code" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="anticipated_award_type" class="control-label col-md-3">Anticipated award type:</label>
								<div class="col-md-5">
									<select name="anticipated_award_type" id="anticipated_award_type" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="P">Postmark</option>
										<option value="R">Receipt</option>
										<option value="T">Target</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="opportunity_title" class="control-label col-md-3">Opportunity title:</label>
								<div class="col-md-5">
									<textarea name="opportunity_title" id="opportunity_title" class="form-control input-sm disabled has-helper" disabled>Non-editable value</textarea>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>
						</fieldset>
					</form>
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
			<button class="btn btn-primary" href="prop.basics.orgloc.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>