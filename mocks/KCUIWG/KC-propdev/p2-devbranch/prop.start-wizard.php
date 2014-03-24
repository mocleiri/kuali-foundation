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

  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
   
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">  <h3>Create Proposal</h3>
        
        
        
        
        <form id="demoForm" method="post" action="prop.basics.details.php" class="bbq" >
            <div id="">
            <div id="start" class="step">
              <fieldset>
                <legend style="padding-bottom:20px;">What type of proposal would you like to create?</legend>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="step2" class="link">
                    Continuation </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="step2" class="link">
                    New </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="step2" class="link">
                    Renewal </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios4" value="step2" class="link">
                    Resubmission </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios5" value="step2" class="link">
                    Revision </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios6" value="step2" class="link">
                    Task Order </label>
                </div>
              </fieldset>
            </div>
            <div id="step2" class="step">
              <fieldset>
                <legend style="padding-bottom:20px;">Which unit is designated as responsible for the application and administration of this proposed project?</legend>
              </fieldset>
              <div class="radio">
                <label>
                  <input type="radio" name="optionsLeadUnit" id="optionsLeadUnit1" value="step3" class="link">
                  000001 - University </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" name="optionsLeadUnit" id="optionsLeadUnit2" value="step3" class="link">
                  BL-IIDC - IND INST ON DISABILITY/COMMNTY </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" name="optionsLeadUnit" id="optionsLeadUnit3" value="step3" class="link">
                  IN-CARD - CARDIOLOGY </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" name="optionsLeadUnit" id="optionsLeadUnit4" value="step3" class="link">
                  IN-CARR - CARDIOLOGY RECHARGE CTR </label>
              </div>
            </div>
            <div id="step3" class="step">
              <fieldset>
                <legend>Which activity type best describes this proposed project?</legend>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType1" value="step4" class="link">
                    Clinical Trial </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType2" value="step4" class="link">
                    Construction </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType3" value="step4" class="link">
                    Fellowship - Post-Doctoral </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType4" value="step4" class="link">
                    Fellowship - Pre-Doctoral</label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType5" value="step4" class="link">
                    Instruction</label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType6" value="step4" class="link">
                    Public Service</label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType7" value="step4" class="link">
                    Research</label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType8" value="step4" class="link">
                    Student</label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsLeadUnit" id="optionsActivityType9" value="step4" class="link">
                    Other</label>
                </div>
              </fieldset>
            </div>
            <div id="step4" class="step">
              <fieldset>
                <legend>Please enter a project title for this proposed project:</legend>
                <div class="form-group col-lg-4">
                  <label for="exampleInputEmail1">Project Title </label>
                  <textarea class="form-control" rows="3"></textarea>
                </div>
              </fieldset>
            </div>
            <div id="step5" class="step">
              <fieldset>
                <legend>Please select a sponsor code for this proposed project:</legend>
                <div class="form-group col-lg-4 " id="">
                  <label for="">Sponsor Code</label>
                  <div class="input-group ">
                    <input type="text" class="form-control">
                    <span class="input-group-addon"><span aria-hidden="true" class="icon-search"></span></span> </div>
                </div>
              </fieldset>
            </div>
            <div id="step6" class="step">
              <fieldset>
                <legend>What is your project date range?</legend>
                <div class="form-group" id="">
                  <label class=""></label>
                  <div class="row">
                    <div class="col-md-3">
                      <input type="text" class="form-control" placeholder="">
                      <span class="help-block">from</span> </div>
                    <div class="col-md-3">
                      <input type="text" class="form-control" placeholder="">
                      <span class="help-block">to</span> </div>
                  </div>
                </div>
              </fieldset>
            </div>
            <div class=" btn-row-widget-action">
              <input class="btn btn-default btn-xs" id="back" value="Back" type="reset" />
              <input class="btn btn-primary btn-xs" id="next" value="Next" type="submit" />
            </div>
          </form>
          
          
          
          
        
      </div>
    </div>
  </div>
</section>

    <script type="text/javascript" src="themes/kc/js/wizard/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="themes/kc/js/wizard/jquery.form.js"></script>
<script type="text/javascript" src="themes/kc/js/wizard/jquery.validate.js"></script>
<script type="text/javascript" src="themes/kc/js/wizard/bbq.js"></script>
<script type="text/javascript" src="themes/kc/js/wizard/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="themes/kc/js/wizard/jquery.form.wizard.js"></script>
<script type="text/javascript">
			$(function(){
				$("#demoForm").formwizard({ 
				 	validationEnabled: true,
				 	focusFirstInput : true,
				 }
				);
  		});
    </script>
    
    
<?php require_once( 'themes/kc/inc/footer.php' ); ?>








