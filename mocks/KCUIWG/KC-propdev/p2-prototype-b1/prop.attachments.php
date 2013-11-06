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
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
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
            <h4>Proposal</h4>
            <div class="panel-group attachments-proposal-entries" id="accordion">

                <?php
                     if(isset($_SESSION['attachments']['proposal']) && is_array($_SESSION['attachments']['proposal'])){
                        foreach($_SESSION['attachments']['proposal'] as $id=>$entry){
                            include "inc/attachments.proposal.entry.php";
                        }
                    }
                ?>
                
            </div>
            <div class="btn-row-widget-action">
              <button id="attachments_proposal_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.proposal.add.php"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="personnel">
            <h4>Personnel</h4>
            <div class="panel-group attachments-personnel-entries" id="accordion2">
                   <?php
                        if(isset($_SESSION['attachments']['personnel']) && is_array($_SESSION['attachments']['personnel'])){
                           foreach($_SESSION['attachments']['personnel'] as $id=>$entry){
                               include "inc/attachments.personnel.entry.php";
                           }
                       }
                   ?>
                   <?php // include "inc/attachments.personnel.entry.php";?>


            </div>
            <div class="btn-row-widget-action">
              <button id="attachments_personnel_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.personnel.add.php"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="internal">
            <h4>Internal</h4>
            <div class="panel-group attachments-internal-entries" id="accordion3">
            <?php
                      if(isset($_SESSION['attachments']['internal']) && is_array($_SESSION['attachments']['internal'])){
                         foreach($_SESSION['attachments']['internal'] as $id=>$entry){
                             include "inc/attachments.internal.entry.php";
                         }
                     }
                 ?>

            </div>
            <div class="btn-row-widget-action">
              <button id="attachments_internal_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.internal.add.php"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="abstracts">
            <h4>Abstracts</h4>
            <div class="panel-group" id="accordion4">

                <?php
                      if(isset($_SESSION['attachments']['abstracts']) && is_array($_SESSION['attachments']['abstracts'])){
                         foreach($_SESSION['attachments']['abstracts'] as $id=>$entry){
                             include "inc/attachments.abstracts.entry.php";
                         }
                     }
                 ?>

            </div>
            <div class="btn-row-widget-action">
              <button id="attachments_abstracts_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-attachments/attachments.abstracts.add.php"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="notes">
            <h4>Notes</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse7"><span aria-hidden="true" class="icon-caret-right"></span>My Note Title</a> </h4>
                    </div>
                    <div class="col-md-3"> McGregor, Geoff </div>
                    <div class="col-md-3"> 10/29/2013 09:58 AM </div>
                  </div>
                </div>
                <div id="collapse7" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Note Topic:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">My Note Title</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Note Text:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id=""><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
        </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter">
          <div class="btn-row-page-action">
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

            if(confirm("Are you sure you want to remove this entry")) $(container).remove();
             console.log($(this).attr('entryId'));
             var id = $(this).attr('entryId');
             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsProposalEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-proposal-entry').live('click' , function(e){

        var data = $(this).closest('form').serialize();
        var entryId= $(this).attr("entryId");
        console.log(data);
        console.log(entryId);

       // $.post('save-session.php', data, function(){
         $.post('process.php', data, function(t){
              $('#attachmentProposalEntry' + entryId).replaceWith(t);

          });
       //  });

        return false;
     });

     $('.cancel-update-attachment-proposal-entry').live('click', function(e){

         var container = $(this).parents('div').eq(2);

         $.post('process.php', {"action": "previewAttachmentProposalEntry", "id" : $(this).attr('entryId') }, function(t){
              $(container).html(t);

         });
         return false;
     });

});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-personnel-entry").live("click", function(){

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentPersonnelEntry"+ id).remove();

             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsPersonnelEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-personnel-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentPersonnelEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachment-personnel-entry').live('click', function(e){
         $.post('process.php', {"action": "previewAttachmentPersonnelEntry", "id" : id }, function(t){
            $("#attachmentPersonnelInfo" + id).html(t);
       });
         return false;
     });


});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-internal-entry").live("click", function(){

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentInternalEntry"+ id).remove();

             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsInternalEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-internal-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentInternalEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachments-internal-entry').live('click', function(e){
         var id = $(this).attr('entryId');
         $.post('process.php', {"action": "previewAttachmentInternalEntry", "id" : id }, function(t){

            $("#attachmentInternalInfo" + id).html(t);
       });
         return false;
     });


});
</script>
<script>
$(document).ready(function(){

    $(".remove-attachments-abstracts-entry").live("click", function(){

            var id = $(this).attr('entryId');
            if(confirm("Are you sure you want to remove this entry")) $("#attachmentAbstractsEntry"+ id).remove();

             $.post('process.php', {'id': id, 'action' : 'removeAttachmentsAbstractsEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-abstracts-entry').live('click' , function(e){
        var id= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();
        // console.log(id);
             $.post('process.php', data, function(t){
                  $('#attachmentAbstractsEntry' + id).replaceWith(t);

              });


        return false;
     });

     $('.cancel-update-attachments-abstracts-entry').live('click', function(e){
         var id = $(this).attr('entryId');
         $.post('process.php', {"action": "previewAttachmentAbstractsEntry", "id" : id }, function(t){

            $("#attachmentAbstractsInfo" + id).html(t);
       });
         return false;
     });


});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>