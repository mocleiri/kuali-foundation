<?php
# Variables
$page = 'compliance';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader ">
            <h3> Special Review</h3>
          </div>
          <div class="boxContent " style=" display:" >
            
            <div id="dl3" style="display:">
              <table class="table table-striped table-bordered table-condensed">
                <thead>
                  <tr>
                    <th> Type</th>
                    <th>Approval Status</th>
                    <th>Protocol Number</th>
                    <th>Application Date</th>
                    <th>Approval Date</th>
                    <th>Expiration Date</th>
                    <th>Exemption #</th>
                    <th style="width: 36px;"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <select class="input-small" title="Type"><option value="">select</option>
<option value="1">Human Subjects</option>
<option value="2">Animal Usage</option>
<option value="3">Recombinant DNA</option>
<option value="4">Radioactive Isotopes</option>
<option value="5">Biohazard Materials</option>
<option value="6">International Programs</option>
<option value="7">Space Change</option>
<option value="8">TLO Review - No conflict (A)</option>
<option value="9">TLO review - Reviewed, no conflict (B1)</option>
<option value="10">TLO Review - Potential Conflict (B2)</option>
<option value="11">TLO PR-Previously Reviewed</option>
<option value="12">Foundation Relations</option></select>
                      </td>
                    <td><select class="input-small" title="* Approval Status"><option value="">select</option>
<option value="2">Approved</option>
<option value="4">Exempt</option>
<option value="6">Link to IACUC</option>
<option value="5">Link to IRB</option>
<option value="3">Not yet applied</option>
<option value="1">Pending</option></select></td>
                    <td>
                      <input type="text" class=" input-mini" name="textfield5" id="textfield3" tabindex="1" />
                      </td>
                    <td><a href="#">01/01/2013</a></td>
                    <td><a href="#">01/12/2013</a></td>
                    <td><a href="#">01/01/2018</a></td>
                    <td><a href="#">select</a></td>
                    <td><a href="#" class="btn btn-inverse btn-mini">add</a></td>
                  </tr>
                  <tr>
                    <td>Human Subjects</td>
                    <td>Link to IRB</td>
                    <td>48456</td>
                    <td>01/01/2013</td>
                    <td>01/12/2013</td>
                    <td>01/01/2018</td>
                    <td>E2, E5, E6</td>
                    <td><a href="#" class="btn btn-mini">delete</a></td>
                  </tr>
                  <tr>
                    <td>Space Change</td>
                    <td>Not yet applied</td>
                    <td>48456</td>
                    <td>01/01/2013</td>
                    <td>01/12/2013</td>
                    <td>01/01/2018</td>
                    <td>E2, E5, E6</td>
                    <td><a href="#" class="btn btn-mini">delete</a></td>
                  </tr>
                  <tr>
                    <td>Biohazard Materials</td>
                    <td>Link to IRB</td>
                    <td>48456</td>
                    <td>01/01/2013</td>
                    <td>01/12/2013</td>
                    <td>01/01/2018</td>
                    <td>E2, E5, E6</td>
                    <td><a href="#" class="btn btn-mini">delete</a></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        
        
        <div style=" padd12px; text-align:center"> <a href="prop.keypersonnel.pers.php" class="btn"><i class="icon-chevron-left"></i> back</a> <a href="#" class="btn">save</a> <a href="prop.customdata.php" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div>

        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>