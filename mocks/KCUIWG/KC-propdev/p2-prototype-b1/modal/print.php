<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3 id="myModalLabel">Print document</h3>
		</div>

		<form  class="form-horizontal" method="get" action="">
		<div class="modal-body">
			<fieldset>
				<legend>Grants.gov forms (0)</legend>
			</fieldset>

			<fieldset>
				<legend>Print Sponsor Form Packages (12)</legend>
				<table class="table table-condensed table-hover table-borderless">
					<tr>
						<th><input type="checkbox" id="select_all" name="select_all" class="checkbox-check-all" data-parent="table" /></th>
						<th>Attachment</th>
						<th>Description</th>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_001" name="att_s2s_001" class="checkbox" /></td>
						<td><label for="att_s2s_001">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Cover page</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_002" name="att_s2s_002" class="checkbox" /></td>
						<td><label for="att_s2s_002">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Period 1</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_003" name="att_s2s_003" class="checkbox" /></td>
						<td><label for="att_s2s_003">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Period 2</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_004" name="att_s2s_004" class="checkbox" /></td>
						<td><label for="att_s2s_004">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Period 3</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_005" name="att_s2s_005" class="checkbox" /></td>
						<td><label for="att_s2s_005">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Period 4</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_006" name="att_s2s_006" class="checkbox" /></td>
						<td><label for="att_s2s_006">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Period 5</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_007" name="att_s2s_007" class="checkbox" /></td>
						<td><label for="att_s2s_007">Generic Printing Forms (Coeus 4.x)</label></td>
						<td>Budget Summary Total</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_008" name="att_s2s_008" class="checkbox" /></td>
						<td><label for="att_s2s_008">NIH 398 package (Coeus 4.0)</label></td>
						<td>Facepage</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_009" name="att_s2s_009" class="checkbox" /></td>
						<td><label for="att_s2s_009">NIH 398 package (Coeus 4.0)</label></td>
						<td>Facepage continued</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_010" name="att_s2s_010" class="checkbox" /></td>
						<td><label for="att_s2s_010">NIH 398 package (Coeus 4.0)</label></td>
						<td>Page 2-Performance Sites Key Personnel</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_011" name="att_s2s_011" class="checkbox" /></td>
						<td><label for="att_s2s_011">NIH 398 package (Coeus 4.0)</label></td>
						<td>Additional Sites</td>
					</tr>
					<tr>
						<td><input type="checkbox" id="att_s2s_012" name="att_s2s_012" class="checkbox" /></td>
						<td><label for="att_s2s_012">NIH 398 package (Coeus 4.0)</label></td>
						<td>Key Personnel report</td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>Print Reports</legend>
				<table class="table table-condensed table-borderless">
					<tr>
						<td width="150">Current Report</td>
						<td>
							<div class="input-group">
								<input type="text" class="form-control input-sm" name="report_person" id="report_person" />
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="lookup-sponsor-detailspage.php" data-modal-height="500"></a>
								</span>
							</div>
						</td>
						<td><button type="button" class="btn btn-default btn-xs">Generate report</button></td>
					</tr>
					<tr>
						<td>Pending Report</td>
						<td>
							<div class="input-group">
								<input type="text" class="form-control input-sm" name="pending_report" id="pending_report" />
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="lookup-sponsor-detailspage.php" data-modal-height="500"></a>
								</span>
							</div>
						</td>
						<td><button type="button" class="btn btn-default btn-xs">Generate report</button></td>
					</tr>
				</table>
			</fieldset>
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-primary">Print selected</button>
			<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
		</div>
		</form>
	</div>
</div>

<?php

# Includes
require_once( 'inc/footer.php' );
?>