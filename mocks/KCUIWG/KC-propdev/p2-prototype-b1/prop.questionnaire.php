<?php
# Variables
$page = 'questions';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->
        
        
          <h3>Questionnaire</h3>
        
        
        
        
        <table class="table table-condensed">
                <tr>
                  <th>Source</th>
                  <th>Status</th>
                  <th>Action</th>
          </tr>
                <tr>
                  <td>Grants.gov Questions</td>
                  <td>completed</td>
                  <td>  
                  <a href="#" class="launch-modal" data-modal-page="modal/questionnaire.html">edit</a></td>
                </tr>
                <tr>
                  <td>Proposal Questions</td>
                  <td>incomplete</td>
                 <td>  
                  <a href="#" class="launch-modal" data-modal-page="modal/questionnaire.html">edit</a></td>
                </tr>
              </table>

        <!-- // --> 
        
        <div class="uif-stickyFooter uif-stickyButtonFooter" style="position:fixed; left: 0; bottom: 0px; width:100%"> 
          
        
		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.attachments.notes.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.budget.summary.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->

        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>





