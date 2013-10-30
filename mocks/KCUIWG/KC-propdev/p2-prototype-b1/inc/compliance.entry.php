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

                    <?php include "compliance.entry.preview.php";?>

                </div>
            </div>
          </div>