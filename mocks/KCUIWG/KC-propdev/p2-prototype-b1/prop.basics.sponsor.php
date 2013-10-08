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
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 
					<h3>Sponsor &amp; Program Information</h3>
				

				
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend style="display:none">Enter sponsor information</legend>
							<div class="form-group clearfix">
								<label for="sponsor_deadline_date" class="control-label col-md-3">Sponsor deadline date:</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control input-sm uif-dateControl" name="sponsor_deadline_date" id="sponsor_deadline_date" placeholder="mm/dd/yyyy" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_deadline_time" class="control-label col-md-3">Sponsor deadline time:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="sponsor_deadline_time" id="sponsor_deadline_time" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="notice_of_opp" class="control-label col-md-3">Notice of Opportunity:</label>
								<div class="col-md-9">
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
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="opportunity_id" id="opportunity_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="cfda_number" class="control-label col-md-3">CFDA number:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="cfda_number" id="cfda_number" />
								</div>
							</div>

							<div class="form-group checkbox clearfix">
								<label for="subawards control-label col-md-9" class="subawards">
									<input type="checkbox" value="" name="subawards" id="subawards" class="checkbox-radio-reset-margin" /> This proposal includes subaward(s)
								</label>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_proposal_id" class="control-label col-md-3">Sponsor proposal ID:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="sponsor_proposal_id" id="sponsor_proposal_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_div_code" class="control-label col-md-3">Sponsor DIV code:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="sponsor_div_code" id="sponsor_div_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_program_code" class="control-label col-md-3">Sponsor program code:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="sponsor_program_code" id="sponsor_program_code" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="nsf_science_code" class="control-label col-md-3">NSF Science code:</label>
								<div class="col-md-9">
									<select name="nsf_science_code" id="nsf_science_code" class="form-control input-sm col-md-8 chzn">
										<option>Astronomy</option>
										<option>Physical Sciences: B.01</option>
										<option>Phsyical Sciences: B.02</option>
										<option>Physical Sciences: Biological</option>
										<option>Life Sciences: F.02</option>
										<option>Life Sciences: Chemistry</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="anticipated_award_type" class="control-label col-md-3">Anticipated award type:</label>
								<div class="col-md-9">
									<select name="anticipated_award_type" id="anticipated_award_type" class="form-control input-sm col-md-8 chzn">
										<option value="P">Grant</option>
										<option value="R">Contract</option>
										<option value="T">Fellowship</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="opportunity_title" class="control-label col-md-3">Opportunity title:</label>
								<div class="col-md-9">
									<textarea name="opportunity_title" id="opportunity_title" class="form-control input-sm disabled" disabled>Non-editable value</textarea>
								</div>
							</div>
                            <div class="form-group clearfix">
								<label for="prev_grantsgov_tracking" class="control-label col-md-3">Previous Grants.gov Tracking ID:</label>
								<div class="col-md-9">
										<input type="text" class="form-control input-sm" name="prev_grantsgov_tracking" id="prev_grantsgov_tracking" />
								</div>
							</div>
                            <div class="form-group clearfix">
								<label for="agency_routing_identifier" class="control-label col-md-3">Agency Routing Identifier:</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-sm" name="agency_routing_identifier" id="agency_routing_identifier" />
								</div>
							</div>
                            
                            
						</fieldset>
					</form>
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
			<div class="btn-row-page-action">
				<?php
				if ($alt && file_exists('prop.basics.deliveryinfo-alt.php')) {
					echo '<button href="prop.basics.deliveryinfo-alt.php" class="btn btn-default">Back</button>';
				} else {
					echo '<button href="prop.basics.deliveryinfo.php" class="btn btn-default">Back</button>';
				}
				?>
				<button class="btn btn-default">Save</button>
		      <button  onclick="location.href='prop.basics.orgloc.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>







	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>