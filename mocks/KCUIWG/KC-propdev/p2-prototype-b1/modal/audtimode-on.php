<?php

# Includes
require_once( 'inc/head.php' );
?>

<style>


.dataTables_filter, .dataTables_length { display:none}

</style>



<div class="modal-dialog">
  <div class="modal-content">
     <div class="modal-header clearfix">
      <h3 class="pull-left">Data Validation </h3>
      <a  href="audtimode-off.php"class="btn btn-default btn-xs pull-right btn-danger" style="margin-right:40px">Turn Off</a>
    </div>
    <div class="modal-body">
      <!-- There are no validation issues at this time! -->

      <!-- <table class="table table-condensed table-hover table-smaller-text" id="example">
        <thead>
          <tr>
            <th> <a href="#"> Area</a></th>
            <th><a href="#"> Section</a></th>
            <th> <a href="#">Description</a></th>
            <th> <a href="#"> Severity</a></th>
            <th> <a href="#">Action</a></th>
          </tr>
        </thead> -->
        <tbody>
          <tr>
            <td>Basics</td>
            <td>Proposal Details</td>
            <td>Activity Type must be specified</td>
            <td><span class="label label-danger">Blocker</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Basics</td>
            <td>Proposal Details</td>
            <td>Prime Sponsor has not been specified</td>
            <td><span class="label label-warning">Warning</span></td>
          <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Basics</td>
            <td>Sponsor &amp; Program Information</td>
            <td>Sponsor Div Code is missing</td>
            <td><span class="label label-info">FYI</span></td>
         <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Personnel</td>
            <td>The Investigators are not all certified. Please certify Ken Graves.</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Credit Allocation</td>
            <td>The Investigators Financial Credit Split does not equal 100%.</td>
            <td><span class="label label-danger">Blocker</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Credit Allocation</td>
            <td>The Investigators Space Credit Split does not equal 100%.</td>
           <td><span class="label label-info">FYI</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Credit Allocation</td>
            <td>The Investigators Responsibility Credit Split does not equal 100%.</td>
             <td><span class="label label-info">FYI</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Credit Allocation</td>
            <td>The Investigators Recognition Credit Split does not equal 100%.</td>
            <td><span class="label label-danger">Blocker</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Key Personnel</td>
            <td>Credit Allocation</td>
            <td>The Unit Recognition Credit Split for Ken Graves does not equal 100%.</td>
            <td><span class="label label-danger">Blocker</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>S2S Opportunity Search</td>
            <td>Opportunity</td>
            <td>You must complete the questionnaire &quot;Grants.gov S2S Questionnaire&quot;</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Attachments</td>
            <td>Proposal</td>
            <td>Invalid document type (thisfile.xml)</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Attachments</td>
            <td>Internal</td>
            <td>Invalid document type (thatfile.xml)</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Attachments</td>
            <td>Internal</td>
            <td>Invalid document type (theotherfile.xml)</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
          <tr>
            <td>Basics</td>
            <td>Sponsor &amp; Program Information</td>
            <td>Sponsor deadline date has not been entered.</td>
            <td><span class="label label-warning">Warning</span></td>
            <td><a href="#">Fix It</a></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- <div class="modal-footer"> </div> -->
  </div>
</div>
</div>
<?php require_once( 'inc/footer.php' ); ?>