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
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1">
      	
      	<div class="alert alert-success alert-dismissable">
  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="$('.alert').slideUp(200)" >&times;</button>
        	<p><i class="icon-ok"></i> Success! Proposal #23533 has been initiated.</p>
    	</div>

      	<h3>Proposal Details</h3>
        <form action="#" method="post" class="form-horizontal">
        
							<fieldset>
								<legend style="display:none">Enter any relevant details for this proposal</legend>
								
								<div class="form-group clearfix">
									<label for="proposal_type" class="control-label col-md-3 required">Proposal type: *</label>
									<div class="col-md-9">
										<select name="proposal_type" id="proposal_type" class="form-control input-sm col-md-8">
											<option value="4">Continuation</option>
											<option value="1" selected="selected">New</option>
											<option value="3">Renewal</option>
											<option value="2">Resubmission</option>
											<option value="5">Revision</option>
											<option value="6">Task Order</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="lead_unit" class="control-label col-md-3 required">Lead unit: *</label>
									<div class="col-md-9">
										<select name="lead_unit" id="lead_unit" class="form-control input-sm col-md-8">
											<option value="000001">000001 - University</option>
											<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
											<option value="IN-CARD" selected="selected">IN-CARD - CARDIOLOGY</option>
											<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="activity_type" class="control-label col-md-3 required">Activity type: *</label>
									<div class="col-md-9">
										<select name="activity_type" id="activity_type" class="form-control input-sm col-md-8">
											<option value="4">Clinical Trial</option>
											<option value="9">Construction</option>
											<option value="7">Fellowship - Post-Doctoral</option>
											<option value="6">Fellowship - Pre-Doctoral</option>
											<option value="2">Instruction</option>
											<option value="3">Public Service</option>
											<option value="1" selected="selected">Research</option>
											<option value="8">Student Services</option>
											<option value="5">other</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label class="control-label col-md-3 required" for="project_start">Project dates: *</label>
									<div class="col-md-9 date-range">
										<div class="col-md-6 date">
											<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_start" id="project_start" placeholder="mm/dd/yyyy" value="12/01/2013" />
											<label for="project_start" class="helper-text">Beginning on</label>
										</div>
										<span class="range">to</span>
										<div class="col-md-6 date">
											<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_end" id="project_end" placeholder="mm/dd/yyyy" value="11/30/2018" />
											<label for="project_end" class="helper-text">Ending on</label>
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="project_title" class="control-label col-md-3 required">Project title: *</label>
									<div class="col-md-9">
										<textarea name="project_title" id="project_title" class="form-control input-sm has-helper"><?php if ($alt) { echo 'Analysis of Significant Chemical Findings'; } else {	echo 'Analysis of Significant Biological Findings'; } ?></textarea>
										<div class="helper-text">
											Give this proposal a title. Be detailed but concise.
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="sponsor_code" class="control-label col-md-3 required">Sponsor code: *</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="sponsor_code" id="sponsor_code" value="<?php if ($alt) { echo '000340 NSF'; } else { echo '000340 NIH'; } ?>" />
										<div class="helper-text">
											Enter the sponsor code for this proposal or find one using the lookup tool.
										</div>
										<span class="input-group-btn">
											<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-sponsor-detailspage.php" data-modal-height="500"></a>
										</span>
									</div>
								</div>

								<!-- <div class="form-group clearfix">
									<label for="sponsor_name" class="control-label col-md-3">Sponsor name:</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="sponsor_name" id="sponsor_name" value="National Science Foundation" />
										<div class="helper-text">
											Enter the sponsor code for this proposal or find one using the lookup tool.
										</div>
									</div>
								</div> -->

								<div class="form-group clearfix">
									<label for="prime_sponsor_id" class="control-label col-md-3">Prime sponsor code:</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="prime_sponsor_id" id="prime_sponsor_id" />
										<div class="helper-text">
											Enter the sponsor code for this proposal or find one using the lookup tool.
										</div>
										<span class="input-group-btn">
											<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-sponsor.php" data-modal-height="500"></a>
										</span>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="award_id" class="control-label col-md-3">Award ID:</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="award_id" id="award_id" />
										<div class="helper-text">
											Enter the award ID for this proposal.
										</div>
										<span class="input-group-btn">
											<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-award.php" data-modal-height="500"></a>
										</span>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inst_proposal_id" class="control-label col-md-3">Institutional Proposal ID:</label>
									<div class="col-md-9 input-group">
										<input type="text" class="form-control input-sm has-helper" name="inst_proposal_id" id="inst_proposal_id" />
										<div class="helper-text">
											Enter the original institutional ID for this proposal.
										</div>
										<span class="input-group-btn">
											<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-institution.php" data-modal-height="500"></a>
										</span>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="keywords" class="control-label col-md-3">Keywords:</label>
									<div class="col-md-9">
										<select name="keywords" id="keywords" class="form-control input-sm col-md-8" multiple>
											<option >Biology</option>
											<option >Biochemistry</option>
											<option>Chemistry</option>
											<option >Physics</option>
											<option >Marine Biology</option>
											<option >Space Physics</option>
										</select>
									</div>
								</div>
							</fieldset>
						</form>
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
    <div class="btn-row-page-action">
      <button class="btn btn-default">Save</button>
		<?php
		if ($alt && file_exists('prop.basics.oppsearch-search-alt.php')) {
			echo '<button href="prop.basics.oppsearch-search-alt.php" class="btn btn-primary">Save and continue</button>';
		} else {
			echo '<button href="prop.basics.oppsearch-search.php" class="btn btn-primary">Save and continue</button>';
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








