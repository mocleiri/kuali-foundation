<?php
# Variables
$section = 'attachments';
$page = 'attach-personnel';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->

				
					<h3>Personnel Attachments</h3>
				

				
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
								<label for="person" class="control-label col-md-3">Person:</label>
								<div class="col-md-5">
									<select name="person" id="person" class="form-control input-sm col-md-8 chzn">
										<option value="">select</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="attachment_type" class="control-label col-md-3">Attachmenet type:</label>
								<div class="col-md-5">
									<select name="attachment_type" id="attachment_type" class="form-control input-sm col-md-8 chzn">
										<option value="">select</option>
									</select>
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
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row --> <div class="container btn-row-page-action"> <button  onclick="location.href='prop.attachments.proposal.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.attachments.internal.php'" class="btn btn-primary">Save and Continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>





