<?php
# Variables
$page = 'docsearch';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents"><?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <div class="row-fluid">
        <div class="span12" style="padding-top:4px">
          <div class="docHeader">
            <h2 style="display:inline;">Doc Search</h2>
          </div>
          <div style="padding-top:20px; padding-bottom:20px;">
            <form  class="form-horizontal" method="get" action="docsearch.results.php">
              <fieldset>
                <div class="control-group">
                  <!-- Text input-->
                  <label class="control-label" for="input01">Document Type</label>
                  <div class="controls">
                    <input type="text" placeholder="" class="input-large">
                    <a href="docsearch.doctype.html" class="btn lookup"><span>lookup</span></a>
                  </div>
                </div>
                <div class="control-group">
                  <!-- Text input-->
                  <label class="control-label" for="input01">Initiator</label>
                  <div class="controls">
                    <input type="text" placeholder="" class="input-large">
                    <a href="docsearch.initiator.html" class="btn lookup"><span>lookup</span></a>
                  </div>
                </div>
                <div class="control-group">
                  <!-- Text input-->
                  <label class="control-label" for="input01">Document ID</label>
                  <div class="controls">
                    <input type="text" placeholder="" class="input-large">
                  </div>
                </div>
                <div class="control-group">
                  <!-- Text input-->
                  <label class="control-label" for="input01">Sub Account Name</label>
                  <div class="controls">
                    <input type="text" placeholder="" class="input-large">
                  </div>
                </div>
                <div class="control-group">
                  <!-- Text input-->
                  <label class="control-label" for="input01">Date Created</label>
                  <div class="controls">
                    <div style="display:inline-block; margin-right:12px;" >
                      <div style="display:inline">
                        <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                      </div>
                      <span style="display:block; width:100px; color:#999"><small>from</small></span>
                    </div>
                    <div style="display:inline-block" >
                      <div style="display:inline">
                        <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                      </div>
                      <span style="display:block; width:100px;color:#999"><small>to</small></span>
                    </div>
                  </div>
                </div>
                <hr>
                <div class="control-group">
                  <label class="control-label"></label>
                  
                  <!-- Button -->
                  <div class="controls">
                    <button id="" type="submit" class="btn btn-inverse">Search</button>
                    <button id="" class="btn btn-link">Cancel</button>
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->


</body>
</html>
