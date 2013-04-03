<?php
# Variables
$page = 'instspecdata-1';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container"> <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
             <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>  <div class="span10">
          <h3>Institution Data 1</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
            <fieldset><div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">Text input</label>
          <div class="controls">
            <input type="text" placeholder="placeholder" class="input-xlarge">
          </div>
        </div>

    <div class="control-group">

          <!-- Select Basic -->
          <label class="control-label">Select - Basic</label>
          <div class="controls">
            <select class="input-xlarge">
              <option>Enter</option>
              <option>Your</option>
              <option>Options</option>
              <option>Here!</option>
            </select>
          </div>

        </div>

    <div class="control-group">

          <!-- Select Multiple -->
          <label class="control-label">Select - Multiple</label>
          <div class="controls">
            <select class="input-xlarge" multiple="multiple">
              <option>Enter</option>
              <option>Your</option>
              <option>Options</option>
              <option>Here!</option>
            </select>
          </div>
        </div>

    <div class="control-group">
          <label class="control-label">Radio buttons</label>
          <div class="controls">

            <!-- Multiple Radios -->
            <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
              Option one
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
              Option two
            </label>
          </div>

        </div>

    </fieldset>
              <div class="control-group buttons">
                <label class="control-label"></label>
                
                <!-- Button -->
                <!-- Button -->
                <div class="controls">
                  <a href="prop.basics.details.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
                  <a href="#" class="btn">Save</a>
                  <a href="prop.basics.sponsor.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
                </div>
              </div>
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
