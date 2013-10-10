<?php include ('head.php') ?>

<body>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Opportunity Search</h3>
		</div>

		<div class="modal-body">
			<table class="table table-condensed table-hover table-smaller-text">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>CFDA Number</th>
						<th align="right">Opening Date</th>
						<th>Closing Date</th>
						<th>Competition ID</th>
						<th align="right"> Opportunity ID</th>
						<th align="right"> Title</th>
						<th align="right">View</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a class="load-tabs btn btn-default btn-xs" href="../prop.basics.oppsearch-search-done.php" target="_parent">Select</a></td>
						<td >00.000</td>
					  <td>12/31/2013</td>
						<td >12/31/2014</td>
						<td >NEWRRFORM</td>
						<td>PA-13-302</td>
					  <td>testing new SF424 RR form</td>
					  <td><a href="#">Schema URL</a> | <a href="#">Instructions</a></td>
					</tr>
					<tr>
						<td><a class="load-tabs btn btn-default btn-xs" href="../prop.basics.oppsearch-search-done.php" target="_parent">Select</a></td>
						<td >00.000</td>
						<td>12/31/2013</td>
						<td >12/31/2014</td>
						<td >NEWRRFORM</td>
						<td>PA-13-302</td>
						<td>testing new SF424 RR form</td>
						<td><a href="#">Schema URL</a> | <a href="#">Instructions</a></td>
					</tr>
					<tr>
						<td><a class="load-tabs btn btn-default btn-xs" href="../prop.basics.oppsearch-search-done.php" target="_parent">Select</a></td>
						<td >00.000</td>
						<td>12/31/2013</td>
						<td >12/31/2014</td>
						<td >NEWRRFORM</td>
						<td>PA-13-302</td>
						<td>testing new SF424 RR form</td>
						<td><a href="#">Schema URL</a> | <a href="#">Instructions</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="modal-footer">
			<a href="lookup-oppsearch.php" class="btn btn-primary pull-right" id="btn-newsearch">New Search</a>
		</div>
	</div>
</div>

<?php include ('footer.php') ?>