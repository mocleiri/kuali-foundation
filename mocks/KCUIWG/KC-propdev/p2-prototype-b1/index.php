<?php
# Variables
$page = 'index';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

	<section id="main">

		<div class="container-fluid">
			
			<div class="row">
				<div class="col-md-12">
					<h2>Coeus 5.0</h2>
					<p>Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit, sed diam nonu mmy nibh euismodt. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit, sed diam nonu mmy nibh euismodt. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet.</p>
					<p>Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit, sed diam nonu mmy nibh euismodt. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit, sed diam nonu mmy nibh euismodt. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet.</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-9">
					<h3>Action List Preview</h3>
					<table class="table table-striped table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>Type</th>
								<th>Initiator</th>
								<th>Request</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="prop.basics.details.php">38365</a></td>
								<td>Proposal</td>
								<td>thrclark</td>
								<td><span class="label label-warning">Complete</span></td>
							</tr>
							<tr>
								<td><a href="#">38369</a></td>
								<td>Proposal</td>
								<td>khuntley</td>
								<td><span class="label label-warning">Complete</span></td>
							</tr>
							<tr>
								<td><a href="#">38370</a></td>
								<td>Proposal</td>
								<td>khuntley</td>
								<td><span class="label label-info">FYI</span></td>
							</tr>
							<tr>
								<td><a href="#">38371</a></td>
								<td>IRB Protocol</td>
								<td>khuntley</td>
								<td><span class="label label-info">FYI</span></td>
							</tr>
							<tr>
								<td><a href="#">38365</a></td>
								<td>IRB Protocol</td>
								<td>khuntley</td>
								<td><span class="label label-info">FYI</span></td>
							</tr>
							<tr>
								<td><a href="#">38369</a></td>
								<td>IRB Protocol</td>
								<td>khuntley</td>
								<td><span class="label label-success">Approve</span></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="col-md-3">
					<h3>Reports</h3>
					<strong>Metric 1</strong><span class="pull-right">30%</span>
					<div class="progress progress-info active" style="max-height:4px;">
						<div class="bar" style="width: 30%;"></div>
					</div>

					<strong>Metric 2</strong><span class="pull-right">40%</span>
					<div class="progress progress-info active" style="max-height:4px;">
						<div class="bar" style="width: 40%;"></div>
					</div>

					<strong>Metric 3</strong><span class="pull-right">70%</span>
					<div class="progress progress-info active" style="max-height:4px;">
						<div class="bar" style="width: 70%;"></div>
					</div>

					<strong>Metric 4</strong><span class="pull-right">5%</span>
					<div class="progress progress-info active" style="max-height:4px;">
						<div class="bar" style="width: 5%;"></div>
					</div>

					<strong>Metric 5</strong><span class="pull-right">85%</span>
					<div class="progress progress-info active" style="max-height:4px;">
						<div class="bar" style="width: 85%;"></div>
					</div>
				</div>
			</div>

			<?php require_once( 'themes/kc/inc/third-tier.php' ); ?>

		</div>

	</section>

	<?php require_once( 'themes/kc/inc/footer.php' ); ?>