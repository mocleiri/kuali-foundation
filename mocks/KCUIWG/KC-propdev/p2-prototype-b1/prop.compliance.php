<?php
# Variables
$page = 'compliance';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>
<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
       
        
        
        <div class="has-tools">
            <h3>Compliance</h3>
            <div class="uif-toolbar">
              <?php
              if (!isset($_SESSION['compliance'])) { ?>
              <button id="compliance_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-compliance/compliance.add.php"><i class="icon icon-plus"></i> Add compliance entry</button>
              <?php } else { ?>
              <button id="compliance_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-compliance/compliance.add.php"><i class="icon icon-plus"></i> Add compliance entry</button>
              <?php } ?>
            </div>
        </div>
        
        
        <div class="panel-group compliance-entries" id="accordion">

          <?php
               if(isset($_SESSION['compliance']) && is_array($_SESSION['compliance'])){
                    foreach($_SESSION['compliance'] as $id=>$entry){
                      // print_r($entry);
                      include "inc/compliance.entry.php";

                    }
                }
          ?>


        </div>
        
        
        <div class="uif-stickyFooter uif-stickyButtonFooter">
          <div class="btn-row-page-action">
            <button href="prop.keypersonnel.creditfa.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.attachments.php" class="btn btn-primary">Save and Continue</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script>
$(document).ready(function(){
    $(".remove-compliance-entry").live("click", function(){
            var container = $(this).parents('div').eq(3);

            if(confirm("Are you sure you want to remove this entry")) $(container).remove();
             console.log($(this).attr('entryId'));
             var id = $(this).attr('entryId');
             $.post('process.php', {'id': id, 'action' : 'removeComplianceEntry'}, function(){

             });
             return false;

    });

     $('.update-compliance-entry').live('click' , function(e){

        var entryId= $(this).attr('complianceEntryId');
        var data = $(this).closest('form').serialize();

     //   $.post('save-session.php', data, function(v){
      //  console.log(v);
             $.post('process.php', data, function(t){
                  $('#complianceEntry' + entryId).replaceWith(t);

              });
      //   });

        return false;
     });

     $('.cancel-update-compliance-entry').live('click', function(e){

         var container = $(this).parent('div').parent('form').parent('div');
         $.post('process.php', {"action": "previewComplianceEntry", "id" : $(this).attr('complianceEntryId') }, function(t){
              $(container).html(t);

         });
         return false;
     });

});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>