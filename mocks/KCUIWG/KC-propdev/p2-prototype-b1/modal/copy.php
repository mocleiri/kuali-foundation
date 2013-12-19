<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog" id="main" style="padding:0px">
	<div class="modal-content">
		<div class="modal-header">
			<h3 id="myModalLabel">Copy To New Document</h3>
		</div>
		<form  class="form-horizontal" method="get" action="">
			<div class="modal-body">
				<fieldset>
					<legend class="off-screen">Copy document</legend>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3" for="proposal">Proposal</label>
						<div class="col-sm-9 col-xs-9">
							<input type="text" id="attachments" placeholder="" class="form-control input-sm disabled" value="Yes" disabled="disabled" />
						</div>
					</div>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3 required" for="budget"> Budget?</label>
						<div class="col-sm-9 col-xs-9">
							<select name="select" id="budget" class="form-control input-sm" title="Budget" required>
								<option value="" selected="selected">all versions</option>
							</select>
						</div>
					</div>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3 required" for="lead-unit"> Lead Unit <span class="req">*</span></label>
						<div class="col-sm-9 col-xs-9">
							<select name="lead-unit" id="lead-unit" style="" class="form-control input-sm" title="* Lead Unit" required>
								<option value="">Select</option>
								<option value="000001">000001 - University</option>
								<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY</option>
								<option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
								<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
							</select>
						</div>
					</div>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3" for="attachments1">Attachments</label>
						<div class="col-sm-9 col-xs-9">
							<div class="checkbox">
								<label>
									<input type="checkbox">
									Yes, include attachments </label>
							</div>
						</div>
					</div>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3" for="questionaires">Questionnaires</label>
						<div class="col-sm-9 col-xs-9">
							<div class="checkbox">
								<label>
									<input type="checkbox">
									Yes, include questionnaires </label>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="modal-footer"> <a class="various fancybox.ajax btn btn-primary" data-fancybox-type="ajax" href="modal/copied-document.html">Copy</a>
				<button type="button" class="btn btn-link fancy-close">Close</button>
			</div>
		</form>
	</div>
</div>
<?php

# Includes
require_once( 'inc/footer.php' );
?>
