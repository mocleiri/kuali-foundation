<?php
# Variables
$page = 'basics-sponsor';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<body>


<script type="text/javascript">
    $(document).ready(function () {
     
        $("#timeout1").click(function () {
            $.fancybox.open({
                href: 'modal/timeout1.html',
                type: 'iframe',
                padding: 0,
                width: 500,
            });
        });
		
		  $("#timeout2").click(function () {
            $.fancybox.open({
                href: 'modal/timeout2.html',
                type: 'iframe',
                padding: 0,
                width: 500,
            });
        });

       
    });
	
</script>



<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft" style="margin-top:30px;">
        <div class="span12">
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <div class="control-group">
                  <label class="control-label" for="ProposalType4">Label</label>
                  <div class="controls">
                    <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                    </select>
                  </div>
                </div><div class="control-group">
                  <label class="control-label" for="ProposalType4">Label</label>
                  <div class="controls">
                    <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                    </select>
                  </div>
                </div><div class="control-group">
                  <label class="control-label" for="ProposalType4">Label</label>
                  <div class="controls">
                    <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                      <option value="1">option</option>
                    </select>
                  </div>
                </div><div class="control-group">
                  <label class="control-label" for="ProposalType4">Label</label>
                  <div class="controls">
                    <input type="text" id="Description2" placeholder="" class="input-xlarge">
                  </div>
                </div><div class="control-group">
                  <label class="control-label" for="ProposalType4">Label</label>
                  <div class="controls">
                    <input type="text" id="Description" placeholder="" class="input-xlarge">
                  </div>
                </div>
              </fieldset>
            </form> <div><a id="timeout1" href="javascript:;">Timeout stage 1</a></div><div><a id="timeout2" href="javascript:;">Timeout stage 2</a></div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.basics.grantsgov.php" class="btn">Cancel</a>  <a href="prop.basics.orgloc.php" class="btn btn-inverse">Save</a></div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
