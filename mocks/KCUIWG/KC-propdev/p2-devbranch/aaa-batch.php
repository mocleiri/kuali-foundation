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
            
             <h4>Proposal</h4>
          
          
          
  <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
        <!-- Redirect browsers with JavaScript disabled to the origin page -->
        <noscript><input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"></noscript>
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
    </form>
          </div>
        </div>
        <div class="uif-stickyFooter uif-stickyButtonFooter">
          <div class="container btn-row-page-action">
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
<?php require_once( 'themes/kc/inc/footer.php' ); ?>