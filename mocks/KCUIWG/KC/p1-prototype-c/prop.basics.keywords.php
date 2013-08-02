<?php
# Variables
$page = 'basics-keywords';
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
          <h3>Keywords</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <dl class="table-display" style="margin-top:;">
                  <dt>1</dt>
              <dd>Carbon
                <a href="#">(delete)</a>
              </dd>
              <dt>2</dt>
              <dd>Heat
                <a href="#">(delete)</a>
              </dd>
              <dt>3</dt>
              <dd>
                <a href="#">+ add</a>
              </dd>
          </dl>
       
              </fieldset>
              
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div><div class="docControls">  <a href="prop.basics.deliveryinfo.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
                  <a href="#" class="btn">Save</a>
                  <a href="prop.keypersonnel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a></div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->


</body>
</html>
