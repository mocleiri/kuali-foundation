

                <form method="post" class="form-horizontal">
                  <div class="form-group clearfix">
                    <label for="type" class="control-label col-md-3">Type:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['type']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_status" class="control-label col-md-3">Approval status:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['approval_status']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="protocol_no" class="control-label col-md-3">Protocol number:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['protocol_no']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="application_date" class="control-label col-md-3">Application Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['application_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_date" class="control-label col-md-3">Approval Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['approval_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="expiration_date" class="control-label col-md-3">Expiration Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['expiration_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Exemption:</label>
                    <div class="col-md-9">
                      <p class="form-control-static">E2, E6</p>
                    </div>
                  </div>
                  <div class="btn-row-widget-action pull-right">
                    <button class="btn btn-primary btn-xs edit-entry" complianceId="<?php echo $id?>"> Edit</button>
                    <button class="btn btn-link btn-xs" id=""> Cancel</button>
                  </div>
                </form>

