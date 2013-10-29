<?php
# Variables
$page = 'questions';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->
        
        
          <h3>Questionnaire</h3>
          <div class="panel-group" id="question_set_1">
          	<div class="panel panel-default">
          		<div class="panel-heading">
          			<div class="row">
          				<div class="col-md-6">
          					<h4 class="panel-title">
          						<a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#question_set_1" href="#question_set_1_content"><span aria-hidden="true" class="icon-caret-down"></span></a>&nbsp;
          						<span class="text-muted">Question set 1</span>
          					</h4>
          				</div>
          				<div class="col-md-6">
          					<a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove remove-question"></span></a>
          				</div>
          			</div>
          		</div>
          		<div id="question_set_1_content" class="panel-collapse in">
          			<div class="panel-body">
          				<div class="question-set">
	          				<fieldset class="question">
	          					<div class="question-title">
	          						<legend>Is this application being submitted to other agencies?</legend>
	          					</div>
	          					<div class="question-helper">
	          						<p>128: Is this application being submitted to other agencies?</p>
	      						</div>
	      						<div class="question-response">
		      						<div class="question-reponse-choices">
		      							<div class="choice">
		      								<input type="radio" id="q_1_1a" name="q_1_a" value="1" class="radio-choice" /> <label for="q_1_1a">Yes</label>
		      							</div>
		      							<div class="choice">
		      								<input type="radio" id="q_1_1b" name="q_1_a" value="0" class="radio-choice" /> <label for="q_1_1b">No</label>
		      							</div>
		      						</div>
		      					</div>
	          				</fieldset>
	          				<fieldset class="question dependent" id="dependent_q_1_1a">
	          					<div class="question-title">
	          						<legend>Select a US Government Agency:</legend>
	          					</div>
	          					<div class="question-helper">
	          						<p>111: Select a US Government Agency</p>
	      						</div>
	      						<div class="question-response">
		      						<div class="question-reponse-choices">
		      							<div class="choice">
		      								<label class="off-screen" for="q_1_1_1a">Select a US government agency:</label>
		      								<select name="q_1_1_1a" id="q_1_1_1a">
		      									<option value="101">101: Agency for International Development</option>
												<option value="102">102: Air Force Research Laboratory</option>
												<option value="103">103: Army Research Laboratory</option>
												<option value="104">104: Center for Disease Control and Prevention</option>
												<option value="105">105: Coast Guard</option>
												<option value="106">106: Customs Service</option>
												<option value="107">107: Defense Advanced Research Projects Agency</option>
												<option value="108">108: Department of Agriculture (USDA)</option>
												<option value="109">109: Department of Commerce (DOC)</option>
												<option value="110">110: Department of Defense (DOD)</option>
												<option value="111">111: Department of Education (ED)</option>
												<option value="112">112: Department of Energy (DOE)</option>
												<option value="113">113: Department of Health and Human Services (HHS)</option>
												<option value="114">114: Department of Homeland Security (DHS)</option>
												<option value="115">115: Department of Justice (DOJ)</option>
												<option value="116">116: Department of State (DOS)</option>
												<option value="117">117: Department of the Air Force</option>
												<option value="118">118: Department of the Army</option>
												<option value="119">119: Department of the Interior (DOI)</option>
												<option value="120">120: Department of the Navy</option>
												<option value="121">121: Department of Transportation (DOT)</option>
												<option value="122">122: Department of Veterans Affairs (VA)</option>
												<option value="123">123: Environmental Protection Agency (EPA)</option>
												<option value="124">124: Federal Aviation Administration (FAA)</option>
												<option value="125">125: Federal Emergency Management Agency (FEMA)</option>
												<option value="126">126: Federal Maritime Commission</option>
												<option value="127"> 127: Fish and Wildlife Service</option>
												<option value="128">128: Forest Service</option>
												<option value="129">129: NASA Ames Research Center</option>
												<option value="130">130: NASA Dryden Flight Research Center</option>
												<option value="131">131: NASA Glenn Research Center</option>
												<option value="132">132: NASA Goddard Space Flight Center</option>
												<option value="133">133: NASA Headquarters</option>
												<option value="134">134: NASA Johnson Space Center</option>
												<option value="135">135: NASA Kennedy Space Center</option>
												<option value="136">136: NASA Langley Research Center</option>
												<option value="137">137: NASA Marshall Space Flight Center</option>
												<option value="138">138: NASA Stennis Space Center</option>
												<option value="139">139: National Institute of Standards and Technology (NIST)</option>
												<option value="140">140: National Institutes of Health (NIH)</option>
												<option value="141">141: National Oceanic and Atmospheric Administration (NOAA)</option>
												<option value="142">142: National Park Service</option>
												<option value="143">143: National Science Foundation (NSF)</option>
												<option value="144">144: Naval Observatory</option>
												<option value="145">145: Naval Research Laboratory</option>
												<option value="146">146: Other</option>
												<option value="147">147: Smithsonian Institution</option>
												<option value="148">148: United States Geological Survey (USGS)</option>
												<option value="149">149: United States Marine Corps</option>
												<option value="150">150: Walter Reed Army Institute Research</option></select>
		      								</select>
		      							</div>
		      						</div>
		      					</div>
	          				</fieldset>
	          			</div>
	          			<div class="question-set">
	          				<fieldset class="question">
	          					<div class="question-title">
	          						<legend>Is the proposal subject to review by state executive order 12372 process?</legend>
	          					</div>
	          					<div class="question-helper">
	          						<p>129: s the proposal subject to review by state executive order 12372 process?</p>
	      						</div>
	      						<div class="question-response">
		      						<div class="question-reponse-choices">
		      							<div class="choice">
		      								<input type="radio" id="q_1_2a" name="q_1_b" value="1" class="radio-choice" /> <label for="q_1_2a">Yes</label>
		      							</div>
		      							<div class="choice">
		      								<input type="radio" id="q_1_2b" name="q_1_b" value="0" class="radio-choice" /> <label for="q_1_2b">No</label>
		      							</div>
		      						</div>
		      					</div>
	          				</fieldset>
	          				<fieldset class="question dependent" id="dependent_q_1_2a">
	          					<div class="question-title">
	          						<legend>If Yes, please provide the date the application was made available for review (submitted to the state):</legend>
	          					</div>
	          					<div class="question-helper">
	          						<p>130: When was this application made available for review?</p>
	      						</div>
	      						<div class="question-response">
		      						<div class="question-reponse-choices">
		      							<div class="choice">
		      								<label class="off-screen" for="q_1_2_1a">Date this application was made available for review:</label>
		      								<input type="text" name="q_1_2_1a" id="q_1_2_1a" class="form-control uif-dateControl" placeholder="mm/dd/yyyy" />
		      							</div>
		      						</div>
		      					</div>
	          				</fieldset>
	          				<fieldset class="question dependent" id="dependent_q_1_2b">
	          					<div class="question-title">
	          						<legend>If No, is the program not selected for review or not covered by E.O. 12372?:</legend>
	          					</div>
	          					<div class="question-helper">
	          						<p>131: Is this program not selected for review, or not covered by E.O. 12372?</p>
	      						</div>
	      						<div class="question-response">
		      						<div class="question-reponse-choices">
		      							<div class="choice">
		      								<label class="off-screen" for="q_1_2_1b">Not selected for review or not covered:</label>
		      								<select name="q_1_2_1b" id="q_1_2_1b">
		      									<option value="1">Program is not covered by E.O. 12372</option>
		      									<option value="2">Program not selected for 12372 review</option>
		      								</select>
		      							</div>
		      						</div>
		      					</div>
	          				</fieldset>
	          			</div>
          			</div>
          		</div>
          	</div>
          </div>

          <br />
          <button class="btn btn-default btn-xs">Add a question set</button>
        
        
        
       <!-- <table class="table table-condensed table-hover">
                  <thead>
                    <tr>
                      <th>Question</th>
                      <th>Answer</th>
                      <th Nowrap>Review Date</th>
                      <th>Explanation</th>
                      <th style="width: 35px;"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Will new space or remodeling be required for this project? </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Patents: Answer &quot;N/A&quot; if there are No patents. Answer &quot;Yes&quot; if all patents have been reported. Answer &quot;No&quot; if there are patents that have Not been reported. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Is the principal investigator changing with this application? If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>For U.S. Department of Education, check &quot;Yes&quot; or &quot;No&quot; only if assistance is being requested under a program that gives special consideration to Novice applicants. Otherwise, check &quot;Not Applicable&quot;. </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Does this project have an international connection: that is, collaboration with internationally-based colleagues or institutions; significant activity outside the United States; or pertain topically to countries, regions or populations outside the United States? If so, please indicate in the explanation field the countries with which it is connected. (Used by the International Affairs Office.) </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Historical Sites Are Affected </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                    <tr>
                      <td>Does this project have an actual or potential impact on the environment? </td>
                      <td><label class="radio">
                        <input type="radio" value="Option one" name="group">
                        Yes </label>
                        <label class="radio">
                          <input type="radio" value="Option two" name="group">
                          No </label></td>
                      <td>07/15/2013</td> <td><input type="text" class="form-control input-sm"></td>
                      <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                    </tr>
                  </tbody>
                </table> -->

        <!-- // --> 
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.attachments.notes.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.budget.summary.php'" class="btn btn-primary">Save and Continue</button>
			
		</div>
		<!-- // -->

        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>





