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
<style type="text/css">



</style>



</head>







<body id="Uif-Application">




<div style="width:1200px; margin-left:auto; margin-right:auto; margin-top:100px">
<!-- Modal -->

	<!-- Modal -->
	
	
	
<!--<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->



	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"> My Action List</h4>
			</div>
			<div class="modal-body">
				<p class="pull-left lead">You currently have 12 actionalble items.</p>
				<p class="pull-right"><span aria-hidden="true" class="icon-cog" style="color:#999"></span> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-toggle="modal" data-target="#actionlist-prefs" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"  data-dismiss="modal">Preferences</a> </p>
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
					<li class="active"><a href="#actions-current" data-toggle="tab">Current Items</a></li>
					<li><a href="#actions-completed" data-toggle="tab">Completed Actions</a></li>
				</ul>
				<div id="my-tab-content" class="tab-content clearfix">
					<div class="tab-pane active" id="actions-current">
						<h4>Current Items</h4>
						<table id="u569ish_line0" class="table table-condensed table-bordered table-hover" role="presentation">
							<tbody>
								<tr>
									<th class="">Route Status</th>
									<th scope="row">ID</th>
									<th scope="row">Type</th>
									<th class="uif-gridLayoutCell">Title</th>
									<th class="uif-gridLayoutCell">Action Requested</th>
									<th class="uif-gridLayoutCell">Created</th>
									<th style="width:140px" class="uif-gridLayoutCell">Action</th>
								</tr>
								<tr class>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3231</td>
									<td scope="row">KC Award</td>
									<td class="">My Research Award</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3273</td>
									<td scope="row">KC Protocol</td>
									<td class="">Test Title </td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3237</td>
									<td scope="row">KC Award</td>
									<td class="">Another Research Award</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3355</td>
									<td scope="row">KC Protocol</td>
									<td class="">Institutional Protocol1</td>
									<td class="">Complete</td>
									<td> 03/10/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-danger"> Disapproved </span></td>
									<td scope="row">3308</td>
									<td scope="row">KC Protocol</td>
									<td class="">Institutional Protocol2</td>
									<td class="">Complete</td>
									<td> 03/10/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-danger"> Disapproved </span></td>
									<td scope="row">3333</td>
									<td scope="row">KC Award Budget</td>
									<td class="">My Research Award</td>
									<td class="">Approve</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-danger"> Disapproved </span></td>
									<td scope="row">3454</td>
									<td scope="row">KC Award</td>
									<td class="">Test Title </td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-warning">Exception</span></td>
									<td scope="row">3247</td>
									<td scope="row">KC Award Budget</td>
									<td class="">Another Research Award</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-warning">Exception</span></td>
									<td scope="row">3245</td>
									<td scope="row">KC Award Budget</td>
									<td class="">Institutional Protocol1</td>
									<td class="">Complete</td>
									<td> 03/10/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-primary"> Saved </span></td>
									<td scope="row">3239</td>
									<td scope="row">KC Award</td>
									<td class="">Institutional Protocol2</td>
									<td class="">Complete</td>
									<td> 03/10/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-primary"> Saved </span></td>
									<td scope="row">3388</td>
									<td scope="row">KC Protocol</td>
									<td class="">My Research Award</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-primary"> Saved </span></td>
									<td scope="row">3316</td>
									<td scope="row">KC Award</td>
									<td class="">Test Title </td>
									<td class="">Approve</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">View Routing</a> <a href="#" class="btn btn-xs btn-primary">Open</a></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="actions-completed">
						<h4> Completed Actions</h4>
						<table id="u569ish_line0" class="table table-condensed table-bordered table-hover" role="presentation">
							<tbody>
								<tr>
									<th class="">Route Status</th>
									<th scope="row">ID</th>
									<th scope="row">Type</th>
									<th class="uif-gridLayoutCell">Title</th>
									<th class="uif-gridLayoutCell">Action Requested</th>
									<th class="uif-gridLayoutCell">Created</th>
									<th style="width:140px" class="uif-gridLayoutCell">Action</th>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3355</td>
									<td scope="row">KC Protocol</td>
									<td class="">Test Title </td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3308</td>
									<td scope="row">KC Award</td>
									<td class="">Another Research Award</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
								</tr>
								<tr>
									<td class=""><span class="label label-success"> Approved </span></td>
									<td scope="row">3333</td>
									<td scope="row">KC Protocol</td>
									<td class="">Institutional Protocol1</td>
									<td class="">Complete</td>
									<td> 03/08/2014</td>
									<td class=""><a href="#" class="btn btn-xs btn-default">Remove from List</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Close</a> </div>
		</div>
	</div>
	
	
	
<!--</div>-->





<!-- end Modal -->


<!-- end Modal --></div>









<?php include ('includes/footer-scripts.php') ?>

<!-- Modal --><!-- end Modal -->

</body>
</html>