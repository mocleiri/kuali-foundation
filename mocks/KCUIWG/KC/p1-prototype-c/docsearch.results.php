<?php
# Variables
$page = 'docsearch';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents"><?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <div class="row-fluid">
        <div class="span12" style="padding-top:4px"> <div class="docHeader">
            <h2>Doc Search: Results</h2>
            <small>
            <a href="#" id="triggerEditSearch"><em>refine search</em></a>
            </small>
          </div>
          <div id="editSearch">
            <div class="editSearchForm">
              <h3> Edit Search</h3>
              <form  class="form-horizontal" method="get" action="docsearch.results.html">
                <fieldset>
                  <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="input01">Document Type</label>
                    <div class="controls">
                      <input type="text" placeholder="" class="input-large">
                      <a href="docsearch.doctype.html" class="btn lookup"><span>lookup</span></a>
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="input01">Initiator</label>
                    <div class="controls">
                      <input type="text" placeholder="" value="Huntley, ASDF" class="input-large">
                      <a href="docsearch.initiator.html" class="btn lookup"><span>lookup</span></a>
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="input01">Document ID</label>
                    <div class="controls">
                      <input type="text" placeholder="" class="input-large">
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="input01">Sub Account Name</label>
                    <div class="controls">
                      <input type="text" placeholder="" class="input-large">
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="input01">Date Created</label>
                    <div class="controls">
                      <div style="display:inline-block; margin-right:12px;" >
                        <div style="display:inline">
                          <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                        </div>
                        <span style="display:block; width:100px; color:#999"><small>from</small></span>
                      </div>
                      <div style="display:inline-block" >
                        <div style="display:inline">
                          <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                        </div>
                        <span style="display:block; width:100px;color:#999"><small>to</small></span>
                      </div>
                    </div>
                  </div>
                  <hr>
                  <div class="control-group">
                    <label class="control-label"></label>
                    
                    <!-- Button -->
                    <div class="controls">
                      <button id="submitSearch" type="submit" class="btn btn-inverse">Search</button>
                      <button id="cancelSearch" class="btn btn-link">Cancel</button>
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
          <table class="table table-condensed table-hover">
            <thead >
              <tr >
                <th  > <a href="#" >Doc Id</a></th>
                <th  > <a href="#" >Type</a></th>
                <th  > <a href="#" >Title</a></th>
                <th  > <a href="#" >Route Status </a></th>
                <th  > <a href="#" >Initiator</a></th>
                <th  > <a href="#" >Date Created</a></th>
                <th  > <a href="#" >Route Log</a></th>
              </tr>
            </thead>
            <tbody >
              <tr  >
                <td><a href="asdf.html" title="" >3683</a></td>
                <td>Asset Retirement Global</td>
                <td>New AssetRetirementGlobal - test</td>
                <td>FINAL</td>
                <td><a href="docsearch.results.persinq.html" title="">Locklear, Audrey</a></td>
                <td>03/18/2013 11:14 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3661</a></td>
                <td>Disbursement Voucher</td>
                <td>Disbursement Voucher - Testing Dis Voucher</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Tanner, Tony</a></td>
                <td>03/18/2013 04:47 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3647</a></td>
                <td>Organization</td>
                <td>Edit Organization - hfghfhfghfgh</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Price, Micah</a></td>
                <td>03/18/2013 01:29 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3644</a></td>
                <td>Vendor</td>
                <td>Edit VendorDetail - trdtryy</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Rivera, Jackson</a></td>
                <td>03/18/2013 01:23 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="prop.basics.details.php" title="" >3643</a></td>
                <td>Proposal Development</td>
                <td>Occupational Therapy Community Program</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Clark, Tom</a></td>
                <td>03/18/2013 01:03 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3642</a></td>
                <td>Distribution Of Income And Expense</td>
                <td>Distribution Of Income And Expense - SA Kuali Days Demo</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Werner, Evan</a></td>
                <td>03/18/2013 01:00 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3683</a></td>
                <td>Asset Retirement Global</td>
                <td>New AssetRetirementGlobal - test</td>
                <td>FINAL</td>
                <td><a href="docsearch.results.persinq.html" title="">Jolly, Ayden</a></td>
                <td>03/18/2013 11:14 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3661</a></td>
                <td>Disbursement Voucher</td>
                <td>Disbursement Voucher - Testing Dis Voucher</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Mcduffie, Miles</a></td>
                <td>03/18/2013 04:47 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3647</a></td>
                <td>Organization</td>
                <td>Edit Organization - hfghfhfghfgh</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Allred, Corey</a></td>
                <td>03/18/2013 01:29 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3644</a></td>
                <td>Vendor</td>
                <td>Edit VendorDetail - trdtryy</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Locklear, Audrey</a></td>
                <td>03/18/2013 01:23 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3643</a></td>
                <td>Distribution Of Income And Expense</td>
                <td>Distribution Of Income And Expense - SA Kuali Days Demo</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Tanner, Tony</a></td>
                <td>03/18/2013 01:03 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3642</a></td>
                <td>Distribution Of Income And Expense</td>
                <td>Distribution Of Income And Expense - SA Kuali Days Demo</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Price, Micah</a></td>
                <td>03/18/2013 01:00 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3683</a></td>
                <td>Asset Retirement Global</td>
                <td>New AssetRetirementGlobal - test</td>
                <td>FINAL</td>
                <td><a href="docsearch.results.persinq.html" title="">Rivera, Jackson</a></td>
                <td>03/18/2013 11:14 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3661</a></td>
                <td>Disbursement Voucher</td>
                <td>Disbursement Voucher - Testing Dis Voucher</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Tovar, Brayan</a></td>
                <td>03/18/2013 04:47 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3647</a></td>
                <td>Organization</td>
                <td>Edit Organization - hfghfhfghfgh</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Werner, Evan</a></td>
                <td>03/18/2013 01:29 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3644</a></td>
                <td>Vendor</td>
                <td>Edit VendorDetail - trdtryy</td>
                <td>ENROUTE</td>
                <td><a href="docsearch.results.persinq.html" title="">Jolly, Ayden</a></td>
                <td>03/18/2013 01:23 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3643</a></td>
                <td>Distribution Of Income And Expense</td>
                <td>Distribution Of Income And Expense - SA Kuali Days Demo</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Mcduffie, Miles</a></td>
                <td>03/18/2013 01:03 AM</td>
                <td  >&nbsp;</td>
              </tr>
              <tr  >
                <td><a href="asdf.html" title="" >3642</a></td>
                <td>Distribution Of Income And Expense</td>
                <td>Distribution Of Income And Expense - SA Kuali Days Demo</td>
                <td>SAVED</td>
                <td><a href="docsearch.results.persinq.html" title="">Allred, Corey</a></td>
                <td>03/18/2013 01:00 AM</td>
                <td  >&nbsp;</td>
              </tr>
            </tbody>
          </table>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
    <?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->


</body>
</html>
