<form method="post" class="form-horizontal">
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Added by:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime']?>)</span></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="approval_status" class="control-label col-md-3">Note Topic:</label>
    <div class="col-md-9">
      <p class="form-control-static"><?php echo $entry['topic']?></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Note Text:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"><?php echo $entry['description']?></p>
    </div>
  </div>
</form>