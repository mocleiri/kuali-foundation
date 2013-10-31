<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3 id="myModalLabel">Copy To New Document</h3>
		</div>

		<form  class="form-horizontal" method="get" action="">
		<div class="modal-body">
			<fieldset>
				<legend class="off-screen">Copy document</legend>
				<table class="table table-condensed table-borderless">
					<tr>
						<td><label class="control-label col-lg-2" for="proposal">Proposal</label></td>
						<td><input type="text" id="attachments" placeholder="" class="form-control input-sm disabled" value="Yes" disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label class="control-label col-lg-2" for="budget"> Budget?</label></td>
						<td>
							<select name="select" id="budget" class="form-control input-sm" title="Budget" required="required">
								<option value="" selected="selected">all versions</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label class="control-label col-lg-2" for="lead-unit"> * Lead Unit</label></td>
						<td>
							<select name="lead-unit" id="lead-unit" style="" class="form-control input-sm" title="* Lead Unit" required="required">
								<option value="">Select</option>
								<option value="000001">000001 - University</option>
								<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY</option>
								<option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
								<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label class="control-label col-lg-2" for="attachments1">Attachments</label></td>
						<td><input type="checkbox" id="attachments1" class="checkbox pull-left" /> Yes, include attachments</td>
					</tr>
					<tr>
						<td><label class="control-label col-lg-2" for="questionaires">Questionaires</label></td>
						<td><input type="checkbox" id="questionaires" class="checkbox pull-left" /> Yes, include questionnaires</td>
					</tr>
				</table>
			</fieldset>
		</div>

		<div class="modal-footer">
			<a class="various fancybox.ajax btn btn-primary" data-fancybox-type="ajax" href="modal/copied-document.html">Copy</a>
			<button type="button" class="btn btn-link">Close</button>
		</div>
		</form>
	</div>
</div>

<?php

# Includes
require_once( 'inc/footer.php' );
?>