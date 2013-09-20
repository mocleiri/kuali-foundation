<?php
# Variables
$section = 'attachments';
$page = 'attach-proposal';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container-fluid"> 	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				
					<h3>Proposal Attachments</h3>
				

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Add attachments to this proposal</legend>
							<div class="form-group clearfix">
								<label for="file" class="control-label col-md-3">File:</label>
								<div class="col-md-5">
									<input type="file" name="file" id="file" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="attachment_type" class="control-label col-md-3">Attachment type:</label>
								<div class="col-md-5">
									<select name="attachment_type" id="attachment_type" class="form-control input-sm col-md-8 chzn">
										<option value="">select</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="status" class="control-label col-md-3">Status:</label>
								<div class="col-md-5">
									<select name="status" id="status" class="form-control input-sm col-md-8 chzn">
										<option value="">select</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="contact_name" class="control-label col-md-3">Contact name:</label>
								<div class="col-md-5">
									<input type="text" name="contact_name" id="contact_name" class="form-control input-sm col-md-8" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="uploaded_by" class="control-label col-md-3">Uploaded by:</label>
								<div class="col-md-5">
									<input type="text" name="uploaded_by" id="uploaded_by" class="form-control input-sm col-md-8" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="email_address" class="control-label col-md-3">Email address:</label>
								<div class="col-md-5">
									<input type="text" name="email_address" id="email_address" class="form-control input-sm col-md-8" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="posted_timestamp" class="control-label col-md-3">Posted timestamp:</label>
								<div class="col-md-5">
									<input type="text" name="posted_timestamp" id="posted_timestamp" class="form-control input-sm col-md-8" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="phone_number" class="control-label col-md-3">Phone number:</label>
								<div class="col-md-5">
									<input type="text" name="phone_number" id="phone_number" class="form-control input-sm col-md-8" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="comments" class="control-label col-md-3">Comments:</label>
								<div class="col-md-5">
									<textarea name="comments" id="comments" class="form-control input-sm col-md-8"></textarea>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="description" class="control-label col-md-3">Description:</label>
								<div class="col-md-5">
									<textarea name="description" id="description" class="form-control input-sm col-md-8"></textarea>
								</div>
							</div>
						</fieldset>
					</form>

					<div class="panel-group" id="accordion">
						<div class="panel">
							<div class="panel-heading">
								<h6 class="panel-title">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#file1">some_file_here.pdf</a>
								</h6>
							</div>
							<div id="file1" class="panel-collapse collapse">
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

			</div></div>

		

		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.compliance.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.attachments.personnel.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>