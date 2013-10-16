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
			<div class="form-group clearfix">
				<label for="organization" class="control-label col-md-3 required">Organization:</label>
				<div class="col-md-9 input-group">
					<input type="text" class="form-control input-sm" name="organization" id="organization" />
					<span class="input-group-btn">
						<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup-institution.php" data-modal-height="500"></a>
					</span>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="address1" class="control-label col-md-3 required">Address:</label>
				<div class="col-md-9">
					<input type="text" class="form-control input-sm" name="address1" id="address1" />
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="address2" class="control-label col-md-3">Address:</label>
				<div class="col-md-9">
					<input type="text" class="form-control input-sm" name="address2" id="address2" />
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="city" class="control-label col-md-3 required">City:</label>
				<div class="col-md-9">
					<input type="text" class="form-control input-sm" name="city" id="city" />
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="state" class="control-label col-md-3 required">State:</label>
				<div class="col-md-9">
					<input type="text" class="form-control input-sm" name="state" id="state" />
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="zip" class="control-label col-md-3 required">ZIP:</label>
				<div class="col-md-9">
					<input type="text" class="form-control input-sm" name="zip" id="zip" />
				</div>
			</div>

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
                <label for="submission_type" class="control-label col-md-3 required">Submission type:</label>
                <div class="col-md-9">
                  <select name="submission_type" id="submission_type" class="form-control input-sm col-md-8">
                  	<option></option>
                    <option value="1">Decrease award</option>
                    <option value="1">Increase award</option>
                   
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
			if ($alt && file_exists('prop.basics.sponsor-alt.php')) {
				echo '<button href="prop.basics.sponsor-alt.php" class="btn btn-primary">Save and continue</button>';
			} else {
				echo '<button href="prop.basics.sponsor.php" class="btn btn-primary">Save and continue</button>';
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
