<?php include ('head.php') ?>

<body>
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
              <label for="sponsor_code" class="control-label col-sm-4 col-xs-4" >Sponsor code:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="sponsor_code" name="sponsor_code" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="sponsor_name" class="control-label col-sm-4 col-xs-4">Sponsor name:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="accronym">Acronym:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="accronym" name="accronym" class="form-control input-sm">
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="sponsor_type_code">Sponsor type code:</label>
              <div class="col-sm-8 col-xs-8">
                <select name="proposal_type" id="proposal_type" class="form-control input-sm col-md-8">
                  <option value="4">Continuation</option>
                  <option value="1" selected="selected">New</option>
                  <option value="3">Renewal</option>
                  <option value="2">Resubmission</option>
                  <option value="5">Revision</option>
                  <option value="6">Task Order</option>
                </select>
              </div>
            </div>
            <div class="form-group clearfix">
              <label class="control-label col-sm-4 col-xs-4"for="postal_code">Postal code:</label>
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
              <label class="control-label col-sm-4 col-xs-4"for="country">Country code:</label>
              <div class="col-sm-8 col-xs-8">
                <input type="text" id="country" name="country" class="form-control input-sm">
              </div>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
    <div class="modal-footer"> <a href="lookup-sponsor-results.php" class="btn btn-primary pull-right">Search</a> </div>
  </div>
</div>

<?php include ('footer.php') ?>