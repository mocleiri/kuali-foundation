<?php
# Variables
$section = 'basics';
$page = 'basics-orgloc';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1">     <!-- Main content goes here -->
        
        <h3>Organization &amp; Location</h3>
        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
          <li class="active"><a href="#tab1" data-toggle="tab" class="" title="The organization or institution applying for this grant.">Applicant Organization</a></li>
          <li><a href="#tab2" data-toggle="tab" class="" title="The organization or institution where research will be performed at.">Performing Organization</a></li>
          <li><a href="#tab3" data-toggle="tab" class="" title="Other organizations or institutions that assist with research that are searchable via your organizations directory.">Performance Site Locations</a></li>
       <li><a href="#tab4" data-toggle="tab" class="" title="Organizations or institutions that assist with research that are outside of your organizations directory. You will need to enter the details manually.">Other Organizations</a></li>
        </ul>
        <div id="my-tab-content" class="tab-content">
          <div class="tab-pane active" id="tab1">
            <h4>Applicant Organization</h4>
            <table  class="table table-condensed">
              <tbody>
                <tr>
                  <th class="col-md-3">Organization</th>
                  <td>Your institution here</td>
                </tr>
                <tr>
                  <th class="col-md-3">Address 1</th>
                  <td>100 Some Stree</td>
                </tr>
                <tr>
                  <th>Address 2</th>
                  <td></td>
                </tr>
                <tr>
                  <th>City</th>
                  <td>Somewhereville</td>
                </tr>
                <tr>
                  <th>State</th>
                  <td>MA</td>
                </tr>
                <tr>
                  <th>ZIP</th>
                  <td>12345</td>
                </tr>
                <tr>
                  <th>Congressional Districts</th>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="tab-pane " id="tab2">
      
            
            
            
              
            
            <div class="has-tools">
              <h4> Performing Organization</h4>

          <div class="uif-toolbar">
            <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution.php"><span aria-hidden="true" class="icon-search"></span> Select different organization</button>
          </div>
        </div>
        
        
        
        
        
            <table  class="table table-condensed">
              <tbody>
                <tr>
                  <th class="col-md-3">Organization</th>
                  <td>Your institution here</td>
                </tr>
                <tr>
                  <th class="col-md-3">Address 1</th>
                  <td>100 Some Stree</td>
                </tr>
                <tr>
                  <th>Address 2</th>
                  <td></td>
                </tr>
                <tr>
                  <th>City</th>
                  <td>Somewhereville</td>
                </tr>
                <tr>
                  <th>State</th>
                  <td>MA</td>
                </tr>
                <tr>
                  <th>ZIP</th>
                  <td>12345</td>
                </tr>
                <tr>
                  <th>Congressional Districts</th>
                  <td></td>
                </tr>
              </tbody>
            </table>
          
          </div>
          <div class="tab-pane" id="tab3">
            <h4> Performance Site Locations</h4>
            <div class="panel-group" id="accordion1">
              
            </div>

            <div class="uif-toolbar">
              <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution-other.php"><span aria-hidden="true" class="icon-plus"></span> Add location</button>
            </div>
          </div>
        <div class="tab-pane " id="tab4">
            <h4> Other Organizations</h4>
            <div class="panel-group" id="accordion2">
              
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-institution.php"><span aria-hidden="true" class="icon-plus"></span> Add location</button>
            </div>
          </div>
        </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter">
    <div class="btn-row-page-action">
      <button href="prop.basics.sponsor.php" class="btn btn-default">Back</button>
      <button class="btn btn-default">Save</button>
      <button href="prop.keypersonnel.start.php" class="btn btn-primary">Save and Continue</button>
    </div>
        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>