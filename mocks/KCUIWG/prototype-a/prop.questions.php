<?php
# Variables
$page = 'questionnaire';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader expandControl">
            <h3>Questionnaire</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Question</th>
                  <th>Answer</th>
                  <th Nowrap>ReView Date</th>
                  <th>Explanation</th>
                  <th style="width: 36px;"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Will new space or remodeling be required for this project? </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>Patents: Answer &quot;N/A&quot; if there are No patents. Answer &quot;Yes&quot; if all patents have been reported. Answer &quot;No&quot; if there are patents that have Not been reported. </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>Is the principal investigator changing with this application? If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME. </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>For U.S. Department of Education, check &quot;Yes&quot; or &quot;No&quot; only if assistance is being requested under a program that gives special consideration to Novice applicants. Otherwise, check &quot;Not Applicable&quot;. </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>Does this project have an international connection: that is, collaboration with internationally-based colleagues or institutions; significant activity outside the United States; or pertain topically to countries, regions or populations outside the United States? If so, please indicate in the explanation field the countries with which it is connected. (Used by the International Affairs Office.) </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>Historical Sites Are Affected </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr> <tr>
                  <td>Does this project have an actual or potential impact on the environment? </td>
                  <td>   <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
             Yes
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
            No
            </label></td>
                  <td>07/15/2013</td>
                  <td>
                    <input type="text" class=" input-medium">
                  </td>
                  <td><a href="#" class="btn btn-small" role="button" data-toggle="modal">View</a></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        <div style=" padd12px; text-align:center">
          <a href="prop.abstracts.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.budget.summary.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>