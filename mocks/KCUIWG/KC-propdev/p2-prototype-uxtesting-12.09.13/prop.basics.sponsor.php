<?php
# Variables
$section = 'basics';
$page = 'basics-sponsors';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1"> 
        <h3>Sponsor &amp; Program Information</h3>
        <form action="#" method="post" class="form-horizontal">
          <fieldset>
            <legend style="display:none">Enter sponsor information</legend>

            <div class="form-group clearfix">
                <label class="control-label col-md-3" for="project_start"> Sponsor deadline: </label>
                <div class="col-md-9 date-range">
                    <div class="col-md-6 date">
                        <input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_start" id="project_start" placeholder="mm/dd/yyyy" />
                        <label for="project_start" class="helper-text">Deadline date</label>
                    </div>
                    <span class="range">at</span>
                    <div class="col-md-6 date">
                        <input type="text" size="12" class="form-control input-sm uif-timeControl" name="project_end" id="project_end" placeholder="hh:mm" />
                        <label for="project_end" class="helper-text">Deadline time</label>
                    </div>
                </div>
            </div>

          <div class="form-group clearfix">
              <label for="sponsor_deadline_type" class="control-label col-md-3"> Sponsor Deadline Type: </label>
              <div class="col-md-9">
                <select name="sponsor_deadline_type" id="sponsor_deadline_type" class="form-control input-sm col-md-8">
                    <option></option>
                    <option value="Postmark">Postmark</option>
                    <option value="Receipt">Receipt</option>
                    <option value="Target Date">Target Date</option>
                </select>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="notice_of_opp" class="control-label col-md-3">Notice of Opportunity: </label>
              <div class="col-md-9">
                <select name="notice_of_opp" id="notice_of_opp" class="form-control input-sm col-md-8">
                    <option></option>
                    <option value="1">Federal Solicitation</option>
                    <option value="6">Non-Federal Solicitation</option>
                    <option value="2">Unsolicited</option>
                    <option value="3">Verbal Request for Proposal</option>
                    <option value="4">SBIR Solicitation</option>
                    <option value="5">STTR Solicitation</option>
                    <option value="7">Internal</option>
                </select>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="opportunity_id" class="control-label col-md-3">Opportunity ID: </label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm has-helper" name="opportunity_id" id="opportunity_id" value="<?php if ($alt) { echo 'PD 09-6885'; } else { echo 'PA-13-302'; } ?>" disabled />
                <div class="helper-text">
                    Help text here.
                </div>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="cfda_number" class="control-label col-md-3">CFDA number: </label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm has-helper" name="cfda_number" id="cfda_number" value="10.001" disabled />
                <div class="helper-text">
                    Help text here.
                </div>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="subawards" class="control-label col-md-3">Subawards:</label>
              <div class="col-md-9">
                <p class="form-control-static"> <input type="checkbox" value="" name="subawards" id="subawards" class="checkbox-radio-reset-margin" />
                Yes, this proposal includes subaward(s)</p>
                </div>
            </div>

            <div class="form-group clearfix">
              <label for="sponsor_proposal_id" class="control-label col-md-3">Sponsor proposal ID:</label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm has-helper" name="sponsor_proposal_id" id="sponsor_proposal_id" />
                <div class="helper-text">
                    Help text here.
                </div>
              </div>
            </div>
            
            <!-- <div class="form-group clearfix">
              <label for="sponsor_div_code" class="control-label col-md-3">Sponsor DIV code:</label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm has-helper" name="sponsor_div_code" id="sponsor_div_code" />
                <div class="helper-text">
                    Help text here.
                </div>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="sponsor_program_code" class="control-label col-md-3">Sponsor program code:</label>
              <div class="col-md-9">
                <input type="text" class="form-control input-sm has-helper" name="sponsor_program_code" id="sponsor_program_code" />
                <div class="helper-text">
                    Help text here.
                </div>
              </div>
            </div> -->

            <div class="form-group clearfix">
              <label for="nsf_science_code" class="control-label col-md-3">NSF Science code:</label>
              <div class="col-md-9">
                <select name="nsf_science_code" id="nsf_science_code" class="form-control input-sm col-md-8">
                <option></option>
                  <option value="P">Biology - Life Sciences: B02</option>
                  <option value="R">Biology - Physical Sciences: B01</option>
                  <option value="T">Chemistry - Physical Sciences: B02</option>
                </select>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="anticipated_award_type" class="control-label col-md-3">Anticipated award type:</label>
              <div class="col-md-9">
                <select name="anticipated_award_type" id="anticipated_award_type" class="form-control input-sm col-md-8">
                    <option></option>
                  <option value="P">Grant</option>
                  <option value="R">Contract</option>
                  <option value="T">Fellowship</option>
                </select>
              </div>
            </div>

            <div class="form-group clearfix">
              <label for="opportunity_title" class="control-label col-md-3">Opportunity title:</label>
              <div class="col-md-9">
                <textarea name="opportunity_title" id="opportunity_title" class="form-control input-sm disabled" disabled="disabled">Same title from the first page would be here</textarea>
              </div>
            </div>
            
          </fieldset>
        </form>

        <div class="uif-stickyFooter uif-stickyButtonFooter">
            <div class="btn-row-page-action">
                <button href="prop.basics.deliveryinfo.php" class="btn btn-default">Back</button>
                <button class="btn btn-default">Save</button>
                <button href="prop.basics.orgloc.php" class="btn btn-primary">Save and Continue</button>
            </div>

        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>