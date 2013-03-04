<?php require_once( 'assets/inc/head.php' ) ?>

<!-- Modal -->
 <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"> Employee Search</h3>
  </div>
<div class="modal-body" style="min-height:312px">
  <form class="form-horizontal">
    <fieldset>
      <div class="control-group">
        <!-- Text input-->
        <label class="control-label" for="input3">KcPerson Id:</label>
      <div class="controls">
        <input type="text" placeholder="" class="input-xlarge">
        
      </div>
</div>
    <div class="control-group">

        <!-- Text input-->
        <label class="control-label" for="input01">Last Name</label>
          <div class="controls">
            <input type="text" placeholder="" class="input-xlarge">
            
        </div>
        </div><div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">First Name</label>
          <div class="controls">
            <input type="text" placeholder="" class="input-xlarge">
            
          </div>
        </div>

    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">Email Address</label>
          <div class="controls">
            <input type="text" placeholder="someone@somewhere.edu" class="input-xlarge">
            
        </div>
        </div>
    <div class="control-group">
      <!-- Text input-->
      <label class="control-label" for="input2">Office Phone</label>
      <div class="controls">
        <input type="text" placeholder="123-456-1234" class="input-xlarge">
      </div>
    </div>
    <div class="control-group">
      <!-- Text input-->
      <label class="control-label" for="input4"></label>
      <div class="controls">
        <a href="prop.keypersonnel.add3.php" class="btn btn-inverse">Search</a>      </div>
    </div>
    </fieldset>
  </form>

</div>
<div class="modal-footer" data-spy="">
  <a href="prop.keypersonnel.pers.php" target="_parent" class="btn btn-mini">Close</a>
 
</div>

<?php include( 'assets/inc/scripts.global.php' ) ?>


</body>
</html>
