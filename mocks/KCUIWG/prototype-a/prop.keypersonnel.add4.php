<?php require_once( 'assets/inc/head.php' ) ?>

<!-- Modal -->
 <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"> Role - Ben Webster</h3>
  </div>
<div class="modal-body" style="min-height:312px">
  <form class="form-horizontal">
    <fieldset>
      <div class="control-group">

          <!-- Select Basic -->
          <label class="control-label">Role</label>
          <div class="controls">
           <select title="Proposal Person Role Id"><option value="">select</option>
<option value="PI">PI/Contact</option>
<option value="COI">Co-Principal Investigator</option>
<option value="KP">Key Person</option></select>
          </div>

        </div>
      

    

    </fieldset>
  </form>

</div>
<div class="modal-footer" data-spy="">
  <a href="prop.keypersonnel.pers.php" target="_parent" class="btn btn-mini">Close</a>
  <a href="#" class="btn btn-inverse btn-mini"><i class="icon-white icon-plus"></i> add person</a>
</div>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>
