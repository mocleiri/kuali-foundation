<?php
# Variables
$page = 'attachments';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader">
            <h3>Attachments</h3>
          </div>
          <div class="boxContent">

            <p class="alert alert-success">
              Your file has been uploaded!
            </p>

            <div class="boxSubheader">
              <h4>Add Attachment</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">File </label>
                <!-- File Upload -->
                <div class="controls">
                  <input class="input-file" id="fileInput" type="file">
                </div>
              </div>
              <div class="control-group">
                <!-- Select Basic -->
                <label class="control-label">Attachment Type</label>
                <div class="controls">
                  <select name="select" class="input-xlarge">
                    <option>select</option>
                    <option>Abstract</option>
                    <option>Narrative</option>
                    <option>Equipment</option>
                    <option>Bibliography</option>
                    <option>Project Summary</option>
                    <option>Budget Justification</option>
                    <option>Additional Keypersons</option>
                    <option>Additional Equipment</option>
                    <option>Personal Data</option>
                    <option>Facilities</option>
                    <option>Subaward Budget</option>
                    <option>Table of Contents</option>
                    <option>Supplementary Documentation</option>
                    <option>Other</option>
                  </select>
                </div>
              </div>
              
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Comments</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Description</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01"></label>
                <div class="controls">
                  <a href="#" class="btn btn-success btn-mini"><i class="icon-white icon-plus"></i> Add Attachment</a>
                </div>
              </div>
            </fieldset>

            <div class="boxSubheader expandControl closed ">
              <h4>Statement_of_Work.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File</dt>
                <dd>Statement_of_Work.pdf</dd>
                <dt>Attachment Type:</dt>
                <dd>Abstract</dd>
                <dt>Status</dt>
                <dd>Complete</dd>
                <dt>Contact Name</dt>
                <dd>Test User</dd>
                <dt>Uploaded By</dt>
                <dd>Test User</dd>
                <dt>Email Address</dt>
                <dd>testuser@email.com</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-03-5 19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>This is a test file.</dd>
                <dt>Description</dt>
                <dd>This is a test file.</dd>
              </dl>
            </div>
          </div>
        </div>

        <!-- <div class="box">
          <div class="boxHeader expandControl closed">
            <h3> Personnel Attachments </h3>
          </div>
          <div class="boxContent expandTarget" style=" display:none" >
            <div class="boxSubheader">
              <h4>Add Attachment</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">File </label>
                <div class="controls">
                  <input class="input-file" id="fileInput2" type="file">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">Person</label>
                <div class="controls">
                  <select name="select2" class="input-xlarge">
                    <option value="">select</option>
                    <option value="9">Haskell, Edward </option>
                    <option value="5">Hensler, Judith</option>
                    <option value="8">Rutherford, Violet</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">Attachment Type</label>
                <div class="controls">
                  <select name="select2" class="input-xlarge">
                    <option value="">select</option>
                    <option value="1">Biosketch</option>
                    <option value="2">Currentpending</option>
                    <option value="3">BudgetDetails</option>
                    <option value="4">StatementofCommitment</option>
                    <option value="5">Other</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input2">Description</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input2"></label>
                <div class="controls">
                  <a href="#" class="btn btn-inverse btn-small"><i class="icon-white icon-plus"></i> Add Attachment</a>
                </div>
              </div>
            </fieldset>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3> Internal Attachments</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:none" >
            <div class="boxSubheader">
              <h4>Add Attachment</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">File </label>
                <div class="controls">
                  <input class="input-file" id="fileInput3" type="file">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">Attachment Type</label>
                <div class="controls">
                  <select name="select3" class="input-xlarge">
                    <option value="">select</option>
                    <option value="1">Biosketch</option>
                    <option value="2">Currentpending</option>
                    <option value="3">BudgetDetails</option>
                    <option value="4">StatementofCommitment</option>
                    <option value="5">Other</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input4">Description</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input4"></label>
                <div class="controls">
                  <a href="#" class="btn btn-inverse btn-small"><i class="icon-white icon-plus"></i> Add Attachment</a>
                </div>
              </div>
            </fieldset>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3> Abstracts </h3>
          </div>
          <div class="boxContent expandTarget" style=" display:none" >
            <div class="boxSubheader">
              <h4>Add Abstract</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">Abstract Type</label>
                <div class="controls">
                  <select name="select4" class="input-xlarge">
                    <option value="">select</option>
                    <option value="1">Project Summary</option>
                    <option value="10">Equipment</option>
                    <option value="11">Other Resources</option>
                    <option value="12">Suggested Reviewers</option>
                    <option value="13">Publications</option>
                    <option value="14">Reviewers Not to Include</option>
                    <option value="15">Deviation Authorization</option>
                    <option value="16">Areas Affected</option>
                    <option value="17">Relevance</option>
                    <option value="2">Technical Abstract</option>
                    <option value="3">Layman Abstract</option>
                    <option value="4">Labs</option>
                    <option value="5">Clinical</option>
                    <option value="6">Animal</option>
                    <option value="7">Computer</option>
                    <option value="8">Office</option>
                    <option value="9">Other Facilities</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input5">Details</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input5"></label>
                <div class="controls">
                  <a href="#" class="btn btn-inverse btn-small"><i class="icon-white icon-plus"></i> Add Abstract</a>
                </div>
              </div>
            </fieldset>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
          </div>
        </div>

        <div class="box">
          <div class="boxHeader expandControl closed">
            <h3> Notes</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:none" >
            <div class="boxSubheader">
              <h4>Add Note</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">Note Topic</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input3">Note Text</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="input3"></label>
                <div class="controls">
                  <a href="#" class="btn btn-inverse btn-small"><i class="icon-white icon-plus"></i> Add Note</a>
                </div>
              </div>
            </fieldset>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
            <div class="boxSubheader expandControl closed ">
              <h4> this_file.pdf</h4>
            </div>
            <div class=" clearfix expandTarget" style="display:none">
              <dl class="table-display" style="margin-top:-13px;">
                <dt>File </dt>
                <dd> this_file.pdf </dd>
                <dt>Attachment Type:</dt>
                <dd>Bibliography</dd>
                <dt>Status</dt>
                <dd>Incomplete</dd>
                <dt>Contact Name</dt>
                <dd>Tom Clark</dd>
                <dt>Uploaded By</dt>
                <dd>thrclark</dd>
                <dt>Email Address</dt>
                <dd>thrclark@indiana.edu</dd>
                <dt>Posted Timestamp</dt>
                <dd>2013-01-18T19:33:30+00:00</dd>
                <dt>Phone Number</dt>
                <dd>123-456-7890</dd>
                <dt>Comments</dt>
                <dd>Accipe sacrificium confessionum mearum de manu</dd>
                <dt>Description</dt>
                <dd>um: sed solvis eam, cum voles, aut miserans </dd>
              </dl>
            </div>
          </div>
        </div> -->

        <div style=" padd12px; text-align:center">
          <a href="prop.compliance.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.questionnaire.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
      <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
      
    </div>
  </form>
</div>

<?php include( 'assets/inc/scripts.global.php' ) ?>

<script type="text/javascript">
    $(document).ready(function () {
  
  
  
  $(".disclose-dl1").toggle(

        function () {
            $("#dl1").slideDown(600);
            $(".disclose-dl1").html("hide");
        },

        function () {
            $("#dl1").slideUp(600);
            $(".disclose-dl1").html("show");
        });
  
	
});
</script>

</body>
</html>