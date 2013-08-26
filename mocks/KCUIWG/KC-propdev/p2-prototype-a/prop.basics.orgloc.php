<?php
# Variables
$page = 'basics-orgloc';
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
				autocomplete_url:'assets/js/tags/districts.html' // jquery ui autocomplete requires a json endpoint
			});
			

// Uncomment this line to see the callback functions in action
//			$('input.tags').tagsInput({onAddTag:onAddTag,onRemoveTag:onRemoveTag,onChange: onChangeTag});		

// Uncomment this line to see an input with no interface for adding new tags.
//			$('input.tags').tagsInput({interactive:false});
		});
	
	</script>
<script>
function message()
{
alert("This goes to a lookup");
}


</script>
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
              <h3>Organization &amp; Location</h3>
              <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                <li class="active"><a href="#tab1" data-toggle="tab">Applicant Organization</a></li>
                <li><a href="#tab2" data-toggle="tab">Performing Organization</a></li>
                <li><a href="#tab3" data-toggle="tab">Performance Site Locations</a></li>
                <li><a href="#tab4" data-toggle="tab">Other Organizations</a></li>
              </ul>
              <div id="my-tab-content" class="tab-content">
                <div class="tab-pane active" id="tab1">
                  <table  class="table table-condensed">
                    <tbody>
                      <tr>
                        <th style="width:30%">Organization</th>
                        <td>University of Michigan</td>
                      </tr>
                      <tr>
                        <th style="width:30%">Address 1</th>
                        <td>2044 Wolverine Tower</td>
                      </tr>
                      <tr>
                        <th>Address 2</th>
                        <td>3003 State Street</td>
                      </tr>
                      <tr>
                        <th>City</th>
                        <td>ANN ARBOR</td>
                      </tr>
                      <tr>
                        <th>State</th>
                        <td>MI</td>
                      </tr>
                      <tr>
                        <th>ZIP</th>
                        <td>48109-1273</td>
                      </tr>
                      <tr>
                        <th>Congressional District</th>
                        <td><form>
                            <input id='tags_1' type='text' class='tags'>
                          </form></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="tab-pane " id="tab2">
                  <div class="well well-small"> <a href="#" class="btn btn-small" onclick="message()" value="Show alert box"> change organization... </a> </div>
                  <table  class="table table-condensed">
                    <tbody>
                      <tr>
                        <th style="width:30%">Organization</th>
                        <td>University of Michigan</td>
                      </tr>
                      <tr>
                        <th style="width:30%">Address 1</th>
                        <td>2044 Wolverine Tower</td>
                      </tr>
                      <tr>
                        <th>Address 2</th>
                        <td>3003 State Street</td>
                      </tr>
                      <tr>
                        <th>City</th>
                        <td>ANN ARBOR</td>
                      </tr>
                      <tr>
                        <th>State</th>
                        <td>MI</td>
                      </tr>
                      <tr>
                        <th>ZIP</th>
                        <td>48109-1273</td>
                      </tr>
                      <tr>
                        <th>Congressional District</th>
                        <td><form>
                            <input id='tags_2' type='text' class='tags'>
                          </form></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="tab-pane " id="tab3">
                  <div class="well well-small"> 
                    <!--  <h4>Add location</h4>-->
                    <form class="form-inline">
                      <input type="text" class=" input-large" placeholder="e.g. Research Location">
                      <button type="submit" class="btn btn-small">add</button>
                    </form>
                  </div>
                  <div class="accordion" id="accordion2">
                    <div class="accordion-group">
                      <div class="accordion-heading"> <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"> Clinical Trial Location <i style="margin-top:; margin-right:; opacity:.5" class="icon-remove-sign pull-right"></i> </a> </div>
                      <div id="collapseOne" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                          <table  class="table table-condensed">
                            <tbody>
                              <tr>
                                <th style="width:30%">Organization</th>
                                <td>select... <a href="#"><i class="icon-search" style="opacity:.5"></i></a></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="accordion-group">
                      <div class="accordion-heading"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo"> Research Location <i style="margin-top:; margin-right:; opacity:.5" class="icon-remove-sign pull-right"></i> </a> </div>
                      <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                          <table  class="table table-condensed">
                            <tbody>
                              <tr>
                                <th style="width:30%">Organization</th>
                                <td>United Technologies/Pratt &amp; Whitney <a href="#"><i class="icon-search" style="opacity:.5"></i></a></td>
                              </tr>
                              <tr>
                                <th style="width:30%">Address 1</th>
                                <td>Mail Stop 169-21 </td>
                              </tr>
                              <tr>
                                <th>Address 2</th>
                                <td>400 Main Street </td>
                              </tr>
                              <tr>
                                <th>City</th>
                                <td>East Hartford</td>
                              </tr>
                              <tr>
                                <th>State</th>
                                <td> CT  </td>
                              </tr>
                              <tr>
                                <th>ZIP</th>
                                <td>06108</td>
                              </tr>
                              <tr>
                                <th>Congressional District</th>
                                <td><form>
                                    <input id='tags_3' type='text' class='tags'>
                                  </form></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="accordion-group">
                      <div class="accordion-heading"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">Field Study Location <i style="margin-top:; margin-right:; opacity:.5" class="icon-remove-sign pull-right"></i> </a> </div>
                      <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                          <table  class="table table-condensed">
                            <tbody>
                              <tr>
                                <th style="width:30%">Organization</th>
                                <td> Naval Training Systems Center <a href="#"><i class="icon-search" style="opacity:.5"></i></a></td>
                              </tr>
                              <tr>
                                <th style="width:30%">Address 1</th>
                                <td>Property, Code 773  </td>
                              </tr>
                              <tr>
                                <th>Address 2</th>
                                <td>12350 Research Parkway </td>
                              </tr>
                              <tr>
                                <th>City</th>
                                <td>Orlando</td>
                              </tr>
                              <tr>
                                <th>State</th>
                                <td>FL  </td>
                              </tr>
                              <tr>
                                <th>ZIP</th>
                                <td>32826</td>
                              </tr>
                              <tr>
                                <th>Congressional District</th>
                                <td><form>
                                    <input id='tags_4' type='text' class='tags'>
                                  </form></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                <div class="tab-pane " id="tab4">
                  <div class="well well-small"> 
                    <!--  <h4>Add location</h4>-->
                 
                     <a href="#" class="btn btn-small" onclick="message()" value="Show alert box"> find organizations... </a>
                   
                  </div>
                  <div class="accordion" id="accordion3">
                    
                    <div class="accordion-group">
                      <div class="accordion-heading"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo2"> Communications Power <i style="margin-top:; margin-right:; opacity:.5" class="icon-remove-sign pull-right"></i> </a> </div>
                      <div id="collapseTwo2" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                          <table  class="table table-condensed">
                            <tbody>
                              <tr>
                                <th style="width:30%">Organization</th>
                                <td>Communications Power</td>
                              </tr>
                              <tr>
                                <th style="width:30%">Address 1</th>
                                <td>PO Box 50750 </td>
                              </tr>
                              <tr>
                                <th>Address 2</th>
                                <td>811 Hansen</td>
                              </tr>
                              <tr>
                                <th>City</th>
                                <td>  Palo Alto</td>
                              </tr>
                              <tr>
                                <th>State</th>
                                <td>CA  </td>
                              </tr>
                              <tr>
                                <th>ZIP</th>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <th>Congressional District</th>
                                <td><form>
                                    <input id='tags_3' type='text' class='tags'>
                                  </form></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="accordion-group">
                      <div class="accordion-heading"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree3">Cleveland Clinic Organization <i style="margin-top:; margin-right:; opacity:.5" class="icon-remove-sign pull-right"></i> </a> </div>
                      <div id="collapseThree3" class="accordion-body in collapse" style="height: 0px;">
                        <div class="accordion-inner">
                          <table  class="table table-condensed">
                            <tbody>
                              <tr>
                                <th style="width:30%">Organization</th>
                                <td>Cleveland Clinic Organization</td>
                              </tr>
                              <tr>
                                <th style="width:30%">Address 1</th>
                                <td>One Clinic Center </td>
                              </tr>
                              <tr>
                                <th>Address 2</th>
                                <td>9500 Euclid Ave</td>
                              </tr>
                              <tr>
                                <th>City</th>
                                <td>Orlando</td>
                              </tr>
                              <tr>
                                <th>State</th>
                                <td>FL  </td>
                              </tr>
                              <tr>
                                <th>ZIP</th>
                                <td>32826</td>
                              </tr>
                              <tr>
                                <th>Congressional District</th>
                                <td><form>
                                    <input id='tags_4' type='text' class='tags'>
                                  </form></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
                
                
                
                
                
                
                
              </div>
              <script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
    });
</script> 
             
              
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="docControls"> <a href="prop.basics.sponsor.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.basics.deliveryinfo.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('a[href="#tab2"]').tab('show'); // Select tab by name
    });
</script>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
