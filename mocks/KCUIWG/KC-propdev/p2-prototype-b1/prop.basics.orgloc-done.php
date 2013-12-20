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
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1">     <!-- Main content goes here -->
        
        <h3>Organization &amp; Location</h3>
        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
          <li><a href="#tab1" data-toggle="tab">Applicant Organization</a></li>
          <li><a href="#tab2" data-toggle="tab">Performing Organization</a></li>
          <li><a href="#tab3" data-toggle="tab">Performance Site Locations</a></li>
          <li class="active"><a href="#tab4" data-toggle="tab">Other Organizations</a></li>
        </ul>
        <div id="my-tab-content" class="tab-content">
          <div class="tab-pane" id="tab1">
            <h4>Applicant Organization</h4>
            <table  class="table table-condensed">
              <tbody>
                <tr>
                  <th class="col-md-3">Organization</th>
                  <td>University of Washington</td>
                </tr>
                <tr>
                  <th class="col-md-3">Address 1</th>
                  <td>1410 NE Campus Pkwy</td>
                </tr>
                <tr>
                  <th>Address 2</th>
                  <td></td>
                </tr>
                <tr>
                  <th>City</th>
                  <td>Seattle</td>
                </tr>
                <tr>
                  <th>State</th>
                  <td>WA</td>
                </tr>
                <tr>
                  <th>ZIP</th>
                  <td>98195</td>
                </tr>
                <tr>
                  <th>Congressional District</th>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="tab-pane " id="tab2">
            <h4> Performing Organization</h4>
            <table  class="table table-condensed">
              <tbody>
                <tr>
                  <th class="col-md-3">Organization</th>
                  <td>University of Washington</td>
                </tr>
                <tr>
                  <th class="col-md-3">Address 1</th>
                  <td>1410 NE Campus Pkwy</td>
                </tr>
                <tr>
                  <th>Address 2</th>
                  <td></td>
                </tr>
                <tr>
                  <th>City</th>
                  <td>Seattle</td>
                </tr>
                <tr>
                  <th>State</th>
                  <td>WA</td>
                </tr>
                <tr>
                  <th>ZIP</th>
                  <td>98195</td>
                </tr>
                <tr>
                  <th>Congressional District</th>
                  <td></td>
                </tr>
              </tbody>
            </table>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution.php">Update Organization</button>
            </div>
          </div>
          <div class="tab-pane" id="tab3">
            <h4> Performance Site Locations</h4>
            <div class="panel-group" id="accordion1">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span aria-hidden="true" class="icon-caret-right"></span> Clinical Trial Location </a> </h4>
                    </div>
                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                  <div class="panel-body">
                    <table  class="table table-condensed">
                      <tbody>
                        <tr>
                          <th class="col-md-3">Organization</th>
                          <td>select... <a href="#">lookup (icon)</a></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"> <span aria-hidden="true" class="icon-caret-right"></span> Research Location </a> </h4>
                    </div>
                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse2" class="panel-collapse collapse">
                  <div class="panel-body">
                    <table  class="table table-condensed">
                      <tbody>
                        <tr>
                          <th class="col-md-3">Organization</th>
                          <td>United Technologies/Pratt &amp; Whitney <a href="#">lookup (icon)</a></td>
                        </tr>
                        <tr>
                          <th class="col-md-3">Address 1</th>
                          <td>Mail Stop 169-21 </td>
                        </tr>
                        <tr>
                          <th>Address 2</th>
                          <td>400 Main Street </td>
                        </tr>
                        <tr>
                          <th>City</th>
                          <td>East Hartford</td>
                        </tr>
                        <tr>
                          <th>State</th>
                          <td> CT  </td>
                        </tr>
                        <tr>
                          <th>ZIP</th>
                          <td>06108</td>
                        </tr>
                        <tr>
                          <th>Congressional District</th>
                          <td><form>
                              <input id='tags_3' type='text' class='tags'>
                            </form></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse3"> <span aria-hidden="true" class="icon-caret-right"></span> Field Study Location </a> </h4>
                    </div>
                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse3" class="panel-collapse collapse">
                  <div class="panel-body">
                    <table  class="table table-condensed">
                      <tbody>
                        <tr>
                          <th class="col-md-3">Organization</th>
                          <td> Naval Training Systems Center <a href="#">lookup (icon)</a></td>
                        </tr>
                        <tr>
                          <th class="col-md-3">Address 1</th>
                          <td>Property, Code 773  </td>
                        </tr>
                        <tr>
                          <th>Address 2</th>
                          <td>12350 Research Parkway </td>
                        </tr>
                        <tr>
                          <th>City</th>
                          <td>Orlando</td>
                        </tr>
                        <tr>
                          <th>State</th>
                          <td>FL  </td>
                        </tr>
                        <tr>
                          <th>ZIP</th>
                          <td>32826</td>
                        </tr>
                        <tr>
                          <th>Congressional District</th>
                          <td><form>
                              <input id='tags_4' type='text' class='tags'>
                            </form></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <div class=" btn-row-widget-action">
              <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution.php">Add Location</button>
            </div>
          </div>
			<div class="tab-pane active" id="tab4">
				<h4> Other Organizations</h4>
				<div class="panel-group" id="accordion2">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									<h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse4"> <span aria-hidden="true" class="icon-caret-right"></span> Communications Power </a> </h4>
								</div>
								<div class="col-md-6">
									<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
								</div>
							</div>
						</div>
						<div id="collapse4" class="panel-collapse collapse">
							<div class="panel-body">
								<table  class="table table-condensed">
								<tbody>
								<tr>
								<th class="col-md-3">Organization</th>
								<td>Communications Power</td>
								</tr>
								<tr>
								<th class="col-md-3">Address 1</th>
								<td>PO Box 50750 </td>
								</tr>
								<tr>
								<th>Address 2</th>
								<td>811 Hansen</td>
								</tr>
								<tr>
								<th>City</th>
								<td> Palo Alto</td>
								</tr>
								<tr>
								<th>State</th>
								<td>CA  </td>
								</tr>
								<tr>
								<th>ZIP</th>
								<td>&nbsp;</td>
								</tr>
								<tr>
								<th>Congressional District</th>
								<td>sdgdsdfgsdfg</td>
								</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									<h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse5"> <span aria-hidden="true" class="icon-caret-right"></span> Cleveland Clinic Organization </a> </h4>
								</div>
								<div class="col-md-6">
									<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
								</div>
							</div>
						</div>
						<div id="collapse5" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-condensed">
								<tbody>
								<tr>
								<th class="col-md-3">Organization</th>
								<td>Cleveland Clinic Organization</td>
								</tr>
								<tr>
								<th class="col-md-3">Address 1</th>
								<td>One Clinic Center </td>
								</tr>
								<tr>
								<th>Address 2</th>
								<td>9500 Euclid Ave</td>
								</tr>
								<tr>
								<th>City</th>
								<td>Orlando</td>
								</tr>
								<tr>
								<th>State</th>
								<td>FL  </td>
								</tr>
								<tr>
								<th>ZIP</th>
								<td>32826</td>
								</tr>
								<tr>
								<th>Congressional District</th>
								<td>
								</td>
								</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									<h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse6"> <span aria-hidden="true" class="icon-caret-right"></span> University of Washington </a> </h4>
								</div>
								<div class="col-md-6">
									<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
								</div>
							</div>
						</div>
						<div id="collapse6" class="panel-collapse collapse in">
							<div class="panel-body">
								<table class="table table-condensed">
								<tbody>
								<tr>
								<th class="col-md-3">Organization</th>
								<td>University of Washington</td>
								</tr>
								<tr>
								<th class="col-md-3">Address 1</th>
								<td>1410 NE Campus Pkwy</td>
								</tr>
								<tr>
								<th>Address 2</th>
								<td></td>
								</tr>
								<tr>
								<th>City</th>
								<td>Seattle</td>
								</tr>
								<tr>
								<th>State</th>
								<td>WA</td>
								</tr>
								<tr>
								<th>ZIP</th>
								<td>98195</td>
								</tr>
								<tr>
								<th>Congressional District</th>
								<td>
									<div class="col-md-9">
										<select name="keywords" id="keywords" class="form-control input-sm col-md-8" multiple>
											<option>CA - 01</option>
											<option>CA - 05</option>
											<option>CA - 12</option>
											<option>CA - 33</option>
											<option>HI - 01</option>
											<option>HI - 05</option>
											<option>HI - 12</option>
											<option>HI - 33</option>
											<option>IN - 01</option>
											<option>IN - 05</option>
											<option>IN - 12</option>
											<option>IN - 33</option>
											<option>MA - 01</option>
											<option>MA - 05</option>
											<option>MA - 12</option>
											<option>MA - 33</option>
											<option>NY - 01</option>
											<option>NY - 05</option>
											<option>NY - 12</option>
											<option>NY - 33</option>
											<option>WA - 01</option>
											<option>WA - 05</option>
											<option>WA - 12</option>
											<option>WA - 33</option>
										</select>
									</div>
								</td>
								</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="btn-row-widget-action">
	              <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution.php">Add Location</button>
	            </div>
			</div>            
          </div>
        </div>
        
        <!-- // --> 
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row --> <div class="container btn-row-page-action">
		<?php
		if ($alt && file_exists('prop.basics.sponsor-alt.php')) {
			echo '<button href="prop.basics.sponsor-alt.php" class="btn btn-default">Back</button>';
		} else {
			echo '<button href="prop.basics.sponsor.php" class="btn btn-default">Back</button>';
		}
		?>
		<button class="btn btn-default">Save</button>
		<button href="prop.keypersonnel.start.php" class="btn btn-primary">Save and Continue</button>
    </div>
    <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>





