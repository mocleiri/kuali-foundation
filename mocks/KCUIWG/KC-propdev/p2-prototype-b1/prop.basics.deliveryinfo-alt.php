<?php
# Variables
$section = 'basics';
$page = 'basics-deliveryinfo';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
include( '_temp.select.filler.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1"> <!-- Main content goes here -->
        
        <h3>Delivery Info</h3>
        <form action="#" method="post" class="form-horizontal">
          <h3>Recipient <span><a class="link-edit" href="#">Edit</a></span></h3>
          <table  class="table table-condensed">
            <tbody>
              <tr>
                <th class="col-md-3">Organization</th>
                <td>United Technologies/Pratt &amp; Whitney <a href="#">lookup (icon)</a></td>
              </tr>
              <tr>
                <th class="col-md-3">Address 1</th>
                <td>Mail Stop 169-21</td>
              </tr>
              <tr>
                <th>Address 2</th>
                <td>400 Main Street</td>
              </tr>
              <tr>
                <th>City</th>
                <td>East Hartford</td>
              </tr>
              <tr>
                <th>State</th>
                <td>CT</td>
              </tr>
              <tr>
                <th>ZIP</th>
                <td>06108</td>
              </tr>
              
            </tbody>
          </table>
          <h3>Delivery Details</h3>
          <fieldset>
            <legend>Enter details for this delivery</legend>
            <div class="form-group clearfix">
              <label for="mail_type" class="control-label col-md-3 required">Type:</label>
              <div class="col-md-9">
                <select name="mail_type" id="mail_type" class="form-control input-sm col-md-8 onchange">
                	<option></option>
                  <option value="delivery_service">Delivery Service</option>
                  <option value="electronic">Electronic</option>
                  <option value="usps">US Postal Service</option>
                </select>
              </div>
            </div>
            <div class="hidden-fields" id="electronic">
              <div class="form-group clearfix">
                <label for="opp_id" class="control-label col-md-3 required">Opportunity ID:</label>
                <div class="col-md-9">
                  <input type="text" name="opp_id" id="opp_id" class="form-control input-sm col-md-8" />
                </div>
              </div>
              <div class="form-group clearfix">
                <label for="opp_title" class="control-label col-md-3 required">Opportunity title:</label>
                <div class="col-md-9">
                  <input type="text" name="opp_title" id="opp_title" class="form-control input-sm col-md-8" />
                </div>
              </div>
              <div class="form-group clearfix">
                <label for="cfda_num" class="control-label col-md-3 required">CFDA Number:</label>
                <div class="col-md-9">
                  <input type="text" name="cfda_num" id="cfda_num" class="form-control input-sm col-md-8" />
                </div>
              </div>
              <div class="form-group clearfix">
                <label for="submission_type" class="control-label col-md-3 required">Submission type:</label>
                <div class="col-md-9">
                  <select name="submission_type" id="submission_type" class="form-control input-sm col-md-8">
                  	<option></option>
                    <option value="1">Decrease award</option>
                    <option value="1">Increase award</option>
                    <option value="1">Order pizza</option>
                    <option value="1">I'm kinda hungry</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="mail_by" class="control-label col-md-3 required">Submit by:</label>
              <div class="col-md-9">
                <select name="mail_by" id="mail_by" class="form-control input-sm col-md-8">
                	<option></option>
                  <option value="1">Department</option>
                  <option value="1">OSP</option>
                </select>
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="mail_account_id" class="control-label col-md-3">Mail account ID:</label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm" name="mail_account_id" id="mail_account_id" />
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="number_of_copies" class="control-label col-md-3">Number of copies:</label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm" name="number_of_copies" id="number_of_copies" />
              </div>
            </div>
            <div class="form-group clearfix">
              <label for="mail_description" class="control-label col-md-3">Mail description:</label>
              <div class="col-md-9">
                <textarea name="mail_description" id="mail_description" class="form-control input-sm"></textarea>
              </div>
            </div>
          </fieldset>
        </form>
        
        <!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <?php
			if ($alt && file_exists('prop.basics.oppsearch-search-alt.php')) {
				echo '<button href="prop.basics.oppsearch-search-alt.php" class="btn btn-default">Back</button>';
			} else {
				echo '<button href="prop.basics.oppsearch-search.php" class="btn btn-default">Back</button>';
			}
			?>
            <button class="btn btn-default">Save</button>
			<?php
			if ($alt && file_exists('prop.basics.orgloc-alt.php')) {
				echo '<button href="prop.basics.orgloc-alt.php" class="btn btn-primary">Save and continue</button>';
			} else {
				echo '<button href="prop.basics.orgloc.php" class="btn btn-primary">Save and continue</button>';
			}
			?>
          </div>
          <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>
