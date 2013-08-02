<?php
# Variables
$page = 'review-summary';
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
            <h3>Proposal Summary</h3>
          </div>
          <div class="boxContent " style="min-height:300px" >
            
            <div class="boxSubheader">
              <h4>Overview</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Investigator</dt>
              <dd>Haskell, Edward</dd>
              <dt>Agency/Sponsor</dt>
              <dd>006039 : Novartis Pharma AG</dd>
              <dt>Title</dt>
              <dd>Computationally guided strategies to mitigate viscosities of...</dd>
              <dt>Proposal Number</dt>
              <dd>0015753 (Approval In Progress)</dd>
              <dt>Proposal Period</dt>
              <dd>02/01/2013 &ndash; 01/31/2015</dd>
              <dt>Deadline Date</dt>
              <dd>01/23/2013</dd>
            </dl>
            <div class="clearfix"></div>
            
            <div class="boxSubheader">
              <h4>Budget Total</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Direct Cost</dt>
              <dd style="text-align:right;">$216,298.39</dd>
              <dt>Indirect Cost</dt>
              <dd style="text-align:right;">$118,327.10</dd>
              <dt>Total Cost</dt>
              <dd style="text-align:right;">$334,625.49</dd>
              <dt>Under Recovery</dt>
              <dd style="text-align:right;">$0.00</dd>
              <dt>Cost Share</dt>
              <dd style="text-align:right;">$0.0015</dd>
              <dt>Period</dt>
              <dd>02/01/2013 &ndash; 01/31/2015</dd>
            </dl>
            <div class="clearfix"></div>
            
            <div class="boxSubheader">
              <h4>Critical Flags</h4>
              <!--<div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>-->
            </div>
            <div>
            	<ul>
                	<li>Part of project performed outside US: (PI) Haskell</li>
                	<li>Restricted access or use for some researchers: (PI) Haskell</li>
                	<li>Use of space other than submitting unit: (PI) Haskell</li>
                	<li>Will appoint individuals outside of submitting unit: (PI) Haskell</li>
                	<li>Will ship outside the US: (PI) Haskell</li>
                </ul>
            </div>
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.summary.jpg" alt="temp screenshot" style="width:732px; height:186px;" />
            </div>-->
          
          </div>
        </div>

        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        
        <div style=" padd12px; text-align:center">
          <a href="prop.medusa.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.review.persons.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>