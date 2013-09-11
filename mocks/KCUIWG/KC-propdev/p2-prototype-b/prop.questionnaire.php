<?php
# Variables
$page = 'questions';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

		
	<?php require_once( 'themes/kc/inc/uif-unifiedPageHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container-fluid"><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

		  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->
        
        <div class="section-title">
          <h3>Questionnaire</h3>
        </div>
        
        
        
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
        
      </div>

		</div>

		<!-- Button row -->
		<div class="btn-row-page-action">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>