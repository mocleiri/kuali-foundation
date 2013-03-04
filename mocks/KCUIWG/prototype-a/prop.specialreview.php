<?php
# Variables
$page = 'compliance';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader ">
            <h3>Compliance</h3>
          </div>
          <div class="boxContent " style="display:block" >
            
            <div style="margin-bottom:10px;">
            	<a href="#" class="btn btn-mini btn-inverse addcomplianceprotocol"><i class="icon-white icon-plus"></i> Add Row</a>
                <script>
					$(document).ready(function () {
						$(".addcomplianceprotocol").click(function(){
							$("#addrow01").slideDown(200);
							$("#norows01").slideUp(200);
						});
					});
				</script>
            </div>
            
            <div id="dl3" style="display:block">
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
                    <th style="width: 70px;"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr id="addrow01" style="display:none">
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
                    <td>
                    	<a id="delete01" href="#" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i> Delete</a>
                        <script>
							$(document).ready(function () {
								$("#delete01").click(function(){
									$("#addrow01").slideUp(200);
									$("#norows01").slideDown(200);
								});
							});
						</script>
                        </td>
                  </tr>
                  <tr style="display:none;">
                    <td>Human Subjects</td>
                    <td>Link to IRB</td>
                    <td>48456</td>
                    <td>01/01/2013</td>
                    <td>01/12/2013</td>
                    <td>01/01/2018</td>
                    <td>E2, E5, E6</td>
                    <td><a href="#" class="btn btn-mini">delete</a></td>
                  </tr>
                  <tr style="display:none;">
                    <td>Space Change</td>
                    <td>Not yet applied</td>
                    <td>48456</td>
                    <td>01/01/2013</td>
                    <td>01/12/2013</td>
                    <td>01/01/2018</td>
                    <td>E2, E5, E6</td>
                    <td><a href="#" class="btn btn-mini">delete</a></td>
                  </tr>
                  <tr style="display:none;">
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
              <div id="norows01" style="display:block; font-style:italic; text-align:center; border:1px solid #DDDDDD; border-bottom-left-radius:5px; border-bottom-right-radius:5px; margin-top:-21px; padding:10px;">
                There are no compliance protocols.
              </div>
              <a href="#" class="btn btn-mini btn-inverse addcomplianceprotocol"><i class="icon-white icon-plus"></i> Add Row</a>
            </div>
          </div>
        </div>
        
        
        <div style=" padd12px; text-align:center">
          <a href="prop.permissions.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.abstracts.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>

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