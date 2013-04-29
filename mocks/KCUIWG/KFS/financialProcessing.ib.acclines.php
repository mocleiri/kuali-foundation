<?php
# Variables
$page = 'ib-accLines';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">
      <div class="row-fluid">
        <?php require_once( 'assets/inc/document-header.php' ) ?>
      </div>
      <div class="leftnavContent">
        <?php require_once( 'assets/inc/document-nav.php' ) ?>
        <div id="content" role="application">
          <div class="row-fluid">
            <div class="span12 content">
              <h3> Accounting Lines</h3>
              <table class="table table-condensed table-hover accounting">
                <tr>
                  <th colspan="9" class="theadstyle">Income</th>
                </tr>
                <tr>
                  <th>Chart</th>
                  <th>Acc</th>
                  <th>Sub-acc</th>
                  <th>Obj</th>
                  <th>Sub-Obj</th>
                  <th>Project</th>
                  <th>Org Ref ID</th>
                  <th>Amount</th>
                  <th>Actions</th>
                </tr>
                <tr>
                  <td><select class="input-mini"  id="newSourceLine.chartOfAccountsCode2" name="newSourceLine.chartOfAccountsCode2" title="Chart for New Income Line" onblur="loadChartInfo( this.name, 'newSourceLine.chart.finChartOfAccountDescription' );">
                      <option value="" selected="selected"></option>
                      <option value="BA">BA</option>
                      <option value="BL">BL</option>
                      <option value="EA">EA</option>
                      <option value="FW">FW</option>
                      <option value="HO">HO</option>
                      <option value="IA">IA</option>
                      <option value="IN">IN</option>
                      <option value="IU">IU</option>
                      <option value="KO">KO</option>
                      <option value="NW">NW</option>
                      <option value="SB">SB</option>
                      <option value="SE">SE</option>
                      <option value="UA">UA</option>
                    </select></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><a href="#" class="btn btn-inverse"><i class="icon-white icon-plus"></i></a></td>
                </tr>
                <tr>
                  <td><select class="input-mini"  id="newSourceLine.chartOfAccountsCode3" name="newSourceLine.chartOfAccountsCode3" title="Chart for New Income Line" onblur="loadChartInfo( this.name, 'newSourceLine.chart.finChartOfAccountDescription' );">
                      <option value=""></option>
                      <option value="BA">BA</option>
                      <option value="BL">BL</option>
                      <option value="EA" selected="selected">EA</option>
                      <option value="FW">FW</option>
                      <option value="HO">HO</option>
                      <option value="IA">IA</option>
                      <option value="IN">IN</option>
                      <option value="IU">IU</option>
                      <option value="KO">KO</option>
                      <option value="NW">NW</option>
                      <option value="SB">SB</option>
                      <option value="SE">SE</option>
                      <option value="UA">UA</option>
                    </select></td>
                  <td><input type="text" class="input-mini" value="1200"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input type="text" class="input-mini" value="1421"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input type="text" class="input-mini" value="320.00"></td>
                  <td><a href="#" class="btn"><i class="icon-remove"></i></a></td>
                </tr>
                <tr>
                    <th colspan="9" class="theadstyle">Expense</th>
                </tr>
                <tr>
                  <th>Chart</th>
                  <th>Acc</th>
                  <th>Sub-acc</th>
                  <th>Obj</th>
                  <th>Sub-Obj</th>
                  <th>Project</th>
                  <th>Org Ref ID</th>
                  <th>Amount</th>
                  <th>Actions</th>
                </tr>
                <tr>
                  <td><select  class="input-mini"  id="newSourceLine.chartOfAccountsCode" name="newSourceLine.chartOfAccountsCode" title="Chart for New Income Line" onblur="loadChartInfo( this.name, 'newSourceLine.chart.finChartOfAccountDescription' );">
                      <option value="" selected="selected"></option>
                      <option value="BA">BA</option>
                      <option value="BL">BL</option>
                      <option value="EA">EA</option>
                      <option value="FW">FW</option>
                      <option value="HO">HO</option>
                      <option value="IA">IA</option>
                      <option value="IN">IN</option>
                      <option value="IU">IU</option>
                      <option value="KO">KO</option>
                      <option value="NW">NW</option>
                      <option value="SB">SB</option>
                      <option value="SE">SE</option>
                      <option value="UA">UA</option>
                    </select></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><a href="#" class="btn btn-inverse"><i class="icon-white icon-plus"></i></a></td>
                </tr>
                <tr>
                  <td><select class="input-mini"  id="newSourceLine.chartOfAccountsCode5" name="newSourceLine.chartOfAccountsCode4" title="Chart for New Income Line" onblur="loadChartInfo( this.name, 'newSourceLine.chart.finChartOfAccountDescription' );">
                      <option value=""></option>
                      <option value="BA">BA</option>
                      <option value="BL">BL</option>
                      <option value="EA" selected="selected">EA</option>
                      <option value="FW">FW</option>
                      <option value="HO">HO</option>
                      <option value="IA">IA</option>
                      <option value="IN">IN</option>
                      <option value="IU">IU</option>
                      <option value="KO">KO</option>
                      <option value="NW">NW</option>
                      <option value="SB">SB</option>
                      <option value="SE">SE</option>
                      <option value="UA">UA</option>
                    </select></td>
                  <td><input type="text" class="input-mini" value="1200"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input type="text" class="input-mini" value="1421"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input class="input-mini" type="text"></td>
                  <td><input type="text" class="input-mini" value="320.00"></td>
                  <td><a href="#" class="btn"><i class="icon-remove"></i></a></td>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.attachments.proposal.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.attachments.internal.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
