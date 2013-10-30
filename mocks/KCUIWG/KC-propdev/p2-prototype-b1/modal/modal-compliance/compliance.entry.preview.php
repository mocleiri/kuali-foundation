<div class="panel panel-default">
            <div class="panel-heading">
              <div class="row">
                <div class="col-md-3">
                  <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $key?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a> </h4>
                </div>
                <div class="col-md-2"> <?php echo $entry['approval_status']?> </div>
                <div class="col-md-3"><?php echo $entry['protocol_no']?></div>
                <div class="col-md-3"> Exp: <?php echo $entry['expiration_date']?> </div>
                <div class="col-md-1"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
              </div>
            </div>
            <div id="collapse<?php echo $key?>" class="panel-collapse collapse">
              <div class="panel-body">

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
                    <button class="btn btn-primary btn-xs" id=""> Edit</button>
                    <button class="btn btn-link btn-xs" id=""> Cancel</button>
                  </div>
                </form>

                </div>
            </div>
          </div>