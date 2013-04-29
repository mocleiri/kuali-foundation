<?php
# Variables
$page = 'basics-deliveryinfo';
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
          






<div class="span12 content"> <h3> Delivery Info</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <div class="boxSubheader">
                  <h4>Recipient <a class="tool-link" href="../asdf.html" >change</a> </h4>
                </div>
                <dl class="table-display" style="margin-top:-13px;">
                  <dt>Organization</dt>
                  <dd>UNIVERSITY OF MICHIGAN</dd>
                  <dt>Address 1</dt>
                  <dd>2044 Wolverine Tower</dd>
                  <dt>Address 2</dt>
                  <dd>3003 State Street</dd>
                  <dt> City</dt>
                  <dd>ANN ARBOR</dd>
                  <dt>State</dt>
                  <dd>MI</dd>
                  <dt>Zip</dt>
                  <dd>48109-1273</dd>
                </dl>
                <div class="clearfix"></div>
                <div class="boxSubheader">
                  <h4>Delivery Details </h4>
                </div>
                <div class="control-group">
                  <label class="control-label" for="ProposalType4">Mail By</label>
                  <div class="controls">
                    <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                      <option value="">select</option>
                    </select>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="ProposalType4">Type</label>
                  <div class="controls">
                    <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                      <option value="">select</option>
                    </select>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="Description3">Mail Account ID</label>
                  <div class="controls">
                    <input type="text" id="Description3" placeholder="" class="input-xlarge">
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="Description3">Number of Copies</label>
                  <div class="controls">
                    <input type="text" id="Description3" placeholder="" class="input-xlarge">
                  </div>
                </div>
                <div class="control-group"> 
                  <!-- Textarea -->
                  <label class="control-label">Mail Description</label>
                  <div class="controls">
                    <div class="textarea">
                      <textarea name="textarea2" class="input-xlarge"> </textarea>
                    </div>
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>










        </div> </div>
      




</div>
<div class="docControls"> <a href="prop.basics.orgloc.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.basics.keywords.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>




    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
