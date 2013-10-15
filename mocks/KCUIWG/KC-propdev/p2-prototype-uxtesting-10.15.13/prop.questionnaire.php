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
        
        
        
       <table class="table table-condensed table-hover">
                  <thead>
                    <tr>
                      <th>Question</th>
                      <th>Answer</th>
                      <th Nowrap>Review Date</th>
                      <th>Explanation</th>
                      <th style="width: 35px;"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Will new space or remodeling be required for this project? </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Patents: Answer &quot;N/A&quot; if there are No patents. Answer &quot;Yes&quot; if all patents have been reported. Answer &quot;No&quot; if there are patents that have Not been reported. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Is the principal investigator changing with this application? If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>For U.S. Department of Education, check &quot;Yes&quot; or &quot;No&quot; only if assistance is being requested under a program that gives special consideration to Novice applicants. Otherwise, check &quot;Not Applicable&quot;. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Does this project have an international connection: that is, collaboration with internationally-based colleagues or institutions; significant activity outside the United States; or pertain topically to countries, regions or populations outside the United States? If so, please indicate in the explanation field the countries with which it is connected. (Used by the International Affairs Office.) </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Historical Sites Are Affected </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Does this project have an actual or potential impact on the environment? </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                  </tbody>
                </table>

        <!-- // --> 
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
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





