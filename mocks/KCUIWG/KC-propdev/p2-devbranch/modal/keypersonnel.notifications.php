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
            <th>Recipient</th>
            <th>Last Notfication</th>
          </tr>
          <tr>
            <td>
                <input name="notify" type="checkbox" value="notify">
                Edward H Haskell</td>
            <td>Dec 06, 2013 (4:27pm)</td>
          </tr>
          <tr>
            <td>
                <input name="notify" type="checkbox" value="notify">
                Ward Cleaver</td>
            <td>Dec 03, 2013 (11:13am)</td>
          </tr>
          <tr>
            <td><input name="notify" type="checkbox" value="notify" />
              Jane Smith</td>
            <td>Dec 06, 2013 (4:27pm)</td>
          </tr>
          <tr>
            <td>
              <input type="checkbox" onclick="toggle(this)" />
Select All</td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div>
      
    <div class="modal-footer"> <a class="btn btn-primary" href="">Send Notifications</a> <a class="btn btn-link" href="">Cancel</a> </div>
      
    </form>
  </div>
</div>
<?php

# Includes
require_once( 'inc/footer.php' );
?>
