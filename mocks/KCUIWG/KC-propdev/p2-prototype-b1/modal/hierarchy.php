<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog" id="main" style="padding:0px">
	<div class="modal-content">
		<div class="modal-header">
			<h3 id="myModalLabel">Hierarechy</h3>
		</div>
		<form  class="form-horizontal" method="get" action="">
			<div class="modal-body">
				<p>You are currently viewing Proposal # 18 (Document # 3464), which is currently unlinked to a proposal hierarchy.</p>
				<fieldset>
					<legend class="off-screen">Hierarchy</legend>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3" for="proposal">Link Child Proposal</label>
						<div class="col-sm-9 col-xs-9">
							<input type="text" class="form-control" placeholder="">
						</div>
					</div>
					<div class="form-group clearfix">
						<label class="control-label col-sm-3 col-xs-3 required" for="budget">Link Budget Type</label>
						<div class="col-sm-9 col-xs-9">
							<select name="newHierarchyBudgetTypeCode" tabindex="3" onchange="" onblur="" id="newHierarchyBudgetTypeCode" style="" class="" title="Hierarchy Budget Type">
								<option value="B" selected="selected">Sub-Budget</option>
								<option value="P">Sub-Project</option>
							</select>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="modal-footer"> <a class="various  btn btn-primary" data-fancybox-type="ajax" href="">Link to Hierarchy</a> <a class="various  btn btn-default" data-fancybox-type="ajax" href="">Create Hierarchy</a>
				<button type="button" class="btn btn-link fancy-close">Close</button>
			</div>
		</form>
	</div>
</div>
<?php

# Includes
require_once( 'inc/footer.php' );
?>
