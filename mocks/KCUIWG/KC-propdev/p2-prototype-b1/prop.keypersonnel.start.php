<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-start';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );

$keyPersonnelMessage = "";

 if(isset($_SESSION['sponsor_code']))  {
     $pos = strpos($_SESSION['sponsor_code'], "NIH");
     if($pos !== false) {
           $keyPersonnelMessage =   "PI/Contact is a required Proposal Role prior to submission. Only one PI/Contact is allowed. For single PI submissions, please designate the lead investigator as PI/Contact & other senior personnel as Key Persons. For multiple PI submissions, please designate one PI/Contact. Add additional lead investigators as co-Investigators and check the Multiple PI box. Add other senior personnel as Key Persons.";
     }
 }
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
         
         <div class="alert alert-success fade in " style="display:none">
             <button type="button" class="close" data-dismiss="alert" aria-hidden="true"><span aria-hidden="true" class="icon-remove" style="font-size:14px"></span></button>
             <span id="keypersonnel-message-name"></span> was successfully added.
          </div>

        <h3>Key Personnel</h3>
        <p><?php echo $keyPersonnelMessage?></p>
        <div class="btn-row-widget-action">
          <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-addpersonnel/emp.search.php"><span aria-hidden="true" class="icon-plus"></span> Add Personnel</button>
        </div>

         <div class="panel-group" id="accordion1">
            <!-- <div id="personnel-container"> -->
             <?php

                 if(isset($_SESSION['person'])){

                     foreach($_SESSION['person'] as $key=>$person) {
                        $_SESSION['personnelId'] = $key;
                        include "prop.keypersonnel.person.php";
                     }
                 }
                ?>


            <!-- </div> -->
         </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter">
          <div class="btn-row-page-action">
            <button href="prop.basics.orgloc.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.keypersonnel.creditintel.php" class="btn btn-primary">Save and Continue</button>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</section>
<form>
<input type="hidden" id="personnel-id" />
<input type="hidden" id="personnel-role" />

</form>

<script>
$(document).ready(function(){
    $(".remove-person").live("click", function(){
            var container = $(this).parents('div').eq(3);

            if(confirm("Are you sure you want to remove this person")) $(container).remove();
             console.log($(this).attr('personnel-id'));
             var id = $(this).attr('personnel-id');
             $.post('process.php', {'id': id, 'action' : 'removePersonnelSession'}, function(){

             });
             return false;
          //   alert($(this).parents('div').eq(4).attr('id'));
        });

});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>