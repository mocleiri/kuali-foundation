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
           $keyPersonnelMessage =   "<div class='alert alert-info alert-dismissable'>
  <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
  <strong>NIH Sponsor Notice:</strong> PI/Contact is a required Proposal Role prior to submission. Only one PI/Contact is allowed. For single PI submissions, please designate the lead investigator as PI/Contact & other senior personnel as Key Persons. For multiple PI submissions, please designate one PI/Contact. Add additional lead investigators as co-Investigators and check the Multiple PI box. Add other senior personnel as Key Persons.
</div>

";
     }
 }
?>




<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
       <?php echo $keyPersonnelMessage?>
         
         <div class="alert alert-success fade in " style="display:none">
             <button type="button" class="close" data-dismiss="alert" aria-hidden="true"><span aria-hidden="true" class="icon-remove" style="font-size:14px"></span></button>
             <span id="keypersonnel-message-name"></span> was successfully added.
          </div>
        
        <div class="has-tools">
           <h3>Personnel</h3>
        </div>

        <?php
        if (!isset($_SESSION['keyPersonnel'])) { ?>
        <div class="uif-toolbar">
           <button class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-addpersonnel/emp.search.php"><i class="icon icon-user"></i> Add Personnel</button>
        </div>
        <?php } else { ?>
        <div class="uif-toolbar">
          <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-addpersonnel/emp.search.php"><i class="icon icon-user"></i> Add Personnel</button>  <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/keypersonnel.notifications.php"><i class="icon icon-envelope"></i> Send Certification Requests</button>
        </div>
        <?php } ?>
        
         <div class="panel-group personnel-entries" id="accordion1">

             <?php

                 if(isset($_SESSION['keyPersonnel'])){

                     foreach($_SESSION['keyPersonnel'] as $id=>$person) {
                        //$_SESSION['personnelId'] = $id;
                      include "prop.keypersonnel.person.php";
                     }
                 }
                ?>

         </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <div class="container btn-row-page-action">
            <button href="prop.basics.orgloc.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.keypersonnel.credit.php" class="btn btn-primary">Save and Continue</button>
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

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this person")) $("#keyPersonnelEntry" + id).remove();

             $.post('process.php', {'id': id, 'action' : 'removePersonnelSession'}, function(){

             });
             return false;
          //   alert($(this).parents('div').eq(4).attr('id'));
        });

});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>