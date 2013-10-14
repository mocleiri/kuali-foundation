<?php
# Variables
$section = 'basics';
$page = 'basics-details';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">
        <h3>Proposal Details</h3>
        <form role="form" style="max-width:700px">
          <div class="form-group"  id="tour1">
            <label  for="exampleInputEmail1">Proposal Type</label>
            <select name="proposal_type2" class="form-control input-sm  chzn-off">
              <option value="4">Continuation</option>
              <option value="1" selected="selected">New</option>
              <option value="3">Renewal</option>
              <option value="2">Resubmission</option>
              <option value="5">Revision</option>
              <option value="6">Task Order</option>
            </select>
          </div>
          <div class="form-group"  id="tour2">
            <label for="exampleInputEmail1">Lead Unit </label>
            <select name="lead_unit2" id="lead_unit2" class="form-control input-sm chzn-off">
              <option value="000001" selected="selected">000001 - University</option>
              <option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
              <option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
              <option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
            </select>
          </div>
          <div class="form-group" id="tour3">
            <label for="exampleInputEmail1">Activity Type</label>
            <select name="activity_type2" id="activity_type2" class="form-control input-sm  chzn-off">
              <option value="4" selected="selected">Clinical Trial</option>
              <option value="9">Construction</option>
              <option value="7">Fellowship - Post-Doctoral</option>
              <option value="6">Fellowship - Pre-Doctoral</option>
              <option value="2">Instruction</option>
              <option value="3">Public Service</option>
              <option value="1">Research</option>
              <option value="8">Student Services</option>
              <option value="5">other</option>
            </select>
          </div>
           <div class="form-group" id="tour4">
            <label class="">Project dates:</label>
            <div class="row">
              <div class="col-md-3">
                <input type="text" class="form-control" placeholder=""><span class="help-block">from</span>
              </div>
              <div class="col-md-3"> 
                <input type="text" class="form-control" placeholder=""><span class="help-block">to</span>
              </div>
            </div>
          </div>
           <div class="form-group" id="tour5">
            <label for="exampleInputEmail1">Sponsor Code</label>
            <div class="input-group">
              <input type="text" class="form-control">
              <span class="input-group-addon"><span aria-hidden="true" class="icon-search"></span></span> </div>
          </div>
           <div class="form-group" id="tour6">
            <label for="exampleInputEmail1">Project Title</label>
            <textarea class="form-control" rows="3"></textarea>
          </div>
           <div class="form-group" id="tour7">
            <label for="exampleInputEmail1">Award ID</label>
            <div class="input-group">
              <input type="text" class="form-control">
              <span class="input-group-addon"><span aria-hidden="true" class="icon-search"></span></span> </div>
          </div>
           <div class="form-group" id="tour8">
            <label for="exampleInputEmail1">Original Institutional ID</label>
            <div class="input-group">
              <input type="text" class="form-control">
              <span class="input-group-addon"><span aria-hidden="true" class="icon-search"></span></span> </div>
          </div>
          <div class="form-group" id="tour9">
            <label for="exampleInputEmail1">Keywords</label>
            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="">
          </div>
        </form>
    
        
        <div class="uif-stickyFooter uif-stickyButtonFooter" > 
          
          <!-- Button row -->
          <div class="btn-row-page-action" style="text-align:center">
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='prop.basics.oppsearch-search.php'" class="btn btn-primary" id="tour10" >Save and continue</button>
          </div>
          <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>








