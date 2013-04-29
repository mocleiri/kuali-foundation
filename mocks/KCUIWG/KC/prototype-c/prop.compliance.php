<?php
# Variables
$page = 'compliance';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <?php require_once( 'assets/inc/document-header.php' ) ?>
      
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
          <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>
        <div class="span10">
          <h3>Compliance</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                <script>
					$(document).ready(function () {
						$(".addcomplianceprotocol").click(function(){
							$("#addrow01").slideDown(200);
							$("#norows01").slideUp(200);
						});
					});
				</script>
                <table class="table table-condensed">
                  <thead>
                    <tr>
                      <th>Type</th>
                      <th>Approval Status</th>
                      <th>Protocol Number</th>
                      <th>Application Date</th>
                      <th>Approval Date</th>
                      <th>Expiration Date</th>
                      <th>Exemption #</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr id="addrow01" style="display:none">
                      <td><select name="select" class="input-small" title="Type">
                          <option value="">select</option>
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
                          <option value="12">Foundation Relations</option>
                        </select></td>
                      <td><select name="select" class="input-small" title="* Approval Status">
                          <option value="">select</option>
                          <option value="2">Approved</option>
                          <option value="4">Exempt</option>
                          <option value="6">Link to IACUC</option>
                          <option value="5">Link to IRB</option>
                          <option value="3">Not yet applied</option>
                          <option value="1">Pending</option>
                        </select></td>
                      <td><input type="text" class="input-mini" name="textfield5" id="textfield3" value="9931CD" /></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name="" class="input-mini">
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name="" class="input-mini">
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name=""class="input-mini" >
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td>E2, E5, E6</td>
                      <td><a href="#" class="btn">Save</a> <a id="delete01" href="#">Cancel</a> 
                        <script>
							$(document).ready(function () {
								$("#delete01").click(function(){
									$("#addrow01").slideUp(200);
								});
							});
						</script></td>
                    </tr>
                    <tr id="addrow02">
                      <td><select name="select" class="input-small" title="Type">
                          <option value="">select</option>
                          <option value="1">Human Subjects</option>
                          <option value="2">Animal Usage</option>
                          <option value="3">Recombinant DNA</option>
                          <option value="4" selected="selected">Radioactive Isotopes</option>
                          <option value="5">Biohazard Materials</option>
                          <option value="6">International Programs</option>
                          <option value="7">Space Change</option>
                          <option value="8">TLO Review - No conflict (A)</option>
                          <option value="9">TLO review - Reviewed, no conflict (B1)</option>
                          <option value="10">TLO Review - Potential Conflict (B2)</option>
                          <option value="11">TLO PR-Previously Reviewed</option>
                          <option value="12">Foundation Relations</option>
                        </select></td>
                      <td><select name="select" class="input-small" title="* Approval Status">
                          <option value="">select</option>
                          <option value="2">Approved</option>
                          <option value="4">Exempt</option>
                          <option value="6">Link to IACUC</option>
                          <option value="5">Link to IRB</option>
                          <option value="3">Not yet applied</option>
                          <option value="1" selected="selected">Pending</option>
                        </select></td>
                      <td><input type="text" class="input-mini" name="textfield5" id="textfield3" value="9931CD" /></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name="" class="input-mini">
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name="" class="input-mini">
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td><div class="date" data-date="03-06-2013" data-date-format="mm-dd-yyyy">
                          <div class="input-append">
                            <input type="text" id="" name=""class="input-mini" >
                            <span class="add-on"><i class="icon-calendar"></i></span></div>
                        </div></td>
                      <td>E2, E5, E6</td>
                      <td><a id="delete02" href="#" class="btn btn-danger">Delete</a> 
                        <script>
              $(document).ready(function () {
                $("#delete02").click(function(){
                  $("#addrow02").slideUp(200);
                });
              });
                  </script></td>
                    </tr>
                    <tr style="display:none;">
                      <td>Human Subjects</td>
                      <td>Link to IRB</td>
                      <td>48456</td>
                      <td>01/01/2013</td>
                      <td>01/12/2013</td>
                      <td>01/01/2018</td>
                      <td>E2, E5, E6</td>
                      <td><a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                    <tr style="display:none;">
                      <td>Space Change</td>
                      <td>Not yet applied</td>
                      <td>48456</td>
                      <td>01/01/2013</td>
                      <td>01/12/2013</td>
                      <td>01/01/2018</td>
                      <td>E2, E5, E6</td>
                      <td><a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                    <tr style="display:none;">
                      <td>Biohazard Materials</td>
                      <td>Link to IRB</td>
                      <td>48456</td>
                      <td>01/01/2013</td>
                      <td>01/12/2013</td>
                      <td>01/01/2018</td>
                      <td>E2, E5, E6</td>
                      <td><a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                  </tbody>
                </table>
                <p style="margin-top: 10px;"><a href="#" class="btn addcomplianceprotocol"><i class="icon icon-plus"></i> Add Row</a></p>
              </fieldset>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.keypersonnel.creditfa.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.attachments.proposal.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
