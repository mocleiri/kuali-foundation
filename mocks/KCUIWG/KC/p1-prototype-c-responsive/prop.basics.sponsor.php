<?php
# Variables
$page = 'basics-sponsor';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">   

<?php require_once( 'assets/inc/document-header.php' ) ?>
      
      
      <div class="leftnavContent">
      <?php require_once( 'assets/inc/document-nav.php' ) ?>
      
      <div id="content" role="application">
        <div class="row-fluid">
          






<div class="span12 content"> <h3>Sponsor &amp; Program Information</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
              <fieldset style="">
                         <div class="control-group">
                    <label class="control-label" for="ProposalType4">Notice of Opportunity</label>
                    <div class="controls">
                      <select name="select2" class="input-xlarge" title="Notice of Opportunity">
                        <option value="">select</option>
                        <option value="1">Federal Solicitation</option>
                        <option value="2">Unsolicited</option>
                        <option value="3">Verbal Request for Proposal</option>
                        <option value="4">SBIR Solicitation</option>
                        <option value="5">STTR Solicitation</option>
                        <option value="6">Non-Federal Solicitation</option>
                        <option value="7">Internal</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="ProposalType5">Sponsor Deadline Type</label>
                    <div class="controls">
                      <select class="input-xlarge" title="Sponsor Deadline Type">
                        <option value="">select</option>
                        <option value="P">Postmark</option>
                        <option value="R">Receipt</option>
                        <option value="T">Target</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description3">CFDA Number</label>
                    <div class="controls">
                      <input type="text" id="Description3" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description4">Sponsor Name</label>
                    <div class="controls">
                      <input type="text" id="Description4" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description5">Opportunity ID</label>
                    <div class="controls">
                      <input type="text" id="Description5" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description6">Prime Sponsor ID</label>
                    <div class="controls">
                      <input type="text" id="Description6" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description7">Sponsor Proposal ID</label>
                    <div class="controls">
                      <input type="text" id="Description7" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="ProposalType6">NSF Science Code</label>
                    <div class="controls">
                      <select name="ProposalType2" class="input-xlarge" id="ProposalType6">
                        <option value="">select</option>
                        <option value="A.01">Aeronautical and Astronautical - Engineering: A.01</option>
                        <option value="F.01">Agricultural - Life Sciences: F.01</option>
                        <option value="B.01">Astronomy - Physical Sciences: B.01</option>
                        <option value="C.01">Atmospheric - Environmental Sciences: C.01</option>
                        <option value="A.02">Bioengineering/Biomedical - Engineering: A.02</option>
                        <option value="F.02">Biological - Life Sciences: F.02</option>
                        <option value="J.05">Business and Management - Non-Science and Engineering Fields: J.05</option>
                        <option value="A.03">Chemical - Engineering: A.03</option>
                        <option value="B.02">Chemistry - Physical Sciences: B.02</option>
                        <option value="A.04">Civil - Engineering: A.04</option>
                        <option value="J.06">Communications, Journalism and Library Sciences - Non-Science and Engineering Fields: J.06</option>
                        <option value="E.01">Computer Sciences: E.01</option>
                        <option value="Z.99">Costs not included in NSF report - used for reconciliation purposes: Z.99</option>
                        <option value="J.98">Costs to be allocated: J.98</option>
                        <option value="C.02">Earth Sciences - Environmental Sciences: C.02</option>
                        <option value="H.01">Economics - Social Sciences: H.01</option>
                        <option value="J.01">Education - Non-Science and Engineering Fields: J.01</option>
                        <option value="A.05">Electrical - Engineering: A.05</option>
                        <option value="J.03">Humanities - Non-Science and Engineering Fields: J.03</option>
                        <option value="J.02">Law - Non-Science and Engineering Fields: J.02</option>
                        <option value="D.01">Mathematical Science: D.01</option>
                        <option value="A.06">Mechanical  - Engineering: A.06</option>
                        <option value="F.03">Medical - Life Sciences: F.03</option>
                        <option value="A.07">Metallurgical and Materials  - Engineering: A.07</option>
                        <option value="C.03">Oceanography - Environmental Sciences: C.03</option>
                        <option value="A.99">Other - Engineering: A.99</option>
                        <option value="C.99">Other - Environmental Sciences: C.99</option>
                        <option value="F.99">Other - Life Sciences: F.99</option>
                        <option value="J.99">Other - Non-Science and Engineering Fields: J.99</option>
                        <option value="B.99">Other - Physical Sciences: B.99</option>
                        <option value="H.99">Other - Social Sciences: H.99</option>
                        <option value="I.01">Other Sciences, n.e.c.: I.01</option>
                        <option value="B.03">Physics - Physical Sciences: B.03</option>
                        <option value="H.02">Political Science - Social Sciences: H.02</option>
                        <option value="G.01">Psychology: G.01</option>
                        <option value="J.07">Social Work - Non-Science and Engineering Fields: J.07</option>
                        <option value="H.03">Sociology - Social Sciences: H.03</option>
                        <option value="J.04">Visual and Performing Arts - Non-Science and Engineering Fields: J.04</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="ProposalType7">Subawards</label>
                    <div class="controls">
                      <label class="checkbox">
                        <input type="checkbox" value="">
                        Does this proposal include subaward(s)?</label>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description9">Sponsor Div Code</label>
                    <div class="controls">
                      <input type="text" id="Description9" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="Description10">Sponsor Program Code</label>
                    <div class="controls">
                      <input type="text" id="Description10" placeholder="" class="input-xlarge">
                    </div>
                  </div>
                  <div class="control-group">
                    <!-- Textarea -->
                    <label class="control-label">Opportunity Title</label>
                    <div class="controls">
                      <div class="textarea">
                        <textarea name="textarea2" class="input-xlarge"> </textarea>
                      </div>
                    </div>
                  </div>
       
              </fieldset>
              
            </form>
          </div>
          <div class="clearfix"></div>
        </div>










        </div> </div>
      




</div>
<div class="docControls"> <a href="prop.basics.grantsgov.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
                  <a href="#" class="btn">Save</a>
                  <a href="prop.basics.orgloc.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a></div>




    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
