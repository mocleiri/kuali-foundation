<?php

# Includes
require_once( 'inc/head.php' );
?>

<style>


.dataTables_filter, .dataTables_length { display:none}

</style>



<div class="modal-dialog">
  <div class="modal-content">
     <div class="modal-header clearfix">
      <h3 class="pull-left"> Audit Mode </h3>
      <a href="audtimode-on.php"class="btn btn-default btn-xs pull-right btn-danger" style="margin-right:40px">Turn On</a>
    </div>
    <div class="modal-body">Audit Mode is currently off.</div>
    <div class="modal-footer"> </div>
  </div>
</div>
</div>
<?php require_once( 'inc/footer.php' ); ?>