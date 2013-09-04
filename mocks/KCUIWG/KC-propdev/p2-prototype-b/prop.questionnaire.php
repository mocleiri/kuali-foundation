<?php
# Variables
$page = 'questions';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container">
		
		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

		<div class="col-md-9" id="content" tabindex="-1">
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
                  <a href="#" class="launch-modal" data-modal-page="modals/questionnaire.html">edit</a></td>
                </tr>
                <tr>
                  <td>Proposal Questions</td>
                  <td>incomplete</td>
                 <td>  
                  <a href="#" class="launch-modal" data-modal-page="modals/questionnaire.html">edit</a></td>
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