<?php
# Variables
$page = 'attachments';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">
        
        <h3>Attachments</h3>
        <ul class="nav nav-tabs" id="myTab">
          <li class="active"><a href="#proposal">Proposal</a></li>
          <li><a href="#personnel">Personnel</a></li>
          <li><a href="#internal">Internal</a></li>
          <li><a href="#abstracts">Abstracts</a></li>
          <li><a href="#notes">Notes</a></li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane active" id="proposal">

            <div class="row">
          <div class="col-md-12">
             <h4>Proposal</h4>
          </div>
          <div class="col-md-12">
            <div class="uif-toolbar">
              <?php if (!isset($_SESSION['attachments']['proposal'])) { ?>
              <button id="attachments_proposal_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.proposal.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } else { ?>
              <button id="attachments_proposal_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.proposal.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <button class="btn btn-default btn-xs"><i class="icon icon-check"></i> Select all</button>
              <button class="btn btn-default btn-xs" disabled><i class="icon icon-trash"></i> Remove</button>
              <?php } ?>
            </div>
          </div>
        </div>           
            <div class="panel-group attachments-proposal-entries" id="accordion">

                <?php
                     if(isset($_SESSION['attachments']['proposal']) && is_array($_SESSION['attachments']['proposal'])){
                        foreach($_SESSION['attachments']['proposal'] as $id=>$entry){
                            include "inc/attachments.proposal.entry.php";
                        }
                    }
                ?>
                
            </div>
           
          </div>

          <div class="tab-pane" id="personnel">
            <div class="row">
          <div class="col-md-12">
             <h4>Personnel</h4>
          </div>
          <div class="col-md-12">
            <div class="uif-toolbar">
              <?php if (!isset($_SESSION['attachments']['personnel'])) { ?>
              <button id="attachments_personnel_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.personnel.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } else { ?>
              <button id="attachments_personnel_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.personnel.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } ?>
            </div>             
          </div>
        </div>
            <div class="panel-group attachments-personnel-entries" id="accordion2">
                   <?php
                        if(isset($_SESSION['attachments']['personnel']) && is_array($_SESSION['attachments']['personnel'])){
                           foreach($_SESSION['attachments']['personnel'] as $id=>$entry){
                               include "inc/attachments.personnel.entry.php";
                           }
                       }
                   ?>

            </div>
          
          </div>

          <div class="tab-pane" id="internal">
            <div class="row">
          <div class="col-md-12">
              <h4>Internal</h4>
          </div>
          <div class="col-md-12">
            <div class="uif-toolbar">
              <?php if (!isset($_SESSION['attachments']['internal'])) { ?>
              <button id="attachments_internal_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.internal.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } else { ?>
              <button id="attachments_internal_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.internal.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } ?>
           </div>
          </div>
        </div>        
            <div class="panel-group attachments-internal-entries" id="accordion3">
            <?php
                      if(isset($_SESSION['attachments']['internal']) && is_array($_SESSION['attachments']['internal'])){
                         foreach($_SESSION['attachments']['internal'] as $id=>$entry){
                             include "inc/attachments.internal.entry.php";
                         }
                     }
                 ?>

            </div>
           
          </div>

          <div class="tab-pane" id="abstracts">
             
            <div class="row">
          <div class="col-md-12">
              <h4>Abstracts</h4>
          </div>
          <div class="col-md-12">
            <div class="uif-toolbar">
              <?php if (!isset($_SESSION['attachments']['abstracts'])) { ?>
              <button id="attachments_abstracts_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.abstracts.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } else { ?>
              <button id="attachments_abstracts_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.abstracts.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } ?>
            </div>
          </div>
        </div>
            <div class="panel-group attachments-abstracts-entries" id="accordion4">

                <?php
                      if(isset($_SESSION['attachments']['abstracts']) && is_array($_SESSION['attachments']['abstracts'])){
                         foreach($_SESSION['attachments']['abstracts'] as $id=>$entry){
                             include "inc/attachments.abstracts.entry.php";
                         }
                     }
                 ?>

            </div>
            
          </div>

          <div class="tab-pane" id="notes">
             
            <div class="row">
          <div class="col-md-12">
              <h4>Notes</h4>
          </div>
          <div class="col-md-12"> 
            <div class="uif-toolbar">
              <?php if (!isset($_SESSION['attachments']['notes'])) { ?>
              <button id="attachments_notes_add" class="btn btn-default btn-starter launch-modal" data-modal-page="modal/modal-attachments/attachments.notes.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } else { ?>
              <button id="attachments_notes_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.notes.add.php"><i class="icon icon-plus"></i> Add Entry</button>
              <?php } ?>
            </div>
          </div>
        </div>
        
            <div class="panel-group attachments-notes-entries" id="accordion5">


              <?php
                    if(isset($_SESSION['attachments']['notes']) && is_array($_SESSION['attachments']['notes'])){
                       foreach($_SESSION['attachments']['notes'] as $id=>$entry){
                           include "inc/attachments.notes.entry.php";
                       }
                   }
               ?>

            </div>
            <div class="btn-row-widget-action">
              
            </div>
          </div>
        </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <div class="container btn-row-page-action">
            <button href="prop.compliance.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.questionnaire.php" class="btn btn-primary">Save and Continue</button>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</section>
<script>
$('#myTab a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})

$('#add_proposal').hide();
$('#proposal_add').click(function() {
  $('#add_proposal').fadeIn();
});
</script>
<script>
$(document).ready(function(){


    $(".remove-attachments-proposal-entry").live("click", function(){
            var container = $(this).parents('div').eq(3);
            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentsProposalEntry" + id).remove();
             //console.log($(this).attr('entryId'));
             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsEntry', "section": "proposal"}, function(){

             });
             return false;
    });

     $('.update-attachments-proposal-entry').live('click' , function(e){

        var data = $(this).closest('form').serialize();
        var entryId= $(this).attr("entryId");
        console.log(data);
        console.log(entryId);

       // $.post('save-session.php', data, function(){
         $.post('process.php', data, function(t){
              $('#attachmentsProposalEntry' + entryId).replaceWith(t);

          });
       //  });

        return false;
     });

     $('.cancel-update-attachments-proposal-entry').live('click', function(e){
         var id = $(this).attr('entryId');
         $.post('process.php', {"section": "proposal", "action": "previewAttachmentEntry", "id" : id }, function(t){
              $("#attachmentsProposalInfo" + id).html(t);

         });
         return false;
     });

});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-personnel-entry").live("click", function(){

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentsPersonnelEntry"+ id).remove();

             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsEntry', "section": "personnel"}, function(){

             });
             return false;
    });

     $('.update-attachments-personnel-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentsPersonnelEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachments-personnel-entry').live('click', function(e){
        var id= $(this).attr("entryId");
         $.post('process.php', {"action": "previewAttachmentsEntry", "id" : id, "section": "personnel" }, function(t){
            $("#attachmentsPersonnelInfo" + id).html(t);
       });
         return false;
     });


});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-internal-entry").live("click", function(){

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentsInternalEntry"+ id).remove();

             $.post('process.php', {"section": "personnel", 'id': id, 'action' : 'removeAttachmentsEntry'}, function(){

             });
             return false;
    });

     $('.update-attachments-internal-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentsInternalEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachments-internal-entry').live('click', function(e){
         var id = $(this).attr('entryId');
         $.post('process.php', {"section": "personnel", "action": "previewAttachmentsEntry", "id" : id }, function(t){

            $("#attachmentsInternalInfo" + id).html(t);
       });
         return false;
     });


});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-abstracts-entry").live("click", function(){

                var id = $(this).attr('entryId');
                if(confirm("Are you sure you want to remove this entry")) $("#attachmentsAbstractsEntry"+ id).remove();

                 $.post('process.php', {"section": "personnel", 'id': id, 'action' : 'removeAttachmentsEntry'}, function(){

                 });
                 return false;
        });

     $('.update-attachments-abstracts-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentsAbstractsEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachments-abstracts-entry').live('click', function(e){
         var id = $(this).attr('entryId');
         $.post('process.php', {"section": "abstracts", "action": "previewAttachmentsEntry", "id" : id }, function(t){

            $("#attachmentAbstractsInfo" + id).html(t);
       });
         return false;
     });


});
</script>


<?php require_once( 'themes/kc/inc/footer.php' ); ?>