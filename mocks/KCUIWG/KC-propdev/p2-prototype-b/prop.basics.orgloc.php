<?php
# Variables
$page = 'basics-orgloc';

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
					<h2>Organization / Location</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<h3>Applicant Organization <span><a href="#">Change</a></span></h3>
						<dl>
							<dt>Organization</dt>
							<dd>UNIVERSITY OF MICHIGAN</dd>

							<dt>Address 1</dt>
							<dd>2044 Wolverine Tower</dd>

							<dt>Address 2</dt>
							<dd>3003 State Street</dd>

							<dt> City</dt>
							<dd>ANN ARBOR</dd>

							<dt>State</dt>
							<dd>MI</dd>

							<dt>Zip</dt>
							<dd>48109-1273</dd>

							<dt>Congressional District</dt>
							<dd>MI-302 <button class="btn btn-danger btn-xs" href="#">- Delete</button></dd>

							<dt>Congressional District</dt>
							<dd>MI-307 <button class="btn btn-danger btn-xs" href="#">- Delete</button></dd>

							<dt>Congressional District</dt>
							<dd><button class="btn btn-success btn-xs" href="#">+ Add</button></dd>
						</dl>

						<h3>Performing Organization <span><a href="#">Change</a></span></h3>
						<dl>
							<dt>Organization</dt>
							<dd>UNIVERSITY OF MICHIGAN</dd>

							<dt>Address 1</dt>
							<dd>2044 Wolverine Tower</dd>

							<dt>Address 2</dt>
							<dd>3003 State Street</dd>

							<dt> City</dt>
							<dd>ANN ARBOR</dd>

							<dt>State</dt>
							<dd>MI</dd>

							<dt>Zip</dt>
							<dd>48109-1273</dd>

							<dt>Congressional District</dt>
							<dd>MI-302 <button class="btn btn-danger btn-xs" href="#">- Delete</button></dd>

							<dt>Congressional District</dt>
							<dd>MI-307 <button class="btn btn-danger btn-xs" href="#">- Delete</button></dd>

							<dt>Congressional District</dt>
							<dd><button class="btn btn-success btn-xs" href="#">+ Add</button></dd>
						</dl>
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