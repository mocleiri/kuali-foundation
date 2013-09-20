<?php
# Variables
$section = 'basics';
$page = 'basics-orgloc';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
include( '_temp.select.filler.php' );
?>

<section id="main">

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

	<div class="container">
		<div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">

			  	<div id="content" class="uif-page" tabindex="-1">

			  		<div class="section-title">
				        <h2>Organization &amp; Location</h2>
				    </div>

				    <div class="section-content">
				        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				          <li class="active"><a href="#tab1" data-toggle="tab">Applicant Organization</a></li>
				          <li><a href="#tab2" data-toggle="tab">Performing Organization</a></li>
				          <li><a href="#tab3" data-toggle="tab">Performance Site Locations</a></li>
				          <li><a href="#tab4" data-toggle="tab">Other Organizations</a></li>
				        </ul>

				        <div id="my-tab-content" class="tab-content">

				          <div class="tab-pane active" id="tab1">

				          	<div class="form-group clearfix">
								<label for="app_org_org" class="control-label col-md-3">Organization:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_org" id="app_org_org" value="University of Michigan" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
									<!-- <span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span> -->
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="app_org_add1" class="control-label col-md-3">Address 1:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_add1" id="app_org_add1" value="2044 Wolverine Tower" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="app_org_add2" class="control-label col-md-3">Address 2:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_add2" id="app_org_add2" value="3003 State Street" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="app_org_city" class="control-label col-md-3">City:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_city" id="app_org_city" value="Ann Arbor" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="app_org_state" class="control-label col-md-3">Address 2:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_state" id="app_org_state" value="MI" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="app_org_zip" class="control-label col-md-3">Zipcode:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_zip" id="app_org_zip" value="3003 State Street" disabled />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="app_org_districts" class="control-label col-md-3">Congressional districts:</label>
								<div class="col-md-5">
									<select name="app_org_districts" id="app_org_districts" class="form-control input-sm col-md-8 chzn" multiple>
										<option selected="selected">One</option>
										<option selected="selected">Two</option>
										<option selected="selected">Three</option>
									</select>
								</div>
							</div>

				          </div>

				          <div class="tab-pane " id="tab2">

				            <div class="form-group clearfix">
								<label for="app_org_org" class="control-label col-md-3">Organization:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="app_org_org" id="app_org_org" value="University of Michigan" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
									<!-- <span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span> -->
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="perf_org_add1" class="control-label col-md-3">Address 1:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="perf_org_add1" id="perf_org_add1" value="2044 Wolverine Tower" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="perf_org_add2" class="control-label col-md-3">Address 2:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="perf_org_add2" id="perf_org_add2" value="3003 State Street" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="perf_org_city" class="control-label col-md-3">City:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="perf_org_city" id="perf_org_city" value="Ann Arbor" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="perf_org_state" class="control-label col-md-3">Address 2:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="perf_org_state" id="perf_org_state" value="MI" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

				          	<div class="form-group clearfix">
								<label for="perf_org_zip" class="control-label col-md-3">Zipcode:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="perf_org_zip" id="perf_org_zip" value="3003 State Street" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="perf_org_districts" class="control-label col-md-3">Congressional districts:</label>
								<div class="col-md-5">
									<select name="perf_org_districts" id="perf_org_districts" class="form-control input-sm col-md-8 chzn" multiple>
										<option selected="selected">One</option>
										<option selected="selected">Two</option>
										<option selected="selected">Three</option>
									</select>
								</div>
							</div>

				          </div>

				          <div class="tab-pane" id="tab3">

					        <div class="well">
					        	<form action="" method="">
					        		<button class="btn btn-default btn-sm pull-right" id="add">Add and configure</button>

					        		<input type="text" class="input-sm col-md-3 uif-switchme-input" placeholder="E.g., Research Location" />
					        		<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
					        	</form>
					        </div>

				            <div class="panel-group" id="accordion1">

				              <div class="panel panel-default">
				                <div class="panel-heading">
				                  <div class="row">
				                    <div class="col-md-6">
				                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"> <span aria-hidden="true" class="icon-caret-down"></span> Research Location </a> </h4>
				                    </div>
				                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
				                  </div>
				                </div>
				                <div id="collapse2" class="panel-collapse collapse">
				                  <div class="panel-body">
				                    <div class="form-group clearfix">
										<label for="perf_site_1_1_org" class="control-label col-md-3">Organization:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_org" id="perf_site_1_1_org" value="University of Michigan" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
											<!-- <span class="input-group-btn">
												<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
											</span> -->
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_1_add1" class="control-label col-md-3">Address 1:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_add1" id="perf_site_1_1_add1" value="2044 Wolverine Tower" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_1_add2" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_add2" id="perf_site_1_1_add2" value="3003 State Street" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_1_city" class="control-label col-md-3">City:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_city" id="perf_site_1_1_city" value="Ann Arbor" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_1_state" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_state" id="perf_site_1_1_state" value="MI" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_1_zip" class="control-label col-md-3">Zipcode:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_1_zip" id="perf_site_1_1_zip" value="3003 State Street" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="perf_site_1_1_districts" class="control-label col-md-3">Congressional districts:</label>
										<div class="col-md-5">
											<select name="perf_site_1_1_districts" id="perf_site_1_1_districts" class="form-control input-sm col-md-8 chzn" multiple>
												<option selected="selected">One</option>
												<option selected="selected">Two</option>
												<option selected="selected">Three</option>
											</select>
										</div>
									</div>
				                  </div>
				                </div>
				              </div>

				              <div class="panel panel-default">
				                <div class="panel-heading">
				                  <div class="row">
				                    <div class="col-md-6">
				                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse3"> <span aria-hidden="true" class="icon-caret-down"></span>  Field Study Location </a> </h4>
				                    </div>
				                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
				                  </div>
				                </div>
				                <div id="collapse3" class="panel-collapse collapse">
				                  <div class="panel-body">
				                    <div class="form-group clearfix">
										<label for="perf_site_1_2_org" class="control-label col-md-3">Organization:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_org" id="perf_site_1_2_org" value="University of Michigan" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
											<!-- <span class="input-group-btn">
												<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
											</span> -->
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_2_add1" class="control-label col-md-3">Address 1:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_add1" id="perf_site_1_2_add1" value="2044 Wolverine Tower" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_2_add2" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_add2" id="perf_site_1_2_add2" value="3003 State Street" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_2_city" class="control-label col-md-3">City:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_city" id="perf_site_1_2_city" value="Ann Arbor" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_2_state" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_state" id="perf_site_1_2_state" value="MI" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_2_zip" class="control-label col-md-3">Zipcode:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_2_zip" id="perf_site_1_2_zip" value="3003 State Street" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="perf_site_1_2_districts" class="control-label col-md-3">Congressional districts:</label>
										<div class="col-md-5">
											<select name="perf_site_1_2_districts" id="perf_site_1_2_districts" class="form-control input-sm col-md-8 chzn" multiple>
												<option selected="selected">One</option>
												<option selected="selected">Two</option>
												<option selected="selected">Three</option>
											</select>
										</div>
									</div>
				                  </div>
				                </div>
				              </div>
				              
				              <div id="added" class="panel panel-default">
				                <div class="panel-heading">
				                  <div class="row">
				                    <div class="col-md-6">
				                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span aria-hidden="true" class="icon-caret-down"></span> Research Location</a> </h4>
				                    </div>
				                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
				                  </div>
				                </div>
				                <div id="collapse1" class="panel-collapse in">
				                  <div class="panel-body">
				                    <div class="form-group clearfix">
										<label for="perf_site_1_3_org" class="control-label col-md-3">Organization:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_org" id="perf_site_1_3_org" value="Research Location" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
											<!-- <span class="input-group-btn">
												<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
											</span> -->
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_3_add1" class="control-label col-md-3">Address 1:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_add1" id="perf_site_1_3_add1" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_3_add2" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_add2" id="perf_site_1_3_add2" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_3_city" class="control-label col-md-3">City:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_city" id="perf_site_1_3_city" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_3_state" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_state" id="perf_site_1_3_state" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="perf_site_1_3_zip" class="control-label col-md-3">Zipcode:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="perf_site_1_3_zip" id="perf_site_1_3_zip" />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="perf_site_1_3_districts" class="control-label col-md-3">Congressional districts:</label>
										<div class="col-md-5">
											<select name="perf_site_1_3_districts" id="perf_site_1_3_districts" class="form-control input-sm col-md-8 chzn" multiple>
												<?php get_options() ?>
											</select>
										</div>
									</div>
				                  </div>
				                </div>
				              </div>

				            </div>
				          </div>

				          <div class="tab-pane " id="tab4">

					        <div class="well">
					        	<form action="" method="">
					        		<button class="btn btn-default btn-sm launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Find an organization to add</button>
					        	</form>
					        </div>

				            <div class="panel-group" id="accordion2">
				              <div class="panel panel-default">
				                <div class="panel-heading">
				                 <div class="row">
				                    <div class="col-md-6">
				                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse4"> <span aria-hidden="true" class="icon-caret-down"></span> Communications Power </a> </h4>
				                    </div>
				                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
				                  </div>
				                </div>
				                <div id="collapse4" class="panel-collapse collapse">
				                  <div class="panel-body">
				                    <div class="form-group clearfix">
										<label for="other_org_1_1_org" class="control-label col-md-3">Organization:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_org" id="other_org_1_1_org" value="University of Michigan" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
											<!-- <span class="input-group-btn">
												<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
											</span> -->
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_1_add1" class="control-label col-md-3">Address 1:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_add1" id="other_org_1_1_add1" value="2044 Wolverine Tower" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_1_add2" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_add2" id="other_org_1_1_add2" value="3003 State Street" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_1_city" class="control-label col-md-3">City:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_city" id="other_org_1_1_city" value="Ann Arbor" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_1_state" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_state" id="other_org_1_1_state" value="MI" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_1_zip" class="control-label col-md-3">Zipcode:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_1_zip" id="other_org_1_1_zip" value="3003 State Street" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="other_org_1_1_districts" class="control-label col-md-3">Congressional districts:</label>
										<div class="col-md-5">
											<select name="other_org_1_1_districts" id="other_org_1_1_districts" class="form-control input-sm col-md-8 chzn" multiple>
												<option selected="selected">One</option>
												<option selected="selected">Two</option>
												<option selected="selected">Three</option>
											</select>
										</div>
									</div>
				                  </div>
				                </div>
				              </div>
				              <div class="panel panel-default">
				                <div class="panel-heading">
				                 <div class="row">
				                    <div class="col-md-6">
				                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse5"> <span aria-hidden="true" class="icon-caret-down"></span> Cleveland Clinic Organization </a> </h4>
				                    </div>
				                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
				                  </div>
				                </div>
				                <div id="collapse5" class="panel-collapse collapse">
				                  <div class="panel-body">
				                    <div class="form-group clearfix">
										<label for="other_org_1_2_org" class="control-label col-md-3">Organization:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_org" id="other_org_1_2_org" value="University of Michigan" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
											<!-- <span class="input-group-btn">
												<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
											</span> -->
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_2_add1" class="control-label col-md-3">Address 1:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_add1" id="other_org_1_2_add1" value="2044 Wolverine Tower" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_2_add2" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_add2" id="other_org_1_2_add2" value="3003 State Street" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_2_city" class="control-label col-md-3">City:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_city" id="other_org_1_2_city" value="Ann Arbor" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_2_state" class="control-label col-md-3">Address 2:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_state" id="other_org_1_2_state" value="MI" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

						          	<div class="form-group clearfix">
										<label for="other_org_1_2_zip" class="control-label col-md-3">Zipcode:</label>
										<div class="col-md-5 input-group">
											<input type="text" class="form-control input-sm" name="other_org_1_2_zip" id="other_org_1_2_zip" value="3003 State Street" disabled />
											<div class="helper-text">
												Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
											</div>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="other_org_1_2_districts" class="control-label col-md-3">Congressional districts:</label>
										<div class="col-md-5">
											<select name="other_org_1_2_districts" id="other_org_1_2_districts" class="form-control input-sm col-md-8 chzn" multiple>
												<option selected="selected">One</option>
												<option selected="selected">Two</option>
												<option selected="selected">Three</option>
											</select>
										</div>
									</div>
				                  </div>
				                </div>
				              </div>
				            </div>
				          </div>
				        </div>

				    </div>
        
        <!-- // --> 
        
      </div>

		</div>

	</div>

</section>

<script>
$('#added').hide();
$('#add').click(function() {
	$('#added').fadeIn();
	return false;
});
</script>

<div class="page-controls clearfix">
	<div class="page-actions">
		<div class="well"></div>
	</div>

	<div class="page-navigation">
		<div class="well">
			<button class="btn btn-default">Save</button>
			<button class="btn btn-primary" href="prop.basics.deliveryinfo.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>