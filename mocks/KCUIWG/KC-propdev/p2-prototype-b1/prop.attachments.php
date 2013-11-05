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
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse9"><span aria-hidden="true" class="icon-caret-right"></span> MIT Cost-Sharing Distribution</a> </h4>
                    </div>
                    <div class="col-md-3"> MIT-costsharingdoc.pdf </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse9" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">MIT Cost-Sharing Distribution</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> MIT-costsharingdoc.pdf</a></span> </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Edit</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse5"><span aria-hidden="true" class="icon-caret-right"></span> MIT Cost-Sharing Distribution</a> </h4>
                    </div>
                    <div class="col-md-3"> MIT-costsharingdoc.pdf </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse5" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <select name="type3" id="type3" class="form-control input-sm col-md-8">
                            <option value="">select:</option>
                            <option value="55" selected="selected">MIT Cost-Sharing Distribution</option>
                            <option value="56">MIT F&amp;A Under-recovery Distribution</option>
                            <option value="60">Other Institutional Attachment</option>
                            <option value="299">Institutional Narrative 1 - Change this description as Needed</option>
                            <option value="900">1st Institutional Attachment</option>
                            <option value="901">2nd Institutional Attachment</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> MIT-costsharingdoc.pdf</a></span> 
                          <span class="">
                          <div class="dropdown pull-right"><span class="caret"></span> <a data-toggle="dropdown" href="#">Actions</a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                              <li><a href="#">Replace File</a></li>
                              <li><a href="#">Set Permissions</a></li>
                            </ul>
                          </div>
                          </span>
                          </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Save</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
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
          <div class="tab-pane" id="abstracts">
            <h4>Abstracts</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-9">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse6"><span aria-hidden="true" class="icon-caret-right"></span> Project Summary</a> </h4>
                    </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse6" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">Project Summary</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Abstract Details:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                      
                      
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Edit</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                      
                      
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-9">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse10"><span aria-hidden="true" class="icon-caret-right"></span> Project Summary</a> </h4>
                    </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse10" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
                        <div class="col-md-9">
                          <select name="type3" id="type3" class="form-control input-sm col-md-8">
                            <option value="">select</option>
                            <option value="1" selected="selected">Project Summary</option>
                            <option value="2">Technical Abstract</option>
                            <option value="4">Labs</option>
                            <option value="5">Clinical</option>
                            <option value="6">Animal</option>
                            <option value="7">Computer</option>
                            <option value="8">Office</option>
                            <option value="9">Other Facilities</option>
                            <option value="10">Equipment</option>
                            <option value="11">Other Resources</option>
                            <option value="12">Suggested Reviewers</option>
                            <option value="13">Publications</option>
                            <option value="14">Reviewers Not to Include</option>
                            <option value="15">Deviation Authorization</option>
                            <option value="16">Areas Affected</option>
                            <option value="17">Relevance</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Abstract Details:</label>
                        <div class="col-md-9 input-group">
                          <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                        </div>
                      </div>
                      
                      
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Save</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
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
        
        <!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <button  onclick="location.href='prop.attachments.internal.php'" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='prop.attachments.notes.php'" class="btn btn-primary">Save and Continue</button>
          </div>
          <!-- // --> 
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
             $.post('save-session.php', {'id': id, 'action' : 'removeAttachmentsProposalEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-proposal-entry').live('click' , function(e){
        var form = $(this).closest('form');
        var data = $(form).serialize();
        var form_id =  $(this).closest('form').attr('id');
        var entryId= $("#" + form_id + " #id").val();
        console.log(data);
        console.log(entryId);

        $.post('save-session.php', data, function(){
             $.post('process.php', {"action": "updateAttachmentProposalEntry", "id" : entryId }, function(t){
                  $('#attachmentProposalEntry' + entryId).replaceWith(t);

              });
         });

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
            var container = $(this).parents('div').eq(3);

            if(confirm("Are you sure you want to remove this entry")) $(container).remove();
             console.log($(this).attr('entryId'));
             var id = $(this).attr('entryId');
             $.post('save-session.php', {'id': id, 'action' : 'removeAttachmentsPersonnelEntry'}, function(){

             });
             return false;
    });

     $('.update-attachment-personnel-entry').live('click' , function(e){
     var entryId= $(this).attr("entryId");
        var form = $(this).closest('form');
        var data = $(form).serialize();

        console.log(data);
        console.log('test ' + entryId);

        $.post('save-session.php', data, function(){
             $.post('process.php', {"action": "updateAttachmentPersonnelEntry", "id" : entryId }, function(t){
                  $('#attachmentPersonnelEntry' + entryId).replaceWith(t);
                  console.log(t);

              });
         });

        return false;
     });

     $('.cancel-update-attachment-personnel-entry').live('click', function(e){

         var container = $(this).parents('div').eq(1);

         $.post('process.php', {"action": "previewAttachmentPersonnelEntry", "id" : $(this).attr('entryId') }, function(t){
              $(container).html(t);

         });
         return false;
     });

});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>
