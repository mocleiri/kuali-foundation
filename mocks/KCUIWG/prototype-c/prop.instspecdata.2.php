<?php
# Variables
$page = 'instspecdata-2';
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
         <h3>Institution Data 2</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
             <fieldset>
     
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
          <label class="control-label">Checkboxes</label>
          <div class="controls">
      <!-- Multiple Checkboxes -->
      <label class="checkbox">
        <input type="checkbox" value="Option one">
        Option one
      </label>
      <label class="checkbox">
        <input type="checkbox" value="Option two">
        Option two
      </label>
      <label class="checkbox">
        <input type="checkbox" value="Option three">
        Option three
      </label>
      <label class="checkbox">
        <input type="checkbox" value="Option four">
        Option four
      </label>
  </div>

        </div>

    <div class="control-group">
          <label class="control-label">File Button</label>

          <!-- File Upload -->
          <div class="controls">
            <input class="input-file" id="fileInput" type="file">
          </div>
        </div>

    <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">Textarea</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="" > </textarea>
            </div>
          </div>
        </div>

    </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div><div class="docControls"> <a href="prop.instspecdata.1.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.instspecdata.3.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->


</body>
</html>
