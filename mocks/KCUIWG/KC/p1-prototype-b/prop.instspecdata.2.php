<?php
# Variables
$page = 'instspecdata-2';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader">
            <h3>Institution Specific Data 2</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" > <fieldset>
     
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
                  <textarea type="" class=""> </textarea>
            </div>
          </div>
        </div>

    </fieldset>
          </div>
        </div>
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        
        
        
        
        <div style=" padd12px; text-align:center">
          <a href="prop.instspecdata.1.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.instspecdata.3.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>