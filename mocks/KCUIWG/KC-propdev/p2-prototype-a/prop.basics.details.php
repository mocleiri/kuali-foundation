<?php
# Variables
$page = 'basics-details';
?>
<?php require_once( 'assets/inc/head.php' ) ?>

<link rel="stylesheet" type="text/css" href="assets/js/tags/jquery.tagsinput.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/tags/jquery.tagsinput.js"></script>
<!-- To test using the original jQuery.autocomplete, uncomment the following -->
<!--
	<script type='text/javascript' src='http://xoxco.com/x/tagsinput/jquery-autocomplete/jquery.autocomplete.min.js'></script>
	<link rel="stylesheet" type="text/css" href="http://xoxco.com/x/tagsinput/jquery-autocomplete/jquery.autocomplete.css" />
	-->
<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js'></script>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/themes/start/jquery-ui.css" />
<script type="text/javascript">
		
		function onAddTag(tag) {
			alert("Added a tag: " + tag);
		}
		function onRemoveTag(tag) {
			alert("Removed a tag: " + tag);
		}
		
		function onChangeTag(input,tag) {
			alert("Changed a tag: " + tag);
		}
		
		$(function() {

		
			$('#tags_1, #tags_2, #tags_3, #tags_4').tagsInput({
				width: 'auto',

				//autocomplete_url:'test/fake_plaintext_endpoint.html' //jquery.autocomplete (not jquery ui)
				autocomplete_url:'assets/js/tags/keywords.html' // jquery ui autocomplete requires a json endpoint
			});
			

// Uncomment this line to see the callback functions in action
//			$('input.tags').tagsInput({onAddTag:onAddTag,onRemoveTag:onRemoveTag,onChange: onChangeTag});		

// Uncomment this line to see an input with no interface for adding new tags.
//			$('input.tags').tagsInput({interactive:false});
		});
	
	</script>
    
    <style> #tags_1_addTag { padding-top:5px}</style>
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
            <div class="span12 content">
              <h3> Proposal Details</h3>
              <div class=" formfields" style="display:">
                <form  class="form-horizontal" method="get" action="">
                  <fieldset style="">
                    <div class="control-group">
                      <label class="control-label" for="ProposalType">Proposal Type</label>
                      <div class="controls">
                        <select name="ProposalType" class="input-xlarge" id="ProposalType" required="required">
                          <option value="">select</option>
                          <option value="4">Continuation</option>
                          <option value="1" selected="selected">New</option>
                          <option value="3">Renewal</option>
                          <option value="2">Resubmission</option>
                          <option value="5">Revision</option>
                          <option value="6">Task Order</option>
                        </select>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="leadUnit">Lead Unit</label>
                      <div class="controls">
                        <select name="select" id="leadUnit" class="input-xlarge" title="Lead Unit" required="required">
                          <option value="">select</option>
                          <option value="000001" selected="selected">000001 - University</option>
                          <option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
                          <option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
                          <option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
                        </select>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="activityType">Activity Type</label>
                      <div class="controls">
                        <select name="select" id="activityType" class="input-xlarge" title="Activity Type" required="required">
                          <option value="">select</option>
                          <option value="4" selected="selected">Clinical Trial</option>
                          <option value="9">Construction</option>
                          <option value="7">Fellowship - Post-Doctoral</option>
                          <option value="6">Fellowship - Pre-Doctoral</option>
                          <option value="2">Instruction</option>
                          <option value="3">Public Service</option>
                          <option value="1">Research</option>
                          <option value="8">Student Services</option>
                          <option value="5">other</option>
                        </select>
                      </div>
                    </div>
                    <div class="control-group"> 
                      <!-- Text input-->
                      <label class="control-label" for="input01">Project Start/End</label>
                      <div class="controls">
                        <div style="display:inline-block; margin-right:12px;" >
                          <div style="display:inline">
                            <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                          </div>     <span style="display:block; width:100px; color:#999"><small>Start</small></span>
                      </div>
                      <div style="display:inline-block" >
                        <div style="display:inline">
                          <input type="text" placeholder="mm/dd/yyyy" class="input-small">
                        </div>
                        <span style="display:block; width:100px;color:#999"><small>End</small></span> </div>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="orgdocnum2">Sponsor Code</label>
                      <div class="controls">
                        <input type="text" id="Description" placeholder="" class=" input-small">
                        <a href="#" id="" class="btn lookup"><span>lookup</span></a> </div>
                    </div>
                    <div class="control-group"> 
                      <!-- Textarea -->
                      <label class="control-label" for="projectTitle">Project Title</label>
                      <div class="controls">
                        <textarea name="textarea" id="projectTitle" class="input-xlarge" required>This is a test project</textarea>
                      </div>
                    </div>     
                       <div class="control-group"> 
                      <!-- Textarea -->
                      <label class="control-label" for="projectTitle">Keywords (optional)</label>
                      <div class="controls">
                          <input id='tags_1' type='text' class='tags'>
                      </div>
                    </div>     
                       
                       <div class="control-group">
                    <label class="control-label" for="orgdocnum">Award ID</label>
                    <div class="controls">
                  
                      <input type="text" id="Description" placeholder="" class=" input-small"> 
                      <a href="#" id="" class="btn lookup"><span>lookup</span></a> 
                    </div>
                  </div><div class="control-group">
                    <label class="control-label" for="orgdocnum">Original Institutional Proposal ID</label>
                    <div class="controls">
                  
                      <input type="text" id="Description" placeholder="" class=" input-small"> 
                      <a href="#" id="" class="btn lookup"><span>lookup</span></a> 
                    </div>
                  </div>
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="#" class="btn">Save</a> <a href="prop.basics.grantsgov.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<script>
$(document).ready(function() {
    $(".lookup").on('click', function() {
        $.fancybox.open({
            href: 'modal/lookup.html',
            type: 'iframe',
            padding: 0,
            width: 640,
        });
    });
});
</script>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
