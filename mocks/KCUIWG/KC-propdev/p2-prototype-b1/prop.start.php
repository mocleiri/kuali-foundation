<?php
# Variables
$section = 'basics';
$page = 'basics-details';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
include( '_temp.select.filler.php' );
?>

<section id="main">

  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
   
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">  <h3>Create Proposal</h3>
      	<!--<div class="alert alert-required">
      		<p>Required fields are <strong>bold</strong>, marked with asterisks (* ), and have a light blue highlight.</p>
      	</div>-->
        <p class="text-muted"> * Indicates required fields</p>

        <form action="#" method="post" class="form-horizontal">
							<fieldset>
								<legend style="display:none">Enter any relevant details for this proposal</legend>
								
								<div class="form-group clearfix">
									<label for="proposal_type" class="control-label col-md-3 required"> * Proposal type:</label>
									<div class="col-md-9">
										<select name="proposal_type" id="proposal_type" class="form-control input-sm col-md-8">
											<optgroup label="Most common">
												<option value="1">New</option>
												<option value="4">Continuation</option>
											</optgroup>
											<optgroup label="Other">
												<option value="3">Renewal</option>
												<option value="2">Resubmission</option>
												<option value="5">Revision</option>
												<option value="6">Task Order</option>
											</optgroup>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="lead_unit" class="control-label col-md-3 required"> * Lead unit:</label>
									<div class="col-md-9">
										<select name="lead_unit" id="lead_unit" class="form-control input-sm col-md-8">
											<optgroup label="Most common">
											</optgroup>
											<optgroup label="Other">
												<option value="000001">000001 - University</option>
												<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY</option>
												<option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
												<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
											</optgroup>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="activity_type" class="control-label col-md-3 required">* Activity type:</label>
									<div class="col-md-9">
										<select name="activity_type" id="activity_type" class="form-control input-sm col-md-8">
											<optgroup label="Most common">
												<option value="1">Research</option>
												<option value="4">Clinical Trial</option>
											</optgroup>
											<optgroup label="Other">
												<option value="9">Construction</option>
												<option value="7">Fellowship - Post-Doctoral</option>
												<option value="6">Fellowship - Pre-Doctoral</option>
												<option value="2">Instruction</option>
												<option value="3">Public Service</option>
												<option value="8">Student Services</option>
												<option value="5">Other</option>
											</optgroup>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label class="control-label col-md-3 required" for="project_start">* Project dates:</label>
									<div class="col-md-9 date-range">
										<div class="col-md-6 date">
											<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_start" id="project_start" placeholder="mm/dd/yyyy" />
											<label for="project_start" class="helper-text">Beginning on</label>
										</div>
										<span class="range">to</span>
										<div class="col-md-6 date">
											<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_end" id="project_end" placeholder="mm/dd/yyyy" />
											<label for="project_end" class="helper-text">Ending on</label>
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="project_title" class="control-label col-md-3 required">* Project title:</label>
									<div class="col-md-9">
										<textarea name="project_title" id="project_title" class="form-control input-sm has-helper"></textarea>
										<div class="helper-text">
											Enter the project title here.
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="sponsor_code" class="control-label col-md-3 required">* Sponsor code:</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="sponsor_code" id="sponsor_code" />
										<div class="helper-text">
											Enter the sponsor code for this proposal or find one using the lookup tool.
										</div>
										<span class="input-group-btn">
											<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-sponsor-startpage.php" data-modal-height="500"></a>
										</span>
									</div>
								</div>
						
							</fieldset>
						</form>
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
    <div class="btn-row-page-action">
      <button class="btn btn-default">Cancel</button>
		<?php
		if ($alt && file_exists('prop.basics.details-alt.php')) {
			echo '<button href="prop.basics.details-alt.php?msg=1" class="btn btn-primary">Save and Continue</button>';
		} else {
			echo '<button href="prop.basics.details.php?msg=1" class="btn btn-primary">Save and Continue</button>';
		}
		?>
    </div>
    <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>