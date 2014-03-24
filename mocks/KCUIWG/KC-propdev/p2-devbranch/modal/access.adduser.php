<?php

# Includes
require_once( 'inc/head.php' );
?>
<!-- Continues with nav.php -->
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h3 id="myModalLabel">Access: Add User</h3>
    </div>
     <div class="modal-body" style="min-height:300px">
    <form  class="form-horizontal" method="get" action="">
 
  
      <fieldset>
        <legend style="display:none">Enter lookup criteria</legend>
        <div class="form-group clearfix">
          <label for="sponsor_code" class="control-label col-sm-3 col-xs-3" >KcPerson Id:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="sponsor_code" name="sponsor_code" class="form-control input-sm">
          </div>
        </div>
        <div class="form-group clearfix">
          <label for="sponsor_name" class="control-label col-sm-3 col-xs-3">Last Name:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm">
          </div>
        </div>
        <!-- <div class="form-group clearfix">
              <label for="sponsor_name" class="control-label col-sm-3 col-xs-3 col-md-offset-4 col-sm-offset-4">Sponsor Name:</label>
              <div class="col-sm-8 col-xs-8 col-md-offset-4 col-sm-offset-4">
                <input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm ">
              </div>
            </div>-->
        <div class="form-group clearfix">
          <label class="control-label col-sm-3 col-xs-3" for="accronym">First Name:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="accronym" name="accronym" class="form-control input-sm">
          </div>
        </div>
        <div class="form-group clearfix">
          <label class="control-label col-sm-3 col-xs-3" for="accronym">User Name:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="accronym" name="accronym" class="form-control input-sm">
          </div>
        </div>
        <div class="form-group clearfix">
          <label class="control-label col-sm-3 col-xs-3" for="accronym">Email:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="accronym" name="accronym" class="form-control input-sm">
          </div>
        </div>
        <div class="form-group clearfix">
          <label class="control-label col-sm-3 col-xs-3" for="accronym">Phone:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="accronym" name="accronym" class="form-control input-sm">
          </div>
        </div>
        <div class="form-group clearfix">
          <label class="control-label col-sm-3 col-xs-3" for="accronym">Home Unit:</label>
          <div class="col-sm-9 col-xs-9">
            <input type="text" id="accronym" name="accronym" class="form-control input-sm">
          </div>
        </div>
      </fieldset>
    </form>
    </div>
    
    <div class="modal-footer"> <a class="various fancybox.ajax btn btn-primary" data-fancybox-type="ajax" href="access.adduser.results.php">Search</a> <a href="access.php" class="btn btn-link">Cancel</a> </div>

  </div>
</div>


<?php

# Includes
require_once( 'inc/footer.php' );
?>