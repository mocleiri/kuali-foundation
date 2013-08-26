<?php
# Variables
$page = 'basics-grantsgov';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">
      <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="leftnavContent">
        <?php require_once( 'assets/inc/document-nav.php' ) ?>
        <div id="content" role="application">
          <div class="row-fluid">
            <div class="span12 content">
              <h3>Opportunity Search</h3>
              
                   <div class="well well-small">
                    <h5 style="margin-top:0px;">Opportunity actions</h5>
                   <a href="#"id="addopp" class="btn btn-small">Load New...</a> </div>
              
           
              
      
              
              <!--<div class=" formfields">
                <form  class="form-horizontal" method="get" action="">
                  <fieldset style="">
                
                    <div class="control-group">
                      <label class="control-label" for="orgdocnum2"> Search Domain</label>
                      <div class="controls">
                        <select name="select" id="activityType" class="input-medium" title="Activity Type" required="required">
                          <option value="" selected="selected">select</option>
                          <option value="4" >Grants.Gov</option>
                          <option value="9">Research.Gov</option>
                        </select>
                        <a href="#" id="" class="btn lookup"><span>lookup</span></a> </div>
                    </div>
                  
                    <div class="control-group">
                      <label class="control-label" for="Description">Opportunity ID</label>
                      <div class="controls">
                        <input type="text" id="Description" placeholder="" class=" input-medium">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="Description">CFDA Number</label>
                      <div class="controls">
                        <input type="text" id="Description" placeholder="" class="input-medium">
                      </div>
                    </div><div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-small btn-default">Search</button>
  </div>
</div>
                  </fieldset>
                </form>
              </div>-->
              
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.basics.details.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.basics.sponsor.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a></div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<script>
$(document).ready(function() {
    $("#addopp").on('click', function() {
        $.fancybox.open({
            href: 'modal/lookup-oppsearch.html',
            type: 'iframe',
            padding: 0,
            width: 800,
        });
    });
});
</script>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
