<?php
# Variables
$section = 'attachments';
$page = 'attach-notes';

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

			<div class="col-md-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Notes</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Add notes to this proposal</legend>
							<div class="form-group clearfix">
								<label for="note_topic" class="control-label col-md-3">Note topic:</label>
								<div class="col-md-5">
									<input type="text" name="note_topic" id="note_topic" class="form-control input-sm" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="note_text" class="control-label col-md-3">Note text:</label>
								<div class="col-md-5">
									<textarea name="note_text" id="note_text" class="form-control input-sm col-md-8"></textarea>
								</div>
							</div>
						</fieldset>
					</form>

					<div class="panel-group" id="accordion">
						<div class="panel">
							<div class="panel-heading">
								<h6 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#file1">some_file_here.pdf</a>
								</h6>
							</div>
							<div id="file1" class="panel-collapse collapse in">
								<div class="panel-body">
									<dl>
										<dt>File:</dt>
										<dd>some_file_here.pdf</dd>

										<dt>Attachment type:</dt>
										<dd>Bibiolography</dd>

										<dt>Stats:</dt>
										<dd>Incomplete</dd>

										<dt>Contact name:</dt>
										<dd>Tom Clark</dd>

										<dt>Uploaded by:</dt>
										<dd>thrclark</dd>

										<dt>Email address:</dt>
										<dd>thrclark@indiana.edu</dd>

										<dt>Posted timestamp:</dt>
										<dd>2013-08-14 T19:33:30+00:00</dd>

										<dt>Phone number:</dt>
										<dd>123-456-7899</dd>

										<dt>Comments:</dt>
										<dd>Accipe sacrificiu confessionum mearum de manu</dd>

										<dt>Description:</dt>
										<dd>um: sed solivs earn, cum voles, aut misearans</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="panel">
							<div class="panel-heading">
								<h6 class="panel-title">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#file2">some_file_here.pdf</a>
								</h6>
							</div>
							<div id="file2" class="panel-collapse collapse">
								<div class="panel-body">
									<dl>
										<dt>File:</dt>
										<dd>some_file_here.pdf</dd>

										<dt>Attachment type:</dt>
										<dd>Bibiolography</dd>

										<dt>Stats:</dt>
										<dd>Incomplete</dd>

										<dt>Contact name:</dt>
										<dd>Tom Clark</dd>

										<dt>Uploaded by:</dt>
										<dd>thrclark</dd>

										<dt>Email address:</dt>
										<dd>thrclark@indiana.edu</dd>

										<dt>Posted timestamp:</dt>
										<dd>2013-08-14 T19:33:30+00:00</dd>

										<dt>Phone number:</dt>
										<dd>123-456-7899</dd>

										<dt>Comments:</dt>
										<dd>Accipe sacrificiu confessionum mearum de manu</dd>

										<dt>Description:</dt>
										<dd>um: sed solivs earn, cum voles, aut misearans</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="panel">
							<div class="panel-heading">
								<h6 class="panel-title">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#file3">some_file_here.pdf</a>
								</h6>
							</div>
							<div id="file3" class="panel-collapse collapse">
								<div class="panel-body">
									<dl>
										<dt>File:</dt>
										<dd>some_file_here.pdf</dd>

										<dt>Attachment type:</dt>
										<dd>Bibiolography</dd>

										<dt>Stats:</dt>
										<dd>Incomplete</dd>

										<dt>Contact name:</dt>
										<dd>Tom Clark</dd>

										<dt>Uploaded by:</dt>
										<dd>thrclark</dd>

										<dt>Email address:</dt>
										<dd>thrclark@indiana.edu</dd>

										<dt>Posted timestamp:</dt>
										<dd>2013-08-14 T19:33:30+00:00</dd>

										<dt>Phone number:</dt>
										<dd>123-456-7899</dd>

										<dt>Comments:</dt>
										<dd>Accipe sacrificiu confessionum mearum de manu</dd>

										<dt>Description:</dt>
										<dd>um: sed solivs earn, cum voles, aut misearans</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="panel">
							<div class="panel-heading">
								<h6 class="panel-title">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#file4">some_file_here.pdf</a>
								</h6>
							</div>
							<div id="file4" class="panel-collapse collapse">
								<div class="panel-body">
									<dl>
										<dt>File:</dt>
										<dd>some_file_here.pdf</dd>

										<dt>Attachment type:</dt>
										<dd>Bibiolography</dd>

										<dt>Stats:</dt>
										<dd>Incomplete</dd>

										<dt>Contact name:</dt>
										<dd>Tom Clark</dd>

										<dt>Uploaded by:</dt>
										<dd>thrclark</dd>

										<dt>Email address:</dt>
										<dd>thrclark@indiana.edu</dd>

										<dt>Posted timestamp:</dt>
										<dd>2013-08-14 T19:33:30+00:00</dd>

										<dt>Phone number:</dt>
										<dd>123-456-7899</dd>

										<dt>Comments:</dt>
										<dd>Accipe sacrificiu confessionum mearum de manu</dd>

										<dt>Description:</dt>
										<dd>um: sed solivs earn, cum voles, aut misearans</dd>
									</dl>
								</div>
							</div>
						</div>
					</div>
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