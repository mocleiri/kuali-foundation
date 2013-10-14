<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Sponsor Search</h3>
    </div>
    <div class="modal-body">
      <div class="container">
        <form class="form-horizontal" method="get" action="">
          <fieldset>
            <legend style="display:none">Enter lookup criteria</legend>
            <div class="form-group clearfix">
              <label for="sponsor_code" class="control-label col-sm-4 col-xs-4" >Sponsor Code:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="sponsor_code" name="sponsor_code" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="sponsor_name" class="control-label col-sm-4 col-xs-4">Sponsor Name:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm">
              </div>
            </div>
           <!-- <div class="form-group clearfix">
              <label for="sponsor_name" class="control-label col-sm-4 col-xs-4 col-md-offset-4 col-sm-offset-4">Sponsor Name:</label>
              <div class="col-sm-8 col-xs-8 col-md-offset-4 col-sm-offset-4">
                <input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm ">
              </div>
            </div>-->
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="accronym">Acronym:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="accronym" name="accronym" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="sponsor_type_code">Sponsor Type:</label>
              <div class="col-sm-8 col-xs-8">
                <select name="proposal_type" id="proposal_type" class="form-control input-sm col-md-8">
                  <option selected>Select</option>
                  <option value="0">Federal</option>
                  <option value="1">State</option>
                  <option value="2">Local Government</option>
                  <option value="3">Private Profit</option>
                  <option value="4">Private Non-Profit</option>
                  <option value="5">Foundation</option>
                  <option value="6">Institution of Higher Education</option>
                  <option value="10">Foreign Federal Government</option>
                  <option value="11">Foreign State Government</option>
                  <option value="12">Foreign Local Government</option>
                  <option value="13">Foreign Private Profit</option>
                  <option value="14">Foreign Private Non-Profit</option>
                  <option value="15">Foreign Foundation</option>
                  <option value="16">Foreign Institution of Higher Education</option>
                </select>
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="country">Address Book ID:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="country" name="country" class="form-control input-sm">
                <span class="help-block"><a href="#">Find an ID</a></span> </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="postal_code">Postal Code:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="postal_code" name="postal_code" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="state">State:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="state" name="state" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="country">Country Code:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="country" name="country" class="form-control input-sm">
              </div>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
    <div class="modal-footer"> <a href="lookup-sponsor-startpage-results.php" class="btn btn-primary btn-sm">Search</a> <a href="#" class="btn btn-link btn-sm">Cancel</a></div>
  </div>
</div>
<?php require_once( 'inc/footer.php' ); ?>