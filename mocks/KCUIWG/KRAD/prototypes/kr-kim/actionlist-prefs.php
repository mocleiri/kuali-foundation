<?php
$section = 'asdf';
$page = 'asdf';
?>

<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css"></style>
</head>

<body id="Uif-Application">
<div style="width:1200px; margin-left:auto; margin-right:auto; margin-top:100px"> 
	<!-- Modal --> 
	
	<!-- Modal --> 
	
	<!--<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
	
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel"> Action List Preferences</h4>
			</div>
			<div class="modal-body uif-cssGridSection">
				<p class="pull-right"><a href="#"> <span aria-hidden="true" class="icon-arrow-left" style="color:#999"></span> Return to List</a></p>
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
					<li class="active"><a href="#general" data-toggle="tab">General</a></li>
					<li><a href="#rowhighlight" data-toggle="tab">Row Highlighting</a></li>
					<li><a href="#email" data-toggle="tab">Email Notifications</a></li>
				</ul>
				<div id="my-tab-content" class="tab-content clearfix">
					<div class="tab-pane active" id="general">
						<h4>General</h4>
						<div class="form-horizontal" role="form">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group ">
										<label for="inputEmail3" class="col-sm-2 control-label">Displayed Columns</label>
										<div class="col-sm-9 well">
											<div class="row">
												<div class="col-sm-4">
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Document Type </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Title </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Action Requested </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Initiator </label>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" >
															Delegator </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Date Created </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Date Approved </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Current Route Node(s) </label>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" >
															WorkGroup Request </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Document Route Status </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" >
															Clear FYI </label>
													</div>
													<div class="checkbox">
														<label>
															<input type="checkbox" value="" checked>
															Use Outbox </label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">List Refresh Rate</label>
										<div class="col-sm-2">
											<select class="form-control">
												<option>never</option>
												<option>1 min</option>
												<option>5 min</option>
												<option>10 min</option>
												<option>1 hour</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Items per Page</label>
										<div class="col-sm-2">
											<select class="form-control">
												<option value="Primary Delegates only on Filter Page" selected>10</option>
												<option value="Primary Delegates on Action List Page">25</option>
												<option value="Primary Delegates on Action List Page">100</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Delegator Filter</label>
										<div class="col-sm-4">
											<select class="form-control">
												<option value="Secondary Delegators only on Filter Page">Secondary Delegators only on Filter Page</option>
												<option value="Secondary Delegators on Action List Page" selected="selected">Secondary Delegators on Action List Page</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Primary Delegate Filter</label>
										<div class="col-sm-4">
											<select class="form-control">
												<option value="Primary Delegates only on Filter Page">Primary Delegates only on Filter Page</option>
												<option value="Primary Delegates on Action List Page" selected="selected">Primary Delegates on Action List Page</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="rowhighlight">
						<h4>Row Highlighting</h4>
						<p>Stuff goes here</p>
					</div>
					<div class="tab-pane" id="email">
						<h4>Email Notifications</h4>
						<div class="form-horizontal" role="form">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Send Emails</label>
										<div class="col-sm-4">
											<select class="form-control">
												<option value="no">Never</option>
												<option value="daily">Daily</option>
												<option value="weekly">Weekly</option>
												<option value="immediate" selected="selected">Immediately</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<div class="checkbox">
												<label>
													<input type="checkbox">
													Receive Primary Delegate Emails </label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<div class="checkbox">
												<label>
													<input type="checkbox">
													Receive Secondary Delegate Emails </label>
											</div>
										</div>
									</div>
									
									
									
									
									
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Doc Type Notifications </label>
										<div class="col-sm-6">
										
										
										
										
										
										
										
										<table id="u569ish_line0" class="table table-condensed" role="presentation">
							<tbody>
								<tr>
									<th scope="row">Doc Type</th>
									<th class="uif-gridLayoutCell">Frequency</th>
									<th class="uif-gridLayoutCell" style="width:30px;">Actions</th>
								</tr>
								<tr>
									<td scope="row">Identity Management Role</td>
									<td class="">Weekly</td>
									<td class=""> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
								<tr>
									<td scope="row">Term Maintenance </td>
									<td class="">Weekly</td>
									<td class=""> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
								<tr>
									<td scope="row">State Maintenance</td>
									<td class="">Daily</td>
									<td class=""> <a href="#" class="btn btn-xs btn-default">Delete</a></td>
								</tr>
								<tr>
									<td scope="row">&nbsp;</td>
									<td class="">&nbsp;</td>
									<td class=""><a href="#" class="btn btn-xs btn-default"> <span class="icon-plus" style="font-size:10px"></span> Add New</a> </td>
								</tr>
							</tbody>
						</table>
					
					
					
					
					
										</div>
									</div>
									
									
									
									
									
									
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer"> <a href="" class="btn btn-primary" data-dismiss="">Save Changes</a> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> </div>
			</div>
		</div>
	</div>
</div>

<!--</div>--> 

<!-- end Modal --> 

<!-- end Modal -->
</div><?php include ('includes/footer-scripts.php') ?>
<!-- Modal --><!-- end Modal -->

</body>
</html>