 <!-- Modal -->
<div class="modal fade" id="createNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="nameBudget">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
				<h4 class="modal-title" id="myModalLabel">
					Create a Budget Version
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Parent Proposal</label> 
						<div class="col-sm-9">
							<p class="form-control-static">
								#23533 
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">Budget Name</label> 
						<div class="col-sm-9">
							<input type=" text" class="form-control" id="" placeholder="">
						</div>
					</div>
					<fieldset id="casual-user-options">
						<p>
							<b>Would you like to create a detailed budget or enter a summary only?</b> 
						</p>
						<p>
							<label for="radio-start-detail-budget"> 
							<input type="radio" name="radio-start-budget" value="detail" id="radio-start-detail-budget">
							Start a detailed budget</label> 
						</p>
						<p>
							<label for="radio-start-summary-budget"> 
							<input type="radio" name="radio-start-budget" value="summary" id="radio-start-summary-budget">
							Start a summary budget</label> 
						</p>
						<p class="topMargin1em">
							Will this be a modular budget? <label> 
							<input type="radio" name="radio-modular-budget" value="modular" id="radio-modular-budget_yes">
							Yes</label> <label> 
							<input type="radio" name="radio-modular-budget" value="periods" id="radio-modular-budget_no">
							No</label> 
						</p>
					</fieldset>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> 
				<div class="btn-group">
					<a href="budget-ng-start.php" type="button" class="btn btn-primary" id="budgetChoices">Create</a> 
<!-- <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> Create <span class="caret"></span> </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Create and stay on Proposal</a></li>
                                            <li><a href="budget-ng-start.php">Create and go to Budget</a></li>
                                        </ul> -->
				</div>
			</div>
		</div>
	</div>
</div>


<!-- CASUAL USER FLOW -->
<style type="text/css">
#casual-user-options{margin:10px 5px 5px 40px;}
</style>
<script>
(function($){
			
	 $("#budgetChoices").click(function (e) {	
	  e.preventDefault();		
	
	
		if ($("#radio-start-summary-budget").is(":checked") & $("#radio-modular-budget_yes").is(":checked") ){
			document.location.href='budget-ng-periods.php?modular-budget=yes&amp;summary=yes';
			}
				
		
		else if ($("#radio-start-summary-budget").is(":checked") & $("#radio-modular-budget_no").is(":checked") ){
			document.location.href='budget-ng-periods.php?modular-budget=no&amp;summary=yes';
			}
		
		
			else if ($("#radio-start-detail-budget").is(":checked") & $("#radio-modular-budget_no").is(":checked") ){
			document.location.href='budget-ng-personnelCosts-projPersonnel1.php?modular-budget=no&amp;detail=yes';
			}	
		
		else if ($("#radio-start-detail-budget").is(":checked") & $("#radio-modular-budget_yes").is(":checked") ){
			document.location.href='budget-ng-personnelCosts-projPersonnel1.php?modular-budget=yes&amp;detail=yes';
			}
			
		
		else{
	
				$(".topMargin1em").addClass("shake").one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
				$(this).removeClass("shake");
				});
			
			}		
  });


$(".tabWarning").click(function(e){
  alert("[MODAL WINDOW] --- You are about to enter costs in a later period. Doing so will prevent you from using the generate all periods function to auto-calculate later periods. Do you wish to continue? {{ CANCEL (button)  | CONTINUE (button) }} ---[MODAL WINDOW]");
return false;
});


}(jQuery))

</script>
<!-- //END CASUAL USER FLOW -->
