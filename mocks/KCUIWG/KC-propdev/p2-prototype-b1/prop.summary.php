<?php
# Variables
$page = 'prop-summary';

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

			

				

					
						<h3>Budget Summary and Submission</h3>
					

					<ul id="prop_summary_tabs" class="nav nav-tabs">
						<li class="active"><a href="#summary" data-toggle="tab">Summary</a></li>
						<li><a href="#submit" data-toggle="tab">Submit</a></li>
					</ul>

					<div class="tab-content">
						<div class="tab-pane active" id="summary">
							<div class="box">
								<div class="boxHeader">
									
										<h3>Workflow routing</h3>
									
								</div>
								<div class="boxContent">
									<div class="well">
										<h4>Proposal progress</h4>
										<div class="progress-details">
											<div class="empty complete" style="width: 20%">In Progress</div>
											<div class="empty complete" style="width: 20%">Routing &amp; Review</div>
											<div class="empty active" style="width: 20%">Final Institutional Review</div>
											<div class="empty" style="width: 20%">Approved</div>
											<div class="empty" style="width: 20%">Submitted to Sponsor</div>
										</div>
										<div class="progress">
											<div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
											<div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
											<div class="progress-bar progress-bar-warning" style="width: 20%;" title="In Progress" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
											<div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
											<div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
										</div>
									</div>

									<div class="panel-group" id="accordion">
										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop1">Proposal Summary</a>
												</h6>
											</div>
											<div id="prop1" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>

										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop2">Key Persons</a>
												</h6>
											</div>
											<div id="prop2" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>
										
										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop3">Attachments</a>
												</h6>
											</div>
											<div id="prop3" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>
										
										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop4">Special Review</a>
												</h6>
											</div>
											<div id="prop4" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>
										
										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop5">Proposal Print</a>
												</h6>
											</div>
											<div id="prop5" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>
										
										<div class="panel">
											<div class="panel-heading">
												<h6 class="panel-title">
													<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop6">Proposal Summary</a>
												</h6>
											</div>
											<div id="prop6" class="panel-collapse collapse">
												<div class="panel-body">
													Stuff here...
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane" id="submit">
							<div class="box">
								<div class="boxHeader">
									
										<h3>Workflow actions</h3>
									
								</div>
								<div class="boxContent">
									<div class="well">
										<h4>Proposal actions</h4>

										<div class="proposal-actions clearfix">
											<a href="#" class="btn btn-success">Approve</a>
											<a href="#" class="btn btn-danger">Reject</a>
											<a href="#" class="btn btn-default">Recall</a>
											<a href="#" class="btn btn-default">Send Notification</a>
											<a href="#" class="btn btn-primary pull-right">Submit to Sponsor</a>
										</div>
									</div>

									<div class="comments">
										<div class="comment-actions">
											<button class="btn btn-default">+ Add Comment</button>
										</div>

										<div class="comment-row comment-headers clearfix">
											<div class="col-md-2">
												Date
											</div>
											<div class="col-md-2">
												Person
											</div>
											<div class="col-md-8">
												Comment
											</div>
										</div>

										<div class="comment-row clearfix">
											<div class="col-md-2">
												03/22/2013
											</div>
											<div class="col-md-2">
												Bart Niedner
											</div>
											<div class="col-md-8">
												Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sed lorem in risus ornare semper vitae sit amet mauris. Nam imperdiet sollicitudin odio vel convallis. Nullam non malesuada nisl. Nulla tempor fermentum ornare. Fusce sollicitudin lobortis arcu ut malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lacinia interdum eleifend. Cras nec hendrerit nulla. Nulla facilisi. In lectus arcu, consectetur vitae auctor in, condimentum at libero. Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. Suspendisse at quam tortor, ac pulvinar augue.
											</div>
										</div>

										<div class="comment-row clearfix">
											<div class="col-md-2">
												03/21/2013
											</div>
											<div class="col-md-2">
												Colnel Mustard
											</div>
											<div class="col-md-8">
												Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum.
											</div>
										</div>
									</div>
								</div>
							</did>
						</div>
					</div>
				</div>

				<!-- // -->

        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.inst.3.php'" class="btn btn-default">Back</button>

			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>






