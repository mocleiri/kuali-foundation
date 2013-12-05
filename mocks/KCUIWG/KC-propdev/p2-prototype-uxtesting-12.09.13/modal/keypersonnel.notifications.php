<?php

# Includes
require_once( 'inc/head.php' );
?>


<script language="JavaScript">
function toggle(source) {
  checkboxes = document.getElementsByName('notify');
  for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}
</script>

<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h3 id="myModalLabel">Send Certification Requests</h3>
    </div>
    <form  class="form-horizontal" method="get" action="">
      <div class="modal-body">
       
        <table class="table table-condensed">
          
          <tr>
            <th><label style="font-weight:normal" class="">
                <input type="checkbox" onClick="toggle(this)">
                Select All </label></th>
          </tr>
          <tr>
            <td><label>
                <input name="notify" type="checkbox" value="notify">
                Edward H Haskell </label></td>
          </tr>
          <tr>
            <td><label>
                <input name="notify" type="checkbox" value="notify">
                Ward Cleaver </label></td>
          </tr>
          <tr>
            <td><label>
                <input name="notify" type="checkbox" value="notify">
                Jane Smith </label></td>
          </tr>
        </table>
      </div>
      
    <div class="modal-footer"> <a class="btn btn-primary" href="access.adduser.php">Send Notifications</a> <a class="btn btn-link" href="access.adduser.php">Cancel</a> </div>
      
    </form>
  </div>
</div>
<?php

# Includes
require_once( 'inc/footer.php' );
?>
